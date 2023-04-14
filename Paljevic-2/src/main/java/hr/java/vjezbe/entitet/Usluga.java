package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class Usluga extends Artikl {

    public Usluga(String naslov, String opis, BigDecimal cijena) {
        super(naslov, opis, cijena);
    }

    @Override
    public String tekstOglasa() {
        return "Naslov usluge: " + super.getNaslov() + System.lineSeparator() +
                "Opis usluge: " + super.getOpis() + System.lineSeparator() +
                "Cijena usluge: " + super.getCijena();
    }
}
