package e1;

import java.util.StringTokenizer;
import java.util.Stack;

public class MyDC {
    /**
     * constant for addition symbol
     */
    private final char ADD = '+';
    /**
     * constant for subtraction symbol
     */
    private final char SUBTRACT = '-';
    /**
     * constant for multiplication symbol
     */
    private final char MULTIPLY = '*';
    /**
     * constant for division symbol
     */
    private final char DIVIDE = '/';
    /**
     * the stack
     */
    private Stack<Integer> stack;

    /**
     * Sets up this evalutor by creating a new stack.
     */
    public MyDC() {
        stack = new Stack<Integer>();
    }

    public int evaluate(String expr) {
        int op1, op2, result = 0;
        String token;
        StringTokenizer tokenizer = new StringTokenizer(expr);

        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();

            if (isOperator(token)) {
                op2 = (stack.pop()).intValue();
                op1 = (stack.pop()).intValue();
                result = evalSingleOp(token.charAt(0), op1, op2);
                stack.push(new Integer(result));
            } else
                stack.push(new Integer(Integer.parseInt(token)));
        }

        return result;
    }

    private boolean isOperator(String token) {
        return (token.equals("+") || token.equals("-") ||
                token.equals("*") || token.equals("/"));
    }

    private int evalSingleOp(char operation, int op1, int op2) {
        int result = 0;

        switch (operation) {
            case ADD:
                result = op1 + op2;
                break;
            case SUBTRACT:
                result = op1 - op2;
                break;
            case MULTIPLY:
                result = op1 * op2;
                break;
            case DIVIDE:
                result = op1 / op2;
        }

        return result;
    }
}