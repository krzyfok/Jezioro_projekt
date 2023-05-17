package org.example;

import java.awt.*;
import java.util.Random;

public abstract class Fish {

    protected int speed;
    protected int coY;
    protected int coX;
    protected int size;

    protected int power;
    protected int agility;
    protected int health;
    protected boolean gobackx;
    protected boolean gobacky;
    public  void position(int size)
    {
        //tu na dole ustawiana jest pierwsza lokalizacja ryby, z uwzglednieniem warunku, zeby byla na planszy
        Random rand = new Random();

        this.coX=((rand.nextInt(Map.size-2* Map.border-size))+ Map.border);
        this.coY=((rand.nextInt(Map.size- Map.upBorder- Map.downBorder-size))+ Map.upBorder);

    }
    public void swim(Graphics g){}

}
