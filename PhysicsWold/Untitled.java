import java.util.*;

public class Untitled {
    public static void main(String[] args) {
        Cat robert = new Cat("roger", 13);
        System.out.println(robert.age);
        robert.miaou();

        Explosion tchernobil = new Explosion("pologne", 30, 41.53f);
        System.out.println(tchernobil.nombreDeMort());

        Atome naoh = new Atome("NaOH", 20, 1, 0.3f);
        System.out.println(naoh.getMasse());

        try {
            System.out.print("un int random :");
            Scanner sc = new Scanner(System.in);
            System.out.print(sc.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("c'etait pas un int sale merde");
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        int i = 0;
        while (i < 10) {
            i++;

        }

        i = 2;
        if (i == 3) {
            System.out.println("bla bla bla");
        } else if (i == 2) {
            System.out.println("v");
        }

        switch (i) {
            case 3:
                System.out.println("yrf");
                break;
            case 2:
                System.out.println("fg");
                break;
            case 1:
                System.out.println(" wx");
                break;
            case 0:
                System.out.println("bgd");
                break;
            default:
                System.out.println("g fbg");
                break;
        }

        String strA = "fef";
        String strB = "fnvc";
        System.out.println(strA.concat(strB));
    }
}

class Cat {
    String nom;
    int age;

    public Cat(String nomDuChat, int ageDuChat) {
        nom = nomDuChat;
        age = ageDuChat;
    }

    public void miaou() {
        System.out.println("je miaule");
    }
}

// sus
class Explosion extends Cat {
    float rayon;

    public Explosion(String nom, int age, float rayon) {
        super(nom, age);
        this.rayon = rayon;
    }

    public float nombreDeMort() {
        return 3 * this.rayon;
    }
}

class Atome extends Explosion {
    private float masse;

    public float getMasse() {
        return (this.masse);
    }

    public Atome(String nom, int age, float rayon, float masseAtome) {
        super(nom, age, rayon);
        this.masse = masseAtome;
    }

}