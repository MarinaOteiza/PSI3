package appUsr;

import dataUsr.*;
import java.util.Scanner;
import Peticiones.*;
import Productes.*;
import ControlFitxers.*;
/**
 *
 * @author Marina Oteiza
 *
 */
public class RegUser{
    static Scanner teclat = new Scanner(System.in);
    public static void main(String[] args){
        int opcion=10; //hay que iniciarlo, asi que ponemos un valor cualquiera
        boolean exit=false;
        LlistaUser llista = new LlistaUser(100);
        LlistaProductes llista1= new LlistaProductes(500); //500 como max elem

        do{
            menu();
            try{
                opcion = Integer.parseInt(teclat.nextLine());
                if(opcion<1 || opcion>9) throw new IndexOutOfBoundsException();
            }catch(NumberFormatException e){
                System.out.println("Introduce un valor numérico \nERROR: "+e+"\n\n");
            }catch (IndexOutOfBoundsException e){
                System.out.println("Ha introducido un valor fuera del rango (1-9) \nERROR:  "+e+"\n\n");
            }

            switch (opcion)
            {
                case 1:
                    nuevoUsuario(llista);
                    break;
                case 2:
                    verUsers(llista);
                    break;
                case 3:
                    verProds(llista);
                    break;
                case 4:
                    verIntercamb(llista);
                    break;
                case 5:
                    sumaProd(llista);
                    break;
                case 6:
                    sumaIntercamb(llista);
                    break;
                case 7:
                    menosProd(llista,llista1);
                    break;
                case 8:
                    menosIntercamb(llista);
                    break;
                case 9:
                    ContestarUsuario(llista);
                    break;
                case 10:
                    ContestarCliente(llista);
                    break;
                case 11:
                    desactivaProducte(llista1);
                case 12:
                    ServeisActius(llista1);
                case 13:
                    MostraProdActius(llista1);
                case 14:
                    ServeiMesIntercanvis(llista1);
                case 15:
                    System.out.println("Has salido");
                    exit = true;
                    break;
            }
        }while (!exit);
        teclat.close();
    }

    private static void menu() {
        System.out.println("Que quieres hacer? ");
        System.out.println("1-Nuevo registro ");
        System.out.println("2-Ver usuarios registados ");
        System.out.println("3-Ver productos ");
        System.out.println("4-Ver intercambios ");
        System.out.println("5-Sumar producto ");
        System.out.println("6-Proponer intercambio");
        System.out.println("7-Quitar producto");
        System.out.println("8-Quitar intercambio");
        System.out.println("9-Responder intercambios");
        System.out.println("10-Valoración del intercambio");
        System.out.println("11-Desactivar un producto");
        System.out.println("12-Ver lista de todos los servicios activos");
        System.out.println("13-Mostrar todos los productos activos");
        System.out.println("14-Mostrar el servicio con mas intercambios");
        System.out.println("15-Salir ");
        System.out.print("\n\t\t\tIndica opcion:\n");
    }

