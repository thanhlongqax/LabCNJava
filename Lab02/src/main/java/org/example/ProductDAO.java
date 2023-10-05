package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Repository<Product, Long>{
    String url;
    DBConnection dbConnection;

    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public Long add(Product item) {
        Connection connection = dbConnection.getConnection();
        String name = item.getName();
        double price = item.getPrice();
        String detail = item.getDetail();
        String sql = "insert into product (name, price, detail) values (?, ?, ?)";
        try{
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setString(3, detail);

            int rows = preparedStatement.executeUpdate();
            long id = 0;
            if(rows == 1){
                System.out.println("Added product successfully");
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    id = resultSet.getLong(1);
                }
            }
            connection.close();
            preparedStatement.close();
            return id;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Product> readAll() {
        try{
            Connection connection = dbConnection.getConnection();
            String sql = "select * from product";
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);

            ResultSet resultSet = statement.getResultSet();
            List<Product> list = new ArrayList<Product>();

            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String detail = resultSet.getString("detail");
                double price = resultSet.getDouble("price");

                Product product = new Product(id, name, detail, price);
                list.add(product);
            }
            connection.close();
            statement.close();
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Product read(Long id) {
        String sql = "select * from product where id = ?";
        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            statement.executeQuery();

            ResultSet resultSet = statement.getResultSet();
            if(resultSet.next()){
                Long idd = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String detail = resultSet.getString("detail");
                double price = resultSet.getDouble("price");
                connection.close();
                statement.close();
                return new Product(idd, name, detail, price);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean update(Product item) {
        Long id = item.getId();
        String name = item.getName();
        String detail = item.getDetail();
        double price = item.getPrice();

        String sql = "update product set name = ?, detail = ?, price = ? where id = ?";
        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, detail);
            statement.setDouble(3, price);
            statement.setLong(4, id);
            int rows = statement.executeUpdate();
            connection.close();
            statement.close();
            if(rows == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean delete(Long id) {
        String sql = "delete from product where id = ?";
        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            int rows = statement.executeUpdate();
            connection.close();
            statement.close();
            if(rows == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}

