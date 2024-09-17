import javax.swing.*;
import java.awt.*;

/**
 * @author ruzicka
 * @since 2024-09-16
 */
public class Trojuhelnik2 {
    private Point point1;
    private Point point2;
    private Point point3;

    public Trojuhelnik2(Point p1, Point p2, Point p3) {
        point1 = p1;
        point2 = p2;
        point3 = p3;
        if (getSideA() + getSideB( ) <= getSideC() || getSideA() + getSideC() <= getSideB() || getSideB() + getSideC() <= getSideA()) {
            throw new IllegalArgumentException("Body netvori trojuhelnik");
        }
    }
    public static Trojuhelnik2 GUIpopupask() {
        JTextField x1Field = new JTextField(5);
        JTextField y1Field = new JTextField(5);
        JTextField x2Field = new JTextField(5);
        JTextField y2Field = new JTextField(5);
        JTextField x3Field = new JTextField(5);
        JTextField y3Field = new JTextField(5);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 4));
        panel.add(new JLabel("x1:"));
        panel.add(x1Field);
        panel.add(new JLabel("y1:"));
        panel.add(y1Field);
        panel.add(new JLabel("x2:"));
        panel.add(x2Field);
        panel.add(new JLabel("y2:"));
        panel.add(y2Field);
        panel.add(new JLabel("x3:"));
        panel.add(x3Field);
        panel.add(new JLabel("y3:"));
        panel.add(y3Field);

        int result = JOptionPane.showConfirmDialog(null, panel, "Zadejte souradnice bodu", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int x1 = Integer.parseInt(x1Field.getText());
            int y1 = Integer.parseInt(y1Field.getText());
            int x2 = Integer.parseInt(x2Field.getText());
            int y2 = Integer.parseInt(y2Field.getText());
            int x3 = Integer.parseInt(x3Field.getText());
            int y3 = Integer.parseInt(y3Field.getText());

            return new Trojuhelnik2(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3));
        }
        return null;
    }
    public double getSideA() {
        return point1.distance(point2);
    }
    public double getSideB() {
        return point2.distance(point3);
    }
    public double getSideC() {
        return point3.distance(point1);
    }
    public double getPerimeter() {
        return getSideA() + getSideB() + getSideC();
    }
    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - getSideA()) * (s - getSideB()) * (s - getSideC()));
    }
    @Override
    public String toString() {
        return String.format("Trojuhelnik: %s, %s, %s", point1, point2, point3);
    }
    public static void main(String[] args) {
        Trojuhelnik2 trojuhelnik = GUIpopupask();
        System.out.println(trojuhelnik);
        System.out.println("Obvod: " +String.format("%.2f", trojuhelnik.getPerimeter()));
        System.out.println("Obsah: " + String.format("%.2f", trojuhelnik.getArea()) );
    }
}
