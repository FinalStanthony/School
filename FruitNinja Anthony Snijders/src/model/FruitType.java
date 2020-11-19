package model;

public enum FruitType {
	APPLE("Groot"), ORANGE("Groot"), STRAWBERRY("Klein");

	private String groote;

	private FruitType(String groote) {
		this.groote = groote;
	}

	public String getGroote() {
		return groote;
	}
}
