import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayGame {
    public int range;
    public List<String> step;
    List<int[]> next_Step;

    public static int[][] possible = {
            {1, 2, 4}, {1, 3, 6}, {2, 4, 7}, {4, 2, 1}, {6, 3, 1}, {7, 4, 2},
            {2, 5, 9}, {3, 5, 8}, {3, 6, 10}, {9, 5, 2}, {8, 5, 3}, {10, 6, 3},
            {4, 5, 6}, {7, 8, 9}, {8, 9, 10}, {6, 5, 4}, {9, 8, 7}, {10, 9, 8}};

    public PlayGame(List<String> gameList, int n) {
        this.step = gameList;
        this.next_Step = new ArrayList<int[]>();
        this.range = n;
    }

    public boolean feasible_Moves(List<String> gameList, int f, int x, int t) {
        if (gameList.get(f).equals("*") && gameList.get(x).equals("*") && gameList.get(t).equals(".") && f != x && x != t && t != f) {
            int[] x1 = {f, x, t};
            for (int[] turn : this.possible) {
                for (int j = 0; true; j++) {
                    if (turn[j] != x1[j] + 1)
                        break;
                    if (j == 2) return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean is_Feasible(List<String> gameList, int length, List<int[]> state) {
        int count = Collections.frequency(gameList, ".");
        if (count == 9) {
            this.next_Step = state;
            return true;
        } else {
            for (int i = 0; i < length; i++) {
                if (gameList.get(i).equals("."))
                    continue;
                for (int j = 0; j < length; j++) {
                    if (gameList.get(j).equals("."))
                        continue;
                    for (int k = 0; k < length; k++) {
                        if (gameList.get(k).equals("*"))
                            continue;
                        if (feasible_Moves(gameList, i, j, k)) {
                            this.step.set(k, "*");
                            this.step.set(i, ".");
                            this.step.set(j, ".");

                            int[] a = {i, j, k};
                            boolean nex = is_Feasible(this.step, length, state);
                            if (nex) {
                                state.add(a);
                                return true;
                            } else {
                                this.step.set(j, "*");
                                this.step.set(k, ".");
                                this.step.set(i, "*");
                            }
                        }
                    }
                }
            }
            return false;
        }
    }
}
