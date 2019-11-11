package beans;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import controller.CombatController;
import utils.Mouvement;

public class Joueur {	
	private static final Logger LOG = LogManager.getLogger(Joueur.class);
	private String id;
	private String nom;
	private int score;
	private boolean alive;
	private Position position;
	private List<Bombe> listeBombe = new ArrayList<>(10);
	private Tree tree = new Tree();
	private int rayonExplosionBombe=1;
	private int nbreBombeMax=1;
	
	public Joueur() {
		super();
	}
	
	public Joueur(JSONObject json, Position position) {
		JSONObject jsonObjectCompetitor = json.getJSONObject("competitor");
		this.id = jsonObjectCompetitor.getString("id");
		this.nom = jsonObjectCompetitor.getString("name");
		this.alive = json.getBoolean("alive");
		this.score = json.getInt("score");
		this.position = position;
	}
	
	//Getters et Setters
	public String getNom() {
		return nom;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}	
	
	public List<Bombe> getListeBombe() {
		return listeBombe;
	}
	
	public Tree getTree() {
		return tree;
	}

	public int getRayonExplosionBombe() {
		return rayonExplosionBombe;
	}

	public int getNbreBombeMax() {
		return nbreBombeMax;
	}

	//FOnction utilitaire
	public void initBonus(int rayon, int nbreBombe) {
		this.rayonExplosionBombe=rayon;
		this.nbreBombeMax = nbreBombe;
	}	
	
	public List<Mouvement> getDeplacementMortel(Contexte contexte){
		List<Mouvement> listeMouvementMortel = new ArrayList<>(Contexte.NBRE_MOUVEMENT_POSSIBLE);
		List<Mouvement> listeMouvementBombe = eviterBombes(contexte);
		//LOG.info("Mouvement après evitement des bombes : "+listeMouvementPossible);
		List<Mouvement> listeMouvementBlocage = eviterBloquage(contexte);
		//LOG.info("Mouvement après evitement des bloquages : "+listeMouvementPossible);
		listeMouvementMortel.addAll(listeMouvementBombe);
		listeMouvementMortel.addAll(listeMouvementBlocage);
		return listeMouvementMortel;
	}
	
	public List<Mouvement> getDeplacementPossible(Contexte contexte){
		//Init Mouvement
		List<Mouvement> listeMouvementPossible = new ArrayList<>(Arrays.asList(Mouvement.values()));
		listeMouvementPossible.remove(Mouvement.BOMB);
		
		listeMouvementPossible = eviterContour(listeMouvementPossible);
		//LOG.info("Mouvement après evitement des contours : "+listeMouvementPossible);
		listeMouvementPossible = eviterCaisses(listeMouvementPossible, contexte);
		//LOG.info("Mouvement après evitement des caisses : "+listeMouvementPossible);
		listeMouvementPossible = eviterJoueur(listeMouvementPossible,contexte);
		//LOG.info("Mouvement après evitement des joueurs : "+listeMouvementPossible);
		
		return listeMouvementPossible;
	}
	
	public List<Mouvement> eviterContour(List<Mouvement> listeMouvementPossible){
		if(position.getX()==0) {
			listeMouvementPossible.remove(Mouvement.MOVE_TOP);
		}else if (position.getX() == 9) {
			listeMouvementPossible.remove(Mouvement.MOVE_BOTTOM);
		}
		
		if(position.getY()==0) {
			listeMouvementPossible.remove(Mouvement.MOVE_LEFT);
		}else if (position.getY() == 9) {
			listeMouvementPossible.remove(Mouvement.MOVE_RIGHT);
		}
		return listeMouvementPossible;
	}
	public void initBombesJoueur(Contexte contexte){
		for(Bombe lBombe : contexte.getListeBombe()) {
			if(this.nom.equals(lBombe.getNomPoseur())) {
				this.listeBombe.add(lBombe);
			}
		}
	}
	
	public boolean peutPoserBombe() {
		boolean peutPoserBombe = false;
		if((this.listeBombe.size()<nbreBombeMax)) {
			peutPoserBombe = true;
		}
		return peutPoserBombe;
	}
	
	public int getNbreDeToursAvantPoserBombe() {
		int nbreTour=0;
		if((this.listeBombe.size()<nbreBombeMax)) {
			nbreTour=0;
		}else {
			nbreTour=3;
			for(Bombe bombe : this.listeBombe) {
				int nbreTourBombe=bombe.getToursAvantExplosion();
				if(nbreTourBombe<nbreTour) {
					nbreTour=nbreTourBombe;
				}
			}
		}
		return nbreTour;
	}
	
