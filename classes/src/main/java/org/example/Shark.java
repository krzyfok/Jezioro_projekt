package org.example;
import java.awt.*;
import java.util.Random;

public class Shark extends Fish{
    Shark() {  //"konstruktor rekina"
        Random rand = new Random();

        this.speed = 5;
        this.size = 150;
        this.gobackx = rand.nextBoolean();
        this.health=200;
        this.power=100;
        this.position(size);

    }

    public void swim(Graphics g,Fish shark) {
        g.setColor(Color.GRAY);

        g.fillOval(coX, coY,size,size);
        //zmienienie wspolrzednych ryby (poruszenie sie ryby)
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
