
import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.lang.NumberFormatException;
import java.util.Iterator;
import java.util.NoSuchElementException;
class Oblig4 {
    @SuppressWarnings("unchecked") 
	public static void main (String args []) throws FileNotFoundException {
        Read rd = new Read();
        rd.readFile();  // read from the file.
        rd.menu(); // show the menu
        
    }
} // end of Oblig4

class Read { //
    
        // declaring all the beholder here.
    Tabell <Person> tabper = new Tabell <Person>(20);;
    Person person = null;
    YngsteForstReseptListe  persResBeholder = new YngsteForstReseptListe();
    EldsteForstReseptListe  legResBeholder = new EldsteForstReseptListe();
    Tabell <Legemiddel> tabLmiddel= new Tabell <Legemiddel>(100);
    Legemiddel lmiddler = null;
    SortertEnkelListe <Leger> legeBeholder = new SortertEnkelListe <Leger> ( );
    Leger leg = null;
    EnkelReseptListe reseptBeholder = new EnkelReseptListe ();
    Resept resept1 = null;
    //person variables
    int personUniktNo;
    String navn;
    String gender;
    int unique; // unik lopende nummer
    int pernumber;
    int personUnique; // person unique number to keep count on the new added person
    int reseptUnique; // keep count on the new added resept.
                          
    // reading from file

