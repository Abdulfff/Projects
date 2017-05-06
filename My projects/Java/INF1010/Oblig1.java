/*
Den er Oblig1 som består 3 klasser som oppgaven sier.
og jeg har jobbet sammen med  Stephen Simei som har denne bruke navn:  stephesk 

*/

class Person
{
	private String navn;
	private Person [] kjenner;
	private Person [] likerikke;
	private Person [] venn;
	private Person forelsketi;
	private int vennteller = 0;

	Person(String n, int lengde)
	{
		navn = n;
		kjenner = new Person[lengde];
		likerikke = new Person[lengde];
		venn = new Person[likerikke.length];
	}
	
	public String hentNavn()
	{
		return navn;
	}
	
	
	 public boolean erKjentMed(Person p) 
     {
 
 	   boolean test = false;
       for(int i = 0; i<kjenner.length; i++) 
       {
         if (!p.hentNavn().equals(kjenner[i].hentNavn()))
         test = true;
       }
       return test;

        
      }


	public	void	blirKjentMed ( Person p) 
	{
 		if(p != this)
 		{
 			for(int i =0; i<kjenner.length; i++)
 			{
 				if (kjenner[i] == null)
 				{
 					kjenner[i] = p;
 					break;
 				}
 			}			
 		}
	}
	
  	public	void blirForelsketI (Person	p) 
	{
		//System.out.println(forelsketi.hentNavn());
		if(!navn.equals(p.hentNavn()))
		{
        	forelsketi = p;	
    	}
	}
	
	public void blirUvennMed(Person p) 
	{
		if(p != this)
 		{
 			for(int i =0; i<likerikke.length; i++)
 			{
 				if (likerikke[i] == null)
 				{
 					likerikke[i] = p;
 					break;
 				}
 			}	
 			
 			
 		}
	}
	
	public boolean erVennMed(Person p) 
	{
		 // returnerer sann hvis Dana kjenner p og ikke er uvenner med p
		
		boolean test = false;
		 for(int i = 0; i<kjenner.length; i++)
		 {
        	if(kjenner[i].equals(p) && (!likerikke[i].equals(p)))
        	{
            	test = true;
            }
         }
                return test;
 		
	}
	
	
	public void blirvennMed(Person p) 
	{

		// samme virkning som blirKjentMed(p), men hvis Dana ikke  
		//liker p dvs. (likerikke[i] == p) for en gitt i // blir likerikke[i] satt til null.

		if(p != this)
 		{
 			for(int i =0; i<likerikke.length; i++)
 			{
 				if (likerikke[i] != p)
 				{
 					venn[vennteller] = p;
                    vennteller++;

 					likerikke[i] = null;
 					break;
 				}
 			}  
 		}
 			
 	}
 		
  	 
	

	public void skrivUtVenn() 
	{
    	        
    	// skriver ut navnet på dem Dana kjenner, unntatt dem hun ikke liker.
    	//System.out.print(navn + " er venner med: ");
    	
        for (Person p: venn) 
		{
	 		if ( p!=null) System.out.print(p.hentNavn() + "  ");
	 		
		}
            //int antalvenn = venn.length;
			//System.out.println(antalvenn);

		
    }
    
	public Person hentBestevenn ()

	{
	  // returtypen skal du bestemme 
	  // returnerer en peker til Danas bestevenn. 
  	// En persons bestevenn er for enkelhets skyld definert til å være 
  	// det objektet som pekes på av kjennerarrayens indeks 0.
  	
  	return kjenner[0];
	
	}

	public	Person[] hentVenner () 

