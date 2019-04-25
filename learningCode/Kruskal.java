package class_6;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * kruskal算法 适用范围：要求无向图
 */

 public class Kruskal {

     // 并查集
    public static class UnionFind {
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> rankMap;

        public UnionFind() {
            fatherMap = new HashMap<Node, Node>();
            rankMap = new HashMap<Node, Integer>();
        }

        private Node findFather(Node n) {
            Node father = fatherMap.get(n);
            if (father != n) {
                father = findFather(father);
            }
            fatherMap.put(n, father);
            return father;
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            rankMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                rankMap.put(node, 1);
            }
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aFather = findFather(a);
            Node bFather = findFather(b);
            if (aFather != bFather) {
                int aFrank = rankMap.get(aFather);
                int bFrank = rankMap.get(bFather);
                if (aFrank <= bFrank) {
                    fatherMap.put(aFather, bFather);
                    rankMap.put(bFather, aFrank + bFrank);
                } else {
                    fatherMap.put(bFather, aFather);
                    rankMap.put(aFather, aFrank + bFrank);
                }
            }
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
             return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
         UnionFind unionFind = new UnionFind();
         unionFind.makeSets(graph.nodes.values());
         Queue<Edge> minHeap = new PriorityQueue<>(new EdgeComparator());
         for (Edge edge : graph.edges) {
             minHeap.add(edge);
         }
         Set<Edge> res = new HashSet<>();
         Edge curEdge = null;
         while (!minHeap.isEmpty()) {
             curEdge = minHeap.poll();
             if (!unionFind.isSameSet(curEdge.from, curEdge.to)) {
                 unionFind.union(curEdge.from, curEdge.to);
                 res.add(curEdge);
             }
         }
         return res;
    }
 }