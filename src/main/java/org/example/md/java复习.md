### 单例模式有哪几种实现？如何保证线程安全？

**饿汉式：**线程安全的，在类加载时初始化

```java
public class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}
```

**懒汉式：** 线程不安全，在第一次调用时初始化

```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        // 此处线程不安全
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
} 
```

**双重检查：** 优化懒汉式，线程安全

```java
public class Singleton {
    // volatile 保证多线程环境下的可见性，防止指令重排
    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        // 第一次避免不必要的同步
        if (instance == null) {
            synchronized (Singleton.class) {
                // 第二次避免创建多个实例
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

**静态内部类：** 线程安全，利用静态内部类实现懒加载

```java
public class Singleton {
    private Singleton() {
    }

    // 静态内部类
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
```

**枚举：** 通过枚举实现单例，防止反射和序列化攻击

```java
public enum Singleton {
    INSTANCE;

    public void doSomething() {
        // 业务逻辑
    }
}

Singleton singleton = Singleton.INSTANCE;
singleton.

doSomething();
```

### 什么是策略模式？一般用在什么场景？

**策略模式：** （行为设计模式）定义一系列算法，将每个算法封装起来，并使它们可以相互替换。策略模式让算法的变化独立于使用算法的客户端。
如果业务中存在大量的 `if else` 或者 `switch` 语句，可以考虑使用策略模式进行优化。主要目的是为了**解耦**多个策略（方便调用方灵活调用）。

常用于：

- 多种策略可以替换 （例如选择某个排序算法）
- 与上下文独立（不需要关心策略具体实现细节）如果有新的策略方便拓展
- 出现大量判断语句可以考虑策略模式

**代码例子(排序算法)**  
**接口**

```java
public interface ISort {

    // 排序
    void doSort(int[] arr);
}
```

**具体的排序策略**

```java
/**
 * 冒泡
 */
public class BubbleSort implements ISort {

    @Override
    public void doSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }
}

/**
 * 快排
 */
public class QuickSort implements ISort {

    @Override
    public void doSort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIdx = partition(arr, left, right);
            sort(arr, 0, pivotIdx - 1);
            sort(arr, pivotIdx + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int idx = left + 1;
        for (int i = idx; i <= right; i++) {
            if (arr[left] > arr[i]) {
                swap(arr, i, idx++);
            }
        }
        swap(arr, left, idx - 1);
        return idx - 1;
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}

/**
 * 选择
 */
public class SelectionSort implements ISort {
    @Override
    public void doSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            int minVal = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[minVal] > arr[j]) {
                    minVal = j;
                }
            }
            if (minVal != i) {
                int tmp = arr[i];
                arr[i] = arr[minVal];
                arr[minVal] = tmp;
            }
        }
    }
}
```

**上下文**

```java
public class Context {

    private ISort iSort;

    public void setSort(ISort iSort) {
        this.iSort = iSort;
    }

    public void doSort(int[] arr) {
        if (iSort != null) {
            iSort.doSort(arr);
        } else {
            //默认使用冒泡排序
            BubbleSort bubbleSort = new BubbleSort();
            bubbleSort.doSort(arr);
        }
    }
}
```

**测试**

```java
public class SortTest {

    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 9, 3};
        //创建上下文
        Context context = new Context();
        //默认排序
        context.doSort(arr);

        //使用快排
        QuickSort quickSort = new QuickSort();
        context.setSort(quickSort);
        context.doSort(arr);
        //选择
        SelectionSort selectionSort = new SelectionSort();
        context.setSort(selectionSort);
        context.doSort(arr);
    }
}
```

### 什么是模板方法模式？一般用在什么场景？

**模板方法模式：** （行为设计模式）定义一个操作中的算法的骨架((类似于插槽))，而将一些步骤延迟到子类中。
模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。

**特点：**

- **父类定义骨架，子类实现细节**（算法骨架）
- 复用了代码，只需要不同点抽出来具体实现
- 骨架对拓展开发，对修改关闭 （**开闭原则**）

**一般场景：**（数据处理流程，Web请求处理）

- 定义算法骨架 ：根据需求写出业务步骤
- 代码复用：抽出差异代码，复用公共逻辑
- 拓展开发：子类实现差异逻辑

**代码：**     
制作奶茶例子：冲奶粉 -> 选配料(可选择) -> 冲泡搅拌 ->  可以喝奶茶了

奶茶模板代码（抽象类）

```java
/**
 * 抽象类 奶茶
 */
