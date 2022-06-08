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

    @PostMapping
    public ResponseEntity<Article> create(@RequestBody ArticleRequest request, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if(userService.isRole(userDetails.getId(), ERole.ROLE_ADMIN)){
            Article article = articleService.createFromRequest(request);
            return new ResponseEntity<>(article, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/{url}")
    public ResponseEntity<Article> createByUrl(@PathVariable("url") String url, Authentication authentication) throws IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if(userService.isRole(userDetails.getId(), ERole.ROLE_ADMIN)){
            articleService.createFromUrl(url);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getById(@PathVariable("id") Long id){
        Article article = articleService.getById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Article article = articleService.getById(id);
        if(userService.isRole(userDetails.getId(), ERole.ROLE_ADMIN)){
            articleService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/all")
    public List<Article> findAll(){
        return articleService.findAll();
    }

    @GetMapping
    public List<Article> getByCity(@RequestParam String url){
        if(url.equals("all") || url.isBlank()){
            return articleService.findAll();
        }
        return articleService.getByUrl(url);
    }
}
