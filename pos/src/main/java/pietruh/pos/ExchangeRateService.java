package pietruh.pos;

import java.util.Currency;

/**
 * Created by pietruh on 23.06.2017.
 */
public interface ExchangeRateService {
    ExchangeRate getExchangeRate(Currency currency);
}
