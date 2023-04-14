package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class Automobil extends Artikl implements Vozilo {
    private BigDecimal snagaKs;

    public Automobil(String naslov, String opis, BigDecimal cijena, BigDecimal snagaKs) {
        super(naslov, opis, cijena);
        this.snagaKs = snagaKs;
    }

    public BigDecimal getSnagaKs() {
        return snagaKs;
    }

    public void setSnagaKs(BigDecimal snagaKs) {
        this.snagaKs = snagaKs;
    }

    @Override
    public String tekstOglasa() {
        return "Naslov automobila: " + super.getNaslov() + System.lineSeparator() +
                "Opis automobila: " + super.getOpis() + System.lineSeparator() +
                "Snaga automobila: " + this.getSnagaKs() + System.lineSeparator() +
                "Izračun osiguranja automobila: " + this.izracunajCijenuOsiguranja() + System.lineSeparator() +
                "Cijena automobila: " + super.getCijena();
    }

    @Override
    public int izracunajGrupuOsiguranja() {
        int grupaOsiguranja = GRUPA_OSIGURANJA_PRVA;

        if (snagaKs.compareTo(new BigDecimal(50)) <= 0)
            grupaOsiguranja = GRUPA_OSIGURANJA_PRVA;
        else if (snagaKs.compareTo(new BigDecimal(50)) > 0 && snagaKs.compareTo(new BigDecimal(70)) <= 0)
            grupaOsiguranja = GRUPA_OSIGURANJA_DRUGA;
        else if (snagaKs.compareTo(new BigDecimal(70)) > 0 && snagaKs.compareTo(new BigDecimal(90)) <= 0)
            grupaOsiguranja = GRUPA_OSIGURANJA_TRECA;
        else if (snagaKs.compareTo(new BigDecimal(90)) > 0 && snagaKs.compareTo(new BigDecimal(120)) <= 0)
            grupaOsiguranja = GRUPA_OSIGURANJA_CETVRTA;
        else
            grupaOsiguranja = GRUPA_OSIGURANJA_PETA;

        return grupaOsiguranja;
    }
}
