package f_pontes;

import java.io.IOException;
import java.util.Scanner;

public class Pontes2 {

    public static void main(String[] args) throws IOException {
        int grau[];
        Scanner ler = new Scanner(System.in);
        
        while (ler.hasNextInt()) {
            int r = ler.nextInt();
            int k = ler.nextInt();
            grau = new int[r + 1];
            for (int i = 0; i < k; i++) {
                grau[ler.nextInt()]++;
                grau[ler.nextInt()]++;
            }
            boolean m[][] = new boolean[r][k + 1];
            for (int i = 0; i < m.length; i++) {
                m[i][0] = true;
                if (grau[i] == i) {
                    m[0][i] = true;
                }
            }

            for (int i = 1; i < m.length; i++) {
                for (int j = grau[i]; j < m[0].length; j++) {
                    if (m[i - 1][j - grau[i]] || m[i - 1][j]) {
                        m[i][j] = true;
                    }
                }
            }
            if (m[r - 1][k]) {
                System.out.println("S");
            } else {
                System.out.println("N");
            }
        }

        ler.close();
    }

}
