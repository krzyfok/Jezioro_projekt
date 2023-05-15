package org.example;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Fish {

    protected int speed;
    protected int coY;
    protected int coX;
    protected int size;

    protected int power;
    protected int agility;
    protected int health;
    protected boolean goback;           //jeszcze nie wiem jak ogarnac jak to zrobic zeby ryby sie odbijaly od sciany, wiec na probe zrobilam im zmienna boolowska, ktora po uderzeniu w sciane sie zmienia i zmieniaja kierunek w metodzie plywania

    public  void position(int size)
    {
        //tu na dole ustawiana jest pierwsza lokalizacja ryby, z uwzglednieniem warunku, zeby byla na planszy
        Random rand = new Random();
       // do {
       //     this.coX = rand.nextInt()%(Draw.size-Draw.border);
      //  }while(coX <= Draw.border || coX >= Draw.size-2*Draw.border);
        this.coX=((rand.nextInt(Draw.size-2*Draw.border-size))+Draw.border+(size/2));
       // do {
       //     this.coY = rand.nextInt()%(Draw.size-Draw.border);
      //  }while(coY <= Draw.upBorder || coY >= Draw.size-2*Draw.border);
        this.coY=((rand.nextInt(Draw.size-Draw.upBorder-Draw.downBorder-size))+Draw.upBorder+(size/2));

    }
    public static void swim( Fish fish, int size){        //tu musi byc to g do rysowania. W tej metodzie od razu rysuje te ryby(na razie kulki), mozna to podzielic na wiecej metod. Np oddzielna do rysowania i oddzielna do zmieniania wspolrzednych




        //zmienienie wspolrzednych ryby (poruszenie sie ryby)
        if(fish.coX >= Draw.border && fish.coX <= Draw.size-Draw.border) {
            if(fish.goback==true)
                fish.coX += fish.speed;
            else
                fish.coX -= fish.speed;
        }
        else {                                              //tutaj ryba zawraca gdy spotka krawedz
            if(fish.goback==true) {
                fish.goback=false;
                fish.coX -= fish.speed;
            }
            else {
                fish.goback=true;
                fish.coX += fish.speed;
            }
        }

    }
}
