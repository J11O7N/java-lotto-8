package lotto.service;

import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.result.ProfitRate;
import lotto.domain.result.WinningResult;
import lotto.domain.ticket.Tickets;
import lotto.domain.winning.WinningNumbers;

import java.util.List;

public class LottoService {
    private final LottoNumberGenerator generator;

    public LottoService(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public Tickets issueTickets(int money) {
        int count = money / 1000;
        return Tickets.purchase(count, generator);
    }

    public WinningResult evaluate(Tickets tickets, List<Integer> winning, int bonus) {
        WinningNumbers winningNumbers = new WinningNumbers(winning, bonus);
        return tickets.calculateResult(winningNumbers);
    }

    public ProfitRate calculateProfit(WinningResult result, int money) {
        return new ProfitRate(result.totalPrize(), money);
    }
}
