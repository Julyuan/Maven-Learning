package GuomiJava;

import java.util.Random;

public class Guomi_Interface {
    public static Jni_Guomi nativeCode;
    public static boolean isInit = false;
    // Definitions of public and private keys for sm2
    public static class PubKey
    {
        public byte[] X;
        public byte[] Y;
    }
    public static class PriKey
    {
        public PubKey Pub_Key;
        public byte[] Key;
    }

    // Get native methods
    public static void cryptoInit()
    {
        isInit = true;
        nativeCode = new Jni_Guomi();
        try {
            System.loadLibrary("NativeCode");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Randomly generate an sm2 key pair
    public static PriKey cryptoSM2GetKey()
    {
        if(isInit==false)
            cryptoInit();
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

    //sm2 sign method
    public static byte[] cryptoSM2Sign(byte[] msg, PriKey Pri_Key)
    {
        if(isInit==false)
            cryptoInit();
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

    //sm2 verify method
    public static boolean cryptoSM2Verify(byte[] msg, byte[] signature, PubKey Pub_Key)
    {
        if(isInit==false)
            cryptoInit();
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

    //sm3 hash method
    public static byte[] cryptoSM3Hash(byte[] msg)
    {
        if(isInit==false)
            cryptoInit();
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

    //sm4 encrytion method, iv is a randomly generated 16-byte array, msg must be at least 16 bytes long and key is 16 bytes long
    //For exemple, iv = randomBytes(16); sm4EncCBC (msg, key, iv)
    public static byte[] cryptoSM4EncCBC(byte[] msg, byte[] key, byte[] iv)
    {
        if(isInit==false)
            cryptoInit();
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

    //sm4 decryption method, using the same key and iv in encryption to decrypt
    public static byte[] cryptoSM4DecCBC(byte[] cypher, byte[] key, byte[] iv)
    {
        if(isInit==false)
            cryptoInit();
        byte[] result = null;

        try{
            result = nativeCode.sm4CBCDec(cypher, cypher.length, key, iv);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    //Randomly generates an arbitrarily long byte-array
    public static byte[] cryptoRandomBytes(int length)
    {
        byte[] result = new byte[length];
        Random r = new Random();

        for(int i = 0; i < length; i++)
        {
            result[i] = (byte)(r.nextInt(256));
        }

        return result;
    }
}
