package lotto;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }


    @DisplayName("구매 금액이 1000원 단위가 아니면 예외 메시지가 출력된다.")
    @Test
    void 구매금액_1000단위아님_예외() { // (추가) "1500" → 1000 단위 아님
        assertSimpleTest(() -> {
            runException("1500"); // (추가)
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("당첨 번호가 6개가 아니면 예외 메시지가 출력된다.")
    @Test
    void 당첨번호_6개아님_예외() { // (추가) WinningNumbers 검증
        assertSimpleTest(() -> {
            runException(
                    "8000",            // (추가) 구매 금액
                    "1,2,3,4,5",       // (추가) 당첨 번호(5개)
                    "7"                // (추가) 보너스 번호(도달 전 예외 예정)
            );
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("당첨 번호가 범위를 벗어나면 예외 메시지가 출력된다.")
    @Test
    void 당첨번호_범위밖_예외() { // (추가) 0 포함
        assertSimpleTest(() -> {
            runException(
                    "8000",
                    "0,1,2,3,4,5", // (추가) 0은 범위 밖
                    "7"
            );
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("당첨 번호에 중복이 있으면 예외 메시지가 출력된다.")
    @Test
    void 당첨번호_중복_예외() { // (추가)
        assertSimpleTest(() -> {
            runException(
                    "8000",
                    "1,1,2,3,4,5", // (추가) 1 중복
                    "7"
            );
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("보너스 번호가 숫자가 아니면 예외 메시지가 출력된다.")
    @Test
    void 보너스번호_숫자아님_예외() { // (추가)
        assertSimpleTest(() -> {
            runException(
                    "8000",
                    "1,2,3,4,5,6",
                    "a"                // (추가) 숫자 아님
            );
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("보너스 번호가 범위를 벗어나면 예외 메시지가 출력된다.")
    @Test
    void 보너스번호_범위밖_예외() { // (추가)
        assertSimpleTest(() -> {
            runException(
                    "8000",
                    "1,2,3,4,5,6",
                    "46"               // (추가) 45 초과
            );
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외 메시지가 출력된다.")
    @Test
    void 보너스번호_중복_예외() { // (추가)
        assertSimpleTest(() -> {
            runException(
                    "8000",
                    "1,2,3,4,5,6", // (추가)
                    "6"            // (추가) 당첨 번호와 중복
            );
            assertThat(output()).contains("[ERROR] 동일한 수를 입력할 수 없습니다."); // (추가)
        });
    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
