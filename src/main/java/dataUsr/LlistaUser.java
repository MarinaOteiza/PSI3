package dataUsr;

import ControlFitxers.Data;
import Peticiones.*;
import Productes.*;
import Productes.LlistaProductes;

/**
 *
 * @author Marina Oteiza
 *
 */
public class LlistaUser{
    private User[] llista;
    private int nElem;
    private Codigo codi;
    public LlistaUser(int elem){
        nElem = 0;
        llista = new User[elem];
        codi = new Codigo(1000);
    }

    /**Añadimos un nuevo usuario a la lista
     *
     * @param u usuario nuevo que va a ser registrado
     */
    public void nuevoUsr(User u){
        if(nElem==0) {
            llista[nElem]=u.copia();
            nElem++;
        }else {
            if(usuarioRegistrado(u) && nElem<=llista.length) { //Lo añadimos si el usuario no se ha registrado anteriormente
                llista[nElem]=u.copia();
                nElem++;
            }
        }
    }

    /**Comprobamos si el alias del usuario pasado por parámetro aparece ya en llista[]
     *
     * @param u usuario nuevo que va ser buscado
     * @return trobat true si se ha encontrado, false si no
     */
    public boolean usuarioRegistrado(User u) {
        boolean trobat=false;
        for(int i=0;i<nElem;i++) {
            if(llista[i].getAlias().equals(u.getAlias())){
                trobat=true;
                break;
            }
        }
        return(trobat);
    }

    /**Comprobamos si el alias del usuario pasado por parámetro aparece ya en llista[]
     *
     * @param alias que se quiere comprobar
     * @return trobat true si se ha encontrado, false si no
     */
    public boolean usuarioRegistrado(String alias) {
        boolean trobat=false;
        for(int i=0;i<nElem;i++) {
            if(llista[i].getAlias().equals(alias)){
                trobat=true;
                break;
            }
        }
        return(trobat);
    }

    /**Borramos el producto del usuario cuyo alias y producto son especificados por parámetro
     *
     * @param alias para saber qué usuario tenemos que modificar
     * @param p producto que se quiere quitar
     * @return correcto -1:no se ha encontrado el alias; 0:intercambio no encontrado; 1:intercambio encontrado
     */
    public int quitaProducto(String alias, String p) {
        int correcto=-1, pos;
        if(this.usuarioRegistrado(alias)) { //Nos aseguramos de que el usuario de dicho alias exista previamente
            pos= this.posUsuario(alias);    //Si existe, calculamos la posición de la llista en la que se encuentra (pos)
            if(llista[pos].prodTrobat(p)!=-1) { //Comprobamos que el usuario haya introducido un producto válido
                llista[pos].borraProd(p);
                correcto=1;	//intercambio encontrado
            }else correcto=0;//intercambio no encontrado
        }

        return correcto;
    }

    /**Borramos el intercambio del usuario cuyo alias y producto son especificados por parámetro
     *
     * @param alias para saber qué usuario tenemos que modificar
     * @param inter intercambio que se quiere quitar
     * @return correcto -1:no se ha encontrado el alias; 0:producto no encontrado; 1:producto encontrado
     */
    public int quitaIntercambio(String alias, Peticiones inter) {
        int correcto=-1, pos;
        if(this.usuarioRegistrado(alias)) { //Nos aseguramos de que el usuario de dicho alias exista previamente
            pos= this.posUsuario(alias);    //Si existe, calculamos la posición de la llista en la que se encuentra (pos)
            if(llista[pos].intercambTrobat(inter)!=-1) { //Comprobamos que el usuario haya introducido un producto válido
                llista[pos].borraIntercamb(inter);
                correcto=1;	//intercambio encontrado
            }else correcto=0;//intercambio no encontrado
        }
        return correcto;
    }



    /**Añadimos un intercambio a un usuario
     *
     * @param alias para saber qué usuario tenemos que modificar
     * @param inter intercambio que se quiere añadir
     * @return correcto true si se ha podido añadir, false si no
     */
    public boolean nuevoIntercambio(String alias, Peticiones inter) {
        boolean correcto=false;
        int pos;
        if(this.usuarioRegistrado(alias)) {
            pos= this.posUsuario(alias);
            llista[pos].newIntercamb(inter);
            correcto=true;
        }
        return correcto;
    }

