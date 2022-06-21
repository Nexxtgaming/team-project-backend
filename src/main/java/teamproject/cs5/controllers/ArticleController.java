package teamproject.cs5.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import teamproject.cs5.models.Article;
import teamproject.cs5.models.ERole;
import teamproject.cs5.payload.request.ArticleRequest;
import teamproject.cs5.security.services.UserDetailsImpl;
import teamproject.cs5.services.ArticleService;
import teamproject.cs5.services.UserService;

import java.io.IOException;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/article")
public class ArticleController {
    final ArticleService articleService;
    final UserService userService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @PostMapping("/createbyurl/{url}")
    public ResponseEntity<Article> createByUrl(@PathVariable("url") long url, Authentication authentication) throws IOException {
        articleService.createFromUrl(url);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getById(@PathVariable("id") Long id) {
        Article article = articleService.getById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<Article> deleteAll() {
        System.out.println("delete controller");
        articleService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public List<Article> findAll() {
        return articleService.findAll();
    }

    @GetMapping("/getbyurl/{url}")
    public List<Article> getByUrl(@PathVariable long url) {
        return articleService.getByUrl(url);
    }
}
