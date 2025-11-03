package lotto.controller;

import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.domain.result.WinningResult;
import lotto.domain.result.ProfitRate;
import lotto.domain.ticket.Tickets;

import java.util.List;

public class LottoController {
    private final LottoService service;

    public LottoController(LottoService service) {
        this.service = service;
    }

    public void run() {
        int money = InputView.readValidMoney();
        List<Integer> winning = InputView.readValidWinningNumbers();
        int bonus = InputView.readValidBonusNumber(winning);

        Tickets tickets = service.issueTickets(money);
        OutputView.printIssued(tickets);

        WinningResult result = service.evaluate(tickets, winning, bonus);
        OutputView.printStatistics(result);

        ProfitRate profitRate = service.calculateProfit(result, money);
        OutputView.printProfitRate(profitRate);
    }
}
