import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Wikipedia {
    private static HashMap<String, String> sCache = new HashMap<>(2);

    public static String getWikiFeatureArticle(long ts) {
        String featureTitle = "";
        String url = "";
        String summary = "";
        String cacheDateKey = getCacheDateKey(ts);

        if (sCache.containsKey(cacheDateKey))
            return sCache.get(cacheDateKey);

        try {
            Document doc = Jsoup.connect("https://en.m.wikipedia.org/wiki/Wikipedia:Today's_featured_article/" + Wikipedia.getWikiFeatureMonth(ts)).get();
            Element x = doc.body().select("#bodyContent").select("a").get(1);
            featureTitle = x.html();
            url = x.attr("href");
            if (!featureTitle.isEmpty()) {
                String wikiUrl = "https://rest.wikimedia.org/en.wikipedia.org/v1/page/summary/" + URLEncoder.encode(featureTitle).replace("+", "%20");
                summary = doGET(wikiUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json = "{\"featured\":\"" + featureTitle + "\",\"url\":\"https://en.wikipedia.org" + url + "\"";
        if (!summary.isEmpty() && summary.contains("{"))
            json = json + "," + summary.substring(summary.indexOf("{") + 1, summary.lastIndexOf("}"));
        json = json + "}";
        sCache.put(cacheDateKey, json);
        return json;
    }

    private static String getWikiFeatureMonth(long ts) {
        if (ts <= 0)
            ts = System.currentTimeMillis();
        Date date = new Date(ts);
        return new SimpleDateFormat("MMMM_d,_yyyy").format(date);
    }

    private static String getCacheDateKey(long ts) {
        if (ts <= 0)
            ts = System.currentTimeMillis();
        Date date = new Date(ts);
        String dateKey = new SimpleDateFormat("MMddyyyy").format(date);
        System.out.println(dateKey);
        return dateKey;
    }

    public static String doGET(String httpUrl) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
