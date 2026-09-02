package chillguy;

import java.util.Scanner;

/**
 * Runs the Chillguy chatbot.
 */
public class Chillguy {
    private static final int MAX_TASKS = 100;
    private static final int SEPARATOR_LENGTH = 60;
    private static final String SEPARATOR = "_".repeat(SEPARATOR_LENGTH);
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND_PREFIX = "mark ";
    private static final String UNMARK_COMMAND_PREFIX = "unmark ";
    private static final String DELETE_COMMAND_PREFIX = "delete ";
    private static final String TODO_COMMAND_PREFIX = "todo ";
    private static final String DEADLINE_COMMAND_PREFIX = "deadline ";
    private static final String EVENT_COMMAND_PREFIX = "event ";
    private static final String BY_SEPARATOR = " /by ";
    private static final String FROM_SEPARATOR = " /from ";
    private static final String TO_SEPARATOR = " /to ";
    private static final String BANNER = "   _____ _   _ ___ _     _      _____ _   _ __   __\n"
            + "  / ____| | | |_ _| |   | |    / ____| | | |\\ \\ / /\n"
            + " | |    | |_| || || |   | |   | |  __| | | | \\ V /\n"
            + " | |___ |  _  || || |___| |___| | |_ | |_| |  | |\n"
            + "  \\____||_| |_|___|_____|______\\_____|____/   |_|";

    private final Task[] tasks = new Task[MAX_TASKS];
    private int taskCount;

    /**
     * Starts the chatbot and processes commands until the user enters {@code bye}.
     * Ordinary text is stored as a task, {@code list} displays all stored tasks,
     * {@code mark INDEX} marks a task as done, {@code unmark INDEX} marks a task as not done,
     * {@code delete INDEX} removes a task, {@code todo DESCRIPTION} adds a todo task,
     * {@code deadline DESCRIPTION /by DATE} adds a deadline task, and
     * {@code event DESCRIPTION /from START /to END} adds an event task.
     *
     * @param args Command-line arguments, which are not used.
     */
    public static void main(String[] args) {
        new Chillguy().run();
    }

    private void run() {
        showGreeting();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            System.out.println(SEPARATOR);

            boolean shouldExit = handleCommand(command);
            if (shouldExit) {
                break;
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

    private boolean handleCommand(String command) {
        if (command.equals(EXIT_COMMAND)) {
            showExitMessage();
            return true;
        }

        if (command.equals(LIST_COMMAND)) {
            showTasks();
        } else if (command.startsWith(MARK_COMMAND_PREFIX)) {
            markTaskAsDone(command);
        } else if (command.startsWith(UNMARK_COMMAND_PREFIX)) {
            markTaskAsNotDone(command);
        } else if (command.startsWith(DELETE_COMMAND_PREFIX)) {
            deleteTask(command);
        } else if (command.startsWith(TODO_COMMAND_PREFIX)) {
            addTodo(command);
        } else if (command.startsWith(DEADLINE_COMMAND_PREFIX)) {
            addDeadline(command);
        } else if (command.startsWith(EVENT_COMMAND_PREFIX)) {
            addEvent(command);
        } else {
            addTask(new Todo(command));
        }

        return false;
    }

    private void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    private void showTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }

    private void markTaskAsDone(String command) {
        int taskIndex = getTaskIndex(command, MARK_COMMAND_PREFIX);
        tasks[taskIndex].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks[taskIndex]);
    }

    private void markTaskAsNotDone(String command) {
        int taskIndex = getTaskIndex(command, UNMARK_COMMAND_PREFIX);
        tasks[taskIndex].markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks[taskIndex]);
    }

    private void deleteTask(String command) {
        int taskIndex = getTaskIndex(command, DELETE_COMMAND_PREFIX);
        Task deletedTask = tasks[taskIndex];

        for (int i = taskIndex; i < taskCount - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        taskCount--;
        tasks[taskCount] = null;

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + deletedTask);
        System.out.println("Now you have " + getTaskCountLabel() + " in the list.");
    }

    private String getTaskCountLabel() {
        if (taskCount == 1) {
            return "1 task";
        }

        return taskCount + " tasks";
    }

    private int getTaskIndex(String command, String commandPrefix) {
        int taskNumber = Integer.parseInt(command.substring(commandPrefix.length()));
        return taskNumber - 1;
    }

    private void addTodo(String command) {
        String description = command.substring(TODO_COMMAND_PREFIX.length());
        addTask(new Todo(description));
    }

    private void addDeadline(String command) {
        String details = command.substring(DEADLINE_COMMAND_PREFIX.length());
        int byIndex = details.indexOf(BY_SEPARATOR);
        String description = details.substring(0, byIndex);
        String by = details.substring(byIndex + BY_SEPARATOR.length());
        addTask(new Deadline(description, by));
    }

    private void addEvent(String command) {
        String details = command.substring(EVENT_COMMAND_PREFIX.length());
        int fromIndex = details.indexOf(FROM_SEPARATOR);
        int toIndex = details.indexOf(TO_SEPARATOR);
        String description = details.substring(0, fromIndex);
        String from = details.substring(fromIndex + FROM_SEPARATOR.length(), toIndex);
        String to = details.substring(toIndex + TO_SEPARATOR.length());
        addTask(new Event(description, from, to));
    }

    private void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + getTaskCountLabel() + " in the list.");
    }
}
