package org.example;


import java.awt.*;
import java.util.Random;
public class GreenFish extends Fish {

    private int extinction = 15;
    private int breedValue = 5;

    public GreenFish() {  //"konstruktor pojedynczej ryby"
        Random rand = new Random();

        this.speedX = 8;
        this.speedY=3;
        this.size = 15;
        this.gobackx = rand.nextBoolean();
        this.gobacky = rand.nextBoolean();
        this.health=70;
        this.power=30;
        this.agility=30;
        this.position(size);
        this.dobreed=rand.nextInt()%50;
    }
    public GreenFish(int coX, int coY, int health)
    {
        Random rand = new Random();
        this.speedX = 8;
        this.speedY=3;
        this.size = 15;
        this.gobackx = rand.nextBoolean();
        this.gobacky = rand.nextBoolean();
        this.health=health;
        this.power=30;
        this.agility=30;
        this.position(size);
        this.dobreed=rand.nextInt()%50;

        this.coX=coX;
        this.coY=coY;

    }
    public void swim(Graphics g) {


        g.setColor(Color.GREEN);
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
            if((this.coY-this.speedY)<=(Map.border+this.size+Map.upBorder))
                this.gobacky=false;
            else
                this.coY-=this.speedY;
        }
        else if(this.gobacky==false) {
            if((this.coY+this.speedY)>=(Map.size-Map.downBorder-this.size))
                this.gobacky=true;
            else
                this.coY+=this.speedY;
        }


        if(this.numberOfFish()<extinction)
            this.dobreed+=breedValue*10;
        this.dobreed+=breedValue;
        this.hunger++;
    }


}
