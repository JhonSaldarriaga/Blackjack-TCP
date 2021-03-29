package model;

public class Card {

	public static final String TYPE_CLASS = "Card";
	public String type = TYPE_CLASS;
	
	private int number;
	private String logo;
	
	public Card() {}
	public Card(int number, String logo) {
		this.number=number;
		this.logo=logo;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
}