	public List<Mouvement> eviterBombes(Contexte contexte){
		List<Mouvement> listeMouvementPossibleApresEviterBombe = new ArrayList<>(Contexte.NBRE_MOUVEMENT_POSSIBLE);
		List<Mouvement> listeMouvement = new ArrayList<>(Arrays.asList(Mouvement.values()));
		listeMouvement.remove(Mouvement.BOMB);
		
		for(Bombe bombe : contexte.getListeBombe()) {
			List<Position> pointExplosionBombe = bombe.getListePointExplosionBombe();
			//LOG.info("Infos Bombe" + bombe.toString() +" / Point :" +pointExplosionBombe.toString());
			for(Mouvement mouvementPossible : listeMouvement){
				Position positionProchainTour = new Position();
				switch (mouvementPossible.getKey()) {
				case "MOVE_RIGHT":
					positionProchainTour.setLocation(getPosition().getX(),getPosition().getY()+1);
					if(bombe.getToursAvantExplosion()>1) {
						if(positionProchainTour.equals(bombe.getPosition()) ){
							listeMouvementPossibleApresEviterBombe.add(Mouvement.MOVE_RIGHT);
						}
					}else {
						if(pointExplosionBombe.contains(positionProchainTour)) {
							listeMouvementPossibleApresEviterBombe.add(Mouvement.MOVE_RIGHT);
						}
					}
					break;
				case "MOVE_LEFT":
					positionProchainTour.setLocation(getPosition().getX(),getPosition().getY()-1);
					if(bombe.getToursAvantExplosion()>1) {
						if(positionProchainTour.equals(bombe.getPosition())) {
							listeMouvementPossibleApresEviterBombe.add(Mouvement.MOVE_LEFT);
						}
					}else {
						if(pointExplosionBombe.contains(positionProchainTour)) {
							listeMouvementPossibleApresEviterBombe.add(Mouvement.MOVE_LEFT);
						}
					}
					break;
				case "MOVE_TOP":
					positionProchainTour.setLocation(getPosition().getX()-1,getPosition().getY());
					if(bombe.getToursAvantExplosion()>1) {
						if(positionProchainTour.equals(bombe.getPosition())) {
							listeMouvementPossibleApresEviterBombe.add(Mouvement.MOVE_TOP);
						}
					}else {
						if(pointExplosionBombe.contains(positionProchainTour)) {
							listeMouvementPossibleApresEviterBombe.add(Mouvement.MOVE_TOP);
						}
					}
					break;
				case "MOVE_BOTTOM":
					positionProchainTour.setLocation(getPosition().getX()+1,getPosition().getY());
					if(bombe.getToursAvantExplosion()>1) {
						if(positionProchainTour.equals(bombe.getPosition())) {
							listeMouvementPossibleApresEviterBombe.add(Mouvement.MOVE_BOTTOM);
						}
					}else {
						if(pointExplosionBombe.contains(positionProchainTour)) {
							listeMouvementPossibleApresEviterBombe.add(Mouvement.MOVE_BOTTOM);
						}
					}
					break;
				case "STAY":
					positionProchainTour.setLocation(getPosition().getX(),getPosition().getY());
					if(bombe.getToursAvantExplosion()>1) {
						if(positionProchainTour.equals(bombe.getPosition())) {
							listeMouvementPossibleApresEviterBombe.add(Mouvement.STAY);
						}
					}else {
						if(pointExplosionBombe.contains(positionProchainTour)) {
							listeMouvementPossibleApresEviterBombe.add(Mouvement.STAY);
						}
					}
					break;
				default:
					break;
				}
			}
		}
		return listeMouvementPossibleApresEviterBombe;
	}
	
	public boolean poserBombeFaitPoint(Contexte contexte) {
		Bombe bombe = new Bombe();
		bombe.setRayon(rayonExplosionBombe);
		bombe.setPosition(new Position(position.getX(),position.getY()));
		List<Position> listePointExplosionBombe = bombe.getListePointExplosionBombe();
		for(Caisse caisse : contexte.getListeCaisse()) {
			if(listePointExplosionBombe.contains(caisse.getPosition())) {
				if(contexte.getListeBombe().isEmpty()) {
					return true;
				}else {
					for(Bombe instanceBombe : contexte.getListeBombe()) {
						List<Position> listePointExplosionInstanceBombe = instanceBombe.getListePointExplosionBombe();
						if(!(listePointExplosionInstanceBombe.contains(caisse.getPosition()))) {
							return true;
						}
					}
				}
			}
		}
		if(contexte.getLevel()==3) {
			for(Joueur joueur : contexte.getListeJoueur()) {
				if((joueur.alive)&&(joueur.estDansCoin())&&(!(joueur.nom.equals("CIA")))&&(listePointExplosionBombe.contains(joueur.getPosition()))){
					return true;
				}
			}
		}
		return false;
	}
	
