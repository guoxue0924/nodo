package com.bluemobi.test.redis;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

public class Testtest {

    private static Jedis jedis = new Jedis("172.51.97.111", 2000);

    /**
     * redis支持的类型包括string(字符串)、list(链表)、set(集合)、zset(sorted set --有序集合)和hash(哈希类型)
     */
    
    static {
        // 清空所有key
        System.out.println("333333333333333333333333333333333*********************************");
        jedis.set("test", "ts");
        System.out.println(jedis.keys("*"));
        System.out.println("22222222222222222222222222*************************************");
        jedis.flushDB();

        // jedis.set("goods:1", "content", "{name:商品1,price:100}");
        // jedis.set("goods:2", "content", "{name:商品2,price:150}");
        // jedis.set("goods:3", "content", "{name:商品3,price:130}");
        // jedis.set("goods:4", "content", "{name:商品4,price:260}");
        //
        // jedis.set("price:1", "100");
        // jedis.set("price:2", "150");
        // jedis.set("price:3", "130");
        // jedis.set("price:4", "260");

        jedis.lpush("userlist", "33");
        jedis.lpush("userlist", "22");
        jedis.lpush("userlist", "55");
        jedis.lpush("userlist", "11");
        jedis.hset("user:66", "name", "66");
        jedis.hset("user:55", "name", "55");
        jedis.hset("user:33", "name", "33");
        jedis.hset("user:22", "name", "79");
        jedis.hset("user:11", "name", "24");
        jedis.hset("user:11", "add", "beijing");
        jedis.hset("user:22", "add", "shanghai");
        jedis.hset("user:33", "add", "guangzhou");
        jedis.hset("user:55", "add", "chongqing");
        jedis.hset("user:66", "add", "xi'an");
        
        
    }

