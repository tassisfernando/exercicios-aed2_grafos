package b_setas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Dijkstra
public class SetasAntigo {

    public static class Grafo {
        ArrayList<Vertice> vertices;

        public Grafo() {
            vertices = new ArrayList();
        }

        public void addVertice(int num) {
            Vertice v = new Vertice(num);
            vertices.add(v);
        }

        public boolean temVertice(int num) {
            for (Vertice v : vertices) {
                if (v.num == num) {
                    return true;
                }
            }
            return false;
        }

        public void addAresta(Vertice origem, Vertice destino, int peso) {
            Aresta a = new Aresta(origem, destino, peso);
            origem.adj.add(a);
        }

        public void addAresta(int origem, int destino, int peso) {
            if (!this.temVertice(origem)) {
                this.addVertice(origem);
            }
            if (!this.temVertice(destino)) {
                this.addVertice(destino);
            }
            Vertice vOrig, vDest;
            vOrig = this.buscaVertice(origem);
            vDest = this.buscaVertice(destino);

            Aresta a = new Aresta(vOrig, vDest, peso);
            vOrig.adj.add(a);
        }

        public Vertice buscaVertice(int num) {
            for (Vertice v : vertices) {
                if (v.num == num) {
                    return v;
                }
            }
            return null;
        }

        public int dijkstra(Vertice raiz, Vertice destino) {
            ArrayList<Vertice> visitados = new ArrayList();
            int custo[] = new int[vertices.size() + 1];

            //inicializa vetor de custos mínimos
            for (int i = 0; i < custo.length; i++) {
                custo[i] = 1000000000;
            }

            //custo da raiz é sempre zero
            custo[raiz.num] = 0;
            Vertice atual = raiz;
            visitados.add(raiz);

            while (visitados.size() < vertices.size()) {
                //Atualiza estimativas dos vertices adjacentes
                for (Aresta a : atual.adj) {
                    int calculoCusto = custo[atual.num] + a.peso;
                    if (calculoCusto < custo[a.destino.num]) {
                        custo[a.destino.num] = calculoCusto;
                    }
                }

                int menor = 1000000000;
                Vertice aux = null;
                for (int i = 0; i < custo.length; i++) {
                    aux = this.buscaVertice(i);
                    if (custo[i] < menor && !visitados.contains(aux)) {
                        menor = custo[i];
                        atual = aux;
                    }
                }
                visitados.add(atual);
            }

            return custo[destino.num];
        }
    }

    public static class Vertice {

        int num;
        ArrayList<Aresta> adj;

        public Vertice(int num) {
            this.num = num;
            adj = new ArrayList();
        }

        public Vertice() {
        }
    }

    public static class Aresta {

        Vertice origem;
        Vertice destino;
        int peso;

        public Aresta() {
        }

        public Aresta(Vertice origem, Vertice destino, int peso) {
            this.origem = origem;
            this.destino = destino;
            this.peso = peso;
        }
    }

    public static void main(String[] args) {
        File entrada = new File("teste1_Setas.txt");
        Scanner in = null;

        try {
            in = new Scanner(entrada);
        } catch (FileNotFoundException e) {
            in = new Scanner(System.in);
        }

        int quantCirculos, quantSetas, extrA, extrB, circ1, circ2;
        String linha = in.nextLine();
        String itens[] = linha.split(" ");

        quantCirculos = Integer.parseInt(itens[0]);
        quantSetas = Integer.parseInt(itens[1]);
        extrA = Integer.parseInt(itens[2]);
        extrB = Integer.parseInt(itens[3]);

        Grafo graf = new Grafo();
        Vertice origem, destino;
        for (int i = 0; i < quantSetas; i++) {
            String circulos = in.nextLine();
            String circ[] = circulos.split(" ");

            circ1 = Integer.parseInt(circ[0]);
            circ2 = Integer.parseInt(circ[1]);

            graf.addAresta(circ1, circ2, 0);
            graf.addAresta(circ2, circ1, 1);
        }
        
        int bibi = graf.dijkstra(graf.buscaVertice(extrA), graf.buscaVertice(extrB));
        int bibika = graf.dijkstra(graf.buscaVertice(extrB), graf.buscaVertice(extrA));

        if (bibi < bibika) {
            System.out.println("Bibi: " + bibi);
        } else if (bibi > bibika) {
            System.out.println("Bibika: " + bibika);
        } else {
            System.out.println("Bibibibika");
        }
    }
}