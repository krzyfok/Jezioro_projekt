import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class UnitTest{
    static boolean postion(int x, int y, int size)
    {
        if(x>=Map.border+size && x<=Map.size-Map.border-size && y>=Map.upBorder+Map.border && y<=Map.size-Map.downBorder-Map.border-size) return true;
        else return false;
    }



    @RepeatedTest(500)
    void test_postion()
    {
      Fish fish= new RedFish();

        Assertions.assertTrue(postion(fish.give_coX(), fish.give_coY(), fish.give_size()));

    }


}
