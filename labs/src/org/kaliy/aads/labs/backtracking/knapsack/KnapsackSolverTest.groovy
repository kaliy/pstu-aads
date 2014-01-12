package org.kaliy.aads.labs.backtracking.knapsack

class KnapsackSolverTest extends GroovyTestCase {

    KnapsackSolver solver

    @Override
    void setUp() {
        solver = new KnapsackSolver()
    }

    void testSolverReturnsEveryElementIfTheirWeightDoesntExceedMaximum() {
        solver.maxWeight = 1000
        def items = [[price:100, weight:10],[price:100, weight:20]] as Set

        assert items == solver.solve(items)
    }

    void testSolverReturnsElementsThatHaveMaximumPrice() {
        solver.maxWeight = 20
        def items = [[price:100, weight:5],[price:500, weight:10],[price:300, weight: 10],[price: 150, weight: 5]] as Set

        assert [[price:300, weight: 10],[price:500, weight: 10]] as Set == solver.solve(items)
    }

}
