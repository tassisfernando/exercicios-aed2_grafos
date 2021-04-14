package b_setas.agoraVai;

import java.util.ArrayList;
import java.util.Scanner;

public class SetasAntigo2 {

    public static class Vertice {

        int num;
        int bibi;
        int bibika;
        ArrayList<Aresta> filhos = new ArrayList<>();

        public Vertice() {
            this.bibi = 10000;
            this.bibika = 10000;
        }

    }

    public static class Aresta {
        Vertice c2;
        int caminho;
    }

    public static void dijkstra(int origem, int destino, Vertice[] vertices) {

        boolean visitados[] = new boolean[vertices.length];
        int pos = origem;
        int ver1 = 0;
        int ver = 0;
        int menor = 0;
        if (vertices[origem].filhos.isEmpty()) {
            ver = 1;
        }
        //BIBI
        vertices[pos].bibi = 0;

        while (!verifica(visitados)) {
            if (!vertices[pos].filhos.isEmpty() && ver == 0) { //se tem filhos
                boolean verif = false;
                ver1 = 0;
                for (int i = 0; i < vertices[pos].filhos.size(); i++) {
                    if (!visitados[vertices[pos].filhos.get(i).c2.num]) {
                        if (ver1 == 0) {
                            menor = vertices[pos].filhos.get(i).c2.num;
                            ver1 = 1;
                        }
                        verif = true;
                        //verifica se o caminho entre vertices[pos] é menor que o caminho inicial de c2
                        if (vertices[pos].bibi + vertices[pos].filhos.get(i).caminho < vertices[pos].filhos.get(i).c2.bibi) {
                            vertices[pos].filhos.get(i).c2.bibi = vertices[pos].bibi + vertices[pos].filhos.get(i).caminho;

                            if (vertices[menor].bibi > vertices[pos].filhos.get(i).c2.bibi) {
                                menor = vertices[pos].filhos.get(i).c2.num;
                            }
                        }
                    }
                }
                if (!verif) {
                    visitados[pos] = true;
                    for (int i = 1; i < visitados.length; i++) {
                        if (visitados[i] == false) {
                            pos = i;
                            break;
                        }
                    }
                } else {
                    visitados[pos] = true;
                    pos = menor;
                }
            } else {
                visitados[pos] = true;
                for (int i = 1; i < visitados.length; i++) {
                    if (visitados[i] == false) {
                        pos = i;
                        break;
                    }
                }
            }
        }
        //zerando o vetor visitados
        for (int i = 1; i < visitados.length; i++) {
            visitados[i] = false;
        }

        //BIBIKA
        pos = destino;
        vertices[pos].bibika = 0;
        menor = 0;
        while (!verifica(visitados)) {
            if (!vertices[pos].filhos.isEmpty() && ver == 0) { //se tem filhos
                boolean verif = false;
                ver1 = 0;
                for (int i = 0; i < vertices[pos].filhos.size(); i++) {
                    if (!visitados[vertices[pos].filhos.get(i).c2.num]) {
                        if (ver1 == 0) {
                            menor = vertices[pos].filhos.get(i).c2.num;
                            ver1 = 1;
                        }
                        verif = true;
                        //verifica se o caminho entre vertices[pos] é menor que o caminho inicial de c2
                        if (vertices[pos].bibika + vertices[pos].filhos.get(i).caminho < vertices[pos].filhos.get(i).c2.bibika) {
                            vertices[pos].filhos.get(i).c2.bibika = vertices[pos].bibika + vertices[pos].filhos.get(i).caminho;

                            if (vertices[menor].bibika > vertices[pos].filhos.get(i).c2.bibika) {
                                menor = vertices[pos].filhos.get(i).c2.num;
                            }
                        }
                    }
                }
                if (!verif) {
                    visitados[pos] = true;
                    for (int i = 1; i < visitados.length; i++) {
                        if (visitados[i] == false) {
                            pos = i;
                            break;
                        }
                    }
                } else {
                    visitados[pos] = true;
                    pos = menor;
                }
            } else {
                visitados[pos] = true;
                for (int i = 1; i < visitados.length; i++) {
                    if (visitados[i] == false) {
                        pos = i;
                        break;
                    }
                }
            }
        }

    }

    public static boolean verifica(boolean[] v) {
        boolean verif = true;
        for (int i = 1; i < v.length; i++) {
            verif = v[i] && verif;
        }
        return verif;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int c, s, a, b, c1, c2;

        c = sc.nextInt(); //quantidade de vertices
        s = sc.nextInt(); //quantidade de arestas
        a = sc.nextInt(); //origem
        b = sc.nextInt(); //destino

        Vertice[] vertices = new Vertice[c + 1];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertice();
        }

        for (int i = 0; i < s; i++) {

            c1 = sc.nextInt(); //partida
            c2 = sc.nextInt(); //chegada

            Aresta a1 = new Aresta();
            Aresta a2 = new Aresta();

            a1.c2 = vertices[c2];
            a1.caminho = 0;
            a2.c2 = vertices[c1];
            a2.caminho = 1;

            vertices[c1].num = c1;
            vertices[c2].num = c2;

            vertices[c1].filhos.add(a1);
            vertices[c2].filhos.add(a2);
        }

        dijkstra(a, b, vertices);

        if (vertices[a].bibika == vertices[b].bibi) {
            System.out.println("Bibibibika");
        } else if (vertices[a].bibika > vertices[b].bibi) {
            System.out.println("Bibi: " + vertices[b].bibi);
        } else if (vertices[a].bibika < vertices[b].bibi) {
            System.out.println("Bibika: " + vertices[a].bibika);
        }
    }

}
