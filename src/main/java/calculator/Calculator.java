package calculator;

import java.util.List;

public class Calculator {
    public int sum(List<Integer> list) {
        int sum = 0;
        for (Integer value : list) {
            sum += value;
        }
        return sum;
    }

    public int split(String input) {
        if(input == null || input.isBlank()) {
            return 0;
        }
        return 1;
    }
}
