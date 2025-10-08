package idv.js.solid.dip;

public class MongoAdapter implements DataAccess {
  @Override
  public void save(Object object) {
    System.out.println("Save to MongoDB: " + object);
  }

  @Override
  public Object findById(String id) {
    System.out.println("Find from MongoDB by id: " + id);
    return null;
  }
}
