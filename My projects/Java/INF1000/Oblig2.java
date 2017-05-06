

/*

Navn: Abdullahi Ibrahim Ahmed

Linje: Nettverk og Programmering  

Emnet: INF1000 

Innleveringsfrist: mandag 25. sept. 16:00

Oblig2.

*/


/*

Dette program har to classer og fire metoder som besvarer det oppgaven i oblig2

*/



import easyIO.*;

class sist

{

			public static void main(String args[])
			{

			objekt L = new objekt();
            int testcase =0;
			L.meny();
			In input = new In();
			System.out.println("Oppgi et tall mellom 1 og 4");
			int navn = input.inInt();
			testcase = navn;  
			switch (testcase)
								{
										case 1: 
										L.register();
										break;
										case 2: 
										L.skrivut();
										break;
										case 3: 
										L.skrivutsted();
										break;
										case 4: 
										break;
										default:
										System.out.println(" Gi et tall mellom 1 og 4");

							    }




			}



	  static class objekt 


		   {

			             void meny()

						    {
							  System.out.println("                              ");
							  System.out.println(" 1. Registrer en fugleobservasjon ");
							  System.out.println("                              ");
							  System.out.println(" 2. Skriv ut alle observasjoner av en fugletype ");
							  System.out.println("                              ");
							  System.out.println(" 3. Skriv ut alle observasjonene pa ett bestemt sted ");
							  System.out.println("                              ");
							  System.out.println(" 4. Avslutt systemet ");
							  System.out.println("                              ");
						    }

			             void register()

							{

								Out F = new Out("Fugler.txt",true);
								In key = new In();
								System.out.println("Oppgi fulgetype");
								String navn1 = key.readLine();
								String navn=navn1.toUpperCase();
								System.out.println("Oppgi fulgekjønn");
								String kj = key.inWord();
								System.out.println("Oppgi steder der u fante fulge");
								String std1 = key.inWord();
								String std=std1.toUpperCase();
								System.out.println("Oppgi Dato du fante fulge");
								String Dt = key.inWord();
								F.outln(navn + " "+ kj + " "+std+ " "+Dt);
								F.close();
								
							}


			             void skrivut()
			                {
								In FT = new In("Fugler.txt");
								In key = new In();
								System.out.println("Oppgi fulgetype");
								String navn = key.readLine();
								String name=navn.toUpperCase();
								System.out.println("       "+navn+"       ");
								System.out.println("  ******************  ");
								System.out.println("                      ");
								while(!FT.endOfFile())
			                               {
												String nam = FT.readLine();
												if(nam.contains(name))
												      {
												        System.out.println( nam );
												       }
											}
			                }


			             void skrivutsted()
						    
							 {

									In fil = new In("Fugler.txt");
									In key = new In();
									System.out.println("Oppgi Sted");
									String navn1 = key.readLine();
									String navn=navn1.toUpperCase();
									System.out.println("       "+navn+"       ");
									System.out.println("  ******************  ");
									System.out.println("                      ");
									while(!fil.endOfFile())
									{
											String nam = fil.readLine();
											if(nam.contains(navn))
											{
											  System.out.println( nam );
											}
									}
			                 }



			}



}