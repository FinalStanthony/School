import java.util.Scanner;

/**
 * Deze klasse speelt een Blackjack spel.
 * 
 * @author Anthony Sniijders
 *
 */
public class BlackJack {
	private Player p;
	private Dealer d;
	private Cards c;

	/**
	 * De constructor van Blackjack
	 * 
	 * @param p
	 *            De speler
	 * @param d
	 *            De Deler
	 * @param c
	 *            Het stok kaarten
	 */
	public BlackJack(Player p, Dealer d, Cards c) {
		this.p = p;
		this.d = d;
		this.c = c;
	}

	/**
	 * Speelt een spel BlackJack
	 * 
	 * @param s
	 *            De input Scanner
	 */
	public void speel(Scanner s) {
		int dHand = this.d.getAantalHanden();
		int pHand = this.p.getAantalHanden();
		p.reset();
		d.reset();
		for (int i = 0; i < pHand; i++) {
			p.getHand(i).addCard(c.pop());
			p.getHand(i).addCard(c.pop());
		}
		d.getHand(dHand - 1).addCard(c.pop());
		for (int i = 0; i < 70; i++) {
			System.out.print("*");
		}
		System.out.println();
		System.out.println("Deler: " + d.getHand(dHand - 1));
		System.out.println();
		for (int i = 0; i < pHand; i++) {
			System.out.println(p.getNaam() + ", hand " + (i + 1) + ": " + p.getHand(i).toString());
		}
		for (int i = 0; i < 70; i++) {
			System.out.print("*");
		}
		System.out.println();

		for (int i = 0; i < pHand; i++) {
			this.playerturn(i, s);
		}
		boolean dealer = true;
		while (dealer) {
			if (d.minderdan17()) {
				d.getHand(dHand - 1).addCard(c.pop());
			} else {
				System.out.println("Deler: " + d.getHand(dHand - 1));
				System.out.println(
						"De deler is gepast en is geëindigd met " + d.getHand(dHand - 1).getWaarde() + " punten");
				dealer = false;
			}
		}
		for (int i = 0; i < 70; i++) {
			System.out.print("*");
		}
		System.out.println();
		System.out.println("Deler: " + d.getHand(dHand - 1));
		System.out.println();
		for (int i = 0; i < pHand; i++) {
			System.out.println(p.getNaam() + ", hand " + (i + 1) + ": " + p.getHand(i).toString());
		}
		for (int i = 0; i < 70; i++) {
			System.out.print("*");
		}
		System.out.println();
		for (int i = 0; i < pHand; i++) {
			this.uitbetaal(i, dHand - 1);
		}
		System.out.println("Jij hebt " + p.getKapitaal());
		if (c.size() < 104) {
			c.shuffle();
		}
	}

	/**
	 * De methode om uit te betalen
	 * 
	 * @param PHand
	 *            het nummer van de hand van de speler
	 * @param DHand
	 *            het nummer van de hand van de deler
	 */
	public void uitbetaal(int PHand, int DHand) {
		String vergelijk = p.getHand(PHand).vergelijk(d.getHand(DHand));
		if (vergelijk == "Win") {
			if (p.getHand(PHand).isBlackjack()) {
				p.setKapitaal(p.getInzet() * 1.5 + p.getKapitaal());
			} else {
				p.setKapitaal(p.getInzet() * p.getHand(PHand).getDouble() + p.getKapitaal());
				System.out.println(p.getNaam() + ", je wint hand " + (PHand + 1) + " met een inzet van "
						+ p.getHand(PHand).getInzet() * p.getHand(PHand).getDouble() + ".");
			}

		} else if (vergelijk == "Verlies") {
			p.setKapitaal(p.getKapitaal() - (p.getHand(PHand).getDouble() * p.getInzet()));
			System.out.println(p.getNaam() + ", je verliest hand " + (PHand + 1) + " met een inzet van "
					+ p.getHand(PHand).getInzet() * p.getHand(PHand).getDouble() + ".");
		} else {
			System.out.println(p.getNaam() + ", je speelt gelijk met hand " + (PHand + 1) + " met een inzet van "
					+ p.getHand(PHand).getInzet() * p.getHand(PHand).getDouble() + ".");
		}
	}

	/**
	 * Deze methode speelt de beurt van de speler
	 * 
	 * @param i
	 *            Het nummer van de hand van de speler
	 * @param s
	 *            De input Scanner
	 */
	public void playerturn(int i, Scanner s) {
		boolean playerturn = true;
		while (playerturn) {
			if (p.getHand(i).isBlackjack()) {
				p.getHand(i).setStatus("BLACKJACK");
				break;
			}
			System.out.println(p.getNaam() + ", hand " + (i + 1) + ": " + p.getHand(i).toString());
			if (p.getHand(i).size() == 2 && p.getKapitaal() >= p.getAantalHanden() * p.getInzet()) {
				System.out.println("Wat wil je doen? (h/p/d)");
			} else {
				System.out.println("Wat wil je doen? (h/p)");
			}
			String aktie = s.next();
			if (aktie.equals("h")) {
				p.getHand(i).addCard(c.pop());
				if (p.getHand(i).getWaarde() == -1 || p.getHand(i).getWaarde() == 21) {
					if (p.getHand(i).getWaarde() == 21) {
						p.getHand(i).setStatus("Gepast");
					} else {
						p.getHand(i).setStatus("Dood");
					}
					System.out.println("Nieuwe situatie: " + p.getHand(i).toString());
					playerturn = false;
				}
			} else if (aktie.equals("p")) {
				p.getHand(i).setStatus("Gepast");
				playerturn = false;
			} else if (aktie.equals("d")) {
				if (p.getKapitaal() >= p.getAantalHanden() * p.getInzet()) {
					p.getHand(i).dubbel();
					p.getHand(i).addCard(c.pop());
					p.getHand(i).setStatus("Dubbel");
					System.out.println("Nieuwe situatie: " + p.getHand(i).toString());
					playerturn = false;
				} else {
					System.out.println("Niet genoeg geld voor dubbel");
				}

			} else {
				System.out.println("Dit begrijp ik niet");
			}
		}
	}

}
