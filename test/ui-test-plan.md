# UI Test Plan

This file records console UI test cases for this project. Update it whenever a code change affects commands, console inputs, or expected output.

Run all cases with Java 25 and Python 3 using `python test/run-ui-tests.py`. The runner compiles the app, compares exact output and stderr, stops at the first failure, and writes `test/ui-test-transcript.md`.

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

- Notes: The runner compiles all Java files recursively under `src/main/java`, including subpackages, before running the cases.

### Delete Mixed Task Types

- Aim: Verifies the Level 6 example removes the middle event, preserves task order and status, and supports deleting the last task and a completed deadline using the updated numbering.
- Command: `java -cp out chillguy.Chillguy`
- Input:

```text
todo read book
deadline return book /by June 6th
event project meeting /from Aug 6th 2pm /to 4pm
todo join sports club
todo borrow book
mark 1
mark 2
mark 4
list
delete 3
list
unmark 3
delete 4
delete 2
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
  [D][ ] return book (by: June 6th)
Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] join sports club
Now you have 4 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] borrow book
Now you have 5 tasks in the list.
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] read book
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [D][X] return book (by: June 6th)
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] join sports club
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][X] read book
2.[D][X] return book (by: June 6th)
3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
4.[T][X] join sports club
5.[T][ ] borrow book
____________________________________________________________
____________________________________________________________
Noted. I've removed this task:
  [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
Now you have 4 tasks in the list.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][X] read book
2.[D][X] return book (by: June 6th)
3.[T][X] join sports club
4.[T][ ] borrow book
____________________________________________________________
____________________________________________________________
OK, I've marked this task as not done yet:
  [T][ ] join sports club
____________________________________________________________
____________________________________________________________
Noted. I've removed this task:
  [T][ ] borrow book
Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
Noted. I've removed this task:
  [D][X] return book (by: June 6th)
Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][X] read book
2.[T][ ] join sports club
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

- Notes: The initial list and `delete 3` reproduce the supplied Level 6 example. Every subsequent index refers to the current list.

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

### Reject Malformed Deadline

- Aim: Verifies that a deadline command without `/by` shows an error message instead of crashing.
- Command: `java -cp out chillguy.Chillguy`
- Input:

```text
deadline byebye /today 6pm
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
Sorry, deadline tasks need this format: deadline DESCRIPTION /by DATE
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

- Notes: The app should keep running after the error and handle the next command.

### Reject Malformed Event

- Aim: Verifies that an event command without `/to` shows an error message instead of crashing.
- Command: `java -cp out chillguy.Chillguy`
- Input:

```text
event meeting /from Monday 2pm
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
Sorry, event tasks need this format: event DESCRIPTION /from START /to END
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

- Notes: The app should keep running after the error and handle the next command.

### Reject Unknown and Empty Commands

- Aim: Verifies errors never create tasks, exact command names are required, extra arguments do not exit, and surrounding spaces and tabs are accepted.
- Command: `java -cp out chillguy.Chillguy`
- Input:

```text

   	  
todo
todo   
blah
read a book
todos book
TODO book
list
  todo	read book  
list extra
bye now
mark	1
blah
  list  
unmark  1 
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
Please enter a command, such as todo DESCRIPTION or list.
____________________________________________________________
____________________________________________________________
Please enter a command, such as todo DESCRIPTION or list.
____________________________________________________________
____________________________________________________________
ERROR: Description of todo cannot be empty.
____________________________________________________________
____________________________________________________________
ERROR: Description of todo cannot be empty.
____________________________________________________________
____________________________________________________________
ERROR: Unknown command.
____________________________________________________________
____________________________________________________________
ERROR: Unknown command.
____________________________________________________________
____________________________________________________________
ERROR: Unknown command.
____________________________________________________________
____________________________________________________________
ERROR: Unknown command.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.
____________________________________________________________
____________________________________________________________
The list command takes no extra input. Use: list
____________________________________________________________
____________________________________________________________
The bye command takes no extra input. Use: bye
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] read book
____________________________________________________________
____________________________________________________________
ERROR: Unknown command.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][X] read book
____________________________________________________________
____________________________________________________________
OK, I've marked this task as not done yet:
  [T][ ] read book
____________________________________________________________
____________________________________________________________
Noted. I've removed this task:
  [T][ ] read book
Now you have 0 tasks in the list.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

- Notes: Literal tabs and spaces in the input are intentional. No errors may alter task count or status.

### Validate Task Numbers and Preserve State

- Aim: Verifies missing, nonnumeric, fractional, multiple, overflowing, zero, negative, and out-of-range indices for all three modifying commands, including empty and shifted lists.
- Command: `java -cp out chillguy.Chillguy`
- Input:

