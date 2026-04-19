#!/usr/bin/env bash

set -euo pipefail

project_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")/../Hungarian Algorithm" && pwd)"

rm -rf "$project_dir/bin"

echo "Removed build directory: $project_dir/bin"
