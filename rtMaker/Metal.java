package rtMaker;

public class Metal implements Materials {
    public vec3 albedo;
    public double fuzz;

    Metal(vec3 a, double f) {
        albedo = a;
        fuzz = f;
    }

    public boolean scatter(ray rIn, objects_record rec, vec3 attenuation, ray scattered) {
        vec3 reflected = vec3.reflect(vec3.rUnitVector(rIn.getDir()), rec.getNormal());
        scattered.replaceAll(rec.getP(), vec3.rAddTwoVec(reflected,
                vec3.rMultOneVec(vec3.random_in_unit_sphere(), fuzz)));
        attenuation.replaceAll(albedo);
        return vec3.rDotVec(scattered.getDir(), rec.getNormal()) > 0;
    }
}
