import java.util.Scanner;

/**
 * Runs the Chillguy chatbot.
 */
public class Chillguy {
    private static final int MAX_TASKS = 100;

    /**
     * Starts the chatbot and processes commands until the user enters {@code bye}.
     * Ordinary text is stored as a task, {@code list} displays all stored tasks,
     * {@code mark INDEX} marks a task as done, and {@code unmark INDEX} marks a task as not done.
     *
     * @param args command-line arguments, which are not used
     */
    public static void main(String[] args) {
        String separator = "_".repeat(60);
        String banner = "   _____ _   _ ___ _     _      _____ _   _ __   __\n"
                + "  / ____| | | |_ _| |   | |    / ____| | | |\\ \\ / /\n"
                + " | |    | |_| || || |   | |   | |  __| | | | \\ V /\n"
                + " | |___ |  _  || || |___| |___| | |_ | |_| |  | |\n"
                + "  \\____||_| |_|___|_____|______\\_____|____/   |_|";

        System.out.println(separator);
        System.out.println(banner);
        System.out.println("Hello! I'm Chillguy.");
        System.out.println("What can I do for you?");
        System.out.println(separator);

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            System.out.println(separator);

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(separator);
                break;
            }

            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
            } else if (command.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(command.substring("mark ".length()));
                int taskIndex = taskNumber - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[taskIndex]);
            } else if (command.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(command.substring("unmark ".length()));
                int taskIndex = taskNumber - 1;
                tasks[taskIndex].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[taskIndex]);
            } else {
                tasks[taskCount] = new Task(command);
                taskCount++;
                System.out.println("added: " + command);
            }

            System.out.println(separator);
        }
    }
}
