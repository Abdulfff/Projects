// Programmet løkker hvis brukeren oppgir riktige stasjoner ellers Programmer avslutter og gir passende melding

import easyIO.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
class Oblig5last2
{
	public static void main(String args[])
	{
		Planlegger lf=new Planlegger();		 // Her jeg har lagt objekt av klasse Planlegg
	}
}
class Stasjon
{
	// opprett objekter av navnet på linjene men ikke de som har same navn.
	String St;
	List<Linje> linjer = new ArrayList<Linje>(); // Her lagt jeg Arraylist linjer som jeg skal legge til alle linjer
	int LinjeNo;		
	Stasjon(String St)
	{
		this.St=St;
		this.LinjeNo=LinjeNo;				
	}
		void leggTilininje(Linje lin) // denne metode legges alle linjene til Arraylist linjer
		{
			linjer.add(lin);
		}		
}


class Overgang
{
	String txt;
	double reisetid;
	Overgang(String txt, double reisetid) 
	{
		this.txt = txt;
		this.reisetid = reisetid;
	}
}
class Planlegger   // denne klassen er innholder sentrale metoder nemlig beregnRute.
{
	In FT,read;  // Her deklarert jeg FT og read for å lese file og fra brukeren henholdvis.
	Linje lin=null; // Her deklarert jeg Objekt lin av klass Linje
	Overgang overgang = null;
	Stasjon ss;  // Her deklarert jeg Objekt ss av klass Stasjon
	HashMap<String,Stasjon> hm = new HashMap<String,Stasjon>(); // Her lager jeg HashMap av klasse Stasjon som jeg skal legger til alle Stasjoner
	Planlegger()
	{
		Lesfil();
	}
	void Lesfil()   // Her leser jeg filen Trikk 
	{
		if (new File("TrikkOgTbane.txt").exists())  // Her sjekker jeg om filen finnes.
			{
				FT = new In("TrikkOgTbane.txt");					
					while(!FT.endOfFile())					
					{
						 String St = FT.readLine();						
						if(St.contains("*"))
						{
							String LN = St.substring(St.indexOf("*Linje*")+8); // Her henter jeg ut alle linjer fra file og legger til objektet lin av klasse Linje 
							int LinjeNo =Integer.parseInt(LN);
							lin = new Linje(LinjeNo);			
						}
						else
						{							
							if (hm.get(St)!=null) //dvs hvis Stasjon er funnet fra før, så kan vi jobbe med det objektet vi alinerede har
							{
								 ss = hm.get(St); //vi henter ut objektet
							}
							else 
							{
								ss = new Stasjon(St); //lager objektet ss av klasse Stasjon
								hm.put(ss.St,ss);      // Her legger jeg alle Stasjoner til HashMap
							}
								ss.leggTilininje(lin);    // Her legger jeg objekter ss og lin hverandre ved hjelp av metoder.
								lin.leggTilSt(ss);																													
						}						
					}
				LesFraTilSt();
			}
	}
	
	void LesFraTilSt() // Less fra brukeren hvilken mellnom to stasjoner skal fra og til.
	{	
		read = new In();
		int test = -1;
		while(test != 0)
		{
			System.out.println("\n"+" Velkommen til Rute planlegging .."+"\t");
			System.out.println(" --------- --- ---- -----------");
			String fraSt, tilSt;
			System.out.println(" Oppgi Stasjon fra: ");
			fraSt = read.inLine();
			fraSt=fraSt.substring(0,1).toUpperCase()+fraSt.substring(1); // Her gjør jeg først bokstav til Storbok stav.
			System.out.println(" Oppgi Stasjon Til: ");
			tilSt = read.inLine();
			tilSt=tilSt.substring(0,1).toUpperCase()+tilSt.substring(1);
			Stasjon fraStj = hm.get(fraSt);
			Stasjon tilStj = hm.get(tilSt);
			if(fraStj!=null && tilStj!=null && fraStj != tilStj) // Her sjekker jeg om fra stasjon og til stasjon ikke er null og ikke er like hvis så kalle jeg beregn metode
			{
				beregnRuter(fraStj,tilStj); 				
			}
			else if(fraStj==tilStj)
			{
				System.out.println("Du Ma opp gi forskjellige Stasjoner! Skjor programmet engang til");
				test=0;
			}
			else 
			{
				System.out.println("Fant ikke Stasjon Prov engang til!");
				test=0;
			}			
		}	
		
	}
	
