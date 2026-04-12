# Ryan - Project Portfolio Page

## Project: PathLock

PathLock is a desktop CLI application designed to help Computer Engineering (CEG) students at NUS plan and track their multi-year academic journey. It enables students to record completed modules, monitor MC progress towards the 160-MC graduation requirement, check prerequisite and post-requisite chains, and organise planned modules across semesters, all offline, with data stored in a human-editable file.

Given below are my contributions to the project.

- **New Feature**: Added the `prereq` command.
    - What it does: displays the prerequisite modules required before a student can take a specified module.
    - Justification: This feature helps students plan their semester workload by understanding module dependency chains before registering for modules.
    - Highlights: Retrieves prerequisite data from the JSON module database. Handles unrecognised modules, modules with no prerequisites, and case-insensitive input.

- **New Feature**: Added the `postreq` command.
    - What it does: shows all modules that are unlocked upon completing a given module.
    - Justification: This feature gives students forward visibility into how completing a module opens up future options, aiding long-term planning.
    - Highlights: Scans the full module list to find all modules listing the given module as a prerequisite. Required traversal of the prerequisite graph across all modules to perform the reverse lookup.

- **New Feature**: Added the `count` command.
    - What it does: summarises a student's MC progress towards the 160-MC graduation requirement, showing completed MCs, remaining MCs, and percentage progress.
    - Justification: Students need a quick way to check how far along they are in their degree without manually adding up MCs.
    - Highlights: Counts MCs from both internal (recognised) and external modules. Caps remaining MCs at 0 when completed exceeds 160.

- **Code contributed**: [RepoSense link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=ryanzx-zone&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other)

- **Enhancements to existing features**:
    - Added MC value mapping for all CEG required modules and implemented OR group handling for internship and capstone requirements (e.g., `CG4002 OR CG4001 OR CP4106`). This underpins the `count`, `list incomplete`, and `list needed` commands across the project (PR [#31](https://github.com/AY2526S2-CS2113-F14-3/tp/pull/31)).
    - Refactored the module data layer from hardcoded lists to JSON-based loading using Gson. Created `ModuleLoader` to parse `modules.json` at startup and restructured `Module.java` to support prerequisite, preclusion, and OR group data. This was a large refactor (16 files, ~600 lines added) that all team members' features depend on (PR [#68](https://github.com/AY2526S2-CS2113-F14-3/tp/pull/68)).
    - Added help entries for `prereq`, `postreq`, and `count` commands in `HelpCommand`.
    - Made all command keywords case-insensitive in the Parser so that inputs like `DONE CS2113`, `List Completed`, and `COUNT` are accepted, while preserving original casing for arguments such as module codes and usernames (PR [#240](https://github.com/AY2526S2-CS2113-F14-3/tp/pull/240), fixes #205).
    - Fixed `postreq` accepting unrecognised module codes without error by adding a recognised-module check to `getModulesUnlockedBy()` (#193, #215).
    - Fixed `count` displaying negative incomplete percentage when completed MCs exceed 160 by capping remaining MCs at 0 (#214).

- **Project management**:
    - Set up the initial JUnit test infrastructure (configured `build.gradle` for JUnit 5) and wrote the first test suite covering `Module`, `ModuleList`, `Parser`, and `DuplicateException` (PRs [#36](https://github.com/AY2526S2-CS2113-F14-3/tp/pull/36), [#48](https://github.com/AY2526S2-CS2113-F14-3/tp/pull/48))
    - Enabled assertions in `build.gradle` run configuration
    - Release management: Generated the v2.0 release on GitHub
    - Triaged and resolved PE-D issues assigned to me: #177, #184, #185, #186, #191, #193, #194, #205, #211, #214, #215

- **Documentation**:
    - User Guide:
        - Added documentation for the `prereq`, `postreq`, and `count` commands with usage examples and expected output (PR [#96](https://github.com/AY2526S2-CS2113-F14-3/tp/pull/96))
        - Added note under `count` section about manually tracking GEN/GEC modules via `done /mc` (#186)
        - Added FAQ entry explaining why GEN/GEC modules are not in the built-in module list (#186)
        - Added note under `planner add` and Known Issues entry about co-scheduling constraints (#184)
        - Updated Features section to reflect full case-insensitivity of commands (#205)
        - Fixed Command Summary table rendering issues (#177, #191, #211)
    - Developer Guide:
        - Added implementation sections for the `prereq`, `postreq`, and `count` commands (PR [#92](https://github.com/AY2526S2-CS2113-F14-3/tp/pull/92))
        - Added sequence diagrams for all three commands and the `count` class diagram (PR [#94](https://github.com/AY2526S2-CS2113-F14-3/tp/pull/94))
        - Wrote the acknowledgements, value proposition, non-functional requirements, glossary, and manual testing sections (PRs [#63](https://github.com/AY2526S2-CS2113-F14-3/tp/pull/63), [#126](https://github.com/AY2526S2-CS2113-F14-3/tp/pull/126))
        - Fixed DG diagram issues: replaced external PlantUML URLs with local PNGs, replaced Java code blocks with text descriptions per CS2113 DG guidelines

- **Community**:
    - Fixed test compilation errors across 8 test files after the `AppState` refactor (PR [#80](https://github.com/AY2526S2-CS2113-F14-3/tp/pull/80))
    - Wrote and maintained tests for teammates' features (`DoneCommand`, `RemoveCommand`, `ListCompletedCommand`, `ListIncompleteCommand`, `ListNeededCommand`, `HelpCommand`)
    - Fixed `Storage` null `filePath` bug and renamed `storageTest.java` to follow naming conventions
    - Redirected logger output to file instead of console to reduce noise in CLI output
