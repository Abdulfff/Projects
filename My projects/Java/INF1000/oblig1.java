class oblig1
{

public static void main (String args[])

{

int mainb = 89;    // totalt nedb�r i mai
int junnb = 127;  // totalt nedb�r i juni
int julnb = 19;  // totalt nedb�r i juli

int sonb = mainb + junnb + julnb;   // totalt nedb�r som falt i sommeren 2013.

int mair = 22; // dagene med nedb�r i mai
int junr = 18; // dagene med nedb�r i juni
int julr = 8; // dagene med nedb�r i juli
int tdnb = mair + junr + julr;  // dagene med nedb�r i sommeren 2013.

double g = sonb * 1.0 / tdnb;  // Nedb�ren som gjennomsnitt falt de dagene det regnet i sommeren 2013.
double jgn = (19.0/81)*100; // jgn er gjennomsnitlig nedb�r i 2013.


System.out.println();
System.out.println();
 
System.out.println("Det totalt nedb�r i sommeren 2013 er " + sonb + " mm");

System.out.println();
System.out.println();


System.out.println("Nedb�ren som gjennomsnitt falt de dagene det regnet i sommeren 2013 er " + g + " mm");

System.out.println();
System.out.println();

System.out.println("Prosenten av Normal nedb�ren regnet i juli 2013 er " + jgn + "%");


}
}