#!/usr/bin/env bash

set -o pipefail

FLAVOR="${1-Production}"
BUILD_TYPE="${2-Release}"

REPO_DIR="$( cd "$( dirname "$0" )/../" && pwd )"

# Lowercase flavor and build type for artifact path
FLAVOR_LOWER=$(echo "${FLAVOR}" | tr '[:upper:]' '[:lower:]')
BUILD_TYPE_LOWER=$(echo "${BUILD_TYPE}" | tr '[:upper:]' '[:lower:]')

#if [[ $(git status 2> /dev/null | tail -n1) != "nothing to commit, working tree clean" ]]; then
#  echo "Working directory dirty. Please revert or commit."
#  exit 1
#fi

echo "Keystore password: "
read -s KEYSTORE_PASS
if [[ -z "${KEYSTORE_PASS}" ]]; then
  echo "No password given. Abort"
  exit 1
fi
export KEYSTORE_PASSWORD=${KEYSTORE_PASS}

echo "Key (alias) password [return when same]: "
read -s KEYPASS
if [[ -z "${KEYPASS}" ]]; then
  KEYPASS=${KEYSTORE_PASS}
fi
export KEY_PASSWORD=${KEYPASS}

echo "Building bundle 'b' or apk 'a' [return when b]:"
read -s INPUT
if [[ -z "${INPUT}" ]]  ||
   [[ "${INPUT}" == "b" ]] ||
   [[ "${INPUT}" == "B" ]]; then
  APP_TYPE="Bundle"
  GRADLE_COMMAND=bundle
  ARTIFACT="bundle/${FLAVOR_LOWER}${BUILD_TYPE_LOWER}"
elif [[ "${INPUT}" == "a" ]] ||
     [[ "${INPUT}" == "A" ]]; then
  APP_TYPE="APK"
  GRADLE_COMMAND=assemble
  ARTIFACT="apk/${FLAVOR_LOWER}${BUILD_TYPE_LOWER}"
else
  echo "Invalid input. Abort"
  exit 1
fi

echo "Creating ${APP_TYPE} for ${FLAVOR} ${BUILD_TYPE}"

${REPO_DIR}/gradlew -p "$REPO_DIR" clean ${GRADLE_COMMAND}${FLAVOR}${BUILD_TYPE} -Dpre-dex=false

open "$REPO_DIR/android/build/outputs/${ARTIFACT}/"
