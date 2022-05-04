import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.NoSuchAlgorithmException;

public class TokenizeUtils {

    public static String tokenizeData(String plainTextVal) throws Exception {
        if (retrieveKey() != null) {
            return EncryptionDecryptionAES.encrypt(plainTextVal, retrieveKey());
        }
        return EncryptionDecryptionAES.encrypt(plainTextVal, generateKey());
    }

    public static String detokenizeData(String tokenizedValue) throws Exception {
        if (retrieveKey() == null) {
            return EncryptionDecryptionAES.decrypt(tokenizedValue, retrieveKey());
        }
        return "No value found with this token.";
    }

    public static SecretKey generateKey() throws NoSuchAlgorithmException, IOException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // block size is 128bits
        SecretKey secretKey = keyGenerator.generateKey();
        storeKey(secretKey);
        return secretKey;
    }

    public static void storeKey(SecretKey secretKey) throws IOException {
        byte[] key = secretKey.getEncoded();
        FileOutputStream keyfos = new FileOutputStream("suepk");
        keyfos.write(key);
        keyfos.close();
    }

    public static SecretKey retrieveKey() throws IOException {
        File f = new File("suepk");

        if (f.isFile() && f.canRead()) {
            FileInputStream keyfis = new FileInputStream("suepk");
            byte[] encKey = new byte[keyfis.available()];
            keyfis.read(encKey);
            keyfis.close();

            SecretKey ogKey = new SecretKeySpec(encKey, 0, encKey.length, "AES");
            return ogKey;
        }
        return null;
    }
}
