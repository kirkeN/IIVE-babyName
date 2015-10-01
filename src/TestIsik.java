import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
/*"Programmi ülesanne on aidata kasutajal valida lapse nime.\n"
+ "Poiste ja tüdrukute nimed loetakse eraldi failidest ja need nimed on saadud veebilehelt www.nimi.ee. \n"
+ "Programm loob esmalt nendest sisseloetud nimedest kaks järjendit (list) ja siis teeb nad Isik tüüpi isenditeks ning paigutab massiivi.\n"
+ "Siis pakutakse Random meetodit kasutades suvalist nime ja küsitakse kasutaja käest, kas see sobib lapse nimeks.\n"
+ "Kui ei sobi, siis hakatakse kasutajalt küsima erinevaid nime iseloomustavaid parameetreid, millele vastavalt \n"
+ "käivitatakse esitähe otsimise meetod, nime pikkuse leidmise meetod ja täishäälikute arvu leidmise meetod.\n"
+ "Lisaks eesnimetatud meetoditele, kasutab programm mitmes kohas get meetodit.\n"
+ "Kui soovidele vastavat nime ei ole, tagastab programm lause - Sellist nime ei ole.");*/
public class TestIsik  extends JPanel  implements ActionListener {
	JTextArea selgitus;
	JLabel randomNimi;
	JRadioButton nimiSobib;
	JRadioButton nimiEiSobi;
	JRadioButton suguPoiss; 
	JRadioButton suguTydruk; 
	JRadioButton haalikudJah;  
	JRadioButton haalikudEi;
	JRadioButton pikkusJah;  
	JRadioButton pikkusEi;
	JTextField nimePikkus;
	JTextField esimeneTaht;
	JTextField taishaalikuid;
	String juhuslikNimi;
	JButton viimaneNupp;
	static List<String> nimed = new ArrayList<String>(); // teen listi nimede jaoks, mis vastajale kuvatakse, n-ö sobivad nimed
	char sisestatudEsitaht;
	int pikkus;
	int haalikuteArv;
	static Isik[] IsenditeJarjend; 
	static String[] taishaalikud = {"a","e","i","o","u","õ","ä","ö","ü","A","E","I","O","U","Õ","Ä","Ö","Ü","y","Y"}; //taishaalikute massiiv
	static int muutuja;
	static ButtonGroup nupugrupp1;
	static ButtonGroup nupugrupp2;
	static ButtonGroup nupugrupp3;
	static ButtonGroup nupugrupp4;
	
	static void jarjendiLoomine() throws Exception{
		File Poisid = new File("nimed_poiss.txt");
		Scanner sc=new Scanner(Poisid); 
		List<String> listPoisid = new ArrayList<String>(); //list tüüpi järjendi loomine poiste nimedele
		while (sc.hasNextLine()) { //failist lugemine
			String rida=sc.nextLine();  //rida tuleb eraldi muutujasse salvestada
			listPoisid.add(rida);		//igal real eraldi nimi failis, mis lisatakse järjendisse
	}
		sc.close();
		
		File Tydrukud=new File("nimed_tydruk.txt"); // txt failid peavad olema proj. samas kaustas
		 Scanner sc2=new Scanner(Tydrukud); //tuleb eraldi muutuja teha
		 List<String> listTydrukud = new ArrayList<String>();
		 while (sc2.hasNextLine()) {
		  String rida=sc2.nextLine();//rida tuleb eraldi muutujasse salvestada
		  listTydrukud.add(rida);   
	}
		 System.out.println(listTydrukud.size());
		sc2.close();	
		
		IsenditeJarjend= new Isik [listPoisid.size()+listTydrukud.size()];{  //poiste ja tüdrukute nimedele massiivi loomine, array jaoks on vaja size'i 
			 for (int i=0; listPoisid.size()>i; i++){  
			  Isik poiss= new Isik(listPoisid.get(i), "poiss"); //isendite tegemine
			  IsenditeJarjend[i]=poiss; //isendi lisamine massiivi IsenditeJarjend
			 }
			 for(int i=listPoisid.size(); IsenditeJarjend.length>i; i++) {
			  Isik tydruk= new Isik(listTydrukud.get(i-listPoisid.size()), "tüdruk");
			  IsenditeJarjend[i]=tydruk; //isendi lisamine massiivi IsenditeJarjend
			 }
		}
		//System.out.println(IsenditeJarjend.length);
	}
	
