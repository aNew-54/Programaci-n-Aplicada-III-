package ViewModel;

import java.util.ArrayList;

import Model.Lugar;

public class VMLugar {

    ArrayList<Lugar> listaLugares;

    public VMLugar() {
        this.listaLugares=new ArrayList<>();
    }
    public ArrayList<Lugar> listarLugares(){
        return listaLugares;
    }

    public Lugar getLugar(int i){
        return listaLugares.get(i);
    }

    public void AgregarLugar(Lugar lugar){
        listaLugares.add(lugar);
    }
}
