/*

 Jeg har jobbet sammen med  Stephen Simei Kimogol som har denne bruke navn:  stephesk 

*/

public class Oblig3FB
{
	public static void main (String args [])
	{
		TestKlass tk = new TestKlass();
        tk.skrivInfo();      
	
	}

} // Slutt av Oblig3 klass



interface Gave 
{
  String kategori();

  String gaveId();
}



class GaveLager 
{ 
	static private int ANTALLGAVER = 239;
	public Gave [] gaver;
	private int nesteGaveNr = 0;

	GaveLager() 
	{
		gaver = new Gave[ANTALLGAVER];

		gaver[0] = new Bok("William Faulkner: Absalom, Absalom!");
		gaver[1] = new Vin("Badia a Coltibuono Toscana Cancelli, 2011");
		gaver[2] = new Bok("Aldous Huxley: After Many a Summer Dies the Swan");
		gaver[3] = new Bok("Eugene O'Neill: Ah, Wilderness!");
		gaver[4] = new Bok("Sidney Howard: Alien Corn");
		gaver[5] = new Bok("W. Somerset Maugham: The Alien Corn");
		gaver[6] = new Bok("Vita Sackville-West: All Passion Spent");
		gaver[7] = new Bok("Robert Penn Warren: All the King's Men");
		gaver[8] = new Bok("Michael Morpurgo: Alone on a Wide, Wide Sea");
		gaver[9] = new Plate ("Madonna: The Immaculate Collection");
		gaver[10] = new Bok("Aldous Huxley: Antic Hay");
		gaver[11] = new Plate ("Michael Jackson: Thriller");
		gaver[12] = new Bok("George Bernard Shaw: Arms and the Man");
		gaver[13] = new Plate ("Michael Jackson: Dangerous");
		gaver[14] = new Bok("John Grisham: A Time to Kill");
		gaver[15] = new Plate ("Santana: Supernatural");
		gaver[16] = new Bok("Val McDermid: Beneath the Bleeding");
		gaver[17] = new Plate ("Guns N' Roses: Appetite for Destruction");
		gaver[18] = new Bok("Noel Coward: Blithe Spirit");
		gaver[19] = new Plate ("Carole King: Tapestry");
		gaver[20] = new Bok("Francoise Sagan: Bonjour Tristesse");
		gaver[21] = new Bok("Colin Wilson: Brandy of the Damned");
		gaver[22] = new Bok("Dee Brown: Bury My Heart at Wounded Knee");
		gaver[23] = new Bok("Agatha Christie: Butter In a Lordly Dish");
		gaver[24] = new Bok("O. Henry: Cabbages and Kings");
		gaver[25] = new Bok("Dan Simmons: Carrion Comfort");
		gaver[26] = new Bok("Robert B. Parker: A Catskill Eagle");
		gaver[27] = new Bok("Dorothy L. Sayers: Clouds of Witness");
		gaver[28] = new Bok("John Kennedy Toole: A Confederacy of Dunces");
		gaver[29] = new Bok("Iain M. Banks: Consider Phlebas");
		gaver[30] = new Bok("Iain Crichton Smith: Consider the Lilies");
		gaver[31] = new Bok("P. D. James: Cover Her Face");
		gaver[32] = new Bok("Charles Dickens: The Cricket on the Hearth");
		gaver[33] = new Bok("Mark Haddon: The Curious Incident of the Dog in the Night-Time");
		gaver[34] = new Bok("H. E. Bates: The Daffodil Sky");
		gaver[35] = new Bok("Haruki Murakami: Dance Dance Dance");
		gaver[36] = new Bok("Philip Reeve: A Darkling Plain");
		gaver[37] = new Bok("John Gunther: Death Be Not Proud");
		gaver[38] = new Bok("Aldous Huxley: The Doors of Perception");
		gaver[39] = new Bok("David Graham: Down to a Sunless Sea");
		gaver[40] = new Bok("Wilfred Owen: Dulce et Decorum Est");
		gaver[41] = new Bok("George R. R. Martin: Dying of the Light");
		gaver[42] = new Bok("John Steinbeck: East of Eden");
		gaver[43] = new Bok("William Butler Yeats: Ego Dominus Tuus");
		gaver[44] = new Bok("Agatha Christie: Endless Night");
		gaver[45] = new Bok("Jonathan Safran Foer: Everything is Illuminated");
		gaver[46] = new Bok("Aldous Huxley: Eyeless in Gaza");
		gaver[47] = new Bok("H. E. Bates: Fair Stood the Wind for France");
		gaver[48] = new Bok("Howard Spring: Fame Is the Spur");
		gaver[49] = new Bok("Edna O'Brien: A Fanatic Heart");
		gaver[50] = new Bok("Katharine Hull and Pamela Whitlock: The Far-Distant Oxus");
		gaver[51] = new Bok("Ernest Hemingway: A Farewell to Arms");
		gaver[52] = new Bok("Thomas Hardy: Far From the Madding Crowd");
		gaver[53] = new Vin("A.A. Badenhorst Family Swartland, 2009");
		gaver[54] = new Bok("Roger Zelazny: For a Breath I Tarry");
		gaver[55] = new Bok("Ernest Hemingway: For Whom the Bell Tolls");
		gaver[56] = new Bok("James Jones: From Here to Eternity");
		gaver[57] = new Bok("Barbara Pym: A Glass of Blessings");
		gaver[58] = new Bok("William Manchester: The Glory and the Dream");
		gaver[59] = new Bok("Ray Bradbury: The Golden Apples of the Sun");
		gaver[50] = new Bok("Henry James: The Golden Bowl");
		gaver[51] = new Bok("Margaret Mitchell: Gone with the Wind");
		gaver[52] = new Bok("John Steinbeck: The Grapes of Wrath");
		gaver[53] = new Bok("John Crowley: Great Work of Time");
		gaver[54] = new Bok("Louis Bromfield: The Green Bay Tree");
		gaver[55] = new Bok("Evelyn Waugh: A Handful of Dust");
		gaver[56] = new Bok("Dorothy L. Sayers: Have His Carcase");
		gaver[57] = new Bok("Carson McCullers: The Heart Is a Lonely Hunter");
		gaver[58] = new Bok("JT LeRoy: The Heart Is Deceitful Above All Things");
		gaver[59] = new Bok("Philip Pullman: His Dark Materials");
		gaver[50] = new Bok("Edith Wharton: The House of Mirth");
		gaver[51] = new Bok("H. E. Bates: How Sleep the Brave");
		gaver[52] = new Bok("James H. Hunter: How Sleep the Brave");
		gaver[53] = new Bok("John Briley: How Sleep the Brave");
		gaver[54] = new Bok("William Faulkner: If I Forget Thee Jerusalem");
		gaver[55] = new Bok("Primo Levi: If Not Now, When?");
		gaver[56] = new Bok("David Weber & Steve White: In Death Ground");
		gaver[57] = new Bok("John Steinbeck: In Dubious Battle");
		gaver[58] = new Bok("Maya Angelou: I Know Why the Caged Bird Sings");
		gaver[59] = new Bok("Peter Robinson: In a Dry Season");
		gaver[60] = new Vin("Mastroberardino Taurasi Radici Riserva, 2006");
		gaver[61] = new Bok("Ray Bradbury: I Sing the Body Electric");
		gaver[62] = new Bok("Robert A. Heinlein: I Will Fear No Evil");
		gaver[63] = new Bok("Katherine Paterson: Jacob Have I Loved");
		gaver[64] = new Bok("Dominique Lapierre and Larry Collins: O Jerusalem!");
		gaver[65] = new Bok("Aldous Huxley: Jesting Pilate");
		gaver[66] = new Bok("Val McDermid: The Last Temptation");
		gaver[67] = new Bok("Ursula K. Le Guin: The Lathe of Heaven");
		gaver[68] = new Bok("James Agee: Let Us Now Praise Famous Men");
		gaver[69] = new Bok("William Edmund Barrett: Lilies of the Field");
		gaver[70] = new Bok("Conor McPherson: This Lime Tree Bower");
		gaver[71] = new Bok("Alan Hollinghurst: The Line of Beauty");
		gaver[72] = new Bok("Lillian Hellman: The Little Foxes");
		gaver[73] = new Bok("Dan Rhodes: Little Hands Clapping");
		gaver[74] = new Bok("Thomas Wolfe: Look Homeward, Angel");
		gaver[75] = new Bok("Iain M. Banks: Look to Windward");
		gaver[76] = new Bok("Madeleine L'Engle: Many Waters");
		gaver[77] = new Bok("Han Suyin: A Many-Splendoured Thing");
		gaver[78] = new Bok("Val McDermid: The Mermaids Singing");
		gaver[79] = new Bok("Agatha Christie: The Mirror Crack'd from Side to Side");
		gaver[80] = new Bok("Stephen Fry: Moab Is My Washpot");
		gaver[81] = new Bok("Robert Crais: The Monkey's Raincoat");
		gaver[82] = new Bok("Laurie R. King: A Monstrous Regiment of Women");
		gaver[83] = new Bok("Madeleine L'Engle: The Moon by Night");
		gaver[84] = new Bok("Kurt Vonnegut: Mother Night");
		gaver[85] = new Bok("Agatha Christie: The Moving Finger");
		gaver[86] = new Bok("John Buchan: Mr Standfast");
		gaver[87] = new Bok("Kamala Markandaya: Nectar in a Sieve");
		gaver[88] = new Bok("Cormac McCarthy: No Country for Old Men");
		gaver[89] = new Bok("Nevil Shute: No Highway");
		gaver[90] = new Vin("Carlisle Zinfandel Napa Valley Hayne Vineyard, 2011");
		gaver[91] = new Bok("Chinua Achebe: No Longer at Ease");
		gaver[92] = new Bok("H. E. Bates: Now Sleeps the Crimson Petal");
		gaver[93] = new Bok("Lois Lowry: Number the Stars");
		gaver[94] = new Bok("W. Somerset Maugham: Of Human Bondage");
		gaver[95] = new Bok("John Steinbeck: Of Mice and Men");
		gaver[96] = new Bok("H. E. Bates: Oh! To be in England");
		gaver[97] = new Vin("Raymond Cabernet Sauvignon Napa Valley Generations, 2009");
		gaver[98] = new Bok("W. Somerset Maugham: The Painted Veil");
		gaver[99] = new Bok("Robert B. Parker: Pale Kings and Princes");
		gaver[100] = new Bok("Paul Kennedy: The Parliament of Man");
		gaver[101] = new Bok("Humphrey Cobb: Paths of Glory");
		gaver[102] = new Bok("E. M. Forster: A Passage to India");
		gaver[103] = new Bok("Willa Cather: O Pioneers!");
		gaver[104] = new Bok("Agatha Christie: Postern of Fate");
		gaver[105] = new Bok("Mary Webb: Precious Bane");
		gaver[106] = new Bok("Isaac Asimov: The Proper Study");
		gaver[107] = new Bok("Henryk Sienkiewicz: Quo Vadis");
		gaver[108] = new Bok("Reginald Hill: Recalled to Life");
		gaver[109] = new Bok("Robert Silverberg: Recalled to Life");
		gaver[110] = new Bok("Gavin Maxwell: Ring of Bright Water");
		gaver[111] = new Bok("M. Scott Peck: The Road Less Traveled");
		gaver[112] = new Bok("William Faulkner: Shall not Perish");
		gaver[113] = new Bok("P. D. James: The Skull Beneath the Skin");
		gaver[114] = new Bok("Anthony Powell: The Soldier's Art");
		gaver[115] = new Bok("Rex Stout: Some Buried Caesar");
		gaver[116] = new Bok("Michael Cunningham: Specimen Days");
		gaver[117] = new Bok("Stephen Fry: The Stars' Tennis Balls");
		gaver[118] = new Bok("Robert A. Heinlein: Stranger in a Strange Land");
		gaver[119] = new Bok("George Orwell: Such, Such Were the Joys");
		gaver[120] = new Bok("Ernest Hemingway: The Sun Also Rises");
		gaver[121] = new Bok("C. S. Lewis: Surprised by Joy");
		gaver[122] = new Bok("Madeleine L'Engle: A Swiftly Tilting Planet");
		gaver[123] = new Bok("Robert B. Parker: Taming a Sea Horse");
		gaver[124] = new Bok("F. Scott Fitzgerald: Tender Is the Night");
		gaver[125] = new Bok("Bruce Catton: Terrible Swift Sword");
		gaver[126] = new Bok("NJ Crisp: That Good Night");
		gaver[127] = new Vin("Le Macchiole Bolgheri, 2011");
		gaver[128] = new Bok("F. Scott Fitzgerald: This Side of Paradise");
		gaver[129] = new Vin("Seghesio Zinfandel Dry Creek Valley Cortina, 2010");
		gaver[130] = new Bok("Aldous Huxley: Those Barren Leaves");
		gaver[131] = new Vin("Epoch Estate Blend Paderewski Vineyard Paso Robles, 2010");
		gaver[132] = new Bok("Alfred Bester: Tiger! Tiger!");
		gaver[133] = new Vin("Cune Rioja Imperial Gran Reserva, 2004");
		gaver[134] = new Bok("Patrick Leigh Fermor: A Time of Gifts");
		gaver[135] = new Vin("Domaine Serene Pinot Noir Willamette Valley Evenstad Reserve, 2010");
		gaver[136] = new Bok("Lawrence Block: Time To Murder And Create");
		gaver[137] = new Vin("Kongsgaard Chardonnay Napa Valley, 2010");
		gaver[138] = new Vin("Hewitt Cabernet Sauvignon Rutherford, 2010");
		gaver[139] = new Bok("Robert A. Heinlein: To Sail Beyond the Sunset");
		gaver[140] = new Bok("Connie Willis: To Say Nothing of the Dog");
		gaver[141] = new Vin("Graham Tawny Port 20 Year Old");
		gaver[142] = new Bok("William Makepeace Thackeray: Vanity Fair");
		gaver[143] = new Vin("Reynvaan Syrah Walla Walla Valley Stonessence, 2010");
		gaver[144] = new Bok("Flannery O'Connor: The Violent Bear It Away");
		gaver[145] = new Vin("Croft Vintage Port, 2011");
		gaver[146] = new Bok("T. S. Eliot: The Waste Land");
		gaver[147] = new Vin("Olivier Ravoire Gigondas, 2011");
		gaver[148] = new Bok("Colin Dexter: The Way Through the Woods");
		gaver[149] = new Vin("Alexana Pinot Noir Dundee Hills Revana Vineyard, 2010");
		gaver[150] = new Bok("Anthony Powell: What's Become of Waring");
		gaver[151] = new Vin("Hamilton Russell Chardonnay Hemel-en-Aarde Valley, 2012");
		gaver[152] = new Bok("E. M. Forster: Where Angels Fear to Tread");
		gaver[153] = new Bok("Chinua Achebe: Things Fall Apart");
		gaver[154] = new Bok("Henry James: The Wings of the Dove");
		gaver[155] = new Bok("Val McDermid: The Torment of Others");
		gaver[156] = new Bok("Mary Elizabeth Braddon: The World, the Flesh and the Devil");
		gaver[157] = new Bok("Dorothy L. Sayers: Thrones, Dominations");
		gaver[158] = new Bok("Rudyard Kipling short story: Tiger! Tiger!");
		gaver[159] = new Vin("Fontanafredda Barolo La Rosa, 2008");
		gaver[160] = new Bok("Stephen Gray: Time of our Darkness");
		gaver[161] = new Bok("John Steinbeck: To a God Unknown");
		gaver[162] = new Bok("Jessica Anderson: Tirra Lirra by the River");
		gaver[163] = new Vin("Giuseppe Mascarello & Figlio Barolo Monprivato, 2008");
		gaver[164] = new Vin("Wynns Coonawarra Estate Cabernet Sauvignon Coonawarra Black Label, 2010");
		gaver[165] = new Vin("Caymus Cabernet Sauvignon Napa Valley Special Selection, 2010");
		gaver[166] = new Vin("Lewis Cabernet Sauvignon Napa Valley Reserve, 2010");
		gaver[167] = new Vin("Quilceda Creek Cabernet Sauvignon Columbia Valley, 2010");
		gaver[168] = new Bok("Evelyn Waugh: Vile Bodies");
		gaver[169] = new Vin("Turley Zinfandel Paso Robles Dusi Vineyard, 2011");
		gaver[170] = new Bok("J.M. Coetzee: Waiting for the Barbarians");
		gaver[171] = new Vin("Bedrock The Bedrock Heritage Sonoma Valley, 2011");
		gaver[172] = new Bok("Samuel Butler: The Way of All Flesh");
		gaver[173] = new Vin("G.D. Vajra Barolo Albe, 2008");
		gaver[174] = new Bok("Adam Smith: The Wealth of Nations");
		gaver[175] = new Vin("Poggerino Chianti Classico, 2010");
		gaver[176] = new Bok("H. E. Bates: When the Green Woods Laugh");
		gaver[177] = new Plate ("Shania Twain: Come On Over");
		gaver[178] = new Bok("Robert B. Parker: The Widening Gyre");
		gaver[179] = new Vin("Mamete Prevostini Valtellina Superiore Sassella, 2009");
		gaver[180] = new Bok("Susan Swan: The Wives of Bath");
		gaver[181] = new Vin("Livio Sassetti Brunello di Montalcino Pertimali, 2008");
		gaver[182] = new Bok("H. E. Bates: The Yellow Meads of Asphodel");
		gaver[183] = new Vin("Quinta do Crasto Douro Reserva Old Vines, 2010");
		gaver[184] = new Vin("Spring Valley Uriah Walla Walla Valley, 2010");
		gaver[185] = new Vin("Domaine Overnoy-Crinquand Arbois Pupillin Vieilles Vignes, 2009");
		gaver[186] = new Plate ("The Beatles: Abbey Road");
		gaver[187] = new Plate ("Bruce Springsteen: Born in the U.S.A.");
		gaver[188] = new Plate ("Celine Dion: Falling into You");
		gaver[189] = new Plate ("Celine Dion: Let's Talk About Love");
		gaver[190] = new Plate ("Dire Straits: Brothers in Arms");
		gaver[191] = new Plate ("Whitney Houston: Whitney Houston");
		gaver[192] = new Vin("M. Lapierre Morgon, 2011");
		gaver[193] = new Vin("Bodega Norton Malbec Mendoza Reserva, 2011");
		gaver[194] = new Vin("Quinta do Passadouro Douro, 2010");
		gaver[195] = new Vin("Mollydooker Two Left Feet McLaren Vale, 2011");
		gaver[196] = new Plate ("Pink Floyd: The Dark Side of the Moon");
		gaver[197] = new Plate ("Eagles: Their Greatest Hits (1971â€“1975)");
		gaver[198] = new Plate ("AC/DC: Back in Black");
		gaver[199] = new Plate ("Bee Gees: Saturday Night Fever");
		gaver[200] = new Plate ("Fleetwood Mac: Rumours");
		gaver[201] = new Plate ("Whitney Houston: The Bodyguard");
		gaver[202] = new Vin("Pierre Paillard Brut Champagne");
		gaver[203] = new Plate ("Led Zeppelin: Led Zeppelin IV");
		gaver[204] = new Plate ("Meat Loaf: Bat Out of Hell");
		gaver[205] = new Plate ("Alanis Morissette: Jagged Little Pill");
		gaver[206] = new Plate ("The Beatles: Sgt. Pepper's Lonely Hearts Club Band");
		gaver[207] = new Plate ("Eagles: Hotel California");
		gaver[208] = new Plate ("Mariah Carey: Music Box");
		gaver[209] = new Plate ("Various artists: Dirty Dancing");
		gaver[210] = new Plate ("Elton John: Goodbye Yellow Brick Road");
		gaver[211] = new Vin("Mamete Prevostini Valtellina Superiore Sassella, 2009");
		gaver[212] = new Vin("Lagier Meredith Syrah Mount Veeder, 2010");
		gaver[213] = new Vin("Patz & Hall Pinot Noir Carneros Hyde Vineyard, 2010");
		gaver[214] = new Vin("Bodegas Hidalgo Gitana Manzanilla Jerez La Gitana, NV");
		gaver[215] = new Vin("Ravines Riesling Finger Lakes Dry, 2012");
		gaver[216] = new Vin("Chateau Ste. Michelle Sauvignon Blanc Horse Heaven Hills Horse Heaven Vineyard, 2012");
		gaver[217] = new Plate ("James Horner: Titanic: Music from the Motion Picture");
		gaver[218] = new Bok("Madeleine L'Engle: An Acceptable Time");
		gaver[219] = new Plate ("Metallica: Metallica");
		gaver[220] = new Bok("Brian Keenan: An Evil Cradling");
		gaver[221] = new Plate ("Michael Jackson: Bad");
		gaver[222] = new Bok("William Faulkner: As I Lay Dying");
		gaver[223] = new Plate ("Pink Floyd: The Wall");
		gaver[224] = new Bok("Michael Moorcock: Behold the Man");
		gaver[225] = new Plate ("ABBA: ABBA Gold: Greatest Hits");
		gaver[226] = new Bok("Aldous Huxley: Beyond the Mexique Bay");
		gaver[227] = new Plate ("Britney Spears: ...Baby One More Time");
		gaver[228] = new Bok("James Ellroy: Blood's a Rover");
		gaver[229] = new Plate ("Norah Jones: Come Away with Me");
		gaver[230] = new Plate ("U2: The Joshua Tree");
		gaver[231] = new Plate ("Backstreet Boys: Millennium");
		gaver[232] = new Plate ("TLC: CrazySexyCool");
		gaver[233] = new Plate ("Eminem: The Marshall Mathers LP");
		gaver[234] = new Plate ("Boston: Boston");
		gaver[235] = new Plate ("George Michael: Faith");
		gaver[236] = new Plate ("Michael Jackson: HIStory: Past, Present and Future, Book I");
		gaver[237] = new Plate ("Prince & The Revolution: Purple Rain");
		gaver[238] = new Plate ("Tina Turner: Private Dancer");
	}


