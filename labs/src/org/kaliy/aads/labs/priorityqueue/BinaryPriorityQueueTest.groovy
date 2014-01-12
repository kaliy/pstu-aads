package org.kaliy.aads.labs.priorityqueue

class BinaryPriorityQueueTest extends GroovyTestCase {

    PriorityQueue queue

    @Override
    void setUp() {
        queue = new BinaryHeapPriorityQueue()
    }

    void testQueueAddsNewElements() {
        queue.add("test1", 10)
        queue.add("test2", 20)

        assert 2 == queue.size()
    }

    void testPeekReturnsElementWithoutRemoving() {
        queue.add("test", 10)

        assert "test" == queue.peek()
        assert 1 == queue.size()
    }

    void testPopReturnsElementWithRemoving() {
        queue.add("test", 10)

        assert "test" == queue.pop()
        assert 0 == queue.size()
    }

    void testPopReturnsElementWithMaximumPriorityUsingMultiplePops() {
        queue.add("test1", 10)
        queue.add("test2", 40)
        queue.add("test3", 8)
        queue.add("test4", 5)
        queue.add("test5", 32)
        queue.add("test6", 1)
        queue.add("test7", 27)
        queue.add("test8", 7)
        queue.add("test9", 18)
        queue.add("test10", 4)

        assert "test2" == queue.pop()
        assert "test5" == queue.pop()
        assert "test7" == queue.pop()
        assert "test9" == queue.pop()
        assert "test1" == queue.pop()
        assert "test3" == queue.pop()
        assert "test8" == queue.pop()
        assert "test4" == queue.pop()
        assert "test10" == queue.pop()
        assert "test6" == queue.pop()
    }

    void testPopReturnsElementWithMaximumPriority() {
        queue.add("test1", 10)
        queue.add("test2", 40)
        queue.add("test3", 8)

        assert "test2" == queue.pop()

    }

    void testPeekReturnsElementWithMaximumPriority() {
        queue.add("test1", 10)
        queue.add("test2", 40)
        queue.add("test3", 5)

        assert "test2" == queue.peek()
    }

}
