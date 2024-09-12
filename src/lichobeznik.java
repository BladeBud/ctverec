import java.util.Scanner;

/**
 * @author ruzicka
 * @since 2024-09-10
 */
public class lichobeznik {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Zadejte velikost lichobeyniku (musi byt liche): ");
        int velikost = scanner.nextInt();

        for (int i = 1; i <= velikost; i++) {
            for (int j = velikost; j > i; j--) {
                System.out.print(" ");
            }
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i = velikost - 1; i >= 1; i--) {
            for (int j = velikost; j > i; j--) {
                System.out.print(" ");
            }
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}