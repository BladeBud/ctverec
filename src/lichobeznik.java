import java.util.Scanner;

/**
 * @author ruzicka
 * @since 2024-09-10
 */
public class lichobeznik {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String hvezda = "*";

        System.out.println("Zadejte velikost lichobeyniku (musi byt liche): ");
        int velikost = scanner.nextInt();
        if (velikost % 2 != 0) {
            for (int j = 0; j < velikost; j++) {

                System.out.println(hvezda);
                hvezda = hvezda + "*" + "*";
            }

        } else {
            System.out.println("Zadejte liche cislo.");
        }

    }
}