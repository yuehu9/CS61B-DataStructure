import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("@{*noon*{@"));
        assertFalse(palindrome.isPalindrome("moeee"));
        assertTrue(palindrome.isPalindrome("123321"));
        assertTrue(palindrome.isPalindrome(" n "));

    }

    @Test
    public void testOffByOne() {
        CharacterComparator obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("ab",obo));
        assertTrue(palindrome.isPalindrome("abb",obo));
        assertTrue(palindrome.isPalindrome("& %",obo));
    }

    @Test
    public void testOffByN() {
        CharacterComparator obn = new OffByN(5);
        assertTrue(palindrome.isPalindrome("af",obn));
        assertTrue(palindrome.isPalindrome("f a",obn));
        assertTrue(palindrome.isPalindrome("AFAF",obn));
    }
}
