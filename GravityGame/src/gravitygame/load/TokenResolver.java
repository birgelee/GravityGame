/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.load;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Henry
 */
public class TokenResolver {

    private HashMap<String, List<Double[]>> tokens;

    public TokenResolver() {
        tokens = new HashMap<String, List<Double[]>>();

    }

    public void addToken(String token, double x, double y) {
        if (tokens.containsKey(token)) {
            tokens.get(token).add(new Double[]{x, y});
        } else {
            tokens.put(token, new ArrayList<Double[]>());
            tokens.get(token).add(new Double[]{x, y});
        }
    }

    public Object[] resolveToken(String tokenString) throws Exception {
        try {
            if (tokenString.indexOf(":") == -1) {
                return tokens.get(tokenString).get(0);
            }
            String token = tokenString.split(":")[0];
            String index = tokenString.split(":")[1];
            return tokens.get(token).get(Integer.parseInt(index));
        } catch (Exception ex) {
            throw new Exception("Malformated Token.  From String: " + tokenString);
        }
    }
}
