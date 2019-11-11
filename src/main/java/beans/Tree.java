package beans;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.logging.Log;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import controller.CombatController;

public class Tree {	
	private Node nodeSource;
	private List<Node> listeDesNoeud = new ArrayList<>();
	private Node nodeDestination;
	private static final Logger LOG = LogManager.getLogger(Tree.class);
	
	// Constructeur
	public Tree() {
		super();
	}

	public Tree(Node nodeSource) {
		super();
		this.nodeSource = nodeSource;
	}
	
	// getters et setters
	public Node getNodeSource() {
		return nodeSource;
	}

	public void setNodeSource(Node nodeSource) {
		this.nodeSource = nodeSource;
	}
	
	public Node getNodeDestination() {
		return nodeDestination;
	}

	public void setNodeDestination(Node nodeDestination) {
		this.nodeDestination = nodeDestination;
	}

	public List<Node> getListeDesNoeud() {
		return listeDesNoeud;
	}

	public Node getBestNode(Contexte contexte, int nbreTourAvantPoserBombe) {
		boolean meilleurNoeudDangereux=false;
		Node nodeResult = null;
		int meilleurNbreCoup=0;
		float meilleurRapport = 0;
		for(Node node : this.listeDesNoeud) {
			meilleurNoeudDangereux=false;
			int nbreCoupUtile = node.getDepth()-nbreTourAvantPoserBombe<1 ? 1 : node.getDepth()-nbreTourAvantPoserBombe+1;			
			node.setNbreCoupUtile(nbreCoupUtile);
			float rapport = (float)node.getValue()/(float)nbreCoupUtile;
			node.setRapport(rapport);
			int nbreTour =  (node.getDepth()>nbreTourAvantPoserBombe) ? node.getDepth() : nbreTourAvantPoserBombe;
			if(rapport>meilleurRapport) {
				if(contexte.getLevel()==3) {
					if(!contexte.PeutPoserBombe(node.getPosition(), nbreTour, node.getDepth()-1)) {
						meilleurNoeudDangereux=true;
					}
					if(!meilleurNoeudDangereux) {
						if(nodeResult==null) {
							meilleurRapport=rapport;
							nodeResult=node;
							meilleurNbreCoup=nbreCoupUtile;						
						}else {
							if(nbreCoupUtile<5) {
								meilleurRapport=rapport;
								nodeResult=node;
								meilleurNbreCoup=nbreCoupUtile;			
							}
						}
					}
				}else {
					if(nodeResult==null) {
						meilleurRapport=rapport;
						nodeResult=node;
						meilleurNbreCoup=nbreCoupUtile;						
					}else {
						if(nbreCoupUtile<5) {
							meilleurRapport=rapport;
							nodeResult=node;
							meilleurNbreCoup=nbreCoupUtile;			
						}
					}
				}
			}else if(rapport==meilleurRapport) {
				if(nbreCoupUtile<meilleurNbreCoup) {
					if(contexte.getLevel()==3) {
						if(!contexte.PeutPoserBombe(node.getPosition(), nbreTour, node.getDepth())) {
							meilleurNoeudDangereux=true;
						}
						if(!meilleurNoeudDangereux) {
							if(nodeResult==null) {
								meilleurRapport=rapport;
								nodeResult=node;
								meilleurNbreCoup=nbreCoupUtile;						
							}else {
								if(nbreCoupUtile<5) {
									meilleurRapport=rapport;
									nodeResult=node;
									meilleurNbreCoup=nbreCoupUtile;			
								}
							}
						}
					}else {
						if(nodeResult==null) {
							meilleurRapport=rapport;
							nodeResult=node;
							meilleurNbreCoup=nbreCoupUtile;						
						}else {
							if(nbreCoupUtile<5) {
								meilleurRapport=rapport;
								nodeResult=node;
								meilleurNbreCoup=nbreCoupUtile;			
							}
						}
					}
				}
			}
		}
		setNodeDestination(nodeResult);
		return nodeResult;
	}
	
