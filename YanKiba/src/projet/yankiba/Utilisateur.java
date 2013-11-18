package projet.yankiba;



public class Utilisateur {

	protected String pseudo;
	protected int numtel;
	protected int prefixe;


	public Utilisateur(int numtel,int prefixe,String pseudo ){
		this.pseudo=pseudo;
		this.numtel=numtel;
		this.prefixe=prefixe;
		
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getNumtel() {
		return numtel;
	}

	public void setNumtel(int numtel) {
		this.numtel = numtel;
	}

	public int getPrefixe() {
		return prefixe;
	}

	public void setPrefixe(int prefixe) {
		this.prefixe = prefixe;
	}





}
