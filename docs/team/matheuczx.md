# Matheu Cheo's Project Portfolio Page  
## Overview

TutorSwift is a command-line application for private tutors to manage student records, including lessons, grades, and payments.

It is designed to streamline administrative tasks such as tracking student performance and managing lesson schedules efficiently through a CLI interface.

Given below are my contributions to the project.

---

### Summary of Contributions

---

### New Features Implemented

#### `list` feature
* **What it does:** Allows the user to view all students currently in the active list or the archive.
* **Justification:** Provides a quick overview of the system state, essential for managing larger student datasets.
* **Link:** [PR #23](https://github.com/AY2526S2-CS2113-W11-1/tp/pull/23)

#### `find` feature
* **What it does:** Allows the user to search for students using multiple criteria: name (`n/`), subject (`sub/`), or academic level (`l/`).
* **Justification:** Essential for efficient navigation when the student list grows.
* **Highlights:**
  * Supports partial and case-insensitive matching.
  * Allows combining multiple filters (e.g., finding all "Mathematics" students in "Secondary 4").
  * **Design:** Implemented by modifying the `Parser` to support prefix-based arguments and adding a filtering method in `StudentList` to aggregate results.
* **Link:** [PR #44](https://github.com/AY2526S2-CS2113-W11-1/tp/pull/44)

#### `add` duplicate student prevention
* **What it does:** Prevents the addition of students with the same name (case-insensitive) to ensure data integrity.
* **Justification:** Prevents accidental creation of duplicate records, which could lead to billing or scheduling errors.
* **Highlights:** * Introduced a validation check in `AddCommand#execute` that queries the `StudentList` before allowing the addition.
  * Implemented `StudentList#hasStudentWithName` for efficient duplicate detection.
  * Added a custom `TutorSwiftException` to provide clear, actionable feedback to the user via the `Ui` class.
* **Link:** [PR #107](https://github.com/AY2526S2-CS2113-W11-1/tp/pull/107)
---

#### Code Contributed: [RepoSense link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=matheuczx&breakdown=true)

---

**Project management:**
- Contributed to feature development and integration within the team repository
- Released `v2.0`-`v2.0.1` (2 releases) on Github with UG and DG assets 
---

**Enhancements to existing features:**
- Add duplicate handling for existing`add student` feature to resolve major bug raised in PE-D(Pull requests [#107](https://github.com/AY2526S2-CS2113-W11-1/tp/pull/107))

---
**Community:**
- PRs  reviewed with non-trivial review comments): 
- Reported high severity bug for other teams ([#6](https://github.com/NUS-CS2113-AY2526-S2/ped-matheuczx/issues/6)
---

### Documentation:

**User Guide:**
- Added documentation for features`find` and `list` commands [#104](https://github.com/AY2526S2-CS2113-W11-1/tp/pull/104),[#86](https://github.com/AY2526S2-CS2113-W11-1/tp/pull/86)
---

**Developer Guide:**
- Added all design documentation of Architecture, Model, Ui and Logic components. [#89](https://github.com/AY2526S2-CS2113-W11-1/tp/pull/89)
- Design contributions: [Pg 2-9 of Developer Guide]([#6](https://github.com/NUS-CS2113-AY2526-S2/ped-matheuczx/issues/6))
- Added sequence diagrams for: 
  - Execution flow of overall architecture
![Architecture Sequence Diagram](../images/ArchitectureSequenceDiagram.png)
- Added Class diagrams for: [#104](https://github.com/AY2526S2-CS2113-W11-1/tp/pull/104)
  - Model Component
    ![Model Class Diagram](../images/ModelComponentClassDiagram.png)
  - Ui Component
    ![Ui Class Diagram](../images/UiComponentClassDiagram.png)
  - Logic Component
    ![Logic Class Diagram](../images/LogicComponentClassDiagram.png)
- Added implementation details for the `FindCommand` and `ListCommand` feature [#86](https://github.com/AY2526S2-CS2113-W11-1/tp/pull/86)
- Added sequence diagrams for:
    - Execution flow of `FindCommand`
      ![Find Sequence Diagram](../images/FindSequenceDiagram.png)
    - Execution flow of `ListCommand`
      ![List Sequence Diagram](../images/ShowActiveListSequenceDiagram.png)

---

**Contributions beyond project team:**
- Guided team members on usage of PlantUML for sequence diagrams
- Work with Yi Feng to ideate and convert initial user stories and product features into issues for v1.0
- Come out with more exciting ideas (e.g class scheduling) for v2.0. 
---
