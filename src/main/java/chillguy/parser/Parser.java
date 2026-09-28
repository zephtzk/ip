package chillguy.parser;

import chillguy.exception.ChillguyException;
import chillguy.parser.Command.Type;
import chillguy.task.Deadline;
import chillguy.task.Event;
import chillguy.task.Task;
import chillguy.task.Todo;

/**
 * Validates command syntax and constructs command data without changing the task list.
 */
public class Parser {
    // Match standalone separator tokens, including at either end to detect missing fields.
    private static final String BY_SEPARATOR = "(?<!\\S)/by(?=\\s|$)";
    private static final String FROM_SEPARATOR = "(?<!\\S)/from(?=\\s|$)";
    private static final String TO_SEPARATOR = "(?<!\\S)/to(?=\\s|$)";
    private static final String DEADLINE_FORMAT_MESSAGE =
            "Sorry, deadline tasks need this format: deadline DESCRIPTION /by DATE";
    private static final String EVENT_FORMAT_MESSAGE =
            "Sorry, event tasks need this format: event DESCRIPTION /from START /to END";

    /**
     * Parses a line into an operation and its arguments.
     * Task-number range checks belong to TaskList because they depend on the stored tasks.
     *
     * @throws ChillguyException If the command name, arguments, or task fields are invalid.
     */
    public Command parse(String input) throws ChillguyException {
        String command = input.strip();
        if (command.isEmpty()) {
            throw new ChillguyException("Please enter a command, such as todo DESCRIPTION or list.");
        }

        String[] commandParts = command.split("\\s+", 2);
        String commandName = commandParts[0];
        String arguments = commandParts.length == 2 ? commandParts[1].strip() : "";
        return switch (commandName) {
            case "bye", "list" -> {
                requireNoArguments(commandName, arguments);
                yield new Command(commandName.equals("bye") ? Type.EXIT : Type.LIST, null, 0);
            }
            case "mark" -> new Command(Type.MARK, null, parseTaskNumber(arguments, commandName));
            case "unmark" -> new Command(Type.UNMARK, null, parseTaskNumber(arguments, commandName));
            case "delete" -> new Command(Type.DELETE, null, parseTaskNumber(arguments, commandName));
            case "todo" -> new Command(Type.ADD, parseTodo(arguments), 0);
            case "deadline" -> new Command(Type.ADD, parseDeadline(arguments), 0);
            case "event" -> new Command(Type.ADD, parseEvent(arguments), 0);
            default -> throw new ChillguyException("ERROR: Unknown command.");
        };
    }

    private void requireNoArguments(String commandName, String arguments) throws ChillguyException {
        if (!arguments.isEmpty()) {
            throw new ChillguyException("The " + commandName + " command takes no extra input. Use: " + commandName);
        }
    }

    private int parseTaskNumber(String arguments, String commandName) throws ChillguyException {
        if (arguments.isEmpty()) {
            throw new ChillguyException("Please include a task number. Use: " + commandName + " INDEX");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(arguments);
        } catch (NumberFormatException exception) {
            throw new ChillguyException("Please enter one whole-number task index. Use: " + commandName + " INDEX");
        }

        return taskNumber;
    }

    private Task parseTodo(String description) throws ChillguyException {
        if (description.isBlank()) {
            throw new ChillguyException("ERROR: Description of todo cannot be empty.");
        }
        return new Todo(description);
    }

    private Task parseDeadline(String details) throws ChillguyException {
        String[] parts = details.split(BY_SEPARATOR, -1);
        if (parts.length != 2 || parts[0].isBlank() || parts[1].isBlank()) {
            throw new ChillguyException(DEADLINE_FORMAT_MESSAGE);
        }

        String description = parts[0].strip();
        String by = parts[1].strip();
        return new Deadline(description, by);
    }

    private Task parseEvent(String details) throws ChillguyException {
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

        return new Event(description, times[0].strip(), times[1].strip());
    }
}