	public Gave hentGave() 
	{
		Gave nesteGave = null;
		if (nesteGaveNr < 500) 
		{
			nesteGave = gaver[nesteGaveNr % ANTALLGAVER];
			nesteGaveNr++;
		}
		return nesteGave;
	}
} // Slutt av GaveLager klass


class Bok implements Gave 
{

    private String kat, id;
    Bok ( String i ) 
	{
		kat = "bok";
		id = i;
    }
    public String kategori() 
	{
		return kat;
    }
    public String gaveId() 
	{	
		return id;
    }
} // Slutt av Bok klass


class Plate implements Gave 
{
	private String kat, id;
	Plate ( String i ) 
	{
		kat = "cd";
		id = i;
	}
	public String kategori() 
	{
		return kat;
	}
	public String gaveId() 
	{
		return id;
	}
} // Slutt av Plate klass


class Vin implements Gave 
{
    private String kat, id;
    Vin ( String i ) 
	{
		kat = "vin";
		id = i;
    }
    public String kategori() 
	{
		return kat;
    }
    public String gaveId() 
	{
		return id;
    }
} // Slutt av Vin klass



 class Personer 
 {
		
	private String[] oblig3Navn = 
	{
		"Karl", "Kenneth", "Kim", "Kjell", "Kjetil", "Knut",
		"Simen", "Sindre", "Siri", "Stian", "Thanh", "Thomas",
		"Elena", "Endre", "Erik", "Erlend", "Espen", "Fredrik",
		"Gard", "Halvard", "Hanna", "Hans", "Henning", "Henrik",
		"William", "Eystein", "Gandalf", "Aasmund",
		"Herman", "Haakon", "Ingrid", "Jan", "Jarl", "Joakim",
		"Alex", "Alexander", "Alexandra", "Anders", "Andreas", "Anton",
		"Johan", "Johannes", "Jon", "Jonas", "Julia", "Georg",
		"Konstantin", "Kristine", "Kristoffer", "Lars", "Lasse", "Linda",
		"Line", "Mads", "Magnus", "Maria", "Marius", "Marte",
		"Audun", "Bendik", "Benjamin", "Ursus", "Bo", "Baard",
		"Martin", "Martine", "Mathias", "Mats", "Minh", "Mohammed",
		"Ole", "Peter", "Petter", "Robin", "Rune", "Sebastian",
		"Morten", "Natalia", "Nicolay", "Odd", "Ola", "Olav",
		"Tom", "Tor", "Torarin", "Torgeir", "Vegard", "Vilde",
		"Carl", "Christian", "Christine", "Christoffer", "Daniel", "Eirik"
	} ;

	
	
	public int antall = oblig3Navn.length;
	private int nestePersonNr = 0;
	private Person[] persTabell = new Person[antall];

	Personer() 
	{
		// oppretter personene. Forutsetter at det finnes en konstruktÃ¸r
		// i Person som har personens navn som parameter (String)
		
		for (int i=0; i<antall; i++)
			persTabell[i] = new Person(oblig3Navn[i]);

		// Setter opp kjennskapsforbindelsene

		for (int i=1; i<antall; i++)
			for (int j=1; j<i+1; j++)
			persTabell[i].blirKjentMed(persTabell[i-j]);

		for (int i=0; i<20; i++)
			for (int j=1; j<15; j++)
			persTabell[i].blirKjentMed(persTabell[i+j]);

		// Oppretter noen uvennskap

		for (int i=7; i<antall; i++) 
		{
			persTabell[i].blirUvennMed(persTabell[i-7]);
			persTabell[i].blirUvennMed(persTabell[i-6]);
			if (i > 24) persTabell[i].blirUvennMed(persTabell[i-20]);
			if (i > 34) persTabell[i].blirUvennMed(persTabell[i-30]);
			if (i > 62) persTabell[i].blirUvennMed(persTabell[i-60]);
			if (i > 92) persTabell[i].blirUvennMed(persTabell[i-90]);
		}

		// forelskelser og sammen med

		for (int i=6; i<antall; i=i+2) 
			if (persTabell[i].erKjentMed(persTabell[i-2]))
			persTabell[i].blirForelsketI(persTabell[i-2]);

		for (int i=7; i<antall; i=i+3) 
			if (persTabell[i].erKjentMed(persTabell[i-2]))
			persTabell[i].blirSammenMed(persTabell[i-2]);
			
		// oppretter interesser for gaver

		for (int i=0; i<antall; i=i+3)
			persTabell[i].samlerAv("bok", 12);
		for (int i=1; i<antall; i=i+3)
			persTabell[i].samlerAv("vin", 5);
		for (int i=2; i<antall; i=i+3)
			persTabell[i].samlerAv("cd", 7);
		for (int i=1; i<antall; i=i+7)
			persTabell[i].samlerAv("ingenting", 1);
	
	}


	// Denne metoden kalles for å få en peker til en og en person
	// Returnerer null når det ikke er flere personer igjen

	public Person hentPerson() 
	{ 
		Person nestePerson = null;
		if (nestePersonNr < antall) 
		{
			nestePerson = persTabell[nestePersonNr];
			nestePersonNr++;
		}
		return nestePerson;
	}

	// Metode som returnerer navnene på alle personene

	public String[] hentPersonnavn()
	{ 
		return oblig3Navn; 
	}

} // Slutt av Personer klass

