import java.util.List;
import java.util.ArrayList;
import java.util.*;
import org.sql2o.*;

public class Store {
  private int id;
  private String name;
  private String address;
  private String area;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getArea() {
    return area;
  }

  public Store (String name, String address, String area){
    this.name = name;
    this.address = address;
    this.area = area;
  }

  public static List<Store> all() {
    String sql = "SELECT * FROM stores";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Store.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stores (name, address, area) VALUES (:name, :address, :area)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("address", address)
        .addParameter("area", area)
        .executeUpdate()
        .getKey();
    }
  }

  public static Store find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stores where id=:id";
      Store store = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Store.class);
      return store;
    }
  }

  public List<Brand> getBrands() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT brands.* FROM brands JOIN stores_brands ON (brands.id = stores_brands.brand_id) JOIN stores ON (stores_brands.store_id = stores.id) WHERE stores.id =:id";
      List<Brand> brands = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Brand.class);
      return brands;
    }
  }

  public void addBrands(int brand_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stores_brands (store_id, brand_id) VALUES (:store_id, :brand_id)";
      con.createQuery(sql)
        .addParameter("brand_id", brand_id)
        .addParameter("store_id", id)
        .executeUpdate();
    }
  }

  public void update(String name, String address, String area) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stores SET name = :name, address = :address, area = :area WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("address", address)
        .addParameter("area", area)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stores WHERE id=:id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
      String joinsql = "DELETE FROM stores_brands WHERE store_id=:id";
      con.createQuery(joinsql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static List<Store> searchByStore(String store_name) {
    String sql = "SELECT * FROM stores WHERE name LIKE '%" + store_name + "%'";
    List<Store> searchResults;
    try (Connection con = DB.sql2o.open()) {
      searchResults = con.createQuery(sql)
        .executeAndFetch(Store.class);
    }
    return searchResults;
  }

}
