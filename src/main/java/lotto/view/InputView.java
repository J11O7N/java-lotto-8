package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.error.InvalidInputException;

import java.util.*;

public class InputView {

    public static int readValidMoney() {
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                String line = Console.readLine();
                int money = Integer.parseInt(line.trim());
                if (money <= 0 || money % 1000 != 0) {
                    throw new InvalidInputException("[ERROR] 구입 금액은 1,000원 단위의 양수여야 합니다.");
                }
                return money;
            } catch (NumberFormatException e) {
                OutputView.printError("[ERROR] 금액은 숫자여야 합니다.");
            } catch (InvalidInputException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    public static List<Integer> readValidWinningNumbers() {
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                String line = Console.readLine();
                if (line == null || line.isBlank()) {
                    throw new InvalidInputException("[ERROR] 당첨 번호 입력이 비어 있습니다.");
                }
                String[] tokens = line.split(",");
                if (tokens.length != 6) {
                    throw new InvalidInputException("[ERROR] 당첨 번호는 쉼표(,)로 구분된 6개여야 합니다.");
                }
                Set<Integer> set = new HashSet<>();
                List<Integer> nums = new ArrayList<>();
                for (String t : tokens) {
                    int n = Integer.parseInt(t.trim());
                    if (n < 1 || n > 45) {
                        throw new InvalidInputException("[ERROR] 번호는 1~45 범위여야 합니다.");
                    }
                    if (!set.add(n)) {
                        throw new InvalidInputException("[ERROR] 당첨 번호에 중복이 있습니다.");
                    }
                    nums.add(n);
                }
                return nums;
            } catch (NumberFormatException e) {
                OutputView.printError("[ERROR] 당첨 번호에는 숫자만 입력해야 합니다.");
            } catch (InvalidInputException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    public static int readValidBonusNumber(List<Integer> winning) {
        Set<Integer> winningSet = new HashSet<>(winning);
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
                String line = Console.readLine();
                int bonus = Integer.parseInt(line.trim());
                if (bonus < 1 || bonus > 45) {
                    throw new InvalidInputException("[ERROR] 보너스 번호는 1~45 범위여야 합니다.");
                }
                if (winningSet.contains(bonus)) {
                    throw new InvalidInputException("[ERROR] 보너스 번호가 당첨 번호와 중복입니다.");
                }
                return bonus;
            } catch (NumberFormatException e) {
                OutputView.printError("[ERROR] 보너스 번호는 숫자여야 합니다.");
            } catch (InvalidInputException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
