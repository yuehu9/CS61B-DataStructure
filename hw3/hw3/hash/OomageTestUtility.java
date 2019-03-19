package hw3.hash;

import java.util.List;


/*
 * Write a utility function that returns true if the given oomages
 * have hashCodes that would distribute them fairly evenly across
 * M buckets. To do this, convert each oomage's hashcode in the
 * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
 * and ensure that no bucket has fewer than N / 50
 * Oomages and no bucket has more than N / 2.5 Oomages.
 */
public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
       int[] bucketnum = new int[M];
       for (Oomage o : oomages) {
           int num = (o.hashCode() & 0x7FFFFFFF) % M;
           bucketnum[num] += 1;
       }
       int N = oomages.size();
       for (int i = 0; i < M; i++) {
           if (bucketnum[i] < N/50 || bucketnum[i] > N/2.5) return false;
       }
       return true;
    }
}
