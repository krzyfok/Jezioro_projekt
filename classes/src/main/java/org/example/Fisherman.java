package org.example;


import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import static java.lang.Math.sqrt;
public class Fisherman {
    protected int speed = 6;
    protected int coX = Map.size / 2;
    protected int coY = Map.upBorder - 10;    //odejmuje wysokosc lodki
    protected int size = 10;

    protected boolean goback = true;
    protected boolean stop = false;
    protected int stopcountdown = 0;                      //zeby sie zatrzymywal co jakas wartosc. potem mozna to zrobic randomowo
    protected int maxcountdown = 70;

    protected int depth;                         //wskazuje do jakiej glebokosci zanurzy sie lodka
    protected int rodspeed = 20;             //do wedki dodac nowa klase? nw
    protected int roddepth = Map.upBorder;        //aktualna glebokosc wedki
    protected double rodrange = 5;                 //w jakiej odleglosci od wedki ryba zostaje zlapana. potem mozna to uzaleznic od rodzaju ryby

    static int fish_caught=0;


    
    public void swim(Graphics g) {        //tu musi byc to g do rysowania. Tak jak w rybkach, mozna to podzielic na kilka metod (np 2)

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

    public double distance(double x, double y) {
        return sqrt(((x - coX) * (x - coX)) + ((y - roddepth) * (y - roddepth)));
    }

    public  void fishing(Graphics g, ArrayList<Fish> table) {
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
                    //usuwanie ryby:
                    table.remove(fish);
                }

                stop = false;
                roddepth = Map.upBorder;


                return;
            }
        }
    }
}