public abstract class MilkTea {

    public void makeMilkTea() {
        //可以有其他公共逻辑
        // 按照流程步骤
        addPowder();
        addIngredients();
        mix();
        pour();
    }

    //  充粉
    public void addPowder() {
        System.out.println("奶茶第一步：充粉");
    }

    //  选配料（根据口味选择配料）
    public abstract void addIngredients();

    //  搅拌
    public void mix() {
        System.out.println("搅拌");
    }

    //  倒杯
    public void pour() {
        System.out.println("可以喝奶茶了");
    }
}
```

**具体实现(选择特定口味)**

```java
// 添加红豆
public class RedBeanMilkTea extends MilkTea {

    @Override
    public void addIngredients() {
        System.out.println("添加红豆");
    }

}

public class VanillaMilkTea extends MilkTea {
    @Override
    public void addIngredients() {
        System.out.println("添加香草");
    }
}
```

**测试代码**

```java
public class Main {
    public static void main(String[] args) {
        // 制作红豆奶茶
        MilkTea redBeanMilkTea = new RedBeanMilkTea();
        redBeanMilkTea.makeMilkTea();

        // 制作香草奶茶
        MilkTea vanillaMilkTea = new VanillaMilkTea();
        vanillaMilkTea.makeMilkTea();
    }
}
```

---

### 谈谈你了解的最常见的几种设计模式，说说他们的应用场景

- 单例模式：确保某个类全局只有**一个实例**，并提供一个全局访问点。（常用于 配置管理器、全局缓存、数据库连接池）
- 工厂模式：定义一个创建对象的接口，让子类决定实例化哪一个类。工厂方法使一个类的实例化延迟到其子类。（常用于
  日志记录器、数据库访问、XML解析器）
- 策略模式：定义一系列算法，并将每一个算法封装起来，使它们可以互相替换。（常用于 优化算法、算法可扩展、算法可替换）
- 模板方法模式：定义一个操作中的算法的骨架(类似于插槽)，而将一些步骤延迟到子类中
- 建造者模式：快速构建复杂的类 (当一个类的构造函数参数有多个，而且这些参数有些是可选时，可以按需构造)

**一般情况下会多个设计模式一起使用：**例如：支付

1. 因为支付方式有多种：微信、支付宝、银行卡等等，所以可以使用`策略模式`，根据不同的支付方式，选择不同的支付策略
2. 每个支付策略中又有一些重复的逻辑（例如：写日志，数据校验等等），可以使用`模板方法模式`抽出核心逻辑
3. `使用工厂模式`，根据不同的支付方式，选择不同的工厂，工厂中创建对应的支付策略

### 你认为好的代码应该是怎样的？

1. **可读性**：代码可读性高，逻辑清晰，容易理解，方便维护
2. **可扩展性**：代码结构清晰，模块化，容易扩展新的功能
3. **高内聚低耦合**：内部模块职责单一，不同模块依赖关系松散
4. **代码规范**：代码风格统一，命名规范，注释清晰
5. **错误处理**：提供清晰的错误信息，并有良好的日志习惯，便于定位问题
6. **性能优化**：代码性能优化，避免不必要的计算和资源浪费
7. **测试**：代码容易测试，可以确保代码的正确性和稳定性

### 工厂模式和抽象工厂模式有什么区别？

**工厂模式：** 定义一个创建对象的接口，让子类决定实例化哪一个类。工厂方法使一个类的实例化延迟到其子类。
**抽象工厂模式：** 提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类。

`工厂方法模式`是一个超级工厂，里面什么的生产，超级工厂就是抽象类（里面有需要生产的产品（产品实现方法））。你需要什么产品就需要造什么工厂（工厂的具体实现）

`抽象工厂模式`是好比你需要造一辆车，需要制作车的车窗，车轮，发动机等等部件（这就是抽象产品），这些产品怎么制作的有自己方式（具体产品的实现，也就是实现产品接口），
工厂就是负责造这些零件(抽象工厂（也就是抽象工厂里面有这个实现方法）)
，然后每个车都有自己的品牌，就好像宝马，奔驰，宾利等等，每个品牌都有自己的工厂（这就是具体工厂（实现了抽象工厂的接口））

**抽象工厂模式代码例子**
`抽象产品`

```java
/**
 * 车胎
 */
