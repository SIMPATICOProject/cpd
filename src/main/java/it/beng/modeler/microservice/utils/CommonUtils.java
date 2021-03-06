package it.beng.modeler.microservice.utils;

import io.vertx.core.json.JsonObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * <p>This class is a member of <strong>modeler-microservice</strong> project.</p>
 *
 * @author vince
 */
public final class CommonUtils {
    public static final Predicate<String> NON_EMPTY_STRING = p -> p != null && !p.trim().isEmpty();
    public static final Predicate<JsonObject> NON_EMPTY_JSON_OBJECT = p -> p != null && !p.isEmpty();

    public static <T> T coalesce(T... objects) {
        return Stream.of(objects).filter(Objects::nonNull).findFirst().orElse(null);
    }

    public static String implicitUrlOriginPort(String url) {
        try {
            URL u = new URL(url);
            if (u.getPort() == u.getDefaultPort())
                return new URL(u.getProtocol(), u.getHost(), u.getFile()).toExternalForm();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
