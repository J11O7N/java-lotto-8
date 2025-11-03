package lotto.domain.winning;

import lotto.domain.rank.Rank;
import lotto.domain.ticket.Lotto;
import lotto.error.DuplicateNumberException;
import lotto.error.InvalidLottoNumberException;

import java.util.*;

public final class WinningNumbers {
    private final Set<Integer> numbers; // 당첨 6개
    private final int bonus;

    public WinningNumbers(List<Integer> sixNumbers, int bonus) {
        this.numbers = validateWinningNumbers(sixNumbers);
        validateBonus(bonus, numbers);
        this.bonus = bonus;
    }

    private Set<Integer> validateWinningNumbers(List<Integer> nums) {
        if (nums == null || nums.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
        Set<Integer> set = new HashSet<>();
        for (Integer n : nums) {
            if (n == null || n < 1 || n > 45) {
                throw new InvalidLottoNumberException("[ERROR] 번호는 1~45 범위여야 합니다.");
            }
            if (!set.add(n)) {
                throw new DuplicateNumberException("[ERROR] 당첨 번호에 중복이 있습니다.");
            }
        }
        return Collections.unmodifiableSet(set);
    }

    private void validateBonus(int bonus, Set<Integer> six) {
        if (bonus < 1 || bonus > 45) {
            throw new InvalidLottoNumberException("[ERROR] 보너스 번호는 1~45 범위여야 합니다.");
        }
        if (six.contains(bonus)) {
            throw new DuplicateNumberException("[ERROR] 보너스 번호가 당첨 번호와 중복입니다.");
        }
    }

    public Rank evaluate(Lotto ticket) {
        int matches = ticket.countMatches(numbers);
        boolean bonusMatched = ticket.contains(bonus);
        return Rank.of(matches, bonusMatched);
    }
}
