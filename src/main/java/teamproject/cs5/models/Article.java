package teamproject.cs5.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String url_web_site_init;
    private String text;
    private String img_url;
    @NotBlank
    private String title;
    private String article_url;




    public Article(String url_web_site_init, String img_url, String title, String article_url, String text) {
        this.url_web_site_init = url_web_site_init;
        this.img_url = img_url;
        this.title = title;
        this.article_url = article_url;
        this.text=text;
    }


    public Article() {}

    public String getUrl_web_site_init() {
        return url_web_site_init;
    }

    public void setUrl_web_site_init(String url_web_site_init) {
        this.url_web_site_init = url_web_site_init;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

