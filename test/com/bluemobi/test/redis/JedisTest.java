package com.bluemobi.test.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

import com.appcore.util.JsonUtil;

public class JedisTest {
    
    //private static Jedis jedis = new Jedis("172.51.97.111", 2000);
    
    private static Jedis jedis = new Jedis("192.168.88.129", 2000);
    
    /**
     * @author haojian
     * @date 2016-1-27 下午7:56:33
     * @param args
     * @return void
     */

    public static void main(String[] args) {
        
        //JedisTest.clear(jedis);
        //JedisTest.testSort();
        long b = System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            jedis.hset("hao2", "price22", i+"{asasdfasdfasdfl;kjoiujzlkxcvm,sndf,j;zcxvlsndf,mansdkfj;ask;djfnsdk.fnaskdjfasdjfnsfasdfasdfl;kjoiujzlkxcvm,sndf,j;zcxvlsndf,mansdkfj;ask;djfnsdk.fnaskdjfasdjfnsfasdfasdfl;kjoiujzlkxcvm,sndf,j;zcxvlsndf,mansdkfj;ask;djfnsdk.fnaskdjfasdjfnsdkjfnsdkfjnsadkfjnsfsdfsadf092,sdfosdfsldfsdfs.adf,sohoqiwueroihf;skdr[pouwerifjsdfkmaslkdjfhweorpikasdkfasld',s.d,faskljdfwiouefpvnxkvjbaoirfp'sadkfnvxkzjhsljflsakdnfkjhkjhalskjfd}");
            if(i%1000==0){
                System.out.println(i);
            }
        }
        long e = System.currentTimeMillis();
        
        System.out.println("执行耗时"+(e-b)/1000d+"秒");
        
