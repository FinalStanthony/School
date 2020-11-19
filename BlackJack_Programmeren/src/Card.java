/**
 * De card klasse. Een kaart heeft een kleur(h,r,k,s) een waarde(1t/m10,K,B,V,A_
 * en een getal(1t/m 11)
 * 
 * @author Anthony Snijders
 *
 */
public class Card {

	private char kleur;
	private String waarde;
	private int getal;

	/**
	 * De constructor van Card
	 * 
	 * @param kleur
	 *            De kleur van de kaart
	 * @param waarde
	 *            De waarde van de kaart
	 */
	public Card(char kleur, String waarde) {
		this.kleur = kleur;
		this.waarde = waarde;
		if (waarde == "B" || waarde == "V" || waarde == "K") {
			this.getal = 10;
		} else if (waarde == "A") {
			this.getal = 11;
		} else {
			int getal = Integer.valueOf(waarde);
			this.getal = getal;
		}

	}
	
	public char getKleur(){
		return this.kleur;
	}

	/**
	 * De get functie van getal
	 * 
	 * @return het getal(1 t/m 11)
	 */
	public int getGetal() {
		return getal;
	}

	/**
	 * De toString methode van Card
	 */
	@Override
	public String toString() {
		if (!this.waarde.equals("10")) {
			return "  " + this.waarde;
		} else {
			return " " + this.waarde;

		}
	}

}
