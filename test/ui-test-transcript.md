# UI Test Transcript

```text
java -version
openjdk version "25.0.4" 2026-07-21 LTS
OpenJDK Runtime Environment Temurin-25.0.4+7 (build 25.0.4+7-LTS)
OpenJDK 64-Bit Server VM Temurin-25.0.4+7 (build 25.0.4+7-LTS, mixed mode, sharing)
```

```text
javac -version
javac 25.0.4
```

Build: `javac -d out src/main/java/chillguy/Chillguy.java src/main/java/chillguy/exception/ChillguyException.java src/main/java/chillguy/storage/Storage.java src/main/java/chillguy/task/Deadline.java src/main/java/chillguy/task/Event.java src/main/java/chillguy/task/Task.java src/main/java/chillguy/task/Todo.java`

Fat JAR build: `"C:\Users\zepht\Documents\CS2113 iP\ip\gradlew.bat" --console=plain shadowJar`

```text
> Task :compileJava UP-TO-DATE
> Task :processResources NO-SOURCE
> Task :classes UP-TO-DATE
> Task :shadowJar UP-TO-DATE

BUILD SUCCESSFUL in 1s
2 actionable tasks: 2 up-to-date
Consider enabling configuration cache to speed up this build: https://docs.gradle.org/9.7.1/userguide/configuration_cache_enabling.html
```

Exit code: 0

## Run the Fat JAR: PASS

Command: `java -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8 -jar "C:\Users\zepht\Documents\CS2113 iP\ip\build\libs\chillguy-all.jar"`

Working directory: isolated sandbox `0`, storage session `0`.

Input:

```text
todo read book
list
bye
```

Actual output:

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
Here are the tasks in your list:
1.[T][ ] read book
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

Exit code: 0

Stderr: ''

- PASS: saved file contents
- PASS: no temporary save files remain

## Delete Task: PASS

Command: `java -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8 -cp "C:\Users\zepht\Documents\CS2113 iP\ip\out" chillguy.Chillguy`

Working directory: isolated sandbox `1`, storage session `1`.

Input:

```text
todo read book
todo return book
delete 1
list
bye
```

Actual output:

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

Exit code: 0

Stderr: ''

- PASS: no temporary save files remain

## Delete Mixed Task Types: PASS

Command: `java -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8 -cp "C:\Users\zepht\Documents\CS2113 iP\ip\out" chillguy.Chillguy`

Working directory: isolated sandbox `2`, storage session `2`.

Input:

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

Actual output:

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

Exit code: 0

Stderr: ''

- PASS: no temporary save files remain

## Add Level 4 Task Types: PASS

Command: `java -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8 -cp "C:\Users\zepht\Documents\CS2113 iP\ip\out" chillguy.Chillguy`

Working directory: isolated sandbox `3`, storage session `3`.

Input:

```text
todo borrow book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
mark 1
list
bye
```

Actual output:

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

Exit code: 0

Stderr: ''

- PASS: no temporary save files remain

## Reject Malformed Deadline: PASS

Command: `java -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8 -cp "C:\Users\zepht\Documents\CS2113 iP\ip\out" chillguy.Chillguy`

Working directory: isolated sandbox `4`, storage session `4`.

Input:

```text
deadline byebye /today 6pm
bye
```

Actual output:

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

Exit code: 0

Stderr: ''

- PASS: no temporary save files remain

## Reject Malformed Event: PASS

Command: `java -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8 -cp "C:\Users\zepht\Documents\CS2113 iP\ip\out" chillguy.Chillguy`

Working directory: isolated sandbox `5`, storage session `5`.

Input:

```text
event meeting /from Monday 2pm
bye
```

Actual output:

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

Exit code: 0

Stderr: ''

- PASS: no temporary save files remain

## Reject Unknown and Empty Commands: PASS

Command: `java -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8 -cp "C:\Users\zepht\Documents\CS2113 iP\ip\out" chillguy.Chillguy`

Working directory: isolated sandbox `6`, storage session `6`.

Input:

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

Actual output:

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

Exit code: 0

Stderr: ''

- PASS: no temporary save files remain

## Validate Task Numbers and Preserve State: FAIL

Command: `java -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8 -cp "C:\Users\zepht\Documents\CS2113 iP\ip\out" chillguy.Chillguy`

Working directory: isolated sandbox `7`, storage session `7`.

Input:

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

Actual output:

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
Sorry, I couldn't save tasks to data/chillguy.txt. No changes were made. Check that the data folder is writable and the file is not in use.
____________________________________________________________
____________________________________________________________
Noted. I've removed this task:
  [T][ ] second
Now you have 1 task in the list.
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] first
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][X] first
____________________________________________________________
____________________________________________________________
Noted. I've removed this task:
  [T][X] first
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

Exit code: 0

Stderr: ''

- PASS: no temporary save files remain

Expected output:

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
