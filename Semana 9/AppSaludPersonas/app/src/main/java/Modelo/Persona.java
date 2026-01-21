package Modelo;

import android.util.Log;

import java.io.Serializable;

public class Persona implements Serializable {
    public String nombres;
    public String apellidos;
    public int edad;
    public String dni;
    double peso;
    double altura;

    public Persona(String nombres, String apellidos, int edad, String dni, double peso, double altura) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.dni = dni;
        this.peso = peso;
        this.altura = altura;
    }

    //tarea
    public boolean esMayorEdad(){
        return edad>=18;
    }
    public boolean verificarDNI(){
        return dni.matches(("\\d{8}"));
    }

    public int calcularIMC(){
        double par=peso/(altura*altura);
        //Log.e("par:",String.valueOf(par));
        if (par<20)
            return -1;
        else if (par>=20 && par<=25)
            return 0;
        else
            return 1;
    }

    //@Override
    //datos de persona
    public String toStringPersona() {
        return "Persona{" +
                "nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", dni='" + dni + '\'' +
                ", peso=" + peso +
                ", altura=" + altura +
                '}';
    }
    @Override
    //datos de persona con datos relevantes
    public String toString(){
        String datosPersona=nombres+" "+apellidos;
        if (esMayorEdad())
            datosPersona+=", es mayor de edad";
        else
            datosPersona+=", es menor de edad";

        if (calcularIMC()==-1)
            datosPersona+=", tiene bajo peso.";
        else if (calcularIMC()==0)
            datosPersona+=", peso ideal.";
        else
            datosPersona+=", tiene sobrepeso.";
        return datosPersona;
    }

    public String mensajeRegistro(){
        return "Registro satisfactorio";
    }
}