class Person
{
	private String navn;
	private Person [] kjenner;
	private Person [] likerikke;
	private Person [] venn;
	private Person forelsketi;
	private Person sammenMed;
	private int vennteller = 0;
	
	private Bok [] Books;
	private Plate [] Plates;
	private Vin [] Vin;
	private int arstall;
	private String artist;
	Person neste;
	private String hvaSlagsGave;
	public Gave [] mineGaver;

	Person()
	{
    
    }
	
    Person(String n)
	{
		navn = n;
		kjenner = new Person[100];
		likerikke = new Person[10];
		venn = new Person[likerikke.length];		
	}
	
	public void samlerAv(String smlp, int ant)
	{
        hvaSlagsGave =smlp;
        mineGaver = new Gave[ant];


        

	}
	
    public Gave tilbyGave(Gave g)
    {
        if(g.kategori().equals(hvaSlagsGave)) 
    	{
    		for(int i = 0; i < mineGaver.length; i++)
    		{
    			if(mineGaver[i] == null)
    			{
    				mineGaver[i]=g;
    				return null;
    			}
    		}
    	}
    	return g;
    
    }
    
    
	public Gave vilDuHaGaven(Gave g)  
	{
		if(g.kategori().equals(hvaSlagsGave)) // først gi gaven til personen som er interesert i
    	{
    		for(int i = 0; i < mineGaver.length; i++)
    		{
    			if(mineGaver[i] == null)
    			{
    				mineGaver[i]=g;
    				return null;
    			}
    		}
    	}
    	
    		if(sammenMed !=null && sammenMed.tilbyGave(g)==null)  // gi til de som er sammen med
    		{
            
                return null;
                
            
    		}
    		else if(forelsketi !=null && forelsketi.tilbyGave(g)==null)  // gi til de som er forelesket i med

 			{
 				return null;
                
 			}
 			else
 			{
 				for(int i = 0; i < venn.length; i++) // gi til de som er venner med
				{	if(venn[i]!=null)
					if(venn[i].tilbyGave(g)==null)
					{
                        return null;
					}
				}
 			}
    
        return g;
	}
	
	
	public	void blirSammenMed ( Person p) 
	{
		if (sammenMed != null) 
		sammenMed.slaoppSammenMed(this);
		if(this.erSammenMed(p))
		{
			p.erSammenMed(this);
		}
	
	}
	public void slaoppSammenMed(Person p)
	{
		sammenMed=null;
	}
	public boolean erSammenMed(Person p)
	{
		if(!navn.equals(p.hentNavn()))
		{
        	if(navn!=null)
        	sammenMed = p;		
    	}
		return true;
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
       	 if (kjenner[i]!=null)
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
    	
        for (Person p: venn) 
		{
	 		if ( p!=null) System.out.print(p.hentNavn() + "  ");
	 		
		}
    }
    
