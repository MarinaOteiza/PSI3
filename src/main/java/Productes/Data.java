package Productes;

public class Data {
    private int dia;
    private int mes;
    private int any;

    /**
     * Constructor per defecte, sense parametres inicialitza a una data de
     * referencia
     */
    public Data() {
        this.dia = 1;
        this.mes = 1;
        this.any = 2000;
    }

    /**
     * Constructor que rep la data per parametre
     * Ha de validar que la data es correcta. Si rep una data incorrecta inicialitza la instancia
     * amb la data de referencia.
     *
     * @param dia
     * @param mes
     * @param any
     */
    public Data(int dia, int mes, int any) {
        if (esDataCorrecta(dia, mes, any)) { // ens asegurem que hi ha una data valida
            this.dia = dia;
            this.mes = mes;
            this.any = any;
        } else { // posem la data de referencia com a senyal d'error
            this.dia = 1;
            this.mes = 1;
            this.any = 2000;
        }
    }

    /**
     * Getter
     *
     * @return dia de la data
     */
    public int getDia() {
        return dia;
    }

    /**
     * Getter
     *
     * @return mes de la data
     */
    public int getMes() {
        return mes;
    }

    /**
     * Getter
     *
     * @return any de la data
     */
    public int getAny() {
        return any;
    }

    /**
     * Setter conjunt per a poder validar la correctesa de la data rebuda.
     * Nomes es fa la modificacio de la data si que es rep per parametre es correcte.
     *
     * @param dia
     * @param mes
     * @param any
     */
    public void setData(int dia, int mes, int any) {
        if (esDataCorrecta(dia, mes, any)) { // ens asegurem que hi ha una data valida
            this.dia = dia;
            this.mes = mes;
            this.any = any;
        }
    }

    private static boolean esDataCorrecta(int dia, int mes, int any) {
        boolean hoEs = true;
        if (dia < 1 || dia > 31) { // dia incorrecte
            hoEs = false;
        }
        if (mes < 1 || mes > 12) { // mes incorrecte
            hoEs = false;
        }
        if (dia > diesMes(mes, any)) { // dia del mes incorrecte
            hoEs = false;
        }
        return hoEs;
    }

    private static int diesMes(int mes, int any) { // per saber quants dies te un mes d'un any
        int diesMes;
        if (mes == 2) {
            if (esAnyTraspas(any)) {
                diesMes = 29;
            } else {
                diesMes = 28;
            }
        } else {
            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                diesMes = 30;
            } else {
                diesMes = 31;
            }
        }
        return diesMes;
    }

    private static boolean esAnyTraspas(int any) { // ens estalviem crear una instancia de data
        if ((any % 4 == 0) && ((any % 100 != 0) || (any % 400 == 0))) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return ("DATA => dia " + dia + " mes " + mes + " any " + any);
    }

    public Data copia() {
        return new Data(dia, mes, any);
    }
}
