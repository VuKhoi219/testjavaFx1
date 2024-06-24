package Repository;

import Entity.Articles;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class MySQLRepository implements MySQLRepo{
    private final String MYSQL_CONNECTION= "jdbc:mysql://localhost:3306/article";
    private final String MYSQL_USERNAME= "root";
    private final String MYSQL_PASSWORD= "";


    @Override
    public ArrayList<Articles> finAllArticles() {
        ArrayList<Articles> articles = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(MYSQL_CONNECTION,MYSQL_USERNAME,MYSQL_PASSWORD)){
            String sql = "Select id ,Title,describe1,Content from articles1 where status = 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Articles article = new Articles();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("Title"));
                article.setDes(rs.getString("describe1"));
                article.setContent(rs.getString("Content"));
                articles.add(article);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

    @Override
    public Articles finArticleById(int id) {
        Articles article = null;
        try(Connection conn =DriverManager.getConnection(MYSQL_CONNECTION,MYSQL_USERNAME,MYSQL_PASSWORD)){
            PreparedStatement preparedStatement = conn.prepareStatement("select from articles1 where url=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setDes(rs.getString("des"));
                article.setContent(rs.getString("content"));
                article = new Articles();
            } else {
                System.out.println("No such url");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return article;
    }

    @Override
    public Articles save(Articles articles) {
        try(Connection conn =DriverManager.getConnection(MYSQL_CONNECTION,MYSQL_USERNAME,MYSQL_PASSWORD)){
            String sql = "Insert into articles1(id ,Title,describe1,Content,status) values(?,?,?,?,1)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, articles.getId());
            preparedStatement.setString(2, articles.getTitle());
            preparedStatement.setString(3, articles.getDes());
            preparedStatement.setString(4, articles.getContent());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            JOptionPane.showMessageDialog(null,"Save"); // hiển thị thông báo
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

    @Override
    public Articles update(Articles articles) {
        try(Connection conn =DriverManager.getConnection(MYSQL_CONNECTION,MYSQL_USERNAME,MYSQL_PASSWORD)){
            String sql = "Update articles1 set Title=?,describe1=?,Content=? where id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, articles.getTitle());
            preparedStatement.setString(2, articles.getDes());
            preparedStatement.setString(3, articles.getContent());
            preparedStatement.setInt(4, articles.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

    @Override
    public void delete(Articles articles) {
        try(Connection conn =DriverManager.getConnection(MYSQL_CONNECTION,MYSQL_USERNAME,MYSQL_PASSWORD)){
            String sql = "Update articles1 set status = -1 where id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, articles.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
