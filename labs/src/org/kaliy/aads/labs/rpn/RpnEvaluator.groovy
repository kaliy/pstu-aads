package org.kaliy.aads.labs.rpn

class RpnEvaluator {
    def evaluateRPN(expression) {
        println "Evaluating $expression"
        def stack = [] as Stack
        def stackOperation = { operation ->
            return { operation(stack.pop(), stack.pop()) }
        }
        def operations = [
            "+": stackOperation { a, b -> b + a },
            "-": stackOperation { a, b -> b - a },
            "*": stackOperation { a, b -> b * a },
            "/": stackOperation { a, b -> b / a },
            "^": stackOperation { a, b -> b ** a },
        ]
        expression.split(" ").each { item ->
            def operation = operations[item] ?: { item as BigDecimal }
            stack.push(operation())
            println "$item: $stack"
        }
        stack.pop()
    }

    static main(args) {
        println new RpnInfixPrefixConverter().convertToPrefix("52+(1+2)*4-3")
        println new RpnEvaluator().evaluateRPN(new RpnInfixPrefixConverter().convertToPrefix("52+(1+2)*4-3"))
    }
}
