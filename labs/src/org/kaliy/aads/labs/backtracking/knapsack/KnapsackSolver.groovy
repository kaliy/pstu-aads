package org.kaliy.aads.labs.backtracking.knapsack

class KnapsackSolver {

    def items
    def maxWeight
    def private maxPriceNode = new Node()


    class Node {
        def totalWeight
        def totalPrice
        def items = [] as Set
        def children = [] as List
    }

    //http://www.academic.marist.edu/~jzbv/algorithms/Branch%20and%20Bound.htm
    def buildTreeAndGetMaximumNode(node, items) {
        if (node.totalPrice > maxPriceNode.totalPrice) {
            maxPriceNode = node
        }
        items.each {
            def childNode = new Node()
            node.children << childNode
            childNode.items = node.items.clone()
            childNode.items << it
            childNode.totalPrice = childNode.items.collect{it.price}.sum()
            childNode.totalWeight = childNode.items.collect{it.weight}.sum()
            if (childNode.totalWeight <= maxWeight) {
                def newItems = items.clone()
                newItems.remove(it)
                buildTreeAndGetMaximumNode(childNode, newItems)
            }
        }
        maxPriceNode
    }

    def solve(items) {
        buildTreeAndGetMaximumNode(new Node(), items).items
    }

    static main(args) {
        def time = System.currentTimeMillis()
        def items = []
        new File("knapsack").splitEachLine(" ") {
            items << [price: it[0] as BigDecimal, weight: it[1] as BigDecimal]
        }
        def solvedItems = new KnapsackSolver(items: items, maxWeight: 50).solve(items)
        println "Total weight: ${solvedItems.collect{it.weight}.sum()}"
        println "Items: $solvedItems"
        println "Took ${System.currentTimeMillis() - time} ms"
    }

}
