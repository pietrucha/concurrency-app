package pietruh.pos;

import java.util.Currency;
import java.util.List;

/**
 * Created by pietruh on 23.06.2017.
 */
public interface ExchangeRateService {
    ExchangeRate getExchangeRate(Currency currency);

    ExchangeRate exchangeCurrency(Currency currency, List<Currency> exchangeCurrencies);
}
