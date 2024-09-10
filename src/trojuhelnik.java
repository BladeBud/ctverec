import java.util.Scanner;

/**
 * @author ruzicka
 * @since 2024-09-10
 */
public class trojuhelnik {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Zadejte hodnotu strany a: ");
        int a = scanner.nextInt();
        System.out.println("Zadejte hodnotu strany b: ");
        int b = scanner.nextInt();
        System.out.println("Zadejte hodnotu strany c: ");
        int c = scanner.nextInt();

      if (a + b > c && a + c > b && b + c > a) {
            System.out.println("Trojúhelník je platný.");
        } else {
            System.out.println("Trojúhelník není platný.");
        }

    }
}
