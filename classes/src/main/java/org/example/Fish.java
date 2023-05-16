package org.example;
import java.util.Random;

public abstract class Fish {

    protected int speed;
    protected int coY;
    protected int coX;
    protected int size;

    protected int power;
    protected int agility;
    protected int health;
    protected boolean gobackx;           //jeszcze nie wiem jak ogarnac jak to zrobic zeby ryby sie odbijaly od sciany, wiec na probe zrobilam im zmienna boolowska, ktora po uderzeniu w sciane sie zmienia i zmieniaja kierunek w metodzie plywania
    protected boolean gobacky;
    public  void position(int size)
    {
        //tu na dole ustawiana jest pierwsza lokalizacja ryby, z uwzglednieniem warunku, zeby byla na planszy
        Random rand = new Random();
       // do {
       //     this.coX = rand.nextInt()%(Draw.size-Draw.border);
      //  }while(coX <= Draw.border || coX >= Draw.size-2*Draw.border);
        this.coX=((rand.nextInt(Map.size-2* Map.border-size))+ Map.border);
       // do {
       //     this.coY = rand.nextInt()%(Draw.size-Draw.border);
      //  }while(coY <= Draw.upBorder || coY >= Draw.size-2*Draw.border);
        this.coY=((rand.nextInt(Map.size- Map.upBorder- Map.downBorder-size))+ Map.upBorder);

    }
    public static void swim( Fish fish, int size){        //tu musi byc to g do rysowania. W tej metodzie od razu rysuje te ryby(na razie kulki), mozna to podzielic na wiecej metod. Np oddzielna do rysowania i oddzielna do zmieniania wspolrzednych




        //zmienienie wspolrzednych ryby (poruszenie sie ryby)
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
