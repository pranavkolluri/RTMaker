package rtMaker;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class vec3 {
    public Double[] e;

    public vec3() {
        e = new Double[] {0.0, 0.0, 0.0};
    }

    public vec3(double e1, double e2, double e3) {
        e = new Double[] {0.0, 0.0, 0.0};
        e[0] = e1;
        e[1] = e2;
        e[2] = e3;
    }

    public double x() {
        return this.e[0];
    }

    public double y() {
        return this.e[1];
    }

    public double z() {
        return this.e[2];
    }

    public static double x(vec3 v) {
        return v.e[0];
    }

    public static double y(vec3 v) {
        return v.e[1];
    }

    public static double z(vec3 v) {
        return v.e[3];
    }

    public vec3 addVec(vec3 v2) {
        this.e[0] += v2.e[0];
        this.e[1] += v2.e[1];
        this.e[2] += v2.e[2];
        return this;
    }

    public vec3 invertVec() {
        return new vec3(-this.e[0], -this.e[1], -this.e[2]);
    }

    public double getPos(int index) {
        return this.e[index];
    }

    public vec3 mulVec(double t) {
        this.e[0] *= t;
        this.e[1] *= t;
        this.e[2] *= t;
        return this;
    }

    public vec3 divVec(double t) {
        return mulVec(1/t);
    }

    public double lengthSquared() {
        return (this.e[0] * this.e[0]) + (this.e[1] * this.e[1]) + (this.e[2] * this.e[2]);
    }

    public double length() {
        return sqrt(lengthSquared());
    }

    public boolean nearZero() {
        double s = 1e-8;
        return (abs(e[0]) < s) && (abs(e[1]) < s) && (abs(e[2]) < s);
    }

    @Override
    public String toString() {
        return this.e[0] + " " + this.e[1] + " " + this.e[2];
    }

    public void vecToFile(StringBuilder s) {
        s.append(this.e[0] + " " + this.e[1] + " " + this.e[2] + "\n");
    }

    public static vec3 rAddTwoVec(vec3 v, vec3 u) {
        return new vec3(v.e[0] + u.e[0], v.e[1] + u.e[1], v.e[2] + u.e[2]);
    }

    public static vec3 rSubTwoVec(vec3 v, vec3 u) {
        return new vec3(v.e[0] - u.e[0], v.e[1] - u.e[1], v.e[2] - u.e[2]);
    }

    public static vec3 rMultTwoVec(vec3 v, vec3 u) {
        return new vec3(v.e[0] * u.e[0], v.e[1] * u.e[1], v.e[2] * u.e[2]);
    }

    public static vec3 rMultOneVec(vec3 v, double t) {
        return new vec3(v.e[0] * t, v.e[1] * t, v.e[2] * t);
    }

    public static vec3 rDivOneVec(vec3 v, double t) {
        return rMultOneVec(v, 1/t);
    }

    public static vec3 rCrossVec(vec3 v, vec3 u) {
        return new vec3(u.e[1] * v.e[2] - u.e[2] * v.e[1],
                u.e[2] * v.e[0] - u.e[0] * v.e[2],
                u.e[0] * v.e[1] - u.e[1] * v.e[0]);
    }

    public static double rDotVec(vec3 v, vec3 u) {
        return u.e[0] * v.e[0]
                + u.e[1] * v.e[1]
                + u.e[2] * v.e[2];
    }

    public static vec3 rUnitVector(vec3 v) {
        return rDivOneVec(v, v.length());
    }

    public static vec3 reflect(vec3 v, vec3 n) {
        return vec3.rSubTwoVec(v, vec3.rMultOneVec(n, 2 * rDotVec(v, n)));
    }

    public static vec3 refract(vec3 uv, vec3 n, double refractiveIndex) {
        double cosTheta = Math.min(vec3.rDotVec(uv.invertVec(), n), 1.0);
        vec3 rPerp = vec3.rMultOneVec(vec3.rAddTwoVec(uv,
                vec3.rMultOneVec(n, cosTheta)), refractiveIndex);
        vec3 rParallel = vec3.rMultOneVec(n, -1.0 * sqrt(abs(1.0 - rPerp.lengthSquared())));
        return vec3.rAddTwoVec(rPerp, rParallel);
    }

    public static void write_color(StringBuilder s, vec3 pixel_color) {
        s.append((int) (255.999 * pixel_color.x()) + " "
                + (int) (255.999 * pixel_color.y()) + " "
                + (int) (255.999 * pixel_color.z()) + "\n");
    }

    public static vec3 random() {
        return new vec3(RT_Utils.random_double(), RT_Utils.random_double(),
                RT_Utils.random_double());
    }

    public static vec3 random(double min, double max) {
        return new vec3(RT_Utils.random_double(min, max), RT_Utils.random_double(min, max),
                RT_Utils.random_double(min, max));
    }

    public void replaceAll(vec3 v) {
        this.e[0] = v.e[0];
        this.e[1] = v.e[1];
        this.e[2] = v.e[2];
    }

    public static vec3 random_in_unit_sphere() {
        while (true) {
            vec3 p = random(-1, 1);
            if (p.lengthSquared() >= 1) {
                continue;
            } else {
                return p;
            }
        }
    }

    public static vec3 random_unit_vector() {
        return rUnitVector(random_in_unit_sphere());
    }


}
