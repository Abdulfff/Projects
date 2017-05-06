/*
KandidatNummer: 108
EmneKode: INF1060
HjemmeEksamen
*/

// Her includerer jeg filer som jeg trenger å bruke.

#include <stdio.h>
#include <stdlib.h>     
#include <string.h>     
#include <stdint.h> 
#include <unistd.h>
#include <stdint.h>
#include <sys/wait.h>
#include <sys/errno.h>
#include "safefork.c"  
#define MAX 120
#define DEBUG
#define BLOCKS 64
#define BLOCK_SIZE 8
#define DATABLOCK_COUNT 15
 
// Her deklarert jeg noen globale variabler 
extern char **environ;
char line[120];
int no = 1;
int args=1;
int and;
char *param[21];
char *bitmap;
char* datablock;
char* data;
int block_index = 0;

// Her lager jeg link list eller metadata

struct history
{
  char *data;     
  struct history *next; 
  int strlength;
  int dataindex[15];
   
} *first;

struct history*node;

// Denne metode skrives ut ledene tekst
void prompt()
{
	printf("%s@ifish ", getenv("USER")); 
  printf("%d>",no++);
}

// Denne metoden splittes line og settes i param array
void slplitt(char *line)
{

  int i = 0;
  char *t;
  t= strtok (line," ");  
  while(t != NULL)
  {
    param[i++] = t;
    args++;
    t = strtok (NULL," ");
  }
  param[i] = NULL;
  int j;
  
  for(j=0; j<i; j++)
  {
    #ifdef DEBUG 
    fprintf(stderr,"%s\n",param[j]);
    #endif
  }

  //addToHistory();
}

// Denne metoden legges commandoene til History ved hjelp av link list
void exHistory(char *line)
{
 //store every commands entered to the history array. 
  struct history*temp;
  temp = (struct history*)malloc(sizeof (struct history));  
  if(first==NULL)  //hvis den første node er null if the first node is null, do assign the first line read
  { 
        temp->data = malloc(sizeof(line)); //Allocering minne til tmp
        strcpy(temp->data, line);
        temp->next = NULL;
        first = temp; // first er set til temp
       
  } 
  else 
  { 
    struct history *sectmp, *thirdtmp;
    sectmp = first;

    while(1)
    {
      if(sectmp->next !=NULL) // løke inntil nestpeker pekers null
      sectmp=sectmp-> next;
      else
      break; 
    }

    thirdtmp = (struct history*)malloc(sizeof (struct history)); // Allocering minne til thirdtmp  
    thirdtmp->data = malloc(sizeof(line));
    strcpy(thirdtmp->data, line);
    thirdtmp->next = NULL;
    sectmp->next = thirdtmp;
  }
  node = first;
free(temp);
free(temp->data);
//free(thirdtmp);

}

// Denne Metoden skrives ut Histori kommender
void Historyprint()
{
  int i;
  i=args-1;
  printf("%d\n", i);

  printf("Histori list  av sist %d kommender:\n",no-1);
  if(node == NULL) // the listen er null 
  {
    return;
  }
  while(node!=NULL)
  {
    printf("%d: %s\n",i, node-> data); // While node is not NULL.
    node=node->next;
    i--;
  }

}
// Denne Metode leser input fra brukeren
void readCmd()
{   
  if(fgets(line,MAX,stdin) == NULL)
  {
   //har *newline = strrchr(line, '\n');
   //newline = '\0';
    exit(0);
  }

  line[strlen(line)-1] = 0; 

  if(!strlen(line)) // Hvis brukeren taster Enter uten å gi commmand
  { 
    printf("You didn't put any Command The program will Exit\n");
    exit(1);
  }
  exHistory(line); // legge kommendoene til linklist
  and = 0;
  if (strrchr(line,'&')) // Hvis linje innholder & Skrive ut barna id        
    and = 1; 
  
    #ifdef DEBUG 
    //fprintf(stderr,"%s",line);
    #endif  

    slplitt(line); // splitter linjer
}
// Denne Metoden Ekskveres kommandoene
void excutecmd() 
{

  pid_t childpid, parentpid;
  int status;
  char *path;             
  int counter = 0;
  int excuted = 0; 
  char excutecmd[21];      
  
  if (strcmp(param[0],"exit")==0 || strcmp(param[0], "quit")==0) 
  {
    exit(1); 
  }
  if (strcmp(param[0],"h")==0) 
  {
    Historyprint();   
  } 
  if(strcmp(line,"")==0) 
  {
    
  }
 // Start barneprocess
  path = (char*) malloc(sizeof(char)*MAX);
  childpid = safefork();  
  if (childpid == 0) 
  {
    
    path = getenv("PATH");     
    while (*path != '\0') 
    {    
      while (*path != ':' && *path != '\0') 
      { 
        excutecmd[counter++] = *(path++); 
      }
      excutecmd[counter++] = '/';              
      excutecmd[counter] = '\0';             
    
     strcat(excutecmd, line); 

     excuted++;
     execve(excutecmd, param, environ);    
     excuted--;

             
      if (*path != 0)
      path++;
      counter=0;
    }
    // printf("ifish: %s: command not found \n",line);    
	exit(1); 
  }
  else
  {
    free(path);
    if (and == 1) 
    {           
      printf("[%d]\n",(int)childpid);           
    }
    else 
    {                     
      parentpid = wait(&status);
      while (parentpid != childpid) 
      parentpid = wait(&status);             
    } 
  }

}
// Denne Metoden finner Ledige dataBlokk
int finnledigDblock(uint64_t bitmap)
{
 
  unsigned char mask = 01;
  short zero_bit = 0;
  short index = 0;

  while (index < 64) {
    if (((bitmap >> index) & mask) == zero_bit)
      return index;
    index++;
  }

  return -1;

}
// Her jeg har prøvd bitmap men jeg har ikke fått til.

 void addToHistory() 
 {
  
  int len = strlen(line);
  
  if(len%BLOCK_SIZE) 
    len = len + BLOCK_SIZE - (len % BLOCK_SIZE);

  len = len / BLOCK_SIZE;
  
    int x;
  for(x=0; x<len;x++){ // go through
    int index = finnledigDblock(*bitmap);
    block_index = index * 8;
    
     //datablock[index*8];
    //datablock[]
  }

}
// Her prøver jeg å assigne Datana til Data blockker
void assignDataToBitmapandDataBlocker(){
  int i;
  memcpy(datablock,line,2);
  for(i=0; i<BLOCKS; i++)
  {
    
      if(BLOCKS >=0 && BLOCKS<=7){
        data[i]|=(1<<7); 
      }
       if(BLOCKS >=8 && BLOCKS<=15){
        data[i]|=(1<<7); 
      }
       if(BLOCKS >=16 && BLOCKS<=23){
        data[i]|=(1<<7); 
      }

  }

}


// MAIN METODE


int main(void)
{	
  printf(" THIS IS HOME EXAM FOR INF1060 2014\n");

  data = malloc(64*8+8);
  bitmap = data;
  datablock = data+8;

  while(1)
  {	
  
    prompt();
    readCmd();
    excutecmd();

 }
  
	free(data);
	return 0;
}


    
