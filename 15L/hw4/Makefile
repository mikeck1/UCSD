# Homework 4 (Makefile Assignment)
#
# Name:  Michael Kaufman
# PID:   A15747235
# Login: cs15xqc
#
# Your impressive work at your internship has won you a return offer at the
# Galactic Empire. You were but the student; now you are the master! You are
# responsible for all Imperial Vehicles's software reliability. The vehicle
# classes include:
#
#     *   ATAT.java
#     *   ATST.java
#     *   DeathStar.java
#     *   ImperialShuttle.java
#     *   StarDestroyer.java
#     *   TIEBomber.java
#     *   TIEFighter.java
#
# For each of these classes, there is a Tester class that contains all unit
# tests for the corrsponding vehicle named as `(Vehicle)Tester.java`, where
# (Vehicle) can be replaced by any of the vehicle class name.  Write a
# Makefile that does the following:
#
#    ~~ 1.  When type in `make (Vehicle).class`, the corresponding vehicle class
#         is built, if and only if its Java source file has been updated since
#         the last time it was built.
#
#     2.  When type in `make` or `make vehicles`, each vehicle class will be
#         built (ATAT.class, ATST.class, etc.) if and only if the corresponding
#         Java source file has been updated since the last time that class was
#         built.
#
#     ~~3.  When type in `make (Vehicle)Tester.class`, the corresponding tester
#         class is built, if and only if (Vehicle).java or (Vehicle)Tester.java
#         has been updated since the last time the tester was built.
#
#     ~~4.  When type in `make tests`, all tester classes will be built
#         (ATATTester.class, ATSTTester.class, etc.), if and only if the
#         corresponding Java source file for that tester or the tested vehicle
#         has been updated since the last time the tester was built.
#
#    ~~ 5.  When type in `make clean`, all class files will be removed.
#
# Hints:
#
# You are encouraged to use variables to organize your code and increase
# readability. And feel free to create dummy classes for testing purposes.
#==============================================================================
#
# YOUR CODE STARTS HERE
.SUFFIXES: .java .class
.java.class:
	javac $<

# make reads the makefile and begins by processing the first rule
$(Vehicle)

# Vehicles variable
Vehicle = \
	ATAT.class \
	ATST.class \
	DeathStar.class \
	ImperialShuttle.class \
	StarDestroyer.class \
	TIEBomber.class \
	TIEFighter.class \

vehicles:$(Vehicle)

# dependencies
ATATTester.class: ATAT.class 
ATSTTester.class: ATST.class
DeathStarTester.class: DeathStar.class
ImperialShuttleTester.class: ImperialShuttle.class
StarDestroyerTester.class: StarDestroyer.class
TIEBomberTester.class: TIEBomber.class
TIEFighterTester.class: TIEFighter.class



clean:
	rm -r *.class


test = \
	ATATTester.class \
	ATSTTester.class \
	DeathStarTester.class \
	ImperialShuttleTester.class \
	StarDestroyerTester.class \
	TIEBomberTester.class \
	TIEFighterTester.class \

tests:$(test)
