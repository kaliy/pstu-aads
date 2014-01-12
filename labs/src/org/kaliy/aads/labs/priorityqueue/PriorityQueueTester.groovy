package org.kaliy.aads.labs.priorityqueue

class PriorityQueueTester {

    static main(args) {
        def queue = new BinaryHeapPriorityQueue()
        new File("priorityqueue").eachLine {
            switch(it[0]) {
                case "+":
                    def number = it.substring(1) as Integer
                    queue.add(number, number)
                    println queue
                    break
                case "-":
                    println queue.pop()
                    break
            }
        }
    }

}
