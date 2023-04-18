package com.agrify.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	public boolean validString(String s, int length, boolean equal) {
		s = s.trim();
		if (s.length() == 0) {
			return false;
		}
		if (equal == true) {
			if (s.length() == length) {
				return true;
			} else {
				return false;
			}
		} else {
			if (s.length() <= length) {
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean mailCheck(String email) {
		assert (validString(email, 30, false));

		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		boolean matchFound = matcher.find();
		if (matchFound == true) {
			return true;
		}
		return false;
	}
}