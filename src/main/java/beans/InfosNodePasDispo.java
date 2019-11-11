package beans;

import java.util.ArrayList;

public class InfosNodePasDispo {
	private ArrayList<Node> nodeSafePlusProche= new ArrayList<>();
	private Tree tree;
	private Bombe bombeExplosion;
	private int distanceNodeJoueur;
	private int distanceNodePointObjectif;
	private int nbreCoupUtile;
	private float value;
	
	//Getters et Setters
	public ArrayList<Node> getNodeSafePlusProche() {
		return nodeSafePlusProche;
	}
	public void setNodeSafePlusProche(ArrayList<Node> nodeSafePlusProche) {
		this.nodeSafePlusProche = nodeSafePlusProche;
	}
	public Tree getTree() {
		return tree;
	}
	public void setTree(Tree tree) {
		this.tree = tree;
	}
	public Bombe getBombeExplosion() {
		return bombeExplosion;
	}
	public void setBombeExplosion(Bombe bombeExplosion) {
		this.bombeExplosion = bombeExplosion;
	}
	public int getNbreCoupUtile() {
		return nbreCoupUtile;
	}
	public void setNbreCoupUtile(int nbreCoupUtile) {
		this.nbreCoupUtile = nbreCoupUtile;
	}
	
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public int getDistanceNodeJoueur() {
		return distanceNodeJoueur;
	}
	public void setDistanceNodeJoueur(int distanceNodeJoueur) {
		this.distanceNodeJoueur = distanceNodeJoueur;
	}
	public int getDistanceNodePointObjectif() {
		return distanceNodePointObjectif;
	}
	public void setDistanceNodePointObjectif(int distanceNodePointObjectif) {
		this.distanceNodePointObjectif = distanceNodePointObjectif;
	}
	@Override
	public String toString() {
		return "InfosNodePasDispo [nodeSafePlusProche=" + nodeSafePlusProche + ", tree=" + tree + ", bombeExplosion="
				+ bombeExplosion + ", distanceNodeJoueur=" + distanceNodeJoueur + ", distanceNodePointObjectif="
				+ distanceNodePointObjectif + ", nbreCoupUtile=" + nbreCoupUtile + ", value=" + value + "]";
	}
	
	
}
