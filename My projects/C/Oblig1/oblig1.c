/*
Navn: Abdullahi Ibrahim Ahmed
Brukenavn: Abdullia
EmneKode: INF1060
Oblig1: Oppgave1
*/

// Her includerer jeg filer som jeg trenger å bruke.

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>
#define MAX 1000

// Her lager jeg link list 
struct lineContent 
{
  char * strline ;
  struct lineContent* nextline;
    
}links;

// denne metode regns ut antall bokstaver i inputfile.
int mystrlen(char *s)
{
    int i =0;
    while (*s++) i++;
    return i;
}

void printlist();
int len;
struct lineContent* first;

int main(int argc, char** argv)
{
  char rev;
  int numberOfLines = 0;
  char rep;
  // programmer må ha tre command linje argumenter ellers avslutte programmet.
  // også vise brukeren command linje input format
  if(argc < 3)
  {
    printf("USAGE: ./opg1  command input_file\n"); 
    return 1;
  }
  
  // Read from the file.
  char* command = argv[1];
  FILE *readfile ;
  readfile =fopen(argv[2],"r");
  char buf[MAX]; 
  struct lineContent* templine;
  
  if(readfile == NULL)
  {
      printf("ERROR: Unable to open file for reading\n"); 
      return 1;
  }


printf("\n");
// her allocerer jeg minne for å holde fisrs.
first =(struct lineContent*)malloc(sizeof(struct lineContent));
templine = first;

// Her leser jeg fra readfile til buf.
while(fgets(buf,MAX,readfile)!=NULL)
{
  numberOfLines++;
  templine->strline = malloc(strlen(buf) +1); // allocer minne
  len = len + mystrlen(buf);
  strcpy(templine->strline, buf);// kopi fra buf til minne.
  templine ->nextline = malloc(sizeof(struct lineContent));
  templine = templine -> nextline;    // setter templine til å peke nest element
}
          

printf("\n");

fclose(readfile); 

if(strcmp(command, "len") == 0)
{
  printf("The number of characters in the input_file are: %d\n",len ); 
}

if(strcmp(command, "replace") == 0)
{
  
  int v;
  char vowel [] = {'a','e','i','o','u','y'};
  for(v = 0; v  <6; v++) 
  {
    printf("\n");
    printf("\n ******* Replace With  '%c'\n", vowel[v]);
    //f = fopen(fil,"r");
    readfile =fopen(argv[2],"r");
    if(readfile == NULL)
    {
        printf("ERROR: Unable to open file for reading\n"); return 1;
    }
    while((rep = getc(readfile)) != EOF) 
    {
      if ( rep == 'e'|| rep == 'a' || rep == 'i' || rep == 'o' || rep == 'u' || rep == 'y' )
      {
         rep = vowel[v];
      }
      printf("%c",rep);
    }
  }
    printf("\n");
    fclose(readfile);
}

if(strcmp(command, "random")==0)
{
  printf("This line is read as Random. \n");
  srand(time(NULL));  
  int random = rand() % numberOfLines;
  struct lineContent *ramline = first;
  int line = 0;
  while(ramline != NULL)
  {
    if(random == line)
    {
      printf("%s",ramline->strline);
    }
    line++;
    ramline = ramline -> nextline;
  }
  printf("\n");
}

if(strcmp(command, "print") == 0)
{
    
    printlist();
}

if(strcmp(command, "remove")==0)
{
    readfile =fopen(argv[2],"r");

    if(readfile == NULL) 
    {
        printf("ERROR: Unable to open file for reading\n"); 
        return 1;  
    }
    else 
    {
      while((rev = fgetc(readfile)) != EOF) 
      {    
         if (rev != 'e' && rev != 'a' && rev != 'i' && rev != 'o' && rev != 'y' && rev != 'u' ) 
         {
            printf("%c",rev);
          }
      }   
    }
    fclose(readfile);  
}
      

printf("\n");
return 0;

}
// print metode.
void printlist()
{

  struct lineContent* tempLine = first;

        while(tempLine->nextline!=NULL)
        {
          printf("%s \n", tempLine->strline);
                
          tempLine = tempLine->nextline;
        
        }
        
}

