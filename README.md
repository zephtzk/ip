# Chillguy project template

This is a project template for a greenfield Java project named _Chillguy_. Given below are instructions on how to use it.

## Saving and loading tasks

Run Chillguy with Java 25 from the project root. Tasks load from `data/chillguy.txt` at startup.
Adding, deleting, marking, or unmarking a task automatically saves the list before confirming the change.
The app creates the data folder on the first save; a missing or empty file starts an empty list.
The local data folder is ignored by Git.

The file uses UTF-8 and one task per line. Status `1` means done; `0` means not done:

```text
T | 1 | read book
D | 0 | return book | June 6th
E | 0 | project meeting | Aug 6th 2pm | 4pm
```

Pipes and backslashes inside fields are escaped as `\|` and `\\`; line breaks use `\n` and `\r`.
Blank lines and an optional initial UTF-8 byte order mark are accepted.
An unreadable file or malformed record stops startup with an error; fix the file and restart.
The app leaves the original file untouched in that case.

Saving writes a temporary file and replaces the saved file atomically, so a failed write cannot leave a partial list.
If saving fails, the attempted task change is undone. Check folder permissions or close programs locking the file,
then retry the command. The file system must support atomic file replacement.
Use one running Chillguy instance per data file.

## Setting up in Intellij

Prerequisites: JDK 25, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 25** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/chillguy/Chillguy.java` file, right-click it, and choose `Run Chillguy.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.
