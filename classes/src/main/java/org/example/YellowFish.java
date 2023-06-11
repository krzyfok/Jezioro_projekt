package org.example;

import java.awt.*;
import java.util.Random;

public class YellowFish extends Fish {


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

    public void swim(Graphics g) {

        Random rand = new Random();
        g.setColor(Color.yellow);

        g.fillOval(this.coX, this.coY, this.size, this.size);
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
