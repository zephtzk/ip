package chillguy;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import chillguy.exception.ChillguyException;
import chillguy.storage.Storage;
import chillguy.task.Deadline;
import chillguy.task.Event;
import chillguy.task.Task;
import chillguy.task.Todo;

/**
 * Runs the Chillguy chatbot.
 */
public class Chillguy {
    private static final int SEPARATOR_LENGTH = 60;
    private static final String SEPARATOR = "_".repeat(SEPARATOR_LENGTH);
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    // Match standalone separator tokens, including at either end to detect missing fields.
    private static final String BY_SEPARATOR = "(?<!\\S)/by(?=\\s|$)";
    private static final String FROM_SEPARATOR = "(?<!\\S)/from(?=\\s|$)";
    private static final String TO_SEPARATOR = "(?<!\\S)/to(?=\\s|$)";
    private static final String DEADLINE_FORMAT_MESSAGE =
            "Sorry, deadline tasks need this format: deadline DESCRIPTION /by DATE";
    private static final String EVENT_FORMAT_MESSAGE =
            "Sorry, event tasks need this format: event DESCRIPTION /from START /to END";
    private static final String BANNER = """
               _____ _   _ ___ _     _      _____ _   _ __   __
              / ____| | | |_ _| |   | |    / ____| | | |\\ \\ / /
             | |    | |_| || || |   | |   | |  __| | | | \\ V /
             | |___ |  _  || || |___| |___| | |_ | |_| |  | |
              \\____||_| |_|___|_____|______\\_____|____/   |_|\
            """;

    private final ArrayList<Task> tasks = new ArrayList<>();
    private final Storage storage = new Storage(Path.of("data", "chillguy.txt"));

    /**
     * Starts the chatbot and processes commands until the user enters {@code bye}.
     * {@code list} displays all stored tasks,
     * {@code mark INDEX} marks a task as done, {@code unmark INDEX} marks a task as not done,
     * {@code delete INDEX} removes a task, {@code todo DESCRIPTION} adds a todo task,
     * {@code deadline DESCRIPTION /by DATE} adds a deadline task, and
     * {@code event DESCRIPTION /from START /to END} adds an event task.
     */
    static void main() {
        new Chillguy().run();
    }

