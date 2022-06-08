package teamproject.cs5.payload.request;

public class ArticleRequest {

    private final String url_web_site_init;
    private final String text;
    private final String img_url;
    private final String title;
    private final String article_url;

    public ArticleRequest(String url_web_site_init, String text, String img_url, String title, String article_url) {
        this.url_web_site_init = url_web_site_init;
        this.text = text;
        this.img_url = img_url;
        this.title = title;
        this.article_url = article_url;
    }

    public String getUrl_web_site_init() {
        return url_web_site_init;
    }

    public String getText() {
        return text;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getTitle() {
        return title;
    }

    public String getArticle_url() {
        return article_url;
    }
}
