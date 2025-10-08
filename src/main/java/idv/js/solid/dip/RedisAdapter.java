package idv.js.solid.dip;

public class RedisAdapter implements DataAccess {
  @Override
  public void save(Object object) {
    System.out.println("Save to Redis: " + object);
  }

  @Override
  public Object findById(String id) {
    System.out.println("Find from Redis by id: " + id);
    return null;
  }
}
