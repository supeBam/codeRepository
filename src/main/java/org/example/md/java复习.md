### 单例模式有哪几种实现？如何保证线程安全？ 
**饿汉式：**线程安全的，在类加载时初始化
```java
public class Singleton {
    private static Singleton instance = new Singleton();
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        return instance;
    }
}
```
**懒汉式：** 线程不安全，在第一次调用时初始化
```java
public class Singleton {
    private static Singleton instance;
    
    private Singleton() {}
    
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
    
    private Singleton() {}
    
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
    private Singleton() {}
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
singleton.doSomething();
```

###  什么是策略模式？一般用在什么场景？
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
public class BubbleSort implements ISort{

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
public class QuickSort implements ISort{

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
public class SelectionSort implements ISort{
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
        int[] arr = { 5, 2, 8, 1, 9, 3 };
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
public class RedBeanMilkTea extends MilkTea{
    
    @Override
    public void addIngredients() {
        System.out.println("添加红豆");
    }

}

public class VanillaMilkTea extends MilkTea{
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