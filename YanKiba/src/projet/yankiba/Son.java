package projet.yankiba;

import java.io.File;

public class Son {

	protected int id_son;
	protected String nom_son;
	protected String categorie_son;
	protected File son;
	public Son(){
		
	}
	public int getId_son() {
		return id_son;
	}
	public void setId_son(int id_son) {
		this.id_son = id_son;
	}
	public String getNom_son() {
		return nom_son;
	}
	public void setNom_son(String nom_son) {
		this.nom_son = nom_son;
	}
	public String getCategorie_son() {
		return categorie_son;
	}
	public void setCategorie_son(String categorie_son) {
		this.categorie_son = categorie_son;
	}
	public File getSon() {
		return son;
	}
	public void setSon(File son) {
		this.son = son;
	}
	
	
	
}
