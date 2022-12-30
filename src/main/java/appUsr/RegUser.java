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
public class RegUser {
    static Scanner teclat = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 10; //hay que iniciarlo, asi que ponemos un valor cualquiera
        boolean exit = false;
        LlistaUser llista = new LlistaUser(100);
        LlistaProductes llista1 = new LlistaProductes(500); //500 como max elem

        do {
            menu();
            try {
                opcion = Integer.parseInt(teclat.nextLine());
                if (opcion < 1 || opcion > 13) throw new IndexOutOfBoundsException();
            } catch (NumberFormatException e) {
                System.out.println("Introduce un valor numérico \nERROR: " + e + "\n\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Ha introducido un valor fuera del rango (1-9) \nERROR:  " + e + "\n\n");
            }

            switch (opcion) {
                case 1:
                    nuevoProd(llista, 0);
                    break;
                case 2:
                    nuevoProd(llista, 1);
                    break;
                case 3:
                    nuevaPeticion(llista);
                    break;
                case 4:
                    responderPeticiones(llista);
                    responderPeticionesCliente(llista);
                    break;
                case 5:
                    registro(llista);
                    break;
                case 6:
                    eliminaBien(llista);
                    break;
                case 7:
                    desactivaServicio(llista);
                    break;
                case 8:
                    peticionesPendintes(llista);
                    break;
                case 9:
                    peticionesAceptadas(llista);
                    break;
                case 10:
                    peticionesRechazadas(llista);
                    break;
                case 11:
                    muestraValoracionesUsers();
                    break;
                case 12:
                    serviciosMasIntercamb();
                    break;
                case 13:
                    System.out.println("Has salido");
                    exit = true;
                    break;
            }
        } while (!exit);
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
    //TODO: si hay tirmpo, poner una opcion mas para que el usuario pueda ver los codigos de los productos que ofrece
    //nuevoProd: alias, nombre, descripcion, codigo, data1
    private static void nuevoProd(LlistaUser llista, int valor) {
        String nombre, descripcion, codigo;
        int tipus=10;
        String a=pedirAlias(llista);
        System.out.println("Que producto quieres añadir? ");
        nombre=teclat.nextLine();
        System.out.println("Describe brevemente las caracerísticas de tu producto");
        descripcion=teclat.nextLine();
        codigo = llista.getCodigo().cadenaAleatoria();
        System.out.println("Indica la fecha de la oferta del producto (dia, mes y any: 00/00/0000): ");
        String fecha1 = teclat.nextLine();
        String[] fechaPartida1 = fecha1.split("/"); //creamos una tabla con las fechas divididas segun "/"
        Data data1 = new Data (Integer.parseInt(fechaPartida1[0]), Integer.parseInt(fechaPartida1[1]), Integer.parseInt(fechaPartida1[2]));//data1=dataOf (data de la oferta)
        if(valor==0) nuevoServicio(a, llista, nombre, codigo, descripcion, data1);
            else
                nuevoProdFisic(a, llista, nombre, codigo, descripcion, data1);
    }
    private static void nuevoServicio(String alias, LlistaUser llista, String nom, String code, String descripcion, Data data1) {
        System.out.println("Indica la data de disponibilitat final del servei (dia, mes i any: 00/00/0000): ");
        String fecha3 = teclat.nextLine();
        String[] fechaPartida3 = fecha3.split("/"); //creamos una tabla con las fechas divididas segun "/"
        Data data2 = new Data (Integer.parseInt(fechaPartida3[0]), Integer.parseInt(fechaPartida3[1]), Integer.parseInt(fechaPartida3[2]));
        Data data=new Data(); //introduim com data intercanvi 1/01/2000 per defecte
        llista.nouServei(alias, nom, code, descripcion, data1, data2);
    }
    private static void nuevoProdFisic(String alias, LlistaUser llista, String nom, String code, String descripcion, Data data1) {
        double alto, largo, ancho, pes;
        int intercanvis=0;
        //TODO: lanzar excepciones con valores negativos y del tipo String
        System.out.println("Introduce la amplada: ");
        alto = teclat.nextDouble();
        System.out.println("Introduce la alcada: ");
        largo = teclat.nextDouble();
        System.out.println("Introduce el fons: ");
        ancho = teclat.nextDouble();
        System.out.println("Introduce el pes: ");
        pes = teclat.nextDouble();
        llista.nouBe(alias, nom, code, descripcion,data1,ancho, alto, largo, pes);

    }
    private static void nuevaPeticion(LlistaUser llista) {
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
    private static void responderPeticiones(LlistaUser llista) {
        Scanner teclat = new Scanner(System.in);
        String a = pedirAlias(llista);
        int pos = llista.posUsuario(a);
        User[] Llista = llista.getLlista();
        LlistaPeticiones LlistaP = Llista[pos].getIntercamb();
        System.out.println("¿Que producto quieres contestar a una peticion?");
        int i,j=0; Productes prod;
        do {
            prod = pedirProducto(llista, a);
            i = LlistaP.trobatPeticion(prod);
        }while(i==-1);
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
    private static void responderPeticionesCliente(LlistaUser llista){
        Scanner teclat = new Scanner(System.in);
        String a = pedirAlias(llista);
        System.out.println("¿Cual es el usuario que enviaste una propuesta?");
        String u = pedirAlias(llista);
        int pos = llista.posUsuario(u);
        User[] Llista = llista.getLlista();
        LlistaPeticiones LlistaP = Llista[pos].getIntercamb();
        System.out.println("¿Que producto solicitaste una peticion?");
        int i; Productes prod;
        do {
            prod = pedirProducto(llista, u);
            i = LlistaP.trobatPeticion(prod);
        }while(i==-1);
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
    private static void registro(LlistaUser llista) {
        //Pedimos los datos para registrar a un nuevo usuario
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

    private static void eliminaBien(LlistaUser llista) {
        String a = pedirAlias(llista), codeBien=null;
        MostrarProductes(a, llista);
        try{
            System.out.println("Introduce el codigo del bien que quieras eliminar");
            codeBien=teclat.nextLine();
            if(!llista.compProd(a, codeBien)) throw new Exception();
        }catch(Exception e){
            System.out.println("Codigo incorrecto ");
        }
        llista.quitaBien(a, codeBien);
    }
    private static void desactivaServicio(LlistaUser llista) {
        String a = pedirAlias(llista), codeProd=null;
        MostrarProductes(a, llista);
        try{
            System.out.println("Introduce el codigo del servicio que quieras desactivar");
            codeProd=teclat.nextLine();
            if(!llista.compProd(a, codeProd)) throw new Exception();
        }catch(Exception e){
            System.out.println("Codigo incorrecto ");
        }
        System.out.println("Indica la data de desactivacio del servei (dia, mes i any: 00/00/0000): ");
        String fecha3 = teclat.nextLine();
        String[] fechaPartida3 = fecha3.split("/"); //creamos una tabla con las fechas divididas segun "/"
        Data data2 = new Data (Integer.parseInt(fechaPartida3[0]), Integer.parseInt(fechaPartida3[1]), Integer.parseInt(fechaPartida3[2]));
        llista.desactServicio(a, codeProd, data2);
    }
    private static void peticionesPendintes(LlistaUser llistaUser) {
        String nombre= pedirAlias(llistaUser);
        int pos=llistaUser.posUsuario(nombre);
        User[] llista=llistaUser.getLlista();
        llista[pos].getIntercamb().mostrarPeticPendents();
    }
    private static void peticionesAceptadas(LlistaUser llistaUser) {
        String nombre= pedirAlias(llistaUser);
        int pos=llistaUser.posUsuario(nombre);
        User[] llista=llistaUser.getLlista();
        llista[pos].getIntercamb().mostrarPeticDenegats();
    }
    private static void peticionesRechazadas(LlistaUser llistaUser) {
        String nombre= pedirAlias(llistaUser);
        int pos=llistaUser.posUsuario(nombre);
        User[] llista=llistaUser.getLlista();
        llista[pos].getIntercamb().mostrarPeticAcceptats();
    }
    private static void muestraValoracionesUsers() {

    }
    private static void serviciosMasIntercamb() {

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
        //TODO: ARREGLAR PRODUCT!!!!!
        return product;
    }

    private static String pedirObjeto(){
        String objeto;
        System.out.println("¿Que objeto o servicio quieres intercambiar? ");
        objeto= teclat.nextLine();
        return objeto;
    }

    private static void MostrarProductes(String alias, LlistaUser llista){
        System.out.println("Tus productos activos/disponibles son:\n");
        System.out.println(llista.prodActToString(alias));

    }
}