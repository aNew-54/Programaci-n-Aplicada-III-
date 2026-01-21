package unc.edu.pe.appcompras;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Obsequio implements Parcelable {
    String nombre;
    int imagen;

    public Obsequio(String nombre, int imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    protected Obsequio(Parcel in) {
        nombre = in.readString();
        imagen = in.readInt();
    }

    public static final Creator<Obsequio> CREATOR = new Creator<Obsequio>() {
        @Override
        public Obsequio createFromParcel(Parcel in) {
            return new Obsequio(in);
        }

        @Override
        public Obsequio[] newArray(int size) {
            return new Obsequio[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeInt(imagen);
    }
}
