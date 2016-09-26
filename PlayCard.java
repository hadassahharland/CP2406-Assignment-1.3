package src;

/**
 * Created by Hadassah on 25/09/2016.
 * This class is a child class of the abstract class Card which holds properties of the play cards in class variables.
 */
public class PlayCard extends Card {
    String chemicalStructure;
    String classification;
    String cleavage;
    String crystalAbundance;
    String crystalSystem;
    String economicValue;
    String hardness;
    String occurance;
    String specificGravity;

    public PlayCard(int cardIndex, String chemicalStructure, String classification, String cleavage,
                    String crystalAbundance, String crystalSystem, String economicValue, String fileName,
                    String hardness, String imageName, String occurance, String specificGravity, String name) {
        super(cardIndex, fileName, imageName, name);
        this.chemicalStructure = chemicalStructure;
        this.classification = classification;
        this.cleavage = cleavage;
        this.crystalAbundance = crystalAbundance;
        this.crystalSystem = crystalSystem;
        this.economicValue = economicValue;
        this.hardness = hardness;
        this.occurance = occurance;
        this.specificGravity = specificGravity;
    }

    public String toString()  {
        return name + " (" + chemicalStructure + "): Hardness: " + hardness + ", Specific Gravity: " + specificGravity
                + ", Crystal Abundance: " + crystalAbundance + ", Economic Value: " + economicValue;
    }
}
