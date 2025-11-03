package lotto.domain.ticket;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class TicketsTest {
    @Test
    void 사이즈_체크() {
        Tickets tickets = new Tickets(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(7, 8, 9, 10, 11, 12))
        ));
        assertThat(tickets.size()).isEqualTo(2);
    }
}
