import java.util.Scanner;

/**
 * Deze klasse stelt vragen aan de gebruiker en start een BlackJack spel
 * 
 * @author Anthony Snijders
 *
 */
public class Casino {
	private int startkapitaal = 1000;

	/**
	 * Start een nieuw spel
	 */
	public void newspel() {
		System.out.println("Welkom in het Snijders Casino! Het casino waar JIJ altijd wint!");
		System.out.println();
		System.out.println("Commando's:");
		System.out.println("p = passen");
		System.out.println("h = een kaart hitten");
		System.out.println("d = inzet verdubbelen");
		System.out.println();
		Scanner s = new Scanner(System.in);
		System.out.println("Wat is jouw naam?");
		String naam = s.next();
		System.out.println("Hallo " + naam + ", je startkapitaal is € " + startkapitaal);
		int aantal;
		while (true) {
			System.out.println("Met hoeveel handen wil je spelen? (1-5)");
			if (s.hasNextInt()) {
				aantal = s.nextInt();
				if (0 < aantal && aantal < 6) {
					break;
				} else {
					System.out.println("Dit getal ligt niet tussen de 1 en 5");
				}
			} else {
				System.out.println("Dit begrijp ik niet");
				s.next();
			}
		}

		Double maxinzet = Math.floor(startkapitaal / aantal);
		int inzet;
		while (true) {
			System.out.println("Wat is je inzet per hand? (1-" + maxinzet.intValue() + ")");
			if (s.hasNextInt()) {
				inzet = s.nextInt();
				if (0 < inzet && inzet < maxinzet.intValue() + 1) {
					break;
				} else {
					System.out.println("Dit getal ligt niet tussen de 1 en " + maxinzet.intValue());
				}
			} else {
				System.out.println("Dit begrijp ik niet");
				s.next();
			}
		}

		Player p = new Player(naam, aantal, inzet, startkapitaal);
		for (int i = 0; i < aantal; i++) {
			Hand h = new Hand();
			h.setInzet(inzet);
			p.addHand(h);

		}
		Dealer d = new Dealer();
		d.addHand(new Hand());
		Cards c = new Cards();
		BlackJack B = new BlackJack(p, d, c);
		boolean spelen = true;
		while (spelen) {
			B.speel(s);
			if (p.getKapitaal() < p.getInzet() * p.getAantalHanden()) {
				System.out.println("Je hebt niet genoeg geld voor nog een potje");
				System.out.println("Tot ziens!");
				spelen = false;
				break;
			}
			System.out.println("Wil je nog een keer spelen?(y/n)");
			String opnieuw = s.next();
			if (opnieuw.equals("n")) {
				System.out.println("Tot ziens!");
				spelen = false;
			}

		}
		s.close();
	}
}
