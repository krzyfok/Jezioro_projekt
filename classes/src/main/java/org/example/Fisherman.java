package org.example;
import java.awt.*;
import java.util.Random;
import static java.lang.Math.sqrt;
public class Fisherman {
    static int speed = 3;
    static int coX = Draw.size/2;
    static int coY = Draw.upBorder-10;    //odejmuje wysokosc lodki


    static boolean goback = true;
    static boolean stop = false;
    static int stopcountdown = 0;                      //zeby sie zatrzymywal co jakas wartosc. potem mozna to zrobic randomowo


    static int depth;                         //wskazuje do jakiej glebokosci zanurzy sie lodka
    static int rodspeed = 2;             //do wedki dodac nowa klase? nw
    static int roddepth = Draw.upBorder;        //aktualna glebokosc wedki
    static double rodrange = 15;                 //w jakiej odleglosci od wedki ryba zostaje zlapana. potem mozna to uzaleznic od rodzaju ryby
    //predkosc lowienia, itd itp...



    public static void plywanie(Graphics g) {        //tu musi byc to g do rysowania. Tak jak w rybkach, mozna to podzielic na kilka metod (np 2)

        //rysowanie lodki
        g.setColor(Color.ORANGE);
        g.fillRect(coX, coY, 50, 10);

        if(stop==true)             //jesli jest zatrzymany to sie nie porusza
            return;

        //poruszanie sie:
        if(coX >= Draw.border && coX <= Draw.size-2*Draw.border-50) {      //odejmuje dlugosc lodki
            if(goback==true)
                coX += speed;
            else
                coX -= speed;
        }
        else {                                              //tutaj rybak zawraca gdy spotka krawedz
            if(goback==true) {
                goback=false;
                coX -= speed;
            }
            else {
               goback  =true;
                coX += speed;
            }
        }
        stopcountdown++;                       //zwiekszanie licznika, gdy rybak sie porusza

        if(stopcountdown>=70) {                //zatrzymuje sie co 70 klatek
            stopcountdown=0;
            stop=true;

            Random rand = new Random();         //"wylosowanie"glebokosci. mozna dodac metode, np. setGlebokosc czy cos
            do{
                depth = rand.nextInt()%(Draw.size);
            } while(depth<=Draw.upBorder+Draw.border || depth>=Draw.size-Draw.border);

        }

    }

    static double odleglosc(double x, double y) {
        return sqrt((x-y)*(x-y));
    }

    public static void lowienie(Graphics g) {
        if(stop==false)
            return;

        //zanurzanie:
        if(roddepth<depth)
           roddepth+=rodspeed;

        //rysowanie wedki:
        g.setColor(Color.BLACK);
        g.drawLine(coX, Draw.upBorder,coX, roddepth);

        //jesli nie jest w pelni zanurzone to ryby nie beda sie lapac
        if(roddepth<depth)
            return;

        //sprawdzanie czy ryba jest w poblizu, tez mozna dodac metode:
        //dodac opcje, ze jak przez jakis czas nie zlowi ryby to zaczyna lowic od nowa
        for(GreenFish fish : GreenFish.tablicaRyb) {
            if(odleglosc(fish.coX, roddepth)<=rodrange  &&  odleglosc(fish.coX, coX)<=rodrange) {
                //usunRybe();
                System.out.println("Test, zlapana zielona");
                stop=false;
                roddepth=Draw.upBorder;
            }
        }
        for(RedFish fish : RedFish.tablicaRyb) {
            if(odleglosc(fish.coX, roddepth)<=rodrange  &&  odleglosc(fish.coX, coX)<=rodrange) {
                //usunRybe();
                System.out.println("Test, zlapana czerwona");
                stop=false;
                roddepth=Draw.upBorder;
            }
        }
    }
}