	public Person hentBestevenn ()

	{
		return kjenner[0];
	}

	public	Person[] hentVenner () 
	{
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
 	public void skrivUtGaver ()	
  	{
		if(mineGaver !=null)
		{
            for (Gave g: mineGaver) 
            {
                if ( g!=null) System.out.println(g.kategori() + "  "+g.gaveId());
            }
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
			System.out.println(navn + " er forelsket i " + forelsketi .hentNavn());
		}	
		if	(sammenMed!=null)
		{
			System.out.println(navn + " er Sammen Med i " + sammenMed.hentNavn());
		}
		
		System.out.println("");
		System.out.print(navn + " Gaver: "); 
		skrivUtGaver();
		System.out.println("");
	}
}	// Slutt av Person klass

 class ListeAvPersoner 
 {
    public Person personliste, sistePerson;
    private int antall;
    
    ListeAvPersoner()
	{
		Person lh = new Person("LISTEHODE!!");
		personliste = lh;
		sistePerson = lh;
		antall = 0;
    }
       
    public boolean check (Person p) 
    {
    	Person temp;
        temp =personliste; 
         while (temp != null)
         {
             if(temp == p)
             {       
                 return true;  
             }
             temp = temp.neste;    
         }
         return false;
     }
    public void settInnIStarten(Person nypers)
	{
		if(check(nypers))
		{
			System.out.println(" person was already in the list!");
		}
		else
		{
			nypers.neste = personliste.neste;
			personliste.neste = nypers;
			if (sistePerson.neste == nypers) // nyperson er ny siste!
			sistePerson = nypers;
			antall++;
		}
    }
    
