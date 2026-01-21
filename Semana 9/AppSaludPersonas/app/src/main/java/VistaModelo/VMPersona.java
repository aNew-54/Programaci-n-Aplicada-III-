package VistaModelo;

import java.util.ArrayList;

import Modelo.Persona;

public class VMPersona {
    ArrayList<Persona> listaPersona;

    public VMPersona() {
        this.listaPersona = new ArrayList<>();
    }

    public String Agregar(Persona persona)
    {
        if (persona.verificarDNI()){
            listaPersona.add(persona);
            return persona.mensajeRegistro();
        }
        else
            return "DNI incorrecto";
    }

    public ArrayList<Persona> listarPersonas(){
        return listaPersona;
    }
}
