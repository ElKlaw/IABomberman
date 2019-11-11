package beans;

import java.util.ArrayList;
import java.util.List;

public class RetourTree {
	private List<Node> listeDesNoeudsResultats;
	private int profondeur;
	
	
	
	public RetourTree() {
		super();
		this.listeDesNoeudsResultats = new ArrayList<>();
	}
	public RetourTree(int profondeur) {
		super();
		this.listeDesNoeudsResultats = new ArrayList<>();
		this.profondeur = profondeur;
	}
	//Getters et Setters
	public int getProfondeur() {
		return profondeur;
	}
	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}
	public List<Node> getListeDesNoeudsResultats() {
		return listeDesNoeudsResultats;
	}
	
	
}
