package org.example;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
public class GreenFish extends Fish{
    static ArrayList<GreenFish> tablicaRyb;       //przechowuje wszystkie rybki tej odmiany


    GreenFish(int iloscRyb) {
        tablicaRyb = new ArrayList();               //tutaj utworzenie rybek w ilosci zadanej w mainie

        for(int i=0; i<iloscRyb; i++)
            tablicaRyb.add(new GreenFish());  //odwolanie do konstruktora "pojedynczej ryby", ktora jest nizej

    }

    GreenFish() {  //"konstruktor pojedynczej ryby"
        Random rand = new Random();

        this.speed = 5;
        this.size = 10;
        this.goback = rand.nextBoolean();
        this.position(size);


    }
    public void swim(Graphics g) {
        g.setColor(Color.GREEN);
        for (GreenFish fish : tablicaRyb) {
            g.fillOval(fish.coX, fish.coY, fish.size, fish.size);
            super.swim(fish, size);
        }
    }
}