# Create Pull Request

You are tasked with creating a pull request for the changes on the current branch.

## Process

1. **Understand the changes:**
   - Run `git log` and `git diff` against the base branch to understand all commits
   - Review the full scope of changes, not just the latest commit

2. **Check for a PR template:**
   - Look for a pull request template in the following locations (in order):
     - `.github/PULL_REQUEST_TEMPLATE.md`
     - `.github/pull_request_template.md`
     - `PULL_REQUEST_TEMPLATE.md`
     - `pull_request_template.md`
     - `.github/PULL_REQUEST_TEMPLATE/` (use the default template in this directory)
   - If a template is found, use it as the structure for the PR body
   - Fill in each section of the template based on the changes
   - Remove HTML comments (e.g., `<!-- ... -->`) and replace them with actual content
   - Check off checklist items that are clearly addressed by the code changes
   - Leave unchecked any items that are not clearly satisfied

3. **Draft the PR:**
   - Use conventional commits style for the title: `type: description`
     - `feat:` new feature
     - `fix:` bug fix
     - `docs:` documentation changes
     - `chore:` maintenance tasks
     - `refactor:` code refactoring
     - `test:` adding or updating tests
     - `ci:` CI/CD changes
   - If a PR template was found, populate its sections with relevant content
   - If no template was found, keep the summary concise with bullet points
   - Do NOT include a "Test plan" section unless the template has one

4. **Create the PR:**
   - Push the branch if needed
   - Use `gh pr create` to open the pull request

## Important
- **NEVER add co-author information or AI attribution**
- PRs should be authored solely by the user
- Do NOT include any "Generated with Claude" messages
- Do NOT add "Co-Authored-By" lines
- Do NOT include AI/Cursor attribution
- Write PR descriptions as if the user wrote them