    private static void nuevoUsuario(LlistaUser llista) { //Pedimos los datos para registrar a un nuevo usuario
        System.out.println("REGISTRO NUEVO ");
        String alias=null, correo;
        int codiPost=-1;
        User nou;

        System.out.println("Introduce un alias: ");
        try{
            alias=teclat.nextLine();
            if(llista.usuarioRegistrado(alias)) throw new Exception(); //Evitamos que el usuario introduzca una alias repetido
        }catch(Exception e){
            System.out.println("Ese usuario ya ha sido ocupado\nIntroduzca otro usuario");
        }
        System.out.println("CORREO ELECTRONICO: ");
        correo= teclat.nextLine();
        System.out.println("CODIGO POSTAL: ");  //Nos aseguramos de que el código postal sea un valor numérico
        do {
            try {
                codiPost = Integer.parseInt(teclat.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("El codigo postal debe ser un valor numerico\n" + "ERROR: " + e + "\nIntroduzca el codigo postal");
            }
        }while(codiPost==-1);

        nou=new User(alias, correo, codiPost);
        llista.nuevoUsr(nou);
        if(llista.usuarioRegistrado(alias))System.out.println("Usuario guardado correctamente");
    }
    private static void sumaProd(LlistaUser llista) {
        String nombre, descripcion;
        //TODO INTRODUCIR LA DATA DEL DÍA EN EL QUE SE PONE EL PRODUCTO!!!
        int tipus=10;
        String a=pedirAlias(llista);
        System.out.println("Que producto quieres añadir? ");
        //p=teclat.nextLine(); TODO descomentar esto la p da error
       // if(llista.nuevoProducto(a, p)) System.out.println("Producto añadido correctamente");
       // else System.out.println("El producto no se ha podido añadir");
    }

    private static void sumaFisic(LlistaUser llista, String nom, String descripcion, LlistaProductes llista1) {
        int amplada, alcada, fons, pes,intercanvis=0,code;
        boolean estat=true; //afegim un nou produtcte fisic, per tant es troba actiu
        System.out.println("Indica la data d'introduccio del ben (dia, mes i any: 00/00/0000): ");
        String fecha1 = teclat.nextLine();
        String[] fechaPartida1 = fecha1.split("/"); //creamos una tabla con las fechas divididas segun "/"
        Data data1 = new Data (Integer.parseInt(fechaPartida1[0]), Integer.parseInt(fechaPartida1[1]), Integer.parseInt(fechaPartida1[2]));
        code=12345; // TODO  avisarle del código que tiene su producto, este me lo he inventado jejejeje
        Data data2= new Data(); //fecha creada por defecto 1/01/2000
        //TODO: ponemos límites de peso o tamaño????????????Además tiene si se ha intercambiado o no y data del intercambio en ese caso pero NO cuando se crea
        //TODO: lanzar excepciones con valores negativos y del tipo String
        System.out.println("Introduce la amplada: ");
        amplada = Integer.parseInt(teclat.nextLine());
        System.out.println("Introduce la alcada: ");
        alcada = Integer.parseInt(teclat.nextLine());
        System.out.println("Introduce el fons: ");
        fons = Integer.parseInt(teclat.nextLine());
        System.out.println("Introduce el pes: ");
        pes = Integer.parseInt(teclat.nextLine());
        Bienes producte=new Bienes(code,descripcion,data1,data2,estat,intercanvis,amplada,alcada,fons,pes);
        llista1.afegirProducte(producte);
        if(llista1.darrerElem()==producte)
            System.out.println("El bien se ha añadido correctamente");
        else
            System.out.println("El bien no se ha podido añadir");
    }
    /**
     * Método que añade un servicio a la lista de productos
     * @param llista1 Llista de productes
     */
    private static void sumaServei(LlistaUser llista, String nom, String descripcion, LlistaProductes llista1){
        int code,intercanvis=0;
        boolean estat=true;
        code=1234; // TODO avisarle del código que tiene su producto
        System.out.println("Indica la data d'introduccio del servei (dia, mes i any: 00/00/0000): ");
        String fecha1 = teclat.nextLine();
        String[] fechaPartida1 = fecha1.split("/"); //creamos una tabla con las fechas divididas segun "/"
        Data data1 = new Data (Integer.parseInt(fechaPartida1[0]), Integer.parseInt(fechaPartida1[1]), Integer.parseInt(fechaPartida1[2]));
        System.out.println("Indica la data de disponibilitat final del servei (dia, mes i any: 00/00/0000): ");
        String fecha2 = teclat.nextLine();
        String[] fechaPartida2 = fecha2.split("/"); //creamos una tabla con las fechas divididas segun "/"
        Data data2 = new Data (Integer.parseInt(fechaPartida2[0]), Integer.parseInt(fechaPartida2[1]), Integer.parseInt(fechaPartida2[2]));
        Data data3=new Data(); //introduim com data intercanvi 1/01/2000 per defecte
        Serveis producte=new Serveis(code,descripcion,data1,data3,estat,intercanvis,data2);
        llista1.afegirProducte(producte);
        if(llista1.darrerElem()==producte)
            System.out.println("El servicio se ha añadido correctamente");
        else
            System.out.println("El servicio no se ha podido añadir");

    }
    //Dejo la función pedirProducto por si acaso
    private static Productes pedirProducto(LlistaUser llista, String usuario) {
        String a, t;
        int pos = llista.posUsuario(usuario);
        Productes product=null; //Quitar esto (solo está para que no aparezca error)
        do {
            System.out.println("Introduce el producto: ");
            a = teclat.nextLine();
            if (!llista.productoUsuarioRegistrado(a)) System.out.println("Producto no encontrado, vuelve "
                    + "a intentarlo\n");
        } while (!llista.productoUsuarioRegistrado(a));
        User[] llista2 = llista.getLlista();
        t = llista2[pos].getTipusProducte(a);
        //Productes product = new Productes(a, t);
        return product;
    }
    /**
     * Método que elimina un producto de la lista del usuario y de la lista de productos
     * @param llista1 Llista de productes
     * @param llista Llista d'usuari
     */
    private static void menosProd(LlistaUser llista, LlistaProductes llista1) {
        String p;
        String a=pedirAlias(llista);
        System.out.println("Que intercambio quieres quitar? ");
        p=teclat.nextLine();
        int code=1234; //TODO como sabe el usuario el codigo del producto a eliminar
        if((llista.quitaProducto(a, p)==0)&&(llista1.eliminaProducto(code)==false)) System.out.println("No se ha encontrado el producto");
        if((llista.quitaProducto(a, p)==1)&&(llista1.eliminaProducto(code)==true)) System.out.println("Producto quitado correctamente");


    }

    private static String pedirAlias(LlistaUser llista) {
        String a;
        do{
            System.out.println("Introduce un alias: ");
            a=teclat.nextLine();
            if(!llista.usuarioRegistrado(a)) System.out.println("Usuario no encontrado, compruebe que lo " +
                    "haya escrito correctamente\n");
        }while(!llista.usuarioRegistrado(a));
        return a;
    }
    private static void verUsers(LlistaUser llista) {
        System.out.println(llista.toString());
    }
    private static void verProds(LlistaUser llista) {
        System.out.print(llista.muestraProd(pedirAlias(llista)));
    }
    private static void verIntercamb(LlistaUser llista) {
        System.out.print(llista.muestraIntercamb(pedirAlias(llista)));
    }
    private static void sumaIntercamb(LlistaUser llista) {
        String objeto;
        System.out.println("¿Cual es el usuario con quien quieres intercambiar y su producto?");
        String nombreUsu = pedirAlias(llista);
        Productes objetoUsu = pedirProducto(llista, nombreUsu);
        String c = llista.getCodigo().cadenaAleatoria();
        System.out.println("\n-Introduce tu informacion-\n");
        String b = pedirAlias(llista);
        objeto = pedirObjeto();
        if (!objetoUsu.isProductTienePeticion()) {        //Si no hi ha cap peticio anterior creem una llista nova
            if (objetoUsu.getTipus_product().equals("Fisico")) {
                LlistaPeticionesFisic LlistaPF = new LlistaPeticionesFisic(c, nombreUsu, objetoUsu, 100);
                PeticioFisic petF = new PeticioFisic(b, objeto);
                LlistaPF.addF(petF);
                if (llista.nuevoIntercambio(nombreUsu, LlistaPF)) System.out.println("Producto añadido correctamente");
            } else {
                LlistaPeticionesServei LlistaPS = new LlistaPeticionesServei(c, nombreUsu, objetoUsu, 100);
                PeticionesServei petS = new PeticionesServei(b, objeto);
                LlistaPS.addS(petS);
                if (llista.nuevoIntercambio(nombreUsu, LlistaPS)) System.out.println("Producto añadido correctamente");
            }
            objetoUsu.setProductTienePeticion(true);
        } else {                                                  // Com hi ha ja peticions pendents, introduim la nova peticio a la llista que li pertoca
            if (objetoUsu.getTipus_product().equals("Fisico")) {
                PeticioFisic petF = new PeticioFisic(b, objeto);
                User[] Llista = llista.getLlista();
                int pos = llista.posUsuario(nombreUsu);
                LlistaPeticiones LlistaP = Llista[pos].getIntercamb();
                int i = LlistaP.trobatPeticion(objetoUsu);
                Peticiones pet = LlistaP.getLlistaPos(i);
                ((LlistaPeticionesFisic) pet).addF(petF);
            } else {
                PeticionesServei petF = new PeticionesServei(b, objeto);
                User[] Llista = llista.getLlista();
                int pos = llista.posUsuario(nombreUsu);
                LlistaPeticiones LlistaP = Llista[pos].getIntercamb();
                int i = LlistaP.trobatPeticion(objetoUsu);
                Peticiones pet = LlistaP.getLlistaPos(i);
                ((LlistaPeticionesServei) pet).addS(petF);
            }
        }
    }

    private static void menosIntercamb(LlistaUser llista) {
        System.out.println("Introduce la informacion del usuario que le quieres quitar la peticion.");
        String a = pedirAlias(llista);
        int pos;
        do {
            Productes i = pedirProducto(llista, a);
            pos = llista.getPosicionPeticionLlista(a, i);
        }while(pos==-1);
        Peticiones p = llista.getPeticionLlista(a, pos);
        if (llista.quitaIntercambio(a, p) == 0) System.out.println("No se ha encontrado el intercambio");
        if (llista.quitaIntercambio(a, p) == 1) System.out.println("Intercambio quitado correctamente");
    }

    private static String pedirObjeto(){
        String objeto;
        System.out.println("¿Que objeto o servicio quieres intercambiar? ");
        objeto= teclat.nextLine();
        return objeto;
    }

    private static void ContestarUsuario(LlistaUser llista) {
        Scanner teclat = new Scanner(System.in);
        String a = pedirAlias(llista);
        int pos = llista.posUsuario(a);
        User[] Llista = llista.getLlista();
        LlistaPeticiones LlistaP = Llista[pos].getIntercamb();
        System.out.println("¿Que producto quieres contestar a una peticion?");
        Productes prod = pedirProducto(llista, a);
        int i = LlistaP.trobatPeticion(prod), j = 0;
        boolean contestatA = false;
        Peticiones[] llistatP = LlistaP.getLlistaPeticions();
        if (prod.getTipus_product().equals("Fisic")) {
            PeticioFisic[] petF = ((LlistaPeticionesFisic) llistatP[i]).getLlistaF();
            while (!contestatA && j < petF.length) {
                System.out.println("¿Te gusta el intercambio: " + petF[j].getPeticio_interc() + " ? (Si o No)");
                String respuesta = teclat.nextLine();
                if (respuesta.equals("Si")) {
                    contestatA = true;
                    System.out.println("¿Cual es la puntuacion del intercambio a tu parecer?");
                    int nota = teclat.nextInt();
                    petF[j].valoracioFU(nota);
                }
                if (((LlistaPeticionesFisic) llistatP[i]).contestarF(petF[j], contestatA))
                    System.out.println("La respuesta ha sido introducida con exito");
                else System.out.println("La respuesta no ha sido introducida con exito");
                j++;
            }
        } else {
            PeticionesServei[] petS = ((LlistaPeticionesServei) llistatP[i]).getLlistaS();
            while (!contestatA && j < petS.length) {
                System.out.println("¿Te gusta el intercambio: " + petS[j].getPeticio_intercS() + " ? (Si o No)");
                String respuesta = teclat.nextLine();
                if (respuesta.equals("Si")) {
                    contestatA = true;
                    System.out.println("¿Cual es la puntuacion del intercambio a tu parecer?");
                    int nota = teclat.nextInt();
                    petS[j].valoracioFU(nota);
                }
                ((LlistaPeticionesServei) llistatP[i]).contestarS(petS[j], contestatA);
                j++;
            }
        }
    }

    private static void ContestarCliente(LlistaUser llista){
        Scanner teclat = new Scanner(System.in);
        String a = pedirAlias(llista);
        System.out.println("¿Cual es el usuario que enviaste una propuesta?");
        String u = pedirAlias(llista);
        int pos = llista.posUsuario(u);
        User[] Llista = llista.getLlista();
        LlistaPeticiones LlistaP = Llista[pos].getIntercamb();
        System.out.println("¿Que producto solicitaste una peticion?");
        Productes prod = pedirProducto(llista, u);
        int i = LlistaP.trobatPeticion(prod);
        Peticiones[] llistatP = LlistaP.getLlistaPeticions();
        if (prod.getTipus_product().equals("Fisic")) {
            PeticioFisic[] petF = ((LlistaPeticionesFisic) llistatP[i]).getLlistaF();
            int posC=((LlistaPeticionesFisic) llistatP[i]).buscarPeticio(a);
            if(posC>-1){
                if(petF[posC].getPeticioAoD()) {
                    System.out.println("¿Cual es tu valoracion del intercambio?");
                    int nota= teclat.nextInt();
                    petF[posC].valoracioFC(nota);
                }
            }
        }else{
            PeticionesServei[] petS = ((LlistaPeticionesServei) llistatP[i]).getLlistaS();
            int posC=((LlistaPeticionesServei) llistatP[i]).buscarPeticio(a);
            if(posC>-1){
                if(petS[posC].getPeticioAoD()) {
                    System.out.println("¿Cual es tu valoracion del intercambio?");
                    int nota= teclat.nextInt();
                    petS[posC].valoracioFC(nota);
                }
            }
        }
    }
    /** Métode que descativa un servei
     *
     * @param llista1 Llista de productes
     */
    private static void desactivaProducte(LlistaProductes llista1){
        System.out.println("Introdueix el codi del producte que vols desactivar"); //TODO como sabe el usuario el codigo del servicio??
        int code = Integer.parseInt(teclat.nextLine());
        System.out.println("Indica la data en la qual es vol desactivar el servei (dia, mes i any: 00/00/0000): ");
        String fecha2 = teclat.nextLine();
        String[] fechaPartida2 = fecha2.split("/"); //creamos una tabla con las fechas divididas segun "/"
        Data data2 = new Data (Integer.parseInt(fechaPartida2[0]), Integer.parseInt(fechaPartida2[1]), Integer.parseInt(fechaPartida2[2]));
        if(!llista1.desactivaProducte(code,data2))
            System.out.println("no s'ha trobat el servei que es vol eliminar");
    }
    /** Método que muestra los servicios activos
     * @param llista1 Llista de productes
     */
    private static void ServeisActius (LlistaProductes llista1){
       System.out.println(llista1.mostraServeisActius()); //lista con todos los servicios activos (estat=true)
    }
    /**
     * Método que indica cuál es el servicio con mas intercambios
     * @param llista1 Llista de productes
     */
    private static void ServeiMesIntercanvis(LlistaProductes llista1){
        System.out.println(llista1.mesIntercanvis()) ; //de forma toSting así vemos los datos del servicio(incluio el num de intercambios)
    }
    private static void MostraProdActius(LlistaProductes llista1){
        System.out.print(llista1.mostraProductesActius());
    }
}