/**
	Programmet Gjør at hvis du gi to args parameter, den første tar som filen som leses fra og den andre som skrives til.
	Hvis du gir en parameter den tar som den filen som leses fra, og tredje tilfeller er hvis du gir ikke parameter den loader
	JFileChoser for la deg velge filen som skulle leses fra. 
*/



import java.util.*;
import java.util.ArrayList;
import java.awt.BorderLayout;  
import javax.swing.border.Border;  
import javax.swing.border.LineBorder;  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.io.File.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.lang.NumberFormatException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
						CLASS OBLIG 5
*/
class Oblig5
{
	static String  writeTo = "";
	static String  readFrom = "";
	static boolean checkArgs;

	public static void main (String args [])
	{	
		if(args.length == 2) 
		{
			JOptionPane.showMessageDialog(null, "The Solutions will be written to " + args[1]);
			readFrom = args[0];
			writeTo=args[1];
			checkArgs = true;
	    	new Board(readFrom);
		}
		else if (args.length == 1)
		{
			JOptionPane.showMessageDialog(null, "The File will read from" + args[0]);
			readFrom = args[0];
			new Board(readFrom);
		}
		else
		{ 
			FileChooserClass fcc = new FileChooserClass();
	    	fcc.setVisible(true);
	    }
	    
	}
}

/**
       CLASS BEHOLDEREN
*/

class SudokuBeholder
{
	private ArrayList<int[][]>solutions;
	static	int antLosninger;
	int[][] tempSolution;
	int rows;
	int columns;
	SudokuBeholder(int rows, int columns)
	{	
		this.rows = rows;
		this.columns = columns;
		solutions = new ArrayList<int[][]>();
		antLosninger = 0;	
	}
			// Setter lØsninger til beholderen
	public void insert(Square [][] squareSol) 
	{
		if(solutions.size() < 750)
		{	
			tempSolution = new int[rows*columns][rows*columns];
			antLosninger++;
			System.out.print(antLosninger+ ": ");
			for(int i = 0; i < squareSol.length; i++) 
			{		
	    		for(int j = 0; j < squareSol[0].length; j++)
	    		{
					tempSolution[i][j] = squareSol[i][j].sqNo;
					System.out.print(squareSol[i][j].sqNo);
	    		} 
	    		System.out.print("//");
			}	System.out.println();

			solutions.add(tempSolution);

		}
		else
		{
			antLosninger++;
		}			
   		saveSolToFile(); // Her skriver jeg til fil
 	}
 	
	
	public int[][] get(int i) 
	{
		return solutions.get(i);
	}

	public static int getSolutionCount() 
	{
		return antLosninger;
	}
	

	// Metoden som skriver til fil
	int nOfSolution = 0;
	void saveSolToFile()
	{
		if(Oblig5.checkArgs)
		{
			String filename = Oblig5.writeTo;
		   	try
		   	{	

	   			String stt= Oblig5.writeTo;
				PrintWriter write = new PrintWriter(new FileWriter(filename,true));  
				if(solutions.size()<750) 
				{
					nOfSolution++;
					write.print(nOfSolution+ ": ");
					for(int i = 0; i < tempSolution.length; i++) 
					{		
			    		for(int j = 0; j < tempSolution[0].length; j++) 
			    		{
							write.print(tempSolution[i][j]);
						} 
						write.print("//");
					} 
				} 
				else 
				{
					nOfSolution++;
				}
					write.println();
					write.close();
			} 
			catch(Exception e) 
			{
		    	System.out.println("Could not write to file");
		    	System.out.println("Exception e: " + e.getMessage());
		    	e.printStackTrace();
			}	
		}
	}
	
}

/**
				CLASS SQUARE
*/

class Square
{	
	int dim ; 
	int number;
	Column cc;
	Box bx;
	Row rw;
	SudokuBeholder sb;
	Square squareArray[][];
	Square next;
	int sqNo;
	Square (int number, int dim, Row rw, Column cc, Box bx, SudokuBeholder sb)
	{
		this.sqNo = number;
		this.dim=dim;
		this.rw = rw; 
		this.cc = cc; 
		this.bx = bx; 
		this.sb = sb;
	}
	
	void fillInnRemainingOfBoard(){}
}

/**
			CLASS PreFilledValues
*/

class PreFilledValues extends Square 
{
	PreFilledValues(int number, int dim, Row rw, Column cc, Box bx, SudokuBeholder sb)
	{
		super(number, dim, rw, cc, bx, sb);
	}
	void fillInnRemainingOfBoard()
	{		
		if (next != null)
		{
			next.fillInnRemainingOfBoard();
		}
		else 
		{
			sb.insert(Board.squareArray);
		}		
	} 	
}

