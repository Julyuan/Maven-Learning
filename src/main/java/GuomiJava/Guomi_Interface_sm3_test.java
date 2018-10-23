package GuomiJava;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static GuomiJava.Guomi_Interface.*;

@State(Scope.Thread)
public class Guomi_Interface_sm3_test {

    static PriKey[] Pri_Key = new PriKey[19];

    // 用于SM2签名的消息,也同样作用于SM3
    static byte[][] msg = new byte[19][];

    @Param({"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18"})
    static int msg_size;

    @Setup(Level.Trial)
    public static void init(){

        int a[] = {2,4,7,11,17,26,39,58,86,128,191,285,426,637,953,1427,2138,3204,4803};

        for (int i=0;i<19;i++) {
            msg[i] = cryptoRandomBytes(a[i]);
        }

    }
    @TearDown(Level.Trial)
    public static void finish(){}

    @Benchmark
    public static void sm3HashTest(){
        cryptoSM3Hash(msg[msg_size]);
    }


    public static void main(String[] args)
    {
        Options opt = new OptionsBuilder()
                .include(Guomi_Interface_sm3_test.class.getSimpleName())
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
