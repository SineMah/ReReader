
package rereader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Christian Bock <bock.christian@mincedmnd.com>
 */
public class ReGlobals {

    public static ReConfig initGlobals() {
        ReConfig config = null;

        try {
            Constructor ctor = ReConfig.class.getDeclaredConstructor();
            ctor.setAccessible(true);
            config = (ReConfig) ctor.newInstance();
            config.readXML("default.xml");

        } catch (InstantiationException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        } catch (InvocationTargetException x) {
            x.printStackTrace();
        } catch (NoSuchMethodException x) {
            x.printStackTrace();
        }

        return config;
    }

}