    public void readFile()  
    {  
           String[] tokens = null;
    
        try 
        {
            Scanner scan = new Scanner(new FileInputStream("data2.txt"), "UTF-8");
            while(scan.hasNextLine()) 

            {
             String stn = scan.nextLine();
             String stn2 = scan.nextLine();                
            
            if(stn.contains("#"))
            {
                int y = stn.indexOf('#');  // checking the indexes of bound
                int x = stn.indexOf('(');
                String ss = stn.substring(y+1, x-1);
                ss = ss.trim();
                if(ss.equals("Personer")) // check if ss is equal to person and then create and on object of person
                {
                   
                    for(int i=0; i<6; i++)
                    { 
                       
                        Scanner s = new Scanner(stn2).useDelimiter(",");
                        int personUniktNo = s.nextInt();
                        personUnique = personUniktNo; 
                        String navn = s.next();
                        String gender = s.next();

                        person = new Person (personUniktNo,navn,gender);
                        
                        tabper.settIn(personUniktNo, person); // adding to the Tabell <Person> 

                        
                        stn2 = scan.nextLine();
                         }
                         
                        personUnique ++;

                } 
                
                else if (ss.equals("Legemidler")) // check if ss is equal to legemidler and then create and on object of legemidler
                
                 {
                    for(int i=0; i<3; i++){ 
                        tokens = stn2.split(",");
                        int len = tokens.length;                       
                        Scanner s1 = new Scanner(stn2).useDelimiter(", ");
                       if(len == 7) // reading the line that contains 7 tokens

                       {

                            int unikNo = s1.nextInt();
                            unique=unikNo;
                            String LegemiddelNavn = s1.next();
                            String form = s1.next();
                            String type = s1.next();
                            int pris = s1.nextInt();
                            int antallPill = s1.nextInt();
                            int sterkNo = s1.nextInt();
                            
                            if(form.equals("pille") && type.equals("a")) // compare if the form and type and then create NarkotiskPiller: if the type is a
                            {
                            
                            lmiddler = new NarkotiskPiller(LegemiddelNavn, unikNo,pris, sterkNo,antallPill);
                            tabLmiddel.settIn(unikNo, lmiddler); 
                            
                            }

                            else if(form.equals("liniment") && type.equals("a"))
                            {
                               
                                lmiddler = new NarkotiskLiniment(LegemiddelNavn, unikNo,pris, sterkNo,antallPill); // create nakotisk liniment
                                tabLmiddel.settIn(unikNo, lmiddler); // adding to the beholder
                               

                            }
                            else if(form.equals("injeksjon") && type.equals("a")){
                                lmiddler = new NarkotiskInjekksjon (LegemiddelNavn, unikNo,pris, sterkNo,antallPill);
                                tabLmiddel.settIn(unikNo, lmiddler); 
                            } 
                            else if (form.equals("pille") && type.equals("b")){
                                lmiddler = new VannedanendePiller (LegemiddelNavn, unikNo,pris,sterkNo, antallPill);
                                tabLmiddel.settIn(unikNo, lmiddler); 
                             
                            } 
                            else if (form.equals("liniment") && type.equals("b")){
                                lmiddler = new VannedanendeLiniment (LegemiddelNavn, unikNo,pris,sterkNo, antallPill);
                                tabLmiddel.settIn(unikNo, lmiddler); 
                             
                            }
                            else {
                                lmiddler = new VannedanendeInjeksjon (LegemiddelNavn, unikNo,pris,sterkNo, antallPill);
                                tabLmiddel.settIn(unikNo, lmiddler); 
                             
                            }
                            
                            } else { // reading the line that contains 6 tokens: that is the vannlige legemidler
                            int unikNo = s1.nextInt();
                            unique=unikNo;
                            String LegemiddelNavn = s1.next();
                            String form = s1.next();
                            String type = s1.next();
                            int pris = s1.nextInt();
                            int antallPill = s1.nextInt();

                            if(form.equals("pille") && type.equals("c")){
                            lmiddler = new VanligePiller(LegemiddelNavn,unikNo, pris,antallPill);
                            tabLmiddel.settIn(unikNo, lmiddler); 
                            }
                             else if (form.equals("liniment") && type.equals("c")){
                                lmiddler = new VanligeLiniment (LegemiddelNavn,unikNo, pris,antallPill);
                                tabLmiddel.settIn(unikNo, lmiddler); 
                             } 
                             else {
                                
                                lmiddler = new VanligeInjeksjon (LegemiddelNavn,unikNo, pris,antallPill);
                                tabLmiddel.settIn(unikNo, lmiddler); 
                             }    

                                
                            }
                    
                            stn2 = scan.nextLine();
                         }
                        
                        unique++; 
                        

                }  
                 else if (ss.equals("Leger")){
                    for(int i=0; i<5; i++){ 
                       
                    Scanner slege = new Scanner(stn2).useDelimiter(", ");
                    String name = slege.next();
                    int snumber = slege.nextInt();
                    int anumber = slege.nextInt();
                    leg = new Leger(name,snumber,anumber); // creating a new leger object
                    legeBeholder.setteIn(leg); // adding to the beholder
                    stn2 = scan.nextLine();
                 }
                  
        

                } 
                else if (ss.equals("Resepter")){
                    for(int i=0; i<3; i++){ 
                       
                            Scanner s = new Scanner(stn2).useDelimiter(", ");
                            int reseptUnikNo=s.nextInt();
                            reseptUnique = reseptUnikNo;
                            String farge=s.next(); 
                            int pernumber = s.nextInt(); 
                            String legename=s.next();
                            int legemidnumber=s.nextInt();
                            int reit=s.nextInt();

                            if(farge.equals("b")){
                               resept1 = new BlaResept (reseptUnikNo,pernumber,legename,legemidnumber,reit);
                            //resept1 = new Resept (reseptUnikNo,farge,pernumber,legename,legemidnumber,reit);
                            reseptBeholder.settInResept(resept1); // adding to the main resept beholder/ enkeltreseptbeholder
                            } else {
                            resept1 = new HvitResept (reseptUnikNo,pernumber,legename,legemidnumber,reit);
                            reseptBeholder.settInResept(resept1); // adding to the main resept beholder/ enkeltreseptbeholder
                             
                            }


                            Iterator <Person> it = tabper.iterator();
                            while(it.hasNext()){
                            Person p = it.next();
                            int temp = p.finnPersonNo(); 
                            // check if the person number is equal to the person number in the resept
                            if(temp==pernumber){
                                persResBeholder.settInResept(resept1); // adding to the persons resept beholder 
                                  }

                            }

                        for (Leger p: legeBeholder) {   
                        String legNn = p.finneNavn();
                        if(legNn.equals(legename)){
                        legResBeholder.settInResept(resept1); // adding to the leger resept beholder
                                   
                            }

                        } 
                        if(scan.hasNextLine())
                            stn2 = scan.nextLine();
                         }
                          
                        stn2 = scan.nextLine();
                        reseptUnique++; 

                }
                else if (ss.equals("Slutt")) {
                    // confirm end of the file
                    System.out.println("You have reached the end of the file");
                }
                else {


                }

            }

        
        }
    
        scan.close();
    }
    catch(Exception e) {
        System.out.println("Could not read file properly");
        System.out.println("Exception e: " + e.getMessage());
        e.printStackTrace();
    }
    }


void menu(){
        int input=0;
    
    while (input!=7){
    System.out.println();        
    System.out.println("*******************************************************");        
    System.out.println("WELCOME TO  OBLIG4  PRESS 1-6 TO NAVIGATE \n");

    Scanner reader = new Scanner(System.in);
    System.out.println("1. PRINT OVERVIEW: Data from file and update \n"); 
    System.out.println("2. Create and add a new legemiddel \n"); 
    System.out.println("3. Create and add a new lege \n");  
    System.out.println("4. Create and add a new Person  \n");   
    System.out.println("5. Create and add a new Resepet \n");  
    System.out.println("6. Get LegeMiddel \n");  
    System.out.println("7. Terminate the Program \n"); 



    input = reader.nextInt();
        switch(input) {
                case 1:
             System.out.println( "UPDATED OVERVIEW: Data from file and updates"); 
                overview();
                break;

                case 2:
             System.out.println(" Legemiddel"); 
             registerNewLegeMiddler();
                break;


            case 3:
             System.out.println(" Lege"); 
             registerNewLeger();
                break;

            case 4:
             System.out.println(" Personer"); 
             registerNewPerson ();
                break;

            case 5:
             System.out.println(" Resepter"); 
             registerNewResept ();
                break;
            

             case 6:
             System.out.println(" hentLegemiddel"); 
             hentLegemiddel();
                break;
            
            case 7:
            System.out.println(" The Program has been Terminated!"); 
            System.out.println("********************************");
            System.out.println("     ");


             input=7;
                break;


            default:
                System.out.println("Option invalid! Try again");
                break;
            }
        }
    }
    



