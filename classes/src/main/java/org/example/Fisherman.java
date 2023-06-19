package org.example;


import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import static java.lang.Math.sqrt;

/**
 * Fisherman plywa i łowi ryby
 */
public class Fisherman {

    /** Szybkość płynięcia rybaka    */
    protected int speed = 6;
    /** Współrzędna X, początkowo ustawiana jako środek mapy*/
    protected int coX = Map.size / 2;
    /** Współrzędna Y rybaka, górna granica mapa minus wysokość łódki*/
    protected int coY = Map.upBorder - 10;
    /** Rozmiar łódki rybaka */
    protected int size = 10;
    /** Kierunek poruszania rybaka */
    protected boolean goback = true;
    /** Zatrzyamnie rybaka */
    protected boolean stop = false;
    /** licznik do zatrzyamnia rybaka */
    protected int stopcountdown = 0;
    /** wartośc przy której rybak się zatrzymuje */
    protected int maxcountdown = 70;
    /** wskazuje do jakiej glebokosci zanurzy sie wędka */
    protected int depth;
    /** szybkość zanurzania wędki */
    protected int rodspeed = 20;
    /** aktualna głębokość wędki */
    protected int roddepth = Map.upBorder;
    /** zasięg wędki */
    protected double rodrange = 5;
    /** licznik ryb złapanych */
    static int fish_caught=0;


    /**
     * Metoda przeprowadza pływanie ryb
     * @param g odpowiada za rysowanie
     */
    public void swim(Graphics g) {

        //rysowanie lodki
        g.setColor(Color.ORANGE);
        g.fillRect(coX, coY, size*5, size);
        /**
         * Jeżeli rybak został zatrzymany to się nie będzie poruszać
         */
        if (stop == true)
            return;

        /**
         * Poruszanie się rybaka
         */
        if (coX >= Map.border && coX <= Map.size - 2 * Map.border - size*5) {
            if (goback == true)
                coX += speed;
            else
                coX -= speed;
        } else {
            if (goback == true) {
                goback = false;
                coX -= speed;
            } else {
                goback = true;
                coX += speed;
            }
        }
        /**
         * Zwiększanie licznika gdy rybak się porusza
         */
        stopcountdown++;
        /**
         * Gdy licznik będzie mieć określoną wartość rybak się zatrzymuje
         */
        if (stopcountdown >= maxcountdown) {
            stopcountdown = 0;
            stop = true;
            /**
             * Losowanie glębokości na której zanurzy się wędka
             */
            Random rand = new Random();
            do {
                depth = (rand.nextInt(Map.size - Map.upBorder - Map.downBorder)) + Map.upBorder;
            } while (depth <= Map.upBorder + Map.border && depth >= Map.size - Map.downBorder-Map.border);

        }

    }

    /**
     * Metoda liczy odległość między wędką a rybą
     * @param x współrzędna X ryby
     * @param y współrzędna Y ryby
     * @return zwraca odległość
     */
    public double distance(double x, double y) {
        return sqrt(((x - coX) * (x - coX)) + ((y - roddepth) * (y - roddepth)));
    }

    /**
     * Metoda odpowiada za operacje łowienia
     * @param g odpowiada za rysowanie wędki
     * @param table tabela ryb łowionych
     */
    public  void fishing(Graphics g, ArrayList<Fish> table) {
        Random rand = new Random();
        if (stop == false)
            return;

        /**
         * Zanurzanie wędki
         */
        if (roddepth < depth)
            roddepth += rodspeed;

        /**
         * Rysowanie wędki
         */
        g.setColor(Color.BLACK);
        g.drawLine(coX, Map.upBorder, coX, roddepth);

        /**
         * Sprawdzanie czy wędka jest w płeni zanurzona
         */
        if (roddepth < depth)
            return;
        /**
        * łowienie
        */
        for (Fish fish : table) {
            if (distance(fish.coX, fish.coY) <= rodrange ) {
                if(rand.nextInt()%100>fish.agility) {
                    if (fish instanceof GreenFish) {
                        fish_caught++;

                    } else if (fish instanceof RedFish) {
                        fish_caught++;

                    } else if (fish instanceof YellowFish) {
                        fish_caught++;

                    } else if (fish instanceof Shark) {
                        fish_caught++;

                    }
                    /**
                     * Usuwanie złapanych ryb
                     */
                    table.remove(fish);
                }

                stop = false;
                roddepth = Map.upBorder;


                return;
            }
        }
    }
}
