package Productes;
import ControlFitxers.Data;

public class Bienes extends Productes{
    private Data dataOf;
    private Data dataInt;
    private String descrip;
    private int code;
    private double ancho;
    private double alto;
    private double largo;
    private double peso;
    private boolean estat; //estado del producto:activado o desactivado
    private int intercanvis; //numero de intercambios del producto

    public Bienes (int code,String descrip,Data dataOf, Data dataInt, boolean estat, int intercanvis, double ancho, double alto,double largo,double peso){
        super(code,descrip, dataOf, dataInt,estat,intercanvis);
        this.alto=alto;
        this.ancho=ancho;
        this.largo=largo;
        this.peso=peso;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void desactivaProducte() { estat=false;}
    /**
     * Método que hace la copia del biem
     *
     * @return duplicado de la instancia del biem
     */
    public Productes copia() {
        return new Bienes(code,descrip,dataOf,dataInt,estat,intercanvis,ancho,alto,largo,peso);
    }
    /**
     * Método que muestra un bien
     *
     * @return String con toda la información
     */

    public String toString() {
        return "Servei [dataOf="+dataOf+",dataInt="+dataInt+ ", codi=" + code+ ", activacio=" +estat+ ", intercanvis=" +intercanvis;
    }

}
