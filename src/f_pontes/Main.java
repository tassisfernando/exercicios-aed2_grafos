package f_pontes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] regs;
    static boolean[][] mAdjs;

    public static void main(String[] args) throws IOException {
        InputStreamReader inputReader = new InputStreamReader(System.in);
        BufferedReader buffReader = new BufferedReader(inputReader);

        String[] line = buffReader.readLine().split(" ");
        while (line.length == 2) {
            int numRegs = Integer.parseInt(line[0]);
            int numBridges = Integer.parseInt(line[1]);

            regs = new int[numRegs + 1];

            for (int cont = 1; cont <= numBridges; cont++) {
                line = buffReader.readLine().split(" ");
                regs[Integer.parseInt(line[0])]++;
                regs[Integer.parseInt(line[1])]++;
            }

            mAdjs = new boolean[numRegs][numBridges + 1];

            if (isPossible(numRegs, numBridges)) {
                System.out.println("S");
            } else {
                System.out.println("N");
            }

            String lineS = buffReader.readLine();
            if (lineS != null) {
                line = lineS.split(" ");
            } else {
                line = new String[1];
            }
        }
        buffReader.close();
        inputReader.close();
    }

    private static boolean isPossible(int numRegs, int numBridges) {
        for (int i = 0; i < mAdjs.length; i++) {
            mAdjs[i][0] = true;
            if (i == regs[i]) {
                mAdjs[0][i] = true;
            }
        }

        for (int row = 1; row < mAdjs.length; row++) {
            for (int col = regs[row]; col < mAdjs[row].length; col++) {
                mAdjs[row][col] = (mAdjs[row - 1][col - regs[row]] || mAdjs[row - 1][col]);
            }
        }

        return mAdjs[numRegs - 1][numBridges];
    }
}
