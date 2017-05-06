/*
Navn: Abdullahi Ibrahim Ahmed
Brukenavn: Abdullia
EmneKode: INF1060
Oblig1: Oppgave1
*/

// Her includerer jeg filer som jeg trenger å bruke.
# include <stdio.h>
# include <stdlib.h>
# include <string.h>
# define FALSE 0
# define MAX 1000
// Her declerer jeg noen varibler som jeg vil accessere hele filen " Globalt"
char* buf;
FILE *readfile ;
FILE *writefile ;
char* command;
char* filname;
char* filnamer;
// metode prototype
void compress(FILE *f);
void decompress(FILE *f, FILE *fw);

 // les metode
void readFromFile()
   {
      buf = (char*) malloc(sizeof(char)*MAX);
      while(fgets(buf,1000,readfile)!=NULL)
      {
        printf("%s", buf);
      }
      printf("\n");
   }
 // denne metode vil lagre de fire bokstavene ('',:,@,/n) til ett bit for å compresse.
  // og retunerer de fire som ett. 
   char recharmetod(char rechar, char cadd)
   {
      rechar = rechar << 2;
      if(cadd == ' ')
      {      
        rechar = rechar | 0b00;
      }
      else if(cadd == ':')
      {
        rechar = rechar | 0b01;
      }
      else if(cadd == '@')
      {
        rechar = rechar | 0b10;
      }     
      else if(cadd == '\n')
      {
        rechar = rechar | 0b11;
      }
      else
      {   
        exit(0);
      }
        return rechar;
    }


/*long fenght(FILE *f)
  {
    
    fseek(readfile, 0, SEEK_END);
    int lengthOfFile = ftell(readfile);
    fclose(readfile);
    return lengthOfFile;
  }*/


int main(int argc, char** argv)
{
  // programmet skal ha fire command linje argumenter.
  if(argc < 4)
  {
    printf("USAGE: ./opg2  command input_file output_file\n"); return 1;
  }

  command = argv[1];
  filname = argv[2];
  filnamer = argv[3];

  readfile =fopen(argv[2],"rb");
  writefile = fopen(argv[3],"wb");
  
  // Hvis input og output ikke er gitt programmet avsluttes.
  if(readfile == NULL || writefile == NULL)
  {
      printf("ERROR: Unable to open file for reading OR for Writing\n"); return 1;
  }

  if(strcmp(command, "p") == 0)
  { 
      readFromFile();    
  }
  if (strcmp(command, "e") == 0)
  {
    compress(readfile);
  }
  if (strcmp(command, "d") == 0)
  {
    decompress(readfile, writefile);
  }

  fclose(readfile);
  fclose(writefile);

  return 0;

}

// decompress metode
void decompress(FILE *f, FILE *fw)
{
  char signs[] = {' ', ':', '@', '\n'};

  FILE *file = fopen(filname,"rb");
  FILE *filn= fopen(filnamer, "wb");
  
  if (file == NULL || filn == NULL)
  {
    printf("ERROR: Unable to open file for reading \n"); 
    exit(1);
  }
    
  char c, got = 0;
  int counter ;

  while ((c = fgetc(file)) != EOF)
  {
    for(counter = 0;counter<4;counter++) // den løkke går fire ganger for å decompresse c en som er lest fra file.
    {   
       if(counter == 0)
       {
          got = (c >>6) & 3;  // decompress
          putc(signs[got],fw); // skrive til fil
          printf("%c", signs[got]); // skrive til skjermen
       }
        if(counter == 1)
       {
          got = (c >>4) & 3;
          putc(signs[got],fw);
          printf("%c", signs[got]);
       }
       if(counter == 2)
       {
          got = (c >>2) & 3;
          putc(signs[got],fw);
          printf("%c", signs[got]);
       }
       if(counter == 3)
       {
          got = c  & 3;
          putc(signs[got],fw);
          printf("%c", signs[got]); 
       }  
      got = 0;
    }
    
  } 
    

}

// compress metode
void compress(FILE *f)
{
  FILE *file = fopen(filname,"rb");
  FILE *filn= fopen(filnamer, "wb");
  
  if (file == NULL || filn == NULL)
  {
    printf("ERROR: Unable to open file for reading \n"); 
    exit(1);
  }
    
  char c, got;
  int counter = 0;

  while ((c = fgetc(file)) != EOF)
  {
       if(counter >= 4)
       {
          fputc(got,filn);
          got = FALSE;
          counter = FALSE;
          got = recharmetod(got, c);
       }
        else 
       {
          got = recharmetod(got, c);
       }
       counter++;
  } 
}


  