	{
  		 // returnerer en array som peker på Danas venner 
   		// Arrayen skal være akkurat så lang at lengden er lik antallet venner, 
  		// og rekkefølgen skal være den samme som i kjenner-arrayen.
  		
  	 	Person[] hentVennerArray = new Person[kjenner.length];
     	int hentVennerTeller = 0;
    	for(int i = 0; i < kjenner.length; i++)
    	{
    		if (kjenner[i] != null && erVennMed(kjenner[i])) 
    		{
    		 	break;
        	}
          hentVennerArray[hentVennerTeller] = kjenner[i];
          hentVennerTeller++;
    	}
        return hentVennerArray; 
}


public	int antallVenner () 
{
    // returnerer hvor mange venner Dana har
    // Disse metodene trenger du ikke lage selv, men gjør det gjerne for // øvelsens skyld (kanskje de kan forbedres?): public	void	skrivUtKjenninger ()	{
	
	int antallvenner =0;
	
	for(int i = 0; i < kjenner.length; i++)
    {
		if (kjenner[i] != null && erVennMed(kjenner[i])) 
    		{
    		 	antallvenner++;
        	}
	}

	return antallvenner;
}

public void skrivUtKjenninger ()	
  	{
  
  	
		for (Person p: kjenner) 
		{
	 		if ( p!=null) System.out.print(p.hentNavn() + "  ");
		}
 	}
 	
 	public void skrivUtlikerIkke ()	
  	{
  
  	
		for (Person p: likerikke) 
		{
	 		if ( p!=null) System.out.print(p.hentNavn() + "  ");
	 		
		}
 	}

	public void skrivUtAltOmMeg() 
	{ 
		System.out.print(navn + " kjenner: "); 
		skrivUtKjenninger ();
		System.out.println("");
		
		System.out.print(navn + " er venn med: ");
		skrivUtVenn();
   		System.out.println();
   		
   		if (likerikke[0] != null)
   		{
   			System.out.print(navn + " liker ikke: " ); 
			skrivUtlikerIkke ();
			System.out.println("");
		}
		
		if	(forelsketi	!=	null)
		{
		System.out.println(navn +
		" er forelsket i " + forelsketi .hentNavn());
		}		
	}
}	// Slutt av Person klass
	
class testKlass
{
   
	Person Dana = new Person("Dana",3);
	Person Ask = new Person("Ask",3);
	Person Tom = new Person("Tom",3);
   	Person jeg = new Person("Abdul",3);		
				
	public void skrivutInfo()
	{
		
		Dana.blirKjentMed(Ask);
        Dana.blirKjentMed(Tom);
        Dana.blirKjentMed(jeg);  
        
        Ask.blirKjentMed(Dana);
        Ask.blirKjentMed(Tom);
        Ask.blirKjentMed(jeg);
        
        Tom.blirKjentMed(Dana);
        Tom.blirKjentMed(Ask);
        Tom.blirKjentMed(jeg);
                
        jeg.blirKjentMed(Dana);
        jeg.blirKjentMed(Tom);
        jeg.blirKjentMed(Ask);
        
        Dana.blirUvennMed(jeg);           
        Ask.blirUvennMed(Dana);
        Ask.blirUvennMed(Tom);
        Tom.blirUvennMed(Ask);
        Tom.blirUvennMed(jeg);
        
        Ask.blirForelsketI(jeg);
    	Dana.blirForelsketI(Tom);
    	Tom.blirForelsketI(Dana);
        
        jeg.blirvennMed(Dana);
        jeg.blirvennMed(Tom);
        jeg.blirvennMed(Ask);
        Ask.blirvennMed(jeg);
        Dana.blirvennMed(Ask);
        Dana.blirvennMed(Tom);
        Tom.blirvennMed(Dana);
        
        jeg.skrivUtAltOmMeg();
        Ask.skrivUtAltOmMeg();
        Dana.skrivUtAltOmMeg();
    	Tom.skrivUtAltOmMeg();
    	
    }
    
	
} 	// Slutt av Person klass



class Oblig1
{
	public static void main(String args[])
	{
 		testKlass tk = new testKlass(); 
 		System.out.println("");
		tk.skrivutInfo();
		System.out.println("");

		
		
    
   }

} // Slutt av Person klass
