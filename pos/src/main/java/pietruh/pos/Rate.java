package pietruh.pos;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by pietruh on 23.06.2017.
 */
public class Rate {
    private Currency currency;
    private BigDecimal rate;

    public Rate(Currency locale, BigDecimal currency) {
        this.currency = locale;
        this.rate = currency;
    }

    @Override
    public String toString() {
        return "Rate{" + "currency=" + currency + ", rate=" + rate + '}';
    }
}
