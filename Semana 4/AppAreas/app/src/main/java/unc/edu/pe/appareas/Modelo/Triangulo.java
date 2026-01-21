package unc.edu.pe.appareas.Modelo;

public class Triangulo extends FiguraG{
    public Triangulo(double ancho, double alto) {
        super(ancho, alto);
    }

    public Triangulo(double ancho, double alto, double profundidad) {
        super(ancho, alto, profundidad);
    }

    public double CalcularArea(){
        return (ancho * alto)/2;
    }

    public double CalcularVolumen(){
        // Se considerará al volumen de una piramide rectangular
        //return (ancho*alto*profundidad)/3;
        return ((ancho * alto)/2)*profundidad;
    }
}
