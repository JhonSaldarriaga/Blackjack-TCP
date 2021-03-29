package model;

public class DealersDeckOfCards {

	private final Card[] cards = 
		{new Card(11, "AS"),new Card(11, "K"),new Card(11, "J"),new Card(11, "Q"),
		new Card(2, "2p"),new Card(3, "3p"),new Card(4, "4p"),new Card(5, "5p"),
		new Card(6, "6p"),new Card(7, "7p"),new Card(8, "8p"),new Card(9, "9p"),new Card(10, "10p"),
		new Card(2, "2d"),new Card(3, "3d"),new Card(4, "4d"),new Card(5, "5d"),
		new Card(6, "6d"),new Card(7, "7d"),new Card(8, "8d"),new Card(9, "9d"),new Card(10, "10d"),
		new Card(2, "2c"),new Card(3, "3c"),new Card(4, "4c"),new Card(5, "5c"),
		new Card(6, "6c"),new Card(7, "7c"),new Card(8, "8c"),new Card(9, "9c"),new Card(10, "10c"),
		new Card(2, "2t"),new Card(3, "3t"),new Card(4, "4t"),new Card(5, "5t"),
		new Card(6, "6t"),new Card(7, "7t"),new Card(8, "8t"),new Card(9, "9t"),new Card(10, "10t")};
	
	public Card generateRandomCard() {
		return cards[(int)(Math.random()*cards.length)];
	}
}
