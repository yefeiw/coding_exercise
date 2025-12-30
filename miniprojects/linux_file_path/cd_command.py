class CDWrapper:
    """
    Wrapper class for the cd command that maintains state and provides default parameters.
    
    Similar to how a shell handles cd command with implicit context.
    """
    
    def __init__(self, current_dir: str = None, home: str = None, symlink_map: dict = None):
        """
        Initialize the CD wrapper with smart default parameters.
        
        Args:
            current_dir: Initial current working directory (default: "/")
            home: Default home directory for ~ expansion (default: "/home/user")
            symlink_map: Default symlink mapping (default: empty dict)
        """
        # Default current directory to root if not provided
        self.current_dir = current_dir if current_dir is not None else "/"
        # Ensure current_dir is absolute
        if not self.current_dir.startswith('/'):
            self.current_dir = "/" + self.current_dir
        
        # Default home directory to /home/user if not provided
        self.home = home if home is not None else "/home/user"
        # Ensure home is absolute
        if not self.home.startswith('/'):
            self.home = "/" + self.home
        
        # Default symlink map to empty dict
        self.symlink_map = symlink_map if symlink_map is not None else {}
    
    def cd(self, dest: str) -> 'CDWrapper':
        """
        Change directory using the maintained state.
        
        Args:
            dest: Destination path (relative or absolute)
            
        Returns:
            Self for method chaining
        """
        self.current_dir = cd(self.current_dir, dest, self.home, self.symlink_map)
        return self
    
    def pwd(self) -> str:
        """Return current working directory (pwd command equivalent)."""
        return self.current_dir
    
    def set_home(self, home: str) -> 'CDWrapper':
        """Update the home directory.
        
        Args:
            home: New home directory path
            
        Returns:
            Self for method chaining
        """
        self.home = home
        return self
    
    def set_symlink_map(self, symlink_map: dict) -> 'CDWrapper':
        """Update the symlink mapping.
        
        Args:
            symlink_map: New symlink mapping dictionary
            
        Returns:
            Self for method chaining
        """
        self.symlink_map = symlink_map
        return self
    
    def add_symlink(self, link_path: str, real_path: str) -> 'CDWrapper':
        """Add a single symlink to the symlink map.
        
        Args:
            link_path: Symlink path
            real_path: Real path the symlink points to
            
        Returns:
            Self for method chaining
        """
        self.symlink_map[link_path] = real_path
        return self
    
    def remove_symlink(self, link_path: str) -> 'CDWrapper':
        """Remove a symlink from the symlink map.
        
        Args:
            link_path: Symlink path to remove
            
        Returns:
            Self for method chaining
        """
        if link_path in self.symlink_map:
            del self.symlink_map[link_path]
        return self
    
    def __repr__(self) -> str:
        """String representation of the CDWrapper instance."""
        return f"CDWrapper(current_dir='{self.current_dir}', home='{self.home}', symlink_map={self.symlink_map})"


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
    """
    # Validate inputs
    if not current_dir.startswith('/'):
        raise ValueError("current_dir must be an absolute path")
    
    # Step 1: Handle ~ expansion
    if dest.startswith('~'):
        if home is None:
            raise ValueError("home parameter required for ~ expansion")
        # Ensure home directory is absolute
        if not home.startswith('/'):
            raise ValueError("home directory must be an absolute path")
        # Replace ~ with home directory
        if dest == '~':
            dest = home
        else:  # ~/subpath case
            dest = home + dest[1:]
    
    # Step 2: Build full logical path (before resolving symlinks)
    if dest.startswith('/'):
        # Absolute path - use directly
        full_logical_path = dest
    else:
        # Relative path - combine with current directory (logical)
        full_logical_path = current_dir.rstrip('/') + '/' + dest
    
    # Step 3: Simplify the logical path
    path_parts = full_logical_path.split('/')
    simplified_parts = []
    
    for part in path_parts:
        if part == '' or part == '.':
            continue
        elif part == '..':
            if simplified_parts:
                simplified_parts.pop()
        else:
            simplified_parts.append(part)
    
    simplified_logical_path = '/' + '/'.join(simplified_parts)
    
    # Step 4: Resolve symlinks in the simplified path
    if symlink_map:
        resolved_path = simplified_logical_path
        
        # Keep resolving until no more symlinks are found
        resolved = False
        while not resolved:
            resolved = True  # Assume no more symlinks unless we find one
            
            # Check for longest matching prefix in symlink_map (sort by length descending)
            for link_path, real_path in sorted(symlink_map.items(), key=lambda x: len(x[0]), reverse=True):
                if resolved_path == link_path:
                    # Exact match - replace entire path
                    resolved_path = real_path
                    resolved = False
                    break
                elif resolved_path.startswith(f"{link_path}/"):
                    # Partial match - replace prefix
                    resolved_path = real_path + resolved_path[len(link_path):]
                    resolved = False
                    break
        
        # Update with resolved path
        simplified_logical_path = resolved_path
    
    # Step 5: Ensure proper formatting
    if simplified_logical_path != '/' and simplified_logical_path.endswith('/'):
        simplified_logical_path = simplified_logical_path[:-1]
    
    return simplified_logical_path


# Test cases to verify functionality
if __name__ == "__main__":
    print("=" * 60)
    print("        LINUX CD COMMAND TEST RESULTS")
    print("=" * 60)
    print()
    
    test_count = 0
    pass_count = 0
    
    # Helper function to run and display test results
    def run_test(description, current_dir, dest, expected, home=None, symlink_map=None):
        global test_count, pass_count
        test_count += 1
        
        # Display test information
        print(f"Test {test_count}: {description}")
        print("-" * 50)
        print(f"Current directory: {current_dir}")
        print(f"Destination path: {dest}")
        if home is not None:
            print(f"Home directory:    {home}")
        if symlink_map is not None:
            print(f"Symlink map:       {symlink_map}")
        print()
        
        # Run the test
        result = cd(current_dir, dest, home=home, symlink_map=symlink_map)
        
        # Display results
        print(f"Expected: {expected}")
        print(f"Actual:   {result}")
        status = "PASS" if result == expected else "FAIL"
        if status == "PASS":
            pass_count += 1
        print(f"Status:   {status}")
        print()
    
    # Run all tests
    
    # Test 1: Basic path simplification
    run_test(
        "Basic path simplification",
        "/home/user/docs",
        "../A/../B",
        "/home/user/B"
    )
    
    # Test 2: Tilde expansion
    run_test(
        "Tilde expansion",
        "/home/user/docs",
        "~/projects/code",
        "/home/alice/projects/code",
        home="/home/alice"
    )
    
    # Test 3: Symbolic link resolution
    run_test(
        "Symbolic link resolution",
        "/var/www/html",
        "../logs",
        "/srv/http/logs",
        symlink_map={"/var/www": "/srv/http"}
    )
    
    # Test 4: Absolute path
    run_test(
        "Absolute path",
        "/home/user/docs",
        "/etc/../usr/local/bin",
        "/usr/local/bin"
    )
    
    # Test 5: Multiple slashes
    run_test(
        "Multiple slashes",
        "/home/user/docs",
        "/a//b///c",
        "/a/b/c"
    )
    
    # Test 6: Current directory (.)
    run_test(
        "Current directory (.)",
        "/home/user/docs",
        "././test",
        "/home/user/docs/test"
    )
    
    # Test 7: Parent directory at root
    run_test(
        "Parent directory at root",
        "/",
        "..",
        "/"
    )
    
    # Test 8: Complex relative path
    run_test(
        "Complex relative path",
        "/a/b/c/d/e",
        "../../f/../g/h",
        "/a/b/c/g/h"
    )
    
    # Additional test cases for better coverage
    
    # Test 9: Nested symlink resolution
    run_test(
        "Nested symlink resolution",
        "/link1",
        "link2/file.txt",
        "/final/destination/file.txt",
        symlink_map={
            "/link1": "/real/directory",
            "/real/directory/link2": "/final/destination"
        }
    )
    
    # Test 10: Trailing slashes
    run_test(
        "Trailing slashes",
        "/home/user",
        "docs/",
        "/home/user/docs"
    )
    
    # Test 11: Absolute path with symlink
    run_test(
        "Absolute path with symlink",
        "/home/user",
        "/var/www/html",
        "/srv/http/html",
        symlink_map={"/var/www": "/srv/http"}
    )
    
    # Test 12: Multiple symlinks with overlapping paths
    run_test(
        "Multiple symlinks with overlapping paths",
        "/long/path/link/nested",
        "../file.txt",
        "/real/nested/file.txt",
        symlink_map={
            "/long/path/link": "/real/long",
            "/long/path/link/nested": "/real/nested"
        }
    )
    
    # Test 13: Tilde expansion to root
    run_test(
        "Tilde expansion to root",
        "/home/user",
        "~",
        "/",
        home="/"
    )
    
    # Test 14: Complex path with symlinks and simplification
    run_test(
        "Complex path with symlinks and simplification",
        "/a/b/c/d",
        "../../e/f/../g",
        "/x/y/e/g",
        symlink_map={
            "/a/b": "/x/y",
            "/x/y/c": "/p/q"
        }
    )
    
    # Test 15: Edge case - symlink resolving to another symlink
    run_test(
        "Edge case - symlink resolving to another symlink",
        "/",
        "/chain1/file.txt",
        "/final/chain/file.txt",
        symlink_map={
            "/chain1": "/chain2",
            "/chain2": "/final/chain"
        }
    )
    
    # Test 16: Edge case - complex path with multiple symlinks
    run_test(
        "Edge case - complex path with multiple symlinks",
        "/a/x/y/z",
        "../../file.txt",
        "/real/x/file.txt",
        symlink_map={
            "/a/x": "/real/x",
            "/real/x/y": "/real/y",
            "/real/y/z": "/final/z"
        }
    )
    
    # Summary
    print("=" * 60)
    print(f"TEST SUMMARY: {pass_count} out of {test_count} tests passed")
    print("=" * 60)
    
    # Manual verification of the logic
    print("\nKEY FEATURES IMPLEMENTED:")
    features = [
        "Path simplification: Uses stack to handle ., .., and multiple slashes",
        "Tilde expansion: Replaces ~ with home directory",
        "Symlink resolution: Matches longest prefix and replaces with real path",
        "Absolute path handling: Starts fresh with empty stack",
        "Relative path handling: Builds on current directory",
        "Nested symlink resolution: Continues resolving until no more symlinks are found",
        "Overlapping symlink handling: Prioritizes longest matching prefix"
    ]
    
    for i, feature in enumerate(features, 1):
        print(f"{i}. {feature}")
    
    print()
    print("=" * 60)


# Example usage of enhanced CDWrapper (simulating shell-like behavior)
print("\n" + "=" * 60)
print("        CDWRAPPER ENHANCED EXAMPLE")
print("=" * 60)

# Example 1: Default parameters
print("1. CDWrapper with default parameters:")
print("-" * 50)
cd_shell_default = CDWrapper()
print(f"Default current_dir: {cd_shell_default.pwd()}")
print(f"Default home: {cd_shell_default.home}")
print(f"Default symlink_map: {cd_shell_default.symlink_map}")

print()

# Example 2: Custom configuration
print("2. CDWrapper with custom configuration:")
print("-" * 50)
cd_shell = CDWrapper(
    current_dir="/home/user/docs",
    home="/home/user",
    symlink_map={"/home/user/docs": "/var/www/html", "/var/www": "/srv/http"}
)

print(f"Initial directory: {cd_shell.pwd()}")
print(f"Home directory: {cd_shell.home}")
print(f"Symlink map: {cd_shell.symlink_map}")

# Example 3: Method chaining
print()
print("3. Method chaining example:")
print("-" * 50)
cd_shell2 = CDWrapper(current_dir="/home/user")
result = cd_shell2.cd("docs").cd("../downloads").cd("projects")
print(f"Chained cd commands: {result.pwd()}")

# Example 4: Enhanced shell-like behavior
print()
print("4. Shell-like cd commands with pwd:")
print("-" * 50)
cd_shell3 = CDWrapper(
    current_dir="/home/user/docs",
    home="/home/user",
    symlink_map={"/home/user/docs": "/var/www/html", "/var/www": "/srv/http"}
)

print(f"pwd -> {cd_shell3.pwd()}")
cd_shell3.cd("../downloads")
print(f"cd ../downloads && pwd -> {cd_shell3.pwd()}")
cd_shell3.cd("~/.config")
print(f"cd ~/.config && pwd -> {cd_shell3.pwd()}")
cd_shell3.cd("/var/www/html")
print(f"cd /var/www/html && pwd -> {cd_shell3.pwd()}")  # Resolves symlinks
cd_shell3.cd("../css/styles")
print(f"cd ../css/styles && pwd -> {cd_shell3.pwd()}")
cd_shell3.cd("~")
print(f"cd ~ && pwd -> {cd_shell3.pwd()}")

# Example 5: Symlink management
print()
print("5. Symlink management:")
print("-" * 50)
cd_shell4 = CDWrapper(current_dir="/home/user")
# Add symlinks using method chaining
cd_shell4.add_symlink("/home/user/app", "/opt/myapp").add_symlink("/opt/myapp/config", "/etc/myapp")
print(f"Added symlinks: {cd_shell4.symlink_map}")
cd_shell4.cd("/home/user/app/config")
print(f"cd /home/user/app/config -> {cd_shell4.pwd()}")

# Example 6: String representation
print()
print("6. String representation:")
print("-" * 50)
cd_shell5 = CDWrapper(current_dir="/home/user", symlink_map={"/home/user": "/mnt/user"})
print(f"CDWrapper instance: {cd_shell5}")

print()
print("=" * 60)
