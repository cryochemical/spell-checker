/**
 * @ author Stephen Harden
 */
public class SpellingTrie {

    private SpellingNode root;

    public SpellingTrie() {
        root = new SpellingNode(' ');
    }

    public SpellingNode getRoot() {
        return root;
    }

    public void addWord(String word) {

        // add a new word to the tree
        char[] letters = word.toCharArray();
        SpellingNode thisNode = root;

        for (int i = 0; i < letters.length; i++) {
            thisNode.addChild(letters[i]); // Add a new child
            thisNode = thisNode.getChildAt(letters[i]); // Update the reference so it points to the new child

            if (i == letters.length - 1)    // If this is the last letter of the word, mark a completed word
                thisNode.setCorrect();
        }
    }

    public boolean checkWord(String word) {

        char[] letters = word.toCharArray();
        SpellingNode thisNode = root;

        // loop through every letter of this word
        for (int i = 0; i < letters.length; i++) {
            // if a letter contained in this word is not found in the proper position in the dictionary,
            // immediately return false
            if (thisNode.getChildAt(letters[i]) == null) {
                return false;
            }
            thisNode = thisNode.getChildAt(letters[i]);
        }
        if (thisNode.getCorrect()) // check the last letter to see if it denotes the end of a word
            return true;
        return false; // if the letters form a partial word, return false
    }

    public void printWords(String subWord, SpellingNode c) {
        // Recursive method that prints all words alphabetically
        if (c.getCorrect())
            System.out.println(subWord);

        for (int i = 0; i < 26; i++) {
            if (c.getChildAt(i) != null) {
                // add the next letter to the word and recurse
                printWords(subWord + (char)(i+97), c.getChildAt(i));
                // NOTE: I could replace (char)(i+97) with a getValue() call on the child
            }
        }
    }



}
