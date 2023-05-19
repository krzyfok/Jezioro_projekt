package org.example;

import java.awt.*;
import java.util.Random;


public class YellowFish extends Fish{


    YellowFish() {  //"konstruktor pojedynczej ryby"
        Random rand = new Random();

        this.speedX = 10;
        this.speedY=10;
        this.size = 5;
        this.gobackx = rand.nextBoolean();
        this.health=20;
        this.power=10;
        this.agility=10;
        this.position(size);
        this.dobreed=rand.nextInt()%50;

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

    }
}
