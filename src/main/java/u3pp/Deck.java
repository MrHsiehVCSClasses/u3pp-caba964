package u3pp;
import java.util.Random;

public class Deck {
//keeps track of the number of cards that have been discarded
private int discardAmount = 0;
Random myRandom = new Random(); 
private Card[] Card1 = new Card[52];
// creates a deck of 52 cards that is put into the array.
public  Deck() {
    int index = 0;
    for (int i = 0; i < Card.VALUES.length; i++){
        for (int a = 0; a < Card.SUITS.length; a++){
            Card myCard = new Card(Card.SUITS[a], Card.VALUES[i]);
            Card1 [index] = myCard;
            index += 1;
            }
        }
}
//shuffles the cards in the array
public void shuffle() {
    discardAmount = 0;
    for (int i = 0; i < Card1.length; i++){
        int ran;
            ran = myRandom.nextInt(Card1.length);
        Card temp;
        temp = Card1[ran];
        Card1[ran] = Card1[i];
        Card1[i] = temp;
    }
    discardAmount = 0;
}
//checks if their are enough cards in the deck.
public Card deal() {
    if (discardAmount == 52){
        System.out.println("No cards are left. Deck will be shuffled.");
        return null;
    }
    discardAmount += 1;
    return Card1[51 - (discardAmount - 1)];
}
    public int numLeft(){
        return (Card1.length - discardAmount);
    }
}
