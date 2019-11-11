package beans;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private List<Node> nodeParent;
	private Position position;
	private float value;
	private List<Node> listeNodeVoisin;
	private List<Bonus> listeBonusPris;
	private int nbreCoupUtile;
	private float rapport;
	private int nbreTourAvantDispo;
	private InfosNodePasDispo infosNodePasDispo;
		
	// Constructeur
	public Node() {
		super();
		this.listeNodeVoisin = new ArrayList<>();
		this.nodeParent = new ArrayList<>();
		this.listeBonusPris = new ArrayList<>();
		this.infosNodePasDispo = new InfosNodePasDispo();
	}
	
	public Node(float value, Position position) {
		super();
		this.nodeParent = new ArrayList<>();
		this.value = value;
		this.position = position;
		this.listeNodeVoisin = new ArrayList<>();
		this.listeBonusPris = new ArrayList<>();
		this.infosNodePasDispo = new InfosNodePasDispo();
	}
	
	// getters et setters 
	public List<Node> getListNodeParent() {
		return nodeParent;
	}
	public void setListNodeParent(List<Node> nodeParent) {
		this.nodeParent = nodeParent;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public List<Node> getListeNodeVoisin() {
		return listeNodeVoisin;
	}	

	public int getNbreCoupUtile() {
		return nbreCoupUtile;
	}

	public void setNbreCoupUtile(int nbreCoupUtile) {
		this.nbreCoupUtile = nbreCoupUtile;
	}

	public float getRapport() {
		return rapport;
	}

	public void setRapport(float rapport) {
		this.rapport = rapport;
	}

	public List<Bonus> getListeBonusPris() {
		return listeBonusPris;
	}

	public int getNbreTourAvantDispo() {
		return nbreTourAvantDispo;
	}

	public void setNbreTourAvantDispo(int nbreTourAvantDispo) {
		this.nbreTourAvantDispo = nbreTourAvantDispo;
	}

	public InfosNodePasDispo getInfosNodePasDispo() {
		return infosNodePasDispo;
	}

	public void setInfosNodePasDispo(InfosNodePasDispo infosNodePasDispo) {
		this.infosNodePasDispo = infosNodePasDispo;
	}

	// Fonctions utiles
	public void findNeighbors(Contexte contexteSansBombe, Contexte contexteAvecBombe, List<Node> listeNodeParcouru, List<Node> listeNodeParent) {
		float [][] mapSansBombe = contexteSansBombe.getTableauPoints();
		float [][] mapAvecBombe = contexteAvecBombe.getTableauPoints();
		
		//init position des caisses exploser par bombe
		List<Position> listePositionCaisseExploser = new ArrayList<>();
		for(Bombe bombe : contexteAvecBombe.getListeBombe()) {
			listePositionCaisseExploser.addAll(bombe.getListePositionCaisseDetruite(contexteAvecBombe));
		}
		
		
		boolean creerVoisin=true;
		float valueFindNeighbors;
		Node node=null;
		if(this.position.getY()>0) {
			creerVoisin=true;
			Position positionVoisinGauche = this.position.getVoisinGauche();
			if(listePositionCaisseExploser.contains(positionVoisinGauche)) {
				valueFindNeighbors = mapSansBombe[positionVoisinGauche.getX()][positionVoisinGauche.getY()];
			}else {
				valueFindNeighbors = mapAvecBombe[positionVoisinGauche.getX()][positionVoisinGauche.getY()];
			}
			
			if(valueFindNeighbors >=0) {
				if(!(listeNodeParcouru.isEmpty())) {
					for(Node noeudParcouru : listeNodeParcouru) {
						if(noeudParcouru.getPosition().equals(positionVoisinGauche)) {
							creerVoisin=false;
							node = noeudParcouru;
							break;
						}
					}
				}
				if(creerVoisin){
					Node nodeGauche = new Node(valueFindNeighbors, positionVoisinGauche);
					if(listePositionCaisseExploser.contains(positionVoisinGauche)) {
						for(Bombe bombe : contexteAvecBombe.getListeBombe()) {
							if(bombe.getListePositionCaisseDetruite(contexteAvecBombe).contains(positionVoisinGauche)){
								nodeGauche.setNbreTourAvantDispo(bombe.getToursAvantExplosion());
								break;
							}
						}
					}
					nodeGauche.getListNodeParent().add(this);
					nodeGauche.getListeBonusPris().addAll(this.listeBonusPris);
					for(Bonus bonus : contexteAvecBombe.getListeBonus()) {
						if((bonus.getPosition().getX()==nodeGauche.getPosition().getX())&&(bonus.getPosition().getY()==nodeGauche.getPosition().getY())) {
							nodeGauche.getListeBonusPris().add(bonus);
							break;
						}
					}
					this.getListeNodeVoisin().add(nodeGauche);
				}else {
					if((node!=null)&&(!listeNodeParent.contains(node))){
						node.getListNodeParent().add(this);
						node.getListeBonusPris().addAll(this.listeBonusPris);
						if(!this.getListeNodeVoisin().contains(node)) {
							this.getListeNodeVoisin().add(node);
						}
					}
				}
			}
		}
		if(this.position.getY()<(Contexte.TAILLE_MAP-1)) {
			creerVoisin=true;
			Position positionVoisinDroite = this.position.getVoisinDroite();
			if(listePositionCaisseExploser.contains(positionVoisinDroite)) {
				valueFindNeighbors = mapSansBombe[positionVoisinDroite.getX()][positionVoisinDroite.getY()];
			}else {
				valueFindNeighbors = mapAvecBombe[positionVoisinDroite.getX()][positionVoisinDroite.getY()];
			}
			
			if(valueFindNeighbors >=0) {
				if(!(listeNodeParcouru.isEmpty())) {
					for(Node noeudParcouru : listeNodeParcouru) {
						if(noeudParcouru.getPosition().equals(positionVoisinDroite)) {
							creerVoisin=false;
							node = noeudParcouru;
						}
					}
				}
				if(creerVoisin){
					Node nodeDroite = new Node(valueFindNeighbors, positionVoisinDroite);
					if(listePositionCaisseExploser.contains(positionVoisinDroite)) {
						for(Bombe bombe : contexteAvecBombe.getListeBombe()) {
							if(bombe.getListePositionCaisseDetruite(contexteAvecBombe).contains(positionVoisinDroite)){
								nodeDroite.setNbreTourAvantDispo(bombe.getToursAvantExplosion());
								break;
							}
						}
					}
					nodeDroite.getListNodeParent().add(this);
					nodeDroite.getListeBonusPris().addAll(this.listeBonusPris);
					for(Bonus bonus : contexteAvecBombe.getListeBonus()) {
						if((bonus.getPosition().getX()==nodeDroite.getPosition().getX())&&(bonus.getPosition().getY()==nodeDroite.getPosition().getY())) {
							nodeDroite.getListeBonusPris().add(bonus);
							break;
						}
					}
					this.getListeNodeVoisin().add(nodeDroite);
				}else {
					if((node!=null)&&(!listeNodeParent.contains(node))){
						node.getListNodeParent().add(this);
						node.getListeBonusPris().addAll(this.listeBonusPris);
						if(!this.getListeNodeVoisin().contains(node)) {
							this.getListeNodeVoisin().add(node);
						}
					}
				}
			}
		}
		if(this.position.getX()>0) {
			creerVoisin=true;
			Position positionVoisinHaut = this.position.getVoisinHaut();
			if(listePositionCaisseExploser.contains(positionVoisinHaut)) {
				valueFindNeighbors = mapSansBombe[positionVoisinHaut.getX()][positionVoisinHaut.getY()];
			}else {
				valueFindNeighbors = mapAvecBombe[positionVoisinHaut.getX()][positionVoisinHaut.getY()];
			}
			if(valueFindNeighbors >=0) {
				if(!(listeNodeParcouru.isEmpty())) {
					for(Node noeudParcouru : listeNodeParcouru) {
						if(noeudParcouru.getPosition().equals(positionVoisinHaut)) {
							creerVoisin=false;
							node = noeudParcouru;
						}
					}
				}
				if(creerVoisin){
					Node nodeHaut = new Node(valueFindNeighbors, positionVoisinHaut);
					if(listePositionCaisseExploser.contains(positionVoisinHaut)) {
						for(Bombe bombe : contexteAvecBombe.getListeBombe()) {
							if(bombe.getListePositionCaisseDetruite(contexteAvecBombe).contains(positionVoisinHaut)){
								nodeHaut.setNbreTourAvantDispo(bombe.getToursAvantExplosion());
								break;
							}
						}
					}
					nodeHaut.getListNodeParent().add(this);
					nodeHaut.getListeBonusPris().addAll(this.listeBonusPris);
					for(Bonus bonus : contexteAvecBombe.getListeBonus()) {
						if((bonus.getPosition().getX()==nodeHaut.getPosition().getX())&&(bonus.getPosition().getY()==nodeHaut.getPosition().getY())) {
							nodeHaut.getListeBonusPris().add(bonus);
							break;
						}
					}
					this.getListeNodeVoisin().add(nodeHaut);
				}else {
					if((node!=null)&&(!listeNodeParent.contains(node))){
						node.getListNodeParent().add(this);
						node.getListeBonusPris().addAll(this.listeBonusPris);
						if(!this.getListeNodeVoisin().contains(node)) {
							this.getListeNodeVoisin().add(node);
						}
					}
				}
			}
		}
		if(this.position.getX()<(Contexte.TAILLE_MAP-1)) {
			creerVoisin=true;
			Position positionVoisinBas = this.position.getVoisinBas();
			if(listePositionCaisseExploser.contains(positionVoisinBas)) {
				valueFindNeighbors = mapSansBombe[positionVoisinBas.getX()][positionVoisinBas.getY()];
			}else {
				valueFindNeighbors = mapAvecBombe[positionVoisinBas.getX()][positionVoisinBas.getY()];
			}
			if(valueFindNeighbors >=0) {
				if(!(listeNodeParcouru.isEmpty())) {
					for(Node noeudParcouru : listeNodeParcouru) {
						if(noeudParcouru.getPosition().equals(positionVoisinBas)){
							creerVoisin=false;
							node = noeudParcouru;
						}
					}
				}
				if(creerVoisin){
					Node nodeBas = new Node(valueFindNeighbors, positionVoisinBas);
					if(listePositionCaisseExploser.contains(positionVoisinBas)) {
						for(Bombe bombe : contexteAvecBombe.getListeBombe()) {
							if(bombe.getListePositionCaisseDetruite(contexteAvecBombe).contains(positionVoisinBas)){
								nodeBas.setNbreTourAvantDispo(bombe.getToursAvantExplosion());
								break;
							}
						}
					}
					nodeBas.getListNodeParent().add(this);
					nodeBas.getListeBonusPris().addAll(this.listeBonusPris);
					for(Bonus bonus : contexteAvecBombe.getListeBonus()) {
						if((bonus.getPosition().getX()==nodeBas.getPosition().getX())&&(bonus.getPosition().getY()==nodeBas.getPosition().getY())) {
							nodeBas.getListeBonusPris().add(bonus);
							break;
						}
					}
					this.getListeNodeVoisin().add(nodeBas);
				}else {
					if((node!=null)&&(!listeNodeParent.contains(node))){
						node.getListNodeParent().add(this);
						node.getListeBonusPris().addAll(this.listeBonusPris);
						if(!this.getListeNodeVoisin().contains(node)) {
							this.getListeNodeVoisin().add(node);
						}
					}
				}
			}
		}
		
	}
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listeNodeVoisin == null) ? 0 : listeNodeVoisin.hashCode());
		result = prime * result + ((nodeParent == null) ? 0 : nodeParent.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return "Node [position=" + position + ", value=" + value + ", listeBonusPris="
				+ listeBonusPris + ", nbreCoupUtile=" + nbreCoupUtile + ", rapport=" + rapport + ", nbreTourAvantDispo="
				+ nbreTourAvantDispo + "]";
	}

	//Fonctions utiles
	public int getDepth() {
		Node node = this;
		int depth=0;
		while(!node.getListNodeParent().isEmpty()) {
			node=node.getListNodeParent().get(0);
			depth++;
		}
		return depth;
	}
	
	public int getDistanceMini(Position position) {
		int diffX = Math.abs(this.position.getX()-position.getX());
		int diffY = Math.abs(this.position.getY()-position.getY());
		return diffX+diffY;
	}
	
	
}