	public List<Mouvement> eviterCaisses(List<Mouvement> listeMouvementPossible, Contexte contexte){
		List<Mouvement> listeMouvementPossibleApresEviterCaisse = new ArrayList<>(Contexte.NBRE_MOUVEMENT_POSSIBLE);
		float tableauPoint[][] = contexte.getTableauPoints();
		listeMouvementPossibleApresEviterCaisse.addAll(listeMouvementPossible);
		for(Mouvement mouvementPossible : listeMouvementPossible){
			Position positionProchainTour = new Position();
			switch (mouvementPossible.getKey()) {
				case "MOVE_RIGHT":
					positionProchainTour.setLocation(getPosition().getX(),getPosition().getY()+1);
					if(tableauPoint[positionProchainTour.getX()][positionProchainTour.getY()]==-1) {
						listeMouvementPossibleApresEviterCaisse.remove(Mouvement.MOVE_RIGHT);
					}	
					break;
				case "MOVE_LEFT":
					positionProchainTour.setLocation(getPosition().getX(),getPosition().getY()-1);
					if(tableauPoint[positionProchainTour.getX()][positionProchainTour.getY()]==-1) {
						listeMouvementPossibleApresEviterCaisse.remove(Mouvement.MOVE_LEFT);
					}
					break;
				case "MOVE_TOP":
					positionProchainTour.setLocation(getPosition().getX()-1,getPosition().getY());
					if(tableauPoint[positionProchainTour.getX()][positionProchainTour.getY()]==-1) {
						listeMouvementPossibleApresEviterCaisse.remove(Mouvement.MOVE_TOP);
					}
					break;
				case "MOVE_BOTTOM":
					positionProchainTour.setLocation(getPosition().getX()+1,getPosition().getY());
					if(tableauPoint[positionProchainTour.getX()][positionProchainTour.getY()]==-1) {
						listeMouvementPossibleApresEviterCaisse.remove(Mouvement.MOVE_BOTTOM);
					}
					break;
				default:
					break;
			}
		}
		return listeMouvementPossibleApresEviterCaisse;		
	}
	
	
	public List<Mouvement> allerJusquauPoint(Node nodeArrive, Contexte contexte){
		List<Mouvement> listeMouvementPossible = new ArrayList<>();
		if(nodeArrive.getPosition().equals(this.position)) {
			listeMouvementPossible.add(Mouvement.STAY);
		}else {
			List<Node> listNodePrecedent = new ArrayList<>();
			listNodePrecedent.add(nodeArrive);
			Node nodePrecedent = nodeArrive;
			while((nodePrecedent.getListNodeParent().get(0).getPosition().getX()!=this.position.getX())||(nodePrecedent.getListNodeParent().get(0).getPosition().getY()!=this.position.getY())) {
				listNodePrecedent = nodePrecedent.getListNodeParent();
				nodePrecedent =listNodePrecedent.get(0);
			};
			for(Node node : listNodePrecedent) {
				listeMouvementPossible.add(this.position.getMouvementVersPoint(node.getPosition()));
			}
			LOG.info("MOUVEMENT POSSIBLE CALCULE : "+listeMouvementPossible.toString());
		}
		
		// Eviter les joueurs
		List<Mouvement> listeMouvementVersJoueurPossible = new ArrayList<>();
		List<Position> listePositionJoueurPossible = new ArrayList<>();
		// Calcul des positions des joueurs possibles
		for(Joueur joueurAdverse : contexte.getListeJoueur()) {
			if(!"CIA".equals(joueurAdverse.getNom())) {
				Position positionJoueurAdverse = joueurAdverse.getPosition();
				// Ajout 5 positions possible 
				listePositionJoueurPossible.add(positionJoueurAdverse);
				listePositionJoueurPossible.add(positionJoueurAdverse.getVoisinBas());
				listePositionJoueurPossible.add(positionJoueurAdverse.getVoisinDroite());
				listePositionJoueurPossible.add(positionJoueurAdverse.getVoisinGauche());
				listePositionJoueurPossible.add(positionJoueurAdverse.getVoisinHaut());
			}
		}
		if(listePositionJoueurPossible.contains(this.position.getVoisinBas())) {
			listeMouvementVersJoueurPossible.add(Mouvement.MOVE_BOTTOM);
		}
		if(listePositionJoueurPossible.contains(this.position.getVoisinHaut())) {
			listeMouvementVersJoueurPossible.add(Mouvement.MOVE_TOP);
		}	
		if(listePositionJoueurPossible.contains(this.position.getVoisinGauche())) {
			listeMouvementVersJoueurPossible.add(Mouvement.MOVE_LEFT);
		}
		if(listePositionJoueurPossible.contains(this.position.getVoisinDroite())) {
			listeMouvementVersJoueurPossible.add(Mouvement.MOVE_RIGHT);
		}
		LOG.info("MOUVEMENT Dangereux joueur : "+listeMouvementVersJoueurPossible.toString());
		List<Mouvement> listeMouvement = new ArrayList<>();
		listeMouvement.addAll(listeMouvementPossible);
		listeMouvement.removeAll(listeMouvementVersJoueurPossible);
		LOG.info("MOUVEMENT choisi pour eviter joueur : "+listeMouvement.toString());
		if(listeMouvement.isEmpty()) {
			return listeMouvementPossible;
		}else {
			return listeMouvement;
		}
	}
	
	public List<Mouvement> allerJusquauPointSimple(Position pointArrive){
		List<Mouvement> listeMouvementPossible = new ArrayList<>();
		if(this.position.getY()>pointArrive.getY()) {
			listeMouvementPossible.add(Mouvement.MOVE_LEFT);
		}else if(this.position.getY()<pointArrive.getY()){
			listeMouvementPossible.add(Mouvement.MOVE_RIGHT);
		}
		if(this.position.getX()>pointArrive.getX()) {
			listeMouvementPossible.add(Mouvement.MOVE_TOP);
		}else if(this.position.getX()<pointArrive.getX()) {
			listeMouvementPossible.add(Mouvement.MOVE_BOTTOM);
		}
		return listeMouvementPossible;
	}
	
