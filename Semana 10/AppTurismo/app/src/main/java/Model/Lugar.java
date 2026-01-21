
package Model;

public class Lugar {
    private String Nombre;
    private String Ciudad;
    private String Descripcion;
    private double distancia;
    private double tiempo;
    private float valoracion;
    private int imageId;

    // Constructor
    public Lugar(String Nombre, String Ciudad, String Descripcion, double distancia, double tiempo, float valoracion, int imageId) {
        this.Nombre = Nombre;
        this.Ciudad = Ciudad;
        this.Descripcion = Descripcion;
        this.distancia = distancia;
        this.tiempo = tiempo;
        this.valoracion = valoracion;
        this.imageId = imageId;
    }

    // Getters
    public String getNombre() {
        return Nombre;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public double getDistancia() {
        return distancia;
    }

    public double getTiempo() {
        return tiempo;
    }

    public float getValoracion() {
        return valoracion;
    }

    public int getImageId() {
        return imageId;
    }
}
