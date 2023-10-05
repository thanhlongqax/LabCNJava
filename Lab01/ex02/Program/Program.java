import vn.edu.tdtu.*;
public class Program {
    public static void main(String[] args){
        int a[] = {12,4,5,6,7};
        int b[] = {20,191,22,4,2,100};
        ArrayOutput.print(a);
        System.out.println("-------------------------Mang A");
        ArrayOutput.print(b);
        System.out.println("-------------------------Mang B");
        int c[] = ArrayHandler.merge(a,b);
        ArrayOutput.print(c);
        System.out.println("-------------------------Mang C sau khi merge");
        ArrayHandler.sort(c);
        ArrayOutput.print(c);
        System.out.println("-------------------------Mang C da sap xem");
        ArrayOutput.write(c,"output.txt");
    }
}
