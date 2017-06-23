package pietruh.pos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import pietruh.pos.configuration.Constants;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by pietruh on 23.06.2017.
 */
public class FixerExchangeRateService implements ExchangeRateService {

    JsonDeserializer<LocalDate> jd = (jsonElement, type, jsonDeserializationContext) -> LocalDate.parse(jsonElement.getAsString());
    JsonDeserializer<Set<Rate>> rateSetd = (jsonElement, type, jsonDeserializationContext) -> ((JsonObject) jsonElement).entrySet().stream()
          .map(e -> new Rate(Currency.getInstance(e.getKey()), e.getValue().getAsBigDecimal())).collect(Collectors.toSet());

    @Override
    public ExchangeRate getExchangeRate(Currency currency) {
        HttpGet httpGet = new HttpGet(Constants.HTTP_API_FIXER_IO_LATEST + "?base=" + currency.getCurrencyCode());
        return getExchangeRate(httpGet);
    }

    @Override
    public ExchangeRate exchangeCurrency(Currency currency, List<Currency> exchangeCurrencies) {
        HttpGet httpGet = new HttpGet(Constants.HTTP_API_FIXER_IO_LATEST + "?base=" + currency.getCurrencyCode() + "&symbols=" + exchangeCurrencies.stream()
              .map(Currency::getCurrencyCode).collect(Collectors.joining()));
        return getExchangeRate(httpGet);
    }

    private ExchangeRate getExchangeRate(HttpGet httpGet) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpResponse execute = httpClient.execute(httpGet);
            httpGet.getRequestLine();
            HttpEntity entity = execute.getEntity();
            String s = EntityUtils.toString(entity);
            Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, jd).registerTypeAdapter(new TypeToken<Set<Rate>>() {
            }.getType(), rateSetd).create();
            ExchangeRate exchangeRate = gson.fromJson(s, ExchangeRate.class);
            System.out.println(exchangeRate);
            return exchangeRate;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ExchangeRate();
    }

}
