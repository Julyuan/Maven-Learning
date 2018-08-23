//package test;
//
//import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.security.*;
//import java.security.interfaces.ECPrivateKey;
//import java.security.interfaces.ECPublicKey;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Random;
//
//public class CryptoTest {
//    private static String src="Hello SHA";
//    static byte[] msg_SHA256;
//    static byte[] msg_AES;
//    static Key key_AES;
//    static byte[] msg_ECDSA;
//    static KeyPair keyPair_ECDSA;
//    static byte[] sigResult_ECDSA;
//    static byte[] key1_AES;
//    static Key key2_AES;
//    static KeyGenerator keyGenerator;
//    static SecretKey secretKey;
//
//    CryptoTest(){
//        Init();
//    }
//
//    static void Init(){
//        try {
//            msg_SHA256 = randomBytes(16);
//
//
//            keyGenerator = KeyGenerator.getInstance("AES");
//            keyGenerator.init(new SecureRandom());
//            secretKey = keyGenerator.generateKey();
//
//
//            key1_AES = secretKey.getEncoded();
//            key2_AES = new SecretKeySpec(key1_AES, "AES");
//            msg_ECDSA = randomBytes(59);
//            keyPair_ECDSA = jdkECDSAGernerateKey();
//            sigResult_ECDSA = jdkECDSASign();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void main(String[] args){
//        Init();
//        jdkSHA256();
//        jdkAES();
//
//    }
//
//    public static void jdkSHA256(){
//        MessageDigest digest;
//        try {
//            digest = MessageDigest.getInstance("SHA-256");
//            digest.update(msg_SHA256);
//            digest.digest();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //@Benchmark
//    public static void jdkAES(){
//        try {
//            //加密
//            Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5padding");
//            cipher.init(Cipher.ENCRYPT_MODE, key2_AES);
//            byte[] result = cipher.doFinal(src.getBytes());
//
////            //解密
////            cipher.init(Cipher.DECRYPT_MODE, key2);
////            result = cipher.doFinal(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static KeyPair jdkECDSAGernerateKey()
//    {
//        try {
//            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");//参数是EC
//            keyPairGenerator.initialize(256);
//            KeyPair keyPair = keyPairGenerator.generateKeyPair();
//            return keyPair;
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static byte[] jdkECDSASign() {
//        try {
//            //1.初始化密钥
//            ECPublicKey ecPublicKey = (ECPublicKey) keyPair_ECDSA.getPublic();
//            ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair_ECDSA.getPrivate();
//
//            //2.执行签名【私钥签名】
//            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());
//            KeyFactory keyFactory = KeyFactory.getInstance("EC");
//            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
//            Signature signature = Signature.getInstance("SHA256WithECDSA");
//            signature.initSign(privateKey);
//            signature.update(msg_ECDSA);//需要签名的字符串
//            byte[] result = signature.sign();
//
//            return result;
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static void jdkECDSAVerify() {
//        try {
//            //1.初始化密钥
//            ECPublicKey ecPublicKey = (ECPublicKey) keyPair_ECDSA.getPublic();
//            ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair_ECDSA.getPrivate();
//
//            //3.验证签名【公钥验证】
//            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());
//            KeyFactory keyFactory = KeyFactory.getInstance("EC");
//            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
//            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(ecPublicKey.getEncoded());
//            keyFactory = KeyFactory.getInstance("EC");
//            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
//            Signature signature = Signature.getInstance("SHA256WithECDSA");
//            signature = Signature.getInstance("SHA256WithECDSA");
//            signature.initVerify(publicKey);
//            signature.update(msg_ECDSA);
//            boolean bool = signature.verify(sigResult_ECDSA);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    public static byte[] randomBytes(int length)
//    {
//        byte[] result = new byte[length];
//        Random r = new Random();
//
//        for(int i = 0; i < length; i++)
//        {
//            result[i] = (byte)(r.nextInt(256));
//        }
//
//        return result;
//    }
//
//}
