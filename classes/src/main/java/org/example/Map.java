package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Map extends JFrame{
    public static int size = 600;
    public static int border = 20;      //tutaj ustawiam szerokosc ramki. Potem to zmienic na dodatkowo border-dolny, zeby dodac piasek
    public static int upBorder = 100;   //powierzchnia
    public static int downBorder=50;
    static ArrayList<Fish> tableOfFish;      //przeniesione tutaj, zeby dzialalo jako jedna arraylista dla wszystkich rodzajow ryb

    Map() {                                               //konstruktor, ustawienie wielkosci okienka
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(size, size);
        this.setVisible(true);
        setLayout(null);
    }

    public void clean(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0,upBorder,size,size);
        g.setColor(Color.CYAN);
        g.fillRect(0,0, size, upBorder);
        g.setColor(Color.YELLOW);
        g.fillRect(0,size-downBorder,size, downBorder);
    }

    public static void setFish(int green, int red, int yellow) {
        tableOfFish = new ArrayList();
        tableOfFish.add(new Shark());
        for(int i=0; i<red; i++)
            tableOfFish.add(new RedFish());
        for(int i=0; i<green; i++)
            tableOfFish.add(new GreenFish());
        for(int i=0; i<yellow; i++)
            tableOfFish.add(new YellowFish());
    }

    @Override
    public void paint(Graphics g) {                 //klasa do rysowania


        for(int i=0; i<1000; i++) {                 //na razie ustawilam ilosc klatek do 1000, zeby mozna bylo zobaczyc czy to dziala

            //rysuje jeziorko (i czysci namalowane ryby z poprzedniej klatki)
            clean(g);

            //plywanie ryb:
            for(int j=0; j<tableOfFish.size(); j++)
                tableOfFish.get(j).swim(g);

            //rozmnazanie ryb:
            for(int j=0; j<tableOfFish.size(); j++)
                for(int k=0; k<tableOfFish.size(); k++)
                    if(j!=k && tableOfFish.get(j).getClass()==tableOfFish.get(k).getClass())
                        tableOfFish.get(j).reproduct(tableOfFish.get(k));

            //atak ryb:
            for(int j=0; j<tableOfFish.size(); j++)
                for(int k=0; k<tableOfFish.size(); k++)
                    if(j!=k && tableOfFish.get(j).getClass()!=tableOfFish.get(k).getClass())
                        tableOfFish.get(j).attack(tableOfFish.get(k));

            //smierc ryb:
            for(int j=0; j<tableOfFish.size(); j++)
                tableOfFish.get(j).die();


            Fisherman.swim(g);
            Fisherman.fishing(g);
            //innaryba.plywanie...
            //rybak.zrobcos...

            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

    }
}
