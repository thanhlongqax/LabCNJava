package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args){
//        String url = "jdbc:mysql://root:@localhost:3306/";
        String url = args[0];

        try{
            Connection connection = DriverManager.getConnection(url);
            DBConnection dbConnection = new DBConnection();
            init(connection);
            connection.close();

            dbConnection.setUrl(url + "ProductManagement");

            String sql = "drop table if exists product";
            Connection connection1 = dbConnection.getConnection();

            Statement statement = connection1.createStatement();
            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS Product (" +
                    "    id int NOT NULL AUTO_INCREMENT," +
                    "    name varchar(255)," +
                    "    detail varchar(255)," +
                    "    price float," +
                    "    PRIMARY KEY (id)" +
                    ")";

            statement.executeUpdate(sql);

            statement.close();
            connection1.close();
            connection.close();

            boolean isTerminated = false;
            while (!isTerminated){
                System.out.println("1. Read all product");
                System.out.println("2. Read detail of a product by id");
                System.out.println("3. Add a new product");
                System.out.println("4. Update a product");
                System.out.println("5. Delete a product by id");
                System.out.println("6. Exit");
                System.out.print("\nYour choice:");
                Scanner scanner = new Scanner(System.in);
                ProductDAO productDAO = new ProductDAO();
                productDAO.setDbConnection(dbConnection);
                int opt = scanner.nextInt();
                scanner.nextLine();
                switch (opt){
                    case 1:
                        List<Product> list = productDAO.readAll();
                        for(Product product : list){
                            System.out.println(product.toString());
                        }
                        break;
                    case 2:
                        System.out.print("Enter product id: ");
                        long id2 = scanner.nextLong();
                        if(productDAO.read(id2) != null){
                            System.out.println(productDAO.read(id2).toString());
                        }else{
                            System.out.println("Product not found by id: "+id2);
                        }
                        break;
                    case 3:
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter product detail: ");
                        String detail = scanner.nextLine();
                        System.out.print("Enter product price: ");
                        double price = scanner.nextDouble();

                        Product product = new Product(0L, name, detail, price);
                        Long newId = productDAO.add(product);
                        System.out.println("Added product id: " + newId);
                        break;
                    case 4:
                        System.out.print("Enter product id: ");
                        long id4 = scanner.nextLong();
                        scanner.nextLine();
                        System.out.print("Enter product name: ");
                        String name1 = scanner.nextLine();
                        System.out.print("Enter product detail: ");
                        String detail1 = scanner.nextLine();
                        System.out.print("Enter product price: ");
                        double price1 = scanner.nextDouble();

                        Product product1 = new Product(id4, name1, detail1, price1);
                        productDAO.update(product1);
                        break;
                    case 5:
                        System.out.print("Enter product id: ");
                        long id5 = scanner.nextLong();
                        productDAO.delete(id5);
                        break;
                    case 6:
                        isTerminated = true;
                        break;
                    default:
                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void init(Connection connection){
        String sql = "Create database if not exists ProductManagement";
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Create ProductManagement database successfully");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

