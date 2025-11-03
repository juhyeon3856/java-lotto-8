package lotto;

import java.util.HashSet;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            String errorMessage = "[ERROR] 로또 번호는 6개여야 합니다.";
            System.out.println(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (new HashSet<>(numbers).size() != 6) {
            String errorMessage = "[ERROR] 중복된 숫자가 있습니다.";
            System.out.println(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    // TODO: 추가 기능 구현

    @Override
    public String toString() {
        return numbers.toString();
    }
}
