public class Segment extends OpenFigure {
    private Point2D start;
    private Point2D finish;

    public Segment(Point2D s, Point2D f)
    {
        start = s;
        finish = f;
    }
    public Point2D getStart() {
        return start;
    }
    public Point2D setStart(Point2D a) {
        return start = a;
    }
    public Point2D getFinish() {
        return finish;
    }
    public void setFinish(Point2D a) {
        finish = a;
    }
    @Override public  double length() {
        return Point2D.sub(finish, start).abs();
    }
    @Override public  Segment shift(Point2D a) {
        start.add(a); finish.add(a); return this;
    }
    @Override public  Segment rot(double phi) {
        start.rot(phi); finish.rot(phi); return this;
    }
    @Override public Segment symAxis(int i) {
        start.symAxis(i); finish.symAxis(i); return this;
    }

    @Override public  boolean cross(IShape i) {
        if (i instanceof Segment) {
            return counterclockwise(this.start, ((Segment)i).start, ((Segment)i).finish)!= counterclockwise(this.finish, ((Segment)i).start, ((Segment)i).finish) &&counterclockwise(this.start, this.finish, ((Segment)i).start) !=counterclockwise(this.start, this.finish, ((Segment)i).finish);
        }
        else if (i instanceof Circle) {
            return (Point2D.sub(start, ((Circle)i).getP())).abs() < ((Circle)i).getR() || (Point2D.sub(finish, ((Circle)i).getP())).abs() < ((Circle)i).getR();
        }
        else
        {
            Point2D[] pts = null;
            try {
                if (i instanceof Polyline) pts = ((Polyline)i).getP();
                else if (i instanceof Rectangle) pts = ((Rectangle)i).getP();
                else if (i instanceof Trapeze) pts = ((Trapeze)i).getP();
                else if (i instanceof TGon) pts = ((TGon)i).getP();
                else if (i instanceof QGon) pts = ((QGon)i).getP();
                else if (i instanceof NGon) pts = ((NGon)i).getP();
                if (pts == null) {
                    throw new Exception("No points have been passed down");
                }
            }
            catch (Exception e){
                System.out.println("No points have been passed down");
            }
            Point2D prev = pts[0];
            for(Point2D pt : pts)
            {
                if (new Segment(prev, pt).cross(this)) return true;
                prev = pt;
            }
            return (i instanceof NGon) && new Segment(pts[0], pts[pts.length - 1]).cross(this);
        }
    }

    public boolean counterclockwise(Point2D a, Point2D b, Point2D c){
        return (c.getX()[1] - a.getX()[1]) * (b.getX()[0] - a.getX()[0]) > (b.getX()[1] - a.getX()[1]) * (c.getX()[0] - a.getX()[0]);
    }
    @Override public  String toString() {
        return  "start: " + start.toString() + ", finish:" + finish.toString();
    }
}
