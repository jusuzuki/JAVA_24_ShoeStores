import org.junit.*;
import static org.junit.Assert.*;

public class StoreTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Store.all().size(), 0);
  }

  @Test
  public void all_savesIntoDatabase_true() {
    Store myStore = new Store("REI", "1405 NW Johnson St", "NW");
    myStore.save();
    assertEquals(Store.all().get(0).getName(), "REI");
  }

  @Test
  public void stores_instantiatesCorrectly_true() {
    Store myStore = new Store("Patagonia", "907 NW Irving St, Portland", "NW");
    assertEquals(true, myStore instanceof Store);
  }

  @Test
  public void find_findsStoreInDatabase_true() {
    Store myStore = new Store("North Face", "1202 NW Davis St, Portland", "NW");
    myStore.save();
    Store savedStore = Store.find(myStore.getId());
    assertEquals(savedStore.getName(), "North Face");
  }


}
