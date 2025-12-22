import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @ author Stephen Harden
 */
public class SpellChecker {

    private static SpellingTrie myTree;

    public static int checkWords(String filename) throws FileNotFoundException {
        File f = new File(filename);
        Scanner fileScan = new Scanner(f);
        int numIncorrectWords = 0;

        while (fileScan.hasNext()) {
            // Grab the next word, strip all punctuation and make it lower case
            String s = fileScan.next().toLowerCase();
            s = s.replaceAll("\\p{Punct}", "");
            // Check to see if this is a proper word, and print the result
            if (!myTree.checkWord(s)) {
                System.out.println(s);
                numIncorrectWords++;
            }
        }
        return numIncorrectWords;
    }

    public static void readWords(String filename) throws FileNotFoundException {
        File f = new File(filename);
        Scanner fileScan = new Scanner(f);

        while (fileScan.hasNext()) {
            // Grab the next word, strip all punctuation and make it lower case
            String s = fileScan.next().toLowerCase();
            s = s.replaceAll("\\p{Punct}", "");
            // add this word to the dictionary
            myTree.addWord(s);
        }
    }

    public static void main(String[] args) {
        // Create a tree for storing words
        myTree = new SpellingTrie();
        // Add words from a custom dictionary to compare against
        try {
            readWords("my_dictionary");
        } catch (FileNotFoundException e) {
            System.out.println("No file was found. ");
        }

        // print the dictionary
        System.out.println("The dictionary contains these words: ");
        myTree.printWords("", myTree.getRoot());
        System.out.println();

        // Check a selection of words to see if they are within the dictionary
        System.out.println("Misspelled words: ");
        try {
            checkWords("test_words");
        } catch (FileNotFoundException e) {
            System.out.println("No file was found. ");
        }
    }
}