    public void settInnSist(Person inn)
	{
		if(check(inn))
		{
			System.out.println(" person was already in the list!");
		}
		else
		{
        
            sistePerson.neste = inn;
            sistePerson = inn;
            antall++;
        }
    }

    public void settInnEtter(Person denne, Person nypers) 
	{
		if(check(nypers) || !check(denne) || denne == nypers)
		{
			System.out.println(" nypers was already in the list OR denne are not in the list OR nypers and denne are the same person!");
		}
		else
		{

            nypers.neste = denne.neste;
            denne.neste = nypers;
            if (sistePerson.neste == nypers)  // nyperson er ny siste!
			sistePerson = nypers;
            antall++;
        }
    }

    public Person finnPerson(String s) 
	{
		Person p = personliste.neste;
			for (int i = antall; i>0; i--) 
			{
				if (p.hentNavn().equals(s)) return p;
				else p = p.neste;
			}
		return null;
    }

    
    public void skrivAlle()
	{ 
        Person p = personliste.neste;
		System.out.println("------");
        for (int i = antall; i>0; i--) 
		{
     	    System.out.print(antall - i +1 + ": ");
            p.skrivUtAltOmMeg();
            p = p.neste;
        }
		System.out.println("=======");
    }
} // Slutt av ListeAvPersoner klass




class TestKlass
{
	public void skrivInfo()
	{
    	GaveLager gl = new GaveLager();
    	Personer pr = new Personer();
		ListeAvPersoner ll= new ListeAvPersoner();
		Person pers = new Person();
		Person u = pr.hentPerson() ;
		String ss [] = pr.hentPersonnavn();
		while(u!=null)
		{
		
			ll.settInnIStarten(u);
			u = pr.hentPerson();

		}
				 		
		Gave temp = gl.hentGave();
		while (temp !=null)
		{
			for(int i=0; i<ss.length; i++)
			{
		 		Person temp2 = ll.finnPerson(ss[i]);
		 		temp = temp2.vilDuHaGaven(temp);
		 		if(temp ==null)
		 		break;
		 		
                
		  	}
		  	temp= gl.hentGave();
		  	
		}
		for(int i=0; i<ss.length; i++)
		{
		 	Person temp2 = ll.finnPerson(ss[i]);
			temp2.skrivUtAltOmMeg();
		}
			
	}

} // Slutt av Testklass klass



