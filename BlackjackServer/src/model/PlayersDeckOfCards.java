package model;

public class PlayersDeckOfCards {

	public static final String TYPE_CLASS = "Player's deck of cards";
	public String type = TYPE_CLASS;
	
	public static final int MAX_CARDS = 5;
	
	private Card[] cards;
	
	public PlayersDeckOfCards() {
		cards=new Card[5];
	}
	
	public void addCard(Card card) {
		for(int i=0;i<MAX_CARDS;i++) {
			if(cards[i]==null) {
				cards[i]=card;
				break;
			}
		}
	}
	
	public int countValue() {
		int cont = 0;
		for(int i=0;i<MAX_CARDS;i++) {
			if(cards[i]!=null) {
				cont+=cards[i].getNumber();
			}
		}
		return cont;
	}
}
