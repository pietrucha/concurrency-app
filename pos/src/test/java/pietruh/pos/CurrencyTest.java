package pietruh.pos;

import org.junit.Assert;
import org.junit.Test;;

import java.util.Locale;
import java.util.Set;

/**
 * Created by pietruh on 22.06.2017.
 */
public class CurrencyTest {

    public static final CurrencyService CURRENCY_SERVICE = new FixerCurrencyService();

    //    http://api.fixer.io/latest?symbols=USD,GBP
    @Test
    public void getCurrencyFromNetwork() throws Exception {
        Currency currency = CURRENCY_SERVICE.getCurrency(Locale.getDefault());
        Set<Rate> rates = currency.getRates();
        Rate rate1;
        Assert.assertEquals(null, currency);
//        Assert.assertEquals(, rates.stream().findFirst().orElse(null));
    }
}
