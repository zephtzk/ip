# iP status review — 29 September 2026

Checked the supplied dashboard warnings against local source, commit history,
live GitHub refs, public pull requests and releases, and the published guide URL.
Level 8 is intentionally excluded at the owner's request.

## Corrections completed locally

| Dashboard item | Evidence and correction |
| --- | --- |
| Level-0 | Added lightweight tag `Level-0` at `b453279`, which implements the greeting and exit. |
| A-Exceptions | Added lightweight tag `A-Exceptions` at `301cb8c`, the merge containing the custom exception and input validation. |
| A-Collections | Added lightweight tag `A-Collections` at `d9328a9`, the merge containing the growing `ArrayList<Task>` and deletion support. |
| branch-Level-5 | Existing local branch at `5d44dba`; already merged into master by `301cb8c`. Missing on GitHub. |
| branch-Level-6 | Existing local branch at `5dc28f7`; already merged into master by `d9328a9`. Missing on GitHub. |
| branch-Level-7 | Existing local branch at `c2b1735`; already merged into master by `6a55818`. Missing on GitHub. |
| branch-Level-9 | Created the missing local name at the existing Level 9 commit `71e4450`. Its tip is already an ancestor of master. |
| branch-A-JavaDoc | Created the missing local name at the existing Javadoc commit `76258a1`, also the current master tip. |

The two Week 7 branch names were reconstructed after the work was committed on
master. This restores the refs without rewriting history; it does not create a
historical branch-development or merge event. `git branch --merged master`
lists all five required branches. The course checker may impose additional
workflow requirements; its refresh has not been observed.

No existing tags were moved. The guide commit and `A-UserGuide` tag were
created and published as part of the ongoing documentation work.

## Week 7 checks

| Item | Result |
| --- | --- |
| A-MoreOOP | Tag `9074dd6` is already on GitHub. `Ui`, `Parser`, `TaskList`, and `Storage` have separate responsibilities. |
| Level-9 | Tag `71e4450` is already on GitHub. Keyword search is implemented and its recorded UI cases pass. |
| A-JavaDoc | Tag `76258a1` is already on GitHub. Java 25 Javadoc generation passes. |
| A-UserGuide | Tag `18040a3` is on GitHub and `master` contains the complete guide. |
| Published UG | `https://zephtzk.github.io/ip/` returns HTTP 200 and contains the `Chillguy User Guide` heading. |
| Merging PRs | The public GitHub API returns no pull requests in `zephtzk/ip`. A real pull request and merge are still needed. Local merge commits do not satisfy this item. |
| JAR release | The only public release is `A-Jar`, published 15 September 2026 at 19:49:43 Singapore time. It satisfies the earlier release window, but not the Week 7 window shown in the screenshot. |

The Week 7 window shown is 19 September 2026 at 16:00:01 through 7 October 2026
at 16:00:00, Singapore time. A newly built `build/libs/chillguy-all.jar` is ready
for the new release. Keep the existing `A-Jar` release intact.

## Validation

- Java and javac: 25.0.4.
- All 41 recorded UI cases pass, including direct JAR launch, errors, more than
  100 tasks, persistence, failed-save rollback, and keyword search.
- The existing runner built the fat JAR and compared exact output, empty stderr,
  exit codes, and recorded storage expectations. Personal task data was isolated.
- Full command/input/output record: [Week 7 UI transcript](week7-ui-test-transcript.md).
- Javadoc passes with `javadoc -quiet -private -Xdoclint:all,-missing`.
  Optional missing Javadoc tags are excluded from that check.
- `git diff --check` passes. No Java behavior or UI expectations needed changes.
- Existing guide edits and previous verification transcripts were preserved.

## Remaining GitHub-side steps

1. Publish `Level-0`, `A-Exceptions`, and `A-Collections`, plus the existing or
   restored `branch-Level-5`, `branch-Level-6`, `branch-Level-7`,
   `branch-Level-9`, and `branch-A-JavaDoc` refs.
2. Create and merge a pull request into this fork's `master`; the public API
   still reports zero pull requests, so the dashboard's PR item remains open.
3. Configure GitHub Pages to deploy from `master`, folder `/docs`, if the
   current Pages configuration does not already match the working public site.
4. Create a new release for the Week 7 JAR and attach the verified
   `build/libs/chillguy-all.jar`. Verify the download and the publication time.

### Draft commit message and PR title

```text
Document Chillguy commands and verify Week 7 tasks

The user guide contains starter text and does not explain the commands
implemented in Chillguy. Missing milestone refs also leave completed
increments unrecognized by the course dashboard.

Document setup, command examples, search behavior, and storage recovery.
Record the corrected milestone refs and remaining publication steps so
the completed work can be checked against the course requirements.

Validation: All 41 UI cases pass with Java 25.0.4. Javadoc generation
and whitespace checks pass. Level 8 remains intentionally excluded.
```

### Draft PR description

The published repository still has a starter user guide. Replace it with
Chillguy setup instructions, examples for every supported command, search
numbering guidance, and storage troubleshooting. Link the guide from the
project README and record the milestone audit.

Validation: all 41 recorded UI cases pass with Java 25.0.4, and Javadoc
generation passes. Application behavior is unchanged. Level 8 is excluded.

### Draft release notes

Chillguy supports todos, deadlines, events, completion status, deletion,
automatic saving, and keyword search. This release includes the Week 7
refactoring, Javadocs, and user guide.

Download `chillguy-all.jar`, install Java 25, and run
`java -jar chillguy-all.jar` in the folder where you want to keep your tasks.
Dates and times are stored as text. All 41 recorded UI cases pass.
