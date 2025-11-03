package lotto.domain.result;

import lotto.domain.rank.Rank;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public final class WinningResult {
    private final Map<Rank, Integer> counts; // 등수별 개수

    public WinningResult(Map<Rank, Integer> counts) {
        Map<Rank, Integer> copy = new EnumMap<>(Rank.class);
        for (Rank r : counts.keySet()) copy.put(r, counts.getOrDefault(r, 0));
        this.counts = Collections.unmodifiableMap(copy);
    }

    public int totalPrize() {
        int sum = 0;
        for (Map.Entry<Rank, Integer> e : counts.entrySet()) {
            sum += e.getKey().getPrize() * e.getValue();
        }
        return sum;
    }

    public Map<Rank, Integer> getCounts() {
        return counts;
    }
}