	/*public Mouvement allerJusquauNode(Node nodeObjectif){
		Mouvement mouvementPlusRapide = Mouvement.STAY;
		while(nodeObjectif.getNodeParent()!=this.tree.getNodeSource()) {
			this.tree.getNodeSource().getPosition().getMouvementVersPoint(nodeObjectif.getPosition());
		}
		return mouvementPlusRapide;
	}*/
	
	public int nbreObjetBloquantAutour(Position position, Contexte contexte) {
		float [][] tableau = contexte.getTableauPoints();
		int nbreObjetBloquantAutour = 0;
		// init point autour du point
		Position pointDroite = new Position(position);
		pointDroite.addToY(1);
		Position pointGauche= new Position(position);
		pointGauche.addToY(-1);
		Position pointHaut= new Position(position);
		pointHaut.addToX(-1);
		Position pointBas=new Position(position);
		pointBas.addToX(1);
		//Bord de la map
		if((position.getX()==0)||(position.getX()==9)) {
			nbreObjetBloquantAutour++;
		}
		if((position.getY()==0)||(position.getY()==9)) {
			nbreObjetBloquantAutour++;
		}
		// Vérification des caisse
		if((pointBas.getX()<=9)&&(pointBas.getX()>=0)&&(pointBas.getY()>=0)&&(pointBas.getY()<=9)&&(tableau[pointBas.getX()][pointBas.getY()]==-1)) {
			nbreObjetBloquantAutour++;
		}
		if((pointHaut.getX()<=9)&&(pointHaut.getX()>=0)&&(pointHaut.getY()>=0)&&(pointHaut.getY()<=9)&&(tableau[pointHaut.getX()][pointHaut.getY()]==-1)) {
			nbreObjetBloquantAutour++;
		}
		if((pointDroite.getX()<=9)&&(pointDroite.getX()>=0)&&(pointDroite.getY()>=0)&&(pointDroite.getY()<=9)&&(tableau[pointDroite.getX()][pointDroite.getY()]==-1)) {
			nbreObjetBloquantAutour++;
		}
		if((pointGauche.getX()<=9)&&(pointGauche.getX()>=0)&&(pointGauche.getY()>=0)&&(pointGauche.getY()<=9)&&(tableau[pointGauche.getX()][pointGauche.getY()]==-1)) {
			nbreObjetBloquantAutour++;
		}
		
		// Verification joueur dans les coins
		for(Joueur joueur : contexte.getListeJoueur()) {
			if((joueur.isAlive())&&(joueur.estDansCoin())&&(!("CIA".equals(joueur.nom)))) {
				if((pointDroite.equals(joueur.getPosition()))||
						(pointGauche.equals(joueur.getPosition()))||
						(pointHaut.equals(joueur.getPosition()))||
						(pointBas.equals(joueur.getPosition()))){
					nbreObjetBloquantAutour++;
				}
			}
		}		
		//Bombe explosant autour
		for(Bombe bombe : contexte.getListeBombe()) {
			List<Position> pointExplosionBombe = bombe.getListePointExplosionBombe();
			if((bombe.getToursAvantExplosion()==3)&&(nbreObjetBloquantAutour==3)) {
				if((pointDroite.equals(bombe.getPosition()))||
						(pointGauche.equals(bombe.getPosition()))||
						(pointHaut.equals(bombe.getPosition()))||
						(pointBas.equals(bombe.getPosition()))){
					nbreObjetBloquantAutour++;
				}
			}
			if(bombe.getToursAvantExplosion()==2) {
				if((pointDroite.equals(bombe.getPosition()))||
						(pointGauche.equals(bombe.getPosition()))||
						(pointHaut.equals(bombe.getPosition()))||
						(pointBas.equals(bombe.getPosition()))){
					nbreObjetBloquantAutour++;
				}
			}else if (bombe.getToursAvantExplosion()==1) {
				if((pointExplosionBombe.contains(pointBas))||
						(pointExplosionBombe.contains(pointDroite)) ||
						(pointExplosionBombe.contains(pointGauche)) ||
						(pointExplosionBombe.contains(pointHaut)) ) {
					nbreObjetBloquantAutour++;
				}
			}
		}
		
		return nbreObjetBloquantAutour;
	}
		
