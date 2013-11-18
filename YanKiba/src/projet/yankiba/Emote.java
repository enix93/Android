package projet.yankiba;

import java.io.File;

public class Emote {
   protected int id_emote;
   protected String nom_emote;
   protected String categorie_emote;
   protected File emote;
   
   public Emote(){
	   
   }
public int getId_emote() {
	return id_emote;
}
public void setId_emote(int id_emote) {
	this.id_emote = id_emote;
}
public String getNom_emote() {
	return nom_emote;
}
public void setNom_emote(String nom_emote) {
	this.nom_emote = nom_emote;
}
public String getCategorie_emote() {
	return categorie_emote;
}
public void setCategorie_emote(String categorie_emote) {
	this.categorie_emote = categorie_emote;
}
public File getEmote() {
	return emote;
}
public void setEmote(File emote) {
	this.emote = emote;
}
   
   
	
}
