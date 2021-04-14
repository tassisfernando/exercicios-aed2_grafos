package d_imposto;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Real {

    public static class Estrada {

        int tamanho;
        Cidade cidade1;
        Cidade cidade2;
        boolean ok = false;
        boolean visitada = false;
    }

    public static class Cidade {

        int id;
        ArrayList<Estrada> estradas = new ArrayList<Estrada>(); // Estradas que ligam a cidade
        int imposto = -100; // Imposto iniciado com negativo para demonstrar q nao foi utilizado
        int cont = 0;
    }

    public static int Transportar(int capCarruagem, Cidade[] cidades, int montanteTotal, int id) {

        Stack<Cidade> navegacao = new Stack<Cidade>(); // Pilha de cidades
        Stack<Estrada> navegEstradas = new Stack<Estrada>(); // Pilha de arestas

        // int i = id;
        Cidade cidadeDestino = null;
        //boolean estradaDisponivel = false;

        Cidade cidade = cidades[1]; // Cidade 1 == cidade do Rei

        int carruagem = 0;
        int distancia = 0;
        Estrada estAnterior = null;

        //System.out.println(cidade.id);
        int i = 0;

        while (cidades[1].imposto != montanteTotal) { // Enquanto a cidade do rei nao recebeu todo o dinheiro

            for (i = 0; i < cidade.estradas.size(); i++) {

                // Percorre por todas as estradas da cidade atual
                // Para nao voltar na mesma (pega a ponta correta da aresta)
                if (cidade.estradas.get(i).cidade2 == cidade) {
                    cidadeDestino = cidade.estradas.get(i).cidade1;
                } else {
                    cidadeDestino = cidade.estradas.get(i).cidade2;
                }

                if (cidade.estradas.get(i).visitada == false /*&& cidade.estradas.get(i).ok == false*/) { ///
                    // Se aresta ainda nao visitada, passa p/ prox cidade
                    cidade.cont = i;
                    navegacao.push(cidade); // Adiciona a cidade no topo da pilha
                    estAnterior = cidade.estradas.get(i);
                    navegEstradas.push(cidade.estradas.get(i));

                    distancia += estAnterior.tamanho;
                    estAnterior.visitada = true;

                    cidade = cidadeDestino; // Vai p/ prox cidade
                    //System.out.println(cidade.id);
                    i = -1; // Contagem eh zerada, ja q se trata de uma nova cidade

                }

            } // FimFor

            /// Faz o transporte dos impostos
            // i = 0; // Para desconsiderar o incremento realizado pelo for
            while (cidade.imposto != 0) { // Entra ak quando sai do While

                estAnterior = navegEstradas.peek(); // Pega o topo da pilha

                if (cidade.imposto > capCarruagem) {
                    carruagem += capCarruagem;
                    cidade.imposto -= capCarruagem;
                    // if( i < cidade.estradas.size())
                    //distancia += cidade.estradas.get(i).tamanho * 2;
                    distancia += estAnterior.tamanho * 2;
                    // Se vai ter q voltar soma o dobro da distancia

                } else {
                    carruagem += cidade.imposto;
                    cidade.imposto = 0;
                    //distancia += cidade.estradas.get(i).tamanho;
                    distancia += estAnterior.tamanho;
                }

            }

            estAnterior.ok = true; // Marca estrada com OK
            ///// Fim transporte

            cidade = navegacao.pop(); // Retira a cidade de topo // retorna
            navegEstradas.pop();
            i = cidade.cont + 1;
            cidade.imposto += carruagem;
            carruagem = 0;

        }
        // }

        return distancia;
    }

    public static void main(String[] args) {

        InputStream entrada = System.in;

        //String entrada1 = "6 10\r\n" + "0 10 10 10 10 10\r\n" + "1 4 7\r\n" + "5 1 2\r\n" + "3 5 3\r\n" + "2 5 2\r\n"
        //		+ "6 5 2";
        //String entrada4 = "5 5\r\n" + "2 3 5 1 3\r\n" + "4 2 67\r\n" + "3 1 78\r\n" + "3 5 79\r\n" + "2 5 35";
        Scanner sc = new Scanner(entrada);

        int numCidades = sc.nextInt();
        int capCargaCarruagem = sc.nextInt(); // Capacidade de ouro da carruagem do rei

        Cidade[] cidades = new Cidade[numCidades + 1];

        int montante = 0;

        for (int i = 1; i <= numCidades; i++) { // Quantidade de impostos devido por cada cidade
            Cidade cidade = new Cidade();
            cidade.id = i;
            cidade.imposto = sc.nextInt();
            montante += cidade.imposto;
            cidades[i] = cidade;

        }

        for (int i = 0; i < numCidades - 1; i++) { // M

            int a = sc.nextInt(); // cidade A eh ligada
            int b = sc.nextInt(); // a cidade B
            int c = sc.nextInt(); // comprimento

            Cidade cidadeA = cidades[a];
            Cidade cidadeB = cidades[b];

            Estrada estrada = new Estrada();
            estrada.tamanho = c;
            estrada.cidade1 = cidadeA;
            estrada.cidade2 = cidadeB;

            cidadeA.estradas.add(estrada);
            cidadeB.estradas.add(estrada);

        }

        ////// Fim preenchimento
        int id = 1;

        int distancia = Transportar(capCargaCarruagem, cidades, montante, id);

        System.out.println(distancia);

        sc.close();
    }///// Main

}