    public void hentLegemiddel() {
        Legemiddel lmidd= null;
        Resept res=null;
        Person pers =null;

        Scanner in = new Scanner (System.in);
        System.out.println("Enter the personal number");
        int pernum = in.nextInt();
        if( in.hasNextLine() ) {
            in.nextLine();
        }

        System.out.println("Enter the Resept number");
        int respNo = in.nextInt();

            res = reseptBeholder.finne(respNo);
            res.useReit();
            pers = tabper.findObjekt(pernum);
            int legeMNtemp = res.getLegeMNo();
            lmidd = tabLmiddel.findObjekt(legeMNtemp);



            if (res.getReit() != 0) {
                System.out.println("--------------------------------------------------");
                System.out.println("Resept number: " + respNo + " for " + pers.finnPersonName());
                System.out.println("Resepter en av typen " + lmidd.legeMName()+ "  "+"Antall Piller/ Milligram/ Kubikcentimeter: "+lmidd.antallstorrelse());
                System.out.println("Pris: " + lmidd.legeMPris());

                res.printAll();
            } else {
                System.out.println("Resept ugyldig!");
            }
    }

    
    void overview() { 

        System.out.println("Person");
        System.out.println("--------------------------------------------------");
        System.out.println("Person nummer" +"  " + " navn "+"    "+ "kjonn" );
        Iterator <Person> it = tabper.iterator();
        while(it.hasNext()){
         Person p = it.next();
            p.skrivUtPerson();
        
        }  

        System.out.println("Legemiddel");
        System.out.println("--------------------------------------------------");
        System.out.println( "nr" +"  " + "navn" + "  "+"form" +" " + "type" +"   " + "pris" +"   " + " mengde" +" " +"[styrke]" );

        Iterator <Legemiddel> iter = tabLmiddel.iterator();
         while(iter.hasNext()){
            Legemiddel l = iter.next();
            l.skrivUtLegemiddler();
        }


        System.out.println("Leger");
        System.out.println("--------------------------------------------------");
        System.out.println("navn" + "  "+ "spesialist" +"  " + "avtalenr");
        
        for (Leger l: legeBeholder) {   
               l.skrivUt();
        } 

        System.out.println();
        System.out.println("Leger med avtale ");
        System.out.println("--------------------------------------------------");
        for (Leger l: legeBeholder) { 
            int avtaleNo = l.checkAvtale();
            if(avtaleNo !=0 ){
            System.out.println(l.finneNavn());
            }
        }
        System.out.println();
        System.out.println("leger med avtale har skrevet følgende Narkotisk LegeMiddel");
        System.out.println("--------------------------------------------------");
               
        String lName;
        for (Leger l: legeBeholder) {   
            int avtaleNo = l.checkAvtale();
            if(avtaleNo !=0 ){
              lName =  l.finneNavn();
              int antall = 0;
               for (Resept r: reseptBeholder ) {
                    String legName = r.getName();

                    if(legName.equals(lName)){
                        int legMNo = r.getLegeMNo();   
                        Legemiddel ll=  tabLmiddel.findObjekt(legMNo);
                        if(ll instanceof Narkotisk){
                        antall ++;
                        }

                    }
                }
                System.out.println(lName + "    "+"har skrevet" +"    " + antall+ "   " + "Narkotisk Legemidler");

            }

        } 


        System.out.println();
        System.out.println("Persons med gyldig blaa Resept");
        System.out.println("------------------------------------------------------");
        for (Resept p: reseptBeholder ) {   // go through each persons resept beholder
            String fargeTemp = p.getFarge(); // get the colour
            int persNo = p.getpersMNo();    // get the person #
            Person perTemp = tabper.findObjekt(persNo); // use the # to get the object of that specific person
            int legmNo= p.getLegeMNo(); // get the legemiddel #
            Legemiddel temp = tabLmiddel.findObjekt(legmNo); // find the object of that legemiddel based on the #
            String formTemp =  temp.legeMForm(); // get the form of that legemidel 
        
            if(p instanceof BlaResept && formTemp.equals("injeksjon")){
                System.out.println(perTemp.finnPersonName()+"  " +  "har" + "   "+p.getReit() + "  " + "gyldig blaa resept og " +
                + temp.antallstorrelse () + " som er " + " " + temp.legeMForm() + "  " + "dose");
            }

        }
        System.out.println();
        System.out.println("Persons navn");
        System.out.println("------------------------------------------------------");
        
        Iterator <Person> it2 = tabper.iterator(); // got through person beholder
        while(it2.hasNext()){
            Person p = it2.next();
            System.out.println(p.finnPersonName()); // print their names
        }
        System.out.println("------------------------------------------------------");
        

        System.out.println("Persons Resept: gyldig Vannedanende legemiddler");
        System.out.println("------------------------------------------------------");
        
        int antall = 0; // count of total number of vanedannende legemidler which valid/gyldig 
        int antallForMenn = 0; // vanedannede legemidler for men
        int antallForKvinne =0; // for women
        for (Resept r: reseptBeholder ) { // go thro the resept beholder: 
            int gyldig =r.getReit();  // get the "reit"
        
            int legmNo2= r.getLegeMNo(); // get the legemiddel #
            Legemiddel temp2 = tabLmiddel.findObjekt(legmNo2); // find the object of legemiddel
            String typeTemp =  temp2.legeMType(); // get string type 
        
            if(gyldig!=0 && temp2 instanceof Vannedanende){
                antall++;                           // the count for the total numer increase each time one is found
                int persNo =  r.getpersMNo();
                Person pp = tabper.findObjekt(persNo); // find the person object 
                
                if(pp.finnPersonKjonn().equals("m")){ // check the gender of that person
                    antallForMenn ++; // if male the count for male increases
                    }
                    else {
                    antallForKvinne ++; // for women increases
                    }
                    
            }  
        } // end of for loop 
        System.out.println("Gyldig resept som er skrevet på Vannedanende Legemidler for menn er" + "  " + antallForMenn);
        System.out.println("Gyldig resept som er skrevet på Vannedanende Legemidler for kvinne er" + "  " + antallForKvinne);
        System.out.println("Totalt gyldig resept som er skrevet på Vannedanende Legemidler er " + antall);
} // end of method

void registerNewLegeMiddler() { // registration a new LegeMiddel
    
    
    Scanner read = new Scanner (System.in);
    System.out.println("Enter the type: a,b, eller c");
    String type = read.nextLine();

    if(type.equalsIgnoreCase("c")) {  // register first the c: vanlige legemiddler
        System.out.println("Enter name");
        String LegemiddelNavn  = read.next().toUpperCase();

        System.out.println("Enter form: pille, liniment, injeksjon");
        String form = read.nextLine();

        System.out.println("Enter the price");
        int pris = read.nextInt();
        if( read.hasNextLine() ) {
        read.nextLine();
         }

        System.out.println("Enter number of tablets/mengde");
        int  antallPill = read.nextInt();
        if( read.hasNextLine() ) {
        read.nextLine();
         }



        if(form.equals("pille") && type.equals("c")){
            lmiddler = new VanligePiller(LegemiddelNavn,unique, pris,antallPill);
            tabLmiddel.settIn(unique, lmiddler); 
        }
        else if (form.equals("liniment") && type.equals("c")){
            lmiddler = new VanligeLiniment (LegemiddelNavn,unique, pris, antallPill);
            tabLmiddel.settIn(unique, lmiddler); 
         } 
        else {
            lmiddler = new VanligeInjeksjon (LegemiddelNavn,unique, pris, antallPill);
            tabLmiddel.settIn(unique, lmiddler); 
         }    




    } 
    else {    // registration of new LegeMiddel type a and b

        System.out.println("Enter name");
        String LegemiddelNavn  = read.nextLine().toUpperCase();

        System.out.println("Enter form: pille, liniment, injeksjon");
        String form = read.nextLine();

        System.out.println("Enter the price");
        int pris = read.nextInt();
        if( read.hasNextLine() ) {
        read.nextLine();
         }


        System.out.println("Enter number of tablets/mengde");
        int  antallPill = read.nextInt();
        if( read.hasNextLine() ) {
        read.nextLine();
         }

        System.out.println("Enter strength level/styrke");
        int sterkNo = read.nextInt();
        

        if (type.equalsIgnoreCase("a") && form.equalsIgnoreCase("pille")){
            lmiddler = new NarkotiskPiller(LegemiddelNavn, unique,pris, sterkNo,antallPill);
             tabLmiddel.settIn(unique, lmiddler); 
        }
        else if (type.equalsIgnoreCase("a") && form.equalsIgnoreCase("liniment")){
            lmiddler = new NarkotiskLiniment (LegemiddelNavn, unique,pris, sterkNo,antallPill);
             tabLmiddel.settIn(unique, lmiddler); 
        }
        else if (type.equalsIgnoreCase("a") && form.equalsIgnoreCase("injeksjon")){
            lmiddler = new NarkotiskInjekksjon (LegemiddelNavn, unique,pris, sterkNo,antallPill);
             tabLmiddel.settIn(unique, lmiddler); 
        }
        else if (type.equalsIgnoreCase("b") && form.equalsIgnoreCase("pille")){
            lmiddler = new VannedanendePiller (LegemiddelNavn, unique,pris, sterkNo,antallPill);
             tabLmiddel.settIn(unique, lmiddler); 
        }
        else if (type.equalsIgnoreCase("b") && form.equalsIgnoreCase("liniment")){
            lmiddler = new VannedanendeLiniment  (LegemiddelNavn, unique,pris, sterkNo,antallPill);
             tabLmiddel.settIn(unique, lmiddler); 
        }
        else {
            lmiddler = new VannedanendeInjeksjon (LegemiddelNavn, unique,pris, sterkNo,antallPill);
             tabLmiddel.settIn(unique, lmiddler); 
            }

        } 
        unique++;

    } // end of the method

