package pietruh.pos;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import pietruh.pos.configuration.Constants;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pietruh on 23.06.2017.
 */
public class FixerExchangeRateService implements ExchangeRateService {

    //    JsonSerializer<LocalDate> js = (localDate, type, jsonSerializationContext) -> new JsonPrimitive(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
    JsonDeserializer<LocalDate> jd = (jsonElement, type, jsonDeserializationContext) -> LocalDate.parse(jsonElement.getAsString());
    JsonDeserializer<Rate> rated = (jsonElement, type, jsonDeserializationContext) -> new Rate();
    JsonDeserializer<Set<Rate>> rateSetd = (jsonElement, type, jsonDeserializationContext) -> new HashSet<>();

    @Override
    public ExchangeRate getExchangeRate(Currency currency) {
        HttpGet httpGet = new HttpGet(Constants.HTTP_API_FIXER_IO_LATEST);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpResponse execute = httpClient.execute(httpGet);
            httpGet.getRequestLine();
            HttpEntity entity = execute.getEntity();
            String s = EntityUtils.toString(entity);
            System.out.println(s);
            Gson gson = new GsonBuilder().setPrettyPrinting()
                  .registerTypeAdapter(LocalDate.class, jd)
                  .registerTypeAdapter(Rate.class, rated)
                  .registerTypeAdapter(new TypeToken<Set<Rate>>(){}.getType(), rated)
                  .create();
            ExchangeRate exchangeRate = gson.fromJson(s, ExchangeRate.class);
            System.out.println(exchangeRate);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExchangeRate exchangeRate = new ExchangeRate();
        Set<Rate> rates = new HashSet<>();
        rates.add(new Rate());
        exchangeRate.setRates(rates);

        return exchangeRate;
    }

}
