package GuomiJava;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;

@State(Scope.Thread)
public class Guomi_Interface {
    // Definitions of public and private keys for sm2

    static GuomiJava.Jni_Guomi nativeCode;
    static PriKey Pri_Key;
    static byte[] msg;
    static byte[] sig;
    static byte[] plain;
    static byte[] sm4key;
    static byte[] iv;
    static byte[] cypher;


    @Setup(Level.Trial)
    public static void init(){
        nativeCode = getNative();
        Pri_Key = sm2GetKey(nativeCode);
        msg = randomBytes(59);
        sig = sm2Sign(msg, Pri_Key, nativeCode);
        plain = new byte[32];
        for(int i=0;i<32;i++)
            plain[i] = (byte)i;
        sm4key = "1234567890123456".getBytes();
        iv = randomBytes(16);
        cypher = sm4EncCBC(plain, sm4key, iv ,nativeCode);

    }
    @TearDown(Level.Trial)
    public static void finish(){}
    public static class PubKey
    {
        byte[] X;
        byte[] Y;
    }
    public static class PriKey
    {
        PubKey Pub_Key;
        byte[] Key;
    }

    // Get native methods
    public static GuomiJava.Jni_Guomi getNative()
    {
        GuomiJava.Jni_Guomi nativeCode = new GuomiJava.Jni_Guomi();
        try {
            System.loadLibrary("NativeCode");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return nativeCode;
    }

    @Benchmark
    public static void sm2GetKeyTest(){
        GuomiJava.Jni_Guomi nativeCode = getNative();

        sm2GetKey(nativeCode);

    }

    //Randomly generate an sm2 key pair
    public static PriKey sm2GetKey(GuomiJava.Jni_Guomi nativeCode)
    {
        PriKey result = new PriKey();
        result.Pub_Key = new PubKey();
        result.Pub_Key.X = new byte[32];
        result.Pub_Key.Y = new byte[32];
        result.Key = new byte[31];

        try{
            nativeCode.sm2GenerateKey(result.Pub_Key.X, result.Pub_Key.Y, result.Key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    @Benchmark
    public static byte[] sm2SignTest(){

        return sm2Sign(msg, Pri_Key, nativeCode);

    }

    //sm2 sign method
    public static byte[] sm2Sign(byte[] msg, PriKey Pri_Key, GuomiJava.Jni_Guomi nativeCode)
    {
        byte[] signature = null;

        try{
            signature = nativeCode.sm2Sign(Pri_Key.Pub_Key.X, Pri_Key.Pub_Key.Y, Pri_Key.Key, msg, msg.length);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return signature;
    }

    @Benchmark
    public static void sm2VerifyTest(){
        sm2Verify(msg, sig, Pri_Key.Pub_Key, nativeCode);
    }

    //sm2 verify method
    public static boolean sm2Verify(byte[] msg, byte[] signature, PubKey Pub_Key, GuomiJava.Jni_Guomi nativeCode)
    {
        boolean result = false;

        try{
            result = nativeCode.sm2Verify(Pub_Key.X, Pub_Key.Y, msg, msg.length, signature, signature.length);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    @Benchmark
    public static void sm3HashTest(){
        sm3Hash(msg, nativeCode);
    }

    //sm3 hash method
    public static byte[] sm3Hash(byte[] msg, GuomiJava.Jni_Guomi nativeCode)
    {
        byte[] result = null;

        try{
            result = nativeCode.sm3Hash(msg, msg.length);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    @Benchmark
    public static void sm4EncCBCTest(){
        sm4EncCBC(plain, sm4key, iv, nativeCode);
    }

    //sm4 encrytion method, iv is a randomly generated 16-byte array, msg must be at least 16 bytes long and key is 16 bytes long
    //For exemple, iv = randomBytes(16); sm4EncCBC (msg, key, iv, nativeCode
    public static byte[] sm4EncCBC(byte[] msg, byte[] key, byte[] iv, GuomiJava.Jni_Guomi nativeCode)
    {
        byte[] result = null;

        try{
            result = nativeCode.sm4CBCEnc(msg, msg.length, key, iv);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }


    @Benchmark
    public static void sm4DecCBCTest(){
        sm4DecCBC(cypher, sm4key, iv, nativeCode);
    }

    //sm4 decryption method, using the same key and iv in encryption to decrypt
    public static byte[] sm4DecCBC(byte[] cypher, byte[] key, byte[] iv, GuomiJava.Jni_Guomi nativeCOde)
    {
        byte[] result = null;

        try{
            result = nativeCOde.sm4CBCDec(cypher, cypher.length, key, iv);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    //Randomly generates an arbitrarily long byte-array
    public static byte[] randomBytes(int length)
    {
        byte[] result = new byte[length];
        Random r = new Random();

        for(int i = 0; i < length; i++)
        {
            result[i] = (byte)(r.nextInt(256));
        }

        return result;
    }
    public static void main(String[] args)
    {
//        GuomiJava.Jni_Guomi nativeCode = getNative();
//
//        /*       test sm2          */
//        PriKey Pri_Key = sm2GetKey(nativeCode);
//        byte[] msg = randomBytes(59);
//        byte[] sig = sm2Sign(msg, Pri_Key, nativeCode);
//        System.out.println(sm2Verify(msg, sig, Pri_Key.Pub_Key, nativeCode));
//        /*---------------------------------*/
//
//        /*       test sm3          */
//        System.out.println(Arrays.toString(sm3Hash(msg, nativeCode)));
//        /*---------------------------------*/
//
//        /*       test sm4          */
//        byte[] plain = new byte[32];
//        for(int i = 0; i < 32; i++)
//            plain[i] = (byte)i;
//        byte[] sm4key = "1234567890123456".getBytes();
//        byte[] iv = randomBytes(16);
//        byte[] cypher = sm4EncCBC(plain, sm4key, iv, nativeCode);
//        System.out.println(Arrays.toString(sm4DecCBC(cypher, sm4key, iv, nativeCode)));
        /*----------------------------------*/

        Options opt = new OptionsBuilder()
        .include(Guomi_Interface.class.getSimpleName())
        .warmupIterations(10)
        .measurementIterations(10)
        .forks(1)
        .build();
        try {
            new Runner(opt).run();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
