package org.example;
import java.awt.*;
import java.util.Random;

public class Shark extends Fish{
    Shark() {  //"konstruktor rekina"
        Random rand = new Random();

        this.speed = 3;
        this.size = 150;
        this.goback = rand.nextBoolean();
        this.health=200;
        this.power=100;
        this.position(size);

    }

    public void swim(Graphics g,Fish shark) {
        g.setColor(Color.GRAY);

        g.fillOval(coX, coY,size,size);
        super.swim(shark, size);

    }
}
