CSCI-140 Project 01: README
===============================
(please use the RETURN key to make multiple lines; don't assume autowrap.)

0. Author Information
---------------------

CS Username: 	 vxw2046		Name:  		Victor Wu

-------------------
1. Problem Analysis
-------------------

Summarize the analysis work you did. 
What new information did it reveal?  How did this help you?
How much time did you spend on problem analysis?

The board to be a 2D array to me right away. I saw that the board was arranged
in a format in which a 2D array would easily meet the requirements of the board
and it would be easy to print a 2D array. This helped me start off the problem
and I designed the board first. I saw that the filling up as another function
and wrote a function to fill up each square accordingly. I spent around half an
hour thinking about this.

------------------
2a. General Design
------------------

Explain the design you developed to use and why. 
What are the major components? What connects to what?
How much time did you spend on design?

The design is a 2D array of objects. I didn’t find a good way to fuse the +, -,
|, numbers, x’s, and o’s, so I just used the most generic of arrays. This may
present problems in the future but it seemed like the simplest solution at the
start of the project and I stayed with it throughout. There is an initial function
that inputs the elements into the starting board and a separate function to draw
everything.

-----------------------------
2b. Design of the Good Player
-----------------------------

Explain the design behind your "good" player.  What was your overall
idea of a good move?

Not yet implemented

-----------------------------
3. Implementation and Testing
-----------------------------

Describe your implementation efforts here; this is the coding and testing.

What did you put into code first?
How did you test it?
How well does the solution work? 
Does it completely solve the problem?
Is anything missing?
How could you do it differently?
How could you improve it?

How much total time would you estimate you worked on the project? 
If you had to do this again, what would you do differently?

First I designed the board. I made a 2D array and used defineOuterSides()
to put all the values necessary in the board. And then I used showBoard()
to print the board and see if it matched the output. Then I went to make
all the print statements before going to make the AI. The AI was the last
part of the project. The project expanded form being the board, to having
print statements matching exactly what was given, to the human AI working.
And then I put in all the boundaries for the human, such as not being able
to put the first move in the center, and the range of the board size. It 
seems to work smoothly as of now. I think there could be a lot of improvements, 
like how I made the board. Maybe a 2D array would not have been the best
thing to use, and using nodes would have made it easier, but the 2D Array
worked for me throughout the project.
This code almost solves the problem, the only thing missing is the good AI
as of now. I think I spent around 4 hours doing the raw code, 4 hours doing
the debugging, and 4 more hours trying to make everything look nice and
making all the comments. 

----------------------
4. Development Process
----------------------

Describe your development process here; this is the overall process.

How much problem analysis did you do before your initial coding effort?
How much design work and thought did you do before your initial coding effort?
How many times did you have to go back to assess the problem?

What did you learn about software development?

I didn’t really look at the problem that much. I just saw the problem
as 3 parts. The first part was the board that had to be implemented for
anything to be seen and for any debugging to happen. The second part of
the project was getting all the input and output to work correctly so that
it could smoothly run. And then finally I finished the AI. After that I
had to make some changes to the code to be compatible with all the test
cases that were presented to me. For instance, I printed out 00 and 01
when I needed to print out 0 and 1. And after moving my scanner from AI
to BritishSquare, I had to change things so that my boundary testing would
work again. I went back many times to look at this problem. It annoyed
me many times but as I was coding it, I felt really good because I was
coding something substantial. And in the end the final product made me 
feel proud of myself and my achievements. 
