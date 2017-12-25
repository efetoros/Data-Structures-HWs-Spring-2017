package hw3.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by efetoros on 3/21/17.
 */
public class Solver {
    MinPQ<SearchNode> heap;
    SearchNode current;
    int moves;
    int efe;

    private class SearchNode implements Comparable<SearchNode> {
        int moves;
        WorldState world;
        SearchNode prevNode;
        int priorityNumber;

        SearchNode(WorldState world, int moves, SearchNode prevNode) {
            this.moves = moves;
            this.world = world;
            this.prevNode = prevNode;
            priorityNumber = world.estimatedDistanceToGoal() + moves;
        }

        @Override
        public int compareTo(SearchNode s2) {
            return this.priorityNumber - s2.priorityNumber;
        }

    }

    public Solver(WorldState initial) {
        heap = new MinPQ<>();
        current = new SearchNode(initial, 0, null);
        heap.insert(current);
        while (!heap.isEmpty()) {
            current = heap.delMin();
            if (current.world.isGoal()) {
                moves = current.moves;
                break;
            }
            for (WorldState neighbor : current.world.neighbors()) {
                if (current.prevNode == null || !neighbor.equals(current.prevNode.world)) {
                    heap.insert(new SearchNode(neighbor, current.moves + 1, current));
                }
            }
        }
    }


    public int moves() {
        return moves;
    }

    public Iterable<WorldState> solution() {
        ArrayList<WorldState> result = new ArrayList<>();
        SearchNode pointer = current;
        result.add(pointer.world);
        while (pointer.prevNode != null) {
            result.add(pointer.prevNode.world);
            pointer = pointer.prevNode;
        }

        Collections.reverse(result);
        if (!pointer.world.equals(result.get(0))) {
            result.add(pointer.world);
        }
        return result;

    }
}
