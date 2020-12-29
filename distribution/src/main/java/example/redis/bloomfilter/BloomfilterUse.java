package example.redis.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BloomfilterUse
 * @Description: 布隆过滤器使用测试。
 * @Author: Uetec
 * @Date: 2020-12-29-10:00
 * @Version: 1.0
 **/
public class BloomfilterUse {

    private static int size = 1000000;

    //  fpp 容错率，通过容错率逆推到numBits与numHashFunctions的值
    //    long numBits 二进制向量长度
    //    int numHashFunctions  hash函数的个数
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),size,0.0001);

    public static void main(String[] args) {

        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

        List<Integer> list = new ArrayList<Integer>(10000);
        //故意取10000个不在布隆过滤器里的值，看看多少认为在布隆过滤器里
        for (int i = size + 10000; i < size + 20000; i++) {
            if(bloomFilter.mightContain(i)){
                list.add(i);
            }
        }

        System.out.println("误判的数量为："+list.size());

    }
}
