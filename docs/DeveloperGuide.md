# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Command component
API: Command.java

{insert UML diagram here}

How the `Command` Component work:
1. When the user enters a command, the `Command` component identifies the type of command and creates the corresponding Command object.
2. This command is represented as a subclass of `Command`, such as `DoneCommand`, `RemoveCommand`, `CountCommand`, `ListCompletedCommand`, `ListIncompleteCommand`, `ListNeededCommand`, or `AddToPlannerCommand`.
3. The selected command is then executed by calling its `execute(ModuleList modules)` method. During execution, the command interacts with the ModuleList to retrieve, add, remove, or count modules. For example:
- `DoneCommand` adds a completed module to the list and saves the updated data to storage.
- `RemoveCommand` removes a module from the list and saves the updated data to storage.
- `CountCommand` retrieves the total number of MCs completed.
- `ListCompletedCommand`, `ListIncompleteCommand`, and `ListNeededCommand` retrieve different filtered views of the module list. 
4. After execution, the command returns a String result, which is then shown to the user as the system response. This is evident from the shared method signature in Command and the implementations in each subclass.

### Storage component
API: Storage.java

{insert UML diagram here}

The storage component,

can save current completed mods as well as planned mods in a text file

It can also create more than one list for different users

different user can also have different iteration of their plan

## Product scope
### Target user profile
- Y1-Y4 Computer Engineering Undergraduate Students (JC path)
- did not follow the recommended TimeTable
- has a need to manage complex multi-year university pathways
- can type fast
- is reasonably comfortable using CLI apps


### Value proposition

PathLock provides a lightweight, offline CLI tool for CEG students to organise complex multi-year university pathways,
tracking completed modules, monitoring MC progress, and managing graduation requirements without needing a
database or internet connection.

## User Stories

|Version| As a ... | I want to ...                                           | So that I can ...                                           |
|--------|----------|---------------------------------------------------------|-------------------------------------------------------------|
|v1.0|New User| see usage instructions                                  | refer to them when I forget how to use the application      |
|v1.0|User| see my output                                           | know what I entered                                         |
|v2.0|User| have a planner mode                                     | plan which mods I want to take when                         |
|v2.0|User| add mods to the planner                                 | -                                                           |
|v2.0|User| be able to edit the mods I have indicated in my planenr | correct any mistakes I made                                 |
|v2.0|User| have a visual indication of my planner                  | so that I can see my whole planned timetable over my course |

## Non-Functional Requirements

1. Should work on any mainstream OS (Windows, macOS, Linux) with Java 17 or above installed.
2. All data is stored locally and the application should work fully without internet connectivity.
3. The saved plan file should remain human-readable and editable with a standard text editor.

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
