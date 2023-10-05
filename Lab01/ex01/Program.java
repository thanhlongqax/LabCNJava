public class Program {
    public static void main(String[] args){
        try {
            if (args.length != 3) {
                System.out.println("Invalid expression");
                return;
            }
            double num1 , num2 , result;
            
            num1 = Double.parseDouble(args[0]);
            char operator = args[1].charAt(0);
            num2 = Double.parseDouble(args[2]);
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        System.out.println("Division by zero is not allowed");
                        return;
                    }
                    result = num1 / num2;
                    break;
                case 'x':
                    result = num1 * num2;
                    break;
                case '^':
                    result = Math.pow(num1, num2);
                    break;
                default:
                    System.out.println("Invalid expression");
                    return;
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Unsupported operator");
        } 
       
    }
    

}
