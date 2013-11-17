package projet.yankiba;



public class Utilisateur {

	protected String pseudo;
	protected int numtel;
	protected int prefixe;
	protected int id_user;

	public Utilisateur(int id_user,int numtel,int prefixe,String pseudo ){
		this.pseudo=pseudo;
		this.numtel=numtel;
		this.prefixe=prefixe;
		this.id_user=id_user;
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

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}



}
