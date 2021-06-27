package com.ye.back.util;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @PackageName:com.ye.back.util
 * @ClassName:JwtUtil
 * @Description:
 * @author:何进业
 * @date:2021/5/31 16:50
 */
public class JwtUtil {

    /**
     * 加密
     *
     * @param username
     * @param issuer
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String username, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("1sf12sds21ie1inecs078j");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(username)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /**
     * 解密
     *
     * @param jwt
     */
    public static Claims parseJWT(String jwt) {
        //This line will throw an exception if
        // it is not a signed JWS (as expected)
        try {


            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary("1sf12sds21ie1inecs078j"))
                    .parseClaimsJws(jwt)
                    .getBody();
            return claims;
        }catch (ExpiredJwtException e){
            return null;
        }
//        System.out.println("ID: " + claims.getId());
//        System.out.println("Subject: " + claims.getSubject());
//        System.out.println("Issuer: " + claims.getIssuer());
//        System.out.println("Expiration: " + claims.getExpiration());

    }

    public static boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    public static void main(String[] args) {

        //加密
        String jwt = createJWT("zhangsan", "13dsdda", "afrefsa", 1000 * 60 * 60 * 24 * 7);
        System.out.println(jwt);

        //解密
        parseJWT(jwt);
    }
}
