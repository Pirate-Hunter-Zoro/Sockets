# Sockets

This is me having fun with Sockets in Java!<br>
I plan on expanding this project in the future to potentially include reliable delivery packet sending methods between two hosts.
For now, this is simply a server initializer and a client initializer that share the same client socket to communicate with each other – each being recreated until the user terminates the program – to have one be randomly picked to send whatever message the user types into the terminal to the other. The other will report that it heard a message, and print out said message.<br>

# Running Instructions

You can simply compile the four java classes in the src directory, and then run 'Main'. Or you can clone this repository in VSCode and run Main from VSCode!