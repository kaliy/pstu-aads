package org.kaliy.aads.labs.breadthfirstsearch

import org.kaliy.aads.labs.common.Node

class BreadthFirstPathFinderTest extends GroovyTestCase {
    BreadthFirstPathFinder finder
    Node node1
    Node node2
    Node node3
    Node node4
    Node node5

    void setUp() {
        finder = new BreadthFirstPathFinder()
        node1 = new Node(index: 1)
        node2 = new Node(index: 2)
        node3 = new Node(index: 3)
        node4 = new Node(index: 4)
        node5 = new Node(index: 5)
    }

    void testPathFinderReturnsNullIfThereIsNoPathToNode() {
        node1.nodes << node2
        node1.nodes << node3
        node3.nodes << node4
        node2.nodes << node4

        assert null == finder.findPathLength(node1, node5)
    }

    void testPathFinderReturnsShortestPath() {
        node1.nodes << node2
        node2.nodes << node3
        node3.nodes << node4
        node4.nodes << node5
        node3.nodes << node5

        assert 3 == finder.findPathLength(node1, node5)
    }

    void testPathFinderDoesntRaiseErrorOnLoop() {
        node1.nodes << node2
        node2.nodes << node3
        node3.nodes << node1
        node3.nodes << node4
        node4.nodes << node5

        assert 4 == finder.findPathLength(node1, node5)
    }

}
