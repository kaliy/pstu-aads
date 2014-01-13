package org.kaliy.aads.labs.backtracking.operators

class OperatorsMatcher {

    def operators
    def operations = [
            "+": {a, b -> a+b},
            "-": {a, b -> a-b}
    ]

    def solve(numbers, numberToGet) {
        preCheck(numbers, numberToGet)
        def preGeneration = operators.collect { [] << it } //xxx
        def answers = []
        generate(preGeneration, 8).each { operatorsToExecute ->

            def answer = numbers[0]
            def expression = numbers[0] as String
            operatorsToExecute.eachWithIndex { operator, index ->
                answer = operations[operator].call(answer, numbers[index+1])
                expression += "${operator}${numbers[index+1]}"
            }
            if (numberToGet == answer) {
                answers << [expression: expression, answer: answer]
            }
        }
        answers
    }

    def preCheck(List numbers, numberToCheck) {
        def singleOperatorRusults = []
        operators.each { operator ->
             singleOperatorRusults << numbers.inject {a,b -> operations[operator].call(a,b)}
        }
        def max = singleOperatorRusults.max()
        def min = singleOperatorRusults.min()
        assert numberToCheck <= max, "$numberToCheck is more than maximum ($max)"
        assert numberToCheck >= min, "$numberToCheck is less than minimum ($min)"
    }

    def generate(generated, count) {
        def newGeneration = []
        generated.each { generatedList ->
            operators.each { operator ->
                newGeneration << (generatedList.clone() << operator)
            }
        }
        if (--count > 1) {
            generate(newGeneration, count)
        } else {
            return newGeneration
        }
    }

    static main(args) {
        def numbers = [1,2,3,4,5,6,7,8,9]
        def operators = ["+","-"]
        def numberToFind = 11
        new OperatorsMatcher(operators: operators).solve(numbers, numberToFind).each {
            println "$it.expression = $it.answer"
        }
    }
}
