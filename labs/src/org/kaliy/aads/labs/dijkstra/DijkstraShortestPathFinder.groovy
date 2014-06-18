package org.kaliy.aads.labs.dijkstra

import org.jgrapht.Graph
import org.jgrapht.graph.GraphPathImpl
import org.jgrapht.graph.SimpleWeightedGraph
import org.kaliy.aads.labs.common.KFWeightedEdge

class DijkstraShortestPathFinder<V> {

    Map<V, Double> distancesMap = [:]
    def processedVertexes = [] as Set
    def vertexesToProcess = []
    def sourceVertex, targetVertex
    def predecessorsMap = [:]
    Graph<V, KFWeightedEdge> graph

    def DijkstraShortestPathFinder(graph) {
        this.graph = graph
    }

    def getShortestPath(V source, V target) {
        graph.vertexSet().each {
            distancesMap[it] = Double.POSITIVE_INFINITY
        }
        distancesMap[source] = 0
        vertexesToProcess << source
        while (vertexesToProcess) {
            def minimumVertex = vertexWithMinimumPath
            graph.edgesOf(minimumVertex).each { edge ->
                println "edge: $edge, $minimumVertex"
                def otherVertex = (edge.source == minimumVertex ? edge.target : edge.source)
                if (distancesMap[otherVertex] > distancesMap[minimumVertex] + edge.weight) {
                    distancesMap[otherVertex] = distancesMap[minimumVertex] + edge.weight
                    predecessorsMap[otherVertex] = (edge.source == otherVertex ? edge.target : edge.source)
                }
                if (!(edge.target in processedVertexes)) {
                    vertexesToProcess << edge.target
                }
                vertexesToProcess.remove(minimumVertex as V)
            }
            println predecessorsMap
            processedVertexes << minimumVertex
        }
        new GraphPathImpl(graph, source, target, getEdgesListFromPredcessors(source, target), distancesMap[target])
    }

    def getEdgesListFromPredcessors(source, target) {
        def edges = []
        def targetVertex = target
        while (targetVertex != source) {
            println targetVertex
            println predecessorsMap
            edges << graph.getEdge(predecessorsMap[targetVertex], targetVertex)
            targetVertex = predecessorsMap[targetVertex]
        }
        edges.reverse()
    }

    V getVertexWithMinimumPath() {
        def minimumVertex = vertexesToProcess.first()
        println "proc: $processedVertexes, $distancesMap"
        distancesMap.each { vertex, path ->
            if (distancesMap[vertex] < distancesMap[minimumVertex] && !(vertex in processedVertexes)) {
                minimumVertex = vertex
            }
        }
        minimumVertex
    }

    static void main(args) {
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
        println graph
        println graph.getEdge(6,5)
        println new DijkstraShortestPathFinder<Integer>(graph).getShortestPath(1, 5)
    }
}

