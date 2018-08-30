package GuomiJava;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static GuomiJava.Guomi_Interface.*;

@State(Scope.Thread)
public class Guomi_Interface_test {

    static PriKey[] Pri_Key = new PriKey[19];

    // 用于SM2签名的消息,也同样作用于SM3
    static byte[][] msg = new byte[19][];
    // SM2的签名结果
    static byte[][] sig = new byte[19][];

    //用于SM4的明文
    static byte[][] plain = new byte[16][];
    //SM4的密钥
    static byte[][] sm4key = new byte[16][];

    //SM4中CBC向量
    static byte[][] iv = new byte[16][];
    //SM4中加密的密文
    static byte[][] cypher = new byte[16][];

    @Param({"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19"})
    static int msg_size;

  //  @Param({"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"})
    static int plain_size;

    static int plain_length;

    @Setup(Level.Trial)
    public static void init(){
        nativeCode = getNative();
        for(int i=0;i<19;i++)
            Pri_Key[i] = sm2GetKey(nativeCode);

        int a[] = {2,4,7,11,17,26,39,58,86,128,191,285,426,637,953,1427,2138,3204,4803};

        for (int i=0;i<19;i++) {
            msg[i] = randomBytes(a[i]);
            sig[i] = sm2Sign(msg[i], Pri_Key[i], nativeCode);
        }

        // plain = new byte[plain_length];
        // for(int i=0;i<plain_length;i++)
        //    plain[i] = (byte)i;

        for(int i=0;i<10;i++){
            plain[i] = randomBytes((i+1)*1024);
            sm4key[i] = randomBytes(16);
            iv[i] = randomBytes(16);
            cypher[i] = sm4EncCBC(plain[i], sm4key[i], iv[i] ,nativeCode);
        }

        for(int i=10;i<16;i++){
            plain[i] = randomBytes(1<<(i-6));
            sm4key[i] = randomBytes(16);
            iv[i] = randomBytes(16);
            cypher[i] = sm4EncCBC(plain[i], sm4key[i], iv[i], nativeCode);
        }



    }
    @TearDown(Level.Trial)
    public static void finish(){}
 //   @Benchmark
    public static void sm2GetKeyTest(){
        GuomiJava.Jni_Guomi nativeCode = getNative();

        sm2GetKey(nativeCode);

    }

    //@Benchmark
    public static byte[] sm2SignTest(){

        return sm2Sign(msg[msg_size], Pri_Key[msg_size], nativeCode);

    }

    @Benchmark
    public static void sm2VerifyTest(){
        sm2Verify(msg[msg_size], sig[msg_size], Pri_Key[msg_size].Pub_Key, nativeCode);
    }

   // @Benchmark
    public static void sm3HashTest(){
        sm3Hash(msg[msg_size], nativeCode);
    }

  //  @Benchmark
    public static void sm4EncCBCTest(){
        sm4EncCBC(plain[msg_size], sm4key[msg_size], iv[msg_size], nativeCode);
    }

 //   @Benchmark
    public static void sm4DecCBCTest(){
        sm4DecCBC(cypher[msg_size], sm4key[msg_size], iv[msg_size], nativeCode);
    }

    public static void main(String[] args)
    {
        Options opt = new OptionsBuilder()
                .include(Guomi_Interface_test.class.getSimpleName())
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
