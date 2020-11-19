import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * De klasse Cards, deze schudt de kaarten en deelt de kaarten
 * 
 * @author Anthony Snijders
 *
 */
public class Cards {

	private List<Card> stok = new ArrayList<Card>();
	private List<Card> opVolgorde = new ArrayList<Card>();
	private char[] kleur = { 'h', 'r', 'k', 's' };
	private String[] waarde = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "B", "V", "K", "A" };

	/**
	 * De constructor van Cards. Deze schudt 6 stokken kaarten.
	 */
	public Cards() {
		this.shuffle();
	}

	/**
	 * Deze methode deelt de kaarten
	 * 
	 * @return De bovenste kaart
	 */
	public Card pop() {
		Card res = stok.get(0);
		stok.remove(0);
		return res;
	}

	/**
	 * Geeft de grootte van het stok kaarten
	 * 
	 * @return de grootte van het stok kaarten
	 */
	public int size() {
		return stok.size();
	}

	/**
	 * Schudt de kaarten
	 */
	public void shuffle() {
		stok.clear();
		opVolgorde.clear();
		for (int x = 0; x < 6; x++) {
			for (int i = 0; i < this.waarde.length; i++) {
				for (int j = 0; j < this.kleur.length; j++) {
					Card C = new Card(this.kleur[j], this.waarde[i]);
					this.opVolgorde.add(C);
				}
			}
		}
		while (!this.opVolgorde.isEmpty()) {
			int random = ThreadLocalRandom.current().nextInt(0, this.opVolgorde.size());
			stok.add(this.opVolgorde.get(random));
			this.opVolgorde.remove(random);
		}
	}

}
