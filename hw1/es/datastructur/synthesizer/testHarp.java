package es.datastructur.synthesizer;

import edu.princeton.cs.introcs.StdAudio;
import org.junit.Test;

public class testHarp {
    @Test
    public void testPluckTheAString() {
        double CONCERT_A = 440.0;
        Harp aString = new Harp(CONCERT_A);
        aString.pluck();
        for (int i = 0; i < 50000; i += 1) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }

}
