package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Glavna {

    public static void main(String[] args) {

        Scanner skener = new Scanner(System.in);

        Integer brojKorisnika = unesiInteger(skener, "Unesite broj korisnika koji želite unijeti: ");
        Korisnik[] korisnici = new Korisnik[brojKorisnika];

        for (int i = 0; i < brojKorisnika; i++) {
            Korisnik korisnik = unesiKorisnika(skener, i);
            korisnici[i] = korisnik;
        }

        Integer brojKategorija = unesiInteger(skener, "Unesite broj kategorija koji želite unijeti: ");
        Kategorija[] kategorije = new Kategorija[brojKategorija];

        for (int i = 0; i < brojKategorija; i++) {
            Kategorija kategorija = unesiKategoriju(skener, i);
            kategorije[i] = kategorija;
        }

        Integer brojArtikalaZaProdaju = unesiInteger(skener, "Unesite broj artikala koji su aktivno na prodaju: ");
        Prodaja[] prodajaArtikala = new Prodaja[brojArtikalaZaProdaju];

        for (int i = 0; i < brojArtikalaZaProdaju; i++) {
            Prodaja prodaja = unesiProdajuArtikla(skener, korisnici, kategorije);
            prodajaArtikala[i] = prodaja;
        }

        ispisRezultata(prodajaArtikala);

        skener.close();
    }

    private static void ispisRezultata(Prodaja[] prodajaArtikala) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        System.out.println("Trenutno su oglasi na prodaju:");
        System.out.println("-----------------------");
        for (int i = 0; i < prodajaArtikala.length; i++) {
            Artikl artikl = prodajaArtikala[i].getArtikl();
            System.out.println(artikl.tekstOglasa());
            System.out.println("Datum objave: " + prodajaArtikala[i].getDatumObjave().format(formatter));
            Korisnik korisnik = prodajaArtikala[i].getKorisnik();
            System.out.println(korisnik.dohvatiKontakt());
            System.out.println("-----------------------");
        }
    }

    private static Prodaja unesiProdajuArtikla(Scanner skener, Korisnik[] korisnici, Kategorija[] kategorije) {

        System.out.println("Odaberite korisnika:");
        for (int i = 0; i < korisnici.length; i++) {
            System.out.println((i + 1) + ". " + korisnici[i].dohvatiKontakt());
        }

        Integer odabraniKorisnik = odabirZapisa(skener, korisnici.length);

        System.out.println("Odaberite kategoriju:");
        for (int i = 0; i < kategorije.length; i++) {
            System.out.println((i + 1) + ". " + kategorije[i].getNaziv());
        }

        Integer odabranaKategorija = odabirZapisa(skener, kategorije.length);

        Artikl[] artikli = kategorije[odabranaKategorija - 1].getArtikli();

        System.out.println("Odaberite artikl:");
        for (int i = 0; i < artikli.length; i++) {
            System.out.println((i + 1) + ". " + artikli[i].getNaslov());
        }

        Integer odabraniArtikl = odabirZapisa(skener, artikli.length);

        LocalDate datumObjave = LocalDate.now();

        return new Prodaja(artikli[odabraniArtikl - 1], korisnici[odabraniKorisnik - 1], datumObjave);
    }

    private static Korisnik unesiKorisnika(Scanner skener, int i) {
        System.out.println("Unesite tip " + (i + 1) + ". korisnika:");
        System.out.println("1. Privatni");
        System.out.println("2. Poslovni");
        Integer odabraniTipKorisnika= odabirZapisa(skener, 2);

        if (odabraniTipKorisnika==1)
            return unesiPrivatnogKorisnika(skener, i);
        else
            return unesiPoslovnogKorisnika(skener, i);
    }

    private static PrivatniKorisnik unesiPrivatnogKorisnika(Scanner skener, int i) {
        i++;

        String ime = unesiString(skener, "Unesite ime " + i + ". osobe: ");

        String prezime = unesiString(skener, "Unesite prezime " + i + ". osobe: ");

        String email = unesiString(skener, "Unesite e-Mail " + i + ". osobe: ");

        String telefon = unesiString(skener, "Unesite telefon " + i + ". osobe: ");

        return new PrivatniKorisnik( email, telefon, ime, prezime);
    }

    private static PoslovniKorisnik unesiPoslovnogKorisnika(Scanner skener, int i) {
        i++;

        String naziv = unesiString(skener, "Unesite naziv " + i + ". tvrtke: ");

        String email = unesiString(skener, "Unesite e-Mail " + i + ". tvrtke: ");

        String web= unesiString(skener, "Unesite web " + i + ". tvrtke: ");

        String telefon = unesiString(skener, "Unesite telefon " + i + ". tvrtke: ");

        return new PoslovniKorisnik(email, telefon, naziv, web);
    }

    private static Kategorija unesiKategoriju(Scanner skener, int i) {
        i++;

        String nazivKategorije = unesiString(skener, "Unesite naziv " + i + ". kategorije: ");

        Integer brojArtikala = unesiInteger(skener, "Unesite broj artikala koji želite unijeti za unesenu kategoriju: ");

        Artikl[] artikli = new Artikl[brojArtikala];

        for (int j = 0; j < brojArtikala; j++) {
            Artikl artikl = unesiArtikl(skener, j);
            artikli[j] = artikl;
        }

        return new Kategorija(nazivKategorije, artikli);
    }

    private static Artikl unesiArtikl(Scanner skener, int i) {
        System.out.println("Unesite tip " + (i + 1) + ". artikla:");
        System.out.println("1. Usluga");
        System.out.println("2. Automobil");
        Integer odabraniTipArtikla= odabirZapisa(skener, 2);

        if (odabraniTipArtikla==1)
            return unesiUslugu(skener, i);
        else
            return unesiAutomobil(skener, i);
    }

    private static Usluga unesiUslugu(Scanner skener, int i) {
        i++;

        String naslov = unesiString(skener, "Unesite naslov " + i + ". oglasa usluge: ");

        String opis = unesiString(skener, "Unesite opis " + i + ". oglasa usluge: ");

        BigDecimal cijena = unesiBigDecimal(skener, "Unesite cijenu " + i + ". oglasa usluge: ");

        return new Usluga(naslov, opis, cijena);
    }

    private static Automobil unesiAutomobil(Scanner skener, int i) {
        i++;

        String naslov = unesiString(skener, "Unesite naslov " + i + ". oglasa automobila: ");

        String opis = unesiString(skener, "Unesite opis " + i + ". oglasa automobila: ");

        BigDecimal snagaKs = unesiBigDecimal(skener, "Unesite snagu " + i + ". (u ks) oglasa automobila: ");

        BigDecimal cijena = unesiBigDecimal(skener, "Unesite cijenu " + i + ". oglasa automobila: ");

        return new Automobil(naslov, opis, cijena, snagaKs);
    }

    private static Integer unesiInteger(Scanner skener, String poruka) {
        Integer unos = 0;
        boolean ispravanUnos = true;

        do {
            System.out.print(poruka);
            unos = skener.nextInt();
            skener.nextLine();

            ispravanUnos = true;
            if (unos <= 0){
                System.out.println("Neispravan unos. Ponovite unos!");
                ispravanUnos = false;
            }
        }
        while (ispravanUnos == false);

        return unos;
    }

    private static BigDecimal unesiBigDecimal(Scanner skener, String poruka) {
        BigDecimal unos = BigDecimal.ZERO;
        boolean ispravanUnos = true;

        do {
            System.out.print(poruka);
            unos = skener.nextBigDecimal();
            skener.nextLine();

            ispravanUnos = true;
            if (unos.compareTo(BigDecimal.ZERO)<=0)
            {
                System.out.println("Neispravan unos. Ponovite unos!");
                ispravanUnos = false;
            }
        }
        while (ispravanUnos == false);

        return unos;
    }

    private static String unesiString(Scanner skener, String poruka) {
        String unos;
        boolean ispravanUnos = true;

        do {
            System.out.print(poruka);
            unos = skener.nextLine();

            ispravanUnos = true;
            if (unos.length() == 0)
            {
                System.out.println("Neispravan unos. Ponovite unos!");
                ispravanUnos = false;
            }
        }
        while (ispravanUnos == false);

        return unos;
    }

    private static Integer odabirZapisa(Scanner skener, int brojZapisa) {

        boolean ispravanUnos = true;
        Integer odabraniZapis = 0;

        do {
            System.out.println("Odabir >>");
            odabraniZapis = skener.nextInt();
            skener.nextLine();

            ispravanUnos = true;
            if (odabraniZapis < 1 || odabraniZapis > brojZapisa)
            {
                System.out.println("Neispravan unos. Ponovite unos!");
                ispravanUnos = false;
            }
        }
        while (ispravanUnos == false);

        return odabraniZapis;
    }
}
