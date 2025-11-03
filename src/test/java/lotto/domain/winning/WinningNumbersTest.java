package lotto.domain.winning;

import lotto.domain.winning.WinningNumbers;
import lotto.domain.rank.Rank;
import lotto.domain.ticket.Lotto;
import lotto.error.DuplicateNumberException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WinningNumbersTest {

    @Test
    void 당첨번호는_반드시_6개() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5), 7))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스번호는_당첨6개와_중복되면_예외() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(DuplicateNumberException.class);
    }

    @Test
    void evaluate_5개_보너스O면_FIVE_BONUS() {
        WinningNumbers w = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Rank rank = w.evaluate(new Lotto(List.of(1, 2, 3, 4, 5, 7)));
        assertThat(rank).isEqualTo(Rank.FIVE_BONUS);
    }

    @Test
    void evaluate_5개_보너스X면_FIVE() {
        WinningNumbers w = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Rank rank = w.evaluate(new Lotto(List.of(1, 2, 3, 4, 5, 8)));
        assertThat(rank).isEqualTo(Rank.FIVE);
    }

    @Test
    void evaluate_4개면_FOUR() {
        WinningNumbers w = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Rank rank = w.evaluate(new Lotto(List.of(1, 2, 3, 4, 9, 10)));
        assertThat(rank).isEqualTo(Rank.FOUR);
    }

    @Test
    void evaluate_3개면_THREE() {
        WinningNumbers w = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Rank rank = w.evaluate(new Lotto(List.of(1, 2, 3, 9, 10, 11)));
        assertThat(rank).isEqualTo(Rank.THREE);
    }

    @Test
    void evaluate_2개_이하면_NONE() {
        WinningNumbers w = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Rank rank = w.evaluate(new Lotto(List.of(1, 2, 9, 10, 11, 12)));
        assertThat(rank).isEqualTo(Rank.NONE);
    }
}
