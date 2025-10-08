package idv.js.solid.dip;

public class MemberData {
  private final DataAccess dataAccess;

  public MemberData(DataAccess dataAccess) {
    this.dataAccess = dataAccess;
  }

  public void saveMember(Object member) {
    dataAccess.save(member);
  }

  public Object findMemberById(String id) {
    return dataAccess.findById(id);
  }
}
