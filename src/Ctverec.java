import java.util.Scanner;

public class Ctverec {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int s;


        System.out.println("zadejte hodnotu stran čtverce: ");
        s = scanner.nextInt();

        for (int j = 0; j < s; j++){

            for (int i = 0; i < s; i++) {

                if (j == 0 || j == s - 1 || i == 0 || i == s - 1 ){
                    System.out.printf("*" + " ");

                }else{
                    System.out.printf(" " + " ");
                }
            }
            System.out.println();
        }
    }
}