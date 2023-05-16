package org.example;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
public class GreenFish extends Fish{
    static ArrayList<GreenFish> tablicaRyb;       //przechowuje wszystkie rybki tej odmiany


    GreenFish(int fishnumber) {
        tablicaRyb = new ArrayList();               //tutaj utworzenie rybek w ilosci zadanej w mainie

        for(int i=0; i<fishnumber; i++)
            tablicaRyb.add(new GreenFish());  //odwolanie do konstruktora "pojedynczej ryby", ktora jest nizej

    }

    GreenFish() {  //"konstruktor pojedynczej ryby"
        Random rand = new Random();

        this.speed = 5;
        this.size = 10;
        this.gobackx = rand.nextBoolean();
        this.health=20;
        this.power=10;
        this.agility=10;
        this.position(size);


    }
    public void swim(Graphics g) {
        g.setColor(Color.GREEN);
        for (GreenFish fish : tablicaRyb) {
            g.fillOval(fish.coX, fish.coY, fish.size, fish.size);
            if(fish.coX >= Map.border && fish.coX <= Map.size- Map.border-size) {
                if(fish.gobackx==true)
                    fish.coX += fish.speed;
                else
                    fish.coX -= fish.speed;
            }
            else {                                              //tutaj ryba zawraca gdy spotka krawedz
                if(fish.gobackx==true) {
                    fish.gobackx=false;
                    fish.coX -= fish.speed;
                }
                else {
                    fish.gobackx=true;
                    fish.coX += fish.speed;
                }
            }
            if(fish.coY >= Map.upBorder && fish.coY <= Map.size- Map.downBorder-size) {
                if(fish.gobacky==true)
                    fish.coY += fish.speed/2;
                else
                    fish.coY -= fish.speed/2;
            }
            else {                                              //tutaj ryba zawraca gdy spotka krawedz
                if(fish.gobacky==true) {
                    fish.gobacky=false;
                    fish.coY -= fish.speed/2;
                }
                else {
                    fish.gobacky=true;
                    fish.coY += fish.speed/2;
                }
            }
        }
    }
}
