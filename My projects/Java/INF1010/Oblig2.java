/*

 Jeg har jobbet sammen med  Stephen Simei Kimogol som har denne bruke navn:  stephesk 

*/

class Oblig2
{
	public static void main (String args [])
	{
		testKlass tk = new testKlass();
        tk.skrivutInfo();      
	
	}

}


class Book 
{
  private String author;
  private String title;
  private int arstall;
  
  Book (String author, String title, int arstall) 
  {
    this.author = author;
    this.title = title;
    this.arstall = arstall;
  }
  
  public int hentArstall() 
  {
	return arstall;          
  }
}
  
  class Plate 
  {
  	private String artist;
  	private String title;
  	private int antalSpor;
  
  	Plate (String artist, String title, int antalSpor) 
  	{
    	this.artist = artist;
    	this.title = title;
    	this.antalSpor = antalSpor;
  	}
  	public String hentNavn() 
  	{
		return artist;          
  	}

  }
  
  //  Class person fra forrige Oblig
  	
	class Person
{
	// new Oblig2
	private Book [] Books;
	private Plate [] Plates;
	private int arstall;
	private String artist;
	
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
	
	
	public void samlerAv(String smlp, int ant)
	{
		if (smlp.equals("Books"))
		{
			Books = new Book[ant];
			
		}
		
		else if (smlp.equals("Plates"))
		{
			Plates = new Plate[ant];
		}
		else  System.out.println(" SMLP peker noe annet ! ");
	}
	
	public void megetInteressertI(String artist)
	{
		
			this.artist = artist;
			
	}
	
	
	public void megetInteressertI(int eldreEnn)
	{
		this.arstall = eldreEnn;
	}
		
	
	public Plate vilDuHaGaven(Plate p)  // Plate versjon
	{
    	if(Plates == null)
    	{
    		return p;
    	}
        else if(Plates[Plates.length/2]==null)        
        {  
         	for(int i = 0; i < Plates.length; i++) 
            {
            	if(Plates[i] == null)
            	{
                 Plates[i] = p;
                 return null;
                }	
            }
        }
      	
      	else if(Plates[Plates.length/2]!=null && artist.equals(p.hentNavn()))        
      	{       		
        		for(int i = 0; i < Plates.length; i++) 
        		{
        			if(Plates[i]!= null)
            		{
                 		Plates[i] = p;
                 		return null;
                	}
        		}
        	
      	}
      	 else if(Plates[Plates.length -1]!=null)      

      	{      
			if(artist != null && artist.equals(p.hentNavn()))        
			{
        		for(int i = 0; i < Plates.length; i++) 
        		{
        			if(!artist.equals(p.hentNavn()))
					{
						Plate ret = Plates[i];
         				Plates[i] = p;
            			return ret;
					}
        		}
        	}
      	}
      	     	else 
     	{

     	}
     	return p;
	}
	
	public Book vilDuHaGaven(Book b)  // Book versjon
	{
    	if(Books == null)
    	{
    		return b;
    	}
        else if(Books[Books.length/2]==null)        
        {  
         	for(int i = 0; i < Books.length; i++) 
            {
            	if(Books[i] == null)
            	{
                 Books[i] = b;
                 return null;
                }	
            }
        }
      	
      	//else if(Books[Books.length/2]!= null && arstall == b.hentArstall())  
      	      	else if(this.arstall > this.Books.length/2  && arstall == b.hentArstall())        

      	{       		
        		for(int i = 0; i < Books.length; i++) 
        		{
        			if(Books[i]!= null)
            		{
                 		Books[i] = b;
                 		return null;
                	}
        		}
        	
      	}
      	 else if(Books[Books.length -1]!=null)      

      	{       //System.out.println("The loop");
			if(this.arstall > 0  && this.arstall == b.hentArstall())        
			{
        		for(int i = 0; i < Books.length; i++) 
        		{
        			if(this.arstall > b.hentArstall())
					{
						Book ret = Books[i];
         				Books[i] = b;
            			return ret;
					}
        		}
        	}
      	}
      	     	else 
     	{

     	}
     	return b;
	}
	
	
	
	
	public void skrivUtAltOmMeg()
	{
	 
	 	if (Plates!=null)
		for (Plate p: Plates)
	   {
	   	 	if (p!=null)
	   	 	System.out.println(p.hentNavn() + "  ");

	   }
	   
	   
	 if (Books!=null)
		for (Book b: Books)
	   {
	   	 	if (b!=null)
	   	 	System.out.println(b.hentArstall() + "  ");

	   }
	   
	}	
	
	// her er førige oblig
	
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
		if(p!= this)
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

	
}	// Slutt av Person klass
	
	
	class testKlass
{
      
	public void skrivutInfo()
	{	
	Person [] personArray = new Person[7];
	
	Person Dana = new Person("Dana",5);          //plater artist Bob Dylan
	Person Ask = new Person("Ask",3);	        //bare bøker	
	Person Tom = new Person("Tom",3);          //bare plater
   	Person Abdul = new Person("Abdul",3);     //bok og pl(Queen og før 1946)
   	Person Xavi = new Person("Xavi",3);      //bok og pl(Silya Nymoen)
	Person Alan = new Person("Alan",3);     //bok og pl(særlig boker før 1900)
   	Person Pires = new Person("Pires",3);  //samler ingenting
	  
    personArray [0]= Dana;
   	personArray [1]= Ask;
    personArray [2]= Tom;
    personArray [3]= Abdul;
    personArray [4]= Xavi;
    personArray [5]= Alan;
    personArray [6]= Pires;
  
	Book b1 = new Book ("Douglas Adams", "Haikerens Guide til galaksen",1880);
    Book b2 = new Book ("Arne Maus", "Rett paa java", 1930);
    Book b3 = new Book ("Aschehoug", "Store norske leksikon",2010);
    Book b4 = new Book ("Hiedi ", "Fint Vaer", 1987);
    Book b5 = new Book ("Tom", "Family",2010);
        
    Plate p1 = new Plate ("Queen", "Queen",1);
    Plate p2 = new Plate ("Silya", "Nymoen",1);
    Plate p3 = new Plate ("Bob", "Dylen",1);
    Plate p4 = new Plate ("Steph","Ja Vi Elsker",1);
    Plate p5 = new Plate ("M J", "Back Home",1);
	Plate p6 = new Plate ("J J J", "B B B ",1);

    
    Dana.samlerAv("Plates",5);
   	Dana.megetInteressertI(p5.hentNavn());
    Dana.vilDuHaGaven(p1);
    Dana.vilDuHaGaven(p2);
	Dana.vilDuHaGaven(p3);
	
	
    Ask.samlerAv("Books",5);
	Ask.megetInteressertI(b3.hentArstall());
	Ask.vilDuHaGaven(b1);
	Ask.vilDuHaGaven(b2);
	Ask.vilDuHaGaven(b3);
	Ask.vilDuHaGaven(b4);
    Ask.vilDuHaGaven(b5);
    
	

    Dana.skrivUtAltOmMeg();
    Ask.skrivUtAltOmMeg();

   
     
    }
    
	
} 	// Slutt av Person klass
	