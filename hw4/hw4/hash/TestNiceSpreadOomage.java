package hw4.hash;

<<<<<<< HEAD
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
=======
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdRandom;
>>>>>>> 75e3ae05f0902f52cc9449087585b72088e6d969

public class TestNiceSpreadOomage {
    private static class NiceSpreadOomage implements Oomage {
        private int val;

        @Override
        public void draw(double x, double y, double scalingFactor) {
            return;
        }

        @Override
        public int hashCode() {
            return val;
        }

        public static NiceSpreadOomage randomNiceSpreadOomage() {
            NiceSpreadOomage x = new NiceSpreadOomage();
            x.val = StdRandom.uniform(0, 1000000);
            return x;
        }

    }

    /** After you've written haveNiceHashCodeSpread,
     * run this and it should pass.*/
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
<<<<<<< HEAD
        int N = 1000;
=======
        int N = 10000;
>>>>>>> 75e3ae05f0902f52cc9449087585b72088e6d969

        for (int i = 0; i < N; i += 1) {
            oomages.add(NiceSpreadOomage.randomNiceSpreadOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }
}
