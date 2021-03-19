package game;

import game.drawables.elements.Coin;
import game.util.Position;

import java.util.Iterator;
import java.util.List;

public class CoinRetriever {
    int retrieveCoins(List<Coin> coins, Position position) {
        int n = 0;
        Iterator<Coin> iter = coins.iterator();
        while (iter.hasNext()) {
            Coin coin = iter.next();
            if (position.equals(coin.getPosition())) {
                iter.remove();
                n++;
            }
        }
        return n;
    }
}
