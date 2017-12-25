import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

/**
 * Created by efetoros on 5/2/17.
 */
public class test {

    @Test
    public void testtrie() {
        trie trie = new trie();
        trie.add("efe");
        trie.add("hello");
        trie.add("elmo");
        trie.add("elmosworld");
        trie.add("helium");
        trie.add("heliums");

        boolean answer = trie.checkForWord("heliums");
        assertEquals(true, answer);
    }


    @Test
    public void board() {
        try {
            //        Create two dimensional board
            Character[][] board = new Character[4][4];


            BufferedReader br2;
            br2 = new BufferedReader(new FileReader("testBoggle"));

            String line;
            int row = 0;
            while ((line = br2.readLine()) != null) {
                char[] nums = line.toCharArray();
                board[row] = new Character[nums.length]; //initialize dynamically here
                for (int col = 0; col < nums.length; col++) {
                    char n = nums[col];
                    board[row][col] = n;
                }
                row++;
            }

            assertEquals(true, board);
        } catch (IOException e) {
        }
    }


    @Test
    public void testUST() {
        try {

            BufferedReader br;
            br = new BufferedReader(new FileReader("words"));
            String sCurrentLine;
            trie trie = new trie();
            while ((sCurrentLine = br.readLine()) != null) {
                trie.add(sCurrentLine.replaceAll("[^a-zA-Z]", "").toLowerCase());
            }
            boolean answer = trie.checkForWord("ess");
            assertEquals(false, answer);
        } catch (IOException e) {
        }
    }

    @Test
    public void testList1() {

        Character[][] String = new Character[3][3];
        String[0][0] = 'h';
        String[0][1] = 'e';
        String[0][2] = 'l';
        String[1][0] = 'u';
        String[1][1] = 'i';
        String[1][2] = 'x';
        String[2][0] = 'm';
        String[2][1] = 's';
        String[2][2] = 'x';


        try {

            BufferedReader br;
            br = new BufferedReader(new FileReader("words"));
            String sCurrentLine;
            trie trie = new trie();
            while ((sCurrentLine = br.readLine()) != null) {
                trie.add(sCurrentLine.replaceAll("[^a-zA-Z]", "").toLowerCase());
            }
//            ArrayList<String> collector;
//            collector = main.boggleSolver(String, trie);
//            ArrayList<String> expected = new ArrayList<>();
//            assertEquals(expected, collector);
            PriorityQueue<String> collector = new PriorityQueue<>();
            ArrayList<Boggle.seen> seen = new ArrayList<>();
            Boggle.seen temp = new Boggle.seen(0, 0);
            seen.add(temp);
            Boggle.findWrods(String, "h", 0, 0, collector, trie, seen);
            assertEquals(null, collector);
        } catch (IOException e) {
        }
    }


    @Test
    public void testList() {

        Character[][] String = new Character[4][4];
        String[0][0] = 'n';
        String[0][1] = 'e';
        String[0][2] = 's';
        String[0][3] = 's';
        String[1][0] = 't';
        String[1][1] = 'a';
        String[1][2] = 'c';
        String[1][3] = 'k';
        String[2][0] = 'b';
        String[2][1] = 'm';
        String[2][2] = 'u';
        String[2][3] = 'h';
        String[3][0] = 'e';
        String[3][1] = 's';
        String[3][2] = 'f';
        String[3][3] = 't';

        try {

            BufferedReader br;
            br = new BufferedReader(new FileReader("words"));
            String sCurrentLine;
            trie trie = new trie();
            while ((sCurrentLine = br.readLine()) != null) {
                trie.add(sCurrentLine.replaceAll("[^a-zA-Z]", "").toLowerCase());
            }
//            ArrayList<String> collector = new ArrayList<>();
//            ArrayList<main.seen> seen = new ArrayList<>();
//            main.seen temp = new main.seen(3,3);
//            seen.add(temp);
//            main.findWrods(String,"t",3,3, collector, trie, seen);
//            ArrayList<String> answer = new ArrayList<>();
//            for (String item: collector) {
//                if (item.length() > 5) {
//                    answer.add(item);
//                }
//            }
            PriorityQueue<String> collector;
            collector = Boggle.boggleSolver(String, trie);
            ArrayList<String> expected = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                String temp = collector.poll();
                expected.add(temp);
            }


            assertEquals(null, expected);
        } catch (IOException e) {
        }


//
//        ArrayList<String> got = main.findWrods(String, String[3][3], "",3,3, tr  );
//        ArrayList<String> expected = new ArrayList<>();
//        expected.add("thumbtacks");
//        expected.add("thumbtack");
////        expected.add("setbacks");
////        expected.add("setback");
////        expected.add("ascent");
////        expected.add("humane");
////        expected.add("smacks");
//        assertEquals(expected, got);
    }

    @Test
    public void testBoards() {

        try {
            Character[][] board = new Character[4][4];


            BufferedReader scan;
            scan = new BufferedReader(new FileReader("testBoggle"));
            String line = scan.readLine();


            int row = 0;
            while (line != null) {
                char[] nums = line.toCharArray();
                board[row] = new Character[nums.length]; //initialize dynamically here
                for (int col = 0; col < nums.length; col++){
                    char n = nums[col];
                    board[row][col] = n;
                }
                row++;
                line = scan.readLine();
            }
            System.out.print(board[0][0]);
            System.out.print(board[0][1]);
            System.out.print(board[0][2]);
            System.out.println(board[0][3]);

            System.out.print(board[1][0]);
            System.out.print(board[1][1]);
            System.out.print(board[1][2]);
            System.out.println(board[1][3]);

            System.out.print(board[2][0]);
            System.out.print(board[2][1]);
            System.out.print(board[2][2]);
            System.out.println(board[2][3]);

            System.out.print(board[3][0]);
            System.out.print(board[3][1]);
            System.out.print(board[3][2]);
            System.out.print(board[3][3]);

//            assertEquals(1, board);

        } catch (IOException ex) {
        }
    }

    @Test
    public void testScanner() {
        int[] hi = {1,2,3,4,5,6,7,8,9};
        System.out.println(hi[:7]);

    }
}

