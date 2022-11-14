package com.unicamp.Utils;

import com.unicamp.Exceptions.AuthExceptions;

public class AuthCheck {
    // TODO: Change this after implementation of oAuth in database
    private static final String OAUTH_KEY = "909090";
    private static final String KEY_MISMATCHED = "Authentication Problem. Key mismatched.";

    public static Boolean authenticate(String givenKey) throws AuthExceptions {
        if (OAUTH_KEY.equals(givenKey)) {
            return true;
        } else {
            throw new AuthExceptions(KEY_MISMATCHED);
        }
    }
}
