package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Draw extends JFrame{
    public static int size = 800;
    public static int border = 20;      //tutaj ustawiam szerokosc ramki. Potem to zmienic na dodatkowo border-dolny, zeby dodac piasek
    public static int upBorder = 100;   //powierzchnia
    public static int downBorder=50;

    Draw() {                                               //konstruktor, ustawienie wielkosci okienka
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(size, size);
        this.setVisible(true);
        setLayout(null);
    }

    public void clean(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0,upBorder,size,size);
        g.setColor(Color.CYAN);
        g.fillRect(0,0, size, upBorder);
        g.setColor(Color.YELLOW);
        g.fillRect(0,size-downBorder,size, downBorder);
    }

    @Override
    public void paint(Graphics g) {                 //klasa do rysowania
        GreenFish green = new GreenFish();
       RedFish red = new RedFish();
       Shark shark = new Shark();
        Scanner scanner = new Scanner(System.in);
        for(int i=0; i<1000; i++) {                 //na razie ustawilam ilosc klatek do 1000, zeby mozna bylo zobaczyc czy to dziala

            //rysuje jeziorko (i czysci namalowane ryby z poprzedniej klatki)
            clean(g);
            shark.swim(g, shark);
            green.swim(g);
            red.swim(g);

            Fisherman.plywanie(g);
            Fisherman.lowienie(g);
            //innaryba.plywanie...
            //rybak.zrobcos...
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // String z = scanner.nextLine();          //tutaj taki stop na razie, zeby animacja dzialala tylko gdy enter jest wcisniety, zeby nie przelecialo od razu

        }

    }
}