package e1;

import e1.MyDC;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        String expression, again;

        int result;

        try {
            Scanner in = new Scanner(System.in);

            do {
                MyBC bc = new MyBC();
                System.out.println("Enter a valid postfix expression: ");
                expression = in.nextLine();
                String expression1 = bc.exc(expression);
                MyDC evaluator = new MyDC();
                System.out.println(expression1);

                result = evaluator.evaluate(expression1);
                System.out.println();
                System.out.println("That expression1 equals " + result);

                System.out.print("Evaluate another expression [Y/N]? ");
                again = in.nextLine();
                System.out.println();
            }
            while (again.equalsIgnoreCase("y"));
        } catch (Exception IOException) {
            System.out.println("Input exception reported");
        }
    }
}
