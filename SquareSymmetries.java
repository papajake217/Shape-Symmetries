package geometry;

import java.util.ArrayList;
import java.util.Collection;

public class SquareSymmetries implements Symmetries<Square> {
    @Override
    public boolean areSymmetric(Square s1, Square s2) {
        Collection<Square> s1Symm = symmetriesOf(s1);
        for(Square S : s1Symm){
            if (S.equals(s2))
                return true;
        }
        return false;
    }

    @Override
    public Collection<Square> symmetriesOf(Square square) {
        ArrayList<Square> symmetries = new ArrayList<Square>(8);
        symmetries.add(square);
        symmetries.add(square.rotateBy(90));
        symmetries.add(square.rotateBy(180));
        symmetries.add(square.rotateBy(270));
        Point p1 = square.vertices().get(0);
        Point p2 = square.vertices().get(1);
        Point p3 = square.vertices().get(2);
        Point p4 = square.vertices().get(3);
        symmetries.add(new Square(p4,p3,p2,p1));
        symmetries.add(new Square(p2,p1,p3,p4));
        symmetries.add(new Square(p1,p4,p3,p2));
        symmetries.add(new Square(p3,p2,p1,p4));
        return symmetries;
    }
}
