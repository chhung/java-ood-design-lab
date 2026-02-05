package idv.js;

import idv.js.generic.Result;
import idv.js.solid.dip.MemberData;
import idv.js.solid.dip.MongoAdapter;
import idv.js.solid.dip.RedisAdapter;
import idv.js.solid.ocp.Member;
import idv.js.solid.srp.User;
import idv.js.solid.srp.VIP;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    List<String> list = new LinkedList<>();
    list.add("A");
    list.add("B");
    list.add("C");

    printList(list);

    List<Integer> number = new LinkedList<>();
    number.add(423);
    number.add(7872345);
    number.add(4230);
    printList(number);

    Map<Integer, String> map = new HashMap<>();
    map.put(1, "One");
    map.put(2, "Two");
    map.put(3, "Three");
    printMap(map);

    Result<Map<Integer, String>> result = new Result<>("Success", map);
    Result<String> result2 = new Result<>("Success", "html format");
    Result<Integer> result3 = new Result<>("Success", 9001);

    // 展示 Result 的使用
    System.out.println("\n=== Result 範例 ===");
    System.out.println("Result 1 - Message: " + result.getMessage() + ", Data: " + result.getData());
    System.out.println("Result 2 - Message: " + result2.getMessage() + ", Data: " + result2.getData());
    System.out.println("Result 3 - Message: " + result3.getMessage() + ", Data: " + result3.getData());

  }

  public static <K, V> void printMap(Map<K, V> map) {
    for (Map.Entry<K, V> entry : map.entrySet()) {
      System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
    }
  }

  public static <E> void printList(List<E> list) {
    for (Iterator<E> iterator = list.iterator(); iterator.hasNext(); ) {
      E item = iterator.next();
      System.out.println("Item: " + item);
    }
  }



}