    private void test() {
        SortingParams sortingParams = new SortingParams();
        /* 分页查询和排序 */
        // jedis.lpush("testList", "1");
        // jedis.lpush("testList", "2");
        // jedis.lpush("testList", "3");
        // jedis.lpush("testList", "4");
        // sortingParams.desc();
        // sortingParams.limit(0, 2);
        // List<String> result = jedis.sort("testList", sortingParams);

        /* 根据goodsList中的id查询商品名称和价格 */
        jedis.lpush("goodsList", "1");
        jedis.lpush("goodsList", "3");
        jedis.lpush("goodsList", "2");
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
        
        /* 模拟索引用来搜索id */
        System.out.println("3333333333333333333333*********************************************************");
        jedis.hset("man.1", "name", "zhangsan");
        jedis.hset("man.2", "name", "lisi");
        jedis.hset("man.3", "name", "wangwu");
        jedis.hset("man.1", "age", "20");
        jedis.hset("man.2", "age", "25");
        jedis.hset("man.3", "age", "20");
        jedis.hset("man.1", "city", "bj");
        jedis.hset("man.2", "city", "sh");
        jedis.hset("man.3", "city", "sz");
        
        jedis.sadd("man.name.zhangsan", "2");
        jedis.sadd("man.age.20", "1");
        jedis.sadd("man.age.20", "3");
        jedis.sadd("man.city.sh", "2");
        
        System.out.println(jedis.smembers("man.age.20"));
        System.out.println("333333333333333333333333333*********************************************************");
        
        // 这可能是两个能搜索的方法
//        jedis.brpoplpush(source, destination, timeout);
//        jedis.watch(keys);
        // 遗留的两个问题
//        1、lpush和rpush区别；
//        2、jedis.mget；
        
        /* 设置过期时间 */
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        jedis.set("foo", "fooooooooooooooooooo");
        System.out.println(jedis.get("foo"));
        jedis.setex("foo", 5, jedis.get("foo"));
        System.out.println(jedis.get("foo"));
        try {
            for (int i = 0; i < 1; i++) {
                System.out.println(jedis.ttl("foo"));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(jedis.get("foo"));
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        
        
        sortingParams.desc();
        sortingParams.get("goods:*->name");
        sortingParams.get("goods:*->price");
        sortingParams.get("goods:*->content");
        // by是排序条件，根据价格排序
        sortingParams.by("goods:*->price");
        // 输出goodsList的值
        sortingParams.get("#");
        Set<String> list = jedis.zrange("goods:*->price", 120, 200);
        
        jedis.lpush("testtesttest", "100");
        jedis.lpush("testtesttest", "130");
        jedis.lpush("testtesttest", "260");
        
        List<String> list2 = jedis.lrange("testtesttest", 0, 200);
        for (int i = 0; i < list2.size(); i++) {
            System.out.println(list2.get(i));
        }
        System.out.println("----------------------------------------------------");
        System.out.println(list);
        System.out.println("===================================");
        List<String> result = jedis.sort("goodsList", sortingParams);
        
        
        
        /* 和上面一样，根据条件查询的 */
//        jedis.sadd("tom:friend:list", "123"); // tom的好友列表
//        jedis.sadd("tom:friend:list", "456");
//        jedis.sadd("tom:friend:list", "789");
//        jedis.sadd("tom:friend:list", "101");
//
//        jedis.set("score:uid:123", "1000"); // 好友对应的成绩
//        jedis.set("score:uid:456", "6000");
//        jedis.set("score:uid:789", "100");
//        jedis.set("score:uid:101", "5999");
//
//        jedis.set("uid:123", "{'uid':123,'name':'lucy'}"); // 好友的详细信息
//        jedis.set("uid:456", "{'uid':456,'name':'jack'}");
//        jedis.set("uid:789", "{'uid':789,'name':'jay'}");
//        jedis.set("uid:101", "{'uid':101,'name':'jolin'}");
        
//        sortingParams.desc();
        // sortingParameters.limit(0, 2);
        // 注意GET操作是有序的，GET user_name_* GET user_password_*
        // 和 GET user_password_* GET user_name_*返回的结果位置不同
//        sortingParams.get("#");// GET 还有一个特殊的规则—— "GET #"，用于获取被排序对象(我们这里的例子是 user_id )的当前元素。
//        sortingParams.get("uid:*");
//        sortingParams.get("score:uid:*");
//        sortingParams.by("score:uid:*");
        // 对应的redis 命令是./redis-cli sort tom:friend:list by score:uid:* get # get
        // uid:* get score:uid:*
//        List<String> result = jedis.sort("tom:friend:list", sortingParams);
        
//        sortingParams.get("#");// GET 还有一个特殊的规则—— "GET #"，用于获取被排序对象(我们这里的例子是 user_id )的当前元素。
//        sortingParams.by("score:uid:*");
//        jedis.sort("tom:friend:list", sortingParams, "tom:friend:list");
//        List<String> result = jedis.lrange("tom:friend:list", 0, -1);
//        for (String item : result) {
//            System.out.println("item..." + item);
//        }
        
        /* 查询出tag1 */
//        jedis.sadd("zhongsou:news:1000:tags", "1");
//        jedis.sadd("zhongsou:news:1000:tags", "2");
//        jedis.sadd("zhongsou:news:1000:tags", "5");
//        jedis.sadd("zhongsou:news:1000:tags", "77");
//        jedis.sadd("zhongsou:news:2000:tags", "1");
//        jedis.sadd("zhongsou:news:2000:tags", "2");
//        jedis.sadd("zhongsou:news:2000:tags", "5");
//        jedis.sadd("zhongsou:news:2000:tags", "77");
//        jedis.sadd("zhongsou:news:3000:tags", "2");
//        jedis.sadd("zhongsou:news:4000:tags", "77");
//        jedis.sadd("zhongsou:news:5000:tags", "1");
//        jedis.sadd("zhongsou:news:6000:tags", "5");
//
//        jedis.sadd("zhongsou:tag:1:objects", 1000 + "");
//        jedis.sadd("zhongsou:tag:2:objects", 1000 + "");
//        jedis.sadd("zhongsou:tag:5:objects", 1000 + "");
//        jedis.sadd("zhongsou:tag:77:objects", 1000 + "");
//
//        jedis.sadd("zhongsou:tag:1:objects", 2000 + "");
//        jedis.sadd("zhongsou:tag:2:objects", 2000 + "");
//        jedis.sadd("zhongsou:tag:5:objects", 2000 + "");
//        jedis.sadd("zhongsou:tag:77:objects", 2000 + "");
//
//        Set<String> sets = jedis.sinter("zhongsou:tag:1:objects", "zhongsou:tag:2:objects", "zhongsou:tag:5:objects", "zhongsou:tag:77:objects");
//        System.out.println(sets);
        

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
        
        
//        jedis.zadd("zhongsou:hackers", 1940, "Alan Kay");
//        jedis.zadd("zhongsou:hackers", 1953, "Richard Stallman");
//        jedis.zadd("zhongsou:hackers", 1943, "Jay");
//        jedis.zadd("zhongsou:hackers", 1920, "Jellon");
//        jedis.zadd("zhongsou:hackers", 1965, "Yukihiro Matsumoto");
//        jedis.zadd("zhongsou:hackers", 1916, "Claude Shannon");
//        jedis.zadd("zhongsou:hackers", 1969, "Linus Torvalds");
//        jedis.zadd("zhongsou:hackers", 1912, "Alan Turing");
//        
//        Set<String> hackers = jedis.zrange("zhongsou:hackers", 0, -1);
//        System.out.println(hackers);
//
//        Set<String> hackers2 = jedis.zrevrange("zhongsou:hackers", 0, -1);
//        System.out.println(hackers2);
//
//        // 区间操作,我们请求Redis返回score介于负无穷到1920年之间的元素（两个极值也包含了）。
//        Set<String> hackers3 = jedis.zrangeByScore("zhongsou:hackers", "-inf", "1920");
//        System.out.println(hackers3);
//
//        // ZREMRANGEBYSCORE 这个名字虽然不算好，但他却非常有用，还会返回已删除的元素数量。
//        long num = jedis.zremrangeByScore("zhongsou:hackers", "-inf", "1920");
//        System.out.println(num);
        
        jedis.zadd("goods_price", 100, "1");
        jedis.zadd("goods_price", 150, "2");
        jedis.zadd("goods_price", 130, "3");
        jedis.zadd("goods_price", 260, "4");
        
        Set<String> hackers3 = jedis.zrangeByScore("goods_price", 100, 200);
        System.out.println(hackers3);
    }

    public void testSort1() {
        // 排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较
        Jedis redis = jedis;
        // 一般SORT用法 最简单的SORT使用方法是SORT key。
        redis.lpush("mylist", "1");
        redis.lpush("mylist", "4");
        redis.lpush("mylist", "6");
        redis.lpush("mylist", "3");
        redis.lpush("mylist", "0");
        // List<String> list = redis.sort("sort");// 默认是升序
        SortingParams sortingParameters = new SortingParams();
        sortingParameters.desc();
        // sortingParameters.alpha();//当数据集中保存的是字符串值时，你可以用 ALPHA
        // 修饰符(modifier)进行排序。
        sortingParameters.limit(0, 2);// 可用于分页查询
        List<String> list = redis.sort("mylist", sortingParameters);// 默认是升序
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] args) {
        Testtest test = new Testtest();
       
        jedis.flushDB();
        
        
        
    }

}
