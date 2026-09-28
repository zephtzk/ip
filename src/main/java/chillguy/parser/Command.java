package chillguy.parser;

import chillguy.task.Task;

/**
 * Carries a parsed command from the parser to the application without performing it.
 *
 * @param type Operation to perform.
 * @param task New task for ADD, or null for other operations.
 * @param taskNumber One-based task number for MARK, UNMARK, or DELETE; zero otherwise.
 */
public record Command(Type type, Task task, int taskNumber) {
    /**
     * Identifies the operations supported by the chatbot.
     */
    public enum Type {
        EXIT, LIST, ADD, MARK, UNMARK, DELETE
    }
}
