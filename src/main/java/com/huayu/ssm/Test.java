package com.huayu.ssm;


import org.junit.Before;
import redis.clients.jedis.Jedis;

public class Test {

    Jedis jedis;


    @Before
    public void con() {
        jedis = new Jedis("localhost");
        jedis.auth("huayu");
        System.out.println("服务"+jedis.ping());
    }

    @org.junit.Test
    public void keyTest() {

        //清空数据
        //System.out.println(jedis.flushDB());
        //System.out.println(jedis.echo("hello"));

        /*System.out.println(jedis.set("a","44434"));
        System.out.println(jedis.set("b","432523"));
        System.out.println(jedis.set("c","asf"));*/
        //System.out.println(jedis.exists("a"));


        // 如果数据库没有任何key，返回nil，否则返回数据库中一个随机的key。
        //String ran= jedis.randomKey();
        //System.out.println(ran);

        //设置多少秒过期
        //System.out.println(jedis.expire("b",20));

        // 移除key的过期时间
        //System.out.println(jedis.persist("b"));

        //还有多少毫秒过期
        //System.out.println(jedis.pttl("b"));


        // 获取key的类型, "string", "list", "set". "none" none表示key不存在
        //System.out.println("type: " + jedis.type("b"));


        //byte[] bytes = jedis.dump("b");
        //System.out.println(new String(bytes));

        // 将key重命名
        /*jedis.renamenx("b", "newb");
        System.out.println("key是否存在: " + jedis.exists("b"));// 判断是否存在
        System.out.println("keytest是否存在: " + jedis.exists("newb"));// 判断是否存在*/


        // 查询匹配的key
        // KEYS       * 匹配数据库中所有 key 。
        // KEYS       h?llo 匹配 hello ， hallo 和 hxllo 等。
        // KEYS       h*llo 匹配 hllo 和 heeeeello 等。
        // KEYS       h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo 。
        // 特殊符号用 \ 隔开。
        //Set<String> set = jedis.keys("*");
        //System.out.println(set);

        //删除
        jedis.del("c");
        System.out.println(jedis.exists("c"));
    }

    @org.junit.Test
    public void strTest() {

        /*jedis.set("hello", "hello");
        System.out.println(jedis.get("hello"));*/


        // 使用append 向字符串后面添加
        //jedis.append("hello", " world");
        //System.out.println(jedis.get("hello"));


        // 设置过期时间
        /*jedis.setex("hello2", 2, "world2");
        System.out.println(jedis.get("hello2"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println(jedis.get("hello2"));*/


// 一次添加多个key-value对
        /*jedis.mset("a", "1", "b", "2");*/
// 获取a和b的value
        /*List<String> valus = jedis.mget("a", "b");
        System.out.println(valus);*/


       /*jedis.del("a", "b");
        System.out.println(jedis.exists("a"));
        System.out.println(jedis.exists("b"));*/
    }

    @org.junit.Test
    public void listTest() {
        String key = "myList";
        //添加
        /*jedis.rpush(key,"aaaaaaaaa");
        jedis.rpush(key,"bbbbbbbbb");
        jedis.rpush(key,"nnnnnnnnn");
        jedis.rpush(key,"yyyyyyyyy");*/

        //长度
        //System.out.println(jedis.llen(key));

        //打印
        //System.out.println(jedis.lrange(key,0,-1));
        //System.out.println(jedis.lindex(key,0));

        //入队  从左和右
        //jedis.lpush(key,"55","66");
        //jedis.rpush(key,"55","66");

        //出队  从左和右
        //jedis.lpop(key);
        //jedis.rpop(key);

        //移除元素  三个参数 1.操作的list 2.正数或负数（从左往右 或 从右往左）删除的个数 3.要删除的元素
        //System.out.println(jedis.lrem(key,-1,"55"));

        //删除区间以外的元素
        System.out.println(jedis.ltrim(key, 1, 3));

    }

    @org.junit.Test
    public void setTest() {

        String key = "myset";
        String key2 = "myset2";

        //添加
        //jedis.sadd(key, "aaa", "bbb", "ccc");
        //jedis.sadd(key2, "bbb", "ccc", "ddd");

        //获取长度
        //System.out.println(jedis.scard(key));

        // 获得两个集合的交集，并存储在一个关键的结果集
        //jedis.sinterstore("destination", key, key2);
        //System.out.println(jedis.smembers("destination"));


        // 获得两个集合的并集，并存储在一个关键的结果集
        //jedis.sunionstore("destination", key, key2);
        //System.out.println(jedis.smembers("destination"));


        // key集合中，key2集合没有的元素，并存储在一个关键的结果集
        //jedis.sdiffstore("destination", key, key2);
        //System.out.println(jedis.smembers("destination"));

        //jedis.sdiffstore("destination", key2, key);
        //System.out.println(jedis.smembers("destination"));

        //判断是否存在此元素
        // System.out.println(jedis.sismember(key,"aaa"));

        //随机返回
        //System.out.println(jedis.srandmember(key));

        //移动元素
        //System.out.println(jedis.smove(key,key2,"aaa"));


        //随机删除集合中的一个元素 并返回删除的那个元素
        //System.out.println(jedis.spop(key));

        //删除多个
        System.out.println(jedis.srem(key2, "aaa", "ddd"));

    }

    @org.junit.Test
    public void testSortSet() {

        String key = "mysortset";

        /*Map<String, Double> scoreMembers = new HashMap<>();
        scoreMembers.put("aaa", 1001.0);
        scoreMembers.put("bbb", 1002.0);
        scoreMembers.put("ccc", 1003.0);*/


        // 添加数据
        jedis.zadd(key, 1004.0, "ddd");
        //jedis.zadd(key, scoreMembers);


        //长度
        //System.out.println(jedis.zcard(key));

        //遍历
        //Set<String> coll = jedis.zrange(key, 0, -1);
        //System.out.println(coll);


        // 返回的成员在指定范围内的逆序集合
        //coll = jedis.zrevrange(key, 0, -1);
        //System.out.println(coll);


        //返回权值
        //System.out.println(jedis.zscore(key, "bbb"));


        //删除
        //System.out.println(jedis.zrem(key, "aaa"));

        //总数
        System.out.println(jedis.zcount(key, 1, 2000));

    }

    @org.junit.Test
    public void hashTest() {
        String key = "myhash";
        //Map<String, String> hash = new HashMap<>();
        //hash.put("aaa", "11");
        //hash.put("bbb", "22");
        //hash.put("ccc", "33");

// 添加数据
        //jedis.hmset(key, hash);
        //jedis.hset(key, "ddd", "44");


// 获取hash的所有元素(key值)
        //System.out.println(jedis.hkeys(key));

// 获取hash中所有的key对应的value值
        //System.out.println(jedis.hvals(key));

// 获取hash里所有元素的数量
        //System.out.println(jedis.hlen(key));

// 获取hash中全部的域和值,以Map<String, String> 的形式返回
        //Map<String, String> elements = jedis.hgetAll(key);
        //System.out.println(elements);


// 判断给定key值是否存在于哈希集中
        //System.out.println(jedis.hexists(key, "bbb"));

// 获取hash里面指定字段对应的值
        //System.out.println(jedis.hmget(key, "aaa", "bbb"));

// 获取指定的值
        //System.out.println(jedis.hget(key, "aaa"));

// 删除指定的值
        //System.out.println(jedis.hdel(key, "aaa"));
        //System.out.println(jedis.hgetAll(key));

// 为key中的域 field 的值加上增量 increment
        System.out.println(jedis.hincrBy(key, "bbb", 100));
        System.out.println(jedis.hgetAll(key));


    }
}