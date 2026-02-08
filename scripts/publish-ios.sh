#!/usr/bin/env bash

set -eo pipefail

echo "Publishing iOS App to TestFlight"

REPO_DIR="$( cd "$( dirname "$0" )/../" && pwd )"

# Verify repo is clean
if [[ $(git status 2> /dev/null | tail -n1) != "nothing to commit, working tree clean" ]]; then
  echo "Working directory dirty. Please revert or commit."
  exit 1
fi

# Verify required environment variables
if [[ -z "${ASC_KEY_ID}" ]]; then
  echo "ASC_KEY_ID not set. Abort"
  exit 1
fi

if [[ -z "${ASC_ISSUER_ID}" ]]; then
  echo "ASC_ISSUER_ID not set. Abort"
  exit 1
fi

# Find the IPA
IPA_PATH=$(find "${REPO_DIR}/build/ios/output" -name "*.ipa" -print -quit)
if [[ -z "${IPA_PATH}" ]]; then
  echo "No IPA found in build/ios/output. Run 'just export-ios' first. Abort"
  exit 1
fi

echo "Uploading ${IPA_PATH} to TestFlight"

# Set API key directory
export API_PRIVATE_KEYS_DIR="${REPO_DIR}/private_keys"

# Upload to TestFlight
xcrun altool \
  --upload-app \
  -f "${IPA_PATH}" \
  -t ios \
  --apiKey "${ASC_KEY_ID}" \
  --apiIssuer "${ASC_ISSUER_ID}"
