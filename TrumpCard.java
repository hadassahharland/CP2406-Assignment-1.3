package src;

/**
 * Created by Hadassah on 25/09/2016.
 *
 */
public class TrumpCard extends Card {
    String function;

    public TrumpCard(int cardIndex, String fileName, String imageName, String function, String name) {
            super(cardIndex, fileName, imageName, name);
        this.function = function;
    }

    public String toString()  {
        return "***Card details***";
    }
}
