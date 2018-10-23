package GuomiJava;

import java.util.Arrays;

import static GuomiJava.Guomi_Interface.*;

public class example {
    public static void test(){
        /*       test sm2          */
        System.out.println("以下是SM2算法的例子");
        PriKey Pri_Key = cryptoSM2GetKey();
        byte[] msg = cryptoRandomBytes(59);
        System.out.println("消息的内容是" + Arrays.toString(msg));
        byte[] sig = cryptoSM2Sign(msg, Pri_Key);
        System.out.println("签名的结果是"+Arrays.toString(sig));
        System.out.println("验证签名的结果是" + cryptoSM2Verify(msg, sig, Pri_Key.Pub_Key));
        /*---------------------------------*/

        /*       test sm3          */

        System.out.println("\n以下是SM3算法的例子");
        System.out.println("消息的内容是"+Arrays.toString(msg));
        System.out.println("哈希函数的结果是" + Arrays.toString(cryptoSM3Hash(msg)));
        /*---------------------------------*/

        /*       test sm4          */

        System.out.println("\n以下是SM4算法的例子");
        byte[] plain = new byte[32];
        for(int i = 0; i < 32; i++)
            plain[i] = (byte)i;
        System.out.println("明文的内容是" + Arrays.toString(plain));
        byte[] sm4key = "1234567890123456".getBytes();
        System.out.println("SM4密钥的内容是" + Arrays.toString(sm4key));
        byte[] iv = cryptoRandomBytes(16);
        System.out.println("随机字符串的内容是" + Arrays.toString(iv));
        byte[] cypher = cryptoSM4EncCBC(plain, sm4key, iv);
        System.out.println("加密的结果是"+ Arrays.toString(cypher));
        System.out.println("解密的结果是" + Arrays.toString(cryptoSM4DecCBC(cypher, sm4key, iv)));
    }

    public static void main(String[] args){
        test();
    }
}
