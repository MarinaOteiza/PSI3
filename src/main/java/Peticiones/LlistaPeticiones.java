package Peticiones;
import Productes.*;


//ÀITOR OLIVARES PERUCHO

public class LlistaPeticiones {
    private Peticiones LlistaP[];
    private int numllist;

    public LlistaPeticiones(int num){
        LlistaP= new Peticiones[num];
        numllist=0;
    }

    public void addP(Peticiones p1){
        if(numllist<=LlistaP.length){
            LlistaP[numllist]=p1.copia();
            numllist++;
        }
    }

    public void subP(Peticiones p1){
        boolean trobat=false;
        int j=0, pos=0;
        if(numllist>0){
            while(!trobat && j<numllist) {
                if (LlistaP[j].equals(p1)) {
                    trobat = true;
                    pos = j;
                }
                j++;
            }
            if(trobat){
                int i=0;
                for(i=pos; i<numllist-1; i++){
                    LlistaP[i]=LlistaP[i+1];
                }
                i++;
                LlistaP[i]=LlistaP[i+1];
                numllist--;
            }

        }
    }

    public int getNumllist(){
        return numllist;
    }

    public Peticiones getLlistaPos(int i){
        return LlistaP[i];
    }

    public Peticiones[] getLlistaPeticions(){
        return LlistaP;
    }

    public Peticiones copia(LlistaPeticiones llist, int i){
        return this.LlistaP[i]= llist.LlistaP[i];
    }

    public LlistaPeticiones copiaL(){
        LlistaPeticiones llista= new LlistaPeticiones(100);
        for(int i=0; i<this.numllist; i++){
            llista.LlistaP[i]=this.LlistaP[i];
        }
        llista.numllist=this.numllist;
        return llista;
    }

    public int trobatPeticion(Peticiones p){
        boolean trobat=false;
        int i=0;
        while (!trobat && i<numllist){
            if(LlistaP[i].equals(p)){
                return i;
            }
            i++;
        }
        return -1;
    }

    public int trobatPeticion(Productes p){
        boolean trobat=false;
        int i=0;
        while (!trobat && i<numllist){
            if(LlistaP[i].getProducte().equals(p.getDescrip())){
                return i;
            }
            i++;
        }
        return -1;
    }
}
