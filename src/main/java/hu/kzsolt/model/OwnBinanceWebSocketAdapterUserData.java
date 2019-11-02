package hu.kzsolt.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceEventExecutionReport;
import com.webcerebrium.binance.datatype.BinanceEventOutboundAccountInfo;
import com.webcerebrium.binance.websocket.BinanceWebSocketAdapterUserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OwnBinanceWebSocketAdapterUserData extends BinanceWebSocketAdapterUserData {

    private static final Logger LOGGER = LoggerFactory.getLogger(OwnBinanceWebSocketAdapterUserData.class);

    @Override
    public void onWebSocketText(String message) {
        LOGGER.debug("onWebSocketText message={}", message);
        JsonObject operation = (new Gson()).fromJson(message, JsonObject.class);

        try {
            String eventType = operation.get("e").getAsString();
            if (eventType.equals("outboundAccountInfo")) {
                onOutboundAccountInfo(new BinanceEventOutboundAccountInfo(operation));
            } else if (eventType.equals("executionReport")) {
                onExecutionReport(new BinanceEventExecutionReport(operation));
            } else if (eventType.equals("outboundAccountPosition")) {
                outboundAccountPosition(new OutboundAccountPosition(operation));
            } else {
                LOGGER.error("Error in websocket message - unknown event Type");
            }
        } catch (BinanceApiException e) {
            LOGGER.error("Error in websocket message {}", e.getMessage());
        }
    }

    public void outboundAccountPosition(OutboundAccountPosition message) {
        System.out.println(message);
    }

    @Override
    public void onOutboundAccountInfo(BinanceEventOutboundAccountInfo message) {
    }

    @Override
    public void onExecutionReport(BinanceEventExecutionReport message) {
        System.out.println("onExecutionReport");
        System.out.println(message);
    }
}
