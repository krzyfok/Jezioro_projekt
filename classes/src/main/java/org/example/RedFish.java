package org.example;






import java.awt.*;
import java.util.Random;
public class RedFish extends Fish {

    public RedFish() {  //"konstruktor pojedynczej ryby"
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


    public void swim(Graphics g) {
        Random rand =new Random();

        g.setColor(Color.RED);
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
            if((this.coY+this.speedY)>=(Map.size-Map.border-Map.downBorder-this.size))
                this.gobacky=true;
            else
                this.coY+=this.speedY;
        }

        //randomowe zawracanie
        if(rand.nextInt()%15==0)
            this.gobacky = !this.gobacky;


        if(this.numberOfFish()<10)
            this.dobreed+=50;
        this.dobreed++;
        this.hunger++;
    }

}
