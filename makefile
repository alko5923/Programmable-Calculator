DIR=org.ioopm.calculator


all:
	javac -d . -cp .:/usr/share/java/junit4.jar:/usr/share/java/hamcrest/core.jar \
	*.java

run: all
	java $(DIR).Calculator

unittest: all
	java -cp .:/usr/share/java/junit4.jar:/usr/share/java/hamcrest/core.jar \
	org.junit.runner.JUnitCore org.ioopm.calculator.testing.TestsUnitAST 
	
inttest: all
	java -cp .:/usr/share/java/junit4.jar:/usr/share/java/hamcrest/core.jar \
	org.junit.runner.JUnitCore org.ioopm.calculator.testing.TestsIntegrationAST

parsertest: all
	java -cp .:/usr/share/java/junit4.jar:/usr/share/java/hamcrest/core.jar \
	org.junit.runner.JUnitCore org.ioopm.calculator.testing.TestsParser

systemtest: all
	java org.ioopm.calculator.Calculator < SystemInput1.txt > output1.txt
	diff output1.txt SystemOutput1.txt
	java org.ioopm.calculator.Calculator < SystemInput2.txt > output2.txt
	diff output2.txt SystemOutput2.txt
	java org.ioopm.calculator.Calculator < SystemInput3.txt > output3.txt
	diff output3.txt SystemOutput3.txt
	java org.ioopm.calculator.Calculator < SystemInput4.txt > output4.txt
	diff output4.txt SystemOutput4.txt
	java org.ioopm.calculator.Calculator < SystemInput5.txt > output5.txt
	diff output5.txt SystemOutput5.txt
	java org.ioopm.calculator.Calculator < SystemInput6.txt > output6.txt
	diff output6.txt SystemOutput6.txt
	java org.ioopm.calculator.Calculator < SystemInput7.txt > output7.txt
	diff output7.txt SystemOutput7.txt
	java org.ioopm.calculator.Calculator < SystemInput8.txt > output8.txt
	diff output8.txt SystemOutput8.txt

scopetest: all
	java -cp .:/usr/share/java/junit4.jar:/usr/share/java/hamcrest/core.jar \
	org.junit.runner.JUnitCore org.ioopm.calculator.testing.TestsScope

condtest: all
	java -cp .:/usr/share/java/junit4.jar:/usr/share/java/hamcrest/core.jar \
	org.junit.runner.JUnitCore org.ioopm.calculator.testing.TestsConditionals

functest: all
	java -cp .:/usr/share/java/junit4.jar:/usr/share/java/hamcrest/core.jar \
	org.junit.runner.JUnitCore org.ioopm.calculator.testing.TestsFunctions

clean:
	rm -rf ./org/ioopm/calculator/*.class ./org/ioopm/calculator/*/*.class 
