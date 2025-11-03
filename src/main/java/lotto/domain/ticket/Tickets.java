package lotto.domain.ticket;

import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.rank.Rank;
import lotto.domain.result.WinningResult;
import lotto.domain.winning.WinningNumbers;

import java.util.*;

public final class Tickets {
    private final List<Lotto> tickets;

    public Tickets(List<Lotto> tickets) {
        this.tickets = Collections.unmodifiableList(new ArrayList<>(tickets));
    }

    public static Tickets purchase(int count, LottoNumberGenerator generator) {
        if (count < 0) throw new IllegalArgumentException("[ERROR] 발행 장수는 음수가 될 수 없습니다.");
        List<Lotto> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Lotto(generator.generate()));
        }
        return new Tickets(list);
    }

    public WinningResult calculateResult(WinningNumbers winning) {
        Map<Rank, Integer> counts = new EnumMap<>(Rank.class);

        for (Lotto ticket : tickets) {
            Rank rank = winning.evaluate(ticket);

            if (!rank.isWinning()) {
                continue; // 꽝이면 그냥 건너뛰기
            }

            int currentCount = 0;
            if (counts.containsKey(rank)) {
                currentCount = counts.get(rank);
            }

            counts.put(rank, currentCount + 1);
        }

        return new WinningResult(counts);
    }


    public int size() {
        return tickets.size();
    }

    public List<Lotto> asList() {
        return tickets;
    }
}
