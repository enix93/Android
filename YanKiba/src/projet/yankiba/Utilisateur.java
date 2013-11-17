package projet.yankiba;



public class Utilisateur {

	private String Pseudo;
	private int numtel;
	private int prefixe;
	private int ID_USER;

	public Utilisateur(String Pseudo,int numtel,int prefixe,int ID_USER ){
		this.Pseudo=Pseudo;
		this.numtel=numtel;
		this.prefixe=prefixe;
		this.ID_USER=ID_USER;
	}

	public String getPseudo() {
		return Pseudo;
	}

	public void setPseudo(String pseudo) {
		Pseudo = pseudo;
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

	public int getID_USER() {
		return ID_USER;
	}

	public void setID_USER(int iD_USER) {
		ID_USER = iD_USER;
	}

}
