package chillguy;

/**
 * Represents a task that needs to be done before a specific date or time.
 */
public class Deadline extends Task {
    private static final String TASK_TYPE_ICON = "D";

    private final String by;

    /**
     * Creates a deadline task that is not done yet.
     *
     * @param description Description of the deadline task.
     * @param by Date or time by which the task should be done.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
