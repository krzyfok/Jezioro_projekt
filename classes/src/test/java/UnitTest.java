import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.sqrt;

/**
 * Testy jednostkowe
 */
public class UnitTest{
    Random rand = new Random();
    static ArrayList<Fish> table;

    /**
     * Metoda sprawdza czy ryba jest w granicach mapy
     * @param x współrzędna X ryby
     * @param y współrzędna Y ryby
     * @param size rozmiar ryby
     * @return zwracana odległość
     */
    static boolean postion(int x, int y, int size)
    {
        if(x>=Map.border+size && x<=Map.size-Map.border-size && y>=Map.upBorder+Map.border && y<=Map.size-Map.downBorder-Map.border-size) return true;
        else return false;
    }

    /**
     * Metoda zwraca odległość między dwoma rybami
     * @param fish1 1 ryba
     * @param fish2 2 ryba
     * @return zwracana odległość
     */
    static public double distance(Fish fish1, Fish fish2) {
        return (Math.sqrt((fish1.give_coX()+fish1.give_size()/2-fish2.give_coX()-fish2.give_size()/2)*(fish1.give_coX()+fish1.give_size()/2-fish2.give_coX()-fish2.give_size()/2)+(fish1.give_coY()+fish1.give_size()/2-fish2.give_coY()-fish2.give_size()/2)*(fish1.give_coY()+fish1.give_size()/2-fish2.give_coY()-fish2.give_size()/2)));
    }


    /**
     * Test sprawdza czy ryba tworzona jest w granicy mapy
     */
    @RepeatedTest(10)
    void test_postion()
    {
      Fish fish= new RedFish();
        Assertions.assertTrue(postion(fish.give_coX(), fish.give_coY(), fish.give_size()));

    }

    /**
     * Test sprawdza czy akcja ataku jest prawidłowo przeprowadzana
     */
    @RepeatedTest(10)
    void test_attack()
    {

        Fish green= new GreenFish(rand.nextInt(40), rand.nextInt(40),70);
        Fish red = new RedFish(rand.nextInt(40), rand.nextInt(40), 50,100,40);
        red.attack(green);
        if(distance(green,red)<=red.give_attackrange())
        {
            Assertions.assertEquals(70-100,green.give_health());
        }
        else
        {
            Assertions.assertEquals(70,green.give_health());
        }

    }

    /**
     * Test sprawdza czy ryby prawidłowo umierają
     */
    @RepeatedTest(10)
    void test_die()
    {

        table= new ArrayList();
        int health=rand.nextInt(1);
        int expected;
        if(health==1) expected=0;
        else expected=1;


        table.add(new RedFish(0,0,0, health,40));
        Assertions.assertEquals(expected,table.get(0).die(table));



    }


}





