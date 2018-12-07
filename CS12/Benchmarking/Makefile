.SUFFIXES: .java .class
.java.class:
	javac $<
$(Files)
Files = \
	Entry.java \
	Helpers.java \
	Improve.java  \
	ImproveTest.java \
	Search.java  \
	SearchTest.java  \
	Sort.java  \
	SortTest.java \
	SearchTestFormatToCSV.java \

build:$(Files)

ImproveTest.class: $(Files)
Tests = \
	ImproveTest \
	SearchTest \

tests:$(Tests)

clean:
	rm -r *.class

test:
	javac $(Files) && java org.junit.runner.JUnitCore $(Tests)

test2:
	javac $(Files) && java org.junit.runner.JUnitCore SearchTestFormatToCSV
test3:
	javac $(Files) && java org.junit.runner.JUnitCore SortTest
