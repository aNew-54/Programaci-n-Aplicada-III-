package unc.edu.pe.appcompras;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Electrodomestico implements Parcelable{
    String nombre;
    double precio;
    int imagen;

    public Electrodomestico(String nombre, double precio, int imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    protected Electrodomestico(Parcel in) {
        nombre = in.readString();
        precio = in.readDouble();
        imagen = in.readInt();
    }

    public static final Creator<Electrodomestico> CREATOR = new Creator<Electrodomestico>() {
        @Override
        public Electrodomestico createFromParcel(Parcel in) {
            return new Electrodomestico(in);
        }

        @Override
        public Electrodomestico[] newArray(int size) {
            return new Electrodomestico[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeDouble(precio);
        dest.writeInt(imagen);
    }
}
