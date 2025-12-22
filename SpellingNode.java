/**
 * @ author Stephen Harden
 */
public class SpellingNode {

    private char value;
    private SpellingNode[] children;
    private boolean correctWord;

    public SpellingNode(char letter) {
        value = letter;
        children = new SpellingNode[26];
        correctWord = false;
    }

    public void setCorrect() {
        correctWord = true;
    }

    public boolean getCorrect() {
        return correctWord;
    }

    public boolean addChild(char letter) {

        // add A to 0 index or C to 2 index
        int charIndex = letter - 97; // subtract 97 to so that all lowercase letters have the values 0-25
        if (children[charIndex] == null) {
            children[charIndex] = new SpellingNode(letter);
            return true;
        }
        // If the letter has already been added to children, return false
        return false;
    }

    public SpellingNode getChildAt(char val) {
        return children[val-97];
    }

    // Override the previous method with an int input
    public SpellingNode getChildAt(int val) {
        return children[val];
    }

}
