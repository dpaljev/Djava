package hr.java.vjezbe.entitet;

public final class PrivatniKorisnik extends Korisnik implements Recenzent {
    private String ime;
    private String prezime;

    public PrivatniKorisnik(String email, String telefon, String ime, String prezime) {
        super(email, telefon);
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String dohvatiKontakt() {
        return "Osobni podaci prodavatelja: " + this.getIme() + " " +
                this.getPrezime() +
                ", mail: " + super.getEmail() +
                ", tel: " + super.getTelefon();
    }

    @Override
    public void ostaviRecenziju(Artikl artikl, Recenzija recenzija) {
        artikl.recenzije.add(recenzija);
    }
}