    void registerNewLeger (){ // register new leger/doctor
        Leger leg1;
        Scanner addLeger = new Scanner (System.in);
        System.out.println("Enter the Doctor name ");
        String nameLeg = addLeger.nextLine().toUpperCase();

        System.out.println("Enter Doctor's special number");
        int specialNo = addLeger.nextInt();

        if( addLeger.hasNextLine() ) {
        addLeger.nextLine();
        }

        System.out.println("Enter the agreement number/avtale nummer: 0 hvis ingen avtale) ");
        int  avtaleNo = addLeger.nextInt();

        leg1 = new Leger(nameLeg,specialNo,avtaleNo); // creating a new lege
        legeBeholder.setteIn(leg1);    // adding to the beholder
                  // adding to the beholder
    } // end of method

    void registerNewPerson () { // creating a new person

        Scanner addPerson = new Scanner (System.in);
        System.out.println("Enter the Person name ");
        String navn = addPerson.nextLine();

        System.out.println("Enter the gender m for male and k for female ");
        String  gender = addPerson.nextLine();


        person = new Person (personUnique,navn,gender);
        tabper.settIn(personUnique, person); // adding to the beholder

        personUnique++;
    } // end of method

    void registerNewResept (){ // register a new resept

        Scanner readRe = new Scanner (System.in);            
        System.out.println("Enter the Resept colour b for blaa or h for hvit");
        String farge = readRe.nextLine();

       
        System.out.println("Enter the Person's number");
        int pernumber = readRe.nextInt();
        if( readRe.hasNextLine() ) {
        readRe.nextLine();
        }

        
        System.out.println("Enter the Doctor's name");
        String  legename = readRe.nextLine();


        System.out.println("Enter the Legemidler number");
        int legemidnumber = readRe.nextInt();
        if( readRe.hasNextLine() ) {
        readRe.nextLine();
        }

    
        System.out.println("Enter the reit");
        int reit = readRe.nextInt();
        if( readRe.hasNextLine() ) {
        readRe.nextLine();
        }

        if (farge.equals("b")) {
        resept1 = new BlaResept (reseptUnique,pernumber,legename,legemidnumber,reit);
        reseptBeholder.settInResept(resept1); // add to the beholder
        } else {
        resept1 = new HvitResept (reseptUnique,pernumber,legename,legemidnumber,reit);
        reseptBeholder.settInResept(resept1); // add to the beholder
        
        }

       Iterator <Person> it = tabper.iterator();
        while(it.hasNext()){
        Person p = it.next();
        int temp = p.finnPersonNo(); 
        // check if the person number is equal to the person number in the resept
        if(temp==pernumber){
            persResBeholder.settInResept(resept1); // adding to the persons resept beholder 
              }

        }

        for (Leger p: legeBeholder) {   
        String legNn = p.finneNavn();
        if(legNn.equals(legename)){
        legResBeholder.settInResept(resept1); // adding to the leger resept beholder
                   
            }

        }

        reseptUnique++;

    }

    
} // end of the class Read




