package idv.js;

import idv.js.solid.ocp.Member;
import idv.js.solid.srp.User;
import idv.js.solid.srp.VIP;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {

    User user = new User();
    Member member = new Member(user);
    System.out.println("discount: " + member.getDiscount());

    VIP vip = new VIP();
    // 這裡需要再new一個 Member 物件, 因為給Member的建構子物件是不能改參考的
    //member.setUser(vip);
    Member vipMember = new Member(vip);
    System.out.println("VIP discount: " + vipMember.getDiscount());

  }
}
