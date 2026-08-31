import java.util.Scanner;

/**
 * Runs the Chillguy chatbot.
 */
public class Chillguy {
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
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(separator);
                break;
            }

            System.out.println(command);
            System.out.println(separator);
        }
    }
}
