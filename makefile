all: 
	javac BritishSquare.java

run:
	java BritishSquare human bad < example_in_2.txt > my_out_2.txt
	java BritishSquare human human < example_in_3.txt > my_out_3.txt

show:
	vimdiff my_out_2.txt example_out_2.txt
	vimdiff my_out_3.txt example_out_3.txt

clean:
	rm *class
	rm my_out_*
	clear