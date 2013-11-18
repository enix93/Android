package projet.yankiba;

public class Message {
	protected String contenu;
	protected Utilisateur expediteur;
	protected Utilisateur destinataire;
	
	public Message(String contenu,Utilisateur expediteur,Utilisateur destinataire){
		this.contenu=contenu;
		this.expediteur=expediteur;
		this.destinataire=destinataire;
		
		
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

}
