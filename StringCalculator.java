
// Name: Wan Muhammad Syamil bin W Mohd Yusof
// Matric No : 2220561
// Section : 2
// Lecturer's Name : Ts. Dr. Muhamad Sadry Abu Seman
import java.util.Stack;

public class StringCalculator {
    private Stack<Float> operand = new Stack<>();
    private Stack<Character> operator = new Stack<>();
    private String[] arrayofStrings;

    private int numsize = 0;// size of number
    private int opsize = 0;// size of operator
    private int invalidSize = 0;// size of invalid symbol
    private boolean isInvalid = false;// check if true then is invalid
    private float total = 0;// total of the calculation

    public StringCalculator() {

    }

    public StringCalculator(String input) {
        // arrayofStrings = input.split(
        // "(?<=\\d)(?=[^\\.\\d])|(?<=[^\\.\\d])(?=\\d)|(?<=\\.)(?=[^\\d])|(?<=[^\\d])(?=\\.)|(?<=\\D)(?=\\D)");
        arrayofStrings = input.split(
                "(?<=\\d)(?=[^\\.\\d])|(?<=[^\\.\\d])(?=\\d)|(?<=\\.)(?=[^\\d])|(?<=[^\\d])(?=\\.)|(?<=\\D)(?=\\D)|(?<=\\()|(?<=\\))|(?=\\()|(?=\\))");
        /*
         * this regex split decimal and operator,, ".5" and "5." is consider as number
         * eventhough its 5.a or a.5... but 'a' is invalid
         */
        checkArray();
    }

    // to check for valid and invalid symbol
    private void checkArray() {
        for (int i = 0; i < this.arrayofStrings.length; i++) {
            if (this.arrayofStrings[i].charAt(0) >= '0' && this.arrayofStrings[i].charAt(0) <= '9'
                    || this.arrayofStrings[i].matches("\\.\\d+")) {
                setNumsize();
            } else if (this.arrayofStrings[i].charAt(0) == '+' || this.arrayofStrings[i].charAt(0) == '-'
                    || this.arrayofStrings[i].charAt(0) == '*' || this.arrayofStrings[i].charAt(0) == '/'
                    || this.arrayofStrings[i].charAt(0) == '%' || this.arrayofStrings[i].charAt(0) == '^') {
                setOpsize();
            } else if (this.arrayofStrings[i].charAt(0) == '(' || this.arrayofStrings[i].charAt(0) == ')') {
                setOpsize();
            } else {
                setInvalidSymbol();
            }
        }
    }

    // setter and getter
    private void setInvalidSymbol() {
        this.invalidSize++;
        this.isInvalid = true;
    }

    private void setNumsize() {
        this.numsize++;
    }

    private void setOpsize() {
        this.opsize++;
    }

    public int getInvalidSymbol() {
        return this.invalidSize;
    }

    public int getNumsize() {
        return this.numsize;
    }

    public int getOpsize() {
        return this.opsize;
    }
    // end of setter and getter

    // function to get result
    public String result() {
        if (isInvalid == true) {
            return "Invalid!!";
        }

        evaluateExpression();

        total = operand.pop();

        return String.format("%.2f", total);
    }

    // to evaluate according to BODMAS
    private void evaluateExpression() {
        for (int i = 0; i < arrayofStrings.length; i++) {
            if (Character.isDigit(arrayofStrings[i].charAt(0))) {
                operand.push(Float.parseFloat(arrayofStrings[i]));
            } else if (arrayofStrings[i].equals('(')) {
                operator.push(arrayofStrings[i].charAt(0));
            } else if (arrayofStrings[i].equals(")")) {
                while (!operator.isEmpty() && operator.peek() != '(') {
                    calculation();
                }
                if (operator.peek() == '(') {
                    operator.pop();
                }
            } else {
                while (!operator.isEmpty() && rankBODMAS(arrayofStrings[i].charAt(0)) <= rankBODMAS(operator.peek())) {
                    calculation();
                }
                operator.push(arrayofStrings[i].charAt(0));
            }

        }
        while (!operator.isEmpty()) {
            calculation();
        }
    }

    // perform the calculation
    private void calculation() {
        float val1 = operand.pop();
        float val2 = operand.pop();
        char op = operator.pop();
        switch (op) {
            case '+':
                operand.push(val2 + val1);
                break;
            case '-':
                operand.push(val2 - val1);
                break;
            case '*':
                operand.push(val2 * val1);
                break;
            case '/':
                operand.push(val2 / val1);
                break;
            case '%':
                operand.push(val2 % val1);
                break;
            case '^':
                operand.push((float) Math.pow(val2, val1));
                break;
        }
    }

    // to rank the bodmas based on its precedence
    private int rankBODMAS(char c) {
        if (c == '^') {
            return 3;
        } else if (c == '*' || c == '/' || c == '%') {
            return 2;
        } else if (c == '+' || c == '-') {
            return 1;
        } else {
            return 0;
        }
    }
}
