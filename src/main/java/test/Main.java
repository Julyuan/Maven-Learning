//////package test;
//////
//////import org.openjdk.jmh.annotations.Benchmark;
//////import org.openjdk.jmh.runner.Runner;
//////import org.openjdk.jmh.runner.RunnerException;
//////import org.openjdk.jmh.runner.options.Options;
//////import org.openjdk.jmh.runner.options.OptionsBuilder;
//////
//////import javax.crypto.Cipher;
//////import javax.crypto.KeyGenerator;
//////import javax.crypto.SecretKey;
//////import javax.crypto.spec.SecretKeySpec;
//////import java.security.*;
//////import java.security.interfaces.ECPrivateKey;
//////import java.security.interfaces.ECPublicKey;
//////import java.security.spec.PKCS8EncodedKeySpec;
//////import java.security.spec.X509EncodedKeySpec;
//////
//////public class Main {
////////    @Benchmark
////////    public void wellHelloThere() {
////////        // this method was intentionally left blank.
////////    }
//////
//////      private static String src="Hello SHA";
//////    @Benchmark
//////    public static double jdkSHA1(){
//////        MessageDigest digest;
//////        double ans = 0.0;
//////        try {
//////            digest = MessageDigest.getInstance("SHA-256");
//////            digest.update(src.getBytes());
//////            byte[] a =digest.digest();
//////            ans = ans + 1.0;
//////        } catch (NoSuchAlgorithmException e) {
//////            e.printStackTrace();
//////        }
//////
//////        return ans;
//////    }
//////
//////    @Benchmark
//////    public static void jdkAES( ){
//////        try {
//////            KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
//////            keyGenerator.init(new SecureRandom());
//////            SecretKey secretKey = keyGenerator.generateKey();
//////            byte[] key1 = secretKey.getEncoded();
//////
//////            Key key2 = new SecretKeySpec(key1, "AES");
//////
//////            Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5padding");
//////            cipher.init(Cipher.ENCRYPT_MODE, key2);
//////            byte[] result = cipher.doFinal(src.getBytes());
//////
//////           cipher.init(Cipher.DECRYPT_MODE, key2);
//////            result = cipher.doFinal(result);
//////        } catch (Exception e) {
//////            e.printStackTrace();
//////        }
//////    }
//////
//////    @Benchmark
//////    public static void jdkECDSA() {
//////        try {
//////            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");//参数是EC
//////            keyPairGenerator.initialize(256);
//////            KeyPair keyPair = keyPairGenerator.generateKeyPair();
//////            ECPublicKey ecPublicKey = (ECPublicKey) keyPair.getPublic();
//////            ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair.getPrivate();
//////
//////                        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());
//////                        KeyFactory keyFactory = KeyFactory.getInstance("EC");
//////                        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
//////                        Signature signature = Signature.getInstance("SHA1WithECDSA");
//////                        signature.initSign(privateKey);
//////                        signature.update(src.getBytes());
//////                        byte[] result = signature.sign();
//////
//////                         X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(ecPublicKey.getEncoded());
//////                        keyFactory = KeyFactory.getInstance("EC");
//////                        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
//////                        signature = Signature.getInstance("SHA1WithECDSA");
//////                        signature.initVerify(publicKey);
//////                        signature.update(src.getBytes());
//////                        boolean bool = signature.verify(result);
//////        }
//////        catch (Exception e)
//////        {
//////            e.printStackTrace();
//////        }
//////    }
//////
//////
//////    public static void main(String[] args) throws RunnerException {
//////        Options opt = new OptionsBuilder()
//////                .include(Main.class.getSimpleName())
//////                .forks(1)
//////                .build();
//////
//////        new Runner(opt).run();
//////    }
//////
//////
//////}
////
////package test;
////
////import org.openjdk.jmh.annotations.*;
////import org.openjdk.jmh.runner.Runner;
////import org.openjdk.jmh.runner.RunnerException;
////import org.openjdk.jmh.runner.options.Options;
////import org.openjdk.jmh.runner.options.OptionsBuilder;
////
////import javax.crypto.Cipher;
////import javax.crypto.KeyGenerator;
////import javax.crypto.SecretKey;
////import javax.crypto.spec.SecretKeySpec;
////import java.security.*;
////import java.security.interfaces.ECPrivateKey;
////import java.security.interfaces.ECPublicKey;
////import java.security.spec.PKCS8EncodedKeySpec;
////import java.security.spec.X509EncodedKeySpec;
////import java.util.Random;
////
////@State(Scope.Thread)
////public class Main {
////    private static String src="Hello SHA";
////    static byte[] msg_SHA256 = randomBytes(16);;
////    static byte[] msg_AES;
////    static Key key_AES;
//////    static byte[] msg_ECDSA_Sign = randomBytes(59);
//////    static byte[] msg_ECDSA_Verify;
//////    static KeyPair keyPair_ECDSA_Sign;
//////    static byte[] sigResult_ECDSA_Sign;
//////    static KeyPair keyPair_ECDSA_Verify;
//////    static byte[] sigResult_ECDSA_Verify;
////    static byte[] msg_ECDSA;
////    static KeyPair keyPair_ECDSA;
////    static byte[] sigResult_ECDSA;
////
////    static byte[] key1_AES;
////    static Key key2_AES;
////    static KeyGenerator keyGenerator;
////    static SecretKey secretKey;
////
////    @Setup(Level.Trial)
////    public static void Init(){
////        try {
////            msg_SHA256 = randomBytes(16);
////            keyGenerator = KeyGenerator.getInstance("AES");
////            keyGenerator.init(new SecureRandom());
////            secretKey = keyGenerator.generateKey();
////
////
////            key1_AES = secretKey.getEncoded();
////            key2_AES = new SecretKeySpec(key1_AES, "AES");
////
////            msg_ECDSA = randomBytes(59);
////            keyPair_ECDSA = jdkECDSAGernerateKey();
////            sigResult_ECDSA = jdkECDSASign();
//////            msg_ECDSA_Sign = randomBytes(59);
//////            msg_ECDSA_Verify = randomBytes(59);
////
//////            keyPair_ECDSA_Sign = jdkECDSAGernerateKey();
//////            sigResult_ECDSA_Sign = jdkECDSASign();
//////            keyPair_ECDSA_Verify = jdkECDSAGernerateKey();
//////            sigResult_ECDSA_Verify = jdkECDSASign();
////        }
////        catch (Exception e){
////            e.printStackTrace();
////        }
////    }
////
////    @TearDown(Level.Trial)
////    public static void finish(){}
////
////    public static void main(String[] args)throws RunnerException {
////        Init();
//////        System.out.println(msg_SHA256);
//////        System.out.println(msg_ECDSA);
//////        System.out.println(keyPair_ECDSA);
//////        System.out.println(sigResult_ECDSA);
////        Options opt = new OptionsBuilder()
////        .include(Main.class.getSimpleName())
////        .warmupIterations(5)
////        .measurementIterations(5)
////        .forks(1)
////        .build();
////
////        new Runner(opt).run();
////    }
////
////    @Benchmark
////    public static void jdkSHA256(){
////        MessageDigest digest;
////        try {
////            digest = MessageDigest.getInstance("SHA-256");
////            digest.update(msg_SHA256);
////            digest.digest();
////        } catch (NoSuchAlgorithmException e) {
////            e.printStackTrace();
////        }
////    }
////
////    @Benchmark
////    public static void jdkAES(){
////        try {
////            //加密
////            Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5padding");
////            cipher.init(Cipher.ENCRYPT_MODE, key2_AES);
////            byte[] result = cipher.doFinal(src.getBytes());
////
//////            //解密
//////            cipher.init(Cipher.DECRYPT_MODE, key2);
//////            result = cipher.doFinal(result);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
////
////    @Benchmark
////    public static KeyPair jdkECDSAGernerateKey()
////    {
////        try {
////            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");//参数是EC
////            keyPairGenerator.initialize(256);
////            KeyPair keyPair = keyPairGenerator.generateKeyPair();
////            return keyPair;
////        }
////        catch (Exception e)
////        {
////            e.printStackTrace();
////            return null;
////        }
////    }
////
////    @Benchmark
////    public static byte[] jdkECDSASign() {
////        try {
////            //1.初始化密钥
//////            ECPublicKey ecPublicKey = (ECPublicKey) keyPair_ECDSA_Sign.getPublic();
//////            ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair_ECDSA_Sign.getPrivate();
////            ECPublicKey ecPublicKey = (ECPublicKey) keyPair_ECDSA.getPublic();
////            ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair_ECDSA.getPrivate();
////            //2.执行签名【私钥签名】
////            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());
////            KeyFactory keyFactory = KeyFactory.getInstance("EC");
////            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
////            Signature signature = Signature.getInstance("SHA256WithECDSA");
////            signature.initSign(privateKey);
//////            signature.update(msg_ECDSA_Sign);//需要签名的字符串
////            signature.update(msg_ECDSA);
////            byte[] result = signature.sign();
////
////            return result;
////        }
////        catch (Exception e)
////        {
////            e.printStackTrace();
////            return null;
////        }
////    }
////
////    @Benchmark
////    public static void jdkECDSAVerify() {
////        try {
////            //1.初始化密钥
//////            ECPublicKey ecPublicKey = (ECPublicKey) keyPair_ECDSA_Verify.getPublic();
//////            ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair_ECDSA_Verify.getPrivate();
////
////            ECPublicKey ecPublicKey = (ECPublicKey) keyPair_ECDSA.getPublic();
////            ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair_ECDSA.getPrivate();
////            //3.验证签名【公钥验证】
////            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());
////            KeyFactory keyFactory = KeyFactory.getInstance("EC");
////            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
////            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(ecPublicKey.getEncoded());
////            keyFactory = KeyFactory.getInstance("EC");
////            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
////            Signature signature = Signature.getInstance("SHA256WithECDSA");
////            signature = Signature.getInstance("SHA256WithECDSA");
////            signature.initVerify(publicKey);
//////            signature.update(msg_ECDSA_Verify);
//////            boolean bool = signature.verify(sigResult_ECDSA_Verify);
////            signature.update(msg_ECDSA);
////            boolean bool = signature.verify(sigResult_ECDSA);
////         //   System.out.println(bool);
////        }
////        catch (Exception e)
////        {
////            e.printStackTrace();
////        }
////    }
////
////    public static byte[] randomBytes(int length)
////    {
////        byte[] result = new byte[length];
////        Random r = new Random();
////
////        for(int i = 0; i < length; i++)
////        {
////            result[i] = (byte)(r.nextInt(256));
////        }
////
////        return result;
////    }
////
////}
//
//package test;
//
//import com.sun.jna.Library;
//import com.sun.jna.Native;
//import com.sun.jna.Structure;
//
//import java.security.PublicKey;
//import java.util.List;
//
///** Simple example of JNA interface mapping and usage. */
//public class PublicKey extends Structure{
//
//    @Override
//    protected List<String> getFieldOrder() {
//        return null;
//    }
//
//
//}
//
//public class Main {
//
//    // This is the standard, stable way of mapping, which supports extensive
//    // customization and mapping of Java to native types.
//
//    public interface CLibrary extends Library {
//        CLibrary INSTANCE = (CLibrary)
//                  Native.loadLibrary("libGMSM2",CLibrary.class);
//              //  Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"),
//              //          CLibrary.class);
//
//        //void printf(String format, Object... args);
//        PublicKey* NewPubKey(byte *x, byte *y);
//
//    }
//
//    public static void main(String[] args) {
//        CLibrary.INSTANCE.printf("Hello, World\n");
//        for (int i=0;i < args.length;i++) {
//            CLibrary.INSTANCE.printf("Argument %d: %s\n", i, args[i]);
//        }
//    }
//}