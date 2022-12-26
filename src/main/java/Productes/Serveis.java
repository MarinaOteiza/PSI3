package Productes;
import ControlFitxers.Data;
public class Serveis extends Productes{
    private Data dataOf; //fecha de la oferta
    private Data dataInt; //fecha del intercambio
    private String descrip; //descripcion del producto
    private boolean estat; //estado del producto:activado o desactivado
    private int intercanvis; //numero de intercambios del producto
    private Data dataDes; //fecha de desactivacion del servicio
    private int code; //codigo del producto

    public Serveis(int code, String descrip, Data dataOf, Data dataInt, boolean estat, int intercanvis, Data dataDes){
        super(code,descrip, dataOf, dataInt,estat,intercanvis);
        this.dataDes=null; //creamos una fecha de descativacion
    }

    public Data getDataDes() {
        return dataDes;
    }

    public void setDataDes(Data dataDes) {
        this.dataDes = dataDes;
    }
    /**
     * Método que desactiva un servicio
     *
     */
    public void desactivaProducte(){ estat=false;}

    /**
     * Método que hace la copia del servicio
     *
     * @return duplicado de la instancia del servicio
     */
    public Productes copia() {
        return new Serveis(code,descrip,dataOf,dataInt,estat,intercanvis,dataDes);
    }
    /**
     * Método que muestra Serevi
     *
     * @return String con toda la información
     */
    public String toString() {
        return "Servei [dataOf="+dataOf+",dataInt="+dataInt+",dataFi=" + dataDes + ", codi=" + code+ ", activacio=" +estat+ ", intercanvis=" +intercanvis;
    }
}
