package src;

/**
 * Created by Hadassah on 25/09/2016.
 * This class is a child class of the abstract class Card which holds properties of the trump cards in class variables.
 */
public class TrumpCard extends Card {
    String function;

    public TrumpCard(int cardIndex, String fileName, String imageName, String function, String name) {
        super(cardIndex, fileName, imageName, name);
        this.function = function;
    }

    public String toString()  {
        return name + " (Trump card): " + function;
    }

    public boolean validPlay(Card lastCard, int currentCategoryIndex) {
        // always a valid play to play a trump card
        return true;
    }
}
