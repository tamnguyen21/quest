package fr.formation;

public interface MonInterfaceFonctionnelle {
    public String test(String test);

    public default void test2() {
        System.out.println("Blabla test2");
    }
}
