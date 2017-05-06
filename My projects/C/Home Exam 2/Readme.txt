I have two c programs, which I named he2server.c and he2client respectively; my server can listen up to 5 clients at same time. 
The server takes a port number as parameter while the clients takes port number and computer name, which is printed by the server as the first time it runs.
 When the clients are connected to the server, they have 7 choices they can request from the server side, which the server can process, their request and send respond back to them.
In the sever side I have made method that make possible for the server to meet the requirement of the clients. And among those method are:
•	A method that prints a list of the files and folders in the working directory.
•	A method that prints folders those are available in the working directory.
•	A method that checks whether a file exists or not.
•	A method that reads files as char and prints them if they are printable otherwise prints as a dot.
•	A method that tells the passed parameter if it link, regular, or directory.
•	A method that that changes into another folder inside working directory.
•	A method that prints current working directory.

The server uses all these method to respond to the client’s request.
I have also used all the programming mechanisms in c programming that allows me to establish a connection between the server and the clients.
I have used select as the pooling mechanism, which allows the server to connect more clients simultaneously.  And the reason I have chosen select I explained in my report.

I have small problem that is blocking IO;
 that is when one client waits   a file name from the user input, the server is blocked because of that blocking input output and can’t respond to other clients until the user inputs the filename.
I could solve this problem; but because of the time, I could not manage it because I found these at late.

Other wise my program works fine and I did not get any errors, all the allocated memories are freed and there is not memory leaks.
