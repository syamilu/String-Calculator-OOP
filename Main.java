// Name: Wan Muhammad Syamil bin W Mohd Yusof
// Matric No : 2220561
// Section : 2
// Lecturer's Name : Ts. Dr. Muhamad Sadry Abu Seman

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String text;
        Boolean exit = false;

        System.out.println("Welcome to String Calculator!");
        do {
            System.out.print("Enter input : ");
            text = input.nextLine();
            StringCalculator calculator = new StringCalculator(text);

            System.out.printf("Numbers\t: " + calculator.getNumsize() + "\n");
            System.out.printf("Math Symbols\t: " + calculator.getOpsize() + "\n");
            System.out.printf("Invalid Symbols\t: " + calculator.getInvalidSymbol() + "\n\n");
            System.out.printf("Total\t : " + calculator.result());
            // calculator.rankBODMAS();//testing for sortBODMAS which is private method
            System.out.println("\n\nDo you want to continue? (Y/N)");
            String choice = input.nextLine();

            if (choice.equals("N") || choice.equals("n")) {
                exit = true;
            } else if (choice.equals("Y") || choice.equals("y")) {
                exit = false;
            } else {
                System.out.println("Invalid input. Exiting...");
                exit = true;
            }
        } while (!exit);
        input.close();
    }
}