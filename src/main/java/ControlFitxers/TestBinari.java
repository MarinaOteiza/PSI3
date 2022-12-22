package ControlFitxers;

import dataUsr.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestBinari {
    @SuppressWarnings("unused")
    public void escribirBinario() throws IOException {
        try {
            int i = 15;
            FileOutputStream archivo = new FileOutputStream("usuari.bin");
            //OutputStream es una clase abstracta que representa la salida de escritura. //

            ObjectOutputStream escritura = new ObjectOutputStream(archivo);

            escritura.writeObject(new usuari(15, "sonia"));
            System.out.println("añadido");
            escritura.close();


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(System.out);
        }


    }

  public User LeerBinario(){
        User u = new User();
        


  }
  // el archivo binario contiene



}