    /**Mostramos los productos de un usuario
     *
     * @param alias para saber qué usuario tenemos que modificar
     * @return aux productos del usuario
     */
    public String muestraProd(String alias) {
        String aux;
        int pos;
        if(usuarioRegistrado(alias)) {  //Comprobamos que el alias exista en llista[]
            pos= this.posUsuario(alias);
            aux = "Lista de productos de "+llista[pos].getAlias()+"\n";
            for(int i=0;i<nElem;i++) {
                aux=aux+"Usuario "+(i+1)+": "+llista[i].showProd()+"\n";
            }
        }else aux="El nombre de usuario no ha sido encontado. Por favor, intentelo de nuevo";
        return aux;
    }

    /**Mostramos los intercambios de un usuario
     *
     * @param alias para saber qué usuario tenemos que modificar
     * @return aux intercambios del usuario
     */
    public String muestraIntercamb(String alias) {
        String aux;
        int pos;
        if(usuarioRegistrado(alias)) {
            pos= this.posUsuario(alias);
            aux = "Lista de intercambios de "+llista[pos].getAlias()+"\n";
            for(int i=0;i<nElem;i++) {
                aux=aux+"Usuario "+(i+1)+": "+llista[i].showIntercamb()+"\n";
            }
        }else aux="El nombre de usuario no ha sido encontado. Por favor, intentelo de nuevo";
        return aux;
    }

    /**Buscamos la posición en la que se encuentra el usuario en llista[]
     *
     * @param alias para saber qué usuario tenemos que buscar
     * @return pos posición del usuario en llista[]
     */
    public int posUsuario(String alias) {
        int pos=0;
        for(int i=0;i<nElem;i++) {
            if(llista[i].getAlias().equals(alias)) pos=i;
        }
        return pos;
    }

    public boolean productoUsuarioRegistrado(String objeto){
        boolean trobat=false;
        for(int i=0;i<nElem;i++) {
            if(llista[i].prodTrobat(objeto)>-1) trobat=true;
        }
        return(trobat);
    }
    public void nouBe(String alias, String nom, String code, String descripcion, Data data1, double alto, double largo, double ancho, double peso){
        llista[this.posUsuario(alias)].newBien(nom, code, descripcion, ancho, alto, largo, peso, data1);
    }
    public void nouServei(String alias, String nom, String code, String descripcion, Data data1, Data data2){
        llista[this.posUsuario(alias)].newServei(nom, code, descripcion, data1, data2);
    }

    public boolean compProd(String alias, String p){
        return(llista[this.posUsuario(alias)].prodTrobatDos(p));
    }

    public void quitaBien(String alias, String codigo){
        llista[this.posUsuario(alias)].menosBien(codigo);
    }
    public void desactServicio(String alias, String codigo, Data data2){
        llista[this.posUsuario(alias)].inactivaServicio(codigo, data2);
    }

    /**Buscamos la posición en la que se encuentra el usuario en llista[]
     *
     * @return aux información de los usuarios que hay en llista[]
     */
    public String toString() {
        String aux = "Lista de usuarios\n";
        for(int i=0;i<nElem;i++) {
            aux=aux+"Usuario "+(i+1)+": "+llista[i].toString()+"\n";
        }
        return aux;
    }

    //SETTERS Y GETTERS

    public int getPosicionPeticionLlista(String alias, Productes p){
        int pos=0;
        if(this.usuarioRegistrado(alias)) {
            pos= this.posUsuario(alias);
            return llista[pos].getIntercamb().trobatPeticion(p); //TODO: a que funcion quieres llamar? getIntercamb();???
        }
        return 0;
    }



    public Peticiones getPeticionLlista(String alias, int i){
        int pos=0;
        if(this.usuarioRegistrado(alias)) {
            pos= this.posUsuario(alias);
            return llista[pos].getIntercamb().getLlistaPos(i); //TODO: a que funcion quieres llamar? getIntercamb();???
        }
        return null;
    }
    public User[] getLlista() {
        return llista;
    }


    public void setLlista(User[] llista) {
        this.llista = llista;
    }


    public int getnElem() {
        return nElem;
    }


    public void setnElem(int nElem) {
        this.nElem = nElem;
    }

    public Codigo getCodigo(){return codi;}

}
