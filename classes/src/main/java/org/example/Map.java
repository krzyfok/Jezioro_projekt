package org.example;



import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class Map extends JFrame{
    public static int size = 600;
    public static int border = 10;
    public static int upBorder = 100;   //powierzchnia
    public static int downBorder=50;
    public static int time;
    static ArrayList<Fish> tableOfFish;

    Map() {                                               //konstruktor, ustawienie wielkosci okienka
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

    public static void setFish(int green, int red, int yellow, int time_) {
        tableOfFish = new ArrayList();
        tableOfFish.add(new Shark());
        for(int i=0; i<red; i++)
            tableOfFish.add(new RedFish());
        for(int i=0; i<green; i++)
            tableOfFish.add(new GreenFish());
        for(int i=0; i<yellow; i++)
            tableOfFish.add(new YellowFish());
        time=time_*20;
    }
    
    public static void data_export()
    {
        int red=0, green=0 , yellow=0;
        for (Fish fish : tableOfFish) {

            if (fish instanceof GreenFish) {
                green++;

            } else if (fish instanceof RedFish) {
                red++;

            } else if (fish instanceof YellowFish) {
                yellow++;
            }
        }



            File myObj = new File("data.txt");
             try{
        PrintWriter save = new PrintWriter("data.txt");
        save.println("CZAS TRWANIA SYMULACJI: "+ time/20+" SEKUND");
        save.println("RYB CZERWONYCH: "+ red);
        save.println("RYB ZIELONYCH: "+ green);
        save.println("RYB ZOLTYCH: "+yellow);
        save.println("RYBY ZLOWIONE: "+ Fisherman.fish_caught);
        save.close();
    }catch (Exception e)
             {

             }


    }


    @Override
    public void paint(Graphics g) {

    Fisherman fisherman = new Fisherman();

        for(int i=0; i<time; i++) {

            //rysuje jeziorko (i czysci namalowane ryby z poprzedniej klatki)
            clean(g);

            //plywanie ryb:
            for(int j=0; j<tableOfFish.size(); j++)
                tableOfFish.get(j).swim(g);

            //rozmnazanie ryb:
            for(int j=0; j<tableOfFish.size(); j++) {
                if(tableOfFish.get(j) instanceof YellowFish)            //zolte rozmnazaja sie same ze soba
                    tableOfFish.get(j).reproduct(tableOfFish.get(j));
                for(int k=0; k<tableOfFish.size(); k++)
                    if(j!=k && tableOfFish.get(j).getClass()==tableOfFish.get(k).getClass())
                        tableOfFish.get(j).reproduct(tableOfFish.get(k));
            }

            //atak ryb:
            for(int j=0; j<tableOfFish.size(); j++)
                for(int k=0; k<tableOfFish.size(); k++)
                    if(j!=k && tableOfFish.get(j).getClass()!=tableOfFish.get(k).getClass())
                        tableOfFish.get(j).attack(tableOfFish.get(k));

            //smierc ryb:
            for(int j=0; j<tableOfFish.size(); j++)
                if(tableOfFish.get(j).die(Map.tableOfFish)==1)
                    j--;


            fisherman.swim(g);
            fisherman.fishing(g, Map.tableOfFish);


            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
        data_export();

    }
}
