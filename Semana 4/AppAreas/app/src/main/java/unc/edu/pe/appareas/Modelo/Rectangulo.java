package unc.edu.pe.appareas.Modelo;

public class Rectangulo extends FiguraG {
    public Rectangulo(double ancho, double alto) {
        super(ancho, alto);
    }
    public Rectangulo(double ancho, double alto, double profundidad) {
        super(ancho, alto,profundidad);
    }

    public double CalcularArea(){
        return ancho * alto;
    }

    public double CalcularVolumen(){
        return ancho* alto* profundidad;
    }
}
