import java.util.*;
import java.io.*;
import java.io.File.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.lang.ArrayIndexOutOfBoundsException;

/**
					        Oblig6 
*/

class Oblig6
{
	static int nOword;
    Scanner scan;
    static String filnavn = Sort.filnavn;
    String[] wordArray;
    int nOfThreads;
    static long startTime;
    static BuffContainer bc = new BuffContainer();

    public void read()
	{
		try 
		{
			scan = new Scanner(new File(filnavn));
			nOword  = scan.nextInt();
			wordArray = new String[nOword];
			int count=0;
			while (scan.hasNext())	
			{
				wordArray[count]=scan.next();
				count++;
			}
			System.out.println(" Number of words read are: " + count);
			
			if(wordArray.length == nOword)
			{
				assignThreadsStart(); // Her gir jeg hvert tråd noen ord for å sortere
			}
			else
			{
				System.out.println("The whole number of the first line is not equal to number of words read!");
				System.exit(0);
			}
		}
		catch(FileNotFoundException e) 
		{
		    System.out.println("You Entered wrong file; Program will now exit");
		    System.exit(0);
		}
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("The whole number in the first line of the file is not equal to number of words read!");
		}
		catch(NumberFormatException e)
		{
			System.out.println("Number Format Exception.....");
		}	
	}

	 public void assignThreadsStart() 
    {

		int threadNo = 0; 
		nOfThreads = Sort.nOfThreads; 
		int cntPerThread = nOword / nOfThreads;
		int rest = nOword % nOfThreads;  // Her har jeg rest av ord når det er remainer eg. når jeg har 7 tråder og 20 ord.
		int startIndex = (nOword / nOfThreads)*threadNo;
		int endIndex =0;
		startTime = System.currentTimeMillis(); // Her regner jeg kjøre time

		for(int i = 0; i < nOfThreads; i++) 
		{
		    endIndex =  startIndex + cntPerThread; 
		    if (rest > 0) 
		    {
		    	endIndex++;
		    	rest--;
		    }
		    new Threadclass(startIndex, endIndex, nOword, threadNo,wordArray, bc).start(); 
		    threadNo++;
		    startIndex = endIndex;

		}
    }
}
/**
					        Sort Class 
*/
class Sort
{
	static String filnavn = "";
	static String utfilnavn = "";
	static int nOfThreads = 0 ;

	public static void main(String args [])
	{
		filnavn = args[1];
		utfilnavn = args[2];
		nOfThreads = Integer.parseInt(args[0]);
		Oblig6 ss = new Oblig6();
		if(nOfThreads<=1)
		{
			System.out.println(" The number of threads should be more than one ");
		}
		else
		{
			ss.read();
		}
	}
}

/**
					        Threadclass 
*/

class Threadclass extends Thread 
{
    int startIndex; 
    int endIndex; 
    int threadNo;
    int nOword;
    String wordArray[];
    String[] wordArraySorted, wordArraySorted1;
    BuffContainer bc;

    public Threadclass(int startIndex , int endIndex, int nOword , int threadNo, String wordArray[], BuffContainer bc)
    {
    	this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.wordArray = wordArray;
		this.nOword = nOword;
		this.threadNo = threadNo;
		this.bc = bc;
    }
    
	public void run()
    {
	    sort();
    }


    /**
			Her bruker jeg insersion Sort for å sortere ordene i hver enkelkt tråd, og hver tråd sorterer sine order.
			deretter bruker jeg merge Sort for å flette ordene sammen i en final Array. Dette er invarienten min.
    */
    public void sort()
    {
    	String tmpArr;
		String [] sortArray = new String [endIndex- startIndex];
		int counter = 0;
		for (int i = startIndex; i<endIndex; i++ ) 
		{
    		sortArray[counter] = wordArray[i];
    		counter++;
    	}
    	for (int i = 0; i<sortArray.length; i++ ) 
    	{
	  		tmpArr = sortArray[i];
			for (int j = 0;j < sortArray.length;j++)
			{
			    if (i == j) 
				{    
				    int x = tmpArr.compareTo(sortArray[j]); 
				    if (x < 0) 
				    { 
				      tmpArr = sortArray[j];
				      sortArray[j] = sortArray[i];
				      sortArray[i] = tmpArr;
				    }
				}    
			}
		 }
		String[] mergeArray = bc.settInn(sortArray);
			if(mergeArray!=null)
			{
				mergeStrings(mergeArray, sortArray);
			}

    }
	public void mergeStrings(String [] wordArraySorted, String [] wordArraySorted1)
	{
	    String[] result = new String[wordArraySorted.length + wordArraySorted1.length];
	    int first = 0;
        int second = 0;
        int third = 0;
        while (first < wordArraySorted.length && second < wordArraySorted1.length) 
        {
            if (wordArraySorted[first].compareTo(wordArraySorted1[second])< 0) 
            {
                result[third] = wordArraySorted[first];
                first++;
            } 
            else 
            {
                result[third] = wordArraySorted1[second];
                second++;
            }
            third++;
        }
        		        // Her kopiere jeg resten

        System.arraycopy(wordArraySorted, first, result, third, wordArraySorted.length - first);
        System.arraycopy(wordArraySorted1, second, result, third, wordArraySorted1.length - second);
       
		// Hvis final array length er lik antall ord lest, så sortering er ferdig og skrives ut ordene ellers fortsett.
		
		if(result.length==Oblig6.nOword)
		{
			bc.skrivUtFinalArray(result);
		}
		else
		{
			String[] mergerest = bc.settInn(result);
			if(mergerest!=null)
			{
				mergeStrings(mergerest, result);
			}
		}    
	}

}

/**
					BuffContainer Class
*/

class BuffContainer
{
	String sortedArray[];

	synchronized public String[] settInn(String partialArray2[])
	{
		if(sortedArray==null)
		{
			sortedArray = partialArray2;
			return null;
		}
		else
		{
			String[] temp = sortedArray;
			sortedArray = null;
			return temp;
		}
    }

   	// Denne Metode Skrivesut det Sortert og flyttet ordene( Finale ferdig Sortert). 

   	/**
			Her kommentert jeg printing i linje 260 fordi det tar tid når jeg tester kjøretiden, du kan fjerne kommentære 
			hvis du vil se printingen. 
   	*/

	void skrivUtFinalArray(String [] ifinalresult)
	{
		for (int i = 0;i<ifinalresult.length ;i++ ) 
		{
			//System.out.println(ifinalresult[i]);
		}
		// Her regner jeg skjøre time i sortering og fletting

		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - Oblig6.startTime;
		System.out.println("\n"+"It takes: "+ totalTime/1000.0+" to sort and merge"+"\n");
			
		// Her kaller jeg Metode som skriver til fil.
		saveSolToFile(ifinalresult);
	}
	
	// Denne Metode Skrives til fil

	void saveSolToFile(String [] ifinalresult)
	{
		int x = ifinalresult.length-1;
		if(Oblig6.nOword == ifinalresult.length &&  ifinalresult[x]!= null)
		{
			String filename = Sort.utfilnavn;
		   	try
		   	{	
				PrintWriter write = new PrintWriter(new FileWriter(filename,true));  
					
				for (int i = 0;i<ifinalresult.length ;i++ ) 
	    		{
					write.println(ifinalresult[i]);
				} 
				write.close();
			} 
			catch(Exception e) 
			{
		    	System.out.println("Could not write to file");
			}
		}
		else
		{		
			System.out.println(" Finale Arrayen har forskjellige antall ord som antall ordet i øverst lest fil eller sist element i Arrayen peker på null ");

			System.exit(0);
		}
	}
}
