import io.jsonwebtoken.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;

/**
 * @Author RongHaoLu
 * @Date 2023/2/21 23:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = testCreateToken.class)
public class testCreateToken {

    @Test
    public void testCreateToken(){
        //生成token
        long now = System.currentTimeMillis();
        HashMap map = new HashMap();
        map.put("id",1);
        map.put("mobile","13800138000");
        //使用JWT工具生成token
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS512, "tanhua").setClaims(map)
                .setExpiration(new Date(now + 5000)).compact();
        System.out.println(token);

    }

    @Test
    public void testParaseToken(){
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzgwMDEzODAwMCIsImlkIjoxLCJleHAiOjE2NzY5OTQ1NTl9.Z0kjvha5ae0TwVnj5hdyT9beBEriLqMER8-tdz91KM7QK5fBckbSdKzP_3kXASzbK26IJoy8xzcM3p3e60xEOA";

        try {
            Claims claims = Jwts.parser().setSigningKey("tanhua").parseClaimsJws(token).getBody();

            Object id = claims.get("id");
            Object phone = claims.get("mobile");
            System.out.println(id+":"+phone);
        }catch (ExpiredJwtException e) {
            System.out.println("token已过期");
        }catch (SignatureException e) {
            System.out.println("token不合法");
        }

    }
}
