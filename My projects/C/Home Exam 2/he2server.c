/*
KandidatNummer: 108
EmneKode: INF1060
HjemmeEksamen
*/

// Her includerer jeg filer som jeg trenger å bruke.
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <strings.h>
#include <string.h>
#include <unistd.h>
#include <ctype.h>
#include <sys/uio.h>
#include <dirent.h> 
#include <sys/stat.h>
#include <errno.h>
#include <netdb.h>
#include <sys/time.h> 
#include <arpa/inet.h>
#define MAXCLIARR   30
#define MAXBUFF   10000

// Her deklarert jeg noen globale variabler 

int clisockarr[MAXCLIARR];
int sockd;
int  i;
unsigned int clilen;
char cwd[2000];
char *str;
char blah[2000];
char lsdirbuffer[2000];
char *readbuf;
struct sockaddr_in serv_addr, cli_addr;

// metode prototype

void pwd();
void cdTest();

// Denne metode sjekker om filen existerer

int FilExist(const char *filename) 
{
    struct stat st;
    int result = stat(filename, &st);
    return result == 0;
}

//Denne metode tar dirname som parameter og endrer directory 

char *changeCD(char *dirName)
{
   char *result;
	char cwd[200];
	char changed[200];
	getcwd(cwd, sizeof(cwd));
	char blah[200];
	snprintf(blah, 199, "%s/%s", cwd, dirName);
	int x = chdir(blah);
	if(x  == -1)
	{
	  char mog[200];
	  strcpy(mog,dirName);
          mog[strlen(mog)] = '\0';
	  result = strcat(mog, " Not Exist");
	}
	else 
	{
	  getcwd(changed, sizeof(changed));
	  result = strcat(changed, " : is the New DIR "); 
	}
	return result;
}

//Denne metode tar fn som parameter og leser fil; hvis filen existere sjekker om tegnet er printable.
//hvis er printable skrive ut ellers skrive ut . isteden for unprintable tegnet.

char *readfil(char *fn)
{
	char ch;
	char *temp; 
	int x = FilExist(fn) ;
	FILE *fp;
	char readbuffer[20000];
	readbuf = (char*) malloc(sizeof(char)*20000);
	fp = fopen(fn,"r"); 
	if( fp == NULL )
	{
		temp = "Can not open the file";
		strcpy(readbuf,temp);
	}
	if( x == 0 )
	{
		temp ="File Not Exist";
		strcpy(readbuf,temp);
	}   
	else
	{
		int j=0;
		while((ch = fgetc(fp))!= EOF )
		{
				if(isprint(ch))
				{
					readbuffer[j] = ch;
				}
				else
				{
					readbuffer[j] = '.';
				}
				j++;
		}
		readbuffer[j] = '\0';
		strcpy(readbuf,readbuffer);
		printf("\n");   
		fclose(fp);
	}	
	return readbuf; 
}

//Denne metode tar finf som parameter og forteller hvilken type er finf(om den er link,directory eller regular fil). 
 
char *lstatmethod(char *finf)
{
	struct stat statbuf;
	char *rr ="";
		char *statresult = (char*) malloc(sizeof(char)*20000);
	if (finf == NULL) 
	{
		fprintf(stderr, "usage: %s file ...\n", finf);
		exit(1);
	}
	if (lstat(finf, &statbuf)) 
	{
		rr = " No such file or Directory Exist!";
		strcat(finf,rr);	
	} 
	else 
	{
		if (S_ISDIR(statbuf.st_mode))
		{
			rr = " is a directory file";
			strcat(finf,rr);
		}
		if (S_ISREG(statbuf.st_mode))
		{
			rr = " is a plain";
			strcat(finf,rr);
		} 
		if (S_ISLNK(statbuf.st_mode))
		{
			rr = " is a symlink";
			strcat(finf,rr);
		} 
	}
	strcpy(statresult,finf);
	return statresult;
}


