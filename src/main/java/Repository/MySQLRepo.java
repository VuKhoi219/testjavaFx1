package Repository;

import Entity.Articles;

import java.util.ArrayList;

public interface MySQLRepo {
    ArrayList<Articles> finAllArticles();
    Articles finArticleById(int id);
    Articles save(Articles articles);
    Articles update(Articles articles);
    void delete(Articles articles);

}
