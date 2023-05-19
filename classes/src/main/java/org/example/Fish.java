package org.example;

import java.awt.*;
import java.util.Random;

public abstract class Fish {

    protected int speedX;
    protected int speedY;
    protected int coY;
    protected int coX;
    protected int size;

    protected int power;
    protected int agility;
    protected int health;
    protected boolean gobackx;
    protected boolean gobacky;
    protected static int breedrange = 10;       //mozna dodac inny range rozmnazania dla roznych ryb
    protected int dobreed;                  //czy moga sie rozmnazac
    public  void position(int size)
    {
        //tu na dole ustawiana jest pierwsza lokalizacja ryby, z uwzglednieniem warunku, zeby byla na planszy
        Random rand = new Random();

        this.coX=((rand.nextInt(Map.size-2* Map.border-size))+ Map.border);
        this.coY=((rand.nextInt(Map.size- Map.upBorder- Map.downBorder-size))+ Map.upBorder);

    }
    public void swim(Graphics g){}
    public static double distance(Fish fish1, Fish fish2) {
        return (Math.sqrt((fish1.coX-fish2.coX)*(fish1.coX-fish2.coX)+(fish1.coY-fish2.coY)*(fish1.coY-fish2.coY)));
    }
    public void reproduct() {


        //"indeks" dodawanej rybki. Przydaje sie do ustawienia jej wspolrzednych

        for(int i=0; i<Map.tableOfFish.size(); i++) {
            for(int j=0; j<Map.tableOfFish.size(); j++) {

                //jesli sa te same lub nie moga jeszcze sie rozmnazac to skip
                if(i==j)
                    continue;
                if(Map.tableOfFish.get(i).dobreed<=50 || Map.tableOfFish.get(j).dobreed<=50)
                    continue;



                //sprawdzenie czy dystans jest mniejszy niz range i odmiany rybek sa te same:
                if (distance(Map.tableOfFish.get(i), Map.tableOfFish.get(j)) <= breedrange) {
                    if (Map.tableOfFish.get(i).getClass() == Map.tableOfFish.get(j).getClass()) {

                        if (Map.tableOfFish.get(i) instanceof GreenFish)
                            Map.tableOfFish.add(new GreenFish());
                        else if (Map.tableOfFish.get(i) instanceof RedFish)
                            Map.tableOfFish.add(new RedFish());
                        else if (Map.tableOfFish.get(i) instanceof YellowFish)
                            Map.tableOfFish.add(new YellowFish());



                        //ustawienie wspolrzednych dziecka ryb:
                     //   Map.tableOfFish.get(Map.tableOfFish.size()-1).coX = 500;
                       // Map.tableOfFish.get(Map.tableOfFish.size()-1).coY = 500;
                        Map.tableOfFish.get(Map.tableOfFish.size()-1).position(this.size);
                        Map.tableOfFish.get(Map.tableOfFish.size()-1).gobackx = !Map.tableOfFish.get(i).gobackx;     //zawraca? Zeby nie plynely razem bo beda sie rozmnazac ze soba
                        Map.tableOfFish.get(Map.tableOfFish.size()-1).gobacky = !Map.tableOfFish.get(i).gobacky;

                        Map.tableOfFish.get(i).dobreed = 0;           //te rybki nie beda mogly juz sie rozmnazac przez jakis czas
                        Map.tableOfFish.get(j).dobreed = 0;

                    }
                }
            }
        }


    }

}