//Denne metode lister alle filer og mapper i nåværende directory.

void lsmethod()
{  
	char buf[1000];
	FILE *f = popen("ls *","r");
	if (f) 
	{   
		int j = 0;
		while(fgets(buf,1000,f)!=NULL)
		{
			str = buf;
			j+= snprintf(blah+j, 199, "%s", str); 
		}
		fclose(f);
	} 
	else 
	{
		printf("ERROR LS\n");
	}   
}


/*Denne metode gjør send/receive jobben som er den viktigest
  første leser den hva klienten sendt og tester valget, etter det går den riktig if block f
  for å gi resposnse til hva klieten bedt om...
*/

int doReadWrite (int sock)
{
	int rw;
	char buffer[MAXBUFF]; 
	bzero(buffer,MAXBUFF);
	
	rw = read(sock,buffer,sizeof(buffer));
	if (rw < 0) perror("ERROR READING");
	printf("Here is the message: %s\n",buffer);
	
	if(strcmp(buffer, "?") == 0)
	{
		char *s = "Menu";
		strcpy(buffer, s);
		rw = write(sock,buffer,sizeof(buffer));
		if (rw < 0) perror("ERROR WRITING");
		bzero(buffer,MAXBUFF);

	}
	else if(strcmp(buffer, "none") == 0)
	{
		 char *s = "none";
		 strcpy(buffer, s);
		 rw = write(sock,buffer,sizeof(buffer));
		 if (rw < 0) perror("ERROR WRITING");
		 bzero(buffer,MAXBUFF);
	}
	else if(strcmp(buffer, "finfo") == 0)
	{
		 char *s = "finfo";
		 strcpy(buffer, s);
		 rw = write(sock,buffer,sizeof(buffer));
		 if (rw < 0) perror("ERROR WRITING");
		 bzero(buffer,MAXBUFF);

		// sender list av filer til klient
		 lsmethod();
		 strcpy(buffer, blah);
		 rw = write(sock,buffer,sizeof(buffer));
		 if (rw < 0){ 
		 perror("ERROR WRITING");}
		 bzero(buffer,MAXBUFF);

		//leser navn til filen som klienten bedt om å vite file info.	
		 rw = read(sock,buffer,sizeof(buffer));
		 if (rw < 0) perror("ERROR READING");
		 printf("Here is the file to read: %s\n",buffer);
		 
		// her fjerner jeg "\n" fra bufferen fordi den er lest ved hjelp av fgets.
		 unsigned int f = strlen(buffer);
		 int i; 
		 for ( i = 0; i < f; i++ )
		 {
			 if ( buffer[i] == '\n' )
			 {
				buffer[i] = '\0';
				break;
			 }
		 }
		 // finner file inf og legge resultatet til tt.
		 char *tt = lstatmethod(buffer);
		 bzero(buffer,MAXBUFF);
		 //sender resultatet til klienten
		 strcpy(buffer, tt);
		 free(tt);
		 rw = write(sock,buffer,sizeof(buffer));
		 if (rw < 0) perror("ERROR WRITING");
		 bzero(buffer,MAXBUFF);

	}
	else if(strcmp(buffer, "ls") == 0)
	{
		lsmethod();
		strcpy(buffer, blah);
		rw = write(sock,buffer,sizeof(buffer));
		if (rw < 0) perror("ERROR WRITING");
		bzero(buffer,MAXBUFF);
	}
	else if(strcmp(buffer, "pwd") == 0)
	{
		pwd();
		strcpy(buffer, cwd);
		rw = write(sock,buffer,sizeof(buffer));
		if (rw < 0) perror("ERROR WRITING");
		bzero(buffer,MAXBUFF);
	}
	else if(strcmp(buffer, "cat") == 0)  // det er nesten same som finfo valg da skriver ikke jeg kommentær eller blir det repetsjon. 
	{

		char *s = "cat";
		strcpy(buffer, s);
		rw = write(sock,buffer,sizeof(buffer));
		if (rw < 0) perror("ERROR WRITING");
		bzero(buffer,MAXBUFF);


		lsmethod();
		strcpy(buffer, blah);
		rw = write(sock,buffer,sizeof(buffer));
		if (rw < 0){ 
		perror("ERROR WRITING");}
		bzero(buffer,MAXBUFF);


		rw = read(sock,buffer,sizeof(buffer));
		if (rw < 0) perror("ERROR READING");
		printf("Here is the file to read: %s\n",buffer);

		unsigned int f = strlen(buffer);
		int i; 
		for ( i = 0; i < f; i++ )
		{
		if ( buffer[i] == '\n' )
		{
		  buffer[i] = '\0';
		  break;
		 }
		}
		char *tt = readfil(buffer);
		bzero(buffer,MAXBUFF);
		strcpy(buffer, tt);
		free(tt);
		rw = write(sock,buffer,sizeof(buffer));
		if (rw < 0) perror("ERROR WRITING");
		bzero(buffer,MAXBUFF);

	}
	else if(strcmp(buffer, "cd") == 0) // denne valget har andre valget inne i seg eller er netsen samme som "cat" og "finfo" valget.
	{

		char *s = "cd";
		strcpy(buffer, s);
		rw = write(sock,buffer,sizeof(buffer));
		if (rw < 0) perror("ERROR WRITING");
		bzero(buffer,MAXBUFF);

		cdTest();
		strcpy(buffer, lsdirbuffer);
		rw = write(sock,buffer,sizeof(buffer));
		if (rw < 0) perror("ERROR WRITING");
		bzero(buffer,MAXBUFF);

		rw = read(sock,buffer,sizeof(buffer));
		if (rw < 0) perror("ERROR READING");
		printf("Here is the file to read: %s\n",buffer);

		unsigned int f = strlen(buffer);
		int i; 
		for ( i = 0; i < f; i++ )
		{
			if ( buffer[i] == '\n' )
			{
				buffer[i] = '\0';
				break;
			}
		}

		if(strcmp(buffer, "1") == 0) // valget nr 1 gjør at du hoppe ett directory nivå opp.
		{
			int x = chdir("..");
			if(x  == -1)
			{
				char *bufErr = "CD .. ERROR"; 
				strcpy(buffer,bufErr); 
				rw = write(sock,buffer,sizeof(buffer));
				if (rw < 0) perror("ERROR WRITING");
				bzero(buffer,MAXBUFF);
			}
			else
			{
				getcwd(buffer, sizeof(buffer));
				rw = write(sock,buffer,sizeof(buffer));
				if (rw < 0) perror("ERROR WRITING");
				bzero(buffer,MAXBUFF);
			}
		}


		if(strcmp(buffer, "2") == 0) // valget nr 2 gjør at du hoppe den sist directory.
		{
			int x = chdir("/");
			if(x  == -1)
			{
				char *bufErr = "CD .. ERROR"; 
				strcpy(buffer,bufErr); 
				rw = write(sock,buffer,sizeof(buffer));
				if (rw < 0) perror("ERROR WRITING");
				bzero(buffer,MAXBUFF);
			}
			else
			{
				getcwd(buffer, sizeof(buffer));
				rw = write(sock,buffer,sizeof(buffer));
				if (rw < 0) perror("ERROR WRITING");
				bzero(buffer,MAXBUFF);
			}

		}


		if(strcmp(buffer, "3") == 0) // valget nr 3 la deg å bytte den mappen du i til en annen men samme nivå directory
		{
			char *s = "3";
			strcpy(buffer, s);
			rw = write(sock,buffer,sizeof(buffer));
			if (rw < 0) perror("ERROR WRITING");
			bzero(buffer,MAXBUFF);

			cdTest();
			strcpy(buffer, lsdirbuffer);
			rw = write(sock,buffer,sizeof(buffer));
			if (rw < 0){ 
			perror("ERROR WRITING");}
			bzero(buffer,MAXBUFF);

			rw = read(sock,buffer,sizeof(buffer));
			if (rw < 0) perror("ERROR READING");

			unsigned int f = strlen(buffer);
			int i; 
			for ( i = 0; i < f; i++ )
			{
				if ( buffer[i] == '\n' )
				{
					 buffer[i] = '\0';
					 break;
				}
			}
			
			char *tt = changeCD(buffer);
			// tt holder nå pathen til den byttet til mappen. eller den nye mappen du er i.
			bzero(buffer,MAXBUFF);
			char mm[200];	
			strcat(mm,tt);
			printf("%s\n",tt );

			rw = write(sock,mm,sizeof(buffer));
			if (rw < 0) perror("ERROR WRITING");
			bzero(buffer,MAXBUFF);
			bzero(mm,200);	

		}

		if(strcmp(buffer, "q") == 0)
		{

		 char *s = "qcd";
		 strcpy(buffer, s);
		 rw = write(sock,buffer,sizeof(buffer));
		 if (rw < 0) perror("ERROR WRITING");
		 bzero(buffer,MAXBUFF);	

		}
		if(strcmp(buffer, "?") == 0)
		{
			char *s = "cd?";
			strcpy(buffer, s);
			rw = write(sock,buffer,sizeof(buffer));
			if (rw < 0) perror("ERROR WRITING");
			bzero(buffer,MAXBUFF);	
		}
		
	}  // end of cd
	else if(strcmp(buffer, "q") == 0) // avslutte klienten
	{
	char *s = "q";
	strcpy(buffer, s);
	rw = write(sock,buffer,sizeof(buffer));
	if (rw < 0) perror("ERROR WRITING");
	bzero(buffer,MAXBUFF);

	printf("THE CONNECTED CLIENT HAS QUITED\n");
	getpeername(sockd , (struct sockaddr*)&cli_addr , (socklen_t*)&clilen);
	printf("Host disconnected , IP %s , PORT %d \n" , inet_ntoa(cli_addr.sin_addr) , ntohs(cli_addr.sin_port));                 

	close(sock);
	clisockarr[i] = 0;
	return -1;

	}
	else
	{
		printf("None of the Choice! ");
		return;
	}

}





