package org.kaliy.aads.labs.depthfirstsearch

import org.kaliy.aads.labs.adjacencymatrix.AdjacencyMatrixBuilder
import org.kaliy.aads.labs.common.Node

class DepthFirstSearchVertexFinder {

    def available = []
    def visitedNodesMap = [:]

    List<Node> findAvailableVertexes(Node node) {
        node.nodes.each {
            if (!visitedNodesMap[it]) {
                visitedNodesMap[it] = true
                available << it
                findAvailableVertexes(it)
            }
        }
        available
    }

    static void main(args) {
        Node node1 = new Node(index: 1)
        Node node2 = new Node(index: 2)
        Node node3 = new Node(index: 3)
        Node node4 = new Node(index: 4)
        Node node5 = new Node(index: 5)
        Node node6 = new Node(index: 6)

        node2.nodes << node4
        node2.nodes << node5
        node5.nodes << node6
        node4.nodes << node3

        println "Graph:\n${new AdjacencyMatrixBuilder().fromNodes([node1, node2, node3, node4, node5, node6])}"

        println "Avaiblable nodes from node 2: ${new DepthFirstSearchVertexFinder().findAvailableVertexes(node2)}"
        println "Avaiblable nodes from node 5: ${new DepthFirstSearchVertexFinder().findAvailableVertexes(node5)}"

    }
}
