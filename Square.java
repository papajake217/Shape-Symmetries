package geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The class impementing squares.
 * Note: you can add more methods if you want, but additional methods must be <code>private</code> or <code>protected</code>.
 *
 * @author Jake Papaspiridakos
 */
public class Square implements Shape {
    private ArrayList<Point> vertices;
    /**
     * The constructor accepts an array of <code>Point</code>s to form the vertices of the square. If more than four
     * points are provided, only the first four are considered by this constructor. If less than four points are
     * provided, or if the points do not form a valid square, the constructor throws <code>java.lang.IllegalArgumentException</code>.
     *
     * @param vertices the array of vertices (i.e., <code>Point</code> instances) provided to the constructor.
     */
    public Square(Point... vertices) {
        Point p1 = vertices[0];
        Point p2 = vertices[1];
        Point p3 = vertices[2];
        Point p4 = vertices[3];
        this.vertices = new ArrayList<Point>(4);
        this.vertices.add(p1);
        this.vertices.add(p2);
        this.vertices.add(p3);
        this.vertices.add(p4);
        Point center = calculateCenter(this.vertices);
        

        Collections.sort(this.vertices,new Counterclockwise(center));
        Point swapP1 = this.vertices.get(0);
        this.vertices.remove(0);
        this.vertices.add(3,swapP1);


        if(!(isMember(this.vertices))){
            throw new IllegalArgumentException();
        }


    }
    
    /**
     * Checks if the series of <code>Point</code> instances form a valid square if the first four form the vertices of
     * the square. This method considers the points in a counterclockwise manner starting with the vertex with the least
     * x-value. If multiple vertices have the same x-value, the counterclockwise ordering starts at the vertex with the
     * least y-value amongst them.
     *
     * @param vertices the list of specified vertices.
     * @return <code>true</code> if the first four vertices in the argument form a valid square, and <code>false</code>
     * otherwise.
     */
    @Override
    public boolean isMember(List<Point> vertices) {
        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Point p3 = vertices.get(2);
        Point p4 = vertices.get(3);
        double e1 = getDistance(p1,p2);
        double e2 = getDistance(p2,p3);
        double e3 = getDistance(p3,p4);
        double e4 = getDistance(p4,p1);
        double comp1 = Math.abs(e1-e2);
        double comp2 = Math.abs(e1-e3);
        double comp3 = Math.abs(e1-e4);
        if ( (comp1 < 0.0001) && (comp2 < 0.0001) && (comp3 < 0.0001) )
            return true;
         else
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
        return 4;
    }
    
    @Override
    public List<Point> vertices() {
        return this.vertices;
    }
    
    @Override
    public Square rotateBy(int degrees) {
        double radians = degrees * (Math.PI / 180);
        Point p1 = this.vertices().get(0);
        Point p2 = this.vertices().get(1);
        Point p3 = this.vertices().get(2);
        Point p4 = this.vertices().get(3);
        Point[] points = {p1,p2,p3,p4};
        Point[] rotatedPoints = new Point[4];
        Point center = calculateCenter(this.vertices);
        double centerX = center.getX();
        double centerY = center.getY();
        for(int i=0;i<4;i++){
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
        Square rotatedSquare = new Square(rotatedPoints);

        return rotatedSquare;
    }
    
    @Override
    public String toString() {
        String finalStr = "Square: ";
        for(int i=0;i<4;i++){
            double x = this.vertices().get(i).getX();
            double y = this.vertices().get(i).getY();
            String xString = "";
            String yString = "";
            if(x % 1 < 0.0001){
                xString += Math.round(x);
            } else{
                xString += (Math.round(this.vertices().get(i).getX() * 1000.0) / 1000.0);
            }

            if(y % 1 < 0.0001){
                yString += Math.round(y);
            } else{
                yString += (Math.round(this.vertices().get(i).getY() * 1000.0) / 1000.0);
            }
            finalStr += "(" + xString  + ", " +
                    yString + "),";
        }
        return finalStr;
    }

    public Point calculateCenter(List<Point> vertices){
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

    public boolean equals(Square s){
        for(int i=0;i<3;i++){
            Point p1 = this.vertices.get(0);
            Point p2 = s.vertices.get(0);
            double x1 = p1.getX();
            double y1 = p1.getY();
            double x2 = p2.getX();
            double y2 = p2.getY();
            if((x1 != x2) || (y1 != y2))
                return false;
        }
        return true;
    }

}
