/*
	KandidatNummer: 108
	EmneKode: INF1060
	HjemmeEksamen
*/

// Her includerer jeg filer som jeg trenger å bruke.
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <netdb.h> 

// Her deklarert jeg noen globale variabler 

#define MAXBUFF   10000
char buffer[MAXBUFF];
int sock; 
int rw;
char *tempbuff;
char inputstr[100];
char filinfo[100];
char cd[100];
char input;

// metode prototype

void readWrite (char *s);
int loop();

// Denne metode skriver ut hovedmeny.
void menu() 
{
  printf("\n");
  printf("! [1]list content of the current directory(ls)\n");
  printf("! [2] print the name of the current directory(pwd)\n");
  printf("! [3] change the current directory(cd)\n");
  printf("! [4] get the file information\n");
  printf("! [5] display file (cat)\n");
  printf("! [?] this menu\n");
  printf("! [q] quit\n");
  printf("\n");
  printf("cmd (? for help)>?\n");
  loop();
}

// Denne metode skriver ut CDdmeny.

void cdMenu() 
{
  printf("\n");
  printf("  new dir (? for help)>?\n");
  printf("! [1] .. the parent directory(ls)\n");
  printf("! [2] / a new absolute directory \n");
  printf("! [3] a new directory relative to the current position \n");
  printf("! [?] this menu \n");
  printf("! [q] leave this menu \n");
  printf("\n");
}

// Her er Main metode

int main(int argc, char *argv[])
{

  int portno;
  int activate = 1;
  struct sockaddr_in serv_addr;
  struct hostent *server;
  
//brukeren må oppgi datamaskin navn og port nummer
  if (argc < 3) 
  {
     fprintf(stderr,"USAGE %s HOSTNAME PORT\n", argv[0]);
     exit(0);
  }

  portno = atoi(argv[2]);
  if ((sock = socket(AF_INET, SOCK_STREAM, 0)) == -1) 
  {
      perror("SOCKET ERROR");
      exit(1);
  }

 
    if(setsockopt(sock, IPPROTO_TCP, TCP_NODELAY, &activate, sizeof(int))== -1)
    {
	     perror("SET SOCKET ERROR");
	     exit(1);
    }

  if ((server = gethostbyname(argv[1])) == NULL) 
  {
      fprintf(stderr,"ERROR, NO SUCH HOST\n");
      exit(0);
  }   

// opperettet sockets
  bzero((char *) &serv_addr, sizeof(serv_addr));
  serv_addr.sin_family = AF_INET;
  bcopy((char *)server->h_addr, (char *)&serv_addr.sin_addr.s_addr, server->h_length);
  serv_addr.sin_port = htons(portno); 

      
  if (connect(sock,(struct sockaddr *) &serv_addr, sizeof(serv_addr)) == -1)
  { 
      perror("CONNECTION ERROR");
      exit(1);
  }
  printf("Connection Successful: Client Side\n");
  menu();
  loop();
 
}
/* Denne metode gjør send/receive jobben som er den viktigest
   første sender den request til serveren og deretter leser hva severen har sendt som resultat osv. 
   når server sendt noe tester den sendt melding og går den riktig if block.
 
*/

