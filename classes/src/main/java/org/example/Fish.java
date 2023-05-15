package org.example;
import java.awt.*;
import java.util.ArrayList;
public class Fish {
    int speed;
    int coY;
    int coX;
    int size;
    boolean goback;           //jeszcze nie wiem jak ogarnac jak to zrobic zeby ryby sie odbijaly od sciany, wiec na probe zrobilam im zmienna boolowska, ktora po uderzeniu w sciane sie zmienia i zmieniaja kierunek w metodzie plywania

    public static void swim( Fish fish){        //tu musi byc to g do rysowania. W tej metodzie od razu rysuje te ryby(na razie kulki), mozna to podzielic na wiecej metod. Np oddzielna do rysowania i oddzielna do zmieniania wspolrzednych




        //zmienienie wspolrzednych ryby (poruszenie sie ryby)
        if(fish.coX >= Draw.border && fish.coX <= Draw.size-2*Draw.border) {
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