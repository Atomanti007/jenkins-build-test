package hu.kzsolt;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;

import java.io.IOException;
import java.util.Map;

public class Binance {

    public static void main(String[] args) throws BinanceApiException, IOException, InterruptedException {
        Binance binance = new Binance();

        Map<String, String> env = System.getenv();
        BinanceApi binanceApi = new BinanceApi(env.get("key"), env.get("secret"));

        Injector injector = Guice.createInjector(binder -> {
            binder.bind(BinanceApi.class).toInstance(binanceApi);
        });

        Websocket instance = injector.getInstance(Websocket.class);
        instance.start();

//        try {
////            System.out.println(binanceApi.balances());
//            System.out.println(binanceApi.balancesMap().get("USDT"));
//        } catch (BinanceApiException e) {
//            e.printStackTrace();
//        }
    }

}