```text
mark
mark 1
unmark
unmark 1
delete
delete 1
todo first
todo second
mark 2
mark abc
mark 1.5
mark 1 2
mark 999999999999999999999
mark -2147483649
mark 0
mark -1
mark -2147483648
mark 3
mark 2147483647
list
unmark abc
unmark 1.5
unmark 1 2
unmark 999999999999999999999
unmark -2147483649
unmark 0
unmark -1
unmark -2147483648
unmark 3
unmark 2147483647
list
delete abc
delete 1.5
delete 1 2
delete 999999999999999999999
delete -2147483649
delete 0
delete -1
delete -2147483648
delete 3
delete 2147483647
list
unmark 2
delete 1
delete 2
mark 1
list
delete 1
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
Please include a task number. Use: mark INDEX
____________________________________________________________
____________________________________________________________
Your task list is empty. Add a task first with todo DESCRIPTION.
____________________________________________________________
____________________________________________________________
Please include a task number. Use: unmark INDEX
____________________________________________________________
____________________________________________________________
Your task list is empty. Add a task first with todo DESCRIPTION.
____________________________________________________________
____________________________________________________________
Please include a task number. Use: delete INDEX
____________________________________________________________
____________________________________________________________
Your task list is empty. Add a task first with todo DESCRIPTION.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] first
Now you have 1 task in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] second
Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] second
____________________________________________________________
____________________________________________________________
Please enter one whole-number task index. Use: mark INDEX
____________________________________________________________
____________________________________________________________
Please enter one whole-number task index. Use: mark INDEX
____________________________________________________________
____________________________________________________________
Please enter one whole-number task index. Use: mark INDEX
____________________________________________________________
____________________________________________________________
Please enter one whole-number task index. Use: mark INDEX
____________________________________________________________
____________________________________________________________
Please enter one whole-number task index. Use: mark INDEX
____________________________________________________________
____________________________________________________________
Please choose a task number from 1 to 2. Use list to see them.
____________________________________________________________
____________________________________________________________
Please choose a task number from 1 to 2. Use list to see them.
____________________________________________________________
____________________________________________________________
Please choose a task number from 1 to 2. Use list to see them.
____________________________________________________________
____________________________________________________________
Please choose a task number from 1 to 2. Use list to see them.
____________________________________________________________
____________________________________________________________
Please choose a task number from 1 to 2. Use list to see them.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] first
2.[T][X] second
____________________________________________________________
____________________________________________________________
Please enter one whole-number task index. Use: unmark INDEX
____________________________________________________________
____________________________________________________________
Please enter one whole-number task index. Use: unmark INDEX
____________________________________________________________
____________________________________________________________
Please enter one whole-number task index. Use: unmark INDEX
____________________________________________________________
____________________________________________________________
Please enter one whole-number task index. Use: unmark INDEX
____________________________________________________________
____________________________________________________________
Please enter one whole-number task index. Use: unmark INDEX
____________________________________________________________
____________________________________________________________
Please choose a task number from 1 to 2. Use list to see them.
____________________________________________________________
____________________________________________________________
Please choose a task number from 1 to 2. Use list to see them.
____________________________________________________________
____________________________________________________________
Please choose a task number from 1 to 2. Use list to see them.
____________________________________________________________
____________________________________________________________
Please choose a task number from 1 to 2. Use list to see them.
____________________________________________________________
____________________________________________________________
Please choose a task number from 1 to 2. Use list to see them.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] first
2.[T][X] second
____________________________________________________________
____________________________________________________________
Please enter one whole-number task index. Use: delete INDEX
____________________________________________________________
____________________________________________________________
Please enter one whole-number task index. Use: delete INDEX
____________________________________________________________
____________________________________________________________
Please enter one whole-number task index. Use: delete INDEX
____________________________________________________________
____________________________________________________________
Please enter one whole-number task index. Use: delete INDEX
____________________________________________________________
____________________________________________________________
Please enter one whole-number task index. Use: delete INDEX
____________________________________________________________
____________________________________________________________
Please choose a task number from 1 to 2. Use list to see them.
____________________________________________________________
____________________________________________________________
Please choose a task number from 1 to 2. Use list to see them.
____________________________________________________________
____________________________________________________________
Please choose a task number from 1 to 2. Use list to see them.
____________________________________________________________
____________________________________________________________
Please choose a task number from 1 to 2. Use list to see them.
____________________________________________________________
____________________________________________________________
Please choose a task number from 1 to 2. Use list to see them.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] first
2.[T][X] second
____________________________________________________________
____________________________________________________________
OK, I've marked this task as not done yet:
  [T][ ] second
____________________________________________________________
____________________________________________________________
Noted. I've removed this task:
  [T][ ] first
Now you have 1 task in the list.
____________________________________________________________
____________________________________________________________
Please choose a task number from 1 to 1. Use list to see them.
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] second
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][X] second
____________________________________________________________
____________________________________________________________
Noted. I've removed this task:
  [T][X] second
Now you have 0 tasks in the list.
____________________________________________________________
____________________________________________________________
Your task list is empty. Add a task first with todo DESCRIPTION.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

- Notes: List output after each invalid group must preserve both original tasks and their completion status.

### Validate Task Fields and Recover

- Aim: Verifies missing and blank descriptions and times, missing or repeated separators, reversed event separators, and successful recovery with all task types.
- Command: `java -cp out chillguy.Chillguy`
- Input:

```text
todo keep
deadline
deadline   
deadline /by Sunday
deadline task
deadline task /by
deadline task /by   
deadline task /by Sunday /by Monday
deadline task /bySunday
list
deadline	return book	/by	Sunday  
mark 2
event
event   
event /from Monday /to Tuesday
event meeting
event meeting /from Monday
event meeting /to Tuesday
event meeting /from /to Tuesday
event meeting /from Monday /to
event meeting /from   /to Tuesday
event meeting /from Monday /to   
event meeting /to Tuesday /from Monday
event meeting /from Monday /from Tuesday /to Wednesday
event meeting /from Monday /to Tuesday /to Wednesday
event meeting /fromMonday /to Tuesday
list
event	meeting	/from	Mon 2pm	/to	4pm  
unmark 2
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
  [T][ ] keep