interface Piller {
public int antallstorrelse();

}

interface Injeksjon {
public int antallstorrelse ();
}

interface Liniment{
public int antallstorrelse();
}


abstract class Legemiddel  {
String navn;
int pris;
int unikNo; 
String LegemiddelNavn;
   
   Legemiddel ( String LegemiddelNavn, int unikNo, int pris ) {
        
         this.LegemiddelNavn= LegemiddelNavn;
         this.unikNo = unikNo;
         this.pris = pris;
         
    }
    public void skrivUtLegemiddler() {
        
        System.out.println( unikNo + " " + LegemiddelNavn +" " + " "+ pris);
    } 
    public String legeMName(){
        return LegemiddelNavn;
    }
    public String legeMForm(){
       return null;

    }
    public String legeMType(){
        return null;
    }


    public int legeMPris(){
        return pris;
    }

    public int antallstorrelse(){
        return 0;
    }


}

abstract class Narkotisk extends  Legemiddel  { // CLASS TYPE A
    int sterkNo;
    
    

    Narkotisk (  String LegemiddelNavn, int unikNo, int pris, int sterkNo){
        super(LegemiddelNavn,unikNo,pris);
        this.sterkNo = sterkNo;

       
    }
}

class NarkotiskInjekksjon extends Narkotisk implements Injeksjon {
    int hvorMye;
    final String form;
    String type;
    
    
    NarkotiskInjekksjon (  String LegemiddelNavn, int unikNo, int pris, int sterkNo, int hvorMye){
        super(LegemiddelNavn,unikNo,pris, sterkNo);
        this.hvorMye = hvorMye;
        form = " injeksjon";
        
         
    }
    

    public void skrivUtLegemiddler() {
        if (this instanceof Narkotisk) {
            type = "a";
        }
    System.out.println( unikNo + " " + LegemiddelNavn + " "+ form +" " + type + " "+ pris +" "+ hvorMye +" "+sterkNo);
    } 

    public int antallstorrelse (){return hvorMye;}
    public String legeMForm(){
        return form;
    }

}

class NarkotiskPiller extends Narkotisk implements Piller {
    int antallPill;
    final String form;
    String type;
    
    NarkotiskPiller (String LegemiddelNavn, int unikNo, int pris, int sterkNo, int antallPill){
         super(LegemiddelNavn,unikNo,pris, sterkNo);
         
         this.antallPill = antallPill;
         form = "pille";
         
    }
   public void skrivUtLegemiddler() {
    if (this instanceof Narkotisk) {
            type = "a";
        }
        System.out.println( unikNo + " " + LegemiddelNavn + " "+ form +" " + type + " "+ pris +" "+ antallPill +" "+sterkNo);
    }
    public int antallstorrelse(){
        return antallPill;
    }
    public String legeMForm(){
        return form;
    }

}

