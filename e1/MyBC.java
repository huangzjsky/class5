package e1;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * 20189210 mujian
 */
public class MyBC {
    public boolean comparePrior(String operator1, String operator2) {
        if("(".equals(operator2)) {
            return true;
        }
        if ("*".equals(operator1) || "/".equals(operator1)) {
            if ("+".equals(operator2) || "-".equals(operator2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isOperator(String token) {
        return (token.equals("+") || token.equals("-") ||
                token.equals("*") || token.equals("/"));
    }
    private boolean isNum(String token){
        if (Integer.parseInt(token)>=0) {
            return true;
        }
        return false;
    }

    public String exc(String expr){
        String token;
        StringBuffer exch=new StringBuffer();
        Stack<String> op=new Stack<String>();
        StringTokenizer tokenizer = new StringTokenizer(expr);
        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            //若遇到操作数，直接输出，并输出一个空格作为两个操作数的分隔符
            if (isOperator(token)){
                if (op.empty() || comparePrior(token,op.peek())){
                    op.push(token);
                }
                else{
                    String s=op.pop();
                    exch.append(s);
                    exch.append(" ");
                }
            }
            else if("(".equals(token) || ")".equals(token)){
                if ("(".equals(token)){
                    op.push(token);
                }
                if(")".equals(token)){
                    while (!(op.peek()).equals("(")){
                        String s=op.pop();
                        exch.append(s);
                        exch.append(" ");
                    }
                }
            }
            else{
                exch.append(token);
                exch.append(" ");
            }

        }
        while (!op.empty()){
            String s=op.pop();
            if (isOperator(s))
            {
                exch.append(s);
                exch.append(" ");}
        }
        return exch.toString();
    }
}