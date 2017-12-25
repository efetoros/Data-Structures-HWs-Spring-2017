
package synthesizer;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {

        int result = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<>(result);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.enqueue(0.0);
        }
        // loop and add the required 0's
    }



    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {

        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each other.
        for (int i = 0; i < buffer.capacity(); i++) {
            //delete every zero and add zero w/ dequeue
            double r = Math.random() - .5;
            buffer.dequeue();
            buffer.enqueue(r);

        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {

        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play()
        double temp = buffer.dequeue();
        buffer.enqueue((.5 * (temp + buffer.peek())) * DECAY);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {

        return buffer.peek();
    }
}
