package GuomiJava;

public class Jni_Guomi {

    public native void sm2GenerateKey(byte[] Pub_X, byte[] Pub_Y, byte[] Pri_key);
    public native byte[] sm2Sign(byte[] Pub_X, byte[] Pub_Y, byte[] Pri_key, byte[] msg, int msg_length);
    public native boolean sm2Verify(byte[] Pub_X, byte[] Pub_Y, byte[] msg, int msg_length, byte[] sig, int sig_length);
    public native byte[] sm3Hash(byte[] msg, int length);
    public native byte[] sm4CBCEnc(byte[] msg, int msg_length, byte[] key, byte[] iv);
    public native byte[] sm4CBCDec(byte[] cypher, int cypher_length, byte[] key, byte[] iv);

}
