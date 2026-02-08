# AI Assistant Support

This repository is configured for use with [Claude Code](https://docs.anthropic.com/en/docs/claude-code) and [Cursor](https://cursor.sh).

## Structure
- `CLAUDE.md` - Project context and guidelines (symlinked to `.cursor/rules/project.mdc`)
- `.claude/commands/` - Executable workflows (invoke with `/command`)
- `.cursor/commands/` - Symlinks to `.claude/commands/` for Cursor compatibility

## Usage

**Claude Code:**
- `/commit` - Run the commit workflow
- `/pr` - Run the pull request workflow

**Cursor:**
- Project rules auto-load from `.cursor/rules/project.mdc`
- Commands available via `.cursor/commands/`

## Adding New Commands

1. Create a markdown file in `.claude/commands/` with the workflow steps. Commands are executable prompts invoked with `/command-name` in Claude Code.

2. Symlink for Cursor:
```bash
ln -s ../../.claude/commands/your-command.md .cursor/commands/your-command.mdc
```
