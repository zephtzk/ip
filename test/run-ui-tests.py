"""Run the recorded console UI plan with Java 25, stopping at the first failure."""

from pathlib import Path
import re
import subprocess
import sys


ROOT = Path(__file__).resolve().parents[1]
PLAN = ROOT / "test/ui-test-plan.md"
TRANSCRIPT = ROOT / "test/ui-test-transcript.md"


def normalize(text):
    return text.replace("\r\n", "\n")


def read_block(case, label):
    """Read exact inline text or a repository-relative fixture from the plan."""
    match = re.search(rf"- {label}:\s*\n\n```text\n(.*?)```", case, re.S)
    if match:
        return match.group(1)
    match = re.search(rf"- {label} file: `([^`]+)`", case)
    if match:
        return (ROOT / match.group(1)).read_text(encoding="utf-8")
    raise ValueError(f"Missing {label} in test case")


def main():
    transcript = ["# UI Test Transcript\n"]
    for executable in ("java", "javac"):
        result = subprocess.run([executable, "-version"], capture_output=True, text=True, cwd=ROOT)
        version = result.stdout + result.stderr
        transcript.append(f"```text\n{executable} -version\n{version}```\n")
        if result.returncode or not re.search(r'(?:version "|javac )25(?:\.|\")', version):
            raise RuntimeError(f"Java 25 required: {version}")

    sources = sorted(str(path) for path in (ROOT / "src/main/java").rglob("*.java"))
    build = subprocess.run(["javac", "-d", "out", *sources], capture_output=True, text=True, cwd=ROOT)
    transcript.append("Build: `javac -d out src/main/java/chillguy/*.java`\n")
    if build.returncode or build.stdout or build.stderr:
        transcript.append(f"```text\n{build.stdout}{build.stderr}```\n")
        TRANSCRIPT.write_text("\n".join(transcript), encoding="utf-8")
        raise RuntimeError("Compilation failed or produced unexpected output; see transcript")

    cases = re.split(r"^### ", PLAN.read_text(encoding="utf-8"), flags=re.M)[1:]
    if not cases:
        raise ValueError("No test cases found")
    for case in cases:
        name = case.splitlines()[0]
        command = re.search(r"- Command: `([^`]+)`", case).group(1)
        if command != "java -cp out chillguy.Chillguy":
            raise ValueError(f"Unsupported command: {command}")
        inputs = read_block(case, "Input")
        expected = read_block(case, "Expected output")
        actual = subprocess.run(command.split(), input=inputs, capture_output=True,
                                text=True, cwd=ROOT, timeout=15)
        passed = (actual.returncode == 0 and not actual.stderr
                  and normalize(actual.stdout) == normalize(expected))
        transcript.append(f"## {name}: {'PASS' if passed else 'FAIL'}\n\n"
                          f"Command: `{command}`\n\nInput:\n\n```text\n{inputs}```\n\n"
                          f"Actual output:\n\n```text\n{actual.stdout}```\n\n"
                          f"Exit code: {actual.returncode}\n\n"
                          f"Stderr: {actual.stderr!r}\n")
        if not passed:
            transcript.append(f"Expected output:\n\n```text\n{expected}```\n")
        TRANSCRIPT.write_text("\n".join(transcript), encoding="utf-8")
        print(f"{'PASS' if passed else 'FAIL'}: {name}")
        if not passed:
            print(f"Stopped at first failure. See {TRANSCRIPT}")
            return 1
    print(f"All {len(cases)} cases passed. Transcript: {TRANSCRIPT}")
    return 0


if __name__ == "__main__":
    sys.exit(main())
