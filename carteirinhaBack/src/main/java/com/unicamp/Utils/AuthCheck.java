package com.unicamp.Utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Service;
import com.unicamp.Exceptions.AuthExceptions;
import com.unicamp.dao.OauthDao;

@Service
public class AuthCheck {
    private static OauthDao oAuthDao;
    private static final String KEY_MISMATCHED = "Authentication Problem. Key mismatched.";
    private static final String NO_SUCH_ALGORITHM = "Authentication Problem. No such algorithm enconder in environment.";
    private static final String UNSUPPORTED_ENCONDING = "Authentication Problem. Unsupported encoder format.";

    public AuthCheck(OauthDao oAuthDao) {
        AuthCheck.oAuthDao = oAuthDao;
    }

    public static Boolean authenticate(String givenKey, Integer id) throws AuthExceptions {
        if (oAuthDao.findById(id).get().getAccess_token().equals(givenKey)) {
            return true;
        } else {
            throw new AuthExceptions(KEY_MISMATCHED);
        }
    }

    public static Boolean authenticateAdmin(String givenKey, Integer id) throws AuthExceptions {
        if (id == 0 && oAuthDao.findById(id).get().getAccess_token().equals(givenKey)) {
            return true;
        } else {
            throw new AuthExceptions(KEY_MISMATCHED);
        }
    }

    public static String generateHash(String password) throws AuthExceptions {
        MessageDigest algorithm;
        byte messageDigest[];
        String finalHash = null;
        try {
            algorithm = MessageDigest.getInstance("SHA-256");
            messageDigest = algorithm.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            finalHash = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new AuthExceptions(NO_SUCH_ALGORITHM);
        } catch (UnsupportedEncodingException e) {
            throw new AuthExceptions(UNSUPPORTED_ENCONDING);
        }
        return finalHash;
    }
}
