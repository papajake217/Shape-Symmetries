package geometry;

import java.util.ArrayList;
import java.util.Collection;

public class TriangleSymmetries implements Symmetries<EqTriangle>{

    @Override
    public boolean areSymmetric(EqTriangle s1, EqTriangle s2) {
        Collection<EqTriangle> s1Symm = symmetriesOf(s1);
        for(EqTriangle t : s1Symm){
            if (t.equals(s2))
                    return true;
        }
        return false;
    }

    @Override
    public Collection<EqTriangle> symmetriesOf(EqTriangle eqTriangle) {
        ArrayList<EqTriangle> triangleList = new ArrayList<EqTriangle>(6);
        triangleList.add(eqTriangle);
        triangleList.add(eqTriangle.rotateBy(120));
        triangleList.add(eqTriangle.rotateBy(240));
        Point p1 = eqTriangle.vertices().get(0);
        Point p2 = eqTriangle.vertices().get(1);
        Point p3 = eqTriangle.vertices().get(2);
        triangleList.add(new EqTriangle(p3,p2,p1));
        triangleList.add(new EqTriangle(p1,p3,p2));
        triangleList.add(new EqTriangle(p2,p1,p3));

        return triangleList;

    }
    //itself, rotated by 120 240 and reflection on 3 altitudes
}
