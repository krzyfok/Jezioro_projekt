package org.example;

import java.awt.*;
import java.util.Random;
public class RedFish extends Fish{

    RedFish() {  //"konstruktor pojedynczej ryby"
        Random rand = new Random();

        this.speed = 10;
        this.size = 20;
        this.health=20;
        this.power=10;
        this.agility=40;
        this.gobackx = rand.nextBoolean();
        this.position(size);
    }


    public void swim(Graphics g) {

        g.setColor(Color.RED);

            g.fillOval(this.coX, this.coY, this.size, this.size);
            if(this.coX >= Map.border && this.coX <= Map.size- Map.border-size) {
                if(this.gobackx==true)
                    this.coX += this.speed;
                else
                    this.coX -= this.speed;
            }
            else {                                              //tutaj ryba zawraca gdy spotka krawedz
                if(this.gobackx==true) {
                    this.gobackx=false;
                    this.coX -= this.speed;
                }
                else {
                    this.gobackx=true;
                    this.coX += this.speed;
                }
            }
            if(this.coY >= Map.upBorder && this.coY <= Map.size- Map.downBorder-size) {
                if(this.gobacky==true)
                    this.coY += this.speed/2;
                else
                    this.coY -= this.speed/2;
            }
            else {                                              //tutaj ryba zawraca gdy spotka krawedz
                if(this.gobacky==true) {
                    this.gobacky=false;
                    this.coY -= this.speed/2;
                }
                else {
                    this.gobacky=true;
                    this.coY += this.speed/2;
                }
            }
        }

}
