package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The class implementing equilateral triangles, i.e., triangles in which all three sides have the same length.
 * Note: you can add more methods if you want, but additional methods must be <code>private</code> or <code>protected</code>.
 *
 * @author Jake Papaspiridakos
 */
public class EqTriangle implements Shape {
    private ArrayList<Point> vertices;
    /**
     * The constructor accepts an array of <code>Point</code>s to form the vertices of the equilateral triangle. If more
     * than three points are provided, only the first three are considered by this constructor. If less than three
     * points are provided, or if the points do not form a valid equilateral triangle, the constructor throws
     * <code>java.lang.IllegalArgumentException</code>.
     *
     * @param vertices the array of vertices (i.e., <code>Point</code> instances) provided to the constructor.
     */
    public EqTriangle(Point... vertices) {
        if(vertices.length < 3){
            throw new IllegalArgumentException();
        }
        Point p1 = vertices[0];
        Point p2 = vertices[1];
        Point p3 = vertices[2];
        this.vertices = new ArrayList<Point>(3);
        this.vertices().add(p1);
        this.vertices().add(p2);
        this.vertices().add(p3);

        if (!(isMember(this.vertices()))){
            throw new IllegalArgumentException();
        }



    }


    /**
     * Checks if the series of <code>Point</code> instances form a valid equilateral triangle if first three form the
     * vertices of the triangle.
     *
     * @param vertices the list of specified vertices.
     * @return <code>true</code> if the first three vertices in the argument form a valid equilateral triangle, and
     * <code>false</code> otherwise.
     */
    @Override
    public boolean isMember(List<Point> vertices) {
        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Point p3 = vertices.get(2);

        if ((Math.abs(getDistance(p1,p2) - getDistance(p1,p3) ) < 0.001) &&
                (Math.abs(getDistance(p1,p2) - getDistance(p2,p3)) < 0.001)){

            return true;

        } else
            return false;

    }

    private double getDistance(Point p1, Point p2){
        double x1 = p1.getX();
        double y1 = p1.getY();

        double x2 = p2.getX();
        double y2 = p2.getY();

        double xDif = x2 - x1;
        double yDif = y2 - y1;
        xDif *= xDif;
        yDif *= yDif;

        double togetherDif = xDif + yDif;

        return ((float) Math.sqrt(togetherDif));


    }
    
    @Override
    public int numberOfSides() {
        return 3;
    }

    @Override
    public List<Point> vertices() {
        return this.vertices;
    }

    @Override
    public EqTriangle rotateBy(int degrees) {
        double radians = degrees * (Math.PI / 180);
        Point p1 = this.vertices().get(0);
        Point p2 = this.vertices().get(1);
        Point p3 = this.vertices().get(2);
        Point[] points = {p1,p2,p3};
        Point[] rotatedPoints = new Point[3];
        Point center = getCenter(this.vertices);
        double centerX = center.getX();
        double centerY = center.getY();
        for(int i=0;i<3;i++){
            Point currPoint = points[i];
            double x = currPoint.getX();
            double y = currPoint.getY();
            x -= centerX;
            y -= centerY;
            double newX = x * Math.cos(radians);
            newX -= y * Math.sin(radians);
            double newY = x * Math.sin(radians);
            newY += y * Math.cos(radians);
            newX += centerX;
            newY += centerY;
            Point newPoint = new Point(newX,newY);
            rotatedPoints[i] =  newPoint;
        }
        return new EqTriangle(rotatedPoints);
    }

    public Point getCenter(List<Point> vertices){
        double xSum = 0;
        double ySum = 0;
        for(int i=0;i<vertices.size();i++){
            xSum += vertices.get(i).getX();
            ySum += vertices.get(i).getY();
        }
        double xMid = xSum / this.numberOfSides();
        double yMid = ySum / this.numberOfSides();
        return new Point(xMid,yMid);


    }

    public boolean equals(EqTriangle triangle){
        for(int i=0;i<3;i++){
            Point p1 = this.vertices.get(0);
            Point p2 = triangle.vertices.get(0);
            double x1 = p1.getX();
            double y1 = p1.getY();
            double x2 = p2.getX();
            double y2 = p2.getY();
            if((x1 != x2) || (y1 != y2))
                return false;
        }
        return true;
    }


    public String toString(){
        String result = "";
        for(int i=0;i<3;i++){
            result += "(" + (Math.round(this.vertices().get(i).getX() * 1000.0) / 1000.0) + ", " +
                    (Math.round(this.vertices().get(i).getY() * 1000.0) / 1000.0) + "), ";
        }
        return result;
    }

}
