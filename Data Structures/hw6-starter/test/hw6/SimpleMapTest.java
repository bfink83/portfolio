package hw6;

@SuppressWarnings("All")
public class SimpleMapTest extends MapTest {
  @Override
  protected Map createMap() {
    return new SimpleMap<String, String>();
  }
}