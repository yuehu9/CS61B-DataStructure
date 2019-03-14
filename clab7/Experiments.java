import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Created by hug.
 * @author : Yue
 *  */
public class Experiments {

    /** runs a computational experiment where you insert 5000 random items into a BST. Make a plot of the average depth of
     * your BST (defined in Task 1) vs. the number of items.
     */
     public static void experiment1() {
         BST<Integer> b = new BST<>();
         Random r = new Random();
         List<Integer> size = new ArrayList<>();
         List<Double> avDepth = new ArrayList<>();
         List<Double> bestDepth = new ArrayList<>();
         for (int i = 0; i < 5000; i++) {
             while (b.size() < i + 1) {
                 b.add(r.nextInt(10000));
             }
             if (i % 50 == 1) {
                 size.add(i);
                 avDepth.add(b.depthAv());
                 bestDepth.add(Math.log(i) / Math.log(2.));
             }
         }

         XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("Tree size").yAxisTitle("average depth").build();
         chart.addSeries("our tree", size, avDepth);
         chart.addSeries("best tree", size, bestDepth);
         new SwingWrapper(chart).displayChart();


     }

    public static void experiment2() {
    }

    public static void experiment3() {
    }

    public static void main(String[] args) {
         experiment1();
    }
}
