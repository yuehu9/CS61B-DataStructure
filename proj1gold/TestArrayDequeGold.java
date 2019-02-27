import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    /**randomly call StudentArrayDeque and ArrayDequeSolution methods
    until they disagree on an output
     @source StudentArrayDequeLauncher. used its StdRsndom syntax. */

    @Test
    public void firstTest() {
        StudentArrayDeque<Integer> stud = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> rightd = new ArrayDequeSolution<>();
        String message = "Your operation sequence was:\n";

        for (int i = 0; i < 500; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.25) {
                stud.addLast(i);
                rightd.addLast(i);
                message += "addLast(";
                message += i;
                message += ")\n";
                assertEquals(message,rightd.get(rightd.size()-1),stud.get(stud.size()-1));

            } else if (numberBetweenZeroAndOne <0.5) {
                stud.addFirst(i);
                rightd.addFirst(i);
                message += "addFirst(";
                message += i;
                message += ")\n";
                assertEquals(message,rightd.get(0),stud.get(0));
            } else if (numberBetweenZeroAndOne < 0.75) {
                if (rightd.size() == 0) {continue;};
                Integer act = stud.removeFirst();
                Integer exp = rightd.removeFirst();
                message += "removeFirst()\n";
                assertEquals(message,exp,act);
            } else {
                if (rightd.size() == 0) {continue;};
                Integer act = stud.removeLast();
                Integer exp = rightd.removeLast();
                message += "removeLast()\n";
                assertEquals(message,exp,act);
            }
        }

    }

}
