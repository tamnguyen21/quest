package fr.formation;

import java.util.Arrays;

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

    public int add(String value) {
        if (value == null || value.isBlank()) {
            return 0;
        }

        String[] values = value.split("[,;\\n]+");

        int total = 0;

        for (String val : values) {
            int valInt = Integer.parseInt(val);

            if (valInt < 0) {
                throw new NegativeNotAllowedException();
            }

            total += valInt;
        }

        // return total;

        return Arrays.asList(values).stream()
            // .map(val -> Integer.parseIn(val))
            .map(Integer::parseInt)

            .peek(val -> {
                if (val < 0) {
                    throw new NegativeNotAllowedException();
                }
            })

            // .reduce(0, (total, val) -> total + val)
            .reduce(0, Integer::sum)
        ;
    }
}
