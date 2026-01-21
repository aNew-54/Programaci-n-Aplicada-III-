package unc.edu.pe.catgalleryapp.Models;

public class Cat {
    private String imageUrl;
    private String name;
    private String origin;
    private String temperament;

    public Cat(String imageUrl, String name, String origin, String temperament) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.origin = origin;
        this.temperament = temperament;
    }

    public String getImageUrl() { return imageUrl; }
    public String getName() { return name; }
    public String getOrigin() { return origin; }
    public String getTemperament() { return temperament; }
}
