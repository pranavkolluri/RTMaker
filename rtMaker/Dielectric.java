package rtMaker;


public class Dielectric implements Materials {
    public double indexOfRefraction;

    Dielectric(double iR) {
        indexOfRefraction = iR;
    }

    public boolean scatter(ray rIn, objects_record rec, vec3 attenuation, ray scattered) {
        attenuation.replaceAll(new vec3(1.0, 1.0, 1.0));
        double refractionRatio;
        if (rec.getFront()) {
            refractionRatio = 1.0 / indexOfRefraction;
        } else {
            refractionRatio = indexOfRefraction;
        }

        vec3 unitDirection = vec3.rUnitVector(rIn.getDir());
        double cosTheta = Math.min(vec3.rDotVec(unitDirection.invertVec(), rec.getNormal()), 1.0);
        double sinTheta = Math.sqrt(1.0 - cosTheta * cosTheta);

        boolean cannotRefract = refractionRatio * sinTheta > 1.0;
        vec3 direction = new vec3();
        if (cannotRefract || reflectance(cosTheta, refractionRatio) > RT_Utils.random_double()) {
            direction = vec3.reflect(unitDirection, rec.getNormal());
        } else {
            direction = vec3.refract(unitDirection, rec.getNormal(), refractionRatio);
        }

        scattered.replaceAll(rec.getP(), direction);
        return true;
    }

    private static double reflectance(double cosine, double refIndex) {
        double rZero = (1 - refIndex) / (1 + refIndex);
        rZero = rZero * rZero;
        return rZero + (1 - rZero) * Math.pow((1 - cosine), 5);
    }
}
