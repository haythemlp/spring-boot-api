package com.haythemlp.springboot.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    private final String CLAIMS_SUB="sub";
    private final String CLAIMS_CREATED="created";

    @Value("${auth.expiration}")
    private  Long TOKEN_VALIDITY=608400L;

    @Value("${auth.secret}")
    private String TOKEN_SECRET="spring";

    private String generateToken(UserDetails userDetails)
    {


        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIMS_SUB,userDetails.getUsername());
        claims.put(CLAIMS_CREATED,new Date());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,TOKEN_SECRET)
                .compact();
    }

    private  Date generateExpirationDate(){

return new Date(System.currentTimeMillis()+TOKEN_VALIDITY *1000);
    }

}
