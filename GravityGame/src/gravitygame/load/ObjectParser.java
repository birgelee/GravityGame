/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.load;

import java.util.ArrayList;
import java.util.List;
import static gravitygame.load.PopulationLoader.getObjectsFromParams;

/**
 *
 * @author Henry
 */
public class ObjectParser {

    int currentIndex = 0;
    private String args;
    private TokenResolver tokenResolver;

    public ObjectParser(String args, TokenResolver tokenResolver) {
        this.args = args;
        this.tokenResolver = tokenResolver;
    }

    public Object[] parse() {
        //args = args.concat("}");
        return parseList();
    }

    private Object[] parseList() {
        List result = new ArrayList();
        outer:
        while (true) {
            
            switch (args.charAt(currentIndex)) {
                case ' ':
                    break;
                case ',':
                    currentIndex++;
                    result.add(paseExpression());
                    break;
                case '}':
                    currentIndex++;
                    break outer;
                default:
                    result.add(paseExpression());
            }
            if (currentIndex >= args.length()) {
                break outer;
            }
        }
        return result.toArray();
    }

    private Object paseExpression() {
        Object result = null;
        outer:
        while (true) {
            
            switch (args.charAt(currentIndex)) {
                case ' ':
                    break;
                case '{':
                    currentIndex++;
                    result = parseList();
                    break outer;
                default:
                    int endIndex = Math.min(getNextCharOrStringEnd(','), getNextCharOrStringEnd('}'));
                    try {
                        result = Integer.parseInt(args.substring(currentIndex, endIndex));
                        break outer;
                    } catch (RuntimeException rex) {
                        try {
                            result = Double.parseDouble(args.substring(currentIndex, endIndex));
                            break outer;
                        } catch (Exception e1) {
                            try {
                                result = tokenResolver.resolveToken(args.substring(currentIndex, endIndex));
                                break outer;
                            } catch (Exception e2) {
                                try {
                                } catch (Exception e3) {
                                }
                            }


                        }
                    }
                    break outer;

            }
            currentIndex++;
            if (currentIndex == args.length()) {
                break outer;
            }

        }
        currentIndex = Math.min(getNextCharOrStringEnd(','), getNextCharOrStringEnd('}'));
        return result;

    }
    private int getNextCharOrStringEnd(char ch) {
        int index = args.indexOf(ch, currentIndex);
        if (index == -1) {
            return args.length();
        }
        return index;
    }
}