	public List<Mouvement> eviterBloquage(Contexte contexte){
		// cas bloquage par caisse pour éviter bombe 
		List<Mouvement> listeMouvementPossibleApresEviterBloquage = new ArrayList<>(Contexte.NBRE_MOUVEMENT_POSSIBLE);
		List<Position> listePointMortel = contexte.getListePointExplosion();
		List<Mouvement> listeMouvement= new ArrayList<>(Arrays.asList(Mouvement.values()));
		for(Mouvement mouvementPossible : listeMouvement){
			Position positionProchainTour = new Position();
			switch (mouvementPossible.getKey()) {
			case "MOVE_RIGHT":
				positionProchainTour.setLocation(getPosition().getX(),getPosition().getY()+1);		
				if((nbreObjetBloquantAutour(positionProchainTour, contexte)>=4)&&(listePointMortel.contains(positionProchainTour))) {
					listeMouvementPossibleApresEviterBloquage.add(Mouvement.MOVE_RIGHT);
				}
				break;
			case "MOVE_LEFT":
				positionProchainTour.setLocation(getPosition().getX(),getPosition().getY()-1);
				if((nbreObjetBloquantAutour(positionProchainTour, contexte)>=4)&&(listePointMortel.contains(positionProchainTour))){
					listeMouvementPossibleApresEviterBloquage.add(Mouvement.MOVE_LEFT);
				}
				break;
			case "MOVE_TOP":
				positionProchainTour.setLocation(getPosition().getX()-1,getPosition().getY());
				if((nbreObjetBloquantAutour(positionProchainTour, contexte)>=4)&&(listePointMortel.contains(positionProchainTour))){
					listeMouvementPossibleApresEviterBloquage.add(Mouvement.MOVE_TOP);
				}
				break;
			case "MOVE_BOTTOM":
				positionProchainTour.setLocation(getPosition().getX()+1,getPosition().getY());
				if((nbreObjetBloquantAutour(positionProchainTour, contexte)>=4) &&(listePointMortel.contains(positionProchainTour))){
					listeMouvementPossibleApresEviterBloquage.add(Mouvement.MOVE_BOTTOM);
				}
				break;
			case "STAY":
				positionProchainTour.setLocation(getPosition().getX(),getPosition().getY());
				if((nbreObjetBloquantAutour(positionProchainTour, contexte)>=4)&&(listePointMortel.contains(positionProchainTour))) {
					listeMouvementPossibleApresEviterBloquage.add(Mouvement.STAY);
				}
				break;
			default:
				break;
			}
		}
		return listeMouvementPossibleApresEviterBloquage;
	}
	
	public List<Mouvement> eviterJoueur(List<Mouvement> listeMouvementPossible, Contexte contexte){
		List<Mouvement> listeMouvementPossibleApresEviterJoueur = new ArrayList<>(Contexte.NBRE_MOUVEMENT_POSSIBLE);
		for(Mouvement mouvementPossible : listeMouvementPossible){
			listeMouvementPossibleApresEviterJoueur.add(mouvementPossible);
		}
		for(Mouvement mouvementPossible : listeMouvementPossible){
			Position positionProchainTour = new Position();
			switch (mouvementPossible.getKey()) {
			case "MOVE_RIGHT":
				positionProchainTour.setLocation(getPosition().getX(),getPosition().getY()+1);
				for(Joueur joueur : contexte.getListeJoueur()) {
					if((joueur.getPosition().equals(positionProchainTour))&&(joueur.estDansCoin())&&(joueur.isAlive())) {
						listeMouvementPossibleApresEviterJoueur.remove(Mouvement.MOVE_RIGHT);
						break;
					}
				}				
				break;
			case "MOVE_LEFT":
				positionProchainTour.setLocation(getPosition().getX(),getPosition().getY()-1);
				for(Joueur joueur : contexte.getListeJoueur()) {
					if((joueur.getPosition().equals(positionProchainTour))&&(joueur.estDansCoin())&&(joueur.isAlive())) {
						listeMouvementPossibleApresEviterJoueur.remove(Mouvement.MOVE_LEFT);
						break;
					}
				}	
				break;
			case "MOVE_TOP":
				positionProchainTour.setLocation(getPosition().getX()-1,getPosition().getY());
				for(Joueur joueur : contexte.getListeJoueur()) {
					if((joueur.getPosition().equals(positionProchainTour))&&(joueur.estDansCoin())&&(joueur.isAlive())) {
						listeMouvementPossibleApresEviterJoueur.remove(Mouvement.MOVE_TOP);
						break;
					}
				}	
				break;
			case "MOVE_BOTTOM":
				positionProchainTour.setLocation(getPosition().getX()+1,getPosition().getY());
				for(Joueur joueur : contexte.getListeJoueur()) {
					if((joueur.getPosition().equals(positionProchainTour))&&(joueur.estDansCoin())&&(joueur.isAlive())) {
						listeMouvementPossibleApresEviterJoueur.remove(Mouvement.MOVE_BOTTOM);
						break;
					}
				}	
				break;
			case "STAY":
				break;
			default:
				break;
			}
		}
		return listeMouvementPossibleApresEviterJoueur;
	}
	
	public boolean estDansCoin() {
		boolean reponse = false;
		if((this.position.equals(new Position(0, 0)))||(this.position.equals(new Position(9, 0)))||(this.position.equals(new Position(0, 9)))||(this.position.equals(new Position(9, 9)))) {
			reponse = true;
		}		
		return reponse;
	}
	
