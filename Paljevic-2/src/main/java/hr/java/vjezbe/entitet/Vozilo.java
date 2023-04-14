package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;

public interface Vozilo {

    public static final int GRUPA_OSIGURANJA_PRVA = 1;
    public static final int GRUPA_OSIGURANJA_DRUGA = 2;
    public static final int GRUPA_OSIGURANJA_TRECA =3;
    public static final int GRUPA_OSIGURANJA_CETVRTA =4;
    public static final int GRUPA_OSIGURANJA_PETA =5;

    default public BigDecimal izracunajKw(BigDecimal snagaKs) {
        return snagaKs.multiply(new BigDecimal(0.745));
    }

    int izracunajGrupuOsiguranja();

    default public BigDecimal izracunajCijenuOsiguranja() {
        BigDecimal osnovnaCijena = new BigDecimal(2000);
        int grupaOsiguranja = izracunajGrupuOsiguranja();
        Double uvecanjeCijene = 1.0;

        switch (grupaOsiguranja) {
            case GRUPA_OSIGURANJA_PRVA:
                uvecanjeCijene = 1.2;
                break;
            case GRUPA_OSIGURANJA_DRUGA:
                uvecanjeCijene = 1.4;
                break;
            case GRUPA_OSIGURANJA_TRECA:
                uvecanjeCijene = 1.6;
                break;
            case GRUPA_OSIGURANJA_CETVRTA:
                uvecanjeCijene = 1.8;
                break;
            case GRUPA_OSIGURANJA_PETA:
                uvecanjeCijene = 2.0;
                break;
            default:
                uvecanjeCijene = 1.0;
                break;
        }

        return osnovnaCijena.multiply(new BigDecimal(uvecanjeCijene));
    }
}
