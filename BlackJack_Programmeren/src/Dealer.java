/**
 * De dealer klasse
 * 
 * @author Anthony Snijders
 *
 */
public class Dealer extends Participant {

	/**
	 * De constructor van Dealer
	 */
	public Dealer() {
		this.setHanden(1);
	}

	/**
	 * 
	 * @return true als de hand van de deler minder dan 17 is
	 */
	public boolean minderdan17() {
		return (this.getHand(this.getAantalHanden() - 1).getWaarde() < 17);
	}

}
