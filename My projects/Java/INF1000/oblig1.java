class oblig1
{

public static void main (String args[])

{

int mainb = 89;    // totalt nedbør i mai
int junnb = 127;  // totalt nedbør i juni
int julnb = 19;  // totalt nedbør i juli

int sonb = mainb + junnb + julnb;   // totalt nedbør som falt i sommeren 2013.

int mair = 22; // dagene med nedbør i mai
int junr = 18; // dagene med nedbør i juni
int julr = 8; // dagene med nedbør i juli
int tdnb = mair + junr + julr;  // dagene med nedbør i sommeren 2013.

double g = sonb * 1.0 / tdnb;  // Nedbøren som gjennomsnitt falt de dagene det regnet i sommeren 2013.
double jgn = (19.0/81)*100; // jgn er gjennomsnitlig nedbør i 2013.


System.out.println();
System.out.println();
 
System.out.println("Det totalt nedbør i sommeren 2013 er " + sonb + " mm");

System.out.println();
System.out.println();


System.out.println("Nedbøren som gjennomsnitt falt de dagene det regnet i sommeren 2013 er " + g + " mm");

System.out.println();
System.out.println();

System.out.println("Prosenten av Normal nedbøren regnet i juli 2013 er " + jgn + "%");


}
}