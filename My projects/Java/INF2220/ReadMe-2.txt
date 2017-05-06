

1.  Algorithm:
	
My main class (Oblig3) is straight forward; it is just reading the files and printing out the matches. The other class has the a constructor that creates the array for the Boyer Moore Horpsel Algorithm. The other method is the one I implemented for the bad character shift algorithm. I would not repeat all the basic algorithms theory but this is a brief description of how i implemented. First, I set the last to the length of needle and set the offset to zero.  I loop until the end of the haystack text this allows me to get all the matches but not only the first.
if there is no match, the offset is moved to the right with the length of 		unmatched needle. if there is a match we add it to the list and increase the 		offset by 1 for the next search. the matched text is in a list so that i can print it out.


2. My program Compiles with "javac *java”.


3. The Main method is in Oblig3.java file.


4. Assumptions

I assume that the haystack file contains some special character like space, tap and a new line. because of this i used String builder to get exact position or index  of the text that matched the pattern by adding extra new lines.


5. Peculiarities

In the assumptions; specially by adding extra new lines, will increase the length of the haystack file and this will lead to display fail message like “ No match found” instead of “ The needle is greater than haystack”. in this case I tested and is when the needle file contains c_d and haystack contains c only, but it adds two new lines that makes the haystack length 3 which is equal to the needle file. To solve this i used trim() method to get the exact characters of the haystack file in this case when i used trim() the haystack file will be 1 length because it trims the new lines. And it will get right message which says “ The needle is greater than haystack”.


5. Status

Every thing is working properly as i know:

The  two files are read properly

Any potential errors are handled whether it related with the reading file,  	   	   the files are empty or the needle file is greater than haystack.
find the matched text with the pattern if any.

6. Credit


My code is influenced by lecture specially Text Algorithm. 


TEST CASES:

CASE 1:

When needle has multiple matches

[abdullia@fleurus Oblig3]$ javac *.java
[abdullia@fleurus Oblig3]$ java Oblig3 needle.txt haystack.txt

 Matched text is from position:: 11
 Matched text is   : jab       


 Matched text is from position:: 22
 Matched text is   : jbb       


 Matched text is from position:: 37
 Matched text is   : jcb       


 Matched text is from position:: 53
 Matched text is   : jdb       


 Matched text is from position:: 68
 Matched text is   : jeb       


 Matched text is from position:: 84
 Matched text is   : jfb       

CASE 2:


When needle has multiple wild cards:

[abdullia@dictateur Oblig3]$ javac *.java
[abdullia@dictateur Oblig3]$ java Oblig3 needle1.txt haystack1.txt

 Matched text is from position:: 0
 Matched text is   : messi is better than ronaldo      

CASE 3:


When the needle is empty: 

[abdullia@dictateur Oblig3]$ javac *.java
[abdullia@dictateur Oblig3]$ java Oblig3 needle2.txt haystack.txt
needle  is empty

CASE 4:

When the haystack is empty: 

[abdullia@dictateur Oblig3]$ javac *.java
[abdullia@dictateur Oblig3]$ java Oblig3 needle.txt haystack2.txt
 haystack is empty

CASE 5:

When needle is greater than the haystack: 

[abdullia@dictateur Oblig3]$ javac *.java
[abdullia@dictateur Oblig3]$ java Oblig3 needle.txt haystack3.txt
needle greater than haystack



NB:  

needle2.txt and haystack2.txt are empty files. so just run with empty files.

	


