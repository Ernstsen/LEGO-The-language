# LEGO: The Language
A simple programming language for the LEGO EV3.
## Intent
The intent of this project is to enable children to easily program a Lego EV3 robot. 
The reason we want this, is to give children a good and simple introduction to the world
of programming and logic, as we believe that programming is, and should be, for everyone.

We believe that the fact that the robot is programmed by moving physical items makes
it quite a bit easier for a small child, or a complete beginner, to grasp the structure of computer code. 
We also believe that the fact that the program interacts with the physical realm, makes it 
easy to see changes in code, and motivate the user as the level of abstraction is fairly low. 

## Syntax:
The movement of the robot is defined by a sequece of LEGO blocks, on a picture passed to the
Python. Only the colors of the blocks carry meaning and is interpreted as follows:
- Green &rarr; Move forward
- Blue  &rarr; Turn left
- Red   &rarr; Turn right