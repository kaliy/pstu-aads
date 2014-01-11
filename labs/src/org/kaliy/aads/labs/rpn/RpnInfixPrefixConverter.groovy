package org.kaliy.aads.labs.rpn

/**
 * TODO: negative numbers conversion
 */
class RpnInfixPrefixConverter {
    def convertToPrefix(input) {
        println "Converting $input to RPN"
        def stack = [] as Stack
        def prefixString = ""
        def priorities = ["-": 1, "+": 1, "*": 2, "/": 2, "^": 3]
        input.eachWithIndex { it, index ->
            println "$it: $stack, RPN: $prefixString"
            switch(it) {
                case "(":
                    stack.push("(")
                    break
                case ")":
                    while (!stack.empty && "(" != stack.peek()) {
                        prefixString += " ${stack.pop()}"
                    }
                    stack.pop() // )
                    break
                case priorities.keySet():
                    if (!stack.empty && priorities[it] <= priorities[stack.peek()]) {
                        while (!stack.empty && priorities[stack.peek()] >= priorities[it]) {
                            prefixString += " ${stack.pop()}"
                        }
                    }
                    prefixString += " "
                    stack.push(it)
                    break
                default:
                    prefixString += it
                    break
            }
        }
        prefixString = "$prefixString ${stack.reverse().join(" ")}".trim().replaceAll(" +", " ")
        println "Expression in RPN: $prefixString"
        prefixString
    }
}
