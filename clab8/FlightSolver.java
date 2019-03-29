import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    private PriorityQueue<Flight> pQueue;
    Comparator<Flight> flightComparator = (Flight a, Flight b) -> {
        int diff = Integer.compare(a.passengers, b.passengers);
        return -diff;
    };

    public FlightSolver(ArrayList<Flight> flights) {
        pQueue = new PriorityQueue<>(flights.size(),flightComparator);
        for(Flight my : flights) {
            pQueue.add(my);
        }
    }


    public int solve() {
        Flight maxF = pQueue.peek();
        return maxF.passengers;
    }

}
