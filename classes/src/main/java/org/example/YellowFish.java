package org.example;

import java.awt.*;
import java.util.Random;

/**
 * YellowFish pływa,  podgryza silniejsze ryby i się rozmnaża
 */
public class YellowFish extends Fish {

    /**
     * Konstruktor pojendynczej ryby bez parametrów
     */
    public YellowFish() {  //"konstruktor pojedynczej ryby"
        Random rand = new Random();

        this.speedX = 10;
        this.speedY=10;
        this.size = 5;
        this.gobackx = rand.nextBoolean();
        this.health=50;
        this.power=50;
        this.agility=50;
        this.position(size);
        this.dobreed=rand.nextInt()%50;
        this.hunger=rand.nextInt()%50;
    }

    /**
     * Konstruktor pojedynczej ryby z parametrami początkowymi
     * @param coX współrzędna X
     * @param coY współrzędna Y
     * @param hunger głód
     * @param health punkty zdrowia
     */
    public YellowFish(int coX, int coY, int hunger, int health)
    {
        Random rand = new Random();

        this.speedX = 10;
        this.speedY=10;
        this.size = 5;
        this.gobackx = rand.nextBoolean();
        this.health=health;
        this.power=50;
        this.agility=50;
        this.dobreed=rand.nextInt()%50;
        this.hunger=hunger;
        this.coX=coX;
        this.coY=coY;

    }

    /**
     * Metoda odpowiada za poruszanie się ryby
     * @param g odpowiada za rysowanie
     */
    public void swim(Graphics g) {

        Random rand = new Random();
        g.setColor(Color.yellow);

        g.fillOval(this.coX, this.coY, this.size, this.size);
        /** ruch w osi X */
        if(this.coX >= Map.border && this.coX <= Map.size- Map.border-size) {
            this.coX += ((rand.nextInt(this.speedX*2))-10);
        }
        else if(this.coX <= Map.border )
        {
            this.coX+=this.speedX;
        }
        else if( this.coX >= Map.size- Map.border-size)
        {
            this.coX-=this.speedX;
        }
        /** ruch w osi Y*/
        if(this.coY >= Map.upBorder+speedY && this.coY <= Map.size- Map.downBorder-speedY) {

            this.coY += ((rand.nextInt(this.speedY*2))-10);

        }
        else if(this.coY <= Map.upBorder+speedY )
        {
            this.coY+=this.speedY;
        }
        else if( this.coY >= Map.size- Map.downBorder-speedY*2)
        {
            this.coY-=this.speedY;
        }

        this.dobreed++;
        this.hunger++;

    }
}
