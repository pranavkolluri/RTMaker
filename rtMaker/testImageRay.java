package rtMaker;

import java.io.File;

import static rtMaker.Utils.join;
import static rtMaker.Utils.writeContents;

public class testImageRay {
    public static vec3 ray_color(ray r) {
        vec3 unit_direction = vec3.rUnitVector(r.dir);
        double t = 0.5 * (unit_direction.y() + 1.0);
        return vec3.rAddTwoVec(vec3.rMultOneVec(new vec3(1.0, 1.0, 1.0),1.0 - t),
                vec3.rMultOneVec(new vec3(0.5, 0.7, 1.0), t));
    }

    private static double aspect_ratio = 16.0/9.0;
    private static int im_width = 400;
    private static int im_height = (int) ((double) im_width / aspect_ratio);
    public static final File CWD = new File(System.getProperty("user.dir"));

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
                vec3 pixel_color = ray_color(r);
                vec3.write_color(s, pixel_color);
            }
        }
        String fileString = s.toString();
        File outFile = join(CWD, "picture3.ppm");
        writeContents(outFile, fileString);
        System.out.println("\n Done! \n");
    }
}
