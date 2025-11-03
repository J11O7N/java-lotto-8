package lotto.domain.rank;

public enum Rank {
    THREE(3, false, 5_000),
    FOUR(4, false, 50_000),
    FIVE(5, false, 1_500_000),
    FIVE_BONUS(5, true, 30_000_000),
    SIX(6, false, 2_000_000_000),
    NONE(0, false, 0);

    private final int matches;
    private final boolean bonus;   // 보너스 필요 여부
    private final int prize;

    Rank(int matches, boolean bonus, int prize) {
        this.matches = matches;
        this.bonus = bonus;
        this.prize = prize;
    }

    public static Rank of(int matches, boolean bonusMatch) {
        if (matches == 6) return SIX;
        if (matches == 5 && bonusMatch) return FIVE_BONUS;
        if (matches == 5) return FIVE;
        if (matches == 4) return FOUR;
        if (matches == 3) return THREE;
        return NONE;
    }

    public int getMatches() { return matches; }
    public int getPrize() { return prize; }
    public boolean isWinning() { return this != NONE; }
}