/**
			CLASS PossibleValues
*/

class PossibleValues extends Square 
{
	PossibleValues(int number, int dim, Row rw, Column cc, Box bx, SudokuBeholder sb)
	{
		super(number, dim, rw, cc, bx, sb);
	}
	void fillInnRemainingOfBoard()
	{	
		for (int i = 1; i<=dim; i++)
		{	
			if(rw.isLegal(i) && cc.isLegal(i) && bx.isLegal(i)) 
	 		{
	 			sqNo = i;	
	 			if (next != null)
	 			{
	 				next.fillInnRemainingOfBoard();
	 			}
	 			else 
	 			{
	 				sb.insert(Board.squareArray); // funnet løsningene og setter inn i beholderen.
	 				break;
	 			}
	 		} 
		} 	
		sqNo=0;
	}

}

/**
			CLASS BOARD
*/
class Board
{

	Scanner scan;
	static int rows, columns, box;
	int[][] board;
	String filnavn;
	int count;
	int dim;
	Row rw;
	Box bx;
	Column cc;
	SudokuBeholder sb;	
	static Square [][] squareArray;
	Row []rowArray;
	Column [] columnArray;
	Box [] boxArray;
	int rowreg, boxreg, columreg;
	Board(int rows, int columns, int [][] board)
	{	
		this.board = board;
		this.rows = rows;
		this.columns = columns;
		dim = (rows * columns);	
	}
	Board(String filnavn)
	{
		this.filnavn = filnavn;
		try 
		{
			scan = new Scanner( new File(filnavn));
            rows = scan.nextInt();
			columns = scan.nextInt();
			scan.nextLine();
			count =0;
			board = new int[rows*columns][rows*columns];
			dim = rows*columns;
			while (scan.hasNext())
			{
				char[] linje = scan.nextLine().toCharArray();
				for(int i=0; i<rows*columns;i++)
				{
					if(linje[i]!= '.')
					{
							if(Character.isDigit(linje[i]))	
							{
								board[count][i]=Integer.parseInt(""+linje[i]);
							}
							else
							{
								board[count][i]= linje[i]-55;
							}
					}
				}	
				count++;
			} 
			if(count<rows*columns)
			{
				throw new FeilMedBrettUnntakk(" Det er mangeler et et tall");
			}
		} 
		catch (FileNotFoundException e)
		{
		}
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("Det er mangeler et et tall");
		}
		catch(FeilMedBrettUnntakk e)
		{
		}
		for(int i=0; i<rows*columns;i++)
		{
			for(int j=0; j<rows*columns;j++)
			{
				if(board[i][j]== 0)
				{
					System.out.print(".");
				}
				else
				{
					System.out.print(board[i][j]);
				}
			}
			System.out.println();
		}
		sb = new SudokuBeholder(rows, columns); // Her oppretter jeg Sudoku Beholderen.

		/**
		                Sett Array Størrelser
		*/
		rowArray = new Row[rows*columns];
		columnArray = new Column[rows*columns];
		boxArray = new Box[rows*columns];
		squareArray = new Square[rows*columns][rows*columns];

		/**
		                Opprett Arrayer
		*/

		for (int i = 0; i< rows*columns; i++)
		{
			rowArray[i] = new Row (rows*columns);
			columnArray[i] = new Column(rows*columns);
			boxArray[i] = new Box(rows*columns);	
		}
		
		/**
			            Opprett square 
			*/
		for (int i = 0; i< rows*columns; i++)
		{
			for (int j = 0; j< rows*columns; j++)
			{
				boxreg = (i/rows) + columns*(j/columns);     
				columreg = (i%(rows*columns));
				rowreg = (i/rows*columns);
				
				rw = rowArray[i];
				cc = columnArray[j];
				bx = boxArray[boxreg];

				int number = board[i][j];
				if (number == 0) 
				{
					squareArray[i][j] = new PossibleValues(number, dim, rw, cc, bx, sb);
				} 
				else 
				{
					squareArray[i][j] = new PreFilledValues(number, dim, rw, cc, bx, sb);
				}
				
				rowArray[i].settInnRute(squareArray[i][j]);
				columnArray[j].settInnRute(squareArray[i][j]);
				boxArray[boxreg].settInnRute(squareArray[i][j]);
			}
		}

