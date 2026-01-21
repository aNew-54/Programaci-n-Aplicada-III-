package unc.edu.pe.appareas.Modelo;

public class Cuadrado extends FiguraG{

    public Cuadrado(double param1) {
        super(param1);
    }

    public double CalcularArea(){
        return Math.pow(ancho,2);
    }
    public double CalcularVolumen(){
        return Math.pow(ancho,3);
    }
}
