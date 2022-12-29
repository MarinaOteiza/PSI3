package Peticiones;

import dataUsr.Productos;

//ÀITOR OLIVARES PERUCHO
public class Peticiones {
    protected String codi;
    protected String usuari;
    protected Productos producte;

    public Peticiones(String codi, String usuari, Productos producte) {
        this.codi = codi;
        this.usuari = usuari;
        this.producte = producte;
    }

    public Productos getProducte() {return producte;}

    public Peticiones copia(){
        Peticiones p= new Peticiones(this.codi, this.usuari, this.producte);
        return p;
    }
}