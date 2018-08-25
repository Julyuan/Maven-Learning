//public class
//Jni_Guomi {
//    public class PubKey
//    {
//        byte[] X;
//        byte[] Y;
//    }
//    public class PriKey
//    {
//        PubKey Pub_Key;
//        byte[] Key;
//    }
//    public native void sm2GenerateKey(byte[] Pub_X, byte[] Pub_Y, byte[] Pri_key);
//    public native byte[] sm2Sign(byte[] Pub_X, byte[] Pub_Y, byte[] Pri_key, byte[] msg, int msg_length);
//    public native boolean sm2Verify(byte[] Pub_X, byte[] Pub_Y, byte[] msg, int msg_length, byte[] sig, int sig_length);
//    public native byte[] sm3Hash(byte[] msg, int length);
//    public native byte[] sm4CBCEnc(byte[] msg, int msg_length, byte[] key, byte[] iv);
//    public native byte[] sm4CBCDec(byte[] cypher, int cypher_length, byte[] key, byte[] iv);
//    public static void main(String[] args) {
//        System.out.println(System.getProperty("java.library.path"));
//        System.loadLibrary("NativeCode");
//        Jni_Guomi nativeCode = new Jni_Guomi();
//
//        byte[] Pub_X = new byte[32], Pub_Y = new byte[32], Pri_Key = new byte[31], msg = null, sig;
//
//        msg = new byte[59];
//        for(int i = 0; i < 59; i ++)
//            msg[i] = (byte)i;
//        nativeCode.sm2GenerateKey(Pub_X, Pub_Y, Pri_Key);
//        sig = nativeCode.sm2Sign(Pub_X, Pub_Y, Pri_Key, msg, msg.length);
//        System.out.println(nativeCode.sm2Verify(Pub_X, Pub_Y, msg, msg.length, sig, sig.length));
//
//    }
//
//}
