package lotto;

public class Application {
    public static void main(String[] args) {
        try {
            MyLottoList lottoList = new MyLottoList();
            System.out.print(lottoList);

            WinningNumbers winningNumbers = new WinningNumbers();
            LottoChecker lottoChecker = new LottoChecker(lottoList.getLottos(), winningNumbers);

            System.out.println("## 당첨 통계");
            System.out.println();
            lottoChecker.winningDetails();
            lottoChecker.winningRate();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
