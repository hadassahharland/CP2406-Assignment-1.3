/**
 * Created by Hadassah on 21/09/2016.
 *
 */
public abstract class Card {
    int cardIndex;
    String fileName;
    String imageName;
    String name;

    public Card(int cardIndex, String fileName, String imageName, String name) {
        this.cardIndex = cardIndex;
        this.fileName = fileName;
        this.imageName = imageName;
        this.name = name;
    }


    public abstract String toString();

    public abstract boolean validPlay(Card lastCard, int currentCategoryIndex);
}
