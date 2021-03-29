package model;

public class PlayersDeckOfCards {

	public static final String TYPE_CLASS = "Player's deck of cards";
	public String type = TYPE_CLASS;
	
	public static final int MAX_CARDS = 5;
	
	private CardSpaceFullListener cardSpaceListener;
	private Card[] cards;
	
	public PlayersDeckOfCards() {
		cards=new Card[MAX_CARDS];
	}
	
	public void addCard(Card card) {
		for(int i=0;i<MAX_CARDS;i++) {
			if(cards[i]==null) {
				cards[i]=card;
				if(i==MAX_CARDS-1)cardSpaceListener.cardSpaceFull();
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
	
	public void setCardSpaceListener(CardSpaceFullListener cardSpaceListener) {
		this.cardSpaceListener = cardSpaceListener;
	}

	public interface CardSpaceFullListener{
		public void cardSpaceFull();
	}
}
