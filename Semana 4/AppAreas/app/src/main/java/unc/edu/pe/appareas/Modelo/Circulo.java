package unc.edu.pe.appareas.Modelo;

public class Circulo extends FiguraG{

    public Circulo(double param1) {
        super(param1);
    }
    public Circulo(double param1, double alto) {
        super(param1, alto);
    }

    public double CalcularArea(){
        return Math.PI * Math.pow(ancho,2);
    }

    public double CalcularVolumen(){
        return Math.PI * Math.pow(ancho,2)*alto;
    }
}
