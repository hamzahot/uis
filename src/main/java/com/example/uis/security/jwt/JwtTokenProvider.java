package com.example.uis.security.jwt;


import com.example.uis.security.dto.TokenDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private static final String AUTH_KEY = "auth";

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.validityInMinutes}")
    private long tokenValidityInMinutes;

    @Value("${jwt.refreshTokenValidityInMinutes}")
    private long refreshTokenValidityInMinutes;



    /**
     * Generate token from given authentication object
     *
     * @param authentication reference to authenticate object
     * @return String value of JWT token
     */
    public TokenDTO generateToken(Authentication authentication, boolean rememberMe)
    {
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")); // authorities -> role

        // [{name: ROLE_ADMIN}, {name: ROLE_DEVELOPER}]
        // String => "ROLE_ADMIN,ROLE_DEVELOPER"

        long now = new Date().getTime();
        Date accessTokenValidity = new Date(now + tokenValidityInMinutes * 60_000); // now() + 5 minuta




        String accessToken = Jwts.builder()
                .setSubject(authentication.getName()) // username
                .claim(AUTH_KEY, authorities)
                //SignatureAlgorithm.HS512, secretKey
                .signWith(SignatureAlgorithm.HS512, getSignKey())
                .setExpiration(accessTokenValidity)
                .compact();

        String refreshToken = null;
        if (rememberMe) //    ako ne naglasimo da je true onda je false
        {
            Date refreshTokenValidity = new Date(now + refreshTokenValidityInMinutes * 60_000);
            refreshToken = Jwts.builder()
                    .setSubject(authentication.getName())
                    .claim(AUTH_KEY, authorities)
                    .signWith(SignatureAlgorithm.HS512, getSignKey())
                    .setExpiration(refreshTokenValidity)
                    .compact();
        }
        return new TokenDTO(accessToken, refreshToken);
    }

    /**
     * Create authentication from token
     *
     * @param token jwt token
     * @return Authentication Object
     */
    public Authentication getAuthentication(String token)
    {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject(); // username

        // "ROLE_ADMIN,ROLE_DEVELOPER" -> [{"name": "ROLE_ADMIN"}, {"name": "ROLE_DEVELOPER"}]

        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTH_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new UsernamePasswordAuthenticationToken(username, "", authorities);
    }

    /**
     * Validate token
     *
     * @param authToken jwt token
     * @return boolean value (is valid | not)
     */
    public boolean validateToken(String authToken)
    {
        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
        return true;
    }


    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }






}
