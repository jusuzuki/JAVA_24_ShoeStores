import org.junit.*;
import static org.junit.Assert.*;

public class BrandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Brand.all().size(), 0);
  }

  @Test
  public void all_savesIntoDatabase_true() {
  Brand myBrand = new Brand("Onitsuka Tiger");
  myBrand.save();
  assertEquals(Brand.all().get(0).getName(), "Onitsuka Tiger");
  }

  @Test
  public void brands_instantiatesCorrectly_true() {
    Brand myBrand = new Brand("Asics");
    assertEquals(true, myBrand instanceof Brand);
  }

  @Test
    public void find_findsBrandInDatabase_true() {
      Brand myBrand = new Brand("New Balance");
      myBrand.save();
      Brand savedBrand = Brand.find(myBrand.getId());
      assertEquals(savedBrand.getName(), "New Balance");
    }
}
