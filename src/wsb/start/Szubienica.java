package wsb.start;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Szubienica {

    private static List<String> hasla;

    private static void pobierzHaslaZPliku() throws IOException {

        hasla = Files.readAllLines(Paths.get("hasla.txt"));

    }


    private int iloscProb = 10;
    private String wylosowaneHaslo;
    private String maska;
    private List<Character> literki;

    private List<String> haslaPodanePrzezUzytkownika;
    private Random random;


    public Szubienica() throws IOException {
        pobierzHaslaZPliku();
        this.random=new Random();
        this.haslaPodanePrzezUzytkownika = new ArrayList<>();
        this.literki=new ArrayList<>();
        this.wylosowaneHaslo=hasla.get(random.nextInt(hasla.size()));
        this.ustawMaske();
    }

    private void ustawMaske(){

        int ileJestZnakow = wylosowaneHaslo.length();

        maska="";

        for(int i = 0 ; i < ileJestZnakow ; i++){
            maska+="X"; // W przyszłości zamienimy to na StringBuildera :)
        }


    }

    public void pokazLiterki(){
        System.out.println("Literki, które próbowałeś zgadnąć: ");
        for (char c : literki) {
            System.out.print(c+", ");
        }
    }

    public boolean zgdnijZnak(char znak){
        literki.add(znak);
        if(!wylosowaneHaslo.contains(znak+"") || maska.contains(znak+"")){
            --iloscProb;
            return false;
        }

        // java
        List<Integer> indeksy = new ArrayList<>(); // 1, 3
        char[] znaki = wylosowaneHaslo.toCharArray(); // { 'j', 'a', 'v', 'a' }

        for(int i = 0 ; i < znaki.length ; i++){
            if(znaki[i] == znak) indeksy.add(i);
        }

        char[] znakiMaska = maska.toCharArray(); // { 'X', 'X', 'X', 'X' }
        for(int index : indeksy){ // 1, 3
            znakiMaska[index] = znak; // { 'X', 'a', 'X', 'a' }
        }

        maska = new String(znakiMaska); // XaXa


        return true;



    }

    public String pobierzMaske(){
        return this.maska;
    }

    public String zwrocHaslo(){
        return this.wylosowaneHaslo;
    }

    public boolean czyMaProby(){
        return iloscProb>0;
    }



    public boolean sprobujZgadnac(String haslo){
        boolean b = this.wylosowaneHaslo.toLowerCase().trim().equals(haslo.toLowerCase().trim());
        if(!b) {
            haslaPodanePrzezUzytkownika.add(haslo);
            --iloscProb;
        }
        return b;
    }


}
