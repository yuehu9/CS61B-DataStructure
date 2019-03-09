package es.datastructur.synthesizer;

public class Harp {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = 0.993; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public Harp(double frequency) {
        // Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        int capacity = (int) Math.round(SR/frequency);
        buffer = new ArrayRingBuffer<Double>(capacity * 2);
        while(buffer.fillCount() != capacity) {
            buffer.enqueue(0.);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //TODO     Make sure that your random numbers are different from each
        for (int i =0; i < buffer.capacity(); i++) {
            buffer.dequeue();
            double r = Math.random() - 0.5;
            buffer.enqueue(r);
        }

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double front = buffer.dequeue();
        front = - DECAY * (front + buffer.peek()) / 2;
        buffer.enqueue(front);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }


}