	public int prisBonus(Contexte contexte, Mouvement mouvementChoisi) {
		Position positionProchainTour = new Position();
		switch (mouvementChoisi.getKey()) {
			case "MOVE_RIGHT":
				positionProchainTour.setLocation(getPosition().getX(),getPosition().getY()+1);			
				break;
			case "MOVE_LEFT":
				positionProchainTour.setLocation(getPosition().getX(),getPosition().getY()-1);
				break;
			case "MOVE_TOP":
				positionProchainTour.setLocation(getPosition().getX()-1,getPosition().getY());
				break;
			case "MOVE_BOTTOM":
				positionProchainTour.setLocation(getPosition().getX()+1,getPosition().getY());
				break;
			case "STAY":
				break;
			default:
				break;
		}
		for(Bonus bonus : contexte.getListeBonus()) {
			if(bonus.getPosition().equals(positionProchainTour)) {
				return bonus.getTypeBonus();
			}
		}		
		return 0;
	}
		
	public Node getPositionLaPlusInterresante(Contexte contexte) {
		float meilleurRapport = 0;
		Node meilleurNoeud = null;
		Node meilleurSecondNoeud=null;
		List<Position> listeMeilleurCaisseExploser= new ArrayList<>();
		contexte.ajoutPointBonus(this.rayonExplosionBombe);
		contexte.ajoutPointBonusCaisse(this.rayonExplosionBombe);
		contexte.ajoutPointBombe();
		
		//Contexte Sans Bombe
		Contexte contexteSansBombe = contexte.initApresExplosion(this.rayonExplosionBombe);
		
		this.tree.createTree(contexteSansBombe, contexte, this.position, this.rayonExplosionBombe);
		float [][] tableauPoint = contexte.getTableauPoints();
		LOG.info("------------------Tableau Bonus------------------------------");
		for(int f=0; f< tableauPoint.length;f++) {
			LOG.info(Arrays.toString(tableauPoint[f]));
		}
		LOG.info("-------------------------------------------------------");
		
		int nbreTour = getNbreDeToursAvantPoserBombe();
		List<Node> listMeilleurNoeud = this.tree.getListBestNode(contexteSansBombe, contexte, this.position, nbreTour);
		LOG.info("------------------Calcul 2 eme point ------------------- ------------------------------");
		for(Node node : listMeilleurNoeud) {
			int bonusRayonPrisTour1 = 0;
			int bonusBombePrisTour1 = 0;
			List<Bonus> listeBonus = new ArrayList<>();
			listeBonus.addAll(contexte.getListeBonus());
			for(Bonus bonus : node.getListeBonusPris()) {
				if(bonus.isDisponibleSansCasserCaisse()) {
					if(bonus.getTypeBonus()==2) {
						bonusRayonPrisTour1++;
						listeBonus.remove(bonus);
		    		}else if(bonus.getTypeBonus()==1) {
		    			bonusBombePrisTour1++;
		    			listeBonus.remove(bonus);
		    		}
				}
			}
			//Clone contexte
			Contexte contexteProchainTour = new Contexte(contexte);
			
			List<Bombe> listeBombeContexteProchainTour = new ArrayList<>();
			Bombe bombeTourPrecedent = new Bombe();
			bombeTourPrecedent.setNomPoseur(this.nom);
			bombeTourPrecedent.setRayon(rayonExplosionBombe+bonusRayonPrisTour1);
			bombeTourPrecedent.setToursAvantExplosion(3);
			bombeTourPrecedent.setPosition(node.getPosition());
			List<Position> listePositionCaisseExploser = new ArrayList<>();
			listePositionCaisseExploser.addAll(bombeTourPrecedent.getListePositionCaisseDetruite(contexte));
			contexteProchainTour.enleverPointBombeAPoser(bombeTourPrecedent);
			listeBombeContexteProchainTour.add(bombeTourPrecedent);
			
			
			for(Bombe bombe : contexteProchainTour.getListeBombe()) {
				if(bombe.getToursAvantExplosion()<=node.getDepth()+1) {
					// La bombe a exploser
					contexteProchainTour.retirerCaisse(bombe);
				}else {
					bombe.setToursAvantExplosion(bombe.getToursAvantExplosion()-node.getDepth()+1);
					listeBombeContexteProchainTour.add(bombe);
				}
			}			
			//Mise à jour de la liste des bombes
			contexteProchainTour.getListeBombe().clear();
			contexteProchainTour.getListeBombe().addAll(listeBombeContexteProchainTour);
			//Mise à jour des bonus
			contexteProchainTour.getListeBonus().clear();
			contexteProchainTour.getListeBonus().addAll(listeBonus);
			
			List<Bombe> listeBombeJoueurProchainTour = contexteProchainTour.getBombeJoueur(CombatController.NAME_JOUEUR);
			
			// Calcul nbre tour avant prochaine bombe 
			int nbreTourAvantProchaineBombe=3;
			if(listeBombeJoueurProchainTour.size()<(nbreBombeMax+bonusBombePrisTour1)) {
				nbreTourAvantProchaineBombe=0;
			}else {
				nbreTourAvantProchaineBombe=3;
				for(Bombe bombe : listeBombeJoueurProchainTour) {
					int nbreTourBombe=bombe.getToursAvantExplosion();
					if(nbreTourBombe<nbreTourAvantProchaineBombe) {
						nbreTourAvantProchaineBombe=nbreTourBombe;
					}
				}
			}
			// MAJ du contexte en fonction de la bombe posé 
			contexteProchainTour.MajContexte(rayonExplosionBombe+bonusRayonPrisTour1);
			//Contexte Sans Bombe
			Contexte contexteSansBombeProchainTour = contexte.initApresExplosion(this.rayonExplosionBombe);
			
			Tree treeProchainTour = new Tree();
			treeProchainTour.createTree(contexteSansBombeProchainTour, contexteProchainTour, node.getPosition(),rayonExplosionBombe+bonusRayonPrisTour1);
			contexteProchainTour.ajoutPointBonus(treeProchainTour,rayonExplosionBombe+bonusRayonPrisTour1);		
			/*float [][] tableauPointProchainTour = contexteProchainTour.getTableauPoints();
			LOG.info("------------------Tableau Prochain Tour ------------------------------");
			for(int f=0; f< tableauPointProchainTour.length;f++) {
				LOG.info(Arrays.toString(tableauPointProchainTour[f]));
			}
			LOG.info("----------------------------------------------------------------------");*/
			Node nodeResultSecondTour = treeProchainTour.getBestNode(contexteProchainTour, nbreTourAvantProchaineBombe);
			
			LOG.info("nbre tour avant prochaine bombe : "+nbreTourAvantProchaineBombe);
			LOG.info("1er noeud : "+node.toString());
			float ratioTotal= 0;
			if(nodeResultSecondTour!=null) {
				Bombe bombeTourActuel = new Bombe();
				bombeTourActuel.setNomPoseur(this.nom);
				bombeTourActuel.setRayon(rayonExplosionBombe);
				bombeTourActuel.setToursAvantExplosion(3);
				bombeTourActuel.setPosition(nodeResultSecondTour.getPosition());
				listePositionCaisseExploser.addAll(bombeTourActuel.getListePositionCaisseDetruite(contexteProchainTour));
				LOG.info("2ème noeud :"+ nodeResultSecondTour.toString());
				MathContext mc = new MathContext(3,RoundingMode.HALF_UP);
				float nbreTotalCoupUtile = 1;
				if(node.getNbreTourAvantDispo()>0) {
					nbreTotalCoupUtile = new BigDecimal(nodeResultSecondTour.getNbreCoupUtile()+node.getInfosNodePasDispo().getNbreCoupUtile(),mc).floatValue();
				}else {
					nbreTotalCoupUtile = new BigDecimal(nodeResultSecondTour.getNbreCoupUtile()+node.getNbreCoupUtile(),mc).floatValue();
				}
				float valueTotal =  new BigDecimal(nodeResultSecondTour.getValue()+node.getValue(),mc).floatValue();
				
				ratioTotal = valueTotal<=0 ? 0 : valueTotal/nbreTotalCoupUtile;
				
				if((listePositionCaisseExploser.containsAll(listeMeilleurCaisseExploser))&&(listePositionCaisseExploser.size()>listeMeilleurCaisseExploser.size())) {
					listeMeilleurCaisseExploser = new ArrayList<>();
					listeMeilleurCaisseExploser.addAll(bombeTourPrecedent.getListePositionCaisseDetruite(contexte));
					listeMeilleurCaisseExploser.addAll(bombeTourActuel.getListePositionCaisseDetruite(contexteProchainTour));
					meilleurNoeud = node;
					meilleurSecondNoeud=nodeResultSecondTour;
					meilleurRapport=ratioTotal;
				}else {
					if(!listeMeilleurCaisseExploser.containsAll(listePositionCaisseExploser)) {
						if(ratioTotal>meilleurRapport) {
							listeMeilleurCaisseExploser = new ArrayList<>();
							listeMeilleurCaisseExploser.addAll(bombeTourPrecedent.getListePositionCaisseDetruite(contexte));
							listeMeilleurCaisseExploser.addAll(bombeTourActuel.getListePositionCaisseDetruite(contexteProchainTour));
							meilleurNoeud = node;
							meilleurSecondNoeud=nodeResultSecondTour;
							meilleurRapport=ratioTotal;
						}else if(ratioTotal==meilleurRapport) {
							if(node.getValue()>meilleurNoeud.getValue()) {
								listeMeilleurCaisseExploser = new ArrayList<>();
								listeMeilleurCaisseExploser.addAll(bombeTourPrecedent.getListePositionCaisseDetruite(contexte));
								listeMeilleurCaisseExploser.addAll(bombeTourActuel.getListePositionCaisseDetruite(contexteProchainTour));
								meilleurNoeud = node;
								meilleurSecondNoeud=nodeResultSecondTour;
								meilleurRapport=ratioTotal;
							}else if(node.getValue()==meilleurNoeud.getValue()) {
								if(node.getNbreCoupUtile()<meilleurNoeud.getNbreCoupUtile()) {
									listeMeilleurCaisseExploser = new ArrayList<>();
									listeMeilleurCaisseExploser.addAll(bombeTourPrecedent.getListePositionCaisseDetruite(contexte));
									listeMeilleurCaisseExploser.addAll(bombeTourActuel.getListePositionCaisseDetruite(contexteProchainTour));
									meilleurNoeud = node;
									meilleurSecondNoeud=nodeResultSecondTour;
									meilleurRapport=ratioTotal;
								}
							}
						}
					}
				}
			}else {
				LOG.info("2ème noeud : null");
				ratioTotal = node.getRapport();
				if(ratioTotal>meilleurRapport) {
					listeMeilleurCaisseExploser = new ArrayList<>();
					listeMeilleurCaisseExploser.addAll(bombeTourPrecedent.getListePositionCaisseDetruite(contexte));
					meilleurNoeud = node;
					meilleurSecondNoeud=nodeResultSecondTour;
					meilleurRapport=node.getRapport();
				}else if(ratioTotal==meilleurRapport) {
					if(node.getNbreCoupUtile()<meilleurNoeud.getNbreCoupUtile()) {
						listeMeilleurCaisseExploser = new ArrayList<>();
						listeMeilleurCaisseExploser.addAll(bombeTourPrecedent.getListePositionCaisseDetruite(contexte));
						meilleurNoeud = node;
						meilleurSecondNoeud=nodeResultSecondTour;
						meilleurRapport=node.getRapport();
					}
				}
			}
			LOG.info("ratio :"+ ratioTotal);
		}
		LOG.info("------------------ Fin Calcul 2 eme point ------------------- ------------------------------");
		LOG.info("------------------------ CHOIX -------------------------------------------------------------");
		if(meilleurNoeud!=null) {
			LOG.info("meilleur 1er noeud : "+meilleurNoeud.toString());
		}else {
			LOG.info("meilleur 1er noeud : null ");
		}
		if(meilleurSecondNoeud!=null) {
			LOG.info("meilleur second noeud : "+meilleurSecondNoeud.toString());
		}else {
			LOG.info("meilleur second noeud : null ");
		}
		LOG.info("meilleur rapport: "+meilleurRapport);
		LOG.info("------------------------ Fin CHOIX ---------------------------------------------------------");
		return meilleurNoeud;
	}
	
