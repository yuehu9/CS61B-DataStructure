package es.datastructur.synthesizer;

import edu.princeton.cs.introcs.StdAudio;
import org.junit.Test;

public class testDrum {
    @Test
    public void testPluckTheAString() {
        double CONCERT_A = 770.0;
        Drum aString = new Drum(CONCERT_A);
        aString.pluck();
        for (int i = 0; i < 50000; i += 1) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }
}
