package edu.iupui.elliott.hipsterbands;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by robelliott
 */

public class BandName {

    private String bandName;
    private static String sNameArray1[] = {"Arial", "Bauhaus", "Calibri", "Desdemona", "Exo", "Futura", "Gill Sans", "Helvetica", "Impact", "Janda", "Krungthep", "Lucida", "Myriad", "Newon", "Optima", "Palatino", "Quigly", "Rockwell", "Subway", "Tahoma", "Ultra", "Verdana", "Wingding", "Xefora", "Yukon", "Zapf"};
    private static String sNameArray2[] ={"Audio", "Beard", "Combover", "Digital", "Eight-track", "Freelance", "Guacamole", "Hang Glider", "Incident", "Jump Suit", "Kybosh", "Lounge", "Moustache", "Novel", "Owl", "Plaid", "Quiff", "Retro", "Spectacles", "Trampoline", "Ukulele", "Video", "Wheat Grass", "Xbox", "Yogurt", "Zombie"};

    public BandName(String firstName, String lastName) {
        String firstPartOfName;
        String lastPartOfName;
        if (firstName.length() > 2) {
            firstPartOfName = sNameArray1[findLetterFromSource(firstName, 1)];
        }
        else {
            Random r = new Random();
            firstPartOfName = sNameArray1[r.nextInt(25)];
        }

        if (lastName.length() > 4) {
            lastPartOfName = sNameArray2[findLetterFromSource(lastName, 3)];
        }
        else {
            Random r = new Random();
            lastPartOfName = sNameArray1[r.nextInt(25)];

        }

        bandName = firstPartOfName + " " + lastPartOfName;
    }

    private static int findLetterFromSource(String sourceString, int characterPosition) {
        char[] sourceCharacters = sourceString.toCharArray();
        char searchCharacter = sourceCharacters[characterPosition];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        return alphabet.indexOf(searchCharacter);
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }
}
