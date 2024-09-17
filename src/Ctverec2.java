import javax.swing.*;
import java.awt.*;

/**
 * @author ruzicka
 * @since 2024-09-17
 */
public class Ctverec2 {
    //----------------------------------------
    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;
    //----------------------------------------
    /**
     * Konstruktor
     * @param p1 - levy dolni bod ctverce
     * @param p2 - pravy horni bod ctverce
     */
    public Ctverec2(Point p1, Point p2) {
        point1 = p1;
        point2 = p2;
        makePoint3();
        makePoint4();

    }
    /**
     * GUI popup pro zadani souradnic bodu
     */
    public void GUIpopupask() {
        JTextField x1Field = new JTextField(5);
        JTextField y1Field = new JTextField(5);
        JTextField x2Field = new JTextField(5);
        JTextField y2Field = new JTextField(5);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        panel.add(new JLabel("x1:"));
        panel.add(x1Field);
        panel.add(new JLabel("y1:"));
        panel.add(y1Field);
        panel.add(new JLabel("x2:"));
        panel.add(x2Field);
        panel.add(new JLabel("y2:"));
        panel.add(y2Field);


        int result = JOptionPane.showConfirmDialog(null, panel, "Zadejte souradnice bodu", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int x1 = Integer.parseInt(x1Field.getText());
            int y1 = Integer.parseInt(y1Field.getText());
            int x2 = Integer.parseInt(x2Field.getText());
            int y2 = Integer.parseInt(y2Field.getText());

            point1 = new Point(x1, y1);
            point2 = new Point(x2, y2);
            makePoint3();
            makePoint4();
        }
        if (point1.getX() == point2.getX() || point1.getY() == point2.getY() || point1.distance(point3) != point1.distance(point4)) {
            throw new IllegalArgumentException("Body netvori ctverec");
        }

    }
    /**
     * @return point1 - levy dolni bod ctverce
     */
    public Point getPoint1() {
        return point1;
    }
    /**
     * @return point2 - pravy horni bod ctverce
     */
    public Point getPoint2() {
        return point2;
    }
    /**
     * point3 - levy horni bod ctverce
     */
    public void makePoint3() {
        point3 = new Point((int) point1.getX(), (int) point2.getY());
    }
    /**
     * point4 - pravy dolni bod ctverce
     */
    public void makePoint4() {
        point4 = new Point((int) point2.getX(), (int) point1.getY());
    }

    /**
     * @return delka strany ctverce
     */
    public double getSideA() {
        return point1.distance(point3);
    }
    /**
     * @return obvod ctverce
     */
    public double getPerimeter() {
        return 4*getSideA()  ;
    }
    /**
     * @return obsah ctverce
     */
    public double getArea() {
        return getSideA() * getSideA();
    }
    /**
     * @return text ctverec
     */
    @Override
    public String toString() {
        return String.format("Ctverec: %s, %s, %s, %s", point1, point2, point3, point4);
    }
    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        Ctverec2 ctverec = new Ctverec2(new Point(0, 0), new Point(1, 1));
        ctverec.GUIpopupask();
        System.out.println(ctverec);
        System.out.println("Obvod ctverce je " + ctverec.getPerimeter());
        System.out.println("Obsah ctverce je " + ctverec.getArea());
    }
}
