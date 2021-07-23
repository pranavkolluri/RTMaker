package rtMaker;

public class Camera {
    private vec3 origin;
    private vec3 lowerLeftCorner;
    private vec3 horizontal;
    private vec3 vertical;
    private vec3 vUp;

    public Camera(double vfov, double aspectRatio, vec3 lookFrom, vec3 lookAt, vec3 up) {
        //double aspect_ratio = 16.0 / 9.0;
        //double viewport_height = 2.0;
        //double viewport_width = aspect_ratio * viewport_height;
        double theta = RT_Utils.degree_to_rad(vfov);
        double h = Math.tan(theta / 2.0);
        double viewport_height = 2.0 * h;
        double viewport_width = aspectRatio * viewport_height;

        vec3 w = vec3.rUnitVector(vec3.rSubTwoVec(lookFrom, lookAt));
        vec3 u = vec3.rUnitVector(vec3.rCrossVec(vUp, w));
        vec3 v = vec3.rCrossVec(w, u);

        origin = lookFrom;
        horizontal = vec3.rMultOneVec(u, viewport_width);
        vertical = vec3.rMultOneVec(v, viewport_height);
        lowerLeftCorner = vec3.rSubTwoVec(origin,
                vec3.rSubTwoVec(vec3.rDivOneVec(horizontal, 2.0),
                vec3.rSubTwoVec(vec3.rDivOneVec(vertical, 2.0), w)));
        vUp = up;

        //double focal_length = 1.0;

        //origin = new vec3(0.0, 0.0, 0.0);
        //horizontal = new vec3(viewport_width, 0.0, 0.0);
        //vertical = new vec3(0.0, viewport_height, 0.0);
        //lowerLeftCorner = vec3.rSubTwoVec(vec3.rSubTwoVec(vec3.rSubTwoVec(origin,
                //vec3.rDivOneVec(horizontal, 2.0)), vec3.rDivOneVec(vertical, 2.0)),
                //new vec3(0.0, 0.0, focal_length));
    }


    public Camera(double vfov, double aspectRatio) {
        //double aspect_ratio = 16.0 / 9.0;
        //double viewport_height = 2.0;
        //double viewport_width = aspect_ratio * viewport_height;
        double theta = RT_Utils.degree_to_rad(vfov);
        double h = Math.tan(theta / 2.0);
        double viewport_height = 2.0 * h;
        double viewport_width = aspectRatio * viewport_height;

        double focal_length = 1.0;

        origin = new vec3(0.0, 0.0, 0.0);
        horizontal = new vec3(viewport_width, 0.0, 0.0);
        vertical = new vec3(0.0, viewport_height, 0.0);
        lowerLeftCorner = vec3.rSubTwoVec(vec3.rSubTwoVec(vec3.rSubTwoVec(origin,
            vec3.rDivOneVec(horizontal, 2.0)), vec3.rDivOneVec(vertical, 2.0)),
                new vec3(0.0, 0.0, focal_length));
    }

    public ray getRay(double s, double t) {
        return new ray(origin, vec3.rSubTwoVec(vec3.rAddTwoVec(lowerLeftCorner,
                vec3.rAddTwoVec(vec3.rMultOneVec(horizontal, s),
                        vec3.rMultOneVec(vertical, t))), origin));
        //return new ray(origin, vec3.rSubTwoVec(vec3.rAddTwoVec(lowerLeftCorner, vec3.rAddTwoVec(vec3.rMultOneVec(horizontal, u), vec3.rMultOneVec(vertical, v))), origin));
    }
}
