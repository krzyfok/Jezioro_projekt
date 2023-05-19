package org.example;

import java.awt.*;
import java.util.Random;


public class Shark extends Fish{

    protected int hunger;
    Shark() {  //"konstruktor pojedynczej ryby"
        Random rand = new Random();

        this.speedX = 5;
        this.size = 100;
        this.gobackx = rand.nextBoolean();
        this.health=20000;
        this.power=5000;
        this.agility=10;
        this.hunger=100;
        this.position(size);
    }

    public void swim(Graphics g) {


        g.setColor(Color.GRAY);

        g.fillOval(this.coX, this.coY, this.size, this.size);
        if(this.coX >= Map.border && this.coX <= Map.size- Map.border-size) {
            if(this.gobackx==true)
                this.coX += this.speedX;
            else
                this.coX -= this.speedX;
        }
        else {                                              //tutaj ryba zawraca gdy spotka krawedz
            if(this.gobackx==true) {
                this.gobackx=false;
                this.coX -= this.speedX;
            }
            else {
                this.gobackx=true;
                this.coX += this.speedX;
            }
        }
        if(this.coY >= Map.upBorder && this.coY <= Map.size- Map.downBorder-size) {
            if(this.gobacky==true)
                this.coY += this.speedX/2;
            else
                this.coY -= this.speedX/2;
        }
        else {                                              //tutaj ryba zawraca gdy spotka krawedz
            if(this.gobacky==true) {
                this.gobacky=false;
                this.coY -= this.speedX/2;
            }
            else {
                this.gobacky=true;
                this.coY += this.speedX/2;
            }
        }

        this.hunger++;
    }
}
