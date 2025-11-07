package fr.formation;

public class Calc {
    public int add(String a, String b) {
        if (a == null || b == null) {
            return 0;
        }

        Integer valA = Integer.parseInt(a);
        Integer valB = Integer.parseInt(b);

        return valA + valB;

        // if ("4".equals(a) && "6".equals(b)) {
        //     return 10;
        // }

        // if ("10".equals(a) && "5".equals(b)) {
        //     return 15;
        // }

        // return 11;
    }
}
