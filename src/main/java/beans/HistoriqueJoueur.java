package beans;

public class HistoriqueJoueur {
	Position positionPrecedente;
	int nbreToursStatic = 0;
	
	public Position getPositionPrecedente() {
		return positionPrecedente;
	}
	public void setPositionPrecedente(Position positionPrecedente) {
		this.positionPrecedente = positionPrecedente;
	}
	public int getNbreToursStatic() {
		return nbreToursStatic;
	}
	public void setNbreToursStatic(int nbreToursStatic) {
		this.nbreToursStatic = nbreToursStatic;
	}
	@Override
	public String toString() {
		return "HistoriqueJoueur [positionPrecedente=" + positionPrecedente + ", nbreToursStatic=" + nbreToursStatic
				+ "]";
	}
	
	
}
