package org.kaliy.aads.labs.common

class Node implements Comparable<Node> {
    Integer index
    List<Node> nodes = []
    String toString() {
        nodes.collect() { "$index - $it.index" }.join("\n")
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof Node)) return false

        if (index != o.index) return false
        if (nodes.collect{it.index} as Set != o.nodes.collect{it.index} as Set) return false

        return true
    }

    int hashCode() {
        int result
        result = (index != null ? index.hashCode() : 0)
        result = 31 * result + (nodes.collect{it.index})?.hashCode()
        return result
    }

    @Override
    int compareTo(Node o) {
        return index.compareTo(o.index)
    }
}
