package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class WinningNumbers {
    private final int LOTTO_SIZE = 45;

    private NumberType[] numberTypes;
    private List<Integer> winningNumbers;
    private int bonusNumber;

    public int getBonusNumber() {
        return bonusNumber;
    }

    public NumberType[] getNumberTypes() {
        return numberTypes;
    }

    public List<Integer> getWinningNumber() {
        return winningNumbers;
    }

    public NumberType getNumberType(int number) {
        if (number < 1 || number > LOTTO_SIZE) {
            String errorMessage = "[ERROR] 해당 번호는 존재하지 않습니다.";
            System.out.println(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return numberTypes[number];
    }


    public WinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        getWinningNumbers();
        System.out.println("보너스 번호를 입력해 주세요.");
        getBonusNumbers();
    }

    private void getWinningNumbers() {
        String[] input = Console.readLine().split(",");
        if (input.length != 6) {
            String errorMessage = "[ERROR] 로또 번호는 6개여야 합니다.";
            System.out.println(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        numberTypes = new NumberType[LOTTO_SIZE + 1];
        for (int i = 1; i <= 45; i++) {
            numberTypes[i] = NumberType.NONE;  // 초기값은 모두 뽑히지 않음
        }

        winningNumbers = new ArrayList<>();

        for (String s : input) {
            int number = getNumber(s);
            numberTypes[number] = NumberType.WINNING;
            winningNumbers.add(number);
        }
    }

    private void getBonusNumbers() {
        String input = Console.readLine();
        int number = getNumber(input);
        numberTypes[number] = NumberType.WINNING;
        bonusNumber = number;
    }

    private int getNumber(String s) {
        int number;

        try {
            number = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            String errorMessage = "[ERROR] 숫자를 입력하세요.";
            System.out.println(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        if (number < 1 || number > 45) {
            String errorMessage = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";
            System.out.println(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        if (numberTypes[number] != NumberType.NONE) {
            String errorMessage = "[ERROR] 동일한 수를 입력할 수 없습니다.";
            System.out.println(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return number;
    }

}
