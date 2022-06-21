package teamproject.cs5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import teamproject.cs5.models.Article;
import teamproject.cs5.payload.request.ArticleRequest;
import teamproject.cs5.repository.ArticleRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> findAll(){
        return articleRepository.findAll();
    }

    public List<Article> getByUrl(long url){ //Get all article from one website.
        String urlf="";
        if(url==1)
            urlf="https://visitukraine.today/uk/blog";
        if(url==2)
            urlf="https://war.ukraine.ua/ru/news/";
        if(url==3)
            urlf="https://www.ukrinform.ua/";
        List<Article> articles_by_url = new ArrayList<Article>();
        List<Article> all_article = findAll();
        for(Article article : all_article){
            if(article.getUrl_web_site_init().equals(urlf)) {
                articles_by_url.add(article);
            }
        }
        if(articles_by_url.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"unable to find article from this url");
        }
        else {
            return articles_by_url;
        }
    }

    public Article createFromRequest(ArticleRequest request){
        Article article = new Article(
                request.getUrl_web_site_init(),
                request.getImg_url(),
                request.getTitle(),
                request.getArticle_url(),
                request.getText()
        );
        return articleRepository.save(article);
    }

    public void createFromUrl(long url) throws IOException {
        String visitUkraine = "https://visitukraine.today/uk/blog"; //Img + title + url_article
        String warUkraine = "https://war.ukraine.ua/ru/news/"; //Title + text
        String ukrinform = "https://www.ukrinform.ua/"; //Img + title + url_article
        ArrayList<String> web = new ArrayList<String>();
        ArrayList<String> text = new ArrayList<String>();
        ArrayList<String> img_url = new ArrayList<String>();
        ArrayList<String> title = new ArrayList<String>();
        String urlf="";
        if (url==1) {
            urlf = visitUkraine;
            Document doc = Jsoup.connect(visitUkraine).userAgent("Mozilla/5.0").get();
            Elements img = doc.getElementsByClass("page-blog_all-news-wrapp").select("img");
            Elements url_article = doc.getElementsByClass("page-blog_all-news-wrapp").select("a"); //We can extract the url and title with ALT attr
            int n = img.size();
            for (int k = 0; k < n; k++) {
                img_url.add(img.get(k).attr("src"));
                title.add(url_article.get(2 * k).text());     //avoid duplicate value (2 href present)
                web.add(url_article.get(2 * k).attr("href"));
            }
        }
        if (url==2) {
            Document doc = Jsoup.connect(warUkraine).userAgent("Mozilla").get(); //use userAgent because the webSite firewall block the acces if not.
            Elements article = doc.getElementsByClass("news-list").select("p");
            Elements time = doc.getElementsByClass("news-list").select("span");
            int n = article.size();
            for (int k = 0; k < n; k++) {
                text.add(article.get(k).text());
                title.add(time.get(k).text());
            }
        }
        if (url==3) {
            urlf = ukrinform;
            Document doc = Jsoup.connect(ukrinform).userAgent("Mozilla").get();
            Elements img = doc.getElementsByClass("topAnons").select("img");
            Elements url_article = doc.getElementsByClass("topAnons").select("a");
            int n = img.size();
            for (int k = 0; k < n; k++) {
                img_url.add(img.get(k).attr("src"));
                web.add("https://www.ukrinform.ua" + url_article.get(k).attr("href"));
            }
            web.remove(web.size()-1); //problem with the last article
            int m = web.size();
            for (int k = 0; k < m; k++) {
                Document doc2 = Jsoup.connect(web.get(k)).userAgent("Mozilla").get();
                Elements title_article = doc2.getElementsByClass("newsTitle");
                if(title_article.size()!=0)
                    title.add(title_article.get(0).text());
            }
        }
        /*else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Url can't be scrapped");
        }*/
        int n = title.size();
        for(int k = 0 ; k<n ; k++){
            Article article;
            if(url==2) {
                article = new Article(warUkraine, "", title.get(k), "", text.get(k));
            }
            else {
                article = new Article(urlf, img_url.get(k), title.get(k), web.get(k), "");
            }
            articleRepository.save(article);
        }
    }


    public void deleteAll(){articleRepository.deleteAll();}
    public void deleteById(Long id){articleRepository.deleteById(id);}

    public Article getById(Long id){
        if(articleRepository.findById(id).isPresent()){
            return articleRepository.findById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "article not found");
        }
    }
}
