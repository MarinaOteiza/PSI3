package appUsr;

import dataUsr.*;
import java.util.Scanner;

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
        System.out.println("6-Sumar intercambio");
        System.out.println("7-Quitar producto ");
        System.out.println("8-Quitar intercambio");
        System.out.println("9-Salir ");
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
        String p;
        String a=pedirAlias(llista);
        System.out.println("Que producto quieres añadir? ");
        p=teclat.nextLine();
        if(llista.nuevoProducto(a, p)) System.out.println("Producto añadido correctamente");
        else System.out.println("El producto no se ha podido añadir");
    }
    private static void sumaIntercamb(LlistaUser llista) {
        String i;
        String a=pedirAlias(llista);
        System.out.println("Que intercambio quieres añadir? ");
        i=teclat.nextLine();
        if(llista.nuevoIntercambio(a, i)) System.out.println("Producto añadido correctamente");
        else System.out.println("El intercambio no se ha podido añadir");
    }
    private static void menosProd(LlistaUser llista) {
        String p;
        String a=pedirAlias(llista);
        System.out.println("Que intercambio quieres quitar? ");
        p=teclat.nextLine();
        if(llista.quitaProducto(a, p)==0) System.out.println("No se ha encontrado el producto");
        if(llista.quitaProducto(a, p)==1) System.out.println("Producto quitado correctamente");
    }
    private static void menosIntercamb(LlistaUser llista) {
        String i;
        String a=pedirAlias(llista);
        System.out.println("Que intercambio quieres quitar? ");
        i=teclat.nextLine();
        if(llista.quitaIntercambio(a, i)==0) System.out.println("No se ha encontrado el intercambio");
        if(llista.quitaIntercambio(a, i)==1) System.out.println("Intercambio quitado correctamente");
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
}