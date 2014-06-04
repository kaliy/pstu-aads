package org.kaliy.aads.labs.adjacencymatrix

import org.kaliy.aads.labs.common.Node

class AdjacencyMatrixBuilderTest extends GroovyTestCase {

    AdjacencyMatrixBuilder builder
    Node node1
    Node node2
    Node node3

    void setUp() {
        node1 = new Node(index: 1)
        node2 = new Node(index: 2)
        node3 = new Node(index: 3)

        builder = new AdjacencyMatrixBuilder()
    }

    void testBuilderBuildsNodesFromMatrixString() {
        String matrix = "0 1 0\n1 0 0\n1 1 0"
        node1.nodes << node2
        node2.nodes << node1
        node3.nodes << node1
        node3.nodes << node2

        assert [node1, node2, node3] as Set == builder.fromMatrix(matrix) as Set
    }

    void testBuilderBuildsMatrixStringFromNodesList() {
        node1.nodes << node2
        node2.nodes << node1
        node3.nodes << node1
        node3.nodes << node2

        assert "0 1 0\n1 0 0\n1 1 0" == builder.fromNodes([node1, node2, node3])
    }
}
