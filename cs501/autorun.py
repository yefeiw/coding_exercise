# This script aims to automatically compile the latest modified file and run it.
# The assumption is that the file must be of type java.
import glob
import os
import logging
import traceback

def newest_file_in_tree(rootfolder, extension=".java"):
    return max(
        (os.path.join(dirname, filename)
        for dirname, dirnames, filenames in os.walk(rootfolder)
        for filename in filenames
        if filename.endswith(extension)),
        key=lambda fn: os.stat(fn).st_mtime)

##main program
try:
	src_java_file = newest_file_in_tree(".")
	print src_java_file
	src_java_paths = src_java_file.split('/')
	print src_java_paths
	current_week = src_java_paths[1]
	print current_week
	sub_folder = src_java_paths[2] 
	src_java_program = src_java_paths[-1].split('.')[0]
	print src_java_program
	util_java_file = './common/Input.java'
	os.system('javac '+src_java_file+' ' +util_java_file)
	os.system('mv ./common/*.class ./')
	os.system('mv '+current_week+'/'+sub_folder+'/*.class ./')
	os.system('java '+src_java_program)
except:
	logging.error(traceback.print_exc())
finally:
		os.system(' rm *.class')



