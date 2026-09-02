package chillguy;

/**
 * Represents a task with a description and done status.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Creates a task that is not done yet.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon shown in task lists.
     *
     * @return {@code X} if the task is done, or a blank space otherwise.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the icon representing this task's type.
     *
     * @return Task type icon.
     */
    public abstract String getTaskTypeIcon();

    @Override
    public String toString() {
        return "[" + getTaskTypeIcon() + "][" + getStatusIcon() + "] " + description;
    }
}