			/**
					Lager nest peker
			*/
		for (int i = 0; i< rows*columns; i++)
		{
			for (int j = 0; j< rows*columns; j++)
			{	
				if (j != (columns*rows-1)) 
				{
					squareArray[i][j].next = squareArray[i][j+1];
				}
				else if (i!= (columns*rows-1))
				{	
					squareArray[i][j].next = squareArray[i+1][0];
				}
				else
				{
					squareArray[i][j].next =null;	
			    }
				
			}
		}
		squareArray[0][0].fillInnRemainingOfBoard(); // Her kaller jeg metoden for å fille inn bretten
			
		printNext(); // Her lager jeg Gui etter løsningene har funnet. 		
	
	} // Slutt av Board konstructør

	// Denne Metode lager Gui med første løsningen og andre løsningene vise når trykkes NextSolution knappen.
	void printNext()
	{
		@SuppressWarnings("unused")  
		Gui JPanel = new Gui(sb.get(0), rows, columns,sb); 
	}
	
}  // End of class Board.


class FeilMedBrettUnntakk extends Exception
{
	FeilMedBrettUnntakk(String melding)
	{
		System.out.println(melding);
		System.exit(1);
	}		
}

			/**
			Common Super Class 4 Rad, Kolon og Boks
			*/

class CommonSuperClassForBCR 
{
	int dim;
	CommonSuperClassForBCR(int dim)
	{
		this.dim = dim;
	}
	public void settInnRute() 
	{

	}
}
			/**
			Class Box
			*/
class Box extends CommonSuperClassForBCR
{
	Square[] squares;
	int dim;
	Square sqr;
	Box bx;
	Square sqNo;
	int counter =0;
	Box (int dim)
	{
		super(dim);
		squares = new Square [dim];
	}
	void settInnRute( Square sqr)
	{
		squares[counter++] = sqr;
	}
	boolean isLegal(int no) 
	{
		for (int i = 0; i< Board.rows * Board.columns; i++)
		{
			if (squares[i].sqNo == no)
			{
				return false;
			}
		} 
		return true;
	}	
}

/**
			CLASS COLUMN
*/

class Column extends CommonSuperClassForBCR 
{
	Square[] squares;
	Square sqr;
	int dim;
	int counter =0;
	Column(int dim)
	{	
		super(dim);
		squares = new Square [dim];
	}
	void settInnRute( Square sqr)
	{
		squares[counter++] = sqr;
	}
	
	boolean isLegal(int no) 
	{
		for (int i = 0; i< Board.rows * Board.columns; i++)
		{
			if (squares[i].sqNo == no)
			{
				return false;
			}
		} 
		return true;
	}
}

/**
			CLASS ROW
*/

class Row extends CommonSuperClassForBCR  
{
	int dim;
	Square []squares;
	Square sqr;
	int counter = 0;
	Row(int dim)
	 {
	 	super(dim);
	 	squares = new Square [dim];
	 }
	void settInnRute( Square sqr)
	{	
		squares[counter++] = sqr;	
	}
	boolean isLegal(int no) 
	{
		for (int i = 0; i< Board.rows * Board.columns; i++)
		{
			if (squares[i].sqNo == no)
			{
				return false;
			}	
		} 
		return true;
	}
	 
}

/**
					CLASS GUI
*/

