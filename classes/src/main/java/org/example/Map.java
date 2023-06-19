package org.example;



import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import java.io.PrintWriter;
import java.io.File;

/**
 * Klasa przeprowadza symulację
 */
public class Map extends JFrame{
    /**rozmiar mapy */
    public static int size = 600;
    /** granice boczne */
    public static int border = 10;
    /** granica góra, niebo nad wodą*/
    public static int upBorder = 100;
    /** granica dolna, piasek */
    public static int downBorder=50;
    /** czas symulacji */
    public static int time;
    /** tabela przechowująca ryby */
    static ArrayList<Fish> tableOfFish;

    /**
     * Konstruktor, ustawia rozmiar okienka
     */
    Map() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(size, size);
        this.setVisible(true);
        setLayout(null);
    }

    /**
     *  tworzenie czystej mapy
     * @param g odpowiada za rysowanie
     */
    public void clean(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0,upBorder,size,size);
        g.setColor(Color.CYAN);
        g.fillRect(0,0, size, upBorder);
        g.setColor(Color.YELLOW);
        g.fillRect(0,size-downBorder,size, downBorder);
    }

    /**
     * Generowanie ryb do tabeli
     * @param green liczba ryb zielonych
     * @param red liczba ryb czerwnonych
     * @param yellow liczba ryb żółtych
     * @param time_ czas trawania symulacji
     */
    public static void setFish(int green, int red, int yellow, int time_) {
        tableOfFish = new ArrayList();
        tableOfFish.add(new Shark());
        for(int i=0; i<red; i++)
            tableOfFish.add(new RedFish());
        for(int i=0; i<green; i++)
            tableOfFish.add(new GreenFish());
        for(int i=0; i<yellow; i++)
            tableOfFish.add(new YellowFish());
        time=time_*20;
    }

    /**
     * Metoda eksportuje dane do pliku .txt
     */
    public static void data_export()
    {
        int red=0, green=0 , yellow=0;
        for (Fish fish : tableOfFish) {

            if (fish instanceof GreenFish) {
                green++;

            } else if (fish instanceof RedFish) {
                red++;

            } else if (fish instanceof YellowFish) {
                yellow++;
            }
        }



            File myObj = new File("data.txt");
             try{
        PrintWriter save = new PrintWriter("data.txt");
        save.println("CZAS TRWANIA SYMULACJI: "+ time/20+" SEKUND");
        save.println("RYB CZERWONYCH: "+ red);
        save.println("RYB ZIELONYCH: "+ green);
        save.println("RYB ZOLTYCH: "+yellow);
        save.println("RYBY ZLOWIONE: "+ Fisherman.fish_caught);
        save.close();
    }catch (Exception e)
             {

             }


    }

    /**
     * Metoda odpowiada za przebieg sumulacji, wywoływane są w niej wszystkie metody
     * @param g the specified Graphics window
     */
    @Override
    public void paint(Graphics g) {

    Fisherman fisherman = new Fisherman();

        for(int i=0; i<time; i++) {

            /** tworzenie czystej mapy */
            clean(g);

            /** pływanie ryb */
            for(int j=0; j<tableOfFish.size(); j++)
                tableOfFish.get(j).swim(g);

            /** rozmnażanie ryb */
            for(int j=0; j<tableOfFish.size(); j++) {
                if(tableOfFish.get(j) instanceof YellowFish)
                    tableOfFish.get(j).reproduct(tableOfFish.get(j));
                for(int k=0; k<tableOfFish.size(); k++)
                    if(j!=k && tableOfFish.get(j).getClass()==tableOfFish.get(k).getClass())
                        tableOfFish.get(j).reproduct(tableOfFish.get(k));
            }

            /** atak ryb */
            for(int j=0; j<tableOfFish.size(); j++)
                for(int k=0; k<tableOfFish.size(); k++)
                    if(j!=k && tableOfFish.get(j).getClass()!=tableOfFish.get(k).getClass())
                        tableOfFish.get(j).attack(tableOfFish.get(k));

            /** smierć ryb */
            for(int j=0; j<tableOfFish.size(); j++)
                if(tableOfFish.get(j).die(Map.tableOfFish)==1)
                    j--;

            /** pływanie rybaka*/
            fisherman.swim(g);
            /** łowienie ryb */
            fisherman.fishing(g, Map.tableOfFish);


            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
        data_export();

    }
}
