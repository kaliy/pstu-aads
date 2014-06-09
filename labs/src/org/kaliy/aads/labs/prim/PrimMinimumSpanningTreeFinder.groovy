package org.kaliy.aads.labs.prim

import org.jgrapht.Graph
import org.jgrapht.graph.SimpleWeightedGraph
import org.kaliy.aads.labs.common.KFWeightedEdge

class PrimMinimumSpanningTreeFinder {

    def createMinimumSpanningTree(Graph graph) {
        def minimumSpaningTreeEdges = []
        def edges = [] as PriorityQueue
        def unspannedVertexes = new HashSet(graph.vertexSet())
        def root = unspannedVertexes.first()
        unspannedVertexes.remove(root)
        edges.addAll(graph.edgesOf(root))
        while (!edges.empty) {
            def edge = edges.poll()
            def nextVertex = unspannedVertexes.contains(graph.getEdgeSource(edge)) ?
                    graph.getEdgeSource(edge) :
                    graph.getEdgeTarget(edge)
            println unspannedVertexes
            if (unspannedVertexes.contains(nextVertex)) {
                minimumSpaningTreeEdges << edge
                unspannedVertexes.remove(nextVertex)
                graph.edgesOf(nextVertex).each {
                    def vertexToProcess = graph.getEdgeSource(it) == nextVertex ?
                            graph.getEdgeTarget(it) :
                            graph.getEdgeSource(it)
                    if (unspannedVertexes.contains(vertexToProcess)) {
                        edges << it
                    }
                }
            }
        }
        minimumSpaningTreeEdges
    }

    static void main(args) {
        //Пример графа отсюда: http://ru.wikipedia.org/wiki/%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC_%D0%9F%D1%80%D0%B8%D0%BC%D0%B0
        def graph = new SimpleWeightedGraph<String, KFWeightedEdge>(KFWeightedEdge);
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addVertex("C")
        graph.addVertex("D")
        graph.addVertex("E")
        graph.addVertex("F")
        graph.addVertex("G")
        def edgeAB = graph.addEdge("A", "B")
        graph.setEdgeWeight(edgeAB, 7)
        def edgeBC = graph.addEdge("B", "C")
        graph.setEdgeWeight(edgeBC, 8)
        def edgeAD = graph.addEdge("A", "D")
        graph.setEdgeWeight(edgeAD, 5)
        def edgeCE = graph.addEdge("C", "E")
        graph.setEdgeWeight(edgeCE, 5)
        def edgeBD = graph.addEdge("B", "D")
        graph.setEdgeWeight(edgeBD, 9)
        def edgeBE = graph.addEdge("B", "E")
        graph.setEdgeWeight(edgeBE, 7)
        def edgeDE = graph.addEdge("D", "E")
        graph.setEdgeWeight(edgeDE, 15)
        def edgeDF = graph.addEdge("D", "F")
        graph.setEdgeWeight(edgeDF, 6)
        def edgeEF = graph.addEdge("E", "F")
        graph.setEdgeWeight(edgeEF, 8)
        def edgeEG = graph.addEdge("E", "G")
        graph.setEdgeWeight(edgeEG, 9)
        def edgeFG = graph.addEdge("F", "G")
        graph.setEdgeWeight(edgeFG, 11)

        println new PrimMinimumSpanningTreeFinder().createMinimumSpanningTree(graph)
    }

}
