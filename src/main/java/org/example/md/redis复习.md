### Redis 中常见的数据类型有哪些？（简单）

常见类型：

- String：字符串类型 （常用于做缓存、计数器（原子操作））。最大长度512M
- Hash：哈希类型（常用于存储对象，比如用户信息，商品信息）
- List：列表类型（常用于消息队列（简单任务调度），用户的操作的历史记录（快速访问））
- Set：集合类型（不可重复）（用于存储用户的兴趣标签 或则 某个页面多少个用户访问过）
- ZSet：有序集合类型（常用于排行榜，比如商品销量排行榜，用户积分排行榜）‘

其他类型：

- Bitmap：位图类型（常用于统计用户活跃状态，比如统计用户活跃天数）（占用空间少，只有0，1）
- HyperLogLog：基数统计类型（常用于统计用户数量，比如统计网站UV，PV） （概率性数据结构，估算）
- Geospatial：地理空间类型（常用于存储地理位置信息（经纬度），比如附近的人，附近商家（获取坐标））（计算距离）
- Stream：提供日志数据结构（存储时间序列数据或消息流）（消息队列）

### Redis 为什么这么快？（中等）

- 基于内存，内存读写速度比磁盘快很多，redis是存在内存中的（存储方式）
- 单线程，避免了多线程的上下文切换，避免了线程安全问题（线程模型）
- 高效的数据结构，有很多数据结构能在O(1)时间复杂度内完成数据写入
- IO多路复用，单线程处理多个客户端请求，通过事件驱动模型来处理请求（网络模型）
- 6.0后引入了多线程。减少网络I/O等待时间，利用CPU多核优势

#### select、poll、epoll的区别？

都是操作系统中多路复用I/O的机制：

- select：每次调用select，都会有个**固定长度数组**接收文件描述符，每次调用select都要重新构建和检查（1024大小）
- poll：select的升级版，没有固定长度数组，但是还需要遍历所有文件描述符，效率低
- epoll：select和poll的升级版，没有固定长度数组，提供边缘触发(ET)和水平触发（LT）模式，通过事件驱动模型来处理请求(
  只处理实际变化的数据)，效率高

### 为什么 Redis 设计为单线程？6.0 版本为何引入多线程？（中等）

- 单线程：减少了线程的上下文切换带来的性能开销
- redis的操作是基于内存的
- 在单线程下，使用I/O多路复用模型就可以提高redis的I/O利用率

为什么6.0需要引入多线程：因为随着数据变大，影响redis的是网络I/O。所以在网络I/O中引入多线程处理（键值读写是单线程的，多线程只针对网络请求，所以不会出现线程不安全情况）

- **I/O多路复用模型本质还是同步I/O**，用户需要等待数据拷贝到内存中才能获取，并发量高的话，这可能比较耗时（所以瓶颈在于网络I/O）

---

### Redis 中跳表的实现原理是什么？（困难）

**跳表**是一种**多链表**的数据结构，底层（level[0]层）保存了所有元素，每一层都是下一层的**子集**
插入：从最高层开始查找要插入的位置（先找到第一个比它大的元素），**随机**决定插入的层数，最后插入并更新指针
查找：从最高层开始查找要查找的位置，找到第一个比他大的元素后，再向下层找，依次类推，直到找到或遍历完，时间复杂度O(log n)
删除：从最高层开始查找要删除的位置，找到后删除并更新**各层指针**

**redis中跳表实现**

```c
typedef struct zskiplistNode {
    //Zset 对象的元素值
    sds ele;    // 主要用于存储数据
    //元素权重值
    double score;   // 分数
    //后退指针
    struct zskiplistNode *backward; // 指向当前节点的前一个节点
  
    //节点的level数组，保存每层上的前向指针和跨度
    struct zskiplistLevel {
        struct zskiplistNode *forward; // 指向当前节点下一层的节点
        unsigned long span; // 记录当前节点和下一个节点之间的距离（需要多少步才能走到下个节点）
    } level[];
} zskiplistNode;

```

**redis7.0插入元素 随机层数源码**

```c
以下源码来自 redis7.0

#define ZSKIPLIST_MAXLEVEL 32 /* Should be enough for 2^64 elements */
#define ZSKIPLIST_P 0.25   /* Skiplist P = 1/4 */

/* Returns a random level for the new skiplist node we are going to create.
 * The return value of this function is between 1 and ZSKIPLIST_MAXLEVEL
 * (both inclusive), with a powerlaw-alike distribution where higher
 * levels are less likely to be returned. */
int zslRandomLevel(void) {
    static const int threshold = ZSKIPLIST_P*RAND_MAX; // 0.25 * 最大值
    int level = 1; // 第一层开始，最底层是 0
    while (random() < threshold) // 随机
        level += 1;
    return (level<ZSKIPLIST_MAXLEVEL) ? level : ZSKIPLIST_MAXLEVEL;
}
```

### Redis 的 hash 是什么？ （中等）

hash 是一种**键值对**的数据结构，用于存储对象，比如用户信息，商品信息

基本操作：

```redis 
hset key field value
hget key field
hmset key field1 value1 field2 value2
hmget key field1 field2
hdel key field1 field2
-- increment 整数值，给 字段field + 个整数
hincrby key field increment
```

底层实现：

