package org.kaliy.aads.labs.floydwarshall

import org.jgrapht.Graph
import org.jgrapht.graph.GraphPathImpl
import org.jgrapht.graph.SimpleWeightedGraph
import org.kaliy.aads.labs.common.KFWeightedEdge

//http://e-maxx.ru/algo/floyd_warshall_algorithm
class FloydWarshallShortestPathFinder<V> {

    Graph<V, KFWeightedEdge> graph
    List<V> vertexes
    Double[][] d
    Integer[][] backtrace //номера фаз для получения пути

    FloydWarshallShortestPathFinder(Graph graph) {
        this.graph = graph
        vertexes = graph.vertexSet() as List
        vertexes.sort()
        fillMatrix()
    }

    def fillMatrix() {
        if (d) {
            return;
        }
        Integer n = vertexes.size()
        backtrace = new Integer[n][n]
        (0..n-1).each {
            backtrace[it] = [-1]*n
        }

        d = new Double[n][n];
        (0..n-1).each {
            d[it] = [Double.POSITIVE_INFINITY] * n
        }
        (0..n-1).each {
            d[it][it] = 0 //сами с собой
        }

        graph.edgeSet().each { edge ->
            V v1 = graph.getEdgeSource(edge);
            V v2 = graph.getEdgeTarget(edge);

            def v_1 = vertexes.indexOf(v1);
            def v_2 = vertexes.indexOf(v2);

            d[v_1][v_2] = edge.weight
            d[v_2][v_1] = edge.weight //TODO: для ненаправленного графа только, надо смтореть
        }
        (0..n-1).each { k->
            (0..n-1).each { i->
                (0..n-1).each { j->
                    def ik_kj = d[i][k] + d[k][j];
                    if (ik_kj < d[i][j]) {
                        d[i][j] = ik_kj;
                        backtrace[i][j] = k;
                    }
                }
            }
        }
        println "Shortest distances:"
        d.each {
            println it
        }
        println "Backtrace matrix:"
        backtrace.each {
            println it
        }
    }

    def shortestPathRecur(edges, Integer v_a, Integer v_b) {
        int k = backtrace[v_a][v_b];
        if (k == -1) {
            def edge = graph.getEdge(vertexes[v_a], vertexes[v_b]);
            if (edge) {
                edges << edge;
            }
        } else {
            shortestPathRecur(edges, v_a, k);
            shortestPathRecur(edges, k, v_b);
        }
    }

    def getShortestPath(V source, V target) {
        def edges = []
        shortestPathRecur(edges, vertexes.indexOf(source), vertexes.indexOf(target))
        new GraphPathImpl<V, KFWeightedEdge>(graph, source, target, edges, d[vertexes.indexOf(source)][vertexes.indexOf(target)])
    }

    def static void main(args) {
        def graph = new SimpleWeightedGraph<Integer, KFWeightedEdge>(KFWeightedEdge);
        (1..6).each {
            graph.addVertex(it)
        }
        def edge12 = graph.addEdge(1, 2)
        graph.setEdgeWeight(edge12, 7)
        def edge13 = graph.addEdge(1, 3)
        graph.setEdgeWeight(edge13, 9)
        def edge16 = graph.addEdge(1, 6)
        graph.setEdgeWeight(edge16, 14)
        def edge23 = graph.addEdge(2, 3)
        graph.setEdgeWeight(edge23, 10)
        def edge24 = graph.addEdge(2, 4)
        graph.setEdgeWeight(edge24, 15)
        def edge34 = graph.addEdge(3, 4)
        graph.setEdgeWeight(edge34, 11)
        def edge36 = graph.addEdge(3, 6)
        graph.setEdgeWeight(edge36, 2)
        def edge45 = graph.addEdge(4, 5)
        graph.setEdgeWeight(edge45, 6)
        def edge56 = graph.addEdge(5, 6)
        graph.setEdgeWeight(edge56, 9)

        println new FloydWarshallShortestPathFinder<Integer>(graph).getShortestPath(1,5)
    }


}
