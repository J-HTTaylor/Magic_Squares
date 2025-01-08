README.txt
*****************
*Warmup
*CS 221-001
*30/08/24
*James Taylor
*****************
OVERVIEW:
Warm up is a System that will allow an end-user to create magic squares with odd numbers. It will also allow them to check files to see if they are valid 'magic squares'. On both occasions it will print the magic square and if it is valid. This code implements the MagicSquareInterface. 

INCLUDED FILES:
*MagicSquare.java -method file (backend)
*MagicSquareDriver.java - method driver file (makes methods have real world usage)
*MagicSquareInterface.java - interface file for consistency across developers
* README.txt - Current file with all needed information

COMPILING AND RUNNING:
To run these files
-  first ensure all files are under the same directory that you are currently in. This can be done with the 'cd' and 'ls' commands to ensure the files above ae shown to you
- next enter ' javac MagicSquareDriver.java" to create the .class file
- thirdly run  java MagicSquareDriver -check <Name of the file you want to check>
    *if the file you want to check isn't in th e same directory use the full path name to ensure it runs correctly. 
OR
-Thirdly run  java MagicSquareDriver -create <new file name> <size of the magic square
    * The size must be odd to create a magic square

-On successfully rns you will be met with a 'matrix report message saying it=f it a magic square or not along with the matrix'

PROGRAM DESIGN AND IMPORTANT CONCEPTS:

MagicSquare implements the methods set out by the interface file. Magic square has two constructors: one for checking if a file is a valid magic square and another to create a valid odd magic square

The first constructor will ensure that the file given is accessible before checking it is a magic square

The second constructor will create a magic 2D array that holds the values for the magic square. These values are calculated by running through all number from one to the square of the number given. After each number is inputted the rows and column is increased. If either of the values grows to outside the bounds the system automatically sets them back to 0. AFter creating the array the size if the matrix is written to a .txt file along with the array itself.

To check that the square is magic the system does four sets of checks (Vertically, Horizontally, and Diagonally starting at both the top corners). Each set then reduces the magic number by their array value. If at any point the magic number doesn't result in 0 after a set has been completed or if a duplicated number is detected it will brake the loops and return 'false'. 

The magic number is calculated with the expression (n((n^2) +1))/2 where n = size of the array.

For the driver, both check and create are tested to ensure that there will be no errors thrown by the method and returns appropriate error messages for the user.

TESTING:

The program was tested against a separate test code where it ran though 8 different sets of 'magic squares' of both valid and invalid squares. It also ran though both constructor types and expected outputs to ensure that the code was correct.

Furthermore a custom test file was created to independently check the constructs and relevant method as they were written.

The main parts that were tested outside of the IDE was ensuring that odd number s and correct file were the only inputs allowed by end-users

DISCUSSION:

The main issue that I faced during this project was getting back into the swing of writing code that works how it is intended. There were multiple times where I forgot how to do things and had to consult the 121 slides. I also had to get my head round the requirements as I have only had experience to GitHub readme for previous semesters.

The moment when it all clicked for me was when I spent my 4th time reading the docx file seeing what I had missed along with reading other people comments about the project. Having that knowledge of other people running into the same issue lead me to think of a solution from a different angle. basically like a rubber duck (which I did use my dogs as ones a fair few times during this project) Over just spending as much time as I did on this eventually got me back into the swing of things