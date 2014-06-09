package org.kaliy.aads.labs.common

import org.jgrapht.graph.DefaultWeightedEdge

//@Grapes([
//        @Grab(group='org.jgrapht', module='jgrapht-core', version='0.9.0'),
//        @GrabConfig(systemClassLoader=true)
//])
class KFWeightedEdge extends DefaultWeightedEdge implements Comparable<KFWeightedEdge> {

    @Override
    int hashCode() {
        int result
        result = weight.hashCode()
        result = 31 * result + (source == null ? 0 : source.hashCode())
        result = 31 * result + (target == null ? 0 : target.hashCode())
        return result
    }

    @Override
    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof DefaultWeightedEdge)) return false
        if (o.weight != weight) return false
        if (o.source != source) return false
        if (o.target != target) return false
        return true
    }

    String toString() {
        "($source : $target (w: $weight))"
    }

    @Override
    int compareTo(KFWeightedEdge o) {
//        println ("$weight -- $o.weight -- ${weight.compareTo(o.weight)}")
        weight.compareTo(o.weight)
    }
}