- redis6.0之前： ziplist(压缩列表) + hashtable(哈希表)
- redis7之后： listpack(紧凑列表) + hashtable(哈希表)，更换主要是解决ziplist的 级联更新 问题

上述两个列表存在两个值，一个为key默认512，值value默认64。例如：hash-max-ziplist-entries(512大小) ，hash-max-ziplist-value (
64大小)

- 当hash < key && hash < value，使用列表存储
- 当hash > key && hash > value，使用哈希表存储
- 默认值可以修改，且使用hashtable存储后不会退化

**hashtable代码：**

```c
// 
typedef struct dictht {
    //哈希表数组
    dictEntry **table; // 指向哈希表数组，数组中每个元素都是指向 dictEntry 的指针
    //哈希表大小
    unsigned long size;  
    //哈希表大小掩码，用于计算索引值
    unsigned long sizemask;  // sizemask = size - 1。用于计算索引，index = hash & sizemask
    //该哈希表已有的节点数量
    unsigned long used;
} dictht;

// 哈希数组结构体
typedef struct dictEntry {
    //键值对中的键
    void *key;
  
    //键值对中的值
    union {
        void *val;
        uint64_t u64;
        int64_t s64;
        double d;
    } v;
    //指向下一个哈希表节点，形成链表
    struct dictEntry *next;
} dictEntry;

```

#### 渐进式 rehash

**渐进式 rehash**：在rehash过程中，每次对hash表进行操作时，都会将hash表中的部分数据迁移到新的hash表中，而不是一次性迁移，避免阻塞redis
存在两个哈希表，可以分1表和2表，1表数据太大则向2表迁移数据（2表默认空表），扩容步骤

1. 创建一个比原hash表大2倍的hash表的2次方幂（结果是2次方幂且大于原表2倍）
2. rehashindx(索引值) 从 -1 变为 0 。每次增删改查都会将数据从1表迁到2表（插入直接插到2表）。然后rehashindx + 1
3. 重复步骤2，直到数据迁移完成，交换1，2表指针，设置rehashidx = -1

#### 扩容条件

**负载因子 = 哈希表已保存节点的数量 / 哈希表的大小**

1. 负载因子 > 1，没有RDB快照或AOF重写的持久化机制，就会rehash操作
2. 负载因子 > 5，无论有没有持久化机制，都会rehash操作
3. 负载因子 < 0.1，进行缩容（缩容为新表是原表的最近一个2次方幂） 1000 (old)-> 1024 (new)

### Redis ZSet 的实现原理是什么？（简单）

ZSet是一种有序集合，数据结构是 `跳表` + `哈希表`。常用于做排行榜，延迟队列，社交网络粉丝及关注数排序

1. 跳表：用于排序，快速查找
2. 哈希表：用于储存映射关系，快速查找

**元素较少则使用压缩列表ziplist,须同时满足两个条件：**

1. 元素个数 <= zset-max-ziplist-entries(默认128)
2. 元素成员们和分治长度 <= zset-max-ziplist-value(默认64)

---

### Redis 中如何保证缓存与数据库的数据一致性？（中等）

在对数据库进行增删改的时候改的时候，都要对缓存进行操作（可以是删除、或更新），但不推荐 更新缓存->更新数据库 , 更新数据库->
更新缓存， 删除缓存->更新数据库->后续查询回种到缓存。
以上三种都有可能导致数据不一致（当网络不稳定并发的时候）。
建议采用：**缓存双删策略**, 删除缓存->更新数据库->延迟删除缓存。
binlog异步更新缓存: 监听binlog变化（更新数据后），异步（可以是消息队列）更新缓存
实时一致性方案：先写MySQL再删缓存
最终一致性方案：Binlog + 消息队列 异步更新缓存

### Redis 中的缓存击穿、缓存穿透和缓存雪崩是什么？（中等）

**缓存击穿**：缓存中某个热点数据缓存失效，集中访问数据库，导致数据库奔溃
**缓存穿透**：缓存和数据库中都没有的数据（没有数据一直查数据），大量请求直接访问数据库，导致数据库奔溃
**缓存雪崩**：缓存中大量数据在同一时间失效，集中访问数据库，导致数据库奔溃

解决方法：

| 缓存击穿                            | 缓存穿透              | 缓存雪崩                      |
|---------------------------------|-------------------|---------------------------|
| 设置热点数据较长的过期时间（异步更新过期时间）         | 布隆过滤器             | 对缓存进行随机过期时间（在一定范围内随机）     |
| 使用互斥锁，确保同一时间只有一个请求可以去数据库查询并更新缓存 | 对空集也进行缓存（不存在数据缓存） | 双缓存策略（本地缓存+redis缓存等）      |
|                                 | 黑名单（封IP、账号等）      | 缓存预热（数据预热，可以定时任务先把数据更新到缓存） |
|                                 |                   | 服务熔断、构建redis集群            |


### Redis String 类型的底层实现是什么？（SDS）（中等）
String 底层是 sds （简单 动态 字符串）结构实现。并且结合了int，embstr，raw等不同编码进行优化

**c语言字符串缺陷:**
- 结尾为\0，表示字符串结束
- 获取长度需要遍历，时间复杂度为O(n)
- 字符串操作不高效，缓冲区溢出可能导致程序异常终止