public interface Tyre {
    public void makeTyre();
}

/**
 * 车窗
 */
public interface CarWindow {
    public void makeWindow();
}
```

`具体产品实现`

```java
/**
 * 车胎具体实现
 */
public class ConcreteTyre implements Tyre {
    @Override
    public void makeTyre() {
        System.out.println("制作轮胎");
    }
}

/**
 * 车窗具体实现
 */
public class ConcreteCarWindow implements CarWindow {
    @Override
    public void makeWindow() {
        System.out.println("制作车窗");
    }
}
```

`抽象工厂`

```java
/**
 * 造车工厂
 * 只关注需要什么，不关注生产细节
 */
public interface CarFactory {

    // 造车窗
    CarWindow createWindow();

    // 造轮胎
    Tyre createTyre();
}
```

`每个车品牌的工厂`，每个厂有自己的特色

```java
/**
 * 宾利工厂
 */
public class BentleyFactory implements CarFactory {
    @Override
    public CarWindow createWindow() {
        System.out.println("贴个宾利标");
        return new ConcreteCarWindow();
    }

    @Override
    public Tyre createTyre() {
        System.out.println("贴个宾利标");
        return new ConcreteTyre();
    }
}

/**
 * 宝马工厂
 */
public class BMWFactory implements CarFactory {
    @Override
    public CarWindow createWindow() {
        return new ConcreteCarWindow();
    }

    @Override
    public Tyre createTyre() {
        return new ConcreteTyre();
    }
}
```

`测试类`

```java
public class Main {
    public static void main(String[] args) {
        // 创建宾利工厂
        BentleyFactory bentleyFactory = new BentleyFactory();
        // 制作轮胎
        Tyre tyre = bentleyFactory.createTyre();
        tyre.makeTyre();
        // 制作车窗
        CarWindow window = bentleyFactory.createWindow();
        window.makeWindow();

        // 创建宝马工厂
        BMWFactory bmwFactory = new BMWFactory();
        Tyre tyre1 = bmwFactory.createTyre();
        tyre1.makeTyre();
        CarWindow window1 = bmwFactory.createWindow();
        window1.makeWindow();
    }
}
```

**总结：** 工厂方法是一个单独种类的工厂，例如我是水果工厂，我需要什么水果，就创建什么水果工厂（例如苹果工厂，梨子工厂等等）。
而抽象工厂是一个生产族群的，例如 梨子手机工厂就可以生产 梨子手机电池、屏幕、主板等等(
如果我添加一个新零件，例如扩音器，需要大幅修改。如果添加个菠萝手机则方便很多)

---

### 接口和抽象类有什么区别？

|      |                       接口                       |                   抽象类                    |
|:----:|:----------------------------------------------:|:----------------------------------------:|
| 设计方法 |               至上而下（知道某个行为来定义接口）                |     至下而上 （写了很多共性代码，发现可以复用，抽出封装成抽象类）      |
|  继承  |                      多个实现                      |                   单继承                    |
| 成员变量 |            没有构造方法、成员变量默认为常量（final）             | 可以有构造方法，成员变量可以有不同的访问修饰符（private等）,可以不是常量 |
|  方法  | 接口中方法默认为public、abstract(java8后有default方法和静态方法) |            可以有abstract方法和具体实现            |

### JDK 动态代理和 CGLIB 动态代理有什么区别？

**JDK 动态代理：** JDK 动态代理是 Java 语言内置的，不需要额外的依赖。它通过**反射机制**实现，只能代理实现了接口的类。
**CGLIB 动态代理：** CGLIB 动态代理是第三方库，需要额外的依赖。它通过**字节码生成技术**实现，可以代理没有实现接口的类。

```java
public interface GoService {

    void go();
}

public class GoServiceImpl implements GoService {
    @Override
    public void go() {
        System.out.println("GoService go");
    }
}

/**
 * 代理类
 */
public class ServiceInvocationHandler implements InvocationHandler {
    // 代理对象
    private final Object target;

