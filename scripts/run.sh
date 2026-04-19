#!/usr/bin/env bash

set -euo pipefail

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
project_dir="$(cd "$script_dir/../Hungarian Algorithm" && pwd)"

if [[ "${1:-}" != "--skip-build" ]]; then
  "$script_dir/build.sh"
fi

java -cp "$project_dir/bin" Main.Main
