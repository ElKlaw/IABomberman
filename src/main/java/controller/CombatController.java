package controller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import beans.Bombe;
import beans.Contexte;
import beans.HistoriqueJoueur;
import beans.Joueur;
import beans.Node;
import beans.Position;
import utils.Mouvement;

@RestController
public class CombatController {
	
	private static final Logger LOG = LogManager.getLogger(CombatController.class);
	public static final String NAME_JOUEUR = "CIA";
	private int RAYON_EXPLOSION_BOMBE=1;
	private int NBRE_BOMBE_MAX=1;
	private int TOUR_JEU=1;
	
	private Map<String,HistoriqueJoueur> listePositionPrecedenteJoueur = new HashMap<>();
	
	private String idGame="";
	
		
	// V2 du bot
	@PostMapping(value = "/api/combat/stay", produces = "application/json")
	public String getStay(@RequestBody String plateau) {
		SecureRandom random = new SecureRandom();
		List<Mouvement> listeMouvementPossible = new ArrayList<>(Arrays.asList(Mouvement.values()));
		listeMouvementPossible.remove(Mouvement.BOMB);
		Mouvement mouvementChoisi = listeMouvementPossible.get(random.nextInt(listeMouvementPossible.size()));;
		return mouvementChoisi.getMessageJson();
	}
			
