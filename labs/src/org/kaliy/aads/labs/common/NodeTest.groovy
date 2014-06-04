package org.kaliy.aads.labs.common

class NodeTest extends GroovyTestCase {

    Node node1
    Node node2
    Node node3

    void setUp() {
        node1 = new Node(index: 1)
        node2 = new Node(index: 2)
        node3 = new Node(index: 3)
    }

    void testNodeToStringReturnsNodeDataForOneNode() {
        node1.nodes << node2

        assert "1 - 2" == node1.toString()
    }

    void testNodeToStringReturnsNodeDataForTwoNodes() {
        node1.nodes << node2
        node1.nodes << node3

        assert "1 - 2\n1 - 3" == node1.toString()
    }

}
