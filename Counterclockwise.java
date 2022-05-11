package geometry;

import java.util.Comparator;
import java.util.List;

public class Counterclockwise implements Comparator<Point> {
    Point center;
    public Counterclockwise(Point center){
        this.center = center;
    }
    public int compare(Point a,Point b){
        double a1 = (Math.toDegrees(Math.atan2(a.getX() - center.getX(), a.getY() - center.getY())) + 360) % 360;
        double a2 = (Math.toDegrees(Math.atan2(b.getX() - center.getX(), b.getY() - center.getY())) + 360) % 360;

        return (int) (a2 - a1);
    }




}
