import java.util.Scanner;

/**
 * @author ruzicka
 * @since 2024-09-10
 */
public class Kosoctverec {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Zadejte velikost kosoctverce (musi byt liche): ");
        int velikost = scanner.nextInt();
        int polovina = (velikost+1) / 2;

        for (int i = 1; i <= polovina; i++) {
            kosoctverecovani(polovina, i);
        }

        for (int i = polovina - 1; i >= 1; i--) {
            kosoctverecovani(polovina, i);
        }
    }

    private static void kosoctverecovani(int polovina, int i) {
        for (int j = polovina; j > i; j--) {
            System.out.print(" ");
        }
        for (int k = 1; k <= (2 * i - 1); k++) {
            System.out.print("*");
        }
        System.out.println();
    }
}