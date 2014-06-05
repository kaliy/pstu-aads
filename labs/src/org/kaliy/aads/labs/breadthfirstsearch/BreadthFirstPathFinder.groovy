package org.kaliy.aads.labs.breadthfirstsearch

import org.kaliy.aads.labs.adjacencymatrix.AdjacencyMatrixBuilder
import org.kaliy.aads.labs.common.Node

class BreadthFirstPathFinder {

    Integer findPathLength(Node from, Node to) {
        def path = []
        def visited = [:]
        def length = 0
        def queue = [] as Queue<Node>
        queue.offer(from)
        visited[from] = true
        def toCheck = []
        while(!queue.isEmpty()) {
            def node = queue.poll()
            toCheck << node
            visited[node] = true
            if (node == to) {
                return length
            }
            if (queue.isEmpty()) {
                toCheck.each { Node checkingNode ->
                    checkingNode.nodes.each {
                        if (!visited[it]) {
                            queue.offer(it)
                        }
                    }
                }
                length++
            }
        }
        return null
    }

    static void main(args) {
        Node node1 = new Node(index: 1)
        Node node2 = new Node(index: 2)
        Node node3 = new Node(index: 3)
        Node node4 = new Node(index: 4)
        Node node5 = new Node(index: 5)

        node1.nodes << node2
        node2.nodes << node3
        node3.nodes << node4
        node4.nodes << node5
        node3.nodes << node5

        def nodes = [node1,node2,node3,node4,node5]

        println "Graph:\n${new AdjacencyMatrixBuilder().fromNodes(nodes)}"
        nodes.each {
            println "from node2 to node $it: ${new BreadthFirstPathFinder().findPathLength(node2, it)}"
        }
    }

}
