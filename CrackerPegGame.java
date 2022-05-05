import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrackerPegGame {
    public CrackerPegGame() throws IOException {
        List<String> input = new ArrayList<>();
        FileWriter output = new FileWriter("Puzzle4.txt");
        int j = 0;
        while (j < 10) {
            int i = 0;
            while (i < 10) {
                if (i != j)
                    input.add("*");
                else
                    input.add(".");
                i++;
            }
            List<String> step = (ArrayList) ((ArrayList<String>) input).clone();
            List<int[]> next = new ArrayList<>();
            PlayGame PlayGame = new PlayGame(input, 10);
            boolean done = PlayGame.is_Feasible(input, 10, next);
            if (done) {
                System.out.println("Game solved for position = " + j);
                output.write("Game solved for position = " + j + "\n");
                Collections.reverse(PlayGame.next_Step);
                List<int[]> next_Step = PlayGame.next_Step;
                String Answer = "    " + step.get(0) + "\n"
                        + "   " + step.get(1) + " " + step.get(2) + "\n"
                        + "  " + step.get(3) + " " + step.get(4) + " " + step.get(5) + "\n"
                        + " " + step.get(6) + " " + step.get(7) + " " + step.get(8) + " " + step.get(9) + "\n\n";
                output.write(Answer);
                for (int[] x : next_Step) {
                    step.set(x[2], "*");
                    step.set(x[1], ".");
                    step.set(x[0], ".");

                    Answer = "    " + step.get(0) + "\n"
                            + "   " + step.get(1) + " " + step.get(2) + "\n"
                            + "  " + step.get(3) + " " + step.get(4) + " " + step.get(5) + "\n"
                            + " " + step.get(6) + " " + step.get(7) + " " + step.get(8) + " " + step.get(9) + "\n\n";
                    output.write(Answer);
                }
            }
            output.write("\n\n");
            input.clear();
            j++;
        }
        output.close();
    }

    public static void main(String[] args) throws IOException {
        CrackerPegGame crackerPegGame = new CrackerPegGame();
        System.out.println("Game completed!");
    }
}
