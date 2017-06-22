package pietruh.pos;

import java.time.LocalDate;
import java.util.Set;

/**
 * Created by pietruh on 23.06.2017.
 */
public class Currency {
    private String base;
    private LocalDate date;
    private Set<Rate> rate;

    public Set<Rate> getRates() {
        return rate;
    }
}
