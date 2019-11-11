package beans;

public class Caisse {
	private Position position;
	
	
	// Constructeur
	public Caisse() {
		super();
	}
	public Caisse(Position position) {
		this.position = position;
	}
	
	
	//Getter et Setter

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Caisse [position=" + position + "]";
	}	
}
