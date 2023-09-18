public class TGon extends NGon {
    public TGon(Point2D[] p)  {
        super(p);
    }
    public double square()
    {
        double ab = Point2D.sub(p[1], p[0]).abs(),
                bc = Point2D.sub(p[2], p[1]).abs(),
                ca = Point2D.sub(p[0], p[2]).abs(),
                pp = (ab + bc + ca)/2;
        return Math.sqrt(pp * (pp - ab)*(pp - bc)*(pp - ca));
    }
}
