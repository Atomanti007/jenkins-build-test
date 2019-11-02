package hu.kzsolt.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceWalletAsset;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class OutboundAccountPosition {
    public Long eventTime;
    public Long updateTime;
    public List<BinanceWalletAsset> balances;

    public OutboundAccountPosition(JsonObject event) throws BinanceApiException {
        eventTime = event.get("E").getAsLong();
        updateTime = event.get("u").getAsLong();

        balances = new LinkedList<>();
        JsonArray b = event.get("B").getAsJsonArray();
        for (JsonElement asset : b) {
            balances.add(new BinanceWalletAsset(asset.getAsJsonObject()));
        }
    }

    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public List<BinanceWalletAsset> getBalances() {
        return balances;
    }

    public void setBalances(List<BinanceWalletAsset> balances) {
        this.balances = balances;
    }

    @Override
    public String toString() {
        return "OutboundAccountPosition{" +
            "eventTime=" + eventTime +
            ", updateTime=" + updateTime +
            ", balances=" + balances +
            '}';
    }
}
