package hw3.puzzle;
<<<<<<< HEAD

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
=======
import org.junit.Test;
import static org.junit.Assert.*;
>>>>>>> eacb0977b7a18e107bd7f2462cf2e955302c5fbf

public class TestBoard {
    @Test
    public void verifyImmutability() {
        int r = 2;
        int c = 2;
        int[][] x = new int[r][c];
        int cnt = 0;
        for (int i = 0; i < r; i += 1) {
            for (int j = 0; j < c; j += 1) {
                x[i][j] = cnt;
                cnt += 1;
            }
        }
<<<<<<< HEAD


        Board b = new Board(x);
        Board d = new Board(x);
=======
        Board b = new Board(x);
>>>>>>> eacb0977b7a18e107bd7f2462cf2e955302c5fbf
        assertEquals("Your Board class is not being initialized with the right values.", 0, b.tileAt(0, 0));
        assertEquals("Your Board class is not being initialized with the right values.", 1, b.tileAt(0, 1));
        assertEquals("Your Board class is not being initialized with the right values.", 2, b.tileAt(1, 0));
        assertEquals("Your Board class is not being initialized with the right values.", 3, b.tileAt(1, 1));

        x[1][1] = 1000;
<<<<<<< HEAD
        assertEquals(true, b.equals(d));
        assertEquals("Your Board class is mutable and you should be making a copy of the values in the passed tiles array. Please see the FAQ!", 3, b.tileAt(1, 1));
    }
}
=======
        assertEquals("Your Board class is mutable and you should be making a copy of the values in the passed tiles array. Please see the FAQ!", 3, b.tileAt(1, 1));
    }
} 
>>>>>>> eacb0977b7a18e107bd7f2462cf2e955302c5fbf