    public ServiceInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke");
        Object result = method.invoke(target, args);
        System.out.println("after invoke");
        return result;
    }
}

/**
 * 代理测试类
 */
public class test {
    public static void main(String[] args) {
        GoServiceImpl target = new GoServiceImpl();
        GoService goService = (GoService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new ServiceInvocationHandler(target));
        goService.go();
    }
}
```

### 你使用过 Java 的反射机制吗？如何应用反射？

**反射机制：** 反射机制是 Java 语言的一种特性，它允许程序在运行时获取类的信息，例如类的成员变量、方法、构造函数等，并且可以在*
*运行时**动态地创建对象、调用方法、访问成员变量等。

**反射举例：** Spring 使用反射机制来**读取**和**解析**配置文件

**反射优化：** 反射会消耗大量性能，业务中尽量少使用，可以**缓存**第一次反射结果

**反射的主要功能**

- 创建对象：class.newInstance()(过时) 或 Constructor.newInstance()
- 获取属性：Field
- 获取方法：Method
- 获取构造函数：Constructor

```java
// 获取类
Class<?> clazz = Class.forName("com.mianshiya.MyClass");
// 或者
Class<?> clazz = MyClass.class;
// 或者
Class<?> clazz = obj.getClass();

// 创建对象
Object obj = clazz.newInstance(); // 已过时
Constructor<?> constructor = clazz.getConstructor();
Object obj = constructor.newInstance();

// 调用方法 
Method method = clazz.getMethod("myMethod", String.class);
Object result = method.invoke(obj, "param");

// 获取属性
Field field = clazz.getField("myField");
field.

setAccessible(true); // 允许访问 private 字段

Object value = field.get(obj);
field.