	public List<Node> getListBestNode(Contexte contexteSansBombe, Contexte contexte, Position positionJoueur, int nbreTourAvantPoserBombe) {
		List<Node> listeNodeResultat = new ArrayList<>();
		boolean meilleurNoeudDangereux=false;		
		for(Node node : this.listeDesNoeud) {
			if(node.getNbreTourAvantDispo()==0) {
				meilleurNoeudDangereux=false;
				int nbreCoupUtile = node.getDepth()-nbreTourAvantPoserBombe<1 ? 1 : node.getDepth()-nbreTourAvantPoserBombe+1;		
				float rapport = (float)node.getValue()/(float)nbreCoupUtile;
				int nbreTour =  (node.getDepth()>nbreTourAvantPoserBombe) ? node.getDepth() : nbreTourAvantPoserBombe;
				if(rapport>0) {
					node.setNbreCoupUtile(nbreCoupUtile);
					node.setRapport(rapport);
					if(contexte.getLevel()==3) {
						if(!contexte.PeutPoserBombe(node.getPosition(), nbreTour, node.getDepth())) {
							meilleurNoeudDangereux=true;
						}
						if(!meilleurNoeudDangereux) {
							listeNodeResultat.add(node);
						}
					}else {
						listeNodeResultat.add(node);
					}
				}
			}else {
				if(node.getValue()>1) {	
					for(Bombe bombe : contexte.getListeBombe()) {
						if(bombe.getListePointExplosionBombe().contains(node.getPosition())) {
							node.getInfosNodePasDispo().setBombeExplosion(bombe);
							break;
						}
					}
					// Init Liste position Bombe
					List<Position> listePositionExplosionBombe = new ArrayList<>();
					for(Bombe bombe : contexte.getListeBombe()) {
						listePositionExplosionBombe.addAll(bombe.getListePointExplosionBombe());
					}
					//Init Arbre entre Joueur et Position Objectif
					Tree tree = new Tree();
					tree.createTreeEntre2Position(contexteSansBombe, contexte, positionJoueur, node.getPosition(), node.getInfosNodePasDispo().getBombeExplosion().getToursAvantExplosion());
					node.getInfosNodePasDispo().setTree(tree);
					/*LOG.info("-----Noeud Pas Disponible :"+node.toString()+"------------");*/
					for(Node noeudSafe : tree.getListeDesNoeud()) {
						if(!listePositionExplosionBombe.contains(noeudSafe.getPosition())) {
							int nbreCoupUtileJoueurPointSafe = nbreTourAvantPoserBombe>noeudSafe.getDepth() ? 0 : noeudSafe.getDepth()-nbreTourAvantPoserBombe;
							int nbreCoupUtileAttente = nbreTourAvantPoserBombe>noeudSafe.getDepth() ? nbreTourAvantPoserBombe-noeudSafe.getDepth() : 0;
							int nbreCoupUtilePointSafePointObjectif=noeudSafe.getDistanceMini(node.getPosition());
							
							int nbreCoupUtile = nbreCoupUtileJoueurPointSafe+nbreCoupUtileAttente+nbreCoupUtilePointSafePointObjectif+1;
							float rapport = node.getValue()/nbreCoupUtile;
							float rapportActuelle =node.getInfosNodePasDispo().getNbreCoupUtile()>0?node.getInfosNodePasDispo().getValue()/node.getInfosNodePasDispo().getNbreCoupUtile():0;
							if(rapport>rapportActuelle) {
								InfosNodePasDispo infosNodePasDispo = new InfosNodePasDispo();
								infosNodePasDispo.setTree(tree);
								infosNodePasDispo.setNbreCoupUtile(nbreCoupUtile);
								infosNodePasDispo.setValue(node.getValue());
								node.setInfosNodePasDispo(infosNodePasDispo);
								infosNodePasDispo.getNodeSafePlusProche().add(noeudSafe);
								/*LOG.info("Add :"+noeudSafe.toString());
								LOG.info("Rapport Sup :"+infosNodePasDispo.toString());
								LOG.info("---------------------------");*/
							}else if(rapport==rapportActuelle) {
								node.getInfosNodePasDispo().getNodeSafePlusProche().add(noeudSafe);
								/*LOG.info("Add :"+noeudSafe.toString());
								LOG.info("---------------------------");*/
							}
						}
					}
					
					if((node.getInfosNodePasDispo().getValue()>0)&&(!node.getInfosNodePasDispo().getNodeSafePlusProche().isEmpty())) {
						listeNodeResultat.add(node);
					}
				}
			}
		}
		listeNodeResultat.sort(Comparator.comparing(Node::getRapport).reversed());
		return listeNodeResultat;
	}
	
