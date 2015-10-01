
public class Isik {
	private String nimi;
	private String sugu;
	
	Isik(String nimi, String sugu) {
		super();
		this.nimi = nimi;
		this.sugu = sugu;
	}

	String getNimi() {
		return nimi;
	}
	void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
	void setSugu(String sugu) {
		this.sugu = sugu;
	}

	String getSugu() { 
		return sugu;
	}

	int pikkus(String sone) { //meetod nime pikkuse jaoks
		return sone.length();
	}

	@Override
	public String toString() {
		return "Nimi=" + nimi + ", Sugu=" + sugu;
	}

	public char Esitaht(String nimi){  //siin on oluline ette anda, mis on meetodi tagastustüüp
		return nimi.charAt(0);
	}	
	public int taishaalikud(String nimi, String[] haalikud ){
		  String[] tahedSonas = nimi.split(""); 
		  int summa = 0;
		  for (int j=0; j < tahedSonas.length; j++){
		   for (int i=0; i < haalikud.length; i++){
		   if (tahedSonas[j].equals(haalikud[i])){
		    summa+=1;
		   }
		 }
		}
		  return summa;
		  
		 }
}
