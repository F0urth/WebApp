package main.logic.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author F0urth
 */
public class ResponseParser {

    private static final Logger logger = LoggerFactory.getLogger(ResponseParser.class);
    private JsonParser parser;

    public ResponseParser() {
        this.parser = new JsonParser();
    }

    public FullParseData parsNBPTableAResponse(String json) {
        String table = "";
        String no = "";
        String effectiveDate = "";
        logger.info("Parse NBP Response");
        List<RatesParsData> ratesParsData = new ArrayList<>();
        JsonElement parse = this.parser.parse(json);
        JsonArray jArray = (JsonArray) parse;
        for (JsonElement jsonElement : jArray) {
            if (jsonElement.isJsonArray()) {
                logger.error("Unexpected state in json request");
            } else if (jsonElement.isJsonObject()) {
                JsonObject element = (JsonObject) jsonElement;
                table = element.get("table").getAsString();
                no = element.get("no").getAsString();
                effectiveDate = element.get("effectiveDate").getAsString();
                final JsonElement rates = element.get("rates");
                if (rates.isJsonArray()) {
                    JsonArray ratesArray = (JsonArray) rates;
                    for (JsonElement ratesValues : ratesArray) {
                        if (ratesValues.isJsonObject()) {
                            JsonObject element1 = (JsonObject) ratesValues;
                            ratesParsData.add(new RatesParsData(
                                element1.get("mid").getAsDouble(),
                                element1.get("currency").getAsString(),
                                element1.get("code").getAsString()));
                        } else {
                            logger.error("Unexpected element in rates");
                        }
                    }
                }
            }
        }
        logger.info("Finished collecting");
        return new FullParseData(
            table, no, effectiveDate, ratesParsData
        );
    }

    public static class FullParseData {
        private final String table;
        private final String no;
        private final String effectiveDate;
        private final List<RatesParsData> rates;

        private FullParseData(String table, String no, String effectiveDate, List<RatesParsData> rates) {
            this.table = table;
            this.no = no;
            this.effectiveDate = effectiveDate;
            this.rates = rates;
        }

        public String getTable() {
            return table;
        }

        public String getNo() {
            return no;
        }

        public String getEffectiveDate() {
            return effectiveDate;
        }

        public List<RatesParsData> getRates() {
            return rates;
        }

        @Override
        public String toString() {
            return "FullParseData{" +
                "table='" + table + '\'' +
                ", no='" + no + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", rates=" + rates +
                '}';
        }
    }

    public static class RatesParsData {
        private Double mid;
        private String currency;
        private String code;

        private RatesParsData(Double mid, String currency, String code) {
            this.mid = mid;
            this.currency = currency;
            this.code = code;
        }

        public Double getMid() {
            return mid;
        }

        public String getCurrency() {
            return currency;
        }

        public String getCode() {
            return code;
        }

        @Override
        public String toString() {
            return "ParsData{" +
                "mid=" + mid +
                ", currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                '}';
        }
    }
}
