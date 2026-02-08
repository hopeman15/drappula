#!/usr/bin/env bash

set -o pipefail

FLAVOR="${1-Production}"
BUILD_TYPE="${2-Release}"
PLAY_PUBLISH_PASSWORD=$4

echo "Publishing App for ${FLAVOR} ${BUILD_TYPE}"

REPO_DIR="$( cd "$( dirname "$0" )/../" && pwd )"

# Verify repo is clean
if [[ $(git status 2> /dev/null | tail -n1) != "nothing to commit, working tree clean" ]]; then
  echo "Working directory dirty. Please revert or commit."
  exit 1
fi

if [[ -z "${PLAY_PUBLISH_PASSWORD}" ]]; then
  echo "No Play publish password given. Abort"
  exit 1
fi

# Decrypt Play Console service account credentials
echo "Decrypting Play Console credentials"
./scripts/decrypt.sh play-publish-credentials.json.gpg \
    play-publish-credentials.json ${PLAY_PUBLISH_PASSWORD}

# Publish
${REPO_DIR}/gradlew -p "$REPO_DIR" publish${FLAVOR}${BUILD_TYPE}Bundle --info
