package idv.js;

import idv.js.solid.dip.MemberData;
import idv.js.solid.dip.MongoAdapter;
import idv.js.solid.dip.RedisAdapter;
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

    MemberData memberData = new MemberData(new MongoAdapter());
    memberData.saveMember(user);
    memberData.findMemberById("123");

    MemberData memberData2 = new MemberData(new RedisAdapter());
    memberData2.saveMember(user);
    memberData2.findMemberById("123");
  }
}
