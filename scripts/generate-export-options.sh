#!/usr/bin/env bash

set -eo pipefail

# Generate ExportOptions.plist from environment variables
# Required: TEAM_ID, PROVISIONING_PROFILE_NAME

if [[ -z "${TEAM_ID}" ]]; then
  echo "TEAM_ID not set. Abort"
  exit 1
fi

if [[ -z "${PROVISIONING_PROFILE_NAME}" ]]; then
  echo "PROVISIONING_PROFILE_NAME not set. Abort"
  exit 1
fi

REPO_DIR="$( cd "$( dirname "$0" )/../" && pwd )"
OUTPUT_PATH="${REPO_DIR}/build/ios/ExportOptions.plist"

mkdir -p "$(dirname "${OUTPUT_PATH}")"

cat > "${OUTPUT_PATH}" <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN"
  "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>
    <key>method</key>
    <string>app-store-connect</string>

    <key>teamID</key>
    <string>${TEAM_ID}</string>

    <key>signingStyle</key>
    <string>manual</string>

    <key>signingCertificate</key>
    <string>Apple Distribution</string>

    <key>provisioningProfiles</key>
    <dict>
        <key>com.hellocuriosity.drappula</key>
        <string>${PROVISIONING_PROFILE_NAME}</string>
    </dict>

    <key>uploadSymbols</key>
    <true/>

    <key>manageAppVersionAndBuildNumber</key>
    <false/>
</dict>
</plist>
EOF

echo "Generated ${OUTPUT_PATH}"
