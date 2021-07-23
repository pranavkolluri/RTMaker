package rtMaker;

public class ray {
    public vec3 orig;
    public vec3 dir;

    public ray() {
    }

    public ray(vec3 origin, vec3 direction) {
        orig = origin;
        dir = direction;
    }

    public vec3 getOrig() {
        return orig;
    }

    public vec3 getDir() {
        return dir;
    }

    public vec3 at(double t) {
        return vec3.rAddTwoVec(orig, vec3.rMultOneVec(dir, t));
    }

    public void replaceAll(vec3 o, vec3 d) {
        this.orig = o;
        this.dir = d;
    }
}
