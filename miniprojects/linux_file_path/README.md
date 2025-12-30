# Linux File Path - CD Command Simulation

A Python implementation of the Linux `cd` command with enhanced features and a shell-like wrapper class.

## Overview

This project simulates the Linux `cd` command functionality, including path simplification, tilde expansion, and symlink resolution. It provides both a standalone `cd` function and an enhanced `CDWrapper` class that mimics shell-like behavior with state management and method chaining.

## Features

### Core `cd` Function
- **Path Simplification**: Handles `.` (current directory), `..` (parent directory), and multiple slashes
- **Tilde Expansion**: Replaces `~` with home directory path
- **Symlink Resolution**: Maps symbolic links to real paths, supporting nested symlinks
- **Absolute/Relative Path Handling**: Works with both absolute and relative paths
- **Robust Error Checking**: Validates inputs and handles edge cases

### Enhanced `CDWrapper` Class
- **Default Parameters**: Zero-configuration initialization with sensible defaults
- **State Management**: Maintains current directory, home directory, and symlink map
- **Method Chaining**: Fluent API for intuitive command sequences
- **Shell-like API**: Uses `pwd()` for current directory retrieval (similar to shell `pwd` command)
- **Symlink Management**: Add, remove, and update symlinks dynamically
- **Customizable Configuration**: Set home directory and symlink map as needed

## Installation

No installation required - simply copy the `cd_command.py` file to your project and import the functions/classes.

## Usage

### Standalone `cd` Function

```python
from cd_command import cd

# Basic path simplification
result = cd("/home/user/docs", "../A/../B")
# Result: /home/user/B

# Tilde expansion
result = cd("/home/user/docs", "~/projects/code", home="/home/alice")
# Result: /home/alice/projects/code

# Symlink resolution
result = cd("/var/www/html", "../logs", symlink_map={"/var/www": "/srv/http"})
# Result: /srv/http/logs
```

### Enhanced `CDWrapper` Class

```python
from cd_command import CDWrapper

# Default parameters
cd_shell = CDWrapper()
print(cd_shell.pwd())  # Output: /

# Custom configuration
cd_shell = CDWrapper(
    current_dir="/home/user/docs",
    home="/home/user",
    symlink_map={"/home/user/docs": "/var/www/html", "/var/www": "/srv/http"}
)

# Method chaining
cd_shell.cd("../downloads").cd("projects")
print(cd_shell.pwd())  # Output: /home/user/downloads/projects

# Shell-like behavior
cd_shell = CDWrapper(current_dir="/home/user/docs", home="/home/user")
print(cd_shell.pwd())  # /home/user/docs
cd_shell.cd("~/.config")
print(cd_shell.pwd())  # /home/user/.config

# Symlink management
cd_shell.add_symlink("/home/user/app", "/opt/myapp")
cd_shell.cd("/home/user/app/config")
print(cd_shell.pwd())  # /opt/myapp/config
```

## Examples

The `cd_command.py` file includes extensive examples and tests:

1. **Basic Path Operations**:
   - Simple relative paths with `.` and `..`
   - Multiple slashes simplification
   - Absolute path handling

2. **Tilde Expansion**:
   - `~` → home directory
   - `~/subpath` → home directory with subpath

3. **Symlink Resolution**:
   - Basic symlink replacement
   - Nested symlinks
   - Overlapping symlinks (longest match prioritized)

4. **Enhanced CDWrapper Features**:
   - Default parameter behavior
   - Method chaining examples
   - Shell-like command sequences
   - Dynamic symlink management

## Running Tests

Execute the `cd_command.py` file directly to run all tests:

```bash
python cd_command.py
```

This will run 16 comprehensive tests covering all features and display a summary of results.

## Key Implementation Details

### Path Simplification Algorithm
- Uses a stack-based approach to handle path segments
- Processes `.` and `..` by popping/pushing to the stack
- Removes empty segments (multiple slashes)
- Ensures proper leading/trailing slashes

### Symlink Resolution Strategy
- Matches longest prefix first for overlapping symlinks
- Continues resolving until no more symlinks are found
- Handles both exact matches and partial matches

### CDWrapper Design Patterns
- **Builder Pattern**: Allows fluent configuration through method chaining
- **State Pattern**: Maintains internal state across operations
- **Fluent Interface**: Methods return `self` for chained calls
- **Default Arguments**: Sensible defaults for zero-configuration usage

## License

This project is part of the coding_exercise repository and follows the same licensing terms.
