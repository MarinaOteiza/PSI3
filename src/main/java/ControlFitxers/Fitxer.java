package ControlFitxers;

import ControlFitxers.Data;
import Productes.Bienes;
import Productes.LlistaProductes;
import Productes.Serveis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Fitxer {
    public Fitxer() {

    }

    public LlistaProductes LeerBienes(String NomFitxer) throws IOException {
        int MaxLin = 500000;
        LlistaProductes nova = new LlistaProductes(MaxLin);
        Scanner lectura = new Scanner(new File(NomFitxer));
        Scanner part;
        // dividir información del fichero//
        int codi, intercanvis, dat1, dat2;
        String descripcio, frase;
        Data off, Inter;
        boolean estat;
        double ancho, alto, largo, peso;

        while (lectura.hasNext()) {
            frase = lectura.nextLine();
            part = new Scanner(frase);
            part.useDelimiter(";");
            part.useLocale(Locale.ENGLISH);
            codi = part.nextInt();
            descripcio = part.next();
            estat = part.nextBoolean();
            intercanvis = part.nextInt();
            ancho = part.nextDouble();
            alto = part.nextDouble();
            largo = part.nextDouble();
            peso = part.nextDouble();
            dat1 = part.nextInt();
            dat2 = part.nextInt();
            off = new Data(dat1);
            if (dat2 != 0) {
                Inter = new Data(dat2);
            } else Inter = new Data();
            Bienes benou = new Bienes(codi, descripcio, off, Inter, estat, intercanvis, ancho, alto, largo, peso);
            nova.afegirProducte(benou);
            lectura.hasNext();

        }
        lectura.close();
        return nova;

    }
    /**
     * Procediment per a llegir un  archiu
     *  passa la informació a  una llista de productes
     * @param NomFitxer : passem el nom del Fitxer que conté la informació
     * @return Nova: LLista de Productes amb la informació
     */

    public void ModificaFitxer(LlistaProductes n1, String NomFitxer) throws IOException {

        try (BufferedWriter escriptura = new BufferedWriter(new FileWriter(NomFitxer))) {
            int codi, intercanvis, dat1, dat2;
            String descripcio, frase;
            Data off, Inter;
            boolean estat;
            double ancho, alto, largo, peso;
            int i;
            for ( i=0; i<n1.getnumProd(); i++) {

                codi=n1.getList(i).getCode();
                descripcio=n1.getList(i).getDescrip();
                intercanvis=n1.getList(i).getIntercanvis();
                estat=n1.getList(i).isEstat();
                off=n1.getList(i).getDataOf();
                Inter=n1.getList(i).getDataInt();
                frase=(codi+";"+descripcio+";"+estat+ ";" +off+";"+Inter+";"+intercanvis+";");
                escriptura.write(frase);
                escriptura.newLine();
            }
           escriptura.close();
        }
    }
/**
 *Procediment per a escriure una llista de productes a un archiu
 *      * @param NomFitxer : passem el nom del Fitxer que conté la informació
 *      * @param n1: llista de productes
 */
public LlistaProductes LeerServicios(String NomFitxer) throws IOException {
    int MaxLin = 500000;
    LlistaProductes nova = new LlistaProductes(MaxLin);
    Scanner lectura = new Scanner(new File(NomFitxer));
    Scanner part;
    // dividir información del fichero//
    int codi, intercanvis, dat1, dat2, dt3;
    String descripcio, frase;
    Data off, Inter, Des;
    boolean estat;

    while (lectura.hasNext()) {
        frase = lectura.nextLine();
        part = new Scanner(frase);
        part.useDelimiter(";");
        part.useLocale(Locale.ENGLISH);
        codi = part.nextInt();
        descripcio = part.next();
        estat = part.nextBoolean();
        intercanvis = part.nextInt();
        dat1 = part.nextInt();
        dat2 = part.nextInt();
        dt3 = part.nextInt();
        off = new Data(dat1);
        if (dat2 != 0) {
            Inter = new Data(dat2);
        } else Inter = new Data();
        Des= new Data(dt3);
        Serveis Servei = new Serveis(codi, descripcio, off, Inter, estat, intercanvis,Des);
        nova.afegirProducte(Servei);
        lectura.hasNext();
    }
    lectura.close();
    return nova;
}

/**
 * Procediment per a llegir un archiu
 *  passa la informació a  una llista de productes
 * @param NomFitxer : passem el nom del Fitxer que conté la informació
 * @return Nova: LLista de Productes amb la informació
 */

}