	public TestIsik() throws Exception { //konstruktor
		 super(new BorderLayout());
	    		
			selgitus = new JTextArea("Vastake järgmistele küsimustele, et valida lapsele meelepärane nimi.");
			selgitus.setEditable(false);
			selgitus.setOpaque(false);
	     
	        JLabel kys1 = new JLabel("1. Kas see juhuslik nimi sobib lapsenimeks?");  
	        JLabel kys2 = new JLabel("2. Valige sugu");
	        JLabel kys3 = new JLabel("3. Sisestage nime esitaht");
	        JLabel kys4 = new JLabel("4. Kas soovite määrata nime pikkust?");
	        JLabel kys5 = new JLabel("5. Sisestage nime pikkus");
	        JLabel kys6 = new JLabel("6. Kas soovite määrata täishaalikute arvu?");
	        JLabel kys7 = new JLabel("7. Sisestage täishaalikute arv");
	      
	        
	        //loon JLabelid
	        jarjendiLoomine();
	        int jrkNr=(int) Math.round(0 + Math.random()*((IsenditeJarjend.length)+1));//juhusliku nime jaoks on vaja indeksit
	        juhuslikNimi = IsenditeJarjend[jrkNr].getNimi(); // otsin jarjendist juhusliku nime (string)
	        randomNimi = new JLabel(IsenditeJarjend[jrkNr].getNimi()); // juhuslik nimi kuvatakse paneelil (JLabel)
	        nimePikkus= new JTextField(1);
	        esimeneTaht = new JTextField(1);
	        taishaalikuid = new JTextField(1);
	        
	        //Loon raadionupud ja kuularid
	        ButtonGroup nupugrupp1 = new ButtonGroup(); //nuppudegrupp, et ühe sissevajutamsiel teine tühistatakse
	        nimiSobib = new JRadioButton("Jah");
	    	nimiEiSobi = new JRadioButton("Ei");
	    	nupugrupp1.add(nimiSobib);
	    	nupugrupp1.add(nimiEiSobi);
	    	nimiSobib.addActionListener(this);
	    	nimiEiSobi.addActionListener(this);
	    	
	    	ButtonGroup nupugrupp2 = new ButtonGroup();
	    	suguPoiss = new JRadioButton("Poiss");
	    	suguTydruk = new JRadioButton("Tüdruk");
	    	nupugrupp2.add(suguPoiss);
	    	nupugrupp2.add(suguTydruk);
	    	suguPoiss.addActionListener(this);
	    	suguTydruk.addActionListener(this);
	    	
	    	ButtonGroup nupugrupp3 = new ButtonGroup();
	    	pikkusJah = new JRadioButton("Jah");
	    	pikkusEi = new JRadioButton("Ei");
	    	nupugrupp3.add(pikkusJah);
	    	nupugrupp3.add(pikkusEi);
	    	pikkusJah.addActionListener(this);
	    	pikkusEi.addActionListener(this);
	    	
	    	ButtonGroup nupugrupp4 = new ButtonGroup();
	    	haalikudJah = new JRadioButton("Jah");
	    	haalikudEi = new JRadioButton("Ei");
	    	nupugrupp4.add(haalikudJah);
	    	nupugrupp4.add(haalikudEi);    
	    	haalikudJah.addActionListener(this);
	    	haalikudEi.addActionListener(this);
	    	
	    	viimaneNupp = new JButton("Valmis");
	    	viimaneNupp.addActionListener(this);
	    	
	    	//lisan kysimused paneelile
	        JPanel paneel = new JPanel(new GridLayout(0, 1)); //loon paneeli
	        paneel.add(selgitus);
	        paneel.add(kys1);
	        paneel.add(randomNimi);
	        paneel.add(nimiSobib);
	        paneel.add(nimiEiSobi);
	        paneel.add(kys2);
	        paneel.add(suguPoiss);
	        paneel.add(suguTydruk);
	        paneel.add(kys3);
	        paneel.add(esimeneTaht);
	        paneel.add(kys4);
	        paneel.add(pikkusJah);
	        paneel.add(pikkusEi);
	        paneel.add(kys5);
	        paneel.add(nimePikkus);
	        paneel.add(kys6);
	        paneel.add(haalikudJah);
	        paneel.add(haalikudEi);
	        paneel.add(kys7);
	        paneel.add(taishaalikuid);
	        
	        JPanel paneel2 = new JPanel(new FlowLayout()); //"Valmis" nupu jaoks teen eraldi paneeli
	        paneel2.add(viimaneNupp);
	        
	        add(paneel, BorderLayout.LINE_START);  
	        add(paneel2, BorderLayout.PAGE_END);
	        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	        
	}	        
	
