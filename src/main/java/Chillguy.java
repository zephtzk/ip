import java.util.Scanner;

/**
 * Runs the Chillguy chatbot.
 */
public class Chillguy {
    private static final int MAX_TASKS = 100;

    /**
     * Starts the chatbot and processes commands until the user enters {@code bye}.
     * Ordinary text is stored as a task, while {@code list} displays all stored tasks.
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
        String[] tasks = new String[MAX_TASKS];
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
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[taskCount] = command;
                taskCount++;
                System.out.println("added: " + command);
            }

            System.out.println(separator);
        }
    }
}