class NarkotiskLiniment extends Narkotisk implements Liniment {

    int hvorMange;
    final String form;
    String type;
   
    NarkotiskLiniment ( String LegemiddelNavn, int unikNo, int pris, int sterkNo, int hvorMange ){
        super(LegemiddelNavn,unikNo,pris, sterkNo);
        this. hvorMange = hvorMange;
        form = "liniment";
        
    }
    public void skrivUtLegemiddler() {
    if (this instanceof Narkotisk) {
            type = "a";
        }
        System.out.println( unikNo + " " + LegemiddelNavn + " "+ form +" " + type + " "+ pris +" "+ hvorMange +" "+sterkNo);
    }
    public int antallstorrelse(){ return hvorMange;}
    public String legeMForm(){
        return form;
    }

}



abstract class Vannedanende extends  Legemiddel{ // CLASS TYPE B
    
    int vanneNo;
   Vannedanende (  String LegemiddelNavn, int unikNo, int pris, int vanneNo){
        super(LegemiddelNavn,unikNo,pris);
        this.vanneNo = vanneNo;
    }
}


class VannedanendePiller extends Vannedanende implements Piller{
    int antallPill;
    final String form;
    String type;
    VannedanendePiller ( String LegemiddelNavn, int unikNo, int pris, int vanneNo, int antallPill){
        super(LegemiddelNavn,unikNo,pris, vanneNo);
        this.antallPill = antallPill;
        form = "pille";
    }
    public void skrivUtLegemiddler() {
        if (this instanceof Vannedanende) {
            type = "b";
        }
    System.out.println( unikNo + " " + LegemiddelNavn + " "+ form +" " + type + " "+ pris +" "+ antallPill +" "+vanneNo);
    }

    public int antallstorrelse(){return antallPill;}

    public String legeMForm(){
        return form;
    }

}


class VannedanendeInjeksjon extends Vannedanende implements Injeksjon{

    int hvorMye;
    final String form;
    String type;

    VannedanendeInjeksjon (String LegemiddelNavn, int unikNo, int pris, int vanneNo, int hvorMye){
        super(LegemiddelNavn,unikNo,pris, vanneNo);
        this.hvorMye = hvorMye;
        form = "injeksjon";
    }
    public void skrivUtLegemiddler() {
        if (this instanceof Vannedanende) {
            type = "b";
        }
        System.out.println( unikNo + " " + LegemiddelNavn + " "+ form +" " + type + " "+ pris +" "+ vanneNo +" "+hvorMye);
    }
    public int antallstorrelse (){ return hvorMye;}
    
    public String legeMForm(){
        return form;
    }

}

class VannedanendeLiniment extends Vannedanende implements Liniment {
    int hvorMange;
    final String form;
    String type;
    VannedanendeLiniment ( String LegemiddelNavn, int unikNo, int pris, int vanneNo, int hvorMange ){
        super(LegemiddelNavn,unikNo,pris, vanneNo);
        this.hvorMange= hvorMange;
        form = "liniment";
    }

    public void skrivUtLegemiddler() {
        if (this instanceof Vannedanende) {
            type = "b";
        }
        System.out.println( unikNo + " " + LegemiddelNavn + " "+ form +" " + type + " "+ pris +" "+ vanneNo +" "+hvorMange);
    }

    public int antallstorrelse(){return hvorMange;}
   
    public String legeMForm(){
        return form;
    }

}


abstract class Vanlige extends  Legemiddel{  // CLASS TYPE C

    Vanlige ( String LegemiddelNavn, int unikNo, int pris){
        super(LegemiddelNavn,unikNo,pris);
    }
    
}

class VanligePiller extends Vanlige implements Piller {
    int antallPill;
    final String form;
    String type;
VanligePiller (String LegemiddelNavn, int unikNo, int pris, int antallPill){
    super(LegemiddelNavn,unikNo,pris);
    this.antallPill = antallPill;
    form = "pille";
    }
    public void skrivUtLegemiddler() {
        if (this instanceof Vanlige) {
            type = "c";
        }
    System.out.println( unikNo + " " + LegemiddelNavn + " "+ form +" " + type + " "+ pris +" "+ antallPill);
    }

    public int antallstorrelse(){return antallPill;}
    public String legeMForm(){
        return form;
    }

}

class VanligeInjeksjon extends Vanlige implements Injeksjon {
    int hvorMye;
    final String form;
    String type;
    VanligeInjeksjon (String LegemiddelNavn, int unikNo, int pris, int hvorMye ){
    super(LegemiddelNavn,unikNo,pris);
    this.hvorMye = hvorMye;
    form = "injeksjon";
    }
    public void skrivUtLegemiddler() {
        if (this instanceof Vanlige) {
            type = "c";
        }
    System.out.println( unikNo + " " + LegemiddelNavn + " "+ form +" " + type + " "+ pris +" "+ hvorMye);
    }
    public int antallstorrelse (){return hvorMye;}
    public String legeMForm(){
        return form;
    }

}