void readWrite (char *s)
{
  tempbuff = s;
  while(1)
  {
    bzero(buffer,MAXBUFF);
    fgets(buffer,MAXBUFF,stdin);
    
    strcpy(buffer,tempbuff);
    rw = write(sock,buffer,sizeof(buffer));
    if (rw < 0) 
         perror("ERROR writing to socket");
  
    rw = read(sock,buffer,sizeof(buffer));
    if (rw < 0) perror("ERROR reading from socket");
    printf("%s\n",buffer);
    
    if(strcmp(buffer, "Menu") == 0)
    {
      menu();
    }
    else if(strcmp(buffer, "q") == 0)
    {
      exit(0);
    }
    
    else if(strcmp(buffer, "cd") == 0)
    {
	
	     
      rw = read(sock,buffer,sizeof(buffer));
      if (rw < 0) perror("ERROR reading from socket");
      printf("%s\n",buffer);
      bzero(buffer,MAXBUFF);

     
      cdMenu();
      printf("Select one of the following options\n" );
      if(fgets(cd,100,stdin) != NULL)
      strcpy(buffer,cd);
      printf("%s\n", buffer);

      // sender hvilke valg for CD. 
      rw = write(sock,buffer,sizeof(buffer));
      if (rw < 0) perror("ERROR writing to socket");


     // leser CD result
      rw = read(sock,buffer,sizeof(buffer));
      if (rw < 0) perror("ERROR reading from socket");
      printf("%s\n",buffer);

      if(strcmp(buffer, "cd?") == 0)
      {
        cdMenu();
      }	
      else if(strcmp(buffer, "3") == 0)
      {
	      // får en ny DIR nam.
	      rw = read(sock,buffer,sizeof(buffer));
	      if (rw < 0) perror("ERROR reading from socket");
	      printf("%s\n",buffer);
	      bzero(buffer,MAXBUFF);

	      printf("Enter the DIR name \n" );
	      if(fgets(filinfo,100,stdin) != NULL)
	      strcpy(buffer,filinfo);
	
        //  sender DIR navn til serveren for å bytte. 
	      rw = write(sock,buffer,sizeof(buffer));
	      if (rw < 0) perror("ERROR writing to socket");
	      bzero(buffer,MAXBUFF);
        //  får resultatet av den byttet DIR.
	      rw = read(sock,buffer,sizeof(buffer));
	      if (rw < 0) perror("ERROR reading from socket");
	      printf("%s\n",buffer);
	      bzero(buffer,MAXBUFF);		
      }
      else if(strcmp(buffer, "qcd") == 0)
  	  {
    		menu();
  	  }
	
    }
    else if(strcmp(buffer, "finfo") == 0)
    {
      // får list av filer å velg mellom.
      rw = read(sock,buffer,sizeof(buffer));
      if (rw < 0) perror("ERROR reading from socket");
      printf("%s\n",buffer);
      bzero(buffer,MAXBUFF);

      // sender navn til filen som vil vite info om.
      printf("Enter file for info \n" );
      if(fgets(filinfo,100,stdin) != NULL)
      strcpy(buffer,filinfo);
      printf("%s\n", buffer);

      rw = write(sock,buffer,sizeof(buffer));
      if (rw < 0) perror("ERROR writing to socket");
	
	    //leser file info
      rw = read(sock,buffer,sizeof(buffer));
      if (rw < 0) perror("ERROR reading from socket");
      printf("%s\n",buffer);

    }
    else if(strcmp(buffer, "cat") == 0)
    {
      rw = read(sock,buffer,sizeof(buffer));
      if (rw < 0) perror("ERROR reading from socket");
      printf("%s\n",buffer);
      bzero(buffer,MAXBUFF);


      printf("Enter the file for printing \n" );
      if(fgets(inputstr,100,stdin) != NULL)

      strcpy(buffer,inputstr);
      rw = write(sock,buffer,sizeof(buffer));
      if (rw < 0) perror("ERROR writing to socket");
    
      rw = read(sock,buffer,sizeof(buffer));
      if (rw < 0) perror("ERROR reading from socket");
      printf("%s\n",buffer);
      bzero(buffer,MAXBUFF);
      
    }
    else if(strcmp(buffer, "none") == 0)
    {
      printf("Please choose a valid Choice");
      menu();
    }

    printf("cmd (? for help)>?\n");
    loop();
  }
}

  int loop()
  {
    for(;;)
    { 

      scanf( "%c", &input );
 
      switch ( input ) {
       
          case '1':            
              printf( " List of the files in the Server Side: \n" );
              tempbuff = "ls";
              readWrite(tempbuff);
              break;
          case '2':          
              printf( " The working directory of the Server: \n" );
              tempbuff = "pwd";
              readWrite(tempbuff);
              break;
          case '3':         
              
              tempbuff = "cd";
              printf( " Select the Folder You want to change to: \n" );
              readWrite(tempbuff);
              break;
          case '4':        
              tempbuff = "finfo";
              readWrite(tempbuff);
              break;
          case '5':        
              
              tempbuff = "cat";
              readWrite(tempbuff);
              break;
          case '?':        
              
               tempbuff = "?";
               readWrite(tempbuff);
               menu(); 
              
               break;
     case 'q':        
              printf(" The Program will quit \n" );
              tempbuff = "q";
              readWrite(tempbuff);
              break;
          
          default:            
              tempbuff = "none";
              break;
      }
      return 0;
    
    }
}

