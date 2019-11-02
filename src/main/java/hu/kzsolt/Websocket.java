package hu.kzsolt;

import com.google.inject.Inject;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceEventAggTrade;
import com.webcerebrium.binance.datatype.BinanceSymbol;
import com.webcerebrium.binance.websocket.BinanceWebSocketAdapterAggTrades;
import hu.kzsolt.model.OwnBinanceWebSocketAdapterUserData;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.websocket.api.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class Websocket {

    private static final Logger LOGGER = LoggerFactory.getLogger(Websocket.class);

    @Inject
    private BinanceApi binanceApi;

    private Session tradeSession;
    private Session userSession;

    public void start() throws BinanceApiException {
        BinanceSymbol symbol = new BinanceSymbol("BTCUSDT");
//        createSession(symbol);
        userDate();

    }

    private void createSession(BinanceSymbol symbol) throws BinanceApiException {
        tradeSession = binanceApi.websocketTrades(symbol, new BinanceWebSocketAdapterAggTrades() {
            @Override
            public void onMessage(BinanceEventAggTrade message) {
                message.getPrice();
                System.out.println(message.getPrice());
            }

            @Override
            public void onWebSocketError(Throwable cause) {
                super.onWebSocketError(cause);
                tradeSession.close();
                try {
                    start();
                } catch (BinanceApiException e) {
                    LOGGER.error("Websocket tradeSession can not start!");
                }
            }
        });
    }

    private void userDate() throws BinanceApiException {
        String listenKey = binanceApi.startUserDataStream();
        userSession = binanceApi.websocket(listenKey, new OwnBinanceWebSocketAdapterUserData());
    }
}
