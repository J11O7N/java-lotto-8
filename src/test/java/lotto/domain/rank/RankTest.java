package lotto.domain.rank;

import lotto.domain.rank.Rank;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RankTest {

    @Test
    void of_매핑_검증() {
        assertThat(Rank.of(6, false)).isEqualTo(Rank.SIX);
        assertThat(Rank.of(5, true)).isEqualTo(Rank.FIVE_BONUS);
        assertThat(Rank.of(5, false)).isEqualTo(Rank.FIVE);
        assertThat(Rank.of(4, false)).isEqualTo(Rank.FOUR);
        assertThat(Rank.of(3, false)).isEqualTo(Rank.THREE);
        assertThat(Rank.of(2, false)).isEqualTo(Rank.NONE);
    }

    @Test
    void 상금_검증() {
        assertThat(Rank.THREE.getPrize()).isEqualTo(5_000);
        assertThat(Rank.FOUR.getPrize()).isEqualTo(50_000);
        assertThat(Rank.FIVE.getPrize()).isEqualTo(1_500_000);
        assertThat(Rank.FIVE_BONUS.getPrize()).isEqualTo(30_000_000);
        assertThat(Rank.SIX.getPrize()).isEqualTo(2_000_000_000);
        assertThat(Rank.NONE.getPrize()).isEqualTo(0);
    }
}
