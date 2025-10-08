package idv.js.solid.isp;

import idv.js.solid.srp.VIP;

public class PremiumMember implements Member {
  private final VIP vip;

  public PremiumMember() {
    this.vip = new VIP();
  }

  @Override
  public double getDiscount() {
    return 0;
  }

  @Override
  public String getName() {
    return vip.getName();
  }

  @Override
  public String getEmail() {
    return vip.getEmail();
  }
}
