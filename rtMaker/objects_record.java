package rtMaker;

public class objects_record {
    private vec3 p;
    private vec3 normal;
    private Materials matProperty;
    private double t;
    private boolean front_face;

    public void setP(vec3 newP) {
        p = newP;
    }

    public void setNormal(vec3 newNormal) {
        normal = newNormal;
    }

    public void setT(double newT) {
        t = newT;
    }

    public void setF(boolean newF) {
        front_face = newF;
    }

    public void setMatProperty(Materials m) {
        matProperty = m;
    }

    public vec3 getP() {
        return p;
    }

    public vec3 getNormal() {
        return normal;
    }

    public double getT() {
        return t;
    }

    public boolean getFront() {
        return front_face;
    }

    public Materials getMatProperty() {
        return matProperty;
    }

    public void set_face_normal(ray r, vec3 outward_normal) {
        front_face = vec3.rDotVec(r.getDir(), outward_normal) < 0;
        if (front_face) {
            normal = outward_normal;
        } else {
            normal = outward_normal.invertVec();
        }
    }
}



