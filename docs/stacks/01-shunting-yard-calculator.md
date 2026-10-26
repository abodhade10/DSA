# Shunting Yard Algorithm — Infix to Postfix

## Problem

Convert an infix arithmetic expression into postfix notation and evaluate the result.

**Input:**

`1 + 4 * 2 - 5`

**Output:**

- Postfix: `1 4 2 * + 5 -`
- Result: `4`

## Pattern

**Stack + Operator Precedence**

The Shunting Yard algorithm uses an operator stack to convert infix expressions into postfix notation. A second stack
evaluates the postfix expression.

## Why This Algorithm?

In an infix expression, operators have different priorities.

For example:

`1 + 4 * 2 - 5`

Multiplication must happen before addition and subtraction.

The algorithm handles operator precedence and associativity without needing to evaluate the expression directly.

## Key Concepts

### 1. Infix Notation

The operator appears between operands.

Example: `4 * 2`

### 2. Postfix Notation

The operator appears after its operands.

Example: `4 2 *`

Postfix notation removes the need for parentheses and precedence rules during evaluation.

### 3. Operator Precedence

| Operator | Precedence |
|----------|-----------:|
| `^`      |          4 |
| `*`, `/` |          3 |
| `+`, `-` |          2 |

A higher number means higher precedence.

### 4. Associativity

- Exponentiation (`^`) is right-associative.
- Addition, subtraction, multiplication, and division are left-associative.

For example:

- `2 ^ 3 ^ 2` means `2 ^ (3 ^ 2)`.
- `10 - 3 - 2` means `(10 - 3) - 2`.

## Step 1: Convert Infix to Postfix

**Input:** `1 + 4 * 2 - 5`

Maintain two collections:

- `output`: stores the postfix expression.
- `opStack`: temporarily stores operators.

### Conversion Rules

1. If the token is a number, add it to output.
2. If the token is an opening parenthesis, push it onto the operator stack.
3. If the token is a closing parenthesis, pop operators until the matching opening parenthesis.
4. If the token is an operator, pop higher-precedence operators and equal-precedence operators when the current operator
   is left-associative.
5. Push the current operator onto the stack.
6. After processing all tokens, pop the remaining operators into output.

### Dry Run

| Token | Output          | Operator Stack |
|-------|-----------------|----------------|
| `1`   | `1`             | Empty          |
| `+`   | `1`             | `+`            |
| `4`   | `1 4`           | `+`            |
| `*`   | `1 4`           | `+ *`          |
| `2`   | `1 4 2`         | `+ *`          |
| `-`   | `1 4 2 * +`     | `-`            |
| `5`   | `1 4 2 * + 5`   | `-`            |
| End   | `1 4 2 * + 5 -` | Empty          |

### Why Are `*` and `+` Popped When `-` Arrives?

The current operator is `-`.

- `*` has higher precedence, so it must be processed first.
- `+` and `-` have equal precedence.
- Both are left-associative, so the earlier `+` must be processed first.

Therefore, both operators are popped before `-` is pushed.

**Final postfix expression:**

`1 4 2 * + 5 -`

## Step 2: Evaluate Postfix

Process the postfix expression from left to right using a number stack.

### Evaluation Rules

1. If the token is a number, push it onto the stack.
2. If the token is an operator, pop the right operand first and then the left operand.
3. Perform the operation: `left operator right`.
4. Push the result back onto the stack.
5. At the end, exactly one value should remain.

### Dry Run

| Token | Stack       | Action                |
|-------|-------------|-----------------------|
| `1`   | `[1]`       | Push 1                |
| `4`   | `[1, 4]`    | Push 4                |
| `2`   | `[1, 4, 2]` | Push 2                |
| `*`   | `[1, 8]`    | Calculate `4 * 2 = 8` |
| `+`   | `[9]`       | Calculate `1 + 8 = 9` |
| `5`   | `[9, 5]`    | Push 5                |
| `-`   | `[4]`       | Calculate `9 - 5 = 4` |

**Final result: `4`**

## Why Pop the Right Operand First?

For subtraction and division, operand order matters.

Example: `10 3 -`

- Pop right operand: `3`.
- Pop left operand: `10`.
- Calculate `10 - 3 = 7`.

Reversing the operands would incorrectly produce `3 - 10 = -7`.

## Complexity Analysis

Let `n` be the number of tokens in the expression.

- **Time Complexity:** `O(n)` — each token is processed a constant number of times.
- **Space Complexity:** `O(n)` — output and stacks may store up to `n` elements.

## Common Mistakes

1. Evaluating operators without considering precedence.
2. Forgetting to pop higher-precedence operators.
3. Forgetting that equal-precedence operators must be popped for left associativity.
4. Reversing the left and right operands during postfix evaluation.
5. Forgetting to pop remaining operators at the end.
6. Ignoring division by zero or mismatched parentheses.
7. Assuming the implementation supports unary minus or expressions without whitespace between tokens.

## Memory Trick

**Conversion:** Numbers go to output; operators wait on the stack.

**Evaluation:** Numbers go on the stack; operators consume two numbers and return one result.

## Interview Explanation

"The Shunting Yard algorithm converts infix expressions into postfix notation using an operator stack. It handles
precedence, associativity, and parentheses during conversion. Then I evaluate the postfix expression using a number
stack, popping two operands for each operator and pushing the result back. Both phases run in linear time."

## Code Reference

`src/main/kotlin/blind75/stack/ShuntingYardCalculator.kt`
