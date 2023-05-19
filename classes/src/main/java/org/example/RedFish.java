package org.example;

import java.awt.*;
import java.util.Random;
public class RedFish extends Fish{

    RedFish() {  //"konstruktor pojedynczej ryby"
        Random rand = new Random();

        this.speedX = 5;
        this.speedY=10;
        this.size = 20;
        this.health=20;
        this.power=10;
        this.agility=40;
        this.gobackx = rand.nextBoolean();
        this.position(size);
        this.dobreed=rand.nextInt()%50;
    }


    public void swim(Graphics g) {


        g.setColor(Color.RED);

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
            if(this.coY >= Map.upBorder+speedY && this.coY <= Map.size- Map.downBorder-speedY*2) {
                if(this.gobacky==true)
                    this.coY += this.speedY;
                else
                    this.coY -= this.speedY;
            }
            else {                                              //tutaj ryba zawraca gdy spotka krawedz
                if(this.gobacky==true) {
                    this.gobacky=false;
                    this.coY -= this.speedY;
                }
                else {
                    this.gobacky=true;
                    this.coY += this.speedY;
                }
            }
        this.dobreed++;
        }

}
