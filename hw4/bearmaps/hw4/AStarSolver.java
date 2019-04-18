/** @author Yuehu
 * Memory-Optimizing A*.
 * April. 2019
 */

package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private double timeSpent;
    private SolverOutcome outcome;
    private double solWeight = 0;
    private List<Vertex> solution;
    private int numState = 0;
    private HashMap<Vertex, Double> distToS;
    private HashMap<Vertex, Vertex> parentOfV;
    private ExtrinsicMinPQ<Vertex> PQ;
    // a PQ where each vertex v will have priority p equal to the sum of
    // v’s distance from the source plus the heuristic estimate from v to the goal



    /** Constructor which finds the solution, computing everything necessary for all
     *  other methods to return their results in constant time. Note that timeout passed
     *  in is in seconds.*/
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        // initiate
        Stopwatch sw = new Stopwatch();
        PQ = new ArrayHeapMinPQ<>();
        distToS = new HashMap<>();
        parentOfV = new HashMap<>();
        PQ.add(start, 0);
        distToS.put(start, 0.);
        solution = new ArrayList<>();
        boolean found = false;
        while (!found) {
            if (PQ.size() == 0) {
                outcome = SolverOutcome.UNSOLVABLE;
                timeSpent = sw.elapsedTime();
                return;
            } else if (sw.elapsedTime() >= timeout) {
                outcome = SolverOutcome.TIMEOUT;
                timeSpent = sw.elapsedTime();
                return;
            }
            Vertex p = PQ.removeSmallest();
            numState += 1;
            if (p.equals(end)) {
                found = true;
            }
            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(p);
            for (WeightedEdge<Vertex> e: neighborEdges) {
                relax(e, input, end);
                }

        }
        solWeight = distToS.get(end);
        solution = constructSol(solution, end, start);
        outcome = SolverOutcome.SOLVED;
        timeSpent = sw.elapsedTime();
    }

    /** construct solution list by recursion*/
    private List<Vertex> constructSol(List<Vertex> list, Vertex v, Vertex start) {
        list.add(0, v);
        if (v.equals(start)) {return list;}
        Vertex p = parentOfV.get(v);
        return constructSol(list, p, start);
    }

    /** only add to distToS when it has real value
     * Thus return inf if doesn't */
    private double getDistTo(Vertex v) {
        if (!distToS.containsKey(v)) {return Double.POSITIVE_INFINITY;}
        return distToS.get(v);
    }

    private void relax(WeightedEdge<Vertex> e, AStarGraph<Vertex> input, Vertex end) {
        Vertex q = e.to();
        Vertex p = e.from();
        double w = e.weight();
        double distNew = getDistTo(p) + w;
        if (distNew < getDistTo(q)) {
            distToS.put(q, distNew);
            parentOfV.put(q, p);
            double priority = distNew + input.estimatedDistanceToGoal(q, end);
            if (PQ.contains(q)) {
                PQ.changePriority(q, priority);
            } else {
                PQ.add(q, priority);
            }
        }
    }

    /** Returns one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE. Should be SOLVED if
     * the AStarSolver was able to complete all work in the time given. UNSOLVABLE if the priority queue became empty.
     * TIMEOUT if the solver ran out of time. You should check to see if you have run out of time every time you dequeue
     * */
    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    /**A list of vertices corresponding to a solution. Should be empty if result was TIMEOUT or UNSOLVABLE.*/
    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    /**The total weight of the given solution, taking into account edge weights. Should be 0 if result was TIMEOUT
    or UNSOLVABLE.*/
    public double solutionWeight() {
        return solWeight;
    }

    @Override
    /** The total number of priority queue dequeue operation*/
    public int numStatesExplored() {
        return numState;
    }

    @Override
    /**The total time spent in seconds by the constructor*/
    public double explorationTime() {
        return timeSpent;
    }
}
