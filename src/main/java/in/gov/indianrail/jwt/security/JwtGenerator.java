package in.gov.indianrail.jwt.security;

import in.gov.indianrail.jwt.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class JwtGenerator {

    public String generate(JwtUser jwtUser) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 13);
        cal.set(Calendar.MINUTE, 35);
        cal.set(Calendar.SECOND, 45);
        cal.set(Calendar.MILLISECOND, 0);
         cal.getTime();

        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
                //.setExpiration(cal.getTime());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "hjkshdfuiashyfwerqwjkbrnmwbkl1454210sdf2swersdf54fa5s")
                .compact();
    }

}
