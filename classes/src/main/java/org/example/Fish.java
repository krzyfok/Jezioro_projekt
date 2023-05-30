package org.example;




import java.awt.*;
import java.util.Random;

public abstract class Fish implements Swim{


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
    protected static int breedrange = 20;
    protected int attackrange;
    protected double hunger;
    protected double dobreed;      //czy moga sie rozmnazac



    public  void position(int size) {

        //tu na dole ustawiana jest pierwsza lokalizacja ryby, z uwzglednieniem warunku, zeby byla na planszy
        Random rand = new Random();
        this.coX=((rand.nextInt(Map.size-2* Map.border-2*size))+ Map.border+size);
        this.coY=((rand.nextInt(Map.size- Map.upBorder- Map.downBorder-2*size-2*Map.border))+ Map.upBorder+2*size+Map.border);


         //korekta polozenia ryby
        if(this.coY<=Map.upBorder+Map.border) {
            this.coY+=2*this.size;
            this.gobacky=true;
        }
        if(this.coY>=Map.size-Map.downBorder-Map.border-this.size) {
            this.coY-=2*this.size;
            this.gobacky=false;
        }
    }




    public static double distance(Fish fish1, Fish fish2) {
        return (Math.sqrt((fish1.coX+fish1.size/2-fish2.coX-fish2.size/2)*(fish1.coX+fish1.size/2-fish2.coX-fish2.size/2)+(fish1.coY+fish1.size/2-fish2.coY-fish2.size/2)*(fish1.coY+fish1.size/2-fish2.coY-fish2.size/2)));
    }

    public void reproduct(Fish fish2) {
        Random rand = new Random();


        //sprawdzenie czy moga sie rozmnazac
        if(this.dobreed<=30 || fish2.dobreed<=30)
            return;
        if(this.numberOfFish()>=45)      //ograniczenie do 45 osobnikow danej odmiany
            return;


        //sprawdzenie czy dystans jest mniejszy niz range:
        if (distance(this, fish2) <= breedrange) {

            if (this instanceof GreenFish)
                Map.tableOfFish.add(new GreenFish());
            else if (this instanceof RedFish)
                Map.tableOfFish.add(new RedFish());
            else if (this instanceof YellowFish)
                Map.tableOfFish.add(new YellowFish());



            //ustawienie wspolrzednych ryb dziecka

            if(!(this instanceof YellowFish)) {         //zolta ryba generuje sie losowo na mapie, bo inaczej laguje
                Map.tableOfFish.get(Map.tableOfFish.size() - 1).coX = this.coX - rand.nextInt() % 30;
                Map.tableOfFish.get(Map.tableOfFish.size() - 1).coY = this.coY - rand.nextInt() % 30;


                //korekta polozenia ryby
                if(this.coY<=Map.upBorder+Map.border) {
                    this.coY+=2*this.size;
                    this.gobacky=true;
                }
                if(this.coY>=Map.size-Map.downBorder-Map.border-this.size) {
                    this.coY-=2*this.size;
                    this.gobacky=false;
                }
            }


            this.dobreed = 0;           //te rybki nie beda mogly juz sie rozmnazac przez jakis czas
            fish2.dobreed = 0;
        }

    }

    public void attack(Fish fish2) {

        if(this.hunger<10)
            return;
        if (distance(this, fish2) <= this.attackrange)
            if (this.power > fish2.power) {
                fish2.health -= this.power;
                this.hunger = 0;
            }
    }

    public int die() {
        if (this.health<=0) {
            Map.tableOfFish.remove(this);
            return 1;
        }
        else
            return 0;
    }

    public int numberOfFish() {
        int number=0;
        for(int i=0; i<Map.tableOfFish.size(); i++)
            if(Map.tableOfFish.get(i).getClass() == this.getClass())
                number++;

        return number;
    }
}
