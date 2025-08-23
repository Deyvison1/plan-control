package com.plancontrol.api.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.plancontrol.api.service.ITokenService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenServiceImpl implements ITokenService {

	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "uH12jPqz0W+o+fSde9q5Tf0mXEtmCqKnm2Vp3xczxUg=";

	@Override
	public DecodedJWT decodedToken() {
		String token = getToken();
		Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
		JWTVerifier verifier = JWT.require(alg).build();
		return verifier.verify(token);
	}

	@Override
	public String getToken() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		HttpServletRequest request = attrs.getRequest();
		String token = request.getHeader("Authorization");

		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7); // remove "Bearer "
		}
		return token;

	}
}