Now you have 1 task in the list.
____________________________________________________________
____________________________________________________________
Sorry, deadline tasks need this format: deadline DESCRIPTION /by DATE
____________________________________________________________
____________________________________________________________
Sorry, deadline tasks need this format: deadline DESCRIPTION /by DATE
____________________________________________________________
____________________________________________________________
Sorry, deadline tasks need this format: deadline DESCRIPTION /by DATE
____________________________________________________________
____________________________________________________________
Sorry, deadline tasks need this format: deadline DESCRIPTION /by DATE
____________________________________________________________
____________________________________________________________
Sorry, deadline tasks need this format: deadline DESCRIPTION /by DATE
____________________________________________________________
____________________________________________________________
Sorry, deadline tasks need this format: deadline DESCRIPTION /by DATE
____________________________________________________________
____________________________________________________________
Sorry, deadline tasks need this format: deadline DESCRIPTION /by DATE
____________________________________________________________
____________________________________________________________
Sorry, deadline tasks need this format: deadline DESCRIPTION /by DATE
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] keep
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [D][ ] return book (by: Sunday)
Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [D][X] return book (by: Sunday)
____________________________________________________________
____________________________________________________________
Sorry, event tasks need this format: event DESCRIPTION /from START /to END
____________________________________________________________
____________________________________________________________
Sorry, event tasks need this format: event DESCRIPTION /from START /to END
____________________________________________________________
____________________________________________________________
Sorry, event tasks need this format: event DESCRIPTION /from START /to END
____________________________________________________________
____________________________________________________________
Sorry, event tasks need this format: event DESCRIPTION /from START /to END
____________________________________________________________
____________________________________________________________
Sorry, event tasks need this format: event DESCRIPTION /from START /to END
____________________________________________________________
____________________________________________________________
Sorry, event tasks need this format: event DESCRIPTION /from START /to END
____________________________________________________________
____________________________________________________________
Sorry, event tasks need this format: event DESCRIPTION /from START /to END
____________________________________________________________
____________________________________________________________
Sorry, event tasks need this format: event DESCRIPTION /from START /to END
____________________________________________________________
____________________________________________________________
Sorry, event tasks need this format: event DESCRIPTION /from START /to END
____________________________________________________________
____________________________________________________________
Sorry, event tasks need this format: event DESCRIPTION /from START /to END
____________________________________________________________
____________________________________________________________
Sorry, event tasks need this format: event DESCRIPTION /from START /to END
____________________________________________________________
____________________________________________________________
Sorry, event tasks need this format: event DESCRIPTION /from START /to END
____________________________________________________________
____________________________________________________________
Sorry, event tasks need this format: event DESCRIPTION /from START /to END
____________________________________________________________
____________________________________________________________
Sorry, event tasks need this format: event DESCRIPTION /from START /to END
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] keep
2.[D][X] return book (by: Sunday)
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [E][ ] meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
OK, I've marked this task as not done yet:
  [D][ ] return book (by: Sunday)
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] keep
2.[D][ ] return book (by: Sunday)
3.[E][ ] meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

- Notes: Dates and times remain free-form strings. Each required separator must be a separate token and occur exactly once.

### Grow Task List Beyond 100 Tasks

- Aim: Verifies all task types can be added beyond 100 tasks, deletion renumbers a large list, and marking, unmarking, and adding still work after deletion.
- Command: `java -cp out chillguy.Chillguy`
- Input file: `test/fixtures/capacity.input.txt`
- Expected output file: `test/fixtures/capacity.expected.txt`

- Notes: Fixture files contain the complete literal session and exact output; no output is omitted from comparison.

### End of Input After Error

- Aim: Verifies the app exits cleanly when input ends after an error without a bye command.
- Command: `java -cp out chillguy.Chillguy`
- Input:

```text
todo
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
ERROR: Description of todo cannot be empty.
____________________________________________________________
```

- Notes: The process receives EOF after the last input line; no goodbye message is expected.

### End of Input Without Commands

- Aim: Verifies EOF at startup prints only the greeting and exits cleanly.
- Command: `java -cp out chillguy.Chillguy`
- Input:

```text
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
```

- Notes: Input is empty; no command or goodbye output is expected.