	public boolean getJoueurStatic(HistoriqueJoueur historique) {
		Position positionPrecedente = historique.getPositionPrecedente();
		if(positionPrecedente!=null) {
			if( (this.position.getX()==positionPrecedente.getX())&&(this.position.getY()==positionPrecedente.getY())) {
				historique.setNbreToursStatic(historique.getNbreToursStatic() +1);
			}else {
				historique.setNbreToursStatic(0);
			}
		}
		if(historique.getNbreToursStatic()>=3) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public Mouvement maximiserPriseBonus(Contexte contexte, List<Mouvement> listeMouvementPossible) {
		SecureRandom random = new SecureRandom();
		List<Mouvement> listeMouvementAvecBonus = new ArrayList<>();
		for(Mouvement mouvement : listeMouvementPossible) {
			Position positionProchainTour = new Position();
			switch (mouvement.getKey()) {
			case "MOVE_RIGHT":
				positionProchainTour.setLocation(getPosition().getX(),getPosition().getY()+1);		
				for(Bonus bonus : contexte.getListeBonus()) {
					if((bonus.getPosition().getX()==positionProchainTour.getX())&&(bonus.getPosition().getY()==positionProchainTour.getY())) {
						listeMouvementAvecBonus.add(mouvement);
					}
				}
				break;
			case "MOVE_LEFT":
				positionProchainTour.setLocation(getPosition().getX(),getPosition().getY()-1);
				for(Bonus bonus : contexte.getListeBonus()) {
					if((bonus.getPosition().getX()==positionProchainTour.getX())&&(bonus.getPosition().getY()==positionProchainTour.getY())) {
						listeMouvementAvecBonus.add(mouvement);
					}
				}
				break;
			case "MOVE_TOP":
				positionProchainTour.setLocation(getPosition().getX()-1,getPosition().getY());
				for(Bonus bonus : contexte.getListeBonus()) {
					if((bonus.getPosition().getX()==positionProchainTour.getX())&&(bonus.getPosition().getY()==positionProchainTour.getY())) {
						listeMouvementAvecBonus.add(mouvement);
					}
				}
				break;
			case "MOVE_BOTTOM":
				positionProchainTour.setLocation(getPosition().getX()+1,getPosition().getY());
				for(Bonus bonus : contexte.getListeBonus()) {
					if((bonus.getPosition().getX()==positionProchainTour.getX())&&(bonus.getPosition().getY()==positionProchainTour.getY())) {
						listeMouvementAvecBonus.add(mouvement);
					}
				}
				break;
			default:
				break;
			}
		}
		if(listeMouvementAvecBonus.isEmpty()) {
			return listeMouvementPossible.get(random.nextInt(listeMouvementPossible.size()));
		}else {
			return listeMouvementAvecBonus.get(random.nextInt(listeMouvementAvecBonus.size()));
		}
	}
	
	@Override
	public String toString() {
		return "Joueur [id=" + id + ", nom=" + nom + ", score=" + score + ", alive=" + alive + ", position=" + position
				+ ", listeBombe=" + listeBombe + ", rayonExplosionBombe=" + rayonExplosionBombe + ", nbreBombeMax="
				+ nbreBombeMax + "]";
	}
		
	
}
