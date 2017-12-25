import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Boggle {

    public static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String x, String y)
        {

            if (x.length() < y.length())
            {
                return 1;
            }
            if (x.length() > y.length())
            {
                return -1;
            }
            return y.compareTo(x) * -1;
        }
    }

    public static class seen {
        int x;
        int y;

        seen(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object other) {
            seen o = (seen) other;
            if (this.x == o.x && this.y == o.y) {
                return true;
            } else {
                return false;
            }

        }


    }




    public static PriorityQueue<String> boggleSolver(Character[][] m,trie trie) {
        if (m == null) {
            throw new NullPointerException("The matrix cannot be null");
        }
        Comparator<String> comparator = new StringLengthComparator();
        final PriorityQueue<String> wordcollector = new PriorityQueue<>(comparator);
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                ArrayList<seen> seen = new ArrayList<>();
                seen temp = new seen(i,j);
                seen.add(temp);
                findWrods(m, m[i][j] + "", i, j, wordcollector, trie, seen);
            }
        }
        return wordcollector;
    }

    public static void findWrods(Character[][] board, String firstPart,
                                 int x, int y, PriorityQueue<String> wordCollector, trie trie, ArrayList<seen> seen) {

        for (int i = Math.max(0, x - 1); i < Math.min(board.length, x + 2); i++) {
            for (int m = Math.max(0, y - 1); m < Math.min(board[0].length, y + 2); m++) {
                if (!seen.contains(new seen(i,m))) {
                    ArrayList<seen> seencopy = new ArrayList<>();
                    seencopy.addAll(seen);
                    seen temp = new seen(i,m);
                    seencopy.add(temp);

                    String word = firstPart + board[i][m];
                    if (trie.checkForWord(word) && !wordCollector.contains(word)) {
                        wordCollector.add(word);
                    }
                    if (word.length() < 15 && trie.checkifPrefix(word)) {
                        findWrods(board, word, i, m, wordCollector, trie, seencopy);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {


        HashMap<String, String> values = new HashMap<>();

        for (int i = 0; i < args.length; i+=2) {
            values.put(args[i], args[i + 1]);
        }

//        for(String i : args) {
//            System.out.println(i);
//        }



//        sets up dictionary and trie
        try {

            BufferedReader br;
            if (values.containsKey("-d")) {
                br = new BufferedReader(new FileReader(values.get("-d")));
            } else {
                br = new BufferedReader(new FileReader("words"));

            }

            String sCurrentLine;

            trie trie = new trie();
            while ((sCurrentLine = br.readLine()) != null) {
                trie.add(sCurrentLine);
            }


            //        Create two dimensional board


//            System.out.println("1");
            Scanner scan = new Scanner(System.in);
            String line = scan.nextLine();
            int size = line.length();
//            System.out.println("2");


            if (values.containsKey("-n") && values.containsKey("-m")) {
                Character[][] board = new Character[Integer.parseInt(values.get("-n"))]
                        [Integer.parseInt(values.get("-m"))];

//                String line;
                int row = 0;
                while (scan.hasNextLine()) {
                    line = scan.nextLine();
                    char[] nums = line.toCharArray();
//                    board[row] = new Character[nums.length]; //initialize dynamically here
                    for (int col = 0; col < nums.length; col++) {
                        char n = nums[col];
                        board[row][col] = n;
                    }
                    row++;


                }

                PriorityQueue<String> collector;
                collector = Boggle.boggleSolver(board, trie);
//            ArrayList<String> expected = new ArrayList<>();
//                System.out.println(values.get("-k"));
                for (int i = 0; i < Integer.parseInt(values.get("-k")) && !collector.isEmpty(); i++) {
                    System.out.println(collector.poll());
                }
            } else if(values.containsKey("-r")) {
                if (values.containsKey("-n") && values.containsKey("-m")) {
                    Character[][] board = new Character[Integer.parseInt(values.get("-n"))]
                            [Integer.parseInt(values.get("-m"))];
                    for (int i = 0; i < board.length; i++) {
                        for (int m = 0; i < board.length; m++) {
                            Random r = new Random();
                            char c = (char) (r.nextInt(26) + 'a');
                            board[i][m] = c;
                        }
                    }


                    //                String line;
                    int row = 0;
                    char[] nums;
                    while (scan.hasNextLine()) {
                        if (row == 0) {
                            nums = line.toCharArray();
                        } else {
                            line = scan.nextLine();
                            nums = line.toCharArray();
                        }
//                        board[row] = new Character[size]; //initialize dynamically here
                        for (int col = 0; col < nums.length; col++) {
                            char n = nums[col];
                            board[row][col] = n;
                        }
                        row++;

                    }

                    PriorityQueue<String> collector;
                    collector = Boggle.boggleSolver(board, trie);
//            ArrayList<String> expected = new ArrayList<>();
//                System.out.println(values.get("-k"));
                    for (int i = 0; i < Integer.parseInt(values.get("-k")) && !collector.isEmpty(); i++) {
                        System.out.println(collector.poll());
                    }

                } else {
                    Character[][] board = new Character[4][4];
                    for (int i = 0; i < board.length; i++) {
                        for (int m = 0; i < board.length; m++) {
                            Random r = new Random();
                            char c = (char) (r.nextInt(26) + 'a');
                            board[i][m] = c;
                        }
                    }


                    //                String line;
                    int row = 0;
                    char[] nums;
                    while (scan.hasNextLine()) {
                        if (row == 0) {
                            nums = line.toCharArray();
                        } else {
                            line = scan.nextLine();
                            nums = line.toCharArray();
                        }
//                        board[row] = new Character[size]; //initialize dynamically here
                        for (int col = 0; col < nums.length; col++) {
                            char n = nums[col];
                            board[row][col] = n;
                        }
                        row++;

                    }

                    PriorityQueue<String> collector;
                    collector = Boggle.boggleSolver(board, trie);
//            ArrayList<String> expected = new ArrayList<>();
//                System.out.println(values.get("-k"));
                    for (int i = 0; i < Integer.parseInt(values.get("-k")) && !collector.isEmpty(); i++) {
                        System.out.println(collector.poll());
                    }

                }

            } else {

                Character[][] board = new Character[size][size];

//                String line;
                int row = 0;
                char[] nums;
                while (scan.hasNextLine()) {
                    if (row == 0) {
                        nums = line.toCharArray();
                    } else {
                        line = scan.nextLine();
                        nums = line.toCharArray();
                    }
//                    board[row] = new Character[size]; //initialize dynamically here
//                    System.out.println(nums.length != size);
                    if (nums.length == size && row < size) {

                        for (int col = 0; col < nums.length; col++) {
                            char n = nums[col];
                            board[row][col] = n;
                        }
                    }
                    row++;

                }

                PriorityQueue<String> collector;
                collector = Boggle.boggleSolver(board, trie);
//            ArrayList<String> expected = new ArrayList<>();
//                System.out.println(values.get("-k"));
                for (int i = 0; i < Integer.parseInt(values.get("-k")) && !collector.isEmpty(); i ++) {
                    System.out.println(collector.poll());
                }
            }


        } catch (IOException e) {
        }







    }
}

