package Model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Lugar implements Parcelable {

    String nombre;
    String ciudad;
    String descripcion;
    double distancia;
    double tiempo;
    float valoracion;
    Uri imageId;
    Uri imageId2;

    public Lugar(String nombre, String ciudad, String descripcion, double distancia, double tiempo, float valoracion, Uri imageId,Uri imageId2) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.distancia = distancia;
        this.tiempo = tiempo;
        this.valoracion = valoracion;
        this.imageId = imageId;
        this.imageId2=imageId2;
    }

    protected Lugar(Parcel in) {
        nombre = in.readString();
        ciudad = in.readString();
        descripcion = in.readString();
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

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDescripcion() {
        return descripcion;
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
        dest.writeString(nombre);
        dest.writeString(ciudad);
        dest.writeString(descripcion);
        dest.writeDouble(distancia);
        dest.writeDouble(tiempo);
        dest.writeFloat(valoracion);
        dest.writeParcelable(imageId, flags);
        dest.writeParcelable(imageId2, flags);
    }
}
