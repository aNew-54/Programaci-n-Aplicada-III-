package unc.edu.pe.appareas.Modelo;

public class FiguraG {
    protected double ancho;
    protected double alto;
    protected double profundidad;
    public FiguraG(double param1){
        this.ancho = param1;
    }
    public FiguraG(double ancho, double alto) {
        this.ancho = ancho;
        this.alto = alto;
    }
    public FiguraG(double ancho, double alto, double profundidad){
        this.ancho = ancho;
        this.alto = alto;
        this.profundidad = profundidad;
    }
}
