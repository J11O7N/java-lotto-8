package lotto.view;

import lotto.domain.result.WinningResult;
import lotto.domain.result.ProfitRate;
import lotto.domain.ticket.Tickets;
import lotto.domain.ticket.Lotto;
import lotto.domain.rank.Rank;

import java.text.NumberFormat;
import java.util.Locale;

public class OutputView {

    public static void printIssued(Tickets tickets) {
        System.out.println(tickets.size() + "개를 구매했습니다.");
        for (Lotto t : tickets.asList()) {
            System.out.println(t);
        }
    }

    public static void printStatistics(WinningResult result) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        NumberFormat nf = NumberFormat.getInstance(Locale.KOREA);


        for (Rank r : Rank.values()) {
            if (!r.isWinning()) continue;

            String label = r.getMatches() + "개 일치";

            if (r == Rank.FIVE_BONUS) {
                label = "5개 일치, 보너스 볼 일치";
            }

            int count = result.getCounts().getOrDefault(r, 0); // 없으면 0 출력

            System.out.println(label + " (" + nf.format(r.getPrize()) + "원) - " + count + "개");
        }
    }

    public static void printProfitRate(ProfitRate rate) {
        System.out.println(rate.toString());
    }

    public static void printError(String message) {
        System.out.println(message);
    }
}
