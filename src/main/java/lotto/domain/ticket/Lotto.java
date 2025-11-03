package lotto.domain.ticket;

import lotto.error.DuplicateNumberException;
import lotto.error.InvalidLottoNumberException;

import java.util.*;

public class Lotto {
    private static final int SIZE = 6;
    private static final int MIN = 1;
    private static final int MAX = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        List<Integer> copy = new ArrayList<>(numbers);
        Collections.sort(copy);
        this.numbers = Collections.unmodifiableList(copy);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        Set<Integer> set = new HashSet<>();
        for (Integer n : numbers) {
            if (n == null || n < MIN || n > MAX) {
                throw new InvalidLottoNumberException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
            if (!set.add(n)) {
                throw new DuplicateNumberException("[ERROR] 중복된 로또 번호가 있습니다.");
            }
        }
    }

    public int countMatches(Set <Integer> targets) {
        int cnt = 0;
        for (Integer n : numbers) {
            if (targets.contains(n)) {
                cnt++;
            }
        }
        return cnt;
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
