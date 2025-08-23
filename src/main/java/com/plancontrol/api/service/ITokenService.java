package com.plancontrol.api.service;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface ITokenService {

	DecodedJWT decodedToken();

	String getToken();

}