	void beregnRuter(Stasjon fraStj, Stasjon tilStj)
	{		
		for (int i=0; i<fraStj.linjer.size();i++)	// Her går gjennom alle Array liste linjer og regnet ut reisetiden ved kalling Reisetid metoden fra klassen Linje.
		{		
			Linje lin=fraStj.linjer.get(i);
			if (lin.inneholder(tilStj))
			{
				String direk = lin.dir(fraStj, tilStj);
				double reisetid = lin.Reisetid(fraStj, tilStj);
				System.out.print("Ta "+lin.TRellerTB()+" Linje "+lin.LinjeNo+" fra "+fraStj.St+" til "+tilStj.St);
				System.out.print(" i retning "+direk+" Estimert "+reisetid+"\n");
				return;				
			}
		}	 
		
		for (Linje fraLnj : fraStj.linjer) 
		{			
			Iterator<Stasjon> is=fraLnj.stj.iterator(); // Her går gjennom alle Arrayliste stj som innholder alle stasjoner og skriver ut alle mulige overganger
			while (is.hasNext())
			{
				Stasjon St=is.next();
				Iterator<Linje> ms=tilStj.linjer.iterator();
				while (ms.hasNext())
				{
					Linje tilinnj=ms.next();				
					if (tilinnj.inneholder(St)) 
					{
						String direkfra = fraLnj.dir(fraStj, St);
					    String direktil = tilinnj.dir(St, tilStj);
						double reisetid = tilinnj.ventetid()+3.0+fraLnj.Reisetid(fraStj, St)+tilinnj.Reisetid(St, tilStj);
						String SiDir = "Ta "+fraLnj.TRellerTB()+ " linje "+ fraLnj.LinjeNo+ " fra "+fraStj.St+" til "+ St.St+ " i retning "+direkfra
						 +" og deretter "+tilinnj.TRellerTB()+" linje "+tilinnj.LinjeNo+" Til "+tilStj.St+" i retning "+direktil+" Estimert "+reisetid;
						System.out.println(SiDir);	
						OvergangM(SiDir,reisetid);												
					}
				}
			}
		}
		if (overgang != null)		// Her skriver ut Reise alternative med minst reisetid.
		System.out.println("\n"+"Beste Reise Alternative er ");
		System.out.println(" __________________________"+"\n");
		System.out.println(overgang.txt);			
	}
	void OvergangM(String SiDir, double reisetid)
		{
			if (overgang == null || reisetid < overgang.reisetid)
			overgang = new Overgang(SiDir, reisetid);
		}
}

class Linje
{
	int LinjeNo;
	String transporttype;
	List<Stasjon> stj = new ArrayList<Stasjon>(); // Her lagt jeg Arraylist stj som jeg skal legge til alle Stasjoner
	Linje(int LinjeNo )
	{
		this.LinjeNo=LinjeNo;
	}
	void leggTilSt(Stasjon St) // denne metode legges alle Stasjoner til Arraylist stj
	{
		stj.add(St);
	}
	double ventetid() // Denne metode retunerer ventetid for Trikk eller Tban
	{
		double tid;
		if(LinjeNo<10) tid=7.5;
	    else tid=5.0;
		return tid;
	} 
 
 String TRellerTB() // Denne metode retunerer navn på Transport er Trikk eller Tban.
 {
	if(LinjeNo<10)
	{
		transporttype="T-ban";
	}
	else
	{
		transporttype="Trikk";
	}
	return transporttype;
 }
 
	
	boolean inneholder(Stasjon St) // // Denne metode sjekker om Stasjonen finnes fra Array liste som innholder alle stasjoner.
	{
		return stj.contains(St);
	}
	String dir(Stasjon fraStj, Stasjon tilStj) // Denne metode retunerer hvilken retning skal du for å sjekke indeksen av fra stasjonen og til stasjonen. 
	{
		if (stj.indexOf(fraStj) > stj.indexOf(tilStj))
		return stj.get(0).St;
		else
		return stj.get(stj.size() - 1).St;
    }
	
	
	double Reisetid(Stasjon fraStj, Stasjon tilStj) // Denne metode retunerer Reisetid for Trikk eller Tban og jeg har brukt to metoder fra Math klasse for å få positivt tall og runder opp (hvis det er flere tall etter komma) henholdvis.
	{
	  double tid;
	  int antallinStj = Math.abs(stj.indexOf(fraStj) - stj.indexOf(tilStj));
	  if(LinjeNo<10) tid=1.8;
	  else tid=1.4;
	  return Math.round(antallinStj * tid);
	}
	
}

