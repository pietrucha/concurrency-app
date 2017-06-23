package pietruh.pos;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

import static org.junit.Assert.assertEquals;

;

/**
 * Created by pietruh on 22.06.2017.
 */
public class ExchangeRateTest {

    //    http://api.fixer.io/latest?symbols=USD,GBP
    @Test
    public void getCurrencyFromNetwork() throws Exception {
        ExchangeRate exchangeRatePLN = new FixerExchangeRateService().getExchangeRate(Currency.getInstance("PLN"));
        ExchangeRate exchangeRateEUR = new FixerExchangeRateService().getExchangeRate(Currency.getInstance("EUR"));
        assertEquals("PLN", exchangeRatePLN.getBase());
        assertEquals("EUR", exchangeRateEUR.getBase());

        Set<Rate> rates = exchangeRatePLN.getRates();
        assertEquals(LocalDate.now().minusDays(1), exchangeRatePLN.getDate());
        assertEquals(1, rates.size());
    }

    @Test
    public void currencyTest() throws Exception {

    }
}
