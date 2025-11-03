package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;

public class MyLottoList {
    private final int price;
    private final ArrayList<Lotto> lottos;

    public MyLottoList() {
        price = getPrice();
        lottos = getLottos(price / 1000);
    }

    public ArrayList<Lotto> getLottos() {
        return lottos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(price / 1000);
        sb.append("개를 구매했습니다.\n");
        for (Lotto lotto : lottos) {
            sb.append(lotto.toString()).append("\n");
        }
        return sb.toString();
    }

    private ArrayList<Lotto> getLottos(int cnt) {
        LottoMaker lottoMaker = new LottoMaker();
        ArrayList<Lotto> newLottos = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            newLottos.add(lottoMaker.getLottoNumber());
        }
        return newLottos;
    }

    private int getPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();
        int inputPrice;
        try {
            inputPrice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            String errorMessage = "[ERROR] 숫자만 가능합니다.";
//            System.out.println(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        if (inputPrice % 1000 != 0) {
            String errorMessage = "[ERROR] 1000원 단위로 구매가 가능합니다.";
            System.out.println(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return inputPrice;
    }


}
