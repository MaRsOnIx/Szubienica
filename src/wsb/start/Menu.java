package wsb.start;

import java.io.IOException;
import java.util.Scanner;

public class Menu {


    public static void main(String[] args) {
        // It's a project for my students
        Menu menu = new Menu();
        menu.start();

    }

    private void start(){
        Szubienica szubienica = null;
        try{
            szubienica = new Szubienica();
        }catch (IOException e){
            System.out.println(":(");
            System.exit(0);
            return;
        }


        System.out.println("Włączyłeś właśnie Szubienice, podaj jakąś literkę");

        System.out.println("===================");
        System.out.println("     Szubienica");
        System.out.println(szubienica.pobierzMaske());
        System.out.println("===================");
        Scanner sc = new Scanner(System.in);

        boolean czyZgadl = false;
        while (!czyZgadl) {

            if(!szubienica.czyMaProby()){
                System.out.println("Przegrałeś!");
                return;
            }
            //szubienica.pokazLiterki();
            String haslo = sc.nextLine();

            if(haslo.length()==1 && szubienica.zgdnijZnak( haslo.charAt(0) ) ){
                System.out.println(szubienica.pobierzMaske());
                if(!szubienica.pobierzMaske().contains("X")) czyZgadl=true;
                continue;
            }

            if (szubienica.sprobujZgadnac(haslo)) {
                czyZgadl=true;
            } else {
                System.out.println("Nie zgadłeś");
            }
        }
        System.out.println("Zgadłeś");




    }

}