	// Fonctions utiles
	public void createTree(Contexte contexteSansBombe, Contexte contexteAvecBombe, Position positionDepart, int portee) {
		// Init Liste position Bonus
		List<Position> listePositionBonus = new ArrayList<>();
		for(Bonus bonus : contexteSansBombe.getListeBonus()) {
			listePositionBonus.add(bonus.getPosition());
		}
		// init Objet
		float [][] mapAvecBombe = contexteAvecBombe.getTableauPoints();
		
		List<Node> listeNodeCree = new ArrayList<>(100);
		Queue<Node> listeNodeAParcourir = new LinkedList<>();
		List<Node> listeNodeParent = new ArrayList<>(100);
		
		//init node source
		this.nodeSource= new Node(mapAvecBombe[positionDepart.getX()][positionDepart.getY()],positionDepart);
		this.nodeSource.setNbreTourAvantDispo(0);
		
		listeNodeAParcourir.add(nodeSource);
		listeNodeCree.add(nodeSource);
		do {
			Node noeud = listeNodeAParcourir.poll();
			noeud.findNeighbors(contexteSansBombe,contexteAvecBombe,listeNodeCree,listeNodeParent);
			for(Node node : noeud.getListeNodeVoisin()) {
				if(!(listeNodeAParcourir.contains(node))) {
					listeNodeAParcourir.add(node);
				}
				if(!(listeNodeCree.contains(node))) {
					listeNodeCree.add(node);
				}
			}
			if(!(this.listeDesNoeud.contains(noeud))) {
				this.listeDesNoeud.add(noeud);
			}
			if(!(listeNodeCree.contains(noeud))) {
				listeNodeCree.add(noeud);
			}
			//Ajout dans la liste des noeuds parents
			if(!(listeNodeParent.contains(noeud))) {
				listeNodeParent.add(noeud);
			}
		}while(!listeNodeAParcourir.isEmpty());
	}
	
	// Fonctions utiles
	public int createTreeEntre2Position(Contexte contexteSansBombe, Contexte contexte, Position positionDepart, Position positionArrive, int tourDispo) {
		// Init Liste position Bonus
		List<Position> listePositionBonus = new ArrayList<>();
		for(Bonus bonus : contexteSansBombe.getListeBonus()) {
			listePositionBonus.add(bonus.getPosition());
		}
		// init Objet
		float [][] map = contexteSansBombe.getTableauPoints();
		List<Node> listeNodeCree = new ArrayList<>(100);
		Queue<Node> listeNodeAParcourir = new LinkedList<>();
		List<Node> listeNodeParent = new ArrayList<>(100);
		
		this.nodeSource= new Node(map[positionDepart.getX()][positionDepart.getY()],positionDepart);
		listeNodeAParcourir.add(nodeSource);
		listeNodeCree.add(nodeSource);
		
		int profondeur = 50;
		int profondeurActuelle = 0;
		do {
			Node noeud = listeNodeAParcourir.poll();
			noeud.findNeighbors(contexteSansBombe, contexte, listeNodeCree,listeNodeParent);
			profondeurActuelle = noeud.getDepth();
			for(Node node : noeud.getListeNodeVoisin()) {
				if(!(listeNodeAParcourir.contains(node))) {
					listeNodeAParcourir.add(node);
				}
				if(!(listeNodeCree.contains(node))) {
					listeNodeCree.add(node);
				}
			}
			if(!(this.listeDesNoeud.contains(noeud))) {
				this.listeDesNoeud.add(noeud);
			}
			if(!(listeNodeCree.contains(noeud))) {
				listeNodeCree.add(noeud);
			}
			//Ajout dans la liste des noeuds parents
			if(!(listeNodeParent.contains(noeud))) {
				listeNodeParent.add(noeud);
			}
			if(positionArrive.equals(noeud.getPosition())) {
				profondeur = noeud.getDepth();
			}
		}while((!listeNodeAParcourir.isEmpty())&&(profondeurActuelle<=profondeur));
		return profondeur;
	}
}
