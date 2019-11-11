package beans;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class Bombe {
	private int rayon;
	private int toursAvantExplosion;
	private String nomPoseur;
	private Position position ; 
	
	//Constructeur
	public Bombe() {
		super();
	}
	
	public Bombe(JSONObject json, Position position) {
		this.nomPoseur = json.getString("playerName");
		this.rayon = json.getInt("radius");
		this.toursAvantExplosion = json.getInt("timer");
		this.position = position;
	}
	
	
	public Bombe(Bombe pBombe) {
		super();
		this.nomPoseur=pBombe.getNomPoseur();
		this.position=pBombe.getPosition();
		this.rayon=pBombe.getRayon();
		this.toursAvantExplosion=pBombe.getToursAvantExplosion();
	}
	
	//Getter et Setter
	public int getRayon() {
		return rayon;
	}
	
	public void setRayon(int rayon) {
		this.rayon = rayon;
	}
	public int getToursAvantExplosion() {
		return toursAvantExplosion;
	}
	public void setToursAvantExplosion(int toursAvantExplosion) {
		this.toursAvantExplosion = toursAvantExplosion;
	}
	
	public String getNomPoseur() {
		return nomPoseur;
	}
	public void setNomPoseur(String nomPoseur) {
		this.nomPoseur = nomPoseur;
	}
		
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public List<Position> getListePointExplosionBombe(){
		List<Position> listePointExplosionBombe = new ArrayList<>();
		listePointExplosionBombe.add(position);
		for(int i = 1 ; i<rayon+1; i++) {
			
			Position pointDroite = new Position(position);
			pointDroite.addToY(i);
			Position pointGauche= new Position(position);
			pointGauche.addToY(-i);
			Position pointHaut= new Position(position);
			pointHaut.addToX(-i);
			Position pointBas=new Position(position);
			pointBas.addToX(i);
			
			listePointExplosionBombe.add(pointBas);
			listePointExplosionBombe.add(pointHaut);
			listePointExplosionBombe.add(pointDroite);
			listePointExplosionBombe.add(pointGauche);
		}
		return listePointExplosionBombe;
	}

	public List<Position> getListePointMortelBombe(){
		List<Position> listePointMortelBombe = new ArrayList<>();
		if(toursAvantExplosion==2) {
			listePointMortelBombe.add(position);
		}else if(toursAvantExplosion==1) {
			return getListePointExplosionBombe();
		}
		return listePointMortelBombe;
	}
	
	public List<Position> getListePointMortelPourPoserBombe(int tour, int tourArriveSurPosition){
		int tourDepart = tour +1;
		List<Position> listePointMortelBombe = new ArrayList<>();
		if((tourArriveSurPosition==0||tourArriveSurPosition==1)&&(tourDepart>=this.toursAvantExplosion)) {
			listePointMortelBombe.addAll(getListePointExplosionBombe());
		}else if((tourArriveSurPosition==2)&&(tourDepart>=this.toursAvantExplosion)) {
			if(this.toursAvantExplosion>=2) {
				listePointMortelBombe.addAll(getListePointExplosionBombe());
			}
		}else if((tourArriveSurPosition==3)&&(tourDepart>=this.toursAvantExplosion)) {
			if(toursAvantExplosion>=3) {
				listePointMortelBombe.addAll(getListePointExplosionBombe());
			}
		}
		return listePointMortelBombe;
	} 
	
	public List<Position> getListePositionCaisseDetruite(Contexte contexte){
		List<Position> listePositionCaisseDetruite = new ArrayList<>();
		float [][] tableauPoint = contexte.getTableauPoints();
		for(Position position : getListePointExplosionBombe()) {
			if((position.getX()<=9)&&(position.getX()>=0)&&(position.getY()<=9)&&(position.getY()>=0)) {
				if(tableauPoint[position.getX()][position.getY()]==-1) {
					listePositionCaisseDetruite.add(position);
				}
			}
		}
		return listePositionCaisseDetruite;
	}
	
	@Override
	public String toString() {
		return "Bombe [rayon=" + rayon + ", toursAvantExplosion=" + toursAvantExplosion + ", nomPoseur=" + nomPoseur
				+ ", position=" + position + "]";
	}
}
