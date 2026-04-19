#!/usr/bin/env bash

set -euo pipefail

project_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")/../Hungarian Algorithm" && pwd)"

mkdir -p "$project_dir/bin"

javac -encoding ISO-8859-1 -d "$project_dir/bin" \
  "$project_dir"/src/Interfaces/*.java \
  "$project_dir"/src/Implementations/*.java \
  "$project_dir"/src/Control/*.java \
  "$project_dir"/src/Main/*.java

echo "Build successful: $project_dir/bin"
