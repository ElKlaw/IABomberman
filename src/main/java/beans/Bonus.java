package beans;

public class Bonus {
	private Position position;
	private boolean disponibleSansCasserCaisse;
	private int typeBonus;
	
	// Constructeur
	public Bonus() {
		super();
	}
	public Bonus(Position position) {
		this.position = position;
		this.disponibleSansCasserCaisse = false;
	}
	public Bonus(Position position, boolean dispo) {
		this.position = position;
		this.disponibleSansCasserCaisse= dispo;
	}
	public Bonus(Position position, boolean dispo, int typeBonus) {
		this.position = position;
		this.disponibleSansCasserCaisse= dispo;
		this.typeBonus= typeBonus;
	}
	
	//Getters et Setters
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public boolean isDisponibleSansCasserCaisse() {
		return disponibleSansCasserCaisse;
	}
	public void setDisponibleSansCasserCaisse(boolean disponibleSansCasserCaisse) {
		this.disponibleSansCasserCaisse = disponibleSansCasserCaisse;
	}
	public int getTypeBonus() {
		return typeBonus;
	}
	public void setTypeBonus(int typeBonus) {
		this.typeBonus = typeBonus;
	}
	
	@Override
	public String toString() {
		return "Bonus [position=" + position + ", disponibleSansCasserCaisse=" + disponibleSansCasserCaisse
				+ ", typeBonus=" + typeBonus + "]";
	}
}
