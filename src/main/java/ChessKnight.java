import java.util.ArrayDeque;
import java.util.Queue;

public class ChessKnight {

    private static final int[] mx = new int[] {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] my = new int[] {1, 2, 2, 1, -1, -2, -2, -1};

    private static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int countMoves(int width, int height, int startCol, int startRow, int endCol, int endRow) {
        // place knight at 0, 0, reduce board dimensions to coord diffs
        // also add 2 to new w, h not to place endpoint to a corner
        Node s = new Node(0, 0);
        Node e = new Node(Math.abs(startCol - endCol), Math.abs(startRow - endRow));
        width = e.x + 2;
        height = e.y + 2;

        boolean[][] v = new boolean[width][height];
        v[s.x][s.y] = true;

        Queue<Node> q = new ArrayDeque<>();
        q.add(s);
        int c = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node n = q.poll();
                if (e.x == n.x && e.y == n.y) return c;
                for (int j = 0; j < 8; j++) {
                    int x = n.x + mx[j];
                    int y = n.y + my[j];
                    if (isValid(x, y, width, height) && !v[x][y]) {
                        v[x][y] = true;
                        q.add(new Node(x, y));
                    }
                }
            }
            c++;
        }
        return -1;
    }

    private static boolean isValid(int x, int y, int w, int h) {
        return x >= 0 && x < w && y >= 0 && y < h;
    }
}