//Denne metode skriver ut Nåværende directory

void pwd()
{    
	if (getcwd(cwd, sizeof(cwd)) != NULL)
	{
	 //fprintf(stdout, "CURRENT WORKING DIRECTORY: %s\n", cwd);  
	}
	else
	 perror("GET WORKING DIR ERROR");  
}

// Her er Main metode

int main(int argc, char *argv[])
{
	// Deklarasjon av variable
   int sockser; 
	int sockcli;
	int portno; 
	int activate = 1 ;
	int activ;
	int max_sockd;
	struct timeval tout;   // jeg provt å bruke timeout for å implimentere den "Non-blocking I/O mekanismen".
	tout.tv_sec = 1;
	tout.tv_usec = 500000;

    // set av socket descriptors
    fd_set readfds;

    //brukeren må oppgi port nummer
    if (argc < 2) 
    {
        fprintf(stderr,"ERROR, PORT MISSING\n");
        exit(1);
    }
  // initialize klient socket array.
    for (i = 0; i < MAXCLIARR; i++) 
    {
        clisockarr[i] = 0;
    }
	// opprett socket
    if ((sockser = socket(AF_INET, SOCK_STREAM, 0)) == -1) 
    {
        perror("SOCKET ERROR");
        exit(1);
    }
		// her bruker setsockop for å unngå mulig Delay
    if(setsockopt(sockser, IPPROTO_TCP, TCP_NODELAY, &activate, sizeof(int))== -1)
    {
	     perror("SET SOCKET ERROR");
	     exit(1);
    }
		// her bruker setsockop for å unngå bytte port hvergang,
	if(setsockopt(sockser, SOL_SOCKET, SO_REUSEADDR,&activate, sizeof(int))== -1)
    {
	     perror("SET SOCKET ERROR");
	     exit(1);
    }
      
	// opperettet sockets
   bzero((char *) &serv_addr, sizeof(serv_addr));
	portno = atoi(argv[1]);
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = INADDR_ANY;
	serv_addr.sin_port = htons(portno);
	
   //her lagrer jeg Hostname for å skrive ut første gang serveren kjører for å bruke klienten.
	char hostname[1024];
	hostname[1023] = '\0';
	gethostname(hostname, 1023);
	printf("Hostname: %s\n", hostname);

   if ((bind(sockser, (struct sockaddr *) &serv_addr, sizeof(serv_addr))) == -1)
    { 
        perror("BINDING ERROR");
        exit(1);
    }
	printf("Listener on port %d \n", portno);  
	 // kan listner maksimalt 5
  if((listen(sockser,5)) == -1)
	{
		 perror("LISTNING ERROR");
		 exit(1);
	}
    
  clilen = sizeof(serv_addr);
  printf("WAITING CONNECTION ...... \n");
 
  
    while (1) 
    {
         
      FD_ZERO(&readfds); // gjør socket set til zero(clear it)
      FD_SET(sockser, &readfds);// legge socketen til set
      max_sockd = sockser;
	 // lokke til maxcliarr som er 30 for meg
      for ( i = 0 ; i < MAXCLIARR ; i++) 
        {
          // set socket discriptor
          sockd = clisockarr[i];
         //hvis den er valid socket dis. legge til set
          if(sockd > 0)
          {
            FD_SET( sockd , &readfds);
		
          }
            //den sjekker jeg for å bruke i selecten 
				// socketen kan ikke større enn max_sock
          if(sockd > max_sockd)
          {
            max_sockd = sockd;
          }
        }

        activ = select( max_sockd + 1 , &readfds , NULL , NULL , NULL);
        if ((activ == -1) && (errno!=EINTR))  
        {
            perror("SELECT ERROR");
		
        }

        if (FD_ISSET(sockser, &readfds)) 
        {
            if((sockcli = accept(sockser, (struct sockaddr *) &serv_addr, &clilen)) == -1)
            {
              perror("ACCEPT ERROR");
              exit(1);
            }
          
            // her skrives ut socket nummer og port.
            printf("NEW CONNECTION , SOCKET FD IS %d , IP IS : %s , PORT : %d \n" , sockcli , inet_ntoa(cli_addr.sin_addr) , ntohs(cli_addr.sin_port));
          
              
            // Legge til ny socket til klient socket array. 
            for (i = 0; i < MAXCLIARR; i++) 
            {
                
                if( clisockarr[i] == 0 )
                {
                    clisockarr[i] = sockcli;
                    printf("ADDING TO SOCKETS ARRAY  %d\n" , i);
                     
                    break;
                }
            }

            
        }

        // OPERATION ON SOME OTHER SOCKETS.........
       for (i = 0; i < MAXCLIARR; i++) 
        {
            sockd = clisockarr[i];
              
           if (FD_ISSET(sockd , &readfds)) 
            {
                
              doReadWrite(sockd);
		
            }
        }


     }

	return 0;
}


// Denne metode lister alle mapper i nåværende mappe.
void cdTest()
{

	char buf[1000];
	FILE *f = popen("ls -d */ | cut -f1 -d'/'","r");
	if (f) 
	{  int j= 0;
		while(fgets(buf,1000,f)!=NULL)
		{
			str = buf;
			j+= snprintf(lsdirbuffer+j, 199, "%s", str); 

		} 				
		fclose(f);
	} 
	else 
	{
		printf("ERROR LIST DIRECTORY\n");
	}
}


