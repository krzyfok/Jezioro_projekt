package org.example;


import java.awt.*;
import java.util.Random;
import static java.lang.Math.sqrt;
public class Fisherman {
    static int speed = 6;
    static int coX = Map.size / 2;
    static int coY = Map.upBorder - 10;    //odejmuje wysokosc lodki
    static int size = 10;

    static boolean goback = true;
    static boolean stop = false;
    static int stopcountdown = 0;                      //zeby sie zatrzymywal co jakas wartosc. potem mozna to zrobic randomowo
    static int maxcountdown = 70;

    static int depth;                         //wskazuje do jakiej glebokosci zanurzy sie lodka
    static int rodspeed = 20;             //do wedki dodac nowa klase? nw
    static int roddepth = Map.upBorder;        //aktualna glebokosc wedki
    static double rodrange = 5;                 //w jakiej odleglosci od wedki ryba zostaje zlapana. potem mozna to uzaleznic od rodzaju ryby

    

    
    public static void swim(Graphics g) {        //tu musi byc to g do rysowania. Tak jak w rybkach, mozna to podzielic na kilka metod (np 2)

        //rysowanie lodki
        g.setColor(Color.ORANGE);
        g.fillRect(coX, coY, size*5, size);

        if (stop == true)             //jesli jest zatrzymany to sie nie porusza
            return;

        //poruszanie sie:
        if (coX >= Map.border && coX <= Map.size - 2 * Map.border - size*5) {      //odejmuje dlugosc lodki
            if (goback == true)
                coX += speed;
            else
                coX -= speed;
        } else {                                              //tutaj rybak zawraca gdy spotka krawedz
            if (goback == true) {
                goback = false;
                coX -= speed;
            } else {
                goback = true;
                coX += speed;
            }
        }
        stopcountdown++;                       //zwiekszanie licznika, gdy rybak sie porusza

        if (stopcountdown >= maxcountdown) {                //zatrzymuje sie co x klatek
            stopcountdown = 0;
            stop = true;

            Random rand = new Random();         //wylosowanie glebokosci, na ktora zanurzy sie wedka
            do {
                depth = (rand.nextInt(Map.size - Map.upBorder - Map.downBorder)) + Map.upBorder;
            } while (depth <= Map.upBorder + Map.border && depth >= Map.size - Map.downBorder-Map.border);

        }

    }

    static double distance(double x, double y) {
        return sqrt(((x - coX) * (x - coX)) + ((y - roddepth) * (y - roddepth)));
    }

    public static void fishing(Graphics g) {
        Random rand = new Random();
        if (stop == false)
            return;

        //zanurzanie:
        if (roddepth < depth)
            roddepth += rodspeed;

        //rysowanie wedki:
        g.setColor(Color.BLACK);
        g.drawLine(coX, Map.upBorder, coX, roddepth);

        //jesli nie jest w pelni zanurzone to ryby nie beda sie lapac
        if (roddepth < depth)
            return;

        for (Fish fish : Map.tableOfFish) {
            if (distance(fish.coX, fish.coY) <= rodrange ) {
                if(rand.nextInt()%100>fish.agility) {
                    if (fish instanceof GreenFish) {
                        System.out.println("Test, zlapana zielona");

                    } else if (fish instanceof RedFish) {
                        System.out.println("Test, zlapana czerwona");

                    } else if (fish instanceof YellowFish) {
                        System.out.println("Test, zlapana żółta");

                    } else if (fish instanceof Shark) {
                        System.out.println("DZIWNE, zlapany rekin lol");

                    }
                }

                stop = false;
                roddepth = Map.upBorder;

                //usuwanie ryby:
                Map.tableOfFish.remove(fish);
                return;
            }
        }
    }
}
