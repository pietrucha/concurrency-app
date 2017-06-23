package pietruh.pos;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

;

/**
 * Created by pietruh on 22.06.2017.
 */
public class ExchangeRateTest {

    public static final Currency PLN = Currency.getInstance("PLN");
    public static final Currency EUR = Currency.getInstance("EUR");

    //    http://api.fixer.io/latest?symbols=USD,GBP
    @Test
    public void passCurrency_RetrunExchangeRateWithTheSameCurrencyAndCurrencyRates() throws Exception {
        ExchangeRate exchangeRatePLN = new FixerExchangeRateService().getExchangeRate(PLN);
        ExchangeRate exchangeRateEUR = new FixerExchangeRateService().getExchangeRate(EUR);
        assertEquals(Currency.getInstance("PLN"), exchangeRatePLN.getBase());
        assertEquals(Currency.getInstance("EUR"), exchangeRateEUR.getBase());

        Set<Rate> rates = exchangeRatePLN.getRates();
        assertEquals(LocalDate.now(), exchangeRatePLN.getDate());
        assertTrue(!rates.isEmpty());
    }

    @Test
    public void passCurrencyWith1CurrencyExchange_RetrunCurrencyWithOneCurrencyRateExchange() throws Exception {
        ExchangeRate exchangeRate = new FixerExchangeRateService().exchangeCurrency(PLN, Arrays.asList(EUR));
        assertEquals(1, exchangeRate.getRates().size());
    }

    @Test(expected = NullPointerException.class)
    public void passNullAsCurrency_ThrowNPE() throws Exception {
        ExchangeRate exchangeRate = new FixerExchangeRateService().exchangeCurrency(null, Arrays.asList(EUR));
    }
}
