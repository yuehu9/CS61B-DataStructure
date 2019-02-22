public class OffByOne implements CharacterComparator {

    /** equalChars returns true for characters that are
     *  different by exactly one. */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return diff == 1 || diff == -1;
    }
}
