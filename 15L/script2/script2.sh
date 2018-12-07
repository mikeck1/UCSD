#!/bin/bash
#
# CSE 15L - Fall 2018
# Scripting Project 2 (Due: December 7, 11:59pm)
#
# Name:  Tracker Wonderdog
# PID:   AXXXXXXXX
# Login: cs15xzz
#
# For the final scripting assignment in CSE 15L, you will be writing your own
# implementation of the `gethw` script that you have been using all quarter.
#
# You should be fairly familiar with what the script does, but just to bring
# everyone on the same page, here are some expected behaviors of your script
# upon different use cases:
#
# 1. Script usage error
#
#    Your script takes exactly one positional parameter. If a user passes in
#    incorrect number of parameters, print the following usage message and exit
#    the script with code 1.
#
#    USAGE="Usage: ./script2.sh [hw-name]"
#
# 2. Invalid homework error
#
#    Your script takes in the name of the homework as its only parameter. For
#    example: `hw1` or `script2`. Your script should look at the public
#    `homework` directory, found at `~/../public/homework` to see if a homework
#    of the same name can be found. If the user has passed in a homework name
#    that does not match any directory inside public `homework`, print the
#    following error message and exit the script with code 1.
#
#    EINVALID="Invalid homework: $1"
#
# 3. Retrieving valid homework for the first time
#
#    If the parameter turns out to be valid, and the homework directory does not
#    already exist inside your home directory, simply go ahead and copy the
#    entire homework directory matching the specified name under `~/homework`
#    and print the following message once it's done.
#
#    SUCCESS="Done. Navigate to \`~/homework/$1\` to get started."
#
# 4. Retrieving valid homework when the directory already exists
#
#    In case the user forgot that the homework is already half way done, it
#    would be very upsetting if the `gethw` script simply copies a fresh set
#    of empty homework files and overwrites the existing ones. To prevent such
#    incidents from happening, if a user specifies a homework name that already
#    exists under `~/homework/`, prompt the user for each individual file
#    inside the homework directory to ask for confirmation whether the user
#    wants the this file to be overwritten by a fresh copy. Use the following
#    strings for user interaction.
#
#    PROMPT="$file already exists. Overwrite? (y/N) "
#    ABORT="Skipping $file."
#
#    Notice that the 'N' is capitalized, which means that the response is
#    negative by default -- unless the user either enters 'y' or 'Y'. (We won't
#    be testing any response other than upper and lower case 'n' and 'y', but we
#    will be testing on empty response where the user simply hits "enter"
#    without any input.  In this case, your script should treat it as negative.
#
#
#===============================================================================
# USEFUL TIPS
#===============================================================================
#
# 1. To access both public and home directory, use defined environment variables
#    `$PUBLIC` and `$HOME` (or tilde `~`) instead of hard coding the absolute
#    path -- we can't grade you if you hard code it.
#
# 2. You may find the command `read` useful when prompting the user. Check out
#    its man page to best utilize what it can do for you.
#
# 3. For reference, you can always run your script against the `gethw` command
#    on various test cases. We won't be testing beyond what was covered in the
#    above section of the write-up.
#
#
#===============================================================================
# YOUR CODE STARTS BELOW -- HAVE FUN
#===============================================================================

DEST_DIR="$HOME/homework/$1"
file=$DEST_DIR
# Declare any global variables here
USAGE="Usage: $0 [hw-name]"
EINVALID="Invalid homework: $1"
PROMPT="$file already exists. Overwrite? (y/N) "
ABORT="Skipping $file."
SUCCESS="Done. Navigate to \`~/homework/$1\` to get started."
SRC_DIR="$PUBLIC/homework/$1"
# Here are a couple of steps to get started on the script:

# Handle the incorrect number of parameters below:
if test $# -ne 1; then #if incorect number of perameters
	echo "${USAGE}"
	exit 1
fi

# Handle inputs not existing in the public directory below:
cd $PUBLIC/homework # go to public directory
exists=`ls | grep $1` # check if it exists
if test "$exists" != "$1"; then # if not in the ls output
	echo "${EINVALID}" #say it is invalid
	exit 1 # exit the program
fi
# Create the new directory if it doesn't exist yet
cd $HOME/
hwfldrExists=`ls | grep homework`
if test "$hwfldrExists" == ""; then
	cd $HOME/homework/script2 && mkdir homework
fi
# 1. Iterate through the files in the source directory
#    a) If a file does exist in the new directory, ask the user if
#       they want to overwrite each file.
exist="false" # this is used to stop the program from saying success twice
cd ~/homework #go into homework
fullpath=`pwd` #get the path
FILES=~/homework/* #homework folder
for f in $FILES #check each filename
	do
		if test "$f" == "${fullpath}/$1"; then #check if file exists
			exist="true"
			echo -n $PROMPT
			read -n1 answer
			if [ "$answer" != "${answer#[Yy]}" ] ;then #check if to overwrite file
				cd ~/homework 
				rm -r $1 #remove existing file
				ech=`pwd`
				cp -r $PUBLIC/homework/$1 . #overwrite file
				echo -e "\n$SUCCESS" ##echo that it was successful
				else
					echo -e "\n$ABORT"
					fi
		fi
		# take action on each file. $f store current file name
		done

#    b) Otherwise just copy the file
if test "$exist" == "false"; then #the file did not exist so we copy the file
	mkdir $1
	cp -r $PUBLIC/homework/$1 ~/homework/ #copy the file
	echo $SUCCESS #output successful
fi
# 2. Let the user know the script is complete, and return the proper error code.
