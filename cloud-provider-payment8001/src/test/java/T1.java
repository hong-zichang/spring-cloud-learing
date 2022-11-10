import java.time.ZonedDateTime;

/**
 * author: ZiChangHong
 * create-date: 2022/11/13 20:34
 **/

//使用ZonedDateTime获得有时区的datetime
public class T1 {
    public static void main(String[] args) {
        ZonedDateTime z = ZonedDateTime.now();
        System.out.println(z);
    }
}
