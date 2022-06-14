all: clear compile run

clear:
	clear

compile:
	@cd src ; javac -d bin Driver.java

run:
	@cd src ; cd bin ; java Driver

clean:
	rm -r bin