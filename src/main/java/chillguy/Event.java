package chillguy;

/**
 * Represents a task that starts and ends at specific dates or times.
 */
public class Event extends Task {
    private static final String TASK_TYPE_ICON = "E";

    private final String from;
    private final String to;

    /**
     * Creates an event task that is not done yet.
     *
     * @param description Description of the event task.
     * @param from Date or time when the event starts.
     * @param to Date or time when the event ends.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
