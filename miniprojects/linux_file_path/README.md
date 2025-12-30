# Linux File Path - CD Command Simulation

[![Python](https://img.shields.io/badge/Python-3.6+-blue.svg)](https://www.python.org/)
[![License](https://img.shields.io/badge/License-MIT-lightgray.svg)](https://opensource.org/licenses/MIT)

A robust Python implementation of the Linux `cd` command with advanced features and a shell-like wrapper class for intuitive path management.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [API Reference](#api-reference)
  - [Core `cd` Function](#core-cd-function)
  - [Enhanced `CDWrapper` Class](#enhanced-cdwrapper-class)
    - [Initialization](#initialization)
    - [Methods](#methods)
- [Usage Examples](#usage-examples)
  - [Basic Path Operations](#basic-path-operations)
  - [Advanced CDWrapper Usage](#advanced-cdwrapper-usage)
  - [Symlink Management](#symlink-management)
- [Testing](#testing)
- [Implementation Details](#implementation-details)
  - [Path Simplification Algorithm](#path-simplification-algorithm)
  - [Symlink Resolution Strategy](#symlink-resolution-strategy)
  - [Design Patterns](#design-patterns)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## 📖 Overview

This project provides a faithful simulation of the Linux `cd` command with extended capabilities:

- **Path Resolution**: Handles complex path operations including `.`, `..`, and multiple slashes
- **Tilde Expansion**: Supports `~` notation for home directory reference
- **Symlink Management**: Advanced symbolic link resolution with support for nested and overlapping links
- **Shell-like Interface**: Enhanced wrapper class that maintains state across operations
- **Method Chaining**: Fluent API for intuitive command sequences

The implementation is designed to be both educational and practical, demonstrating core algorithms for path manipulation while providing a reusable component for Python applications.

## ✨ Features

### Core `cd` Function

- **Robust Path Simplification**: Stack-based algorithm for reliable path processing
- **Tilde Expansion**: Seamless home directory integration
- **Symlink Resolution**: Intelligent mapping of symbolic links to real paths
- **Type Safety**: Comprehensive input validation and error handling
- **Cross-Platform Compatibility**: Designed for Linux-style path conventions

### Enhanced `CDWrapper` Class

- **Zero-Configuration Startup**: Sensible defaults for immediate use
- **Persistent State**: Maintains current directory, home, and symlink mappings
- **Fluent Interface**: Method chaining for intuitive command sequences
- **Shell-Like Commands**: `pwd()` method for natural interaction
- **Dynamic Configuration**: Runtime adjustment of home directory and symlinks
- **Comprehensive Documentation**: Well-commented code with clear usage examples

## 🚀 Getting Started

### Prerequisites

- Python 3.6 or higher

### Installation

No formal installation is required. Simply copy the `cd_command.py` file to your project directory:

```bash
cp /path/to/cd_command.py /your/project/directory/
```

## 📚 API Reference

### Core `cd` Function

```python
def cd(current_dir: str, dest: str, home: str = None, symlink_map: dict = None) -> str:
    """
    Simulate Linux cd command with path simplification, ~ expansion, and optional symlink resolution.
    
    Args:
        current_dir: Current working directory (absolute path)
        dest: Destination path (relative or absolute)
        home: Optional home directory for ~ expansion
        symlink_map: Optional dictionary mapping symlinks to real paths
        
    Returns:
        Simplified absolute path after cd operation
    
    Raises:
        ValueError: If current_dir is not absolute, or home is required for ~ expansion
    """
```

### Enhanced `CDWrapper` Class

#### Initialization

```python
class CDWrapper:
    def __init__(self, current_dir: str = None, home: str = None, symlink_map: dict = None):
        """
        Initialize the CD wrapper with smart default parameters.
        
        Args:
            current_dir: Initial current working directory (default: "/")
            home: Default home directory for ~ expansion (default: "/home/user")
            symlink_map: Default symlink mapping (default: {})
        """
```

#### Methods

| Method | Description | Returns |
|--------|-------------|---------|
| `cd(dest: str)` | Change directory to specified path | `self` (for chaining) |
| `pwd()` | Get current working directory | `str` |
| `set_home(home: str)` | Update home directory | `self` (for chaining) |
| `set_symlink_map(symlink_map: dict)` | Replace entire symlink map | `self` (for chaining) |
| `add_symlink(link_path: str, real_path: str)` | Add single symlink | `self` (for chaining) |
| `remove_symlink(link_path: str)` | Remove single symlink | `self` (for chaining) |
| `__repr__()` | String representation of instance | `str` |

## 💡 Usage Examples

### Basic Path Operations

```python
from cd_command import cd

# Simple path simplification
result = cd("/home/user/docs", "../projects/code/../scripts")
# Result: /home/user/projects/scripts

# Tilde expansion
result = cd("/tmp", "~/Downloads", home="/home/alice")
# Result: /home/alice/Downloads

# Symlink resolution
result = cd("/var/www/html", "../logs", symlink_map={"/var/www": "/srv/http"})
# Result: /srv/http/logs
```

### Advanced CDWrapper Usage

```python
from cd_command import CDWrapper

# Default configuration
cd_shell = CDWrapper()
print(cd_shell.pwd())  # Output: /

# Custom configuration
cd_shell = CDWrapper(
    current_dir="/home/user/docs",
    home="/home/user",
    symlink_map={"/home/user/docs": "/var/www/html"}
)

# Method chaining
cd_shell.cd("../downloads").cd("projects").cd("..")
print(cd_shell.pwd())  # Output: /home/user/downloads

# Shell-like workflow
cd_shell = CDWrapper(current_dir="/home/user/docs", home="/home/user")
print(f"Current: {cd_shell.pwd()}")  # /home/user/docs
cd_shell.cd("~/.config")
print(f"After cd ~/.config: {cd_shell.pwd()}")  # /home/user/.config
```

### Symlink Management

```python
from cd_command import CDWrapper

# Create shell with initial symlinks
cd_shell = CDWrapper(
    current_dir="/home/user",
    symlink_map={"/home/user/app": "/opt/myapp"}
)

# Add additional symlink
cd_shell.add_symlink("/opt/myapp/config", "/etc/app/config")

# Navigate through symlinks
cd_shell.cd("/home/user/app/config")
print(cd_shell.pwd())  # Output: /etc/app/config

# Remove a symlink
cd_shell.remove_symlink("/home/user/app")
```

## 🧪 Testing

The project includes comprehensive test cases covering all features:

```bash
python cd_command.py
```

### Test Coverage

- **Basic Path Operations**: 4 test cases
- **Tilde Expansion**: 2 test cases
- **Symlink Resolution**: 6 test cases
- **Edge Cases**: 4 test cases

Total: 16 comprehensive tests

## 🔧 Implementation Details

### Path Simplification Algorithm

The path simplification uses a stack-based approach:

1. **Split** the path into segments using `/` as delimiter
2. **Process** each segment:
   - Skip empty segments (multiple slashes)
   - Skip `.` segments (current directory)
   - Pop from stack for `..` segments (parent directory)
   - Push other segments to stack
3. **Reconstruct** the path from the stack
4. **Normalize** slashes and ensure proper format

### Symlink Resolution Strategy

Symlink resolution follows these rules:

1. **Longest Match First**: Prioritizes longer symlink paths for overlapping matches
2. **Recursive Resolution**: Continues resolving until no more symlinks are found
3. **Prefix Matching**: Handles both exact and partial path matches
4. **Cycle Detection**: Prevents infinite loops (not implemented yet)

### Design Patterns

The implementation uses several design patterns:

- **Builder Pattern**: Method chaining for incremental configuration
- **State Pattern**: Maintains persistent state across operations
- **Strategy Pattern**: Different algorithms for path processing
- **Fluent Interface**: Intuitive API design for readability

## 📁 Project Structure

```
linux_file_path/
├── cd_command.py    # Main implementation
└── README.md        # This documentation
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

### Guidelines

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Make your changes
4. Run tests (`python cd_command.py`)
5. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
6. Push to the branch (`git push origin feature/AmazingFeature`)
7. Open a Pull Request

## 📄 License

This project is part of the `coding_exercise` repository and follows the same licensing terms.

## 📞 Support

For support or questions, please open an issue in the repository or contact the project maintainers.
