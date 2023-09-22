// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.lang.Math;
import java.util.Scanner;
import java.util.Arrays;
import java.util.*;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ArrayList<IShape> figures= new ArrayList<>();

        System.out.println("Number of Shapes: ");
        int numberOfShapes = in.nextInt();

        for (int i = 0; i < numberOfShapes; i++)
        {
            System.out.println((i+1) + " Shape type: ");
            String type = in.next().toLowerCase();
            figures.add(newShape(type));
        }

        for(IShape f : figures){
            System.out.println(f.toString());
        }

        double totalSquare = 0, totalLength = 0;

        for (IShape f : figures){
            totalLength+=f.length();
            totalSquare+=f.square();
        }
        System.out.println("Total square of shapes: " + totalSquare);
        System.out.println("Total length of shapes: " + totalLength);
        System.out.println("Average square of a shape: " + totalSquare/numberOfShapes);


        System.out.println("Input ugly twins for preceding shapes. ");
        for (int i = 0; i < numberOfShapes; i++)
        {
            IShape t = figures.get(i);
            System.out.println((i + 1) + " Shape type: " + t);
            IShape shape = null;
            if (t instanceof Segment) shape = newShape("segment");
            else if (t instanceof Polyline) shape = newShape("polyline");
            else if (t instanceof Circle) shape = newShape("circle");
            else if (t instanceof Trapeze) shape = newShape("trapeze");
            else if (t instanceof Rectangle) shape = newShape("rectangle");
            else if (t instanceof QGon) shape = newShape("qgon");
            else if (t instanceof TGon) shape = newShape("tgon");
            else if (t instanceof NGon) shape = newShape("ngon");


            System.out.println("Does the shape cross figure with the same index?");
            if (shape.cross(figures.get(i))) {
                System.out.println("YES");
            }
            else System.out.println("NO");
            System.out.println("Now its time to skrew these shapes. ");
            System.out.println("Movement type: ");
            String movementType = in.next().toLowerCase();
            try {
                switch (movementType) {
                    case "shift" -> {
                        double[] movementVector = new double[2];
                        System.out.println("Shift vector X coordinate: ");
                        movementVector[0] = in.nextDouble();
                        System.out.println("Shift vector Y coordinate: ");
                        movementVector[1] = in.nextDouble();
                        shape.shift(new Point2D(movementVector));
                    }
                    case "rot" -> {
                        System.out.println("Rotation angle (counterclockwise): ");
                        double angle = in.nextDouble();
                        shape.rot(angle);
                    }
                    case "symaxis" -> {
                        System.out.println("Axis index: ");
                        int axisOfSymmetryIndex = in.nextInt();
                        shape.symAxis(axisOfSymmetryIndex);
                    }
                    default -> throw new Exception("Such movement is not applicable");
                }
            }
            catch (Exception e){
                System.out.println("Movement not applicable");
            }
            System.out.println("Movement parameters: ");
            System.out.println(shape);
            System.out.println("Does the shifted shape cross figure with the same index?");
            if (shape.cross(figures.get(i))) System.out.println("YES");
            else System.out.println("NO");
        }

    }
    static Point2D[] createPointArray(int numberOfPoints)
    {
        try {
            Scanner in = new Scanner(System.in);
            Point2D[] p = new Point2D[numberOfPoints];
            for (int j = 0; j < numberOfPoints; j++) {
                double[] xy = new double[2];
                System.out.println((j + 1) + " point. Coordinate X:");
                xy[0] = in.nextDouble();
                System.out.println((j + 1) + " point. Coordinate Y:");
                xy[1] = in.nextDouble();
                p[j] = new Point2D(xy);
            }
            return p;
        }
        catch (Exception ioe) {
            System.out.println("Input Exception occured");
            return null;
        }

    }
    static IShape newShape(String type) {
        Scanner in = new Scanner(System.in);
        IShape shape=null;
        try {
            switch (type) {
                case "polyline" -> {
                    System.out.println("Number of points: ");
                    int numberOfPoints = in.nextInt();
                    shape = new Polyline(createPointArray(numberOfPoints));
                }
                case "ngon" -> {
                    System.out.println("Number of points: ");
                    int numberOfPoints = in.nextInt();
                    shape = new NGon(createPointArray(numberOfPoints));
                }
                case "qgon" -> shape = new QGon(createPointArray(4));
                case "tgon" -> shape = new TGon(createPointArray(3));
                case "trapeze" -> shape = new Trapeze(createPointArray(4));
                case "rectangle" -> shape = new Rectangle(createPointArray(4));
                case "segment" -> {
                    double[] start = new double[2];
                    System.out.println("Start X coordinate: ");
                    start[0] = in.nextDouble();
                    System.out.println("Start Y coordinate: ");
                    start[1] = in.nextDouble();
                    double[] finish = new double[2];
                    System.out.println("Finish X coordinate: ");
                    finish[0] = in.nextDouble();
                    System.out.println("Finish Y coordinate: ");
                    finish[1] = in.nextDouble();
                    shape = new Segment(new Point2D(start), new Point2D(finish));
                }
                case "circle" -> {
                    double[] center = new double[2];
                    System.out.println("Center X coordinate: ");
                    center[0] = in.nextDouble();
                    System.out.println("Center Y coordinate: ");
                    center[1] = in.nextDouble();
                    System.out.println("Radius: ");
                    double radius = in.nextDouble();
                    shape = new Circle(new Point2D(center), radius);
                }
                default -> throw new Exception("Inexistent shape type");
            }
        }
        catch (Exception e){
            System.out.println("Input Exception occured");
        }
        return shape;
    }
}