class VanligeLiniment extends Vanlige implements Liniment {
    int hvorMange;
    final String form;
    String type;
VanligeLiniment (String LegemiddelNavn, int unikNo, int pris, int hvorMange ){
        super(LegemiddelNavn,unikNo,pris);
        this.hvorMange = hvorMange;
        form = "liniment";
    }
    public void skrivUtLegemiddler() {
        if (this instanceof Vanlige) {
            type = "c";
        }
    System.out.println( unikNo + " " + LegemiddelNavn + " "+ form +" " + type + " "+ pris +" "+ hvorMange);
    }
    public int antallstorrelse(){ return hvorMange;}
    public String legeMForm(){
        //return type;
        return form;
    }

} // END OF LEGEMIDDEL AND ITS SUBCLASSES




class Resept {
   
    int reseptUnikNo;
    String farge; // Blaa er gratis
    int pernumber; 
    String legename;
    int legemidnumber;
    int reit;
    private Legemiddel lmpeker;
    
        Resept(int reseptUnikNo,int pernumber, String legename, int legemidnumber, int reit){
        this.reseptUnikNo = reseptUnikNo;
        this.pernumber=pernumber;
        this.legename=legename;
        this.legemidnumber=legemidnumber;
        this.reit=reit;
    }
    public void printAll() {

        System.out.println("Resept er skrevet ut av lege "+legename);
        System.out.println("Reiten igjen er: "+reit+" og fargen er "+farge);
        
    }
    public void useReit() {
        reit--;
    }
    public int getReit() {
        return reit;
    }
    public int getLegeMNo() {
        return legemidnumber;
    }
    public int getpersMNo() {
        return pernumber;
    }
    public String getFarge() {
        return farge;
    }
    public String getName() {
        return legename;
    }



    public void skrivUtResept() {System.out.println(reseptUnikNo+ " "+" "+ pernumber+" "+legename+" "+ legemidnumber+" "+reit);}
    
    public int hentResept(){return reseptUnikNo;}


} // end of class Respt

class BlaResept extends Resept {
    String farge;

    BlaResept(int reseptUnikNo,int pernumber, String legename, int legemidnumber, int reit){
        super(reseptUnikNo, pernumber, legename, legemidnumber, reit);
        }
        
        public void printAll() {
            if (this instanceof BlaResept) {
            farge = "b";
            }

        System.out.println("Resept er skrevet ut av lege "+legename);
        System.out.println("Reiten igjen er: "+reit+" og fargen er "+farge);
        
    }
    
}
class HvitResept extends Resept {
    String farge;
    HvitResept(int reseptUnikNo, int pernumber, String legename, int legemidnumber, int reit){
        super(reseptUnikNo, pernumber, legename, legemidnumber, reit);
    }
         public void printAll() {

        if (this instanceof HvitResept) {
            farge = "h";
        }

        System.out.println("Resept er skrevet ut av lege "+legename);
        System.out.println("Reiten igjen er: "+reit+" og fargen er "+farge);
        
    }
        

}

interface legeAvtale {
   public int checkAvtale ();
    
}
interface Lik {
    
    public boolean samme( String s);
    
    
}

// class Leger
class Leger implements  Comparable<Leger>, Lik, legeAvtale {
	public String unikNavn;
    int anumber;
    int snumber;
   public Leger next;
   EldsteForstReseptListe legResBeholder = new EldsteForstReseptListe  ();
   

    Leger(String unikNavn, int snumber, int anumber){
        this.unikNavn = unikNavn;
        this.snumber=snumber;
        this.anumber=anumber;
        }

   
    public String finneNavn(){return unikNavn; }


    public void skrivUt() {System.out.println( unikNavn+ "\t"+snumber+ "\t"+anumber);}

    public boolean samme(String s) {
        if(unikNavn.equals(s))
        return true;
        else 
        return false;

    } 


    public int checkAvtale(){
    return anumber;

    }

    public int compareTo(Leger d){
        return (this.unikNavn).compareTo(d.unikNavn);
    }
    
} // end of class Leger


class SpesialistLeger extends Leger  {
   // int snumber;
    SpesialistLeger (String unikNavn, int snumber, int anumber){
        super (unikNavn,snumber, anumber);
        
    }

}


// class Person
class Person {
	String navn;
	int personUniktNo;
    String gender;
    YngsteForstReseptListe  persResBeholder = new YngsteForstReseptListe ();
   
    Person(int personUniktNo, String navn, String gender){
        this.personUniktNo = personUniktNo;
        this.navn = navn;
        this.gender=gender;
    }
    public void skrivUtPerson() {System.out.println(personUniktNo +"\t" +navn + "\t" + gender);}

    public int finnPersonNo() { return personUniktNo;}
    public String finnPersonName(){ return navn;}
    public String finnPersonKjonn(){ return gender;}




} // end of the class Person


interface  AbsraktTabell <T > {
     public boolean settIn(int index, T t);
    
    T findObjekt (int index);
    public Iterator <T> iterator ();
   
    }

// class Tabell
class Tabell <T> implements AbsraktTabell  <T>   {

    int index;
    int lengde;
    private T[] elements;

    Tabell( int lengde) {
    elements = (T[])new Object [lengde];
    }
    
    public boolean settIn( int index, T t ){ // adding object based on the specified index
        if(elements.length > index) {
            
        elements[index] = t; 
        return true; 

        } else {

             return false;
         }

    }
    
    
    public T findObjekt (int index ) { // find object based on the specified index
         return elements [index];
    }
     @Override 
    public Iterator <T> iterator ( ) {
        return new ItarateTabell ();
        
    }
    

