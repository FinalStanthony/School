import java.util.ArrayList;
import java.util.List;

/**
 * De klase Hand. Kan kaarten toevoegen en waardes vergelijken
 * 
 * @author Anthony Snijders
 *
 */
public class Hand {
	private List<Card> hand = new ArrayList<Card>();
	private int waarde = 0;
	private int aantalAzen = 0;// Het aantal azen met waarde 11
	private int dubbel = 1;
	private int inzet;
	private String status = new String();

	/**
	 * Voegt een kaart toe
	 * 
	 * @param c
	 *            De kaart die je wilt toevoegen
	 */
	public void addCard(Card c) {
		hand.add(c);
		this.waarde = this.waarde + c.getGetal();
		if (c.getGetal() == 11) {
			this.aantalAzen++;
		}
		if (this.waarde > 21) {
			if (this.aantalAzen > 0) {
				this.aantalAzen--;
				this.waarde = this.waarde - 10;
			} else {
				this.waarde = -1;
			}
		}
	}

	/**
	 * De get metoden van waarde
	 * 
	 * @return de waarde van je hand
	 */
	public int getWaarde() {
		return this.waarde;
	}

	/**
	 * ' Vergelijkt 2 handen met elkaar
	 * 
	 * @param h
	 *            de hand die je wilt vergellijken
	 * @return "Win" als je wint, "Verlies" als je verliest en en "Gelijk" als
	 *         het gelijk is.
	 */
	public String vergelijk(Hand h) {
		if (this.waarde > h.getWaarde()) {
			return "Win";
		} else if (this.waarde < h.getWaarde()) {
			return "Verlies";
		} else {
			if (this.waarde == -1) {
				return "Verlies";
			} else if (this.isBlackjack() && !h.isBlackjack()) {
				return "Win";
			} else if (h.isBlackjack() && !this.isBlackjack()) {
				return "Verlies";
			} else {
				return "Gelijk";

			}
		}
	}

	/**
	 * Deze methode geeft hoeveel azen met waarde 11 in de hand zitten
	 * 
	 * @return het aantal azen in de hand
	 */
	public int getAzen() {
		return this.aantalAzen;
	}

	/**
	 * Reset de hand
	 */
	public void reset() {
		this.hand.clear();
		this.waarde = 0;
		this.aantalAzen = 0;
		this.dubbel = 1;
		this.status = "";
	}

	/**
	 * Geeft de grootte van de hand
	 * 
	 * @return de grootte van de hand
	 */
	public int size() {
		return hand.size();
	}

	/**
	 * Deze methode set de dubbel waarde op 2
	 */
	public void dubbel() {
		this.dubbel = 2;
	}

	/**
	 * Geeft de dubbel
	 * 
	 * @return 1 of 2
	 */
	public int getDouble() {
		return dubbel;
	}

	/**
	 * Geeft aan of het BlackJack is
	 * 
	 * @return true als het BlackJack is
	 */
	public boolean isBlackjack() {
		return this.size() == 2 && this.waarde == 21;
	}

	/**
	 * set de inzet
	 * 
	 * @param i
	 *            de inzet die wordt geset
	 */
	public void setInzet(int i) {
		this.inzet = i;
	}

	/**
	 * geeft de inzet
	 * 
	 * @return de inzet van de hand
	 */
	public int getInzet() {
		return this.inzet;
	}

	/**
	 * set de status
	 * 
	 * @param s
	 *            de status die wordt geset
	 */
	public void setStatus(String s) {

		this.status = s;
	}

	/**
	 * geeft de status
	 * 
	 * @return de status van de hand
	 */
	public String getStatus() {
		return this.getStatus();
	}

	/**
	 * De toString metode van Hand
	 */
	@Override
	public String toString() {
		String res = new String();
		for (int i = 0; i < this.hand.size(); i++) {
			res = res + this.hand.get(i).toString();
		}
		for (int j = this.hand.size(); j < 7; j++) {
			res = res + "   ";
		}
		if (this.getInzet() != 0) {
			res = res + " Inzet = " + this.inzet * this.dubbel;
		} else {

		}
		String status = this.status;
		switch (status) {
		case "Dood":
			res = res + " " + status;
			break;
		case "BLACKJACK":
			res = res + " " + status;
			break;
		case "Dubbel":
			res = res + " " + status;
			break;
		case "Gepast":
			res = res + " " + status;
			break;
		default:
		}
		return res;
	}

}
