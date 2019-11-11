package beans;

import utils.Mouvement;

public class Position {
	private int x;
	private int y;
	
	// Constructeur
	
	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Position() {
		super();
	}
	
	public Position(Position position) {
		super();
		this.x = position.getX();
		this.y = position.getY();
	}
	
	// getters et setters
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	// fonctions utiles	
	public int getNbreCoupMin(Position position) {
		return Math.abs(position.getX()-getX()) + Math.abs(position.getY()-getY());
	}
	
	public void setLocation(int pX, int pY) {
		this.x = pX;
		this.y = pY;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Position other = (Position) obj;
		if((this.x==other.x)&&(this.y==other.y))
			return true;
		return false;
	}
	
	
	
	public void addToX(int pX) {
		this.x = this.x +pX;	
	}
	
	public void addToY(int pY) {
		this.y = this.y +pY;		
	}
	
	public Position getVoisinDroite() {
		int pX = this.x;
		int pY = this.y+1;
		return new Position(pX,pY);
	}
	public Position getVoisinGauche() {
		int pX = this.x;
		int pY = this.y-1;
		return new Position(pX,pY);
	}
	public Position getVoisinHaut() {
		int pX = this.x-1;
		int pY = this.y;
		return new Position(pX,pY);
	}
	public Position getVoisinBas() {
		int pX = this.x+1;
		int pY = this.y;
		return new Position(pX,pY);
	}
	
	public Mouvement getMouvementVersPoint(Position positionObjectif) {
		Mouvement mouvement = Mouvement.STAY;
		if(this.y>positionObjectif.getY()) {
			return Mouvement.MOVE_LEFT;
		}else if(this.y<positionObjectif.getY()){
			return Mouvement.MOVE_RIGHT;
		}
		if(this.x>positionObjectif.getX()) {
			return Mouvement.MOVE_TOP;
		}else if(this.x<positionObjectif.getX()) {
			return Mouvement.MOVE_BOTTOM;
		}
		return mouvement;
	}
	

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}	
}
