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
    protected static int attackrange = 50;         // -//-
    protected int hunger;
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
    public void reproduct(Fish fish2) {
        Random rand = new Random();

        //"indeks" dodawanej rybki. Przydaje sie do ustawienia jej wspolrzednych



        if(this.dobreed<=50 || fish2.dobreed<=50)
            return;


        //sprawdzenie czy dystans jest mniejszy niz range i odmiany rybek sa te same:
        if (distance(this, fish2) <= breedrange) {

            if (this instanceof GreenFish)
                Map.tableOfFish.add(new GreenFish());
            else if (this instanceof RedFish)
                Map.tableOfFish.add(new RedFish());
            else if (this instanceof YellowFish)
                Map.tableOfFish.add(new YellowFish());


            //ustawienie wspolrzednych dziecka ryb:
            //Map.tableOfFish.get(Map.tableOfFish.size()-1).coX = this.coX-rand.nextInt()%15;;
            //Map.tableOfFish.get(Map.tableOfFish.size()-1).coY = this.coY-rand.nextInt()%15;
            Map.tableOfFish.get(Map.tableOfFish.size()-1).position(this.size);                             //generuje sie losowo na mapie
            Map.tableOfFish.get(Map.tableOfFish.size()-1).gobackx = !this.gobackx;     //zawraca? Zeby nie plynely razem bo beda sie rozmnazac ze soba
            Map.tableOfFish.get(Map.tableOfFish.size()-1).gobacky = !this.gobacky;

            this.dobreed = 0;           //te rybki nie beda mogly juz sie rozmnazac przez jakis czas
            fish2.dobreed = 0;
        }

    }

    public void attack(Fish fish2) {
        if (distance(this, fish2) <= breedrange) {      //tu na razie jest breedrange. mozna potem ustawic jakis attackrange
            if (this.power > fish2.power) {
                fish2.health -= this.power / 2;     //traci jakby polowe hp, bo podczas wywolania tej metody w Map, bedzie ona wywolana 2 razy dla tego samego zestawu ryb. wiec lacznie straci 2 razy po pol
                this.hunger=0;
            }
            else {
                this.health -= fish2.power / 2;
                fish2.hunger=0;
            }
        }
    }


    public void die() {
        if (this.health<=0)
            Map.tableOfFish.remove(this);
    }
}
