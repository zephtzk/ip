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
     * {@code mark INDEX} marks a task as done, and {@code unmark INDEX} marks a task as not done.
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
        } else {
            addTask(command);
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

    private int getTaskIndex(String command, String commandPrefix) {
        int taskNumber = Integer.parseInt(command.substring(commandPrefix.length()));
        return taskNumber - 1;
    }

    private void addTask(String command) {
        tasks[taskCount] = new Task(command);
        taskCount++;
        System.out.println("added: " + command);
    }
}
