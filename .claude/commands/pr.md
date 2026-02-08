# Create Pull Request

You are tasked with creating a pull request for the changes on the current branch.

## Process

1. **Understand the changes:**
   - Run `git log` and `git diff` against the base branch to understand all commits
   - Review the full scope of changes, not just the latest commit

2. **Draft the PR:**
   - Use conventional commits style for the title: `type: description`
     - `feat:` new feature
     - `fix:` bug fix
     - `docs:` documentation changes
     - `chore:` maintenance tasks
     - `refactor:` code refactoring
     - `test:` adding or updating tests
     - `ci:` CI/CD changes
   - Keep the summary concise with bullet points
   - Do NOT include a "Test plan" section

3. **Create the PR:**
   - Push the branch if needed
   - Use `gh pr create` to open the pull request

## Important
- **NEVER add co-author information or AI attribution**
- PRs should be authored solely by the user
- Do NOT include any "Generated with Claude" messages
- Do NOT add "Co-Authored-By" lines
- Do NOT include AI/Cursor attribution
- Write PR descriptions as if the user wrote them
