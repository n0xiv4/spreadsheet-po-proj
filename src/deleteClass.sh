#!/bin/bash

# Define the directory where you want to delete .class files
target_directory="./"

# Use the find command to locate and delete .class files
find "$target_directory" -type f -name "*.class" -exec rm -f {} \;

echo "Deleted all .class files in $target_directory and its subdirectories."
