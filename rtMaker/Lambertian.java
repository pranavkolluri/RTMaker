package rtMaker;

public class Lambertian implements Materials{
    public vec3 albedo;
    Lambertian(vec3 a) {
        albedo = a;
    }
    public boolean scatter(ray rIn, objects_record rec, vec3 attenuation, ray scattered) {
        vec3 scatterDirection = vec3.rAddTwoVec(rec.getNormal(), vec3.random_unit_vector());
        if(scatterDirection.nearZero()) {
            scatterDirection = rec.getNormal();
        }
        scattered.replaceAll(rec.getP(), scatterDirection);
        attenuation.replaceAll(albedo);
        return true;
    }

}
