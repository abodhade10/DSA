package blind75.stack

import kotlin.math.pow

fun main() {
    // Input expression in infix notation
    val expression = "1 + 4 * 2 - 5"

    // Step 1: Convert infix expression to postfix notation
    val postfix = infixToPostfix(expression)
    println("POSTFIX: ${postfix.joinToString(" ")}")

    // Step 2: Evaluate the postfix expression
    val result = evaluatePostfix(postfix)
    println("RESULT: $result")
}

// Defines the priority of each operator.
// Higher value means higher priority.
private val precedence = mapOf(
    "^" to 4, // Exponentiation has the highest priority
    "*" to 3, // Multiplication
    "/" to 3, // Division
    "+" to 2, // Addition
    "-" to 2  // Subtraction
)

// Defines whether each operator is right-associative.
// Right-associative: 2 ^ 3 ^ 2 means 2 ^ (3 ^ 2).
// Other operators are left-associative.
private val rightAssociative = mapOf(
    "^" to true,
    "*" to false,
    "/" to false,
    "+" to false,
    "-" to false
)

// Converts an infix expression into postfix notation.
fun infixToPostfix(expression: String): List<String> {
    // Stores the final postfix expression
    val output = mutableListOf<String>()

    // Temporarily stores operators and opening parentheses
    val opStack = ArrayDeque<String>()

    // Splits the expression into tokens using whitespace.
    // Example: "1 + 4 * 2" becomes ["1", "+", "4", "*", "2"]
    val tokens = expression.trim().split(Regex("\\s+"))

    // Process each token from left to right
    for (token in tokens) {
        when {
            token == "(" -> {
                // Push opening parenthesis onto the stack.
                // It marks the beginning of a grouped expression.
                opStack.addLast(token)
            }

            token == ")" -> {
                // Pop operators into output until the matching "(" is found.
                while (opStack.isNotEmpty() && opStack.last() != "(") {
                    output.add(opStack.removeLast())
                }

                // A closing parenthesis must have a matching opening parenthesis.
                require(opStack.isNotEmpty()) {
                    "Mismatched parentheses"
                }

                // Remove "(" from the stack.
                // Parentheses are not included in postfix output.
                opStack.removeLast()
            }

            token in precedence -> {
                // The token is an operator such as +, -, *, / or ^
                while (opStack.isNotEmpty() && opStack.last() != "(") {
                    // Look at the operator currently on top of the stack
                    val top = opStack.last()

                    // Get the priorities of the current and top operators
                    val currentPriority = precedence.getValue(token)
                    val topPriority = precedence.getValue(top)

                    // Decide whether the top operator must be processed first.
                    // Pop if the top operator has higher priority.
                    // If priorities are equal, pop when the current operator
                    // is left-associative.
                    val shouldPop =
                        currentPriority < topPriority ||
                                (
                                        currentPriority == topPriority &&
                                                !rightAssociative.getValue(token)
                                        )

                    // If the top operator should stay on the stack, stop popping
                    if (!shouldPop) break

                    // Remove the top operator and append it to postfix output
                    output.add(opStack.removeLast())
                }

                // Push the current operator onto the stack
                opStack.addLast(token)
            }

            else -> {
                // The token should be a number.
                // toDouble() validates it and throws an error if invalid.
                token.toDouble()

                // Numbers go directly into postfix output
                output.add(token)
            }
        }
    }

    // After processing all tokens, move remaining operators to output
    while (opStack.isNotEmpty()) {
        val operator = opStack.removeLast()

        // An opening parenthesis left here means it has no matching ")"
        require(operator != "(") {
            "Mismatched parentheses"
        }

        output.add(operator)
    }

    // Return the completed postfix expression
    return output
}

// Evaluates a postfix expression and returns the final result.
fun evaluatePostfix(postfix: List<String>): Double {
    // Stores numbers and intermediate calculation results
    val stack = ArrayDeque<Double>()

    // Process each postfix token from left to right
    for (token in postfix) {
        if (token !in precedence) {
            // If the token is a number, push it onto the stack
            stack.addLast(token.toDouble())
        } else {
            // An operator needs two operands.
            // Check that at least two numbers are available.
            require(stack.size >= 2) {
                "Invalid expression"
            }

            // Pop the right operand first
            val right = stack.removeLast()

            // Pop the left operand second
            val left = stack.removeLast()

            // Perform the operation using the correct operand order
            val result = when (token) {
                "+" -> left + right
                "-" -> left - right
                "*" -> left * right

                "/" -> {
                    // Prevent division by zero
                    require(right != 0.0) {
                        "Division by zero"
                    }
                    left / right
                }

                "^" -> left.pow(right)

                // Handles an operator that is not supported
                else -> error("Unsupported operator")
            }

            // Push the calculated result back onto the stack
            stack.addLast(result)
        }
    }

    // A valid postfix expression must leave exactly one result
    require(stack.size == 1) {
        "Invalid expression"
    }

    // Remove and return the final answer
    return stack.removeLast()
}
