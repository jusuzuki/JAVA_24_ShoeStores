import java.util.List;
import java.util.ArrayList;
import java.util.*;
import org.sql2o.*;

public class Brand {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Brand (String name){
    this.name = name;
  }

  public static List<Brand> all() {
    String sql = "SELECT * FROM brands";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Brand.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO brands (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Brand find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM brands where id=:id";
      Brand brand = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Brand.class);
      return brand;
    }
  }

  public List<Store> getStores() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT stores.* FROM stores JOIN stores_brands ON (stores.id = stores_brands.store_id) JOIN brands ON (stores_brands.brand_id = brands.id) WHERE brands.id =:id";
      List<Store> stores = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Store.class);
      return stores;
    }
  }

  public void addStores(int store_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stores_brands (store_id, brand_id) VALUES (:store_id, :brand_id)";
      con.createQuery(sql)
        .addParameter("brand_id", id)
        .addParameter("store_id", store_id)
        .executeUpdate();
    }
  }

  public static List<Brand> searchByBrand(String brand_name) {
    String sql = "SELECT * FROM brands WHERE name LIKE '%" + brand_name + "%'";
    List<Brand> searchResults;
    try (Connection con = DB.sql2o.open()) {
      searchResults = con.createQuery(sql)
        .executeAndFetch(Brand.class);
    }
    return searchResults;
  }

}
