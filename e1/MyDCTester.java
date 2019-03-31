package e1;

import e1.MyDC;

import java.util.Scanner;

public class MyDCTester {

    public static void main(String[] args) {

        String expression, again;

        int result;

        try {
            Scanner in = new Scanner(System.in);

            do {
                MyDC evaluator = new MyDC();
                System.out.println("Enter a valid postfix expression: ");
                expression = in.nextLine();

                result = evaluator.evaluate(expression);
                System.out.println();
                System.out.println("That expression equals " + result);

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
