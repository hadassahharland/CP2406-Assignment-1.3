package src;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.Arrays;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;

/**
 * Created by Hadassah Harland on 21/09/2016.
 * This class generates an object belonging to a player that is an array of card objects representing the player's hand
 * for the game of "Project Mineral: Super Trump"
 */

public class PlayerHand {
    ArrayList<Card> hand;
    boolean winCondition;

    public PlayerHand() {
        // create empty ArrayList in which to store Cards
        // cards are added to the hand not at construction but in the deal() function in game
        hand = new ArrayList<>();
        this.winCondition = false;
    }

    public void show()  {
        System.out.println("Your hand contains the following cards: ");
        for (int i = 0; i < length(hand); i++)  {
            hand.get(i).toString();
        }
    }

    public void removeCard(int cardIndex)  {
        for (int i = 0; i < length(hand); i++)  {
            if (hand.get(i).cardIndex == cardIndex)  {
                hand.remove(i);
                // Win condition is satisfied when the player's hand is empty
                if (length(hand)==0)  {  this.winCondition = true;  }
            }
        }
    }

    public void addCard(Card card)  {
        this.hand.add(card);
    }


}
