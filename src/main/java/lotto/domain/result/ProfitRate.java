package lotto.domain.result;

public final class ProfitRate {
    private final double rate; // %

    public ProfitRate(int totalWinnings, int purchaseAmount) {
        if (purchaseAmount <= 0) throw new IllegalArgumentException("[ERROR] 구입 금액이 올바르지 않습니다.");
        this.rate = (double) totalWinnings / purchaseAmount * 100.0;
    }

    public double value() {
        return rate;
    }

    @Override
    public String toString() {
        return String.format("총 수익률은 %.1f%%입니다.", rate);
    }
}
