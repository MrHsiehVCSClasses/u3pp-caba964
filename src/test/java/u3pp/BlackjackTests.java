package u3pp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class BlackjackTests {

    private static Card[] 
        hand1 = {}, 
        hand2 = { new Card("Clubs", "Ace"), new Card("Spades", "Jack") },
        hand3 = { new Card("Clubs", "2"), new Card("Diamonds", "10"), new Card("Spades", "7") },
        hand4 = { new Card("Clubs", "2"), new Card("Diamonds", "10"), new Card("Spades", "7"),
                new Card("Diamonds", "Ace"), new Card("Hearts", "3") },
        hand5 = { new Card("Clubs", "2"), new Card("Diamonds", "10"), new Card("Spades", "9") },
        hand6 = { new Card("Clubs", "2"), new Card("Diamonds", "10"), new Card("Spades", "4") },
        hand7 = { new Card("Diamonds", "10"), new Card("Spades", "7"), new Card("Hearts", "3") };

    private static Card[] 
        hand1Copy = {}, 
        hand2Copy = { new Card("Clubs", "Ace"), new Card("Spades", "Jack") },
        hand3Copy = { new Card("Clubs", "2"), new Card("Diamonds", "10"), new Card("Spades", "7") },
        hand4Copy = { new Card("Clubs", "2"), new Card("Diamonds", "10"), new Card("Spades", "7"),
                new Card("Diamonds", "Ace"), new Card("Hearts", "3") },
        hand5Copy = { new Card("Clubs", "2"), new Card("Diamonds", "10"), new Card("Spades", "9") },
        hand6Copy = { new Card("Clubs", "2"), new Card("Diamonds", "10"), new Card("Spades", "4") },
        hand7Copy = { new Card("Diamonds", "10"), new Card("Spades", "7"), new Card("Hearts", "3") };

    @Test
    void testCalcPoints() throws Exception {

        assertAll("Calculates appropriate hand totals", () -> assertEquals(0, Blackjack.points(hand1)),
                () -> assertEquals(21, Blackjack.points(hand2)),
                () -> assertEquals(19, Blackjack.points(hand3)),
                () -> assertEquals(33, Blackjack.points(hand4)));

        assertAll("CalcPoints does not alter parameters", 
                () -> assertEquals(Arrays.toString(hand1Copy), Arrays.toString(hand1)),
                () -> assertEquals(Arrays.toString(hand2Copy), Arrays.toString(hand2)),
                () -> assertEquals(Arrays.toString(hand3Copy), Arrays.toString(hand3)),
                () -> assertEquals(Arrays.toString(hand4Copy), Arrays.toString(hand4)));
    }

    @Test
    void testIsBust() throws Exception {
        assertAll("Correctly determines if a hand is a bust or not", () -> assertFalse(Blackjack.isBust(hand1)),
                () -> assertFalse(Blackjack.isBust(hand2)), () -> assertFalse(Blackjack.isBust(hand3)),
                () -> assertTrue(Blackjack.isBust(hand4)));

        assertAll("IsBust does not alter parameters", 
                () -> assertEquals(Arrays.toString(hand1Copy), Arrays.toString(hand1)),
                () -> assertEquals(Arrays.toString(hand2Copy), Arrays.toString(hand2)),
                () -> assertEquals(Arrays.toString(hand3Copy), Arrays.toString(hand3)),
                () -> assertEquals(Arrays.toString(hand4Copy), Arrays.toString(hand4)));
    }

    @Test
    void testIsBlackjack() throws Exception {

        assertAll("Correctly determines if a hand is a Blackjack or not",
                () -> assertFalse(Blackjack.isBlackjack(hand1)), 
                () -> assertTrue(Blackjack.isBlackjack(hand2)),
                () -> assertFalse(Blackjack.isBlackjack(hand4)), 
                () -> assertFalse(Blackjack.isBlackjack(hand5)));

        assertAll("isBlackjack does not alter parameters", 
                () -> assertEquals(Arrays.toString(hand1Copy), Arrays.toString(hand1)),
                () -> assertEquals(Arrays.toString(hand2Copy), Arrays.toString(hand2)),
                () -> assertEquals(Arrays.toString(hand3Copy), Arrays.toString(hand3)),
                () -> assertEquals(Arrays.toString(hand4Copy), Arrays.toString(hand4)),
                () -> assertEquals(Arrays.toString(hand5Copy), Arrays.toString(hand5)));
    }

    @Test
    void testDealerKeepHitting() throws Exception {

        assertAll("Correctly determines if a dealer should keep hitting",
                () -> assertTrue(Blackjack.doesDealerKeepHitting(hand1)),
                () -> assertFalse(Blackjack.doesDealerKeepHitting(hand2)),
                () -> assertFalse(Blackjack.doesDealerKeepHitting(hand4)),
                () -> assertTrue(Blackjack.doesDealerKeepHitting(hand6)));

        assertAll("dealerKeepHitting does not alter parameters", 
                () -> assertEquals(Arrays.toString(hand1Copy), Arrays.toString(hand1)),
                () -> assertEquals(Arrays.toString(hand2Copy), Arrays.toString(hand2)),
                () -> assertEquals(Arrays.toString(hand4Copy), Arrays.toString(hand4)),
                () -> assertEquals(Arrays.toString(hand6Copy), Arrays.toString(hand6)));
    }

    @Test
    void testDetermineResult() throws Exception {

        assertAll("Correctly determines the comparison of two hands",
                () -> assertEquals("User Loses", Blackjack.Result(hand4, hand2)),
                () -> assertEquals("User Loses", Blackjack.Result(hand1, hand2)),
                () -> assertEquals("User Loses", Blackjack.Result(hand6, hand7)),
                () -> assertEquals("User Wins", Blackjack.Result(hand2, hand4)),
                () -> assertEquals("User Wins", Blackjack.Result(hand2, hand7)),
                () -> assertEquals("User Wins", Blackjack.Result(hand7, hand6)),
                () -> assertEquals("User Pushes", Blackjack.Result(hand2, hand2)),
                () -> assertEquals("User Pushes", Blackjack.Result(hand7, hand7)));

        assertAll("dealerKeepHitting does not alter parameters", 
                () -> assertEquals(Arrays.toString(hand1Copy), Arrays.toString(hand1)),
                () -> assertEquals(Arrays.toString(hand2Copy), Arrays.toString(hand2)),
                () -> assertEquals(Arrays.toString(hand7Copy), Arrays.toString(hand7)),
                () -> assertEquals(Arrays.toString(hand6Copy), Arrays.toString(hand6)));       
    }

    // Blackjack.play() will be tested manually
}