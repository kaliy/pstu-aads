package org.kaliy.aads.labs.depthfirstsearch

import org.kaliy.aads.labs.common.Node

class DepthFirstSearchVertexFinderTest extends GroovyTestCase {


    DepthFirstSearchVertexFinder finder
    Node node1
    Node node2
    Node node3
    Node node4
    Node node5

    void setUp() {
        finder = new DepthFirstSearchVertexFinder()
        node1 = new Node(index: 1)
        node2 = new Node(index: 2)
        node3 = new Node(index: 3)
        node4 = new Node(index: 4)
        node5 = new Node(index: 5)
    }

    void testFinderSearchFindsNodes() {
        node1.nodes << node2
        node2.nodes << node3
        node2.nodes << node4
        node4.nodes << node5

        assert [node3, node4, node5] as Set == finder.findAvailableVertexes(node2) as Set
    }

}
