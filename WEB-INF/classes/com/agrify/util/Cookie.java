package com.agrify.util;

import java.util.Base64;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;

/**
 * Cookie
 */
public class Cookie {

	// Get cookie via the name of the cookie
	public static javax.servlet.http.Cookie getCookie(HttpServletRequest request, String name) {
		javax.servlet.http.Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (javax.servlet.http.Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					if (cookie != null) {
						return cookie;
					}
				}
			}
		}

		return null;
	}
	
	// Get cookie data via the name of the cookie
	public static JSONObject getCookieData(HttpServletRequest request, String name) {
		javax.servlet.http.Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (javax.servlet.http.Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					if (cookie != null) {
						String dataString = new String(Base64.getDecoder().decode(cookie.getValue()));
						JSONParser parser = new JSONParser();
						JSONObject data;
						try {
							data = (JSONObject) parser.parse(dataString);
							return data;
						} catch (ParseException e) {
							System.out.println(e.getMessage());
							e.printStackTrace();
							return null;
						}
					}
				}
			}
		}

		return null;
	}
}