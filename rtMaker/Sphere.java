package rtMaker;

import static java.lang.Math.sqrt;

public class Sphere implements objects{
    private vec3 center;
    private double radius;
    private Materials matProperty;

    public Sphere(vec3 cen, double r, Materials m) {
        center = cen;
        radius = r;
        matProperty = m;
    }

    public vec3 getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public boolean hit(ray r, double t_min, double t_max, objects_record rec) {
        vec3 oc = vec3.rSubTwoVec(r.getOrig(), center);
        double a = r.getDir().lengthSquared();
        double half_b = vec3.rDotVec(oc, r.getDir());
        double c = oc.lengthSquared() - radius * radius;

        double discriminant = half_b * half_b - a * c;
        if (discriminant < 0) {
            return false;
        }

        double sqrtD = sqrt(discriminant);

        double root = (-half_b - sqrtD) / a;
        if (root < t_min || t_max < root) {
            root = (-half_b + sqrtD) / a;
            if (root < t_min || t_max < root) {
                return false;
            }
        }

            rec.setT(root);
            rec.setP(r.at(rec.getT()));
            rec.setNormal(vec3.rDivOneVec(vec3.rSubTwoVec(rec.getP(), center), radius));
            vec3 outward_normal = vec3.rDivOneVec(vec3.rSubTwoVec(rec.getP(), center), radius);
            rec.set_face_normal(r, outward_normal);
            rec.setMatProperty(matProperty);

            return true;
    }
}
