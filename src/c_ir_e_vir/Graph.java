package c_ir_e_vir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {

    private List<Edge> edges;
    private List<Vertex> vertex;

    public Graph() {
        this.edges = new ArrayList<>();
        this.vertex = new ArrayList<>();
    }

    public void clear() {
        this.edges.clear();
        this.vertex.clear();
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
    public boolean breadthFirstSearch(Integer root, Integer end) {
        if (this.vertex.size() > 0) {
            Vertex rootVert = getVertex(root);
            Vertex endVert = getVertex(end);

            List<Vertex> marked = new ArrayList<>();
            List<Vertex> queue = new ArrayList<>();

            Vertex current = rootVert; //começa do root
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
                        if (next.equals(endVert)) {
                            return true;
                        }
                    }
                }
                queue.remove(0);
            }
        }
        return false;
    }

    //busca em profundidade
    public int depthFirstSearch(Integer root, List<Edge> visiteds, Integer target) {
        Vertex rootVert = getVertex(root);

        for (Edge edge : rootVert.getEdgesOut()) {
            if (!visiteds.contains(edge)) {
                visiteds.add(edge);
                //System.out.println("Atual:" + rootVert);
                //System.out.println("Atual:" + edge.getEnd().getData());
                if (edge.getEnd().getData().equals(target)) {
                    return 1;
                }
                if (depthFirstSearch(edge.getEnd().getData(), visiteds, target) == 1) {
                    return 1;
                }
            }
        }
        return 0;
    }

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

    public void clearPreviousVertex() {
        for (Vertex v : vertex) {
            v.setPrevious(null);
        }
    }
}
