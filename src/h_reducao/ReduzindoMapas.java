package h_reducao;

import java.util.ArrayList;
import java.util.Scanner;

public class ReduzindoMapas {

    public static class Aresta {

        int v1;
        int v2;
        int peso;

        public Aresta(int v1, int v2, int peso) {
            this.v1 = v1;
            this.v2 = v2;
            this.peso = peso;
        }

        public int getV1() {
            return v1;
        }

        public void setV1(int v1) {
            this.v1 = v1;
        }

        public int getV2() {
            return v2;
        }

        public void setV2(int v2) {
            this.v2 = v2;
        }

        public int getPeso() {
            return peso;
        }

        public void setPeso(int peso) {
            this.peso = peso;
        }
    }

    public static class Grafo {

        int quantVertices;
        ArrayList<Aresta> arestas;

        public Grafo(int quantVertices) {
            this.quantVertices = quantVertices;
            this.arestas = new ArrayList<Aresta>();
        }

        public void addAresta(int v1, int v2, int peso) {
            Aresta novaAresta = new Aresta(v1, v2, peso);
            arestas.add(novaAresta);
        }

        //Busca subconjunto de elemento
        public int buscar(int subset[], int i) {
            if (subset[i] == -1) {
                return i;
            }
            return buscar(subset, subset[i]);
        }

        //Unir subconjunto
        public void unirGrupo(int subset[], int v1, int v2) {
            int v1Set = buscar(subset, v1);
            int v2Set = buscar(subset, v2);
            subset[v1Set] = v2Set;
        }

        public void kruskal() {
            ArrayList<Aresta> agm = new ArrayList<Aresta>();
            this.ordenaArestas();

            int subset[] = new int[quantVertices];
            //Inicializa todo o vetor com -1
            for (int i = 0; i < subset.length; i++) {
                subset[i] = -1;
            }

            for (int i = 0; i < this.arestas.size(); i++) {
                int v1 = buscar(subset, arestas.get(i).getV1());
                int v2 = buscar(subset, arestas.get(i).getV2());
                if (v1 != v2) {
                    //A aresta faz parte da arvore geradora mínima
                    agm.add(arestas.get(i));
                    this.unirGrupo(subset, v1, v2);// Faz a união
                }
            }
            int soma = 0, peso = 0;
            for (int i = 0; i < agm.size(); i++) {
                peso = agm.get(i).getPeso();
                soma = soma + peso;
            }
            System.out.println(soma);
        }

        public void ordenaArestas() {

            Aresta chave;
            int j;
            int i;

            for (j = 1; j < this.arestas.size(); j++) {
                chave = this.arestas.get(j);
                for (i = j - 1; (i >= 0) && (this.arestas.get(i).peso > chave.peso); i--) {
                    this.arestas.set(i + 1, this.arestas.get(i));
                }
                this.arestas.set(i + 1, chave);
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int V;  // Numero de vertices
        int E;  // Numero de arestas

        V = in.nextInt();
        E = in.nextInt();
        //Criando o grafo
        Grafo grafo = new Grafo(E);

        for (int i = 0; i < E; i++) {
            grafo.addAresta(in.nextInt(), in.nextInt(), in.nextInt());
        }
        grafo.kruskal();
    }
}
