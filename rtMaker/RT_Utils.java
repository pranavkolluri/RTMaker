package rtMaker;

import java.io.StringWriter;
import java.util.Random;

import static java.lang.Math.sqrt;

public class RT_Utils {

    public static double clamp(double x, double min, double max) {
        if (x < min) {
            return min;
        } else if (x > max) {
            return max;
        } else {
            return x;
        }
    }

    public static void write_color(StringBuilder s, vec3 pixel_color, int samples_per_pix) {
        double r = pixel_color.x();
        double g = pixel_color.y();
        double b = pixel_color.z();

        double scale = 1.0 / (double) samples_per_pix;
        r = sqrt((scale * r));
        g = sqrt((scale * g));
        b = sqrt((scale * b));

        s.append((int) (256 * clamp(r, 0.0, 0.999)) + " "
                + (int) (256 * clamp(g, 0.0, 0.999)) + " "
                + (int) (256 * clamp(b, 0.0, 0.999)) + "\n");
    }

    public static double random_double() {
        Random r = new Random();
        return r.nextDouble();
    }

    public static double degree_to_rad(double degrees) {
        return degrees * Math.PI / 180.0;
    }

    public static double random_double(double min, double max) {
        return min + (max - min) * (random_double());
    }

    public static vec3 ray_color(ray r, objects world, int depth) {
        objects_record rec = new objects_record();

        if (depth <= 0) {
            return new vec3(0.0, 0.0, 0.0);
        }

        if (world.hit(r, 0.001, Double.POSITIVE_INFINITY, rec)) {
            ray scattered = new ray();
            vec3 attenuation = new vec3();
            if (rec.getMatProperty().scatter(r, rec, attenuation, scattered)) {
                return vec3.rMultTwoVec(attenuation, ray_color(scattered, world, depth - 1));
            }

            vec3 target = vec3.rAddTwoVec(vec3.rAddTwoVec(rec.getP(), rec.getNormal()), vec3.random_unit_vector());
            return vec3.rMultOneVec(ray_color(new ray(rec.getP(),
                    vec3.rSubTwoVec(target, rec.getP())), world, depth - 1), 0.5);
        }
        vec3 unit_direction = vec3.rUnitVector(r.getDir());
        double t = 0.5 * (unit_direction.y() + 1.0);
        return vec3.rAddTwoVec(vec3.rMultOneVec(new vec3(1.0, 1.0, 1.0),1.0 - t),
                vec3.rMultOneVec(new vec3(0.5, 0.7, 1.0), t));
    }
}
