package beans;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Contexte {	
	public static final  int MAX_PROFONDEUR_PARCOURS=9;
	public static final int NBRE_MOUVEMENT_POSSIBLE=6;
	public static final int TAILLE_MAP=10;
	
	private static final String NAME_JOUEUR = "CIA";
	
	private String id;
	private String startDate;
	private int level;
	private List<Joueur> listeJoueur = new ArrayList<>();
	private List<Bombe> listeBombe = new ArrayList<>();
	private List<Caisse> listeCaisse = new ArrayList<>();
	private List<Bonus> listeBonus = new ArrayList<>();
	private float tableauPoints[][] = new float[10][10];//  {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
	private float VALEUR_BONUS_RAYON=1.00f;
	private float VALEUR_BONUS_BOMBE=1.00f;
	private float VALEUR_BONUS_RAYON_CAISSE=0.10f;
	private float VALEUR_BONUS_BOMBE_CAISSE=0.10f;
	
	
	
	public Contexte() {
		super();
	}
	public Contexte(Contexte pContexte) {
		super();
		this.id = pContexte.getId();
		this.startDate = pContexte.getStartDate();
		this.level = pContexte.getLevel();
		this.listeJoueur = new ArrayList<>(pContexte.listeJoueur);
		this.listeBombe = new ArrayList<>(pContexte.listeBombe);
		this.listeCaisse = new ArrayList<>(pContexte.listeCaisse);
		this.listeBonus = new ArrayList<>(pContexte.listeBonus);
		this.tableauPoints = Arrays.stream(pContexte.tableauPoints).map(r -> r.clone()).toArray(float[][]::new);
		VALEUR_BONUS_RAYON = pContexte.VALEUR_BONUS_RAYON;
		VALEUR_BONUS_BOMBE = pContexte.VALEUR_BONUS_BOMBE;
		VALEUR_BONUS_RAYON_CAISSE = pContexte.VALEUR_BONUS_RAYON_CAISSE;
		VALEUR_BONUS_BOMBE_CAISSE = pContexte.VALEUR_BONUS_BOMBE_CAISSE;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public List<Joueur> getListeJoueur() {
		return listeJoueur;
	}
	public List<Bombe> getListeBombe() {
		return listeBombe;
	}
	public List<Caisse> getListeCaisse() {
		return listeCaisse;
	}
	public List<Bonus> getListeBonus() {
		return listeBonus;
	}	
	public float[][] getTableauPoints() {
		return tableauPoints;
	}
	
	public void setTableauPoints(float[][] tableauPoints) {
		this.tableauPoints = tableauPoints;
	}
	
	public void addInfos(String board, int bonus_rayon, String idGame) {
		JSONObject jsonObject = new JSONObject(board);
		this.id = jsonObject.getString("id");
		if(!this.id.equals(idGame)) {
			bonus_rayon=1;
		}
		this.startDate = jsonObject.getString("startDate");
		this.level = jsonObject.getInt("level");
		JSONArray lines = (JSONArray) jsonObject.get("lines");
		int typeBonus=0;
		int nbLine= 0;
		for(int i=0;i<lines.length();i++) {
			JSONObject line = lines.getJSONObject(i);
			int nbCellule =0;
	    	JSONObject jsonObjectLine = new JSONObject(line.toString());
	    	JSONArray tiles = (JSONArray) jsonObjectLine.get("tiles");
	    	for(int j=0;j<tiles.length();j++) {
	    		JSONObject tile = tiles.getJSONObject(j);
		    	JSONObject jsonObjectTile = tile;
		    	if((jsonObjectTile.has("bomb"))){
					JSONObject jsonObjectBombe = jsonObjectTile.getJSONObject("bomb");
					Position positionBombe = new Position(nbLine, nbCellule);
					Bombe bombe = new Bombe(jsonObjectBombe, positionBombe);
					this.listeBombe.add(bombe);
				}
		    	if((jsonObjectTile.has("player"))){
					JSONObject jsonObjectPlayer = jsonObjectTile.getJSONObject("player");
					Position positionJoueur= new Position(nbLine, nbCellule);
					Joueur joueur = new Joueur(jsonObjectPlayer, positionJoueur);
					if((!(NAME_JOUEUR.equals(joueur.getNom())))) {
						tableauPoints[positionJoueur.getX()][positionJoueur.getY()]=-2;
					}
					this.listeJoueur.add(joueur);
				}
		    	if((jsonObjectTile.has("bonus"))){
		    		Bonus bonus = new Bonus();
		    		if(jsonObjectTile.getString("type").contains("wood")) {
		    			bonus.setDisponibleSansCasserCaisse(false);
			    	}else if(jsonObjectTile.getString("type").contains("grass")) {
		    			bonus.setDisponibleSansCasserCaisse(true);
			    	}
		    		bonus.setPosition(new Position(nbLine, nbCellule));
		    		typeBonus=jsonObjectTile.getInt("bonus");
		    		bonus.setTypeBonus(typeBonus);
		    		this.listeBonus.add(bonus);
				}
		    	
		    	if(jsonObjectTile.getString("type").contains("wood")) {
		    		Position positionCaisse= new Position(nbLine, nbCellule);
		    		Caisse caisse = new Caisse(positionCaisse);
		    		ajoutPointCaisse(positionCaisse, bonus_rayon);
		    		tableauPoints[positionCaisse.getX()][positionCaisse.getY()]=-1;
		    		this.listeCaisse.add(caisse);
		    	}
			    nbCellule ++;
		    }
		    nbLine ++;
		}
	}
	
	
	public void ajoutPointCaisse(Position position, int rayonBombe) {
		for(int i = 1 ; i<rayonBombe+1; i++) {
			Position pointDroite = new Position(position);
			pointDroite.addToY(i);
			Position pointGauche= new Position(position);
			pointGauche.addToY(-i);
			Position pointHaut= new Position(position);
			pointHaut.addToX(-i);
			Position pointBas=new Position(position);
			pointBas.addToX(i);

			ajoutPointTableau(pointDroite);
			ajoutPointTableau(pointGauche);
			ajoutPointTableau(pointHaut);
			ajoutPointTableau(pointBas);
		}
	}
	
	public void ajoutPointAjoutRayon(Position positionBonusRayon, int rayonBombe) {
		boolean pointDroiteExplo = false;
		boolean pointGaucheExplo = false;
		boolean pointHautExplo = false;
		boolean pointBasExplo = false;
		
		Position pointDroite = new Position(positionBonusRayon);
		pointDroite.addToY(rayonBombe);
		Position pointGauche= new Position(positionBonusRayon);
		pointGauche.addToY(-rayonBombe);
		Position pointHaut= new Position(positionBonusRayon);
		pointHaut.addToX(-rayonBombe);
		Position pointBas=new Position(positionBonusRayon);
		pointBas.addToX(rayonBombe);
		for(Bombe bombe : this.listeBombe) {
			if(bombe.getListePointExplosionBombe().contains(pointDroite)) {
				pointDroiteExplo=true;
			}
			if(bombe.getListePointExplosionBombe().contains(pointGauche)) {
				pointGaucheExplo=true;		
			}
			if(bombe.getListePointExplosionBombe().contains(pointHaut)) {
				pointHautExplo=true;
			}
			if(bombe.getListePointExplosionBombe().contains(pointBas)) {
				pointBasExplo=true;
			}
		}
		if(!pointDroiteExplo) {
			ajoutPointChangementRayon(positionBonusRayon,pointDroite);
		}
		if(!pointGaucheExplo) {
			ajoutPointChangementRayon(positionBonusRayon,pointGauche);
		}
		if(!pointHautExplo) {
			ajoutPointChangementRayon(positionBonusRayon,pointHaut);
		}
		if(!pointBasExplo) {
			ajoutPointChangementRayon(positionBonusRayon,pointBas);
		}		
	}
	
	
	private void ajoutPointChangementRayon(Position positionbonus, Position positionCaisse) {
		if((positionCaisse.getX()>=0)&&(positionCaisse.getX()<=9)&&(positionCaisse.getY()>=0)&&(positionCaisse.getY()<=9)&&(tableauPoints[positionCaisse.getX()][positionCaisse.getY()]==-1)){
			MathContext mc = new MathContext(3,RoundingMode.HALF_UP);
			tableauPoints[positionbonus.getX()][positionbonus.getY()] = new BigDecimal(tableauPoints[positionbonus.getX()][positionbonus.getY()]+1,mc).floatValue();
		}
	}
	
	private void ajoutPointTableau(Position position) {
		if((position.getX()>=0)&&(position.getX()<=9)&&(position.getY()>=0)&&(position.getY()<=9)&&(tableauPoints[position.getX()][position.getY()]>=0)){
			if((tableauPoints[position.getX()][position.getY()]!=-1)&&(tableauPoints[position.getX()][position.getY()]!=-2) ) {
				MathContext mc = new MathContext(3,RoundingMode.HALF_UP);
				tableauPoints[position.getX()][position.getY()] = new BigDecimal(tableauPoints[position.getX()][position.getY()]+1,mc).floatValue();
			}
		}
	}
	
	private void retirerPointTableau(Position position) {
		if((position.getX()>=0)&&(position.getX()<=9)&&(position.getY()>=0)&&(position.getY()<=9)&&(tableauPoints[position.getX()][position.getY()]>0)){
			if((tableauPoints[position.getX()][position.getY()]!=-1)&&(tableauPoints[position.getX()][position.getY()]!=-2) ) {
				MathContext mc = new MathContext(3,RoundingMode.HALF_UP);
				tableauPoints[position.getX()][position.getY()] = new BigDecimal(tableauPoints[position.getX()][position.getY()]-1,mc).floatValue();
			}
		}
	}
	
	private void ajout5PointTableau(Position position) {
		if((position.getX()>=0)&&(position.getX()<=9)&&(position.getY()>=0)&&(position.getY()<=9)&&(tableauPoints[position.getX()][position.getY()]>=0)){
			if((tableauPoints[position.getX()][position.getY()]!=-1)&&(tableauPoints[position.getX()][position.getY()]!=-2) ) {
				MathContext mc = new MathContext(3,RoundingMode.HALF_UP);
				tableauPoints[position.getX()][position.getY()] = new BigDecimal(tableauPoints[position.getX()][position.getY()]+5,mc).floatValue();
			}
		}
	}
	
	private void ajoutXPointTableau(Position position, float X) {
		if((position.getX()>=0)&&(position.getX()<=9)&&(position.getY()>=0)&&(position.getY()<=9)&&(tableauPoints[position.getX()][position.getY()]>=0)){
			if((tableauPoints[position.getX()][position.getY()]!=-1)&&(tableauPoints[position.getX()][position.getY()]!=-2) ) {
				MathContext mc = new MathContext(3,RoundingMode.HALF_UP);
				tableauPoints[position.getX()][position.getY()] = new BigDecimal(tableauPoints[position.getX()][position.getY()]+X,mc).floatValue();
			}
		}
	}
	
	private void miseA0PointTableau(Position position) {
		if((position.getX()>=0)&&(position.getX()<=9)&&(position.getY()>=0)&&(position.getY()<=9)){
			tableauPoints[position.getX()][position.getY()] = 0;
		}
	}
	
	public void ajoutPointBombe() {
		List<Position> listePositionExplosionBombe = new ArrayList<>();
		for(Bombe bombe : this.listeBombe) {
			for(Position position : bombe.getListePointExplosionBombe()) {
				if((position.getX()<=9)&&(position.getX()>=0)&&(position.getY()<=9)&&(position.getY()>=0)&&(!listePositionExplosionBombe.contains(position))) {
					if(tableauPoints[position.getX()][position.getY()]==-1) {
						for(int i = 1 ; i<bombe.getRayon()+1; i++) {
							Position pointDroite = new Position(position);
							pointDroite.addToY(i);
							Position pointGauche= new Position(position);
							pointGauche.addToY(-i);
							Position pointHaut= new Position(position);
							pointHaut.addToX(-i);
							Position pointBas=new Position(position);
							pointBas.addToX(i);

							retirerPointTableau(pointDroite);
							retirerPointTableau(pointGauche);
							retirerPointTableau(pointHaut);
							retirerPointTableau(pointBas);
						}				
					}
					listePositionExplosionBombe.add(position);
				}
			}
		}
	}
	
	public void ajoutPointBonus(Tree arbre, int portee) {
		for(Node node : arbre.getListeDesNoeud()) {
			if(!node.getListeBonusPris().isEmpty()) {
				int bonusRayon=0;
				int bonusBombe=0;
				for(Bonus bonus : node.getListeBonusPris()) {
					if(this.listeBonus.contains(bonus)) {
						if(bonus.isDisponibleSansCasserCaisse()) {
							if(tableauPoints[node.getPosition().getX()][node.getPosition().getY()]>=0) {
								if(bonus.getTypeBonus()==2) {
									bonusRayon++;
					    		}else if(bonus.getTypeBonus()==1) {
					    			bonusBombe++;
					    		}
							}
						}
					}
				}
				if(bonusRayon>0) {
					tableauPoints[node.getPosition().getX()][node.getPosition().getY()]+=VALEUR_BONUS_RAYON;
	    			ajoutPointAjoutRayon(node.getPosition(),portee+bonusRayon);
				}
				if(bonusBombe>0) {
					tableauPoints[node.getPosition().getX()][node.getPosition().getY()]+=VALEUR_BONUS_BOMBE;
				}
				node.setValue(tableauPoints[node.getPosition().getX()][node.getPosition().getY()]);
			}
		}
	}
	
	public void ajoutPointBonusCaisse(int porteeBombe) {
		for(Bonus bonus : this.listeBonus) {
			int typeBonus = bonus.getTypeBonus();
			int portee=porteeBombe;
			float valeurBonus=0;
			if(typeBonus==2) {
    			valeurBonus=VALEUR_BONUS_RAYON_CAISSE;
    		}else if(typeBonus==1) {
    			valeurBonus=VALEUR_BONUS_BOMBE_CAISSE;
    		}
			if(tableauPoints[bonus.getPosition().getX()][bonus.getPosition().getY()]==-1) {
				for(int i = 1 ; i<portee+1; i++) {
					Position pointDroite = new Position(bonus.getPosition());
					pointDroite.addToY(i);
					ajoutXPointTableau(pointDroite,valeurBonus);
					Position pointGauche= new Position(bonus.getPosition());
					pointGauche.addToY(-i);
					ajoutXPointTableau(pointGauche,valeurBonus);
					Position pointBas=new Position(bonus.getPosition());
					pointBas.addToX(i);
					ajoutXPointTableau(pointBas,valeurBonus);
					Position pointHaut= new Position(bonus.getPosition());
					pointHaut.addToX(-i);
					ajoutXPointTableau(pointHaut,valeurBonus);
				}
			}
		}
	}
	
	public void ajoutPointBonus(int porteeBombe) {
		for(Bonus bonus : this.listeBonus) {
			int typeBonus = bonus.getTypeBonus();
			int X= bonus.getPosition().getX();
			int Y= bonus.getPosition().getY();
			int portee=porteeBombe;
			if(tableauPoints[X][Y]>=0) {
				if(typeBonus==2) {
	    			tableauPoints[X][Y]+=VALEUR_BONUS_RAYON;
	    			ajoutPointAjoutRayon(bonus.getPosition(),portee+1);
	    		}else if(typeBonus==1) {
	    			tableauPoints[X][Y]+=VALEUR_BONUS_BOMBE;
	    		}
			}
		}
	}
	
	public void ajoutPointJoueur(Map<String,HistoriqueJoueur> listeHistoriqueJoueur,int porteeBombe) {
		for(Joueur joueur : this.listeJoueur) {
			boolean bombeSurJoueur= false;
			
			for(Bombe bombe : this.listeBombe) {
				if(bombe.getListePointExplosionBombe().contains(joueur.getPosition())) {
					bombeSurJoueur=true;
				}
			}
			if(!bombeSurJoueur) {
				if((joueur.isAlive())&&(joueur.getJoueurStatic(listeHistoriqueJoueur.get(joueur.getNom())))&&(!(NAME_JOUEUR.equals(joueur.getNom())))) {
					if((joueur.getPosition().getX()<=9)&&(joueur.getPosition().getX()>=0)&&(joueur.getPosition().getY()<=9)&&(joueur.getPosition().getY()>=0)) {
						for(int i = 1 ; i<porteeBombe+1; i++) {
							Position pointDroite = new Position(joueur.getPosition());
							pointDroite.addToY(i);
							Position pointGauche= new Position(joueur.getPosition());
							pointGauche.addToY(-i);
							Position pointHaut= new Position(joueur.getPosition());
							pointHaut.addToX(-i);
							Position pointBas=new Position(joueur.getPosition());
							pointBas.addToX(i);
							ajout5PointTableau(pointDroite);
							ajout5PointTableau(pointGauche);
							ajout5PointTableau(pointHaut);
							ajout5PointTableau(pointBas);
						}
					}
				}
			}
		}
	}
	
	public Joueur getMonJoueur(String name) {
		Joueur joueur = null;
		for(Joueur lJoueur : listeJoueur) {
			if(lJoueur.getNom().equals(name)) {
				joueur= lJoueur;
				break;
			}
		}
		return joueur;
	}	
	
	public boolean isPointProcheDuJoueur(Joueur joueur) {
		final int TAILLE_BORDURE = 3;
		
		int positionXJoueur = joueur.getPosition().getX();
		int positionYJoueur = joueur.getPosition().getY();

		int bordGauche=0;
		int bordDroite=9;
		int bordHaut=0;
		int bordBas=9;
		
		if(positionXJoueur+TAILLE_BORDURE<9) {
			bordBas=positionXJoueur+TAILLE_BORDURE;
		}
		if(positionXJoueur-TAILLE_BORDURE>0) {
			bordHaut=positionXJoueur-TAILLE_BORDURE;
		}
		if(positionYJoueur+TAILLE_BORDURE<9) {
			bordDroite=positionYJoueur+TAILLE_BORDURE;
		}
		if(positionYJoueur-TAILLE_BORDURE>0) {
			bordGauche=positionYJoueur-TAILLE_BORDURE;
		}
		
		for(int i=bordHaut;i<bordBas;i++) {
			for(int j=bordGauche;j<bordDroite;j++) {
				if(tableauPoints[i][j]>0) {
					return true;
				}
			}
		}
		for(Bonus bonus : this.listeBonus) {
			if(bonus.isDisponibleSansCasserCaisse()) {
				return true;
			}
		}
		return false;
	}
	
	public List<Position> getListePointMortel(){
		List<Position> listePointMortel = new ArrayList<>();
		for(Bombe bombe: this.listeBombe) {
			listePointMortel.addAll(bombe.getListePointMortelBombe());
		}
		return listePointMortel;
	}
	
	public List<Position> getListePointExplosion(){
		List<Position> listePointMortel = new ArrayList<>();
		for(Bombe bombe: this.listeBombe) {
			listePointMortel.addAll(bombe.getListePointExplosionBombe());
		}
		return listePointMortel;
	}
	
	public Position getPointLePlusProche(Joueur monJoueur) {
		Position positionPlusProche = null;
		double distancePointPlusProche =20 ;
		for(int i=0;i<TAILLE_MAP;i++) {
			for(int j=0;j<TAILLE_MAP;j++) {
				if(tableauPoints[i][j]>0) {
					Position positionPoint = new Position(i, j);
					int distancePoint= monJoueur.getPosition().getNbreCoupMin(positionPoint);
					if(distancePoint<distancePointPlusProche) {
						positionPlusProche=positionPoint;
						distancePointPlusProche=distancePoint;
					}
				}
			}
		}
		return positionPlusProche;
	}
	
	
	public boolean PeutPoserBombe(Position positionPlusInterresante, int NbreTour, int tourArriveSurPosition) {
		if(PositionDangereuseDansXTour(positionPlusInterresante, NbreTour, tourArriveSurPosition)||PositionDangereuseDansXTourPlus1(positionPlusInterresante, NbreTour)) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean PositionDangereuseDansXTour(Position positionPlusInterresante, int nbreTour, int tourArriveSurPosition) {
		boolean resultat = false;
		for(Bombe bombe : this.listeBombe) {
			if(bombe.getListePointMortelPourPoserBombe(nbreTour, tourArriveSurPosition).contains(positionPlusInterresante)) {
				resultat = true;
				break;
			}
		}
		return resultat;
	}
	
	public boolean PositionDangereuseDansXTourPlus1(Position positionPlusInterresante, int nbreTour) {
		boolean resultat = true;
		
		List<Position> listePosition = new ArrayList<>();
		listePosition.add(positionPlusInterresante);
		Position voisinBas = positionPlusInterresante.getVoisinBas();
		Position voisinHaut = positionPlusInterresante.getVoisinHaut();
		Position voisinDroite = positionPlusInterresante.getVoisinDroite();
		Position voisinGauche = positionPlusInterresante.getVoisinGauche();
		
		if((voisinBas.getX()>=0)&&(voisinBas.getX()<=9)&&(voisinBas.getY()>=0)&&(voisinBas.getY()<=9)&&(tableauPoints[voisinBas.getX()][voisinBas.getY()]>=0)){
			listePosition.add(positionPlusInterresante.getVoisinBas());
		}
		if((voisinHaut.getX()>=0)&&(voisinHaut.getX()<=9)&&(voisinHaut.getY()>=0)&&(voisinHaut.getY()<=9)&&(tableauPoints[voisinHaut.getX()][voisinHaut.getY()]>=0)){
			listePosition.add(positionPlusInterresante.getVoisinHaut());
		}
		if((voisinDroite.getX()>=0)&&(voisinDroite.getX()<=9)&&(voisinDroite.getY()>=0)&&(voisinDroite.getY()<=9)&&(tableauPoints[voisinDroite.getX()][voisinDroite.getY()]>=0)){
			listePosition.add(positionPlusInterresante.getVoisinDroite());
		}
		if((voisinGauche.getX()>=0)&&(voisinGauche.getX()<=9)&&(voisinGauche.getY()>=0)&&(voisinGauche.getY()<=9)&&(tableauPoints[voisinGauche.getX()][voisinGauche.getY()]>=0)){
			listePosition.add(positionPlusInterresante.getVoisinGauche());
		}
		for(Position position : listePosition) {
			if(!PositionDangereuseDansXTour(position, nbreTour+1, 0)) {
				resultat = false;
				break;
			}
		}
		return resultat;
	}

	public void initValeurBonus(int bonusRayon,int bonusBombe) {
		VALEUR_BONUS_RAYON_CAISSE=0.10f;
		VALEUR_BONUS_RAYON=1.00f;
		int nbreCaisse = this.listeCaisse.size();
		float multiplicateur_rayon=0.04f;
		float multiplicateur_caisse_rayon=0.02f;
		
		MathContext mc = new MathContext(3,RoundingMode.HALF_UP);
		VALEUR_BONUS_RAYON= new BigDecimal(VALEUR_BONUS_RAYON+(nbreCaisse*multiplicateur_rayon),mc).floatValue();
		VALEUR_BONUS_RAYON_CAISSE = new BigDecimal(VALEUR_BONUS_RAYON_CAISSE+(nbreCaisse*multiplicateur_caisse_rayon),mc).floatValue();
		if(bonusBombe<2) {
			VALEUR_BONUS_BOMBE=1.20f;
			VALEUR_BONUS_BOMBE_CAISSE=0.20f;
		}else {
			VALEUR_BONUS_BOMBE=1.00f;
			VALEUR_BONUS_BOMBE_CAISSE=0.10f;
		}
		
	}
	
	public int pointCaisseRestant() {
		int nbrePointRestant=0;
		nbrePointRestant=+ listeCaisse.size();		
		return nbrePointRestant;
	}
	
	public void calculPointTableauApresExplosion(Position position, int portee) {
		if(tableauPoints[position.getX()][position.getY()]>=0) {
			tableauPoints[position.getX()][position.getY()]=0;
			for(Bonus bonus : this.listeBonus) {
				if((bonus.getPosition().getX()==position.getX())&&(bonus.getPosition().getY()==position.getY())){
					bonus.setDisponibleSansCasserCaisse(true);
					if(bonus.getTypeBonus()==2) {
		    			tableauPoints[bonus.getPosition().getX()][bonus.getPosition().getY()]+=VALEUR_BONUS_RAYON;
		    			ajoutPointAjoutRayon(bonus.getPosition(),portee+1);
		    		}else if(bonus.getTypeBonus()==1) {
		    			tableauPoints[bonus.getPosition().getX()][bonus.getPosition().getY()]+=VALEUR_BONUS_BOMBE;
		    		}
				}
			}
			for(Joueur joueur : this.listeJoueur) {
				if((joueur.getPosition().getX()==position.getX())&&(joueur.getPosition().getY()==position.getY())){
					joueur.setAlive(false);
				}
			}
			ajoutPointCaisse(position, portee);
		}
	}
	
	public void enleverPointBombeAPoser(Bombe bombe) {
		for(Position position : bombe.getListePointExplosionBombe()) {
			if((position.getX()<=9)&&(position.getX()>=0)&&(position.getY()<=9)&&(position.getY()>=0)) {
				if(tableauPoints[position.getX()][position.getY()]==-1) {
					for(int i = 1 ; i<bombe.getRayon()+1; i++) {
						Position pointDroite = new Position(position);
						pointDroite.addToY(i);
						Position pointGauche= new Position(position);
						pointGauche.addToY(-i);
						Position pointHaut= new Position(position);
						pointHaut.addToX(-i);
						Position pointBas=new Position(position);
						pointBas.addToX(i);

						retirerPointTableau(pointDroite);
						retirerPointTableau(pointGauche);
						retirerPointTableau(pointHaut);
						retirerPointTableau(pointBas);
					}				
				}
			}
		}
	}
	
	public int getNbreBombeJoueur(String nameJoueur) {
		int resultat = 0;
		for(Bombe bombe : this.listeBombe) {
			if(bombe.getNomPoseur().equals(nameJoueur)) {
				resultat ++;
			}
		}
		return resultat;
	}
	
	public List<Bombe> getBombeJoueur(String nameJoueur) {
		List<Bombe> resultat = new ArrayList<>();
		for(Bombe bombe : this.listeBombe) {
			if(bombe.getNomPoseur().equals(nameJoueur)) {
				resultat.add(bombe);
			}
		}
		return resultat;
	}
	
	public void retirerCaisse(Bombe bombe) {
		for(Position position : bombe.getListePointExplosionBombe()) {
			if((position.getX()<=9)&&(position.getX()>=0)&&(position.getY()<=9)&&(position.getY()>=0)) {
				if(tableauPoints[position.getX()][position.getY()]==-1) {
					tableauPoints[position.getX()][position.getY()]=0;		
				}
			}
		}
	}
	
	public void MajContexte(int rayonBombe) {
		//Mise à zéro des cases non joueur ou caisse
		for(int row=0; row< this.tableauPoints.length;row++) {
			for(int col=0; col< this.tableauPoints.length;col++) {				
				if(tableauPoints[row][col]>0) {					
					tableauPoints[row][col]=0;
				}
			}
		}
		//Ajout point caisse
		for(int row=0; row< this.tableauPoints.length;row++) {
			for(int col=0; col< this.tableauPoints.length;col++) {				
				if(tableauPoints[row][col]==-1) {					
					ajoutPointCaisse(new Position(row, col),rayonBombe);
				}
			}
		}
		//Ajout point bombe
		ajoutPointBombe();
	}
	
	public void enleverBombe(int rayonBombe) {
		for(Bombe bombe : this.listeBombe) {
			for(Position position : bombe.getListePointExplosionBombe()) {
				miseA0PointTableau(position);
			}
		}
		//Mise à zéro des cases non joueur ou caisse
		for(int row=0; row< this.tableauPoints.length;row++) {
			for(int col=0; col< this.tableauPoints.length;col++) {				
				if((tableauPoints[row][col]!=-1)&&(tableauPoints[row][col]!=-2)) {					
					tableauPoints[row][col]=0;
				}
			}
		}
		//Ajout point caisse
		for(int row=0; row< this.tableauPoints.length;row++) {
			for(int col=0; col< this.tableauPoints.length;col++) {				
				if(tableauPoints[row][col]==-1) {					
					ajoutPointCaisse(new Position(row, col),rayonBombe);
				}
			}
		}
		//Ajout point bombe
		ajoutPointBonus(rayonBombe);
		ajoutPointBonusCaisse(rayonBombe);
		this.listeBombe = new ArrayList<>();
	}

	public Contexte initApresExplosion(int portee) {
		List<Position> listePositionExplosionBombe = new ArrayList<>();
		for(Bombe bombe : this.listeBombe) {
			listePositionExplosionBombe.addAll(bombe.getListePointExplosionBombe());
		}
		Contexte newContexte = new Contexte(this);
		newContexte.enleverBombe(portee);
		return newContexte;
	}
	
	@Override
	public String toString() {
		return "Contexte [id=" + id + ", startDate=" + startDate + ", level=" + level + ", listeJoueur=" + listeJoueur
				+ ", listeBombe=" + listeBombe + ", listeCaisse=" + listeCaisse + ", listeBonus=" + listeBonus
				+ ", tableauPoints=" + Arrays.toString(tableauPoints) + "]";
	}
}
