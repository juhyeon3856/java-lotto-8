package lotto;

import java.util.List;

public class LottoChecker {
    private final String[] prizeByRankString = {"0", "2,000,000,000", "30,000,000", "1,500,000", "50,000",
            "5,000"}; // 등수별 당첨 상금
    private final int[] prizeByRank = {0, 2_000_000_000, 30_000_000, 1_500_000, 50_000, 5_000}; // 등수별 당첨 상금
    private final String[] rankDetails = {"-", "6개 일치", "5개 일치, 보너스 볼 일치", "5개 일치", "4개 일치", "3개 일치"};

    private int[] winningRankCount = new int[6]; //  1등부터 5등까지, 당첨없음 = 0등
    private long myPrize = 0;
    private final List<Lotto> myLotto;
    private final WinningNumbers winningNumbers;

    LottoChecker(List<Lotto> myLotto, WinningNumbers winningNumbers) {
        this.myLotto = myLotto;
        this.winningNumbers = winningNumbers;
        checkResult();
    }

    public void winningDetails() {
        for (int i = 5; i > 0; i--) {
            System.out.println(rankDetails[i] + " (" + prizeByRankString[i] + "원) - " + winningRankCount[i] + "개");
        }
    }

    public void winningRate() {
        int pay = myLotto.size() * 1000;
        double profitRate = ((double) myPrize / pay) * 100;
        System.out.println("총 수익률은 " + String.format("%.1f", profitRate) + "%입니다.");
    }

    private void checkResult() {
        for (Lotto lotto : myLotto) {
            int rank = lottoResult(lotto);
            winningRankCount[rank]++;
            myPrize += prizeByRank[rank];
        }
    }

    private int lottoResult(Lotto lotto) {
        int winningCount = 0;
        boolean bonusCount = false;

        for (int num : lotto.getNumbers()) {
            NumberType numtype = winningNumbers.getNumberType(num);
            if (numtype == NumberType.WINNING) {
                winningCount++;
            }
            bonusCount = numtype == NumberType.BONUS;
        }

        if (winningCount == 6) {
            return 1;
        }
        if (winningCount == 5) {
            if (bonusCount) {
                return 2;
            }
            return 3;
        }
        if (winningCount == 4) {
            return 4;
        }
        if (winningCount == 3) {
            return 5;
        }
        return 0;
    }
}