	//PEAMEETOD
	public static void main(String[] args) throws Exception, erind{ 
		
		JFrame raam = new JFrame("IIVE");
        raam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new TestIsik();
        raam.setContentPane(newContentPane);
        raam.pack(); // ei ole kindlaid mõõtusid, vastavalt sisule tuleb kast
        raam.setVisible(true);
        }

	@Override
	public void actionPerformed(ActionEvent e) {
		File fail = new File("SobivadNimed.txt"); // fail, kuhu kirjutatakse sobivad nimed
		PrintWriter pw = null;
		if (e.getSource() == viimaneNupp){
			nimed = new ArrayList<String>(); //loon listi, kuhu hakatakse sobivaid, kriteeriumitele vastavaid nimesid lisama
			try{
				//alguses kontrollib, kas esimene küsimus on vastatud
				if (nimiSobib.isSelected() == false && nimiEiSobi.isSelected() == false) {
					throw new erind ("Esimene küsimus on vastamata!");
				}
				//kui valitakse et juhuslik nimi ei sobi, siis kontrollitakse kas teised küsimused on vastatud
				if (nimiEiSobi.isSelected() == true && suguPoiss.isSelected() == false && suguTydruk.isSelected() == false) {
					throw new erind ("Teine küsimus on vastamata!");
				}
				if (nimiEiSobi.isSelected() == true && pikkusJah.isSelected() == false && pikkusEi.isSelected() == false) {
					throw new erind ("Neljas küsimus on vastamata!");
				}
				if (nimiEiSobi.isSelected() == true && haalikudJah.isSelected() == false && haalikudEi.isSelected() == false) {
					throw new erind ("Kuues küsimus on vastamata!");
				}	
			int muutuja = 0; // muutuja, kui sellist nime ei ole, kui nimi on, siis muutujale liidetakse 1;
			
			pw = new PrintWriter(fail);
			
			if (nimiSobib.isSelected()){
			System.out.println(juhuslikNimi);
			nimed.add(juhuslikNimi);
			muutuja = 1;
			}
			else if (nimiEiSobi.isSelected()){
				if(suguPoiss.isSelected()){
				for (int i = 0; IsenditeJarjend.length >i; i++){
				if(esimeneTaht.getText().equals("")){ // häälikute pikkuse lahtrisse ei sisestatata midagi, viskab erindi 
						throw new erind("Nime esimene täht sisestamata!");	
					}				
				char sisestatudEsitaht1 = esimeneTaht.getText().charAt(0);
				if (Character.isDigit(sisestatudEsitaht1)){ // kui esitaht on number, siis tuleb veateade
					throw new erind("Esitäht peab olema täht!");
				}
				 sisestatudEsitaht = Character.toUpperCase(sisestatudEsitaht1); //teen esitahe suureks
					if( IsenditeJarjend[i].getSugu().equals("poiss") && IsenditeJarjend[i].Esitaht(IsenditeJarjend[i].getNimi())==sisestatudEsitaht) {
						if (pikkusJah.isSelected()) {
							if(nimePikkus.getText().equals("")){ // nime pikkuse lahtrisse ei sisestatata midagi, viskab erindi 
								throw new erind("Nime pikkus sisestamata!");	
							}
							try{
							pikkus = Integer.parseInt((nimePikkus.getText()));
							}
							catch(NumberFormatException x){
								throw new NumberFormatException("Määra nime pikkuseks ikka arv!");
							}
							if (IsenditeJarjend[i].pikkus(IsenditeJarjend[i].getNimi()) == pikkus) {    
								if (haalikudJah.isSelected()) {
									if(taishaalikuid.getText().equals("")){ // häälikute pikkuse lahtrisse ei sisestatata midagi, viskab erindi 
										throw new erind("Sisestage täishäälikute arv!");	
									}
									try{
									haalikuteArv = Integer.parseInt((taishaalikuid.getText()));
									}
									catch(NumberFormatException x){
										throw new NumberFormatException("Määra nime pikkuseks ikka arv");
									}
									if (IsenditeJarjend[i].taishaalikud(IsenditeJarjend[i].getNimi(), taishaalikud)==haalikuteArv){
										System.out.println(IsenditeJarjend[i].getNimi());
										nimed.add((IsenditeJarjend[i].getNimi()));//lisan sobiva nime listi "nimed"
										muutuja = 1;
									}
								}
								else if (haalikudEi.isSelected()){
									System.out.println(IsenditeJarjend[i].getNimi());
									nimed.add((IsenditeJarjend[i].getNimi()));
									muutuja = 1;
								}
							}
						}
						else if (pikkusEi.isSelected()){
							if (haalikudJah.isSelected()) {
								if(taishaalikuid.getText().equals("")){ // häälikute pikkuse lahtrisse ei sisestatata midagi, viskab erindi 
									throw new erind("Sisestage täishäälikute arv!");	
								}
								try{
								haalikuteArv = Integer.parseInt((taishaalikuid.getText()));
								}
								catch(NumberFormatException x){
									throw new NumberFormatException("Määra nime pikkuseks ikka arv");
								}
								if (IsenditeJarjend[i].taishaalikud(IsenditeJarjend[i].getNimi(),taishaalikud) == haalikuteArv) {
									System.out.println(IsenditeJarjend[i].getNimi());  
									nimed.add((IsenditeJarjend[i].getNimi()));//lisan sobiva nime listi "nimed"
									muutuja = 1;
								}
							}
							else if (haalikudEi.isSelected()) {
								System.out.println(IsenditeJarjend[i].getNimi());
								nimed.add((IsenditeJarjend[i].getNimi()));//lisan sobiva nime listi "nimed"
								muutuja = 1;
							}
						}
					}
				}
				}
				
				if(suguTydruk.isSelected()){
					for (int i = 0; IsenditeJarjend.length >i; i++){
						if(esimeneTaht.getText().equals("")){ // häälikute pikkuse lahtrisse ei sisestatata midagi, viskab erindi 
							throw new erind("Nime esimene täht sisestamata!");	
						}	
						char sisestatudEsitaht1 = esimeneTaht.getText().charAt(0);
						if (Character.isDigit(sisestatudEsitaht1)){
							throw new erind("Esitäht peab olema täht!");
						}
						 sisestatudEsitaht = Character.toUpperCase(sisestatudEsitaht1); //teen esitahe suureks
					if( IsenditeJarjend[i].getSugu().equals("tüdruk") && IsenditeJarjend[i].Esitaht(IsenditeJarjend[i].getNimi())==sisestatudEsitaht) {       
						if (pikkusJah.isSelected()) {
							if(nimePikkus.getText().equals("")){ // nime pikkuse lahtrisse ei sisestatata midagi, viskab erindi 
								throw new erind("Nime pikkus sisestamata!");	
							}
							try{
							pikkus = Integer.parseInt((nimePikkus.getText()));
							}
							catch(NumberFormatException x){
								throw new NumberFormatException("Määra nime pikkuseks ikka arv!");
							}
							if (IsenditeJarjend[i].pikkus(IsenditeJarjend[i].getNimi()) == pikkus) {   
								if (haalikudJah.isSelected()) {
									if(taishaalikuid.getText().equals("")){ // häälikute pikkuse lahtrisse ei sisestatata midagi, viskab erindi 
										throw new erind("Sisestage täishäälikute arv!");	
									}
									try{
									haalikuteArv=Integer.parseInt((taishaalikuid.getText()));
									}
									catch(NumberFormatException x){
										throw new NumberFormatException("Määra nime pikkuseks ikka arv!");
									}
									if (IsenditeJarjend[i].taishaalikud(IsenditeJarjend[i].getNimi(), taishaalikud)==haalikuteArv){
										System.out.println(IsenditeJarjend[i].getNimi());
										nimed.add((IsenditeJarjend[i].getNimi()));
										muutuja = 1;
									}	
								}
								else if (haalikudEi.isSelected()){
									System.out.println(IsenditeJarjend[i].getNimi());
									nimed.add((IsenditeJarjend[i].getNimi()));
									muutuja = 1;
								}
								}
						}
						else if (pikkusEi.isSelected()){
								if (haalikudJah.isSelected()) {
									if(taishaalikuid.getText().equals("")){ // häälikute pikkuse lahtrisse ei sisestatata midagi, viskab erindi 
										throw new erind("Sisestage täishäälikute arv!");	
									}
									try{
									haalikuteArv = Integer.parseInt((taishaalikuid.getText()));
									}
									catch(NumberFormatException x){
										throw new NumberFormatException("Määra nime pikkuseks ikka arv!");
									}
									if (IsenditeJarjend[i].taishaalikud(IsenditeJarjend[i].getNimi(),taishaalikud) == haalikuteArv) {
										System.out.println(IsenditeJarjend[i].getNimi());
										nimed.add((IsenditeJarjend[i].getNimi()));
										muutuja = 1;
									}
								}
								else if (haalikudEi.isSelected()) {
									System.out.println(IsenditeJarjend[i].getNimi());
									nimed.add((IsenditeJarjend[i].getNimi()));
									muutuja = 1;
								}
						}
					}
					}
				}
			}
			String sobivadNimed = "";
			if (muutuja==0){
				sobivadNimed = "Sellist nime ei ole!";
			}
			else{
			for (int i = 0; nimed.size()>i; i++){ //listiga on size, mitte length
				sobivadNimed = sobivadNimed + nimed.get(i) + " " + "\n";
				pw.println(nimed.get(i)); // sobiv nimi kirjutatakse faili
			}
			pw.close();
			}	
			JTextArea textArea = new JTextArea(sobivadNimed);
			textArea.setEditable(false);
			textArea.setOpaque(false);
			JScrollPane scrollPane = new JScrollPane(textArea); 
			scrollPane.setPreferredSize( new Dimension( 200, 500 ) );
			JOptionPane.showMessageDialog( null, scrollPane, "Võimalikud nimed", JOptionPane.PLAIN_MESSAGE);
			
			}
		catch(erind f){
			 System.out.println(f.getMessage());
			 JOptionPane.showMessageDialog( null,  f.getMessage());
		}
			catch(NumberFormatException x){
				JOptionPane.showMessageDialog( null,  x.getMessage());
			}
			catch (FileNotFoundException e2) {
				System.out.println("Faili ei saadud kätte");
			}
			
	}
	}
	}



			
				
				
	
	

	




