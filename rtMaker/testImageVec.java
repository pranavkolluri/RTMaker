package rtMaker;

import java.io.File;

import static rtMaker.Utils.join;
import static rtMaker.Utils.writeContents;

public class testImageVec {
    private static int im_height = 256;
    private static int im_width = 256;
    public static final File CWD = new File(System.getProperty("user.dir"));


    public static void makeImage() {
        StringBuilder s = new StringBuilder();
        s.append("P3\n" + im_height + " " + im_width + "\n" + "255" + "\n");
        for (int j = im_height - 1; j >= 0; j--) {
            System.out.println("\nScanlines remaining: " + j);
            for (int i = 0; i < im_width; i++) {
                vec3 pixel_color = new vec3((double) i / (im_width - 1),
                        (double) j / (im_height - 1),
                        0.25);
                vec3.write_color(s, pixel_color);
            }
        }
        String fileString = s.toString();
        File outFile = join(CWD, "picture2.ppm");
        writeContents(outFile, fileString);
        System.out.println("\n Done! \n");
    }
}
