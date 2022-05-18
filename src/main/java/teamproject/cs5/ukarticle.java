package teamproject.cs5;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ukarticle {
    public static String visitUkraine = "https://visitukraine.today/uk/blog";
    public static String warUkraine = "https://war.ukraine.ua/ru/news/"; //DATE https://war.ukraine.ua/news/07-05-2022/ !!! 5 days ?
    public static String ukrinform = "https://www.ukrinform.ua/";
    public ArrayList<String> web = new ArrayList<String>();
    public ArrayList<String> text = new ArrayList<String>();
    public ArrayList<String> img_url = new ArrayList<String>();
    public ArrayList<String> title = new ArrayList<String>();

    public ukarticle(String url) throws IOException, InterruptedException {
        if (url.equals(visitUkraine)) {
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
            Elements img = doc.getElementsByClass("page-blog_all-news-wrapp").select("img");
            Elements url_article = doc.getElementsByClass("page-blog_all-news-wrapp").select("a"); //We can extract the url and title with ALT attr
            int n = img.size();
            for (int k = 0; k < n; k++) {
                img_url.add(img.get(k).attr("src"));
                title.add(url_article.get(2 * k).text());     //avoid duplicate value (2 href present)
                web.add(url_article.get(2 * k).attr("href"));
            }
        }

        if (url.equals(warUkraine)) {
            Document doc = Jsoup.connect(url).userAgent("Mozilla").get(); //use userAgent because the webSite firewall block the acces if not.
            Elements article = doc.getElementsByClass("news-list").select("p");
            Elements time = doc.getElementsByClass("news-list").select("span");
            int n = time.size();
            for (int k = 0; k < n; k++) {
                text.add(article.get(k).text());
                title.add(time.get(k).text());
            }
        }

        if (url.equals(ukrinform)) {
            Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements img = doc.getElementsByClass("topAnons").select("img");
            Elements url_article = doc.getElementsByClass("topAnons").select("a");
            int n = img.size();
            for (int k = 0; k < n; k++) {
                img_url.add(img.get(k).attr("src"));
                web.add("https://www.ukrinform.ua" + url_article.get(k).attr("href"));
            }
            int m = web.size();
            for (int k = 0; k < m; k++) {
                Document doc2 = Jsoup.connect(web.get(k)).userAgent("Mozilla").get();
                Elements title_article = doc2.getElementsByClass("newsTitle");
                title.add(title_article.get(0).text());
            }
        }
    }
}