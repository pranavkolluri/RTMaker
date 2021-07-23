package rtMaker;

public interface Materials{
    boolean scatter(ray r, objects_record rec, vec3 attenuation, ray scattered);
}
