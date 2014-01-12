package org.kaliy.aads.labs.priorityqueue


class BinaryHeapPriorityQueue implements PriorityQueue  {

    def list = [] as ArrayList

    @Override
    def add(element, priority) {
        list << new Node(element: element, priority: priority)
        moveLastElementUp()
        println list
    }

    @Override
    def pop() {
        def element = peek()
        updateHeapAfterPop()
        element
    }

    @Override
    def peek() {
        list.first().element
    }

    @Override
    def size() {
        list.size()
    }

    def private moveLastElementUp() {
        def index = list.size()-1
        use(Index, Collections) {
            while (index.hasParent() && list[index.parentIndex]?.priority < list[index]?.priority) {
                list.swap(index.parentIndex, index)
                index = index.parentIndex
            }
        }
    }

    def private updateHeapAfterPop() {
        use(Collections, Index) {
            list.swap(0, list.size()-1)
            list.remove(list.size()-1)
            def index = 0
            while (index.leftIndex <= list.size()-1) {
                int smallerChild = index.leftIndex
                if (index.rightIndex <= list.size()-1 &&
                        list[index.leftIndex].priority < list[index.rightIndex].priority ) {
                    smallerChild = index.rightIndex
                }

                if (list[index].priority < list[smallerChild].priority) {
                    list.swap(index, smallerChild)
                } else {
                    break;
                }
                index = smallerChild;
            }
        }
    }

    class Node {
        def element
        def priority

        @Override
        String toString() {
            "$priority - $element"
        }
    }

    class Index {
        static Integer getParentIndex(Integer self) {
            self / 2 as Integer
        }
        static Integer getLeftIndex(Integer self) {
            self * 2 + 1
        }
        static Integer getRightIndex(Integer self) {
            self * 2 + 2
        }
        static Boolean hasParent(Integer self) {
            self > 0
        }
    }

    String toString() {
        list.toString()
    }
}
