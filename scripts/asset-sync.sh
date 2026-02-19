#!/usr/bin/env bash
set -euo pipefail

ASSETS_AUDIO="android/src/main/assets/audio"
mkdir -p "$ASSETS_AUDIO"

for dir in audio/*/; do
    name=$(basename "$dir")
    link="$ASSETS_AUDIO/$name"
    if [ ! -L "$link" ]; then
        ln -s "../../../../../audio/$name" "$link"
        echo "Created symlink: $link"
    fi
done
