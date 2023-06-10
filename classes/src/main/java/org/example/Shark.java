package org.example;


import java.awt.*;
import java.util.Random;


public class Shark extends Fish {

    private int minHunger;
    public Shark() {  //"konstruktor pojedynczej ryby"
        Random rand = new Random();

        this.speedX = 3;
        this.size = 100;
        this.gobackx = rand.nextBoolean();
        this.health=20000;
        this.power=1050;
        this.agility=10;
        this.hunger=100;
        this.attackrange=50;
        this.position(size);

    }

    public void swim(Graphics g) {


        g.setColor(Color.GRAY);

        g.fillOval(this.coX, this.coY, this.size, this.size);


        //poruszanie sie w osi X
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

        //poruszanie sie w osi Y
        if(this.gobacky==true) {
            if((this.coY-this.speedX/2)<=(Map.border+this.size+Map.upBorder))
                this.gobacky=false;
            else
                this.coY-=this.speedX/2;
        }
        else if(this.gobacky==false) {
            if((this.coY+this.speedX/2)>=(Map.size-Map.border-Map.downBorder-this.size))
                this.gobacky=true;
            else
                this.coY+=this.speedX/2;
        }


        this.hunger+=10;
    }


    @Override
    public void attack(Fish fish) {
        if (distance(this, fish) <= this.attackrange) {

            hunger+=1;
            if(this.hunger<=minHunger)
                return;

            fish.health -= this.power;
            fish.die();
            this.hunger=0;
        }

    }

}

