import java.util.ArrayList;
import java.util.List;

/**
 * ' De Participant klasse.
 * 
 * @author Anthony Snijders
 *
 */
public class Participant {
	private List<Hand> hand = new ArrayList<Hand>();
	private int aantalHanden;

	/**
	 * Geeft een hand van de Participant
	 * 
	 * @param aantal
	 *            Het nummer van de hand
	 * @return de hand
	 */
	public Hand getHand(int aantal) {
		return this.hand.get(aantal);
	}

	/**
	 * Voeg een hand toe
	 * 
	 * @param h
	 *            de hand die wordt toegevoegd
	 */
	public void addHand(Hand h) {
		if (this.aantalHanden > hand.size()) {
			hand.add(h);
		} else {
			System.out.println("Max aantal handen");
		}
	}

	/**
	 * Reset alle handen van de Participant
	 */
	public void reset() {
		for (int i = 0; i < this.aantalHanden; i++) {
			this.getHand(i).reset();
		}
	}

	/**
	 * Set het aantal handen
	 * 
	 * @param i
	 *            het aantal handen
	 */
	public void setHanden(int i) {
		this.aantalHanden = i;
	}

	/**
	 * geeft het aantal handen
	 * 
	 * @return het aantal handen
	 */
	public int getAantalHanden() {
		return this.aantalHanden;
	}
}
