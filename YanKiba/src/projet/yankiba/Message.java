package projet.yankiba;

public class Message {
	protected String contenu;
	protected Utilisateur expediteur;
	protected Utilisateur destinataire;
	protected int id_message;
	
	public Message(){
		
	}
	public Message(int id_message,String contenu,Utilisateur expediteur,Utilisateur destinataire){
		this.contenu=contenu;
		this.expediteur=expediteur;
		this.destinataire=destinataire;
		this.id_message=id_message;
		
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Utilisateur getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(Utilisateur expediteur) {
		this.expediteur = expediteur;
	}

	public Utilisateur getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(Utilisateur destinataire) {
		this.destinataire = destinataire;
	}

	public int getId_message() {
		return id_message;
	}

	public void setId_message(int id_message) {
		this.id_message = id_message;
	}

}
