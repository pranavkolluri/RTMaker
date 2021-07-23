package rtMaker;

import java.util.ArrayList;

public class ObjectList implements objects{
    public ArrayList<objects> oList;

    public ObjectList() {
        oList = new ArrayList<objects>();
    }

    public ObjectList(objects o) {
        oList = new ArrayList<objects>();
        oList.add(o);
    }

    public void clearObjects() {
        oList.clear();
    }

    public void addObjects(objects o) {
        oList.add(o);
    }

    public static void addObjects(ObjectList l, objects o) {
        l.oList.add(o);
    }

    public static void clearObjects(ObjectList l) {
        l.oList.clear();
    }

    public boolean hit(ray r, double t_min, double t_max, objects_record rec) {
        objects_record temp_rec = new objects_record();
        boolean hit_anything = false;
        double closest_so_far = t_max;

        for(objects o : oList) {
            if (o.hit(r, t_min, closest_so_far, temp_rec)) {
                hit_anything = true;
                closest_so_far = temp_rec.getT();
                rec.setNormal(temp_rec.getNormal());
                rec.setT(temp_rec.getT());
                rec.setP(temp_rec.getP());
                rec.setF(temp_rec.getFront());
                rec.setMatProperty(temp_rec.getMatProperty());
            }
        }
        return hit_anything;
    }
}
