package rtMaker;

import java.io.File;

import static rtMaker.Utils.join;
import static rtMaker.Utils.writeContents;

public class testImageObjects {

    public static vec3 ray_color(ray r, objects world) {
        objects_record rec = new objects_record();
        if(world.hit(r, 0, Double.POSITIVE_INFINITY, rec)) {
            return vec3.rMultOneVec(vec3.rAddTwoVec(rec.getNormal(),
                    new vec3(1.0, 1.0, 1.0)), 0.5);
        }
        vec3 unit_direction = vec3.rUnitVector(r.getDir());
        double t = 0.5 * (unit_direction.y() + 1.0);
        return vec3.rAddTwoVec(vec3.rMultOneVec(new vec3(1.0, 1.0, 1.0),1.0 - t),
                vec3.rMultOneVec(new vec3(0.5, 0.7, 1.0), t));
    }

    private static double aspect_ratio = 16.0/9.0;
    private static int im_width = 400;
    private static int im_height = (int) ((double) im_width / aspect_ratio);
    public static final File CWD = new File(System.getProperty("user.dir"));

    public static ObjectList world;

    private static double viewport_height = 2.0;
    private static double viewport_width = aspect_ratio * viewport_height;
    private static double focal_length = 1.0;

    private static vec3 origin = new vec3(0.0, 0.0, 0.0);
    private static vec3 horizontal = new vec3(viewport_width, 0.0, 0.0);
    private static vec3 vertical = new vec3(0.0, viewport_height, 0.0);
    private static vec3 lowerLeftCorner = vec3.rSubTwoVec(vec3.rSubTwoVec(vec3.rSubTwoVec(origin,
            vec3.rDivOneVec(horizontal, 2.0)), vec3.rDivOneVec(vertical, 2.0)),
            new vec3(0.0, 0.0, focal_length));

    public static void world_initializer() {
        Materials groundMat = new Lambertian(new vec3(0.8, 0.8, 0.0));
        Materials centerMat = new Lambertian(new vec3(0.7, 0.3, 0.3));
        world = new ObjectList();
        world.addObjects(new Sphere(new vec3(0.0, 0.0, -1.0), 0.5, centerMat));
        world.addObjects(new Sphere(new vec3(0.0, -100.5, -1.0), 100.0, groundMat));
        //world.addObjects(new Sphere(new vec3(0.5, 0.5, -1.0), 0.5));
    }

    public static void makeImage() {
        world_initializer();
        StringBuilder s = new StringBuilder();
        s.append("P3\n" + im_width + " " + im_height + "\n" + "255" + "\n");
        for (int j = im_height - 1; j >= 0; j--) {
            System.out.println("\nScanlines remaining: " + j);
            for (int i = 0; i < im_width; i++) {
                double u = (double) i / (im_width - 1);
                double v = (double) j / (im_height - 1);
                ray r = new ray(origin, vec3.rAddTwoVec(lowerLeftCorner, vec3.rSubTwoVec(
                        vec3.rAddTwoVec(vec3.rMultOneVec(horizontal, u),
                                vec3.rMultOneVec(vertical, v)), origin)));
                vec3 pixel_color = ray_color(r, world);
                vec3.write_color(s, pixel_color);
            }
        }
        String fileString = s.toString();
        File outFile = join(CWD, "picture6.ppm");
        writeContents(outFile, fileString);
        System.out.println("\nDone! \n");
    }
}