class Gui extends JFrame  
{  
	JButton newSolution = new JButton("Next solution");  
    final SudokuBeholder sb;     
    int [][] brett;
    final int rows ;
    final int columns;
    final JTextField[][] subPanels;
    final JPanel GuiPanel;
    int counter = 1;
 Gui(int [][] tempSolution, int rows, int columns, SudokuBeholder sb)  
    {  
    	super("The sudoku Gui with answers");  
    	this.brett = tempSolution;
    	this.rows = rows;
    	this.columns = columns;
      	this.sb = sb;
      	GuiPanel = new JPanel(new GridLayout(rows*columns, rows*columns));  
      	subPanels = new JTextField[rows*columns][rows*columns];      
      	setSize(500, 500);  
	  	setDefaultCloseOperation(EXIT_ON_CLOSE);  
	  	add(GuiPanel);  
	  	setLocationRelativeTo(null);    
        Border outerBorder = BorderFactory.createLineBorder(Color.black, 2);  
        Border innerBorder = BorderFactory.createLineBorder(Color.BLUE, 1);  
        Border innerCell   = BorderFactory.createLineBorder(Color.RED, 2);  
        GuiPanel.setBorder(innerCell);  
       	for (int r = 0; r < rows*columns; r++)   
        {  
            for (int c = 0; c <  rows*columns; c++)   
            {
            	subPanels[r][c] = new JTextField();  
                subPanels[r][c].setForeground(Color.blue);
                int tempToString =tempSolution[r][c];
                if(tempToString >=10)
                {
                 	char toString = (char)tempToString;
                 	brett[r][c]= toString + 55 ;
                 	int i = brett[r][c];
					char car = (char)i;
					 subPanels[r][c].setText("" + car); 
                }  
                else
                {
                	subPanels[r][c].setText("" + brett[r][c]); 
            	}
               	subPanels[r][c].setHorizontalAlignment(JTextField.CENTER);  
                subPanels[r][c].setBorder(innerBorder);  
                subPanels[r][c].setBorder(innerBorder);  
                subPanels[r][c].setBackground(Color.lightGray);  
                GuiPanel.add(subPanels[r][c]);  
                if ((r/rows + c/columns) % 2 == 0)  
                subPanels[r][c].setBackground(Color.GREEN);  
            }                  
        }

    	newSolution.addActionListener(new ActionListener() 
    	{
      		public void actionPerformed(ActionEvent ae) 
      		{
      			refreshGpanel();        
      		}
    	});

        final JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));  
        ButtonPanel.setLayout(new BorderLayout());          
        ButtonPanel.add(newSolution);  
        Border LineBorder       = new LineBorder(Color.lightGray);  
        ButtonPanel.setBorder(LineBorder);  
        BoxLayout horizontal    = new BoxLayout(ButtonPanel, BoxLayout.X_AXIS);  
        ButtonPanel.setLayout(horizontal);  
        FlowLayout flow = new FlowLayout();  
        ButtonPanel.setLayout(flow);  
        add(ButtonPanel, BorderLayout.SOUTH);  
        setVisible(true);        
    } 
    		// Denne Metoden vises fram løsningene når Nextsolution trykker.
    public void refreshGpanel()       
    {
    	if(sb.antLosninger > 1)
    	{
	    	if(counter<=sb.getSolutionCount())
	    	{
	    	 	int[][] st = sb.get(counter);
		    	for (int r = 1; r < Board.rows*Board.columns; r++)   
		        {  
		            for (int c = 1; c <  Board.rows*Board.columns; c++)   
		            {
		            	subPanels[r][c].setText("" + st[r][c]);
		            }
		        }
		        counter++;
		    }
		    if(counter>= sb.antLosninger || counter == 750)
		    {
		      	JOptionPane.showMessageDialog(null, "The last Solutions has been displayed, The program will EXIT ");
		        System.exit(0);	
		    }
		}
	     else
	     {
     		JOptionPane.showMessageDialog(null, "The Sudoku has one Solution or har not a solution, So the program will EXIT ");
	        System.exit(0);	
	     }
    } 
} 


/**
							CLASS FILECHOOSER
*/
class FileChooserClass extends JFrame 
{
   public FileChooserClass() 
   {
	   	super("Choose the File");
	    setSize(400, 400);
	    Container conPane = getContentPane();
	    conPane.setLayout(new FlowLayout());
	    JButton openButton = new JButton("Open");
	    final JLabel statusbar = new JLabel("Choose the text file");
	   	setDefaultCloseOperation(EXIT_ON_CLOSE);  
	    openButton.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent a) 
	        {
	            try // Her gjør jeg programmet litt Robust slik som den krasjer ikke hvis annet fil format valges.
	            {
			        JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
					chooser.setFileFilter(filter);  
			        chooser.setMultiSelectionEnabled(true);
			        int opt = chooser.showOpenDialog(FileChooserClass.this);
			        if (opt == JFileChooser.APPROVE_OPTION) 
			        {
			        
				        File[] filN = chooser.getSelectedFiles();
				        String fileName = "No File";
				        if (filN.length > 0) fileName = filN[0].getName();
				        for (int i = 1; i < filN.length; i++) 
				        {
				            fileName = filN[i].getName();   
				        }
				        setVisible(false);  
				        int choice = JOptionPane.showConfirmDialog(null, "Do you want to see the Solutions");
						if(choice == JOptionPane.YES_OPTION)
						{
							new Board(fileName);
						}
						else
						{
							System.exit(0);
						}
				        statusbar.setText("You chosed " + fileName);
			        }
			        else 
			        {
			          statusbar.setText("You have canceled.");
			        }
	      		} 
		    	catch(Exception e)
		    	{
			    	JOptionPane.showMessageDialog(null, "The File You chosed is not the right Format, The Program will Exit");
			    	System.exit(0);
		      	}
	      	}
	    });
	    conPane.add(openButton);
	    conPane.add(statusbar);
  	}
}