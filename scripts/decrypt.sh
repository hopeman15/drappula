#!/usr/bin/env bash

set -o pipefail

IN=$1
OUT=$2
PASSWORD=$3

echo "Decrypting ${IN} to ${OUT}"

gpg --batch --yes --passphrase ${PASSWORD} -o ${OUT} -d ${IN}
