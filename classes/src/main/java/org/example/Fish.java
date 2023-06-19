package org.example;


import java.util.ArrayList;
import java.util.Random;

/**
 * Fish pływa i wykonuję określone akcje
 */

public abstract class Fish implements Swim{

    /** prędkosć  w osi X */
    protected int speedX;
    /** prędkość  w osi Y */
    protected int speedY;
    /** Współrzedna  Y */
    protected int coY;
    /** Współrzędna  X*/
    protected int coX;
    /** Rozmiar */
    protected int size;
    /** Siła  */
    protected int power;
    /** Zwinność */
    protected int agility;
    /** Punkty życia */
    protected int health;
    /** Kierunek w osi X */
    protected boolean gobackx;
    /** Kierunek w osi Y */
    protected boolean gobacky;
    /** Zasięg rozmnażania */
    protected static int breedrange = 20;
    /** Zasięg ataku */
    protected int attackrange;
    /** głód */
    protected double hunger;
    /** licznik odpowiadający za możliwosć rozmnażania */
    protected double dobreed;
    /** limit ilości ryb */
    private int maxOfIndividual = 45;
    /** minimalna wartośc licznika dobreed potrzebna do rozmnażania */
    private int minBreed = 30;
    /** Maksymalna odległość nowej ryby od rodzica */
    private int breedArea = 30;
    /** minimalna wartość głodu by przeprowadzić atak */
    private int minHunger = 10;


    /**
     * Metoda ustawia pierwszą lokalizacje ryby, uwzględniając warunek by była na mapie
     * @param size rozmiar ryby
     */
    public  void position(int size) {

        Random rand = new Random();
        this.coX=((rand.nextInt(Map.size-2* Map.border-2*size))+ Map.border+size);
        this.coY=((rand.nextInt(Map.size- Map.upBorder- Map.downBorder-2*size-2*Map.border))+ Map.upBorder+size+Map.border);
}


    /**
     * Metoda mierzy odległość między dwoma rybami
     * @param fish1 ryba 1
     * @param fish2 ryba 2
     * @return zwracana odległość
     */
    public static double distance(Fish fish1, Fish fish2) {
        return (Math.sqrt((fish1.coX+fish1.size/2-fish2.coX-fish2.size/2)*(fish1.coX+fish1.size/2-fish2.coX-fish2.size/2)+(fish1.coY+fish1.size/2-fish2.coY-fish2.size/2)*(fish1.coY+fish1.size/2-fish2.coY-fish2.size/2)));
    }

    /**
     * Metoda przeprowadza rozmnażanie ryb
     * @param fish2 ryba z którą ryba na której została wywołana metoda przeprowadza rozmnażanie
     */
    public void reproduct(Fish fish2) {
        Random rand = new Random();

/**
 * Sprawdzanie czy mogą się rozmnażać oraz ograniczenie osobników danej odmiany
 */

        if(this.dobreed<=minBreed || fish2.dobreed<=minBreed)
            return;
        if(this.numberOfFish()>=maxOfIndividual)
            return;

/**
 * Sprawdzanie czy dystans między rybami jest mniejszy niż breedrange
 */

        if (distance(this, fish2) <= breedrange) {

            if (this instanceof GreenFish)
                Map.tableOfFish.add(new GreenFish());
            else if (this instanceof RedFish)
                Map.tableOfFish.add(new RedFish());
            else if (this instanceof YellowFish)
                Map.tableOfFish.add(new YellowFish());


/**
 * Ustawienie współrzędnych nowych ryb oraz ewentulana korekta położenia ryby
 */


            if(!(this instanceof YellowFish)) {
                Map.tableOfFish.get(Map.tableOfFish.size() - 1).coX = this.coX - rand.nextInt() % breedArea;
                Map.tableOfFish.get(Map.tableOfFish.size() - 1).coY = this.coY - rand.nextInt() % breedArea;



                if(this.coY<=Map.upBorder+Map.border) {
                    this.coY+=2*this.size;
                    this.gobacky=true;
                }
                if(this.coY>=Map.size-Map.downBorder-Map.border-this.size) {
                    this.coY-=2*this.size;
                    this.gobacky=false;
                }
            }

/**
 * Zablokowanie możliwości rozmnażania ryb przez określony czas
 */
            this.dobreed = 0;
            fish2.dobreed = 0;
        }

    }

    /**
     * Metoda przeprowadza akcję ataku między dwoma rybami, słabsza ryba traci punkty życia
     * @param fish2 2 ryba która jest atakowana przez rybę na której została wywołana metoda
     */

    public void attack(Fish fish2) {

        if(this.hunger<minHunger)
            return;
        if (distance(this, fish2) <= this.attackrange)
            if (this.power > fish2.power) {
                fish2.health -= this.power;
                this.hunger = 0;
            }
    }

    /**
     * Metoda sprawdza czy któraś z ryb jest martwa, jeżeli tak zostaje usunięta z tablicy
     * @param table tablica ryb
     * @return jeżeli ryba umiera zwracana jest wartość 1, jeżeli nie to 0
     */
    public int die(ArrayList table) {
        if (this.health<=0) {
            table.remove(this);
            return 1;
        }
        else
            return 0;
    }

    /**
     * Metoda liczy ryby w tabeli tableOfFish
     * @return zwracana jest liczba ryb w tabeli
     */
    public int numberOfFish() {
        int number=0;
        for(int i=0; i<Map.tableOfFish.size(); i++)
            if(Map.tableOfFish.get(i).getClass() == this.getClass())
                number++;

        return number;
    }

    /**
     * Metoda zwraca współrzędną X
     */
    public int give_coX()
    {
        return coX;
    }
    /**
     * Metoda zwraca współrzędną Y
     */
    public int give_coY()
    {
        return coY;
    }
    /**
     * Metoda zwraca rozmiar
     */
    public int give_size()
    {
        return size;
    }
    /**
     * Metoda zwraca attackrange
     */
    public int give_attackrange()
    {
      return attackrange;
    }
    /**
     * Metoda zwraca punkty życia
     */

    public int give_health()
    {
        return health;
    }

}
