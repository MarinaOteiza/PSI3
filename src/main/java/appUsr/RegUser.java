package appUsr;

import dataUsr.*;
import java.util.Scanner;
import Peticiones.*;
import Productes.*;

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
                    menosProd(llista);
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
        System.out.println("2-Ver productos ");
        System.out.println("2-Ver intercambios ");
        System.out.println("5-Sumar producto ");
        System.out.println("6-Proponer intercambio");
        System.out.println("7-Quitar producto ");
        System.out.println("8-Quitar intercambio");
        System.out.println("9-Responder intercambios");
        System.out.println("10-Valoración del intercambio");
        System.out.println("11-Salir ");
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
        p=teclat.nextLine();
        if(llista.nuevoProducto(a, p)) System.out.println("Producto añadido correctamente");
        else System.out.println("El producto no se ha podido añadir");
    }

    private static void sumaFisic(LlistaUser llista, String nom, String descripcion) {
        int amplada, alcada, fons, pes;
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
        //TODO: comprobar que se haya guardado el producto correctamente y avisar al usuario si se ha guardado bien o no.
        // en el caso de que se haya guardado correctamente, avisarle del código que tiene su producto
    }
    private static void sumaServei(LlistaUser llista, String nom, String descripcion){
        System.out.println("¿Hasta cuando estara disponible el servicio?");
        //TODO: pillar datos de fecha hasta la que se ofrece el servicio y controlar las excepciones necesarias
        //TODO: Comprobar que se haya guardado el producto correctamente y avisar al usuario si se ha guardado bien o no.
        // en el caso de que se haya guardado correctamente, avisarle del código que tiene su producto
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
    private static void menosProd(LlistaUser llista) {
        String p;
        String a=pedirAlias(llista);
        System.out.println("Que intercambio quieres quitar? ");
        p=teclat.nextLine();
        if(llista.quitaProducto(a, p)==0) System.out.println("No se ha encontrado el producto");
        if(llista.quitaProducto(a, p)==1) System.out.println("Producto quitado correctamente");
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
}