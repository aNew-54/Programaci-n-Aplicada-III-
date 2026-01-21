package ViewModel;
import java.util.ArrayList;
import Model.Lugar;
public class VMLugar {
    ArrayList<Lugar> listaLugares;
    public VMLugar(){
        this.listaLugares = new ArrayList<Lugar>();
    }
    public ArrayList<Lugar> listarLugares(){
        return listaLugares;
    }

    public Lugar getLugar(int i){
        return listaLugares.get(i);
    }
}
