package Productes;
import ControlFitxers.Data;

public class LlistaProductes  {
    public Productes[] llista;
    public int nElem;

    public LlistaProductes(int elem){
        nElem=0;
        llista= new Productes[elem];
    }
    /**
     * Método que permite mostrar por pantalla LlistaProductes
     *
     * @return String con toda la informacion de la lista
     */
    public String toString() {
        String aux = "LLista Productes => Producte: " + nElem;
        for (int j = 0; j < nElem; j++) {
            aux = aux + "\n\tProducte " + (j + 1) + "\t " + llista[j];
        }
        return aux;
    }
    /**
     * Método que añade un servicio al final de la lista
     *
     * @param nuevo contiene lel nuevo producto que queremos añadir
     */
    public void afegirProducte(Productes nuevo) {
        if (nElem >= llista.length) {
            Productes[]aux = new Productes[llista.length * 2];
            for (int i = 0; i < nElem; i++)
                aux[i] = llista[i];
            llista = aux;
        }
        llista[nElem]=nuevo.copia();
        nElem++;
    }

    /**
     * Método que indica cuál es el servicio con mas intercambios
     *
     * @return la copia de la instancia del servicio
     */
    public String mesIntercanvis() {
        int numIntercanvis, l = 0,m=0;
        boolean trobat=false;
        while(!trobat&&m<nElem){
            if(llista[m] instanceof Serveis)
                trobat=true;
            else m++;
        }
        numIntercanvis = llista[m].getIntercanvis();
        while (m < nElem) {
            if((llista[m] instanceof Serveis)&& (numIntercanvis <= llista[m].getIntercanvis())) { //si el numero de inetrcanvis  es inferior al numero de intercanvis de la posicion analizada
                numIntercanvis = llista[m].getIntercanvis(); //actualizamos el numero de dias max del cartel
                l= m; //guardamos la posicion de numIntercanvis en l
            }
            m++;
        }
        return llista[l].toString(); //Se muestra por pantalla el servicio (con el mayor numero de intercanvis)
    }
    /** Método que descativa un producto
     *
     * @param code contiene el codigo del producto
     * @param data2 contiene la nueva fecha de desactivacion
     * @return true (se ha encontrado y desactivado el producto) o false(no se ha encontrado el producto)
     */
    public boolean desactivaProducte( int code,Data data2) {
        boolean trobat = false;
        for (int n=0; n < nElem; n++) {
            if (llista[n].getCode( )==code){
                llista[n].desactivaProducte();
                if(llista[n] instanceof Serveis)
                    ((Serveis) llista[n]).setDataDes(data2);
            }
        }
        return trobat;
    }
    /** Método que muestra los servicios activos
     *
     * @return String lista con todos los servicios activos
     */
    public String mostraServeisActius( ){
        LlistaProductes aux = new LlistaProductes(nElem);
        int i=0;
        while(i<nElem){
            if((llista[i] instanceof Serveis)&&(llista[i].isEstat())){
                aux.afegirProducte(llista[i]);    //Se desplaza el resto de la lista para eliminarla y no dejar huecos
            }
            i++;
        }
        return aux.toString();
    }
    /** Método que muestra los bienes disponibles
     *
     * @return String lista con todos los bienes disponibles
     */
    public String mostraBensActius( ){
        LlistaProductes aux = new LlistaProductes(nElem);
        int i=0;
        while(i<nElem){
            if((llista[i] instanceof Bienes)&&(llista[i].isEstat())){
                aux.afegirProducte(llista[i]);    //Se desplaza el resto de la lista para eliminarla y no dejar huecos
            }
            i++;
        }
        return aux.toString();
    }
    /** Método que elimina un producto de la lista siempre y cuando tenga mínimo un intercmabio
     *
     * @param code contiene el código del producto.
     */
    public void eliminaProducto(int code){
        int i=0;
        while(i<nElem){
            if((llista[i].getCode( )==code)&&(llista[i].getIntercanvis()>0)){
                for(int k=i; k<nElem-1; k++){           //Si se encuentra el codigo en la lista de productos y tiene algun intercambio
                    llista[k] = llista[k+1];    //Se desplaza el resto de la lista para eliminarlo y no dejar huecos
                }
                nElem--; //Si hemos eliminado un producto, actualizamos el nº de elementos que pasa a tener la lista
            }
            else i++; //Solo si no encontramos el producto
        }
    }

    public int getnumProd() {
        return nElem;
    }
    /**
     *@;SoniaAlfonso
     * torna nElements LLista
     */
    public Productes getList(int i) {
        return llista[i];
    }

    /**
     * * @;SoniaAlfonso
     * torna productes de la llista en la posicio i
     */


}