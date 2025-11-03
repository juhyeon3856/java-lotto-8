package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성

    @DisplayName("로또 번호의 개수가 6개보다 적어도 예외가 발생한다.")
    @Test
    void 로또_번호의_개수가_6개보다_적으면_예외() { // (추가) 6개 미만 예외 검증
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5))) // (추가) 5개 입력
                .isInstanceOf(IllegalArgumentException.class); // (추가)
    }

    @DisplayName("정상 입력(서로 다른 6개)이면 객체가 생성되고 toString은 목록을 그대로 보여준다.")
    @Test
    void 정상_입력_toString_검증() { // (추가) 정상 케이스 확인
        Lotto lotto = new Lotto(List.of(8, 21, 23, 41, 42, 43)); // (추가) 정상 6개
        assertThat(lotto.getNumbers()).hasSize(6); // (추가) 크기 6 확인
        assertThat(lotto.toString()).isEqualTo("[8, 21, 23, 41, 42, 43]"); // (추가) toString 형식 검증
    }
}
