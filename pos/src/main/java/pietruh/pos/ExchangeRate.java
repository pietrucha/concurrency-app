package pietruh.pos;

import java.time.LocalDate;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pietruh on 23.06.2017.
 */
public class ExchangeRate {
    private LocalDate date;
    private Currency base;
    private Set<Rate> rates;

    public ExchangeRate() {
        rates = new HashSet<>();
    }

    public Set<Rate> getRates() {
        return rates;
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }

    public Currency getBase() {
        return base;
    }

    public void setBase(Currency base) {
        this.base = base;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" + "date=" + date + ", base=" + base + ", rates=" + rates + '}';
    }
}
