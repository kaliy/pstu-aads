package org.kaliy.aads.labs.adjacencymatrix

import org.kaliy.aads.labs.common.Node;

class AdjacencyMatrixBuilder {
    String fromNodes(List<Node> nodes) {
        def matrixData = []
        nodes.each { node ->
            def matrixLine = []
            (1..nodes.size()).each {matrixLine << 0}
            node.nodes.collect{it.index}.each {
                matrixLine[it - 1] = 1
            }
            matrixData << matrixLine
        }
        matrixData.collect{it.join(" ")}.join("\n")
    }

    List<Node> fromMatrix(String matrix) {
        def nodeList = []
        def vertexes = matrix.split("\n").collect{it.split(" ")}
        def maxSize = vertexes.collect{it.size()}.max()
        (1..maxSize).each {
            nodeList << new Node(index: it)
        }
        vertexes.eachWithIndex { vertex, vertexIndex ->
            vertex.eachWithIndex { node, index ->
                if ("1" == node) {
                    nodeList[vertexIndex].nodes << nodeList[index]
                }
            }
        }
        nodeList
    }

    static void main(def args) {
        def builder = new AdjacencyMatrixBuilder()

        Node node1 = new Node(index: 1)
        Node node2 = new Node(index: 2)
        Node node3 = new Node(index: 3)
        Node node4 = new Node(index: 4)
        node1.nodes << node2
        node1.nodes << node3
        node2.nodes << node1
        node2.nodes << node2
        node3.nodes << node4
        node3.nodes << node1
        node4.nodes << node3
        println "From nodes:"
        println builder.fromNodes([node1,node2,node3,node4])

        def matrix = "0 0 0 1\n1 1 0 1\n0 0 1 0\n1 1 0 1"
        println "From matrix:\n$matrix\n"
        println builder.fromMatrix(matrix).join("\n")

    }

}
