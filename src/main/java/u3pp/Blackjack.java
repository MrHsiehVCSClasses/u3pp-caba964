package u3pp;
import java.util.Scanner;

public class Blackjack {
String name;
Deck myDeck = new Deck();
Card[] usersHand = new Card[11];
Card[] dealersHand = new Card[11];
//when black jack gets called the deck shuffles.
public Blackjack(){
myDeck.shuffle();
}
//checks the cards in the deck and decides if it needs a shuffle.
private void Shuffles(){
    if (myDeck.numLeft() == 0) {
        myDeck.shuffle();
    }
}
//only goes through the cards that are numbers and not null.
private static int numOnly (Card[] hand){
    int numOnly = 0;
    for (int i = 0; i < hand.length; i++){
        if (hand[i] == null){
            numOnly = i-1;
            break;
        }
        if (hand[i] != null){
            numOnly += 1;
        }
    }
    return numOnly;
}
public static int points(Card[] hand){
    int total = 0;
    int value = 0;
        for (int i = 0; i < hand.length; i++){
            if (hand[i] == null){
                continue;
            }
            if (hand[i].getValue() == "Ace"){
                value = 11;
            }
            else if (hand[i].getValue() == "Jack"){
                value = 10;
            }
            else if (hand[i].getValue() == "Queen"){
                value = 10;
            }
            else if (hand[i].getValue() == "King"){
                value = 10;
            }
            else {
                value = Integer.parseInt(hand[i].getValue());
            }
            total = value + total;
        }
        System.out.println("Total points is: " + total);
        return total;
    }
//checks if the hand reached 21 points
public static boolean isBlackjack(Card[] hand){
    if (points(hand) == 21 && numOnly(hand) == 2 && (isBust(hand) == false)){
        System.out.println( "Blackjack: " + true);
        return true;
    }
    System.out.println( "Blackjack: " + false);
    return false;
}
// checks if points are less than 17 id the dealer can hit.
public static boolean doesDealerKeepHitting(Card[] hand){
    if (points(hand) <= 16){
        System.out.println("Dealer can continue hitting.");
        return true;
    }
    System.out.println("Dealer can no longer hit");
    return false;
}
// checks if the user has over 21 points
public static boolean isBust(Card[] hand){
    if (points(hand) > 21) {
        return true;
    }
    return false;
}
//sets everything to 0
private void reset(){
    for (int i = 0; i <= numOnly(usersHand); i++){
        usersHand[i].setValue("0");
    }
    for (int i = 0; i <= numOnly(dealersHand); i++){
        dealersHand[i].setValue("0");
    }
}
// starts game by dealing cards 2 times.
private void startGame(){
    Shuffles();
    usersHand[0] = myDeck.deal();
    usersHand[1] = myDeck.deal();
    dealersHand[0] = myDeck.deal();
    dealersHand[1] = myDeck.deal();
    for (int i = 2; i < 11; i++){
        usersHand[i] = null;
    }
    for (int i = 2; i < 11; i++){
        dealersHand[i] = null;
    }
}
//determines the results of the game
public static String Result(Card[] userHand, Card[] dealerHand){
    if (points(userHand) == points(dealerHand)){
        return "User Pushes";
    }
    if ((points(userHand) < points(dealerHand) || isBust(userHand) == true) && isBust(dealerHand) == false){
        return "User Loses";
    }
    return "User Wins";
}
private void NumInHand(Card[] userHand, Card[] dealerHand){
    System.out.println(name + "'s " + "hand: ");
    for (int i = 0; i <= numOnly(userHand); i++){
        System.out.println(userHand[i]);
    }
    System.out.println("\nDealer's hand: ");
    for (int i = 0; i <= numOnly(dealerHand); i++){
        System.out.println(dealerHand[i]);
    }
    System.out.println("(Press return to continue)");
    }
public void Start(Scanner scanner) {
    System.out.println("What is your name.");
    name = scanner.nextLine();
    System.out.println("Hi " + name + " lets play blackjack.");
    scanner.nextLine();
    startGame();
    NumInHand(usersHand, dealersHand);
    scanner.nextLine(); 
    if (isBust(dealersHand) == true){
        System.out.println("dealer busted");
        System.out.println(name + " wins");
        System.out.println("Want to continue? Insert yes or no");
        if (scanner.nextLine() .equals ("yes")){
            reset();
            Start(scanner);
        }
        else if (scanner.nextLine() .equals ("no")){
            System.out.println("Thanks for playing");
        }
        else {
            System.out.println("You didn't type yes or no ):");
        }
    }
    if (isBlackjack(dealersHand) == true){
        System.out.println("Dealer got blackjack");
        System.out.println("Want to continue? Insert yes or no");
        if (scanner.nextLine() .equals ("yes")){
            reset();
            Start(scanner);
        }
        else if (scanner.nextLine() .equals ("no")){
            System.out.println("Thanks for playing");
        }
        else {
            System.out.println("You didn't type yes or no ):");
        }
    }
    if (isBlackjack(usersHand) == true) {
        System.out.println(name + " won blackjack.");
        System.out.println("Want to continue? Insert yes or no");
        if (scanner.nextLine() .equals ("yes")){
            reset();
            Start(scanner);
        }
        else if (scanner.nextLine() .equals ("no")){
                System.out.println("Thanks for playing");
            }
            else {
                System.out.println("You didn't type yes or no ):");
            }
        }
    else {
        System.out.println("Will you hit or stay? Insert hit or stay");
        if (scanner.nextLine() .equals ("hit")){
            System.out.println("you are going to hit");
            Shuffles();
            usersHand[numOnly(usersHand) + 1] = myDeck.deal();
            NumInHand(usersHand, dealersHand);
            if (points(usersHand) == 21){
                Result(usersHand, dealersHand);
                System.out.println("Want to continue? Insert yes or no");
                if (scanner.nextLine() .equals ("yes")){
                    reset();
                    Start(scanner);
                }
                else if (scanner.nextLine() .equals ("no")){
                    System.out.println("Thanks for playing.");
                }
                else {
                    System.out.println("You didn't type yes or no ):");
                }
            }
            scanner.nextLine();
    if (isBust(usersHand) == true){
        System.out.println("Sorry " + name + " you busted");
        System.out.println("Dealer won");
        System.out.println("Will you continue? Insert yes or no");
        if (scanner.nextLine() .equals ("yes")){
            reset();
            Start(scanner);
                }
        else if (scanner.nextLine() .equals ("no")){
            System.out.println("Thanks for playing");
                }
        else {
            System.out.println("You didn't type yes or no ):");
                }   
        }
        else if (points(usersHand) <= 21){
                System.out.println("Will you hit or stay? Insert hit or stay");
        if (scanner.nextLine() .equals ("hit")){
                System.out.println("you are going to hit");
                Shuffles();
                usersHand[numOnly(usersHand) + 1] = myDeck.deal();
                NumInHand(usersHand, dealersHand);
                if (points(usersHand) == 21){
                    Result(usersHand, dealersHand);
                    System.out.println("Will you continue? Insert yes or no");
                    if (scanner.nextLine() .equals ("yes")){
                        reset();
                        Start(scanner);
                    }
                    else if (scanner.nextLine() .equals ("no")){
                        System.out.println("Thanks for playing");
                    }
                    else {
                        System.out.println("You didn't type yes or no ):");
                    }
                    }
                    scanner.nextLine();
                    if (isBust(usersHand) == true){
                        System.out.println("Sorry " + name + " you busted");
                        System.out.println("dealer won");
                        Result(usersHand, dealersHand);
                        System.out.println("Will you continue? Insert yes or no");
                        if (scanner.nextLine() .equals ("yes")){
                            reset();
                            Start(scanner);
                        }
                        else if (scanner.nextLine() .equals ("no")){
                            System.out.println("Thanks for playing");
                        }
                        else {
                            System.out.println("You didn't type yes or no ):");
                        }   
                    }
                }
                else if (scanner.nextLine() .equals ("stay")){
                    int num = 3;
                    while (doesDealerKeepHitting(dealersHand) == true) {
                        Shuffles();
                        dealersHand[numOnly(dealersHand) + 1] = myDeck.deal();
                        num +=1;
                        if (isBust(usersHand) == true){
                                System.out.println("Sorry " + name + " you busted");
                                System.out.println("dealer won");
                                Result(usersHand, dealersHand);
                                System.out.println("Will you continue? Insert yes or no");
                                if (scanner.nextLine() .equals ("yes")){
                                    reset();
                                    Start(scanner);
                                }
                                else if (scanner.nextLine() .equals ("no")){
                                    System.out.println("Thanks for playing");
                                }
                                else {
                                    System.out.println("You didn't type yes or no ):");
                                }
                            }
                        }
                        NumInHand(usersHand, dealersHand);
                        scanner.nextLine();
                        System.out.println(Result(usersHand, dealersHand));
                        System.out.println("Will you continue? Insert yes or no");
                        if (scanner.nextLine() .equals ("yees")){
                            reset();
                            Start(scanner);
                        }
                        else if (scanner.nextLine() .equals ("no")){
                            System.out.println("Thanks for playing");
                        }
                        else {
                            System.out.println("You didn't type yes or no ):");
                        }
                    }
                    else {
                        System.out.println("You didn't type yes or no ):");
                    }
                }
            }
            else if (scanner.nextLine() .equals ("stay")){
                int num = 3;
                while (doesDealerKeepHitting(dealersHand) == true) {
                    Shuffles();
                    dealersHand[numOnly(dealersHand) + 1] = myDeck.deal();
                    num +=1;
                    if (isBust(usersHand) == true){
                        System.out.println("Sorry " + name + " you busted");
                        System.out.println("dealer won");
                        System.out.println("Will you continue? Insert yes or no");
                        if (scanner.nextLine() .equals ("yes")){
                            reset();
                            Start(scanner);
                        }
                        else if (scanner.nextLine() .equals ("no")){
                            System.out.println("Thanks for playing");
                        }
                        else {
                            System.out.println("You didn't type yes or no ):");
                        }
                    }
                }
                NumInHand(usersHand, dealersHand);
                scanner.nextLine();
                System.out.println(Result(usersHand, dealersHand));
                System.out.println("Will you continue? Insert yes or no");
                if (scanner.nextLine() .equals ("yes")){
                    reset();
                    Start(scanner);
                }
                else if (scanner.nextLine() .equals ("no")){
                    System.out.println("Thanks for playing");
                }
                else {
                    System.out.println("You didn't type yes or no ):");
                }
            }
            else {
                System.out.println("You didn't type yes or no ):");
            }
        }
    }
}