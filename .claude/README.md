# AI Assistant Support

This repository is configured for use with [Claude Code](https://docs.anthropic.com/en/docs/claude-code) and [Cursor](https://cursor.sh).

## Structure
- `CLAUDE.md` - Project context and guidelines
- `.claude/commands/` - Executable workflows (invoke with `/command`)
- `.claude/rules/` - Guidelines (auto-loaded when relevant)
- `.cursor/rules/` - Symlinks for Cursor compatibility

## Usage

**Claude Code:**
- `/commit` - Run the commit workflow
- Rules auto-load when relevant

**Cursor:**
- Rules auto-load when relevant
- Reference commands with `@.claude/commands/commit.md`

## Adding New Rules

1. Create a markdown file in `.claude/rules/`:
```markdown
---
description: When to use this rule
alwaysApply: false
---

# Rule Name

Your guidelines here.
```

2. Symlink for Cursor:
```bash
ln -s ../../.claude/rules/your-rule.md .cursor/rules/your-rule.mdc
```

## Adding New Commands

Create a markdown file in `.claude/commands/` with the workflow steps. Commands are executable prompts invoked with `/command-name` in Claude Code.