    private class ItarateTabell implements Iterator <T> { // iterator
        private int i = 0;
        private ItarateTabell () {
            while (i < elements.length && elements [i]== null) {
                i++;
            }
        }
        
    @Override 
    public boolean hasNext (){
        return i<elements.length;
    }
    @Override 
    public T next (){
        if(i < elements.length){
            T n = elements[i];
            do {
                i++;
            } 
            while (i < elements.length && elements [i]== null);
            return n;

        } else {
            throw new NoSuchElementException();
        }
    }

    @Override 
    public void remove () {
        throw new UnsupportedOperationException("Invalid operation for sorted list.");
        }

    } 
   // @SuppressWarnings ("unchecked")

} // End of class Tabell



interface AbstracktSortertEnkelListe <U> {

public void setteIn(U element);

public U finne(String s);

public Iterator <U> iterator ();

}


class SortertEnkelListe < U extends Comparable <U> & Lik > implements AbstracktSortertEnkelListe <U>, Iterable <U> {
    public Node foerste, siste;
    private int antall;
    
    SortertEnkelListe(){
    
    }
   
    private class Node {
     
    Node neste;
    Node forrige;
    U element;

    Node (U element) { 
        this.element = element; 
        }

    }

    public void setteIn (U element){  // adding the object to the beholder
        Node n = new Node(element);
           
        if(foerste==null){

        foerste = n;
        siste =n;
        }
        Node temp=foerste;

        if (temp.neste == null && temp.element.compareTo(element)<0){ // sette sist

            foerste.neste = n;
            siste =n;
           
         }

        else if (temp.element.compareTo(element) > 0){
        n.neste = foerste;
        foerste = n;

        }
        else {
        while(temp.neste!=null){
            if(temp.element.compareTo(element)<0 && temp.neste.element.compareTo(element)>0){
                n.neste = temp.neste;
                temp.neste =n;
                } else {
                    temp = temp.neste;
                }
        
            }
       
        }
        antall ++; 
        
    }



    
       
    public U finne(String s){ // find an object based on the given string 
        Node n = foerste;

    for (int i = antall; i>0; i--) {
        
        if (n.element.samme(s)) {
          return n.element;
        } else {
        n = n.neste;
        }   
    }
        return null;
    }

    @Override 
    public Iterator <U> iterator ( ) {
        return new Sortert ();
        
    }
    
    public class Sortert implements Iterator<U> { // Iterator
        Node temp;
        Sortert(){temp = foerste;}
        
        @Override 
        public boolean hasNext( ) {
            return temp != null;

            
        
        }
        @Override 
        public U next ( ) {
            
            U retur = temp.element;
            temp = temp.neste;
            return retur;
           
        }
        @Override 
        public void remove ( ){
          
        }
    }

} // end of SortertEnkelListe class


class EnkelReseptListe implements Iterable <Resept> {
    public Node foerste, siste;
    public int antall;
    
    EnkelReseptListe (){
    siste = foerste;

    }

    protected class Node { 
    int reseptNo;   
    Node neste;
    Resept t;

        Node (Resept t) { 
        this.t = t; 

            }
    
        }

	public void settInResept(Resept t) { // add Resept to the beholder
    Node n = new Node(t);
    if(foerste==null) {
        foerste = n;
        siste = n;
        }   else {
            n.neste = foerste;
            foerste = n;
        }
        
	}

	public Resept finne (int reseptNo ) { // find the resept based on the resept number
        Node n = foerste;
         while(n!=null){

            if(n.t.hentResept() == reseptNo){
            return n.t;
            } 
            else {
            n = n.neste;
            
            }
        }
        return null;

    }

    
    //@SuppressWarnings("unchecked") 
    @Override 
    public Iterator <Resept> iterator ( ) {
        return new Enkel();
        
    }
    
    protected class Enkel implements Iterator<Resept> {
        
        boolean check;
        Node temp;
        Enkel (){temp = foerste;}
        @Override 
        
        public boolean hasNext( ) {
           return temp != null;
            
        }
        @Override 
        public Resept next ( ) {
                if(temp!=null){
            Resept retur = temp.t;
           temp = temp.neste;
           
            return retur;
            }
            else {
                throw new NoSuchElementException(); 
            }
        }
        @Override 
        public void remove ( ){
            
            
        }
    }
} // end of EnkelReseptListe



class EldsteForstReseptListe extends EnkelReseptListe {
    // FIFO
	public void settInResept(Resept t) { // adds the leger respet
         Node n = new Node(t);

    if(foerste==null) {
        foerste = n;
        siste = n;
        }   else {
    siste.neste = n;
    siste = n;
       }
	}
       
} // end of EldsteForstReseptListe

class YngsteForstReseptListe  extends EnkelReseptListe {
	// add persons resepter as LIFO
   public void settInResept(Resept t) {
    Node n = new Node(t);
    if(foerste==null) {
        foerste = n;
        siste = n;
        }   else {
            n.neste = foerste;
            foerste = n;
        }
	}

} // End of YngsteForsteReseptListe
