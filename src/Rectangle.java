public class Rectangle extends QGon{
    public Rectangle(Point2D[] p) {
        super(p);
    }
    public double square() {
        return Point2D.sub(p[1], p[0]).abs() * Point2D.sub(p[2], p[1]).abs();
    }
}
