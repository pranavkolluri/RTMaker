package rtMaker;
import java.io.File;
import static rtMaker.Utils.*;
public class testImage {
    private static int im_height = 256;
    private static int im_width = 256;
    public static final File CWD = new File(System.getProperty("user.dir"));


    public static void makeTestImage() {
        StringBuilder s = new StringBuilder();
        s.append("P3\n" + im_height + " " + im_width + "\n" + "255n" + "\n");
        for (int j = im_height - 1; j >= 0; j--) {
            System.out.println("\nScanlines remaining: " + j);
            for (int i = 0; i < im_width; i++) {
                double r = (double) i / (im_width - 1);
                double g = (double) j / (im_height - 1);
                double b = 0.25;

                int ir = (int) (255.999 * r);
                int ig = (int) (255.999 * g);
                int ib = (int) (255.999 * b);

                s.append(ir + " " + ig + " " + ib + "\n");
            }
        }
        String fileString = s.toString();
        File outFile = join(CWD, "picture1.ppm");
        writeContents(outFile, fileString);
    }


}
