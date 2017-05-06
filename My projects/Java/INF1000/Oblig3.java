
import easyIO.*;
class Oblig3C
     {
		public static void main(String args[] )
		{
			String [] unikWord = new String [5000];
			String [] allWord = new String [35000];
			int teller[][] =  new int [3000][3000]; 
			int [] unikcount = new int [5000];
			int totalword = 0;
			int totalUnikWords = 0;
			int ord_index1=0 ;
			int ord_index2 =0;
			
			In FT = new In("alice.txt");
			totalword = 0;
			int k = 0;
			while (!FT.endOfFile())
			{
				allWord[k] = FT.inWord().toLowerCase();
				k++;
				totalword++;
			} 
			FT.close();
			for (int i = 0; allWord[i]!=null;i++)
			 {
				boolean test = true;
				int j=0 ;
				for ( j=0; unikWord[j]!=null; j++) 
				 {
					if (unikWord[j].equals(allWord[i]))
					{
						unikcount[j]++;
						test = false;

						break;  			
					}
				 }
				if (test)
				 {
					unikWord[j] = allWord [i];
					unikcount[j]++;
					totalUnikWords++;
				 }	 
			 }
			
			for (int y=0; allWord[y]!=null; y++)
			 {
				String ord1= allWord[y];
				String ord2=allWord[y+1];
				for(int x=0;unikWord[x]!=null; x++)
				{
				if(unikWord[x].equals(ord1))
					{
						
						ord_index1 = x;
						break;
					}
					
				}
				for(int x=0;unikWord[x]!=null; x++)
				{	
					if(unikWord[x].equals(ord2))
					{
						ord_index2 = x;
						break;
					}
				}
				
				teller[ord_index1][ord_index2]++;
				
			 }
			int indeksalice=0;	
			System.out.println(" Ordene som kommer etter alice er ");
			for(int jac=0; unikWord[jac]!=null; jac++ )
			{
				if(unikWord[jac].equals("alice"))
				{
					indeksalice= jac;
					break;
				}
			}
			
			for(int i=0; unikWord[i]!=null; i++)
			 {
				if(teller[indeksalice][i]>0)
				{
					System.out.println("Alice - " +unikWord[i]);
				}

			 }
							
		}
     }
		
	 
	
	
	