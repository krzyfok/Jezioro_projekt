package org.example;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
public class RedFish extends Fish{
    static ArrayList<RedFish> tablicaRyb;       //przechowuje wszystkie rybki tej odmiany


    RedFish(int iloscRyb) {
        tablicaRyb = new ArrayList();               //tutaj utworzenie rybek w ilosci zadanej w mainie

        for(int i=0; i<iloscRyb; i++)
            tablicaRyb.add(new RedFish());  //odwolanie do konstruktora "pojedynczej ryby", ktora jest nizej

    }

    RedFish() {  //"konstruktor pojedynczej ryby"
        Random rand = new Random();

        this.speed = 10;
        this.size = 20;
        this.goback = rand.nextBoolean();

        //tu na dole ustawiana jest pierwsza lokalizacja ryby, z uwzglednieniem warunku, zeby byla na planszy
        do {
            this.coX = rand.nextInt()%(Draw.size-2*Draw.border);
        }while(coX <= Draw.border || coX >= Draw.size-2*Draw.border);

        do {
            this.coY = rand.nextInt()%(Draw.size-Draw.border);
        }while(coY <= Draw.upBorder || coY >= Draw.size-2*Draw.border);

    }


    public void swim(Graphics g) {
        g.setColor(Color.RED);
        for (RedFish fish : tablicaRyb) {
            g.fillOval(fish.coX, fish.coY, fish.size, fish.size);
            super.swim(fish);
        }
    }
}