set(obj, newValue);
```

---

### 说说 Java 中 HashMap 的原理？

HashMap 是 Java 中常用的集合类，它基于哈希表实现，提供了快速的查找、插入和删除操作。
HashMap 的原理是使用哈希函数将键值对映射到哈希表中，然后通过哈希表中的索引来快速定位键值对。

java8 之前 是 **数组** + **链表**  
Java 8 之后 是 **数组** + **链表** + **红黑树** （当链表长度 >= 8 时 转为 红黑树， 低于 6 时 转为链表 (中间存在缓冲值)）
HashMap 默认初始容量 16， 负载因子为 0.75，当存储元素数量超过 16 * 0.75 时，会进行扩容，扩容为原来的 2 倍

### Java 中有哪些集合类？请简单介绍

List:

- ArrayList: 基于动态数组实现，支持随机访问，插入和删除操作的时间复杂度为 O(n)。
- LinkedList: 基于双向链表实现，支持高效的插入和删除操作，但不支持随机访问，访问操作的时间复杂度为 O(n)。
- Vector: 类似于 ArrayList，但线程安全，但性能较差。

Set:

- HashSet: 基于哈希表实现，不允许重复元素，支持快速查找。
- LinkedHashSet: 基于链表和哈希表实现，允许重复元素，支持快速查找，同时保持插入顺序。
- TreeSet: 基于红黑树实现，不允许重复元素，支持快速查找，同时保持排序顺序。

Queue:

- PriorityQueue: 基于堆实现，支持优先级队列，按照元素的优先级进行排序。
- LinkedList: 基于双向链表实现，支持队列操作，包括入队、出队、查看队首元素等。

Map:

- HashMap: 基于哈希表实现，支持快速查找、插入和删除操作。
- LinkedHashMap: 基于链表和哈希表实现，支持快速查找、插入和删除操作，同时保持插入顺序。
- TreeMap: 基于红黑树实现，支持快速查找、插入和删除操作，同时保持排序顺序。
- Hashtable: 类似于 HashMap，但线程安全，但性能较差。
- ConcurrentHashMap: 类似于 HashMap，但线程安全，支持并发访问，不允许键或值为 null。

### Java 中 HashMap 的扩容机制是怎样的？

负载因子 决定 HashMap 的扩容 （默认负载因子 0.75 ，默认初始容量 16， 当 16 * 0.75 = 12， 当容量 > 12 时， 容量扩 2 倍）
扩容机制： 定位数组的位置 （(数组长度 - 1）& 哈希值 )，如果出现哈希冲突，则使用链表或红黑树解决冲突

1. 创建一个新的数组，大小为原数组的两倍。
2. 遍历原数组，将每个键值对重新计算哈希值，并插入到新数组中。
3. 将新数组赋值给原数组。

---

### 你了解 Java 线程池的原理吗？

线程池是一种用于管理和复用线程的机制，它通过创建一定数量的线程来执行任务，避免了频繁创建和销毁线程的开销。

```java
// 线程池源码 （七大参数）
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler) {
    if (corePoolSize < 0 ||
            maximumPoolSize <= 0 ||
            maximumPoolSize < corePoolSize ||
            keepAliveTime < 0)
        throw new IllegalArgumentException();
    if (workQueue == null || threadFactory == null || handler == null)
        throw new NullPointerException();
    this.corePoolSize = corePoolSize;
    this.maximumPoolSize = maximumPoolSize;
    this.workQueue = workQueue;
    this.keepAliveTime = unit.toNanos(keepAliveTime);
    this.threadFactory = threadFactory;
    this.handler = handler;

    String name = Objects.toIdentityString(this);
    this.container = SharedThreadContainer.create(name);
}
```

- corePoolSize: 核心线程数，线程池中常驻的线程数量。
- maximumPoolSize: 最大线程数，线程池中允许存在的最大线程数量。
- keepAliveTime: 空闲线程的存活时间，当线程池中的线程数量超过 corePoolSize 时，多余的线程在空闲时间超过 keepAliveTime
  后会被终止。
- unit: keepAliveTime 的时间单位。
- workQueue: 任务队列，用于存放等待执行的任务。
- threadFactory: 线程工厂，用于创建新线程。
- handler: 拒绝策略，当线程池和任务队列都满了时，如何处理新任务。

### 你使用过哪些 Java 并发工具类？

- ConcurrentHashMap: 线程安全的哈希表，支持并发访问。
- AtomicInteger: 线程安全的整数，支持原子操作。
- BlockingQueue: 支持阻塞的队列，用于线程间通信。
- CountDownLatch: 允许一个或多个线程等待其他线程完成操作。
- CyclicBarrier: 允许一组线程互相等待，直到所有线程都到达某个屏障点。
- Semaphore: 计数信号量，用于控制同时访问某个资源的线程数量。

### 什么是 Java 的 CAS（Compare-And-Swap）操作？

cas是一种**硬件级别的原子操作**，通过 比较、 交换 来实现线程安全。

- 比较：比较当前内存中的值和预期值是否相等。
- 交换：如果相等，则将内存中的值更新为新值；如果不相等，则不做任何操作。
- 失败重试：如果比较失败，则循环执行比较和交换操作，直到成功为止。

---

### 说说 AQS 吧？

什么是AQS?
AQS（AbstractQueuedSynchronizer）是 Java 中一个用于实现锁和同步器的框架，它提供了一种通用的机制来管理线程的同步状态和阻塞/唤醒线程。

AQS 的核心思想：使用一个**共享的同步状态**来表示线程的同步状态，通过**原子操作**来修改同步状态，并通过**队列**来管理等待的线程。

### Synchronized 和 ReentrantLock 有什么区别？

|      | Synchronized | ReentrantLock  |
|------|--------------|----------------|
|      | 关键字          | 包下的类           |
| 实现机制 | JVM 层面实现     | API 层面实现       |
| 锁的释放 | 自动释放         | 手动释放（unlock()） |
| 锁类型  | 非公平锁         | 非公平锁&公平锁       |

### Java 中 volatile 关键字的作用是什么？
volatile 是 Java 中的一种轻量级同步机制，用于保证变量的 **可见性**和 **禁止指令重排**(有序性)。
1. 可见性：
   - 当一个线程修改了 volatile 变量的值，这个新值会立即被刷新到主内存中，而不是仅停留在当前线程的工作内存中。
   - 其他线程在读取该 volatile 变量时，会直接从主内存中获取最新值，而不是使用自己工作内存中的缓存值。
2. 禁止指令重排：volatile 变量的读写操作前后会插入内存屏障，防止 JVM 和处理器对其进行重排序优化。
ps：内存屏障是CPU或编译器在对内存随机访问的操作中的一个同步点，使得此点之前的所有读写操作都执行后才可以开始执行此点之后的操作（代码按顺序执行）

