package examples.json.util;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by wilson on 3/7/17.
 */
public class UrlDemo {
    public static void main(String[] args) {
        String sUrl = "http://lp.downloadhub.me/lp/my/mobile/mobiledoctor_01/index.php?fdsf=dasdas&ds&&";
        System.out.println(getTspLink(sUrl));
    }

    public static Map<String, List<String>> parseQueryString(String queryString) throws UnsupportedEncodingException {
        Map<String, List<String>> queryParams = new HashMap<>();

        if (StringUtils.isBlank(queryString)) {
            return queryParams;
        }

        for (String param : queryString.split("&")) {
            String[] pair = param.split("=");
            String key = URLDecoder.decode(pair[0], "UTF-8");

            String value = "";
            if (pair.length > 1) {
                value = URLDecoder.decode(pair[1], "UTF-8");
            }

            if (queryParams.containsKey(key)) {
                List<String> values = queryParams.get(key);
                values.add(value);
                queryParams.put(key, values);
            } else {
                List<String> values = new ArrayList<>();
                values.add(value);
                queryParams.put(key, values);
            }
        }
        return queryParams;
    }

    private static String getTspLink(String url) {
        String[] removeParamKeys =
                {"cookieid", "xsource_data",
                        "add_code", "act", "click_id",
                        "rotate_id", "transaction_id", "cid"};

        try {
            URL checkedUrl = new URL(url);
            String queryString = checkedUrl.getQuery();
            Map<String, List<String>> queryParams = parseQueryString(queryString);
            for (String key : removeParamKeys) {
                deleteParameter(queryParams, key);
            }
            int queryStringIndex = url.indexOf("?" + queryString);
            int endIndex = queryStringIndex > 0 ? queryStringIndex : url.length();
            return addParameters(url.substring(0, endIndex), queryParams);
        } catch (MalformedURLException e) {
            return url;
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }

    private static String addParameters(String url, Map<String, List<String>> queryParams) throws UnsupportedEncodingException {
        if (queryParams.isEmpty()) {
            return url;
        }
        String newUrl = url + "?";

        for (Map.Entry<String, List<String>> entry : queryParams.entrySet()) {
            String key = entry.getKey();

            for (String value : entry.getValue()) {
                newUrl += URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8") + "&";
            }
        }

        return newUrl.substring(0, newUrl.length() - 1);
    }

    private static void deleteParameter(Map<String, List<String>> queryParams, String queryKey) {
        if (queryParams.containsKey(queryKey)) {
            queryParams.remove(queryKey);
        }
    }
}