	// V2 du bot
	@PostMapping(value = "/api/combat/exec", produces = "application/json")
	public String getBattle(@RequestBody String plateau) {
		long startTime = System.currentTimeMillis();
		/*-----------------------------------------------------------INITIALISATION ---------------------------------------------------------*/
		Mouvement mouvementChoisi = Mouvement.STAY;
		SecureRandom random = new SecureRandom();
		//init contexte
		Contexte contexte = new Contexte();
		contexte.addInfos(plateau,RAYON_EXPLOSION_BOMBE,idGame);
		if(!(contexte.getId().equals(idGame))){
			idGame=contexte.getId();
			LOG.info(" ------------------------------NEW GAME (id : "+idGame+")-----------------------------");
			RAYON_EXPLOSION_BOMBE=1;
			NBRE_BOMBE_MAX=1;
			TOUR_JEU=1;
		}
		//init joueur
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		monJoueur.initBombesJoueur(contexte);
		contexte.initValeurBonus(RAYON_EXPLOSION_BOMBE, NBRE_BOMBE_MAX);
		if((contexte.getLevel()==3)&&(TOUR_JEU>1)){
			contexte.ajoutPointJoueur(listePositionPrecedenteJoueur,RAYON_EXPLOSION_BOMBE);
		}
		if(TOUR_JEU==1){
			listePositionPrecedenteJoueur = new HashMap<>();
			for(Joueur joueur : contexte.getListeJoueur()) {
				HistoriqueJoueur historiqueJoueur = new HistoriqueJoueur();
				historiqueJoueur.setPositionPrecedente(joueur.getPosition());
				listePositionPrecedenteJoueur.put(joueur.getNom(), historiqueJoueur);
			}
			
		}else {
			for(Joueur joueur : contexte.getListeJoueur()) {
				HistoriqueJoueur historiqueJoueur = listePositionPrecedenteJoueur.get(joueur.getNom());
				historiqueJoueur.setPositionPrecedente(joueur.getPosition());
				listePositionPrecedenteJoueur.replace(joueur.getNom(), historiqueJoueur);
			}
		}
		
		LOG.info(" -------------- TOUR "+TOUR_JEU+"----------------------");
		LOG.info("Position Précédente : "+listePositionPrecedenteJoueur.toString());
		TOUR_JEU++;
		monJoueur.initBonus(RAYON_EXPLOSION_BOMBE, NBRE_BOMBE_MAX);
		
		LOG.info(" Json d'entrée : " +plateau.replaceAll("[\r\n]",""));
		LOG.info("Mon Joueur "+monJoueur);
		/*float [][] tableauPoint = contexte.getTableauPoints();
		LOG.info("------------------Tableau------------------------------");
		for(int f=0; f< tableauPoint.length;f++) {
			LOG.info(Arrays.toString(tableauPoint[f]));
		}
		LOG.info("-------------------------------------------------------");*/
		
		
		/*---------------------------------------------------------CALCUL DES MOUVEMENTS --------------------------------------------------*/
		
		//Liste des mouvements possibles (Parmis STAY,BOTTOM,TOP,RIGHT,LEFT)
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte); 
		//Liste des mouvements mortels (Parmis STAY,BOTTOM,TOP,RIGHT,LEFT)
		List<Mouvement> listeMouvementMortel;
		if(contexte.getLevel()==3) {
			listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		}else {
			listeMouvementMortel = new ArrayList<>();
		}		
		/*---------------------------------------------------------CALCUL POSITION PLUS INTERESSANTE -----------------------------------------*/
		if(contexte.isPointProcheDuJoueur(monJoueur)) {
			// Algorithme pour search la position la plus intérrèsante
			Node noeudObjectif = monJoueur.getPositionLaPlusInterresante(contexte);
			if(noeudObjectif==null) {
				List<Mouvement> listeMouvementChoisi = new ArrayList<>();
				listeMouvementChoisi.addAll(listeMouvementPossible);
				mouvementChoisi=getListeMouvementPossibleNonMortel(listeMouvementChoisi, listeMouvementMortel);
				LOG.info("Mouvement choisi : "+mouvementChoisi);
				return mouvementChoisi.getMessageJson();
			}
			if(monJoueur.getPosition().equals(noeudObjectif.getPosition())) {
				if((monJoueur.peutPoserBombe())&&(!(listeMouvementMortel.contains(Mouvement.STAY)))) {
					mouvementChoisi = Mouvement.BOMB;
					LOG.info("Mouvement choisi : "+mouvementChoisi);
					return mouvementChoisi.getMessageJson();
				}else if(!(listeMouvementMortel.contains(Mouvement.STAY))){
					boolean positionExplosion=false;
					for(Bombe bombe : contexte.getListeBombe()) {
						if(bombe.getListePointExplosionBombe().contains(monJoueur.getPosition())) {
							positionExplosion=true;
						}
					 }
					if(!positionExplosion) {
						mouvementChoisi = Mouvement.STAY;
						LOG.info(" Mouvement choisi : "+mouvementChoisi);
						return mouvementChoisi.getMessageJson();
					}
				}
			}
			LOG.info("Position la + interresante : "+noeudObjectif.getPosition());
			
			List<Mouvement> listeMouvementVersPointPlusProche = monJoueur.allerJusquauPoint(noeudObjectif,contexte);
			for(Mouvement mouvementMortel : listeMouvementMortel) {
				if(listeMouvementVersPointPlusProche.contains(mouvementMortel)) {
					listeMouvementVersPointPlusProche.remove(mouvementMortel);
				}
			}
			List<Mouvement> listeMouvementChoisi = new ArrayList<>();
			listeMouvementChoisi.addAll(listeMouvementVersPointPlusProche);
			
			for(Mouvement mouvementVersPointPlusProche : listeMouvementVersPointPlusProche) {
				if(!(listeMouvementPossible.contains(mouvementVersPointPlusProche))) {
					listeMouvementChoisi.remove(mouvementVersPointPlusProche);
				}
			}
			if(!(listeMouvementChoisi.isEmpty())) {
				if(listeMouvementChoisi.size()>1) {
					mouvementChoisi=monJoueur.maximiserPriseBonus(contexte, listeMouvementChoisi);
				}else {
					mouvementChoisi = listeMouvementChoisi.get(random.nextInt(listeMouvementChoisi.size()));
				}
			}else {
				if(listeMouvementMortel.contains(mouvementChoisi)) {
					List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<>(); 
					for(Mouvement mouvement : listeMouvementPossible) {
						if(!listeMouvementMortel.contains(mouvement)) {
							listeMouvementPossibleNonMortel.add(mouvement);
						}
					}
					if(!(listeMouvementPossibleNonMortel.isEmpty())) {
						mouvementChoisi = listeMouvementPossibleNonMortel.get(random.nextInt(listeMouvementPossibleNonMortel.size()));
					}else {
						mouvementChoisi = Mouvement.BOMB;
					}
				}
			}
		}else {
			// Algo approximatif pour avec les points les plus prche
			Position positionPlusProche = contexte.getPointLePlusProche(monJoueur);
			LOG.info("Position la + proche : "+positionPlusProche);
			// Algo simple de déplacement vers un point 
			if(positionPlusProche!=null) {
				List<Mouvement> listeMouvement = monJoueur.allerJusquauPointSimple(positionPlusProche);
				mouvementChoisi = getListeMouvementPossibleNonMortel(listeMouvement, listeMouvementMortel);
			}else {
				mouvementChoisi = getListeMouvementPossibleNonMortel(listeMouvementPossible, listeMouvementMortel);
			}
		}
		LOG.info("Mouvement choisi : "+mouvementChoisi);
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		LOG.info("Temps de réponse : "+elapsedTime);
		if(monJoueur.prisBonus(contexte, mouvementChoisi)==1) {
			NBRE_BOMBE_MAX++;
		}else if(monJoueur.prisBonus(contexte, mouvementChoisi)==2) {
			RAYON_EXPLOSION_BOMBE++;
		}
		// Retourne resultat 
		return mouvementChoisi.getMessageJson();
	}
	
	
	
	
	private Mouvement getListeMouvementPossibleNonMortel(List<Mouvement> listeMouvementPossible, List<Mouvement> listeMouvementMortel) {
		SecureRandom random = new SecureRandom();
		Mouvement mouvementChoisi=Mouvement.BOMB;
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}
		if(!(listeMouvementPossibleNonMortel.isEmpty())){
			mouvementChoisi = listeMouvementPossibleNonMortel.get(random.nextInt(listeMouvementPossibleNonMortel.size()));
		}
		return mouvementChoisi;
	}
	
}
