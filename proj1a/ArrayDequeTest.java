import org.junit.Test;
import static org.junit.Assert.*;

/* @source https://github.com/czahie/CS61B/tree/master/Projects/proj1a */

public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }


    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }


    /** Test the ArrayDeque constructor. */
    public static void addisEmptySizeTest() {
        System.out.println("Running add/isEmpty/size test.");
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.addFirst("1");
        boolean passed = checkEmpty(false, deque.isEmpty());
        deque.addLast("2");
        deque.addLast("3");
        deque.addLast("4");
        deque.addLast("5");
        deque.addLast("6");
        deque.addLast("7");
        deque.addLast("8");
        passed = checkSize(8, deque.size()) && passed;
        ArrayDeque<Integer> deque2 = new ArrayDeque<>();
        passed = checkEmpty(true, deque2.isEmpty()) && passed;
        printTestStatus(passed);
        deque.printDeque();
    }


    public static void addRemoveTest() {
        System.out.println("Running add/remove test.");
        ArrayDeque<Integer> deque1 = new ArrayDeque<>();
        deque1.addLast(5);
        deque1.addLast(10);
        deque1.addLast(20);
        deque1.addLast(40);
        deque1.addFirst(1);
        int last1 = deque1.removeLast();
        int last2 = deque1.removeLast();
        int first1 = deque1.removeFirst();
        int first2 = deque1.removeFirst();
        if(last1 == 40 && last2 == 20 && first1 == 1 && first2 == 5) {
            printTestStatus(true);
        }
    }

    public static void getTest() {
        System.out.println("Running get test.");
        ArrayDeque<Integer> deque1 = new ArrayDeque<>();
        deque1.addFirst(5);
        deque1.addLast(10);
        deque1.addLast(20);
        deque1.addLast(40);
        deque1.addFirst(1);
        int get1 = deque1.get(0);
        int get2 = deque1.get(1);
        if(get1 == 5 && get2 == 10  ) {
            printTestStatus(true);
        }
    }

    public static void resizeTest() {
        System.out.println("Running get test.");
        ArrayDeque<Integer> deque1 = new ArrayDeque<>();
        int i = 0;
        while (i < 100) {
            deque1.addLast(i);
            i++;
        }
        System.out.println(" size after 100 adds "+deque1.size());
        deque1.printDeque();
        System.out.println();
        while (i > 10) {
            deque1.removeLast();
            i--;
        }
        System.out.println("size after 90m removes"+deque1.size());
        deque1.printDeque();
    }

/* TEST constructor from other*/
    public static void otherTest() {
        System.out.println("Running other test.");
        ArrayDeque<Integer> deque1 = new ArrayDeque<>();
        int i = 0;
        while (i < 100) {
            deque1.addLast(i);
            i++;
        }
        ArrayDeque<Integer> deque2 = new ArrayDeque<>(deque1);
        deque2.printDeque();
    }

    public static void main(String[] args) {
//        addisEmptySizeTest();
//          addRemoveTest();
//          getTest();
//        resizeTest();
        otherTest();

    }
}