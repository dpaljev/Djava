package hr.java.vjezbe.entitet;

public sealed interface Recenzent permits PrivatniKorisnik, PoslovniKorisnik {
    void ostaviRecenziju (Artikl artikl, Recenzija recenzija);
}