        jedis.close();
        
    }
    
    
    public static void testSort() {
        
        SortingParams sortingParams = new SortingParams();
        jedis.del("goodsId");
        /* 根据goodsList中的id查询商品名称和价格 */
        jedis.lpush("goodsId", "1");
        jedis.lpush("goodsId", "2");
        jedis.lpush("goodsId", "3");
        jedis.lpush("goodsId", "4");
        
 /*
        jedis.zadd("goods:1", 100, "{name:商品1,price:100}");
        jedis.zadd("goods:2", 150, "{name:商品2,price:150}");
        jedis.zadd("goods:3", 130, "{name:商品3,price:130}");
        jedis.zadd("goods:4", 260, "{name:商品4,price:260}");
        */
        jedis.hset("goods:1", "price", "100");
        jedis.hset("goods:2", "price", "150");
        jedis.hset("goods:3", "price", "130");
        jedis.hset("goods:4", "price", "260");
        jedis.hset("goods:1", "name", "商品1");
        jedis.hset("goods:2", "name", "商品2");
        jedis.hset("goods:3", "name", "商品3");
        jedis.hset("goods:4", "name", "商品4");
        jedis.hset("goods:1", "content", "{name:商品1,price:100}");
        jedis.hset("goods:2", "content", "{name:商品2,price:150}");
        jedis.hset("goods:3", "content", "{name:商品3,price:130}");
        jedis.hset("goods:4", "content", "{name:商品4,price:260}");
        
       
        sortingParams.asc();
        //sortingParams.get("goods:*->name");
        //sortingParams.get("goods:*->price");
        sortingParams.get("goods:*->content");
        // by是排序条件，根据价格排序
        sortingParams.by("goods:*->price");
        // 输出goodsList的值
        //sortingParams.get("#");
        
        //Set<String> result = jedis.zrange("goods:*->price", 120, 200);  //从有序集合中取值
        
        List<String> result = jedis.sort("goodsId", sortingParams);
        
        for(String s:result){
            System.out.println(s);
        }
        
        jedis.close();

    }

    

    /**
     * 清理缓存中所有的数据
     * @author haojian
     * @date 2016-1-27 下午10:51:48 
     * @param jedis
     * @return void
     */
    public static void clear(Jedis jedis){
        jedis.flushDB();
    }

    
    /**
     * 将一组对象存到redis的一个map中
     * @author haojian
     * @date 2016-1-27 下午8:15:46 
     * @param objectMapName redis中的map的名字
     * @param objectMap  key：对象唯一id， value：对象
     * @return String
     */
    public static <K, V> String putObjectsToMap(Jedis jedis, String objectMapName, Map<K, V> objectMap) {

        Map<String,String> map = new HashMap<String,String>();
        
        for(K objectKey : objectMap.keySet()){
            V object = objectMap.get(objectKey);
            String key = objectKey.toString();
            String value = JsonUtil.getJsonString(object);
            map.put(key, value);
        }
        
        String str = jedis.hmset(objectMapName, map);
        
        return str;
        
    }
    
    /**
     *  将一个对象存到redis的一个map中
     * @author haojian
     * @date 2016-1-27 下午9:10:31 
     * @param jedis
     * @param objectMapName
     * @param k
     * @param v
     * @return
     * @return Long
     */
    public static <K, V> Long putObjectToMap(Jedis jedis, String objectMapName, K k, V v) {
        
        String json = JsonUtil.getJsonString(v);
        Long l = jedis.hset(objectMapName, k.toString(), json);
        
        return l;
        
    }
    
    /**
     * 根据一组id列表从redis的map中获取一组对象
     * @author haojian
     * @date 2016-1-27 下午8:24:02 
     * @param jedis
     * @param objectMapName redis中的map的名字
     * @param idList 对象唯一id列表
     * @param clazz  对象对应的类
     * @return
     * @return List<T>
     */
    public static <T> List<T> getObjectsFromMapByIds(Jedis jedis, String objectMapName, List<String> idList, Class<T> clazz){
        
        String[] ss = new String[idList.size()];
        List<String> jsonList = jedis.hmget(objectMapName, idList.toArray(ss));
        List<T> objectList = new ArrayList<T>();
        for(String json : jsonList){
            T object = JsonUtil.getObject(json, clazz);
            objectList.add(object);
        }
        return objectList;
        
    }
    
    
    /**
     * 根据一个id列表从redis的map中获取一个对象
     * @author haojian
     * @date 2016-1-27 下午9:04:10 
     * @param jedis
     * @param objectMapName redis中的map的名字
     * @param id 对象唯一id
     * @param clazz 对象对应的类
     * @return
     * @return T
     */
    public static <T> T getObjectFromMapById(Jedis jedis, String objectMapName, String id, Class<T> clazz){
        
        String json = jedis.hget(objectMapName, id);
        T object = JsonUtil.getObject(json, clazz);
        
        return object;
        
    }

    
    /**
     * 将主id和明细id关联起来
     * 
     * @author haojian
     * @date 2016-1-27 下午8:00:22
     * @param jedis
     * @param mainIdName 主id名称，作为存放子id列表的 key的前缀
     * @param mainId   主id
     * @param detailIdList 子id列表
     * @return Long
     */
    public static <T> Long putMainIdAndDetailIds(Jedis jedis, String mainIdName, Object mainId, List<T> detailIdList) {

        String[] ss = new String[detailIdList.size()];
        Long l = jedis.rpush(mainIdName + mainId, detailIdList.toArray(ss) );
        
        return l;
    }

    /**
     * 通过主id获取子id列表
     * 
     * @author haojian
     * @date 2016-1-27 下午8:02:29
     * @param jedis
     * @param mainIdName 主id名称，作为存放子id列表的 key的前缀
     * @param mainId 主id
     * @return
     * @return List<String>
     */
    public static List<String> getDetailIdsByMainId(Jedis jedis, String mainIdName, Object mainId) {

        List<String> detailIdList = jedis.lrange(mainIdName + mainId, 0, -1);

        return detailIdList;

    }




}
