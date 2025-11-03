package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoMaker {
    public Lotto getLottoNumber() {
        List<Integer> lottoNumber = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(lottoNumber);
    }
}
