import java.lang.Math;

/**
 * De klasse Player. De player heeft een kapitaal,een naam,het aantal handen en
 * een inzet;
 * 
 * @author Anthony Snijders
 *
 */
public class Player extends Participant {
	private int kaptitaal;
	private String naam;
	private int inzet;

	/**
	 * De constructor van Player
	 * 
	 * @param naam
	 *            de naam van de speler
	 * @param aantal
	 *            het aantal handen van de speler
	 * @param inzet
	 *            de inzet per hand van de speler
	 * @param kapitaal
	 *            het kapitaal van de speler
	 */
	public Player(String naam, int aantal, int inzet, int kapitaal) {
		this.kaptitaal = kapitaal;
		this.naam = naam;
		this.setHanden(aantal);
		this.inzet = inzet;
	}

	/**
	 * De get methode van naam
	 * 
	 * @return de naam van de speler
	 */
	public String getNaam() {
		return this.naam;
	}

	/**
	 * de get methode van kapitaal
	 * 
	 * @return het kapitaal van de speler
	 */
	public int getKapitaal() {
		return this.kaptitaal;
	}

	/**
	 * De set methode van kapitaal
	 * 
	 * @param k
	 *            het nieuwe kapitaal van de speler
	 */
	public void setKapitaal(double k) {
		Double res = Math.floor(k);
		this.kaptitaal = res.intValue();
	}

	/**
	 * De get methode van inzet
	 * 
	 * @return de inzet van de speler
	 */
	public int getInzet() {
		return this.inzet;
	}

}
