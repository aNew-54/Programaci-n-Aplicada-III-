package Model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Lugar implements Parcelable{
    private String Nombre;
    private String Ciudad;
    private String Descripcion;
    private double distancia;
    private double tiempo;
    private float valoracion;
    private Uri imageId;
    private Uri imageId2;

    // Constructor
    public Lugar(String Nombre, String Ciudad, String Descripcion, double distancia, double tiempo, float valoracion, Uri imageId, Uri imageId2) {
        this.Nombre = Nombre;
        this.Ciudad = Ciudad;
        this.Descripcion = Descripcion;
        this.distancia = distancia;
        this.tiempo = tiempo;
        this.valoracion = valoracion;
        this.imageId = imageId;
        this.imageId2 = imageId2;
    }

    protected Lugar(Parcel in) {
        Nombre = in.readString();
        Ciudad = in.readString();
        Descripcion = in.readString();
        distancia = in.readDouble();
        tiempo = in.readDouble();
        valoracion = in.readFloat();
        imageId = in.readParcelable(Uri.class.getClassLoader());
        imageId2 = in.readParcelable(Uri.class.getClassLoader());
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

    public Uri getImageId() {
        return imageId;
    }

    public Uri getImageId2() {
        return imageId2;
    }

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
        dest.writeParcelable(imageId, flags);
        dest.writeParcelable(imageId2, flags);
    }
}