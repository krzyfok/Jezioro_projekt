package org.example;


import java.awt.*;
import java.util.Random;

/**
 * RedFish pływa, atakuje słabsze ryby i się rozmnaża
 */
public class RedFish extends Fish {
    /** liczba ryb przy której ryby częściej się rozmnażają */
    private int extinction = 10;
    /** Wartość o którą zwiększany jest licznik potrzebny do rozmnażania*/
    private int breedValue = 5;
    /** Im mniejsze tym czesciej zawraca (w pionie) */
    private int goBack = 15;

    /**
     * Konstruktor pojedynczej ryby bez parameetrów początkowych
     */
    
    public RedFish() {
        Random rand = new Random();

        this.speedX = 5;
        this.speedY=5;
        this.size = 20;
        this.health=100;
        this.power=100;
        this.agility=40;
        this.gobackx = rand.nextBoolean();
        this.position(size);
        this.dobreed=rand.nextInt()%50;
        this.attackrange = 20;
        this.hunger=rand.nextInt()%50;
    }

    /**
     * Konstruktor pojedynczej ryby z parametrami początkowymi
     * @param coX wspórzędna X
     * @param coY współrzędna Y
     * @param hunger głóg
     * @param health punkty zdrowia
     * @param aglility zwinność
     */
    public RedFish(int coX, int coY, int hunger, int health, int aglility)
    {
        Random rand = new Random();

        this.speedX = 5;
        this.speedY=5;
        this.size = 20;
        this.health=health;
        this.power=100;
        this.agility=aglility;
        this.gobackx = rand.nextBoolean();
        this.coX=coX;
        this.coY=coY;
        this.dobreed=rand.nextInt()%50;
        this.attackrange = 20;
        this.hunger=hunger;
    }


    /**
     * Metoda odpowiada za poruszanie się ryby
     * @param g odpowaida za rysowanie
     */
    public void swim(Graphics g) {
        Random rand =new Random();

        g.setColor(Color.RED);
        g.fillOval(this.coX, this.coY, this.size, this.size);

        /**
         *  ruch w osi X
         */
        if(this.gobackx==true) {
            if((this.coX-this.speedX)<=(Map.border+this.size))
                this.gobackx=false;
            else
                this.coX-=this.speedX;
        }
        else if(this.gobackx==false) {
            if((this.coX+this.speedX)>=(Map.size-Map.border-this.size))
                this.gobackx=true;
            else
                this.coX+=this.speedX;
        }

        /** ruch w osi Y */
        if(this.gobacky==true) {
            if((this.coY-this.speedY)<=(Map.border+this.size+Map.upBorder))
                this.gobacky=false;
            else
                this.coY-=this.speedY;
        }
        else if(this.gobacky==false) {
            if((this.coY+this.speedY)>=(Map.size-Map.border-Map.downBorder-this.size))
                this.gobacky=true;
            else
                this.coY+=this.speedY;
        }

        /** losowa zmiana kierunku */
        if(rand.nextInt()%goBack==0)
            this.gobacky = !this.gobacky;


        if(this.numberOfFish()<extinction)
            this.dobreed+=breedValue;
        this.dobreed++;
        this.hunger++;
    }

}
