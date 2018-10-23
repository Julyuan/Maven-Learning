package GuomiJava;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static GuomiJava.Guomi_Interface.*;

@State(Scope.Thread)
public class Guomi_Interface_sm4_test {

    //用于SM4的明文
    static byte[][] plain = new byte[16][];
    //SM4的密钥
    static byte[][] sm4key = new byte[16][];

    //SM4中CBC向量
    static byte[][] iv = new byte[16][];
    //SM4中加密的密文
    static byte[][] cypher = new byte[16][];

    @Param({"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"})
    static int plain_size;

    static int plain_length;

    @Setup(Level.Trial)
    public static void init(){
        // nativeCode = getNative();

        for(int i=0;i<10;i++){
            plain[i] = cryptoRandomBytes((i+1)*1024);
            sm4key[i] = cryptoRandomBytes(16);
            iv[i] = cryptoRandomBytes(16);
            cypher[i] = cryptoSM4EncCBC(plain[i], sm4key[i], iv[i]);
        }

        for(int i=10;i<16;i++){
            plain[i] = cryptoRandomBytes(1<<(i-6));
            sm4key[i] = cryptoRandomBytes(16);
            iv[i] = cryptoRandomBytes(16);
            cypher[i] = cryptoSM4EncCBC(plain[i], sm4key[i], iv[i]);
        }



    }
    @TearDown(Level.Trial)
    public static void finish(){}

    @Benchmark
    public static void sm4EncCBCTest(){
        cryptoSM4EncCBC(plain[plain_size], sm4key[plain_size], iv[plain_size]);
    }

    @Benchmark
    public static void sm4DecCBCTest(){
        cryptoSM4DecCBC(cypher[plain_size], sm4key[plain_size], iv[plain_size]);
    }

    public static void main(String[] args)
    {
        Options opt = new OptionsBuilder()
                .include(Guomi_Interface_sm4_test.class.getSimpleName())
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
