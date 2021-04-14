package b_setas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {

    private List<Edge> edges;
    private List<Vertex> vertex;
    private boolean hasCycle = false;

    public Graph() {
        this.edges = new ArrayList<>();
        this.vertex = new ArrayList<>();
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public void setHasCycle(boolean hasCycle) {
        this.hasCycle = hasCycle;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Vertex> getVertex() {
        return vertex;
    }

    public void addVertex(Integer data) {
        Vertex newVert = new Vertex(data);
        this.vertex.add(newVert);
    }

    public void addEdge(Double weight, Integer first, Integer end) {
        Vertex inEdge = this.getVertex(first);
        Vertex endEdge = this.getVertex(end);

        if (inEdge != null && endEdge != null) {
            Edge newEdge = new Edge(weight, inEdge, endEdge);
            inEdge.addEdgeOut(newEdge);
            endEdge.addEdgeIn(newEdge);
            this.edges.add(newEdge);
        }

    }

    public Vertex getVertex(Integer data) {
        for (int i = 0; i < vertex.size(); i++) {
            if (vertex.get(i).getData().equals(data)) {
                return vertex.get(i);
            }
        }
        return null;
    }

    //busca em largura
    public void breadthFirst() {
        if (this.vertex.size() > 0) {
            List<Vertex> marked = new ArrayList<>();
            List<Vertex> queue = new ArrayList<>();

            Vertex current = this.vertex.get(0); //começa do zero
            marked.add(current);
            System.out.println(current.getData());
            queue.add(current);

            while (queue.size() > 0) {
                Vertex visited = queue.get(0);

                for (int i = 0; i < visited.getEdgesOut().size(); i++) {
                    Vertex next = visited.getEdgesOut().get(i).getEnd();
                    if (!marked.contains(next)) {
                        marked.add(next);
                        System.out.println(next.getData());
                        queue.add(next);
                    }
                }
                queue.remove(0);
            }

        }
    }

    public Edge findEdge(Vertex vet1, Vertex vet2) {
        for (int i = 0; i < this.edges.size(); i++) {
            if (((this.edges.get(i).getIn().getData().equals(vet1.getData()))
                    && (this.edges.get(i).getEnd().getData().equals(vet2.getData())))
                    || ((this.edges.get(i).getIn().getData().equals(vet2.getData()))
                    && (this.edges.get(i).getEnd().getData().equals(vet1.getData())))) {
                return this.edges.get(i);
            }
        }
        return null;
    }

    /* DJIKSTRA: MENOR CAMINHO 
    public void startDjikstra(Vertex origin) {
        for (Vertex v : vertex) {
            v.setDistance(Double.POSITIVE_INFINITY);
            v.setPrevious(null);
            if (v.equals(origin)) {
                v.setDistance(0.0);
            }
        }
    }

    public void dijkstra(Vertex origin) {
        startDjikstra(origin);

        List<Vertex> vertexToGo = vertex;

        Collections.sort(vertexToGo);

        while (!vertexToGo.isEmpty()) {
            Vertex current = vertexToGo.get(0);

            for (Vertex adj : current.getNeighbors()) {
                if (!adj.isVisited()) {
                    Edge link = this.findEdge(current, adj);

                    if (adj.getDistance() > (current.getDistance() + link.getWeight())) {
                        adj.setDistance(current.getDistance() + link.getWeight());
                        adj.setPrevious(current);

                    }
                }
            }
        }
    }*/
    public List shortestPath(Vertex origin, Vertex destiny) {
        List<Vertex> path = new ArrayList<>();
        path.add(origin);

        Vertex vertexShortPath;

        //distâncias iniciais
        for (Vertex v : vertex) {
            v.setDistance(Double.POSITIVE_INFINITY);
            v.setPrevious(null);
            if (v.equals(origin)) {
                v.setDistance(0.0);
            }
        }

        List<Vertex> vertexToGo = vertex;

        Collections.sort(vertexToGo);

        while (!vertexToGo.isEmpty()) {
            Vertex current = vertexToGo.get(0);

            for (Vertex adj : current.getNeighbors()) {
                if (!adj.isVisited()) {
                    Edge link = this.findEdge(current, adj);

                    if (adj.getDistance() > (current.getDistance() + link.getWeight())) {
                        adj.setDistance(current.getDistance() + link.getWeight());
                        adj.setPrevious(current);

                        if (adj == destiny) {
                            path.clear();
                            vertexShortPath = adj;
                            path.add(adj);
                            while (vertexShortPath.getPrevious() != null) {
                                path.add(vertexShortPath.getPrevious());
                                vertexShortPath = vertexShortPath.getPrevious();

                            }
                            Collections.sort(path);
                        }
                    }
                }
            }
            current.setIsVisited(true);
            vertexToGo.remove(current);

            Collections.sort(vertexToGo);
        }

        this.clearPreviousVertex();
        return path;
    }

    public void clearPreviousVertex() {
        for (Vertex v : vertex) {
            v.setPrevious(null);
        }
    }

}
