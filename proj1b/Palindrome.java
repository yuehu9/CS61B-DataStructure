/**@author Yue
 *
 */

/* Given a String, wordToDeque should return a Deque
 where the characters appear in the same order as in the String.
 */
public class Palindrome{
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque= new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    /**The isPalindrome method should return true if the given word is
    a palindrome, and false otherwise. A palindrome is defined as a word
    that is the same whether it is read forwards or backwards. */
    public boolean isPalindrome(String word) {
        Deque<Character> WordDeque= wordToDeque(word);
        return dequeIsPal(WordDeque);
    }

    /* helper method for isPalindrome, check if deque is Parlindrome. */
    private boolean dequeIsPal(Deque<Character> WordDeque) {
        int Size = WordDeque.size();
        if (Size == 0 || Size == 1) {
            return true;
        }
        if (WordDeque.removeFirst() == WordDeque.removeLast()) {
            return dequeIsPal(WordDeque);
        }
        return false;
    }

    private boolean dequeIsPal(Deque<Character> WordDeque, CharacterComparator cc) {
        int Size = WordDeque.size();
        if (Size == 0 || Size == 1) {
            return true;
        }
        if (cc.equalChars(WordDeque.removeFirst(),WordDeque.removeLast())) {
            return dequeIsPal(WordDeque, cc);
        }
        return false;
    }

/* The method will return true if the word is a palindrome according to the
 character comparison test provided by the CharacterComparator passed in as
  argument cc.*/
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> WordDeque= wordToDeque(word);
        return dequeIsPal(WordDeque, cc);
    }


}
