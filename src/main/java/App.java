import java.util.HashMap;
import java.util.List;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("brands", Brand.all());
      model.put("stores", Store.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/brands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("brands", Brand.all());
      model.put("template", "templates/brands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stores", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("stores", Store.all());
      model.put("template", "templates/stores.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/brands/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Brand newBrand = new Brand(name);
      newBrand.save();
      model.put("brands", Brand.all());
      model.put("template", "templates/brands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stores/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String address = request.queryParams("address");
      String area = request.queryParams("area");
      Store newStore = new Store(name, address, area);
      newStore.save();
      model.put("stores", Store.all());
      model.put("template", "templates/stores.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/brands/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int brand_id = Integer.parseInt(request.params("id"));
      Brand brand = Brand.find(brand_id);
      model.put("brand", brand);
      model.put("allstores", Store.all());
      model.put("stores", brand.getStores());
      model.put("template", "templates/brand.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/brands/:id/addStore", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int brand_id = Integer.parseInt(request.queryParams("brandId"));
      Brand brand = Brand.find(brand_id);
      int store_id = Integer.parseInt(request.queryParams("storeId"));
      brand.addStores(store_id);
      response.redirect("/brands/" + brand_id);
      return null;
    });

    get("/stores/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int store_id = Integer.parseInt(request.params("id"));
      Store store = Store.find(store_id);
      model.put("store", store);
      model.put("allbrands", Brand.all());
      model.put("brands", store.getBrands());
      model.put("template", "templates/store.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stores/:id/addBrand", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int store_id = Integer.parseInt(request.queryParams("storeId"));
      Store store = Store.find(store_id);
      int brand_id = Integer.parseInt(request.queryParams("brandId"));
      store.addBrands(brand_id);
      response.redirect("/stores/" + store_id);
      return null;
    });

    get("/stores/:id/delete", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int store_id = Integer.parseInt(request.params("id"));
      Store store = Store.find(store_id);
      store.delete();
      response.redirect("/stores");
      return null;
    });

    get("/stores/:id/update", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int store_id = Integer.parseInt(request.params("id"));
      Store store = Store.find(store_id);
      model.put("store", store);
      model.put("template", "templates/updatestore.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stores/:id/update", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int store_id = Integer.parseInt(request.params("id"));
      Store store = Store.find(store_id);
      String name = request.queryParams("name");
      String address = request.queryParams("address");
      String area = request.queryParams("area");
      store.update(name, address, area);
      response.redirect("/stores/" + store_id);
      return null;
    });

    get("/brand_search", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String brand_search = request.queryParams("brand_search");
      model.put("brands", Brand.searchByBrand(brand_search));
      model.put("template", "templates/brands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/store_search", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String store_search = request.queryParams("store_search");
      model.put("stores", Store.searchByStore(store_search));
      model.put("template", "templates/stores.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
