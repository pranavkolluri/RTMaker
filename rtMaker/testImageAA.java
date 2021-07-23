package rtMaker;

import java.io.File;

import static rtMaker.Utils.join;
import static rtMaker.Utils.writeContents;
/*
public class testImageAA {

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
    private static int samples_per_pix = 100;
    public static final File CWD = new File(System.getProperty("user.dir"));

    public static ObjectList world = new ObjectList();
    public static void world_initializer() {
        //world.addObjects(new Sphere(new vec3(0.0, 0.0, -1.0), 0.5));
        //world.addObjects(new Sphere(new vec3(0.0, -100.5, -1.0), 100.0));
    }

    public static Camera cam = new Camera(50, aspect_ratio);

    private static double viewport_height = 2.0;
    private static double viewport_width = aspect_ratio * viewport_height;
    private static double focal_length = 1.0;

    private static vec3 origin = new vec3(0.0, 0.0, 0.0);
    private static vec3 horizontal = new vec3(viewport_width, 0.0, 0.0);
    private static vec3 vertical = new vec3(0.0, viewport_height, 0.0);
    private static vec3 lowerLeftCorner = vec3.rSubTwoVec(vec3.rSubTwoVec(vec3.rSubTwoVec(origin,
            vec3.rDivOneVec(horizontal, 2.0)), vec3.rDivOneVec(vertical, 2.0)),
            new vec3(0.0, 0.0, focal_length));


    public static void makeImage() {
        world_initializer();
        StringBuilder s = new StringBuilder();
        s.append("P3\n" + im_width + " " + im_height + "\n" + "255" + "\n");
        for (int j = im_height - 1; j >= 0; j--) {
            System.out.println("\nScanlines remaining: " + j);
            for (int i = 0; i < im_width; i++) {
                vec3 color_pixel = new vec3(0.0, 0.0, 0.0);
                for (int n = 0; n < samples_per_pix; n++) {
                    double u = (i + RT_Utils.random_double()) / (im_width - 1);
                    double v = (j + RT_Utils.random_double()) / (im_height - 1);
                    ray r = cam.getRay(u, v);
                    color_pixel = vec3.rAddTwoVec(color_pixel, ray_color(r, world));
                }
                RT_Utils.write_color(s, color_pixel, samples_per_pix);
            }
        }
        String fileString = s.toString();
        File outFile = join(CWD, "picture7.ppm");
        writeContents(outFile, fileString);
        System.out.println("\nDone! \n");
    }
}

 */
