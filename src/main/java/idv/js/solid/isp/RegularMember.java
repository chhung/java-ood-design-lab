package idv.js.solid.isp;

import idv.js.solid.srp.User;

public class RegularMember implements Member {
  private final User user;

  public RegularMember() {
    this.user = new User();
  }

  @Override
  public double getDiscount() {
    return user.getDiscount();
  }

  @Override
  public String getName() {
    return user.getName();
  }

  @Override
  public String getEmail() {
    return user.getEmail();
  }
}
