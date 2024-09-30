import javax.swing.*;
import java.awt.*;

public class Ctverec2 {
    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;
    private boolean naklonen;

    public Ctverec2(Point p1, Point p2) {
        point1 = p1;
        point2 = p2;
        this.naklonen = naklonen();
        makePoint3();
        makePoint4();
    }

    public Ctverec2(Point center, double diagonal) {
        double side = diagonal / Math.sqrt(2);
        point1 = new Point((int) (center.x - side / 2), (int) (center.y - side / 2));
        point2 = new Point((int) (center.x + side / 2), (int) (center.y + side / 2));
        this.naklonen = false;
        makePoint3();
        makePoint4();
    }

    public static Ctverec2 GUIpopupask() {
        JTextField x1Field = new JTextField(5);
        JTextField y1Field = new JTextField(5);
        JTextField x2Field = new JTextField(5);
        JTextField y2Field = new JTextField(5);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(new JLabel("x1:"));
        panel.add(x1Field);
        panel.add(new JLabel("y1:"));
        panel.add(y1Field);
        panel.add(new JLabel("x2:"));
        panel.add(x2Field);
        panel.add(new JLabel("y2:"));
        panel.add(y2Field);

        int result = JOptionPane.showConfirmDialog(null, panel, "Enter coordinates of two points", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int x1 = Integer.parseInt(x1Field.getText());
            int y1 = Integer.parseInt(y1Field.getText());
            int x2 = Integer.parseInt(x2Field.getText());
            int y2 = Integer.parseInt(y2Field.getText());

            return new Ctverec2(new Point(x1, y1), new Point(x2, y2));
        }
        return null;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    private boolean naklonen() {
        double dx = point2.getX() - point1.getX();
        double dy = point2.getY() - point1.getY();
        return dx != dy;
    }

    public void makePoint3() {
        if (naklonen) {
            int dx = (int) (point2.getX() - point1.getX());
            int dy = (int) (point2.getY() - point1.getY());
            point3 = new Point((int) (point1.getX() - dy), (int) (point1.getY() + dx));
        } else {
            point3 = new Point((int) point1.getX(), (int) point2.getY());
        }
    }

    public void makePoint4() {
        if (naklonen) {
            int dx = (int) (point2.getX() - point1.getX());
            int dy = (int) (point2.getY() - point1.getY());
            point4 = new Point((int) (point2.getX() - dy), (int) (point2.getY() + dx));
        } else {
            point4 = new Point((int) point2.getX(), (int) point1.getY());
        }
    }

    public double getDiagonalLength() {
        return point1.distance(point2);
    }

    public double getSideA() {
        double diagonal = getDiagonalLength();
        return diagonal / Math.sqrt(2);
    }

    public double getPerimeter() {
        return 4 * getSideA();
    }

    public double getArea() {
        return getSideA() * getSideA();
    }

    @Override
    public String toString() {
        return String.format(
                "Square Details:\n" +
                        "Point 1: (%.2f, %.2f)\n" +
                        "Point 2: (%.2f, %.2f)\n" +
                        "Point 3: (%.2f, %.2f)\n" +
                        "Point 4: (%.2f, %.2f)\n" +
                        "Diagonal Length: %.2f\n" +
                        "Side Length: %.2f\n",
                point1.getX(), point1.getY(),
                point2.getX(), point2.getY(),
                point3.getX(), point3.getY(),
                point4.getX(), point4.getY(),
                getDiagonalLength(),
                getSideA()
        );
    }

    public static void main(String[] args) {
        Ctverec2 ctverec = GUIpopupask();
        if (ctverec != null) {
            System.out.println(ctverec);
            System.out.println("Perimeter of the square is " + ctverec.getPerimeter());
            System.out.println("Area of the square is " + ctverec.getArea());
        }
    }
}