    private void run() {
        showGreeting();
        try {
            tasks.addAll(storage.load());
        } catch (ChillguyException exception) {
            showError(exception.getMessage());
            System.out.println("Your saved file has not been changed.");
            System.out.println(SEPARATOR);
            return;
        }

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().strip();
            System.out.println(SEPARATOR);

            try {
                boolean shouldExit = handleCommand(command);
                if (shouldExit) {
                    break;
                }
            } catch (ChillguyException exception) {
                showError(exception.getMessage());
            }

            System.out.println(SEPARATOR);
        }
    }

    private void showGreeting() {
        System.out.println(SEPARATOR);
        System.out.println(BANNER);
        System.out.println("Hello! I'm Chillguy.");
        System.out.println("What can I do for you?");
        System.out.println(SEPARATOR);
    }

    /**
     * Dispatches a command and reports invalid input before it can change the task list.
     */
    private boolean handleCommand(String command) throws ChillguyException {
        if (command.isEmpty()) {
            throw new ChillguyException("Please enter a command, such as todo DESCRIPTION or list.");
        }

        String[] commandParts = command.split("\\s+", 2);
        String commandName = commandParts[0];
        String arguments = commandParts.length == 2 ? commandParts[1].strip() : "";
        switch (commandName) {
            case EXIT_COMMAND -> {
                requireNoArguments(commandName, arguments);
                showExitMessage();
                return true;
            }
            case LIST_COMMAND -> {
                requireNoArguments(commandName, arguments);
                showTasks();
            }
            case "mark" -> markTaskAsDone(arguments);
            case "unmark" -> markTaskAsNotDone(arguments);
            case "delete" -> deleteTask(arguments);
            case "todo" -> addTodo(arguments);
            case "deadline" -> addDeadline(arguments);
            case "event" -> addEvent(arguments);
            default -> throw new ChillguyException("ERROR: Unknown command.");
        }

        return false;
    }

    private void requireNoArguments(String commandName, String arguments) throws ChillguyException {
        if (!arguments.isEmpty()) {
            throw new ChillguyException("The " + commandName + " command takes no extra input. Use: " + commandName);
        }
    }

    private void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    private void showTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    private void markTaskAsDone(String arguments) throws ChillguyException {
        int taskIndex = getTaskIndex(arguments, "mark");
        updateTaskStatus(tasks.get(taskIndex), true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(taskIndex));
    }

    private void markTaskAsNotDone(String arguments) throws ChillguyException {
        int taskIndex = getTaskIndex(arguments, "unmark");
        updateTaskStatus(tasks.get(taskIndex), false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(taskIndex));
    }

    /**
     * Saves a changed completion status, restoring the old status if saving fails.
     */
    private void updateTaskStatus(Task task, boolean isDone) throws ChillguyException {
        boolean wasDone = task.isDone();
        if (wasDone == isDone) {
            return;
        }
        setTaskStatus(task, isDone);
        try {
            storage.save(tasks);
        } catch (ChillguyException exception) {
            setTaskStatus(task, wasDone);
            throw exception;
        }
    }

    private void setTaskStatus(Task task, boolean isDone) {
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
    }

    /**
     * Removes the selected task and reports its details and the remaining task count.
     */
    private void deleteTask(String arguments) throws ChillguyException {
        int taskIndex = getTaskIndex(arguments, "delete");
        Task deletedTask = tasks.remove(taskIndex);
        try {
            storage.save(tasks);
        } catch (ChillguyException exception) {
            tasks.add(taskIndex, deletedTask);
            throw exception;
        }

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + deletedTask);
        System.out.println("Now you have " + getTaskCountLabel() + " in the list.");
    }

    private String getTaskCountLabel() {
        if (tasks.size() == 1) {
            return "1 task";
        }

        return tasks.size() + " tasks";
    }

    /**
     * Converts a user-facing task number to a list index only after checking its range.
     */
    private int getTaskIndex(String arguments, String commandName) throws ChillguyException {
        if (arguments.isEmpty()) {
            throw new ChillguyException("Please include a task number. Use: " + commandName + " INDEX");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(arguments);
        } catch (NumberFormatException exception) {
            throw new ChillguyException("Please enter one whole-number task index. Use: " + commandName + " INDEX");
        }

        if (tasks.isEmpty()) {
            throw new ChillguyException("Your task list is empty. Add a task first with todo DESCRIPTION.");
        }
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new ChillguyException("Please choose a task number from 1 to " + tasks.size()
                    + ". Use list to see them.");
        }
        return taskNumber - 1;
    }

    private void addTodo(String description) throws ChillguyException {
        if (description.isBlank()) {
            throw new ChillguyException("ERROR: Description of todo cannot be empty.");
        }
        addTask(new Todo(description));
    }

    private void addDeadline(String details) throws ChillguyException {
        String[] parts = details.split(BY_SEPARATOR, -1);
        if (parts.length != 2 || parts[0].isBlank() || parts[1].isBlank()) {
            throw new ChillguyException(DEADLINE_FORMAT_MESSAGE);
        }

        String description = parts[0].strip();
        String by = parts[1].strip();
        addTask(new Deadline(description, by));
    }

    private void addEvent(String details) throws ChillguyException {
        // Keep empty fields so missing descriptions or times cannot become valid tasks.
        String[] fromParts = details.split(FROM_SEPARATOR, -1);
        String[] toParts = details.split(TO_SEPARATOR, -1);
        if (fromParts.length != 2 || toParts.length != 2
                || fromParts[0].split(TO_SEPARATOR, -1).length != 1) {
            throw new ChillguyException(EVENT_FORMAT_MESSAGE);
        }

        String description = fromParts[0].strip();
        String[] times = fromParts[1].split(TO_SEPARATOR, -1);
        if (description.isEmpty() || times[0].isBlank() || times[1].isBlank()) {
            throw new ChillguyException(EVENT_FORMAT_MESSAGE);
        }

        addTask(new Event(description, times[0].strip(), times[1].strip()));
    }

    /**
     * Saves a new task before confirming it, undoing the addition if saving fails.
     */
    private void addTask(Task task) throws ChillguyException {
        tasks.add(task);
        try {
            storage.save(tasks);
        } catch (ChillguyException exception) {
            tasks.removeLast();
            throw exception;
        }
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + getTaskCountLabel() + " in the list.");
    }

    private void showError(String message) {
        System.out.println(message);
    }
}
