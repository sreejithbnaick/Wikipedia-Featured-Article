
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Wikipedia {
    private static HashMap<String, String> sCache = new HashMap<>(2);

    public static String getWikiFeatureArticle(long ts) {
        String featureTitle = "";
        String url="";
        String cacheDateKey = getCacheDateKey(ts);

        if (sCache.containsKey(cacheDateKey))
            return sCache.get(cacheDateKey);

        try {
            Document doc = Jsoup.connect("https://en.m.wikipedia.org/wiki/Wikipedia:Today's_featured_article/" + Wikipedia.getWikiFeatureMonth(ts)).get();
            Element x =doc.body().select("#bodyContent").select("a").get(1);
            featureTitle = x.html();
            url = x.attr("href");
            sCache.put(cacheDateKey, featureTitle);
        } catch (IOException e) {
        }
        String json ="{\"title\":\""+featureTitle+"\",\"url\":\"https://en.wikipedia.org"+url+"\"}";
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

}
