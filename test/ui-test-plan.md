# UI Test Plan

This file records console UI test cases for this project. Update it whenever a code change affects commands, console inputs, or expected output.

## Test Case Format

Each test case should include:

- Aim: What behavior the test verifies.
- Command: The command to run from the repository root.
- Input: Console input to provide, or `None`.
- Expected output: The exact expected console output.
- Notes: Setup, cleanup, assumptions, or intentionally ignored output.

## Test Cases

### Delete Task

- Aim: Verifies that `delete INDEX` removes the selected task and the remaining tasks shift up in the list.
- Command: `java -cp out chillguy.Chillguy`
- Input:

```text
todo read book
todo return book
delete 1
list
bye
```

- Expected output:

```text
____________________________________________________________
   _____ _   _ ___ _     _      _____ _   _ __   __
  / ____| | | |_ _| |   | |    / ____| | | |\ \ / /
 | |    | |_| || || |   | |   | |  __| | | | \ V /
 | |___ |  _  || || |___| |___| | |_ | |_| |  | |
  \____||_| |_|___|_____|______\_____|____/   |_|
Hello! I'm Chillguy.
What can I do for you?
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] return book
Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
Noted. I've removed this task:
  [T][ ] read book
Now you have 1 task in the list.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] return book
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

- Notes: Compile first with `javac -d out src\main\java\chillguy\*.java`.

### Add Level 4 Task Types

- Aim: Verifies that `todo`, `deadline`, and `event` commands create tasks with the correct type labels and details.
- Command: `java -cp out chillguy.Chillguy`
- Input:

```text
todo borrow book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
mark 1
list
bye
```

- Expected output:

```text
____________________________________________________________
   _____ _   _ ___ _     _      _____ _   _ __   __
  / ____| | | |_ _| |   | |    / ____| | | |\ \ / /
 | |    | |_| || || |   | |   | |  __| | | | \ V /
 | |___ |  _  || || |___| |___| | |_ | |_| |  | |
  \____||_| |_|___|_____|______\_____|____/   |_|
Hello! I'm Chillguy.
What can I do for you?
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] borrow book
Now you have 1 task in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [D][ ] return book (by: Sunday)
Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] borrow book
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][X] borrow book
2.[D][ ] return book (by: Sunday)
3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

- Notes: Dates and times are treated as plain strings.
