package dataUsr;
import java.io.Serializable;
/**
 *
 * @author Marina Oteiza
 *
 */
public class User implements Serializable{
    private String alias, correo;
    private String[] prod, intercamb;
    private int codiPost, nProd, nInter;

    public User(String alias, String correo, int cod){
        this.alias=alias;
        this.correo=correo;
        codiPost=cod;
        nProd=0;
        nInter=0;
        prod = new String[100]; //POR AHORA PONEMOS UN TAMAÑO AL AZAR
        intercamb = new String[100];
    }

    /** Esta función devuelve una copia del usuario que se le haya pasado
     *
     *	@return aux copia de la información del registro de un usuario
     */
    public User copia() {
        User aux=new User(alias, correo, codiPost); //creamos el usuario
        for(int i=0;i<nProd;i++) {
            aux.prod[i]= this.prod[i];
        }
        for(int i=0;i<nInter;i++) {
            aux.intercamb[i]= this.intercamb[i];
        }
        aux.nProd=this.nProd;
        aux.nInter=this.nInter;

        return aux;
    }

    /** Esta función añade un nuevo producto al usuario indicado
     *
     * @param p producto que se quiere añadir
     */
    public void newProd(String p) {
        if(nProd<prod.length) {	//Si hay espacio para guardar más productos
            prod[nProd]=p;		//Se guarda el que se ha pasado por parámetro
            nProd++;
        }
    }

    /** Esta función añade un nuevo intercambio al usuario indicado
     *
     * @param i intercambio que se quiere añadir
     */
    public void newIntercamb(String i) {
        if(nInter<intercamb.length) {	//Si hay espacio para guardar más productos
            intercamb[nInter]=i;		//Se guarda el que se ha pasado por parámetro
            nInter++;
        }
    }

    /** Esta función busca el producto indicado y devuelve el valor de la posición de la lista
     * en la que se encuentra
     *
     * @param p producto que se quiere buscar
     * @return pos pos=-1 si no se ha encontrado el producto p o pos=posición
     * en la que se encuentra el producto en this.prod[]
     */
    public int prodTrobat(String p) {
        int pos=-1;
        for(int i=0;i<prod.length;i++) {
            if(prod[i].equals(p))pos=i;
        }

        return pos;
    }

    /** Esta función busca el intercambio indicado y devuelve el valor de la posición de la lista
     * en la que se encuentra
     *
     * @param inter intercambio que se quiere buscar
     * @return pos pos=-1 si no se ha encontrado el intercambio i o pos=posición
     * en la que se encuentra el intercambio en this.intercamb[]
     */
    public int intercambTrobat(String inter) {
        int pos=-1;
        for(int i=0;i<intercamb.length;i++) {
            if(intercamb[i].equals(inter))pos=i;
        }
        return pos;
    }

    /** Borramos el producto introducido por parámetro
     *
     * @param p producto que se quiere borrar
     */
    public void borraProd(String p) {
        int pos=prodTrobat(p);		//Si se ha encotrado el producto (pos!=-1), lo borramos
        if(pos!=-1) {
            for(int i=pos;i<nProd-1; i++) {
                prod[i]=prod[i+1];
            }
            prod[nProd-1]=null;
            nProd--;
        }

    }

    /** Borramos el intercambio introducido por parámetro
     *
     * @param inter intercambio que se quiere borrar
     */
    public void borraIntercamb(String inter) {
        int pos=intercambTrobat(inter);	//Si se ha encotrado el intercambio (pos!=-1), lo borramos
        if(pos!=-1) {
            for(int i=pos;i<nInter-1; i++) {
                intercamb[i]=intercamb[i+1];
            }
            intercamb[nInter-1]=null;
            nInter--;
        }

    }

    /** Devolvemos la información del usuario en formato String para poder mostrarla desde otras clases
     *
     * @return información del usuario en forma de String
     */
    public String toString(){
        return (" Alias=> "+alias+" Correo=> "+correo+" Codigo Postal=> "+codiPost+"\n");

    }

    /** Devolvemos la información de los productos del usuario en formato String
     * para poder mostrarla desde otras clases
     *
     * @return aux información de los productos del usuario en forma de String
     */
    public String showProd() {
        String aux = "Productos de "+this.alias;
        for(int i=0;i<nProd; i++) {
            aux=aux+"Producto "+(i+1)+"=> "+this.prod[i];
        }
        return aux;
    }

    /** Devolvemos la información de los productos del usuario en formato String
     * para poder mostrarla desde otras clases
     *
     * @return aux información de los intercambios del usuario en forma de String
     */
    public String showIntercamb() {
        String aux = "Intercambios de "+this.alias;
        for(int i=0;i<nInter; i++) {
            aux=aux+"Intercambio "+(i+1)+"=> "+this.intercamb[i];
        }
        return aux;
    }

    //SETTERS Y GETTERS

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String[] getProd() {
        return prod;
    }

    public void setProd(String[] prod) {
        this.prod = prod;
    }

    public String[] getIntercamb() {
        return intercamb;
    }

    public void setIntercamb(String[] intercamb) {
        this.intercamb = intercamb;
    }

    public int getCodiPost() {
        return codiPost;
    }

    public void setCodiPost(int codiPost) {
        this.codiPost = codiPost;
    }

    public int getnProd() {
        return nProd;
    }

    public void setnProd(int nProd) {
        this.nProd = nProd;
    }

    public int getnInter() {
        return nInter;
    }

    public void setnInter(int nInter) {
        this.nInter = nInter;
    }
}
