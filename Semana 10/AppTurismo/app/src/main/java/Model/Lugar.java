
package Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Lugar implements Parcelable {
    private String Nombre;
    private String Ciudad;
    private String Descripcion;
    private double distancia;
    private double tiempo;
    private float valoracion;
    private int imageId;
    private int imageId2;

    protected Lugar(Parcel in) {
        Nombre = in.readString();
        Ciudad = in.readString();
        Descripcion = in.readString();
        distancia = in.readDouble();
        tiempo = in.readDouble();
        valoracion = in.readFloat();
        imageId = in.readInt();
        imageId2 = in.readInt();
    }

    public static final Creator<Lugar> CREATOR = new Creator<Lugar>() {
        @Override
        public Lugar createFromParcel(Parcel in) {
            return new Lugar(in);
        }

        @Override
        public Lugar[] newArray(int size) {
            return new Lugar[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(Nombre);
        dest.writeString(Ciudad);
        dest.writeString(Descripcion);
        dest.writeDouble(distancia);
        dest.writeDouble(tiempo);
        dest.writeFloat(valoracion);
        dest.writeInt(imageId);
        dest.writeInt(imageId2);
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId2() {
        return imageId2;
    }

    public void setImageId2(int imageId2) {
        this.imageId2 = imageId2;
    }

    public Lugar(String nombre, String ciudad, String descripcion, double distancia, double tiempo, float valoracion, int imageId, int imageId2) {
        Nombre = nombre;
        Ciudad = ciudad;
        Descripcion = descripcion;
        this.distancia = distancia;
        this.tiempo = tiempo;
        this.valoracion = valoracion;
        this.imageId = imageId;
        this.imageId2 = imageId2;
    }
    public Lugar(String nombre, String ciudad, String descripcion, double distancia, double tiempo, float valoracion, int imageId) {
        Nombre = nombre;
        Ciudad = ciudad;
        Descripcion = descripcion;
        this.distancia = distancia;
        this.tiempo = tiempo;
        this.valoracion = valoracion;
        this.imageId = imageId;
    }}
