package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.generator.RandomLottoGenerator;

public class Application {
    public static void main(String[] args) {
        LottoNumberGenerator generator = new RandomLottoGenerator();
        LottoService service = new LottoService(generator);
        LottoController controller = new LottoController(service);
        controller.run();
    }
}

