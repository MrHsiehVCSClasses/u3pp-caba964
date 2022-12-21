package u3pp;

public class Main 
{
    public static void main(String[] args) {
        
        Deck myDeck = new Deck();
        Blackjack myBlackjack = new Blackjack();
        Scanner myScanner = new Scanner(System.in);
        myDeck.shuffle();
        myBlackjack.Start(myScanner);
    }
}

