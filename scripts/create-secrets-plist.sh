#!/bin/bash
set -euo pipefail

SLACK_BOT_TOKEN="${SLACK_BOT_TOKEN:?SLACK_BOT_TOKEN is required}"
SLACK_CHANNEL_ID="${SLACK_CHANNEL_ID:?SLACK_CHANNEL_ID is required}"
PRIVACY_POLICY_URL="${PRIVACY_POLICY_URL:-https://sites.google.com/view/drappula/home}"

cat > ios/drappula/Secrets.plist << EOF
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>
  <key>SLACK_BOT_TOKEN</key>
  <string>${SLACK_BOT_TOKEN}</string>
  <key>SLACK_CHANNEL_ID</key>
  <string>${SLACK_CHANNEL_ID}</string>
  <key>PRIVACY_POLICY_URL</key>
  <string>${PRIVACY_POLICY_URL}</string>
</dict>
</plist>
EOF
