import easyIO.*;
import java.io.*;

/*
I denne Obligen har jeg brukt tre klasser, Oblig4,Hybel Og hybelhus. 
Og jeg skal prøve å kommentere kodene som jeg synes de er litt u klart!
*/
class Oblig4
 {
	public static void main(String args[])
    {
		if (args.length < 1) 
		{
			System.out.println("Bruk: > java Oblig4 hybeldata.txt");
		} 
		else 
		{ 
			Hybelhus s = new Hybelhus();    
			s.ordrelokke();
		}
    }
}
class Hybel
 {
	Student leietaker;
	String snavn, hv, hybelnv;
	int saldo;
    boolean occupied = false;
	char room, rom;
	int etasje, et;
 }
 class Hybelhus
	{
		Hybel[][] hyblene = new Hybel[3][6]; 
		int manedsleieVanligHybel; 
		int manedsleieToppEtasjeHybel; 
		int Totalfortjent;
		int husleie;
		int utkastgebyr=3000;
		int maanfortjent; 
		int gnmf;
		int Totalmaan;
		boolean occupied;
		int aar;
		int maan;
		int antalmaan;
		int et, etasje, romm, rom;
		String snavn, hybelname;
		String tom = "TOM HYBEL";
		char room;
		int utkastskrav;
		int deposit=15000;
		int maautgifter;
		boolean fantsnavn=false;
		int ML;
		In read = new In();  // Her jeg har lager read og FT for å lese og skrive fra\til file.
		Out FT = new Out();
		
			Hybelhus()
		{
			for (int i = 0; i < 3; i++) 
			{
				for (int j = 0; j < 6; j++) 
				{
					hyblene[i][j] = new Hybel();  //Her Oppreter jeg pekere til alle hyblene.
				}
			}
			if (new File("hybeldata.txt").exists())  // Her sjekker jeg om filen finnes.
			{
				In FT = new In("hybeldata.txt");
				maan= FT.inInt(";");				// Her leser jeg først linje av filen
				aar= FT.inInt(";");
				Totalfortjent= FT.inInt(";");  
				antalmaan= FT.inInt(";");
				manedsleieVanligHybel=FT.inInt(";");
				manedsleieToppEtasjeHybel=FT.inInt(";");
				for (int i = 0; i < 3; i++) 
				{
					for (int j = 0; j < 6; j++)   // Her leser jeg resten av filen med for løkker
					{
						String ledig = "( Ledig ) ";
						String tom = "TOM HYBEL";
						int et = FT.inInt(";");
						char rom = FT.inChar(";");
						int romm = (int) rom;
						romm=romm - 'A';
						int saldo = FT.inInt(";");
						String snavn=FT.inWord(";");
						
						String hybelnv = hyblene[i][j].et+""+hyblene[i][j].rom;
						hyblene[i][j].et = et;
						hyblene[i][j].room = rom;
						hyblene[i][j].snavn= snavn;
						hyblene[i][j].occupied = true;   // Her registrerer jeg verdiene
						hyblene[i][j].saldo = saldo;
						hyblene[i][j].hv = hybelnv;
					}
				}
				FT.close();
			}
				
		}
		void meny()
		{

			System.out.println("\n"+" 1. Skriv oversikt "+"\n");

			System.out.println(" 2. Registrer ny leietager "+"\n");

			System.out.println(" 3. Registrer betaling fra leietager "+"\n");

			System.out.println(" 4. Registrer frivillig utflytting "+"\n");
			
			System.out.println(" 5. Manedskjoring av husleie "+"\n");

			System.out.println(" 6. Kast ut leietakere "+"\n");

			System.out.println(" 7. Ok husleien "+"\n");

			System.out.println(" 8. Avslutt  "+"\n");

		}
		void ordrelokke()
		{
			int test = -1;
			while(test != 0)
			{
				meny();
				In input = new In();
				System.out.println("Oppgi et tall mellom 1 og 8");
				int navn = input.inInt();
				test = navn;  
				switch (test)
				{
					case 1: 

					Skriveoversikt();

					break;

					case 2: 

					RegInn(); 

					break;

					case 3: 

					Regnybetaling();

					break;

					case 4: 

					RegfrivilUtflyting(); 

					break;

					case 5: 

					Manedskjoring();

					break;

					case 6: 

					Kastut(); 

					break;

					case 7: 

					OkeHusleie(); 

					break;

					case 8: 

					avslut();  
					
					test=0;

					break;

					default:
					 
					System.out.println(" Tast lovlig ordne mellom 1 og 8"+"\n");


				}
			}
		}
		void RegfrivilUtflyting()   // Register frivillig utflytting Metode
		{
			System.out.println(" Skriv Student Navn: ");
			String stnavn = read.inLine();
			for (int i = 0; i < 3; i++) 
			{
				for (int j = 0; j < 6; j++) 
				{
					if (hyblene[i][j].snavn.equalsIgnoreCase(stnavn))
					{
						fantsnavn=true;
						if(hyblene[i][j].saldo > 0)
						{
							System.out.println(" Du skal fa refundert belop av " +hyblene[i][j].saldo);
							hyblene[i][j].saldo=0;
							hyblene[i][j].snavn=tom;
						}
						else
						
						System.out.println(" Din saldo er negativt og er: "+hyblene[i][j].saldo);
					
					}
					
				 
				}
				
			}
			if(fantsnavn==false)
			{
				System.out.println("fant ikke leietakere!");
			}
			
		}
		void Regnybetaling()   // Register betaling Metode
		{
			System.out.println("Skrive Etasje");
			int etj = read.inInt();
			etj -= 1;
			System.out.println(" Skriv Rom : ");
			read.skipWhite();
			char romletter = read.inChar();
			romletter= Character.toUpperCase(romletter); // Her konverterer jeg romletter to Stor bokstav
			int romno =  (romletter - 'A'); 
			
			if (etj < 0 || etj > 3 || romno < 0 || romno > 6) 
			{ 
				System.out.println("Denne Hybel eksisterer ikke"+ "\n");
			}
			else if (hyblene[etj][romno].snavn.equals(tom)) 
			{ 
				System.out.println(" Det er ingen som bor her"+ "\n");
			}
			else  
			{   
			   System.out.println(" Hvor mye skal sette inn nå " + " "); 
			   int  paid  = read.inInt();
			   hyblene[etj][romno].saldo = hyblene[etj][romno].saldo + paid;
			   System.out.println(hyblene[etj][romno].snavn + "\t"+"Din saldo er: " + hyblene[etj][romno].saldo);
			}
		}
		
		void Kastut()        // Kastut Metode
		{
			for (int i = 0; i < 3; i++) 
			{
				for (int j = 0; j < 6; j++) 
				{
					if(hyblene[i][j].saldo < ((-1)* manedsleieVanligHybel))  // Her sjekker jeg om studentenes saldo er mindre enn passende Husleie satsen.
					{
						utkastskrav =(hyblene[i][j].saldo*(-1))+(utkastgebyr/2);
						Totalfortjent+=utkastskrav;
						hyblene[i][j].occupied = false;
						etasje = hyblene[i][j].et ;
						room = hyblene[i][j].rom;
						hyblene[i][j].saldo = 0;
						snavn=hyblene[i][j].snavn;
						hyblene[i][j].snavn= "TOM HYBEL";
						tilkallHale(i,j,utkastskrav,snavn);   // Her kaller jeg tilkallHale metode
					}
						
				}
			}
			
		} 
		
		void tilkallHale(int et, int room, int utkastskrav, String snavn) 
		{
			System.out.print(hyblene[et][room].et + " " + hyblene[et][room].room + "\t");
			System.out.print(snavn+"\t");
			System.out.println(utkastskrav);
			FT = new Out("Torpedo.txt", true); 
			FT.outln(hyblene[et][room].et+";"+hyblene[et][room].room + ";" + snavn + ";" + utkastskrav);
			FT.close();
		}
	
		void OkeHusleie()  // ØK Husleie Metode
		{
			
			System.out.println("Husleiesatsen for 1 og 2 etasje er: " +  manedsleieVanligHybel+"for ToppEtasje er: "+manedsleieToppEtasjeHybel);
			System.out.println("Oppgi Nyhusleie satsen for 1 og 2 etasje");
			int nyManedshusleie = read.inInt();
			manedsleieVanligHybel = nyManedshusleie;
			System.out.println("Oppgi Nyhusleie satsen for 3 etasje ");
			int nyManedsleieTopp = read.inInt();
			manedsleieToppEtasjeHybel = nyManedsleieTopp;
			System.out.println("Ny Huslei for 1 og 2 etasje er: " + manedsleieVanligHybel + " og for 3 etasje er: " + manedsleieToppEtasjeHybel);
		}
		
		void Manedskjoring()  // Månedsskjøring Metode
		{
			if(maan==12)   // Her sjekker jeg om det er desember for øke året med en.
			{
				maan=1;
				aar=aar+1;
				System.out.println(" Onsker du a utfore manedskjoring for " + maan + "/"+aar+"(j/n)?");
				System.out.println(" \n");
				maan=maan-1;
			}
			else
			{
				System.out.println(" Onsker du a utfore manedskjoring for " + (maan+ 1)+"/"+ aar +"(j/n)?");
				System.out.println(" \n");
			}
			String svar = read.inWord();
			
			if(svar.equals("j"))
			{
				System.out.println(" Manedskjoring Starter na ... ");
				maanfortjent=0;
				maan=maan+1;
				antalmaan=antalmaan+1;
				
				for (int i = 0; i < 3; i++) 
				{
					for (int j = 0; j < 6; j++) 
					{
					
						if (!hyblene[i][j].snavn.equals(tom)&& hyblene[i][j].et == 1) // Her test jeg om studenten bor på først etasje
						{      
							if(hyblene[i][j].saldo > manedsleieVanligHybel)
						    {
							   hyblene[i][j].saldo -= manedsleieVanligHybel; 
							   maanfortjent+=manedsleieVanligHybel;
							  
						    }
							
						    else
							{
							   maanfortjent+=hyblene[i][j].saldo;
							   hyblene[i][j].saldo -= manedsleieVanligHybel; 
							}
							maanfortjent-=8900;
							
						} 
						else if (!hyblene[i][j].snavn.equals(tom)&& hyblene[i][j].et == 2) // Her test jeg om studenten bor på andre etasje
						{      
							if(hyblene[i][j].saldo > manedsleieVanligHybel)
						    {
							   hyblene[i][j].saldo -= manedsleieVanligHybel; 
							   maanfortjent+=manedsleieVanligHybel;
							  
						    }
							
						    else
							{
							   maanfortjent+=hyblene[i][j].saldo;
							   hyblene[i][j].saldo -= manedsleieVanligHybel; 
							}
							maanfortjent-=8900;
							
						}
						else if (!hyblene[i][j].snavn.equals(tom)&& hyblene[i][j].et == 3) // Her test jeg om studenten bor på tredje etasje
						{
							if(hyblene[i][j].saldo > manedsleieToppEtasjeHybel)
							{
								hyblene[i][j].saldo -= manedsleieToppEtasjeHybel; 
								maanfortjent+=manedsleieToppEtasjeHybel;
							}
								
							else
							{
								maanfortjent+=hyblene[i][j].saldo;
								hyblene[i][j].saldo -= manedsleieToppEtasjeHybel;
							}
							maanfortjent-=8900;
						}
						else 
						{
							Totalfortjent-=26700;
						
						}			
					}
				
				}
				Totalfortjent+=maanfortjent;
				gnmf=Totalfortjent/antalmaan;
				System.out.println(" Maned: " + maan+ "."+aar+"."+"\n");
				System.out.println(" Maneder i drift er: " + antalmaan+"\n");
				System.out.println(" Husleiesatsene for 1 og 2 etasje er: " +manedsleieVanligHybel+"\n");
				System.out.println(" Husleiesatsen for 3 er: " +manedsleieToppEtasjeHybel+"\n");
				System.out.println(" Manedsfortjeneste er: " + maanfortjent+"\n");
				System.out.println(" Total fortjeneste er: " + Totalfortjent+"\n");
				System.out.println(" Gjennomsnittilig Manedsfortjeneste er: " + gnmf+"\n");
				
			}			
			else
			{
				System.out.println(" Kansje du skal kjore nest gangt! ");
			}	
			
			
		}
		void avslut()   // Avslutt Metode i denne metoden skriver jeg alle transaksjonene til file.
		{
			System.out.println(" Systemet avslutet og filen oppdatert... ");
			Out FT = new Out("hybeldata.txt");
			FT.outln(maan+ ";" + aar + ";"+ Totalfortjent+";"+  antalmaan + ";"+manedsleieVanligHybel +";"+ manedsleieToppEtasjeHybel+";");
			for (int i = 0; i <3 ; i++) 
			{
				for (int j = 0; j < 6; j++) 
				{
					if(hyblene[i][j].occupied == true)
					{		
						FT.out(i + 1 +";" + hyblene[i][j].room + ";");
						FT.out(hyblene[i][j].saldo + ";");
						FT.outln(hyblene[i][j].snavn);
					}
					else 
					{
						FT.outln(i + ";" + j  + hyblene[i][j].saldo + ";"+ ";TOM HYBEL");
					}
				}
			}
			FT.close();
		}
		void Skriveoversikt()   // Skriveoversikt metode 
		{
			for (int i = 0; i < 3; i++) 
				{
					for (int j = 0; j < 6; j++) 
					{
						String ledig = "( Ledig ) ";
						String tom = "TOM HYBEL";
						if(hyblene[i][j].snavn.equals(tom))
						{
							System.out.print(i+1);
							System.out.println(hyblene[i][j].room + "\t"+ledig+"\t"+hyblene[i][j].saldo);
							occupied=false;
						}
						else
						{
							System.out.print(i+1);
							System.out.println(hyblene[i][j].room + "\t"+hyblene[i][j].snavn+"\t"+hyblene[i][j].saldo);
							occupied=true;
						}
					}
				}
			System.out.println("\n"+"Maned:"+maan+"."+aar +"."+"\t"+"Manedere i drift er: "+antalmaan+"\t"+"Total fortjenester er: "+Totalfortjent);
		}
	
		void RegInn()  // Registrering ny student
		{
						
			occupied = false;
			for(int i=0; i<3; i++)
				{
					for(int j=0; j<6;j++)
					{
						String tom = "TOM HYBEL";
						if(hyblene[i][j].snavn.equals(tom))
						{
							occupied = true;
						}
					}
				}
			if(occupied = false)
			{
				System.out.println("Det er ikke ledig Hybel!");
			}
			
			else
			{
				System.out.println("Det er ledig Hybler");
				for(int i=0; i<3; i++)
				{
					for(int j=0; j<6;j++)
					{
						if(hyblene[i][j].snavn.equals("TOM HYBEL"))
						{
							System.out.print(i+1);
							System.out.println(hyblene[i][j].room);
						}
					}
				}
			}
			
			
			
            System.out.println("Skrive Etasje");
			int etj = read.inInt();
			etj -= 1;
			System.out.println(" Skriv Rom i stor bokstav: ");
			read.skipWhite();
			char romletter = read.inChar();
			romletter= Character.toUpperCase(romletter);
			int romno =  (romletter - 'A'); 
			System.out.println("Opp gi navn");
			String snavn = read.inWord();
			System.out.println(snavn);
			int saldo = 0;
			int rent = Husleisats(rom);
			if (rom > 2) saldo = (deposit - rent);
	        	else saldo = (deposit - rent);
	        		System.out.println("Du har leide "+(etj+1) + romletter+  " og Din saldo " + saldo + " Din Navn er "  + snavn);
					hyblene[etj][romno].saldo= saldo;											
					hyblene[etj][romno].snavn= snavn;
					System.out.print(etj+1);
					System.out.print(hyblene[etj][romno].room);
						
		}
		
	
		int Husleisats(int rom)  // den lille metoden bestemmer Husleie satsene. er etasje 1,2 eller 3.
		{
		    if(rom > 2)
			{
				ML = manedsleieToppEtasjeHybel;
			}
			else
			{
				ML = manedsleieVanligHybel;
			}
		    return ML;
		}
	
			
	}	
	