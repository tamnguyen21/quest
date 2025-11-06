package fr.formation;

public class ProgFonctionnelle {
    public static void main(String[] args) {


        // Utiliser des interfaces fonctionnelles
        // Une interface qui a UNE ET UNE SEULE méthode à implémenter

        // Consumer
        // Function
        // Predicate
        // Supplier
        // Runnable

        Runnable runnable = () -> {
            System.out.println("Runnable en cours !");
        };

        runnable.run();

        DemoRunnable demoR = new DemoRunnable();

        demoR.run();

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable ici aussi !!!");
            }
        };

        runnable2.run();

        MonInterfaceFonctionnelle mif = arg1 -> {
            System.out.println("OK OK OK");
            return "ok";
        };

        // Vous pouvez enlever les accolades SI il n'y a qu'une seule instruction
        // Et dans ce cas, le mot-clé "return" est implicite
        MonInterfaceFonctionnelle mif2 = arg1 -> "ok 2";

        mif.test("La valeur");
        mif.test2();
    }

    public static class DemoRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Runnable ici !");
        }
    }
}
