package lotto.domain.ticket;

import lotto.error.DuplicateNumberException;
import lotto.error.InvalidLottoNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
                .isInstanceOf(DuplicateNumberException.class);
    }

    @DisplayName("로또 번호에 범위를 벗어난 값(1~45)이 포함되면 예외가 발생한다.")
    @Test
    void 로또_번호에_범위를_벗어난_값이_있으면_예외() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(InvalidLottoNumberException.class);
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(InvalidLottoNumberException.class);
    }

    @DisplayName("로또는 생성 시 오름차순으로 정렬되어 저장된다.")
    @Test
    void 로또는_항상_오름차순() {
        Lotto lotto = new Lotto(List.of(8, 1, 45, 3, 22, 14));
        assertThat(lotto.toString()).isEqualTo("[1, 3, 8, 14, 22, 45]");
    }
}
