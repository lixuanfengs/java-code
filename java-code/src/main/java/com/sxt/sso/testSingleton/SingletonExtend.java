package com.sxt.sso.testSingleton;

import java.util.HashMap;
import java.util.Map;
//单例模式本质上是控制单例类的实例数量只有一个，有些时候我们可能想要某个类特定数量的实例，
//这种情况可以看做是单例模式的一种扩展情况。比如我们希望下面的类SingletonExtend只有三个实例，我们可以利用Map来缓存这些实例。
public class SingletonExtend {
	 //装载SingletonExtend实例的容器
    private static final Map<String,SingletonExtend> container = new HashMap<String, SingletonExtend>();
    //SingletonExtend类最多拥有的实例数量
    private static final int MAX_NUM = 3;
    //实例容器中元素的key的开始值
    private static String CACHE_KEY_PRE = "cache";
    private static int initNumber = 1;
    private SingletonExtend(){
        System.out.println("创建SingletonExtend实例1次！");
    }

    //先从容器中获取实例，若实例不存在，在创建实例，然后将创建好的实例放置在容器中
    public static SingletonExtend getInstance(){
        String key = CACHE_KEY_PRE+ initNumber;
        SingletonExtend singletonExtend = container.get(key);
        if (singletonExtend == null) {
            singletonExtend = new SingletonExtend();
            container.put(key,singletonExtend);
        }
        initNumber++;
        //控制容器中实例的数量
        if (initNumber > 3) {
            initNumber = 1;
        }
        return singletonExtend;
    }

    public static void main(String[] args) {
        SingletonExtend instance = SingletonExtend.getInstance();
        SingletonExtend instance1 = SingletonExtend.getInstance();
        SingletonExtend instance2 = SingletonExtend.getInstance();
        SingletonExtend instance3 = SingletonExtend.getInstance();
        SingletonExtend instance4 = SingletonExtend.getInstance();
        SingletonExtend instance5 = SingletonExtend.getInstance();
        SingletonExtend instance6 = SingletonExtend.getInstance();
        SingletonExtend instance7 = SingletonExtend.getInstance();
        SingletonExtend instance8 = SingletonExtend.getInstance();
        SingletonExtend instance9 = SingletonExtend.getInstance();
        System.out.println(instance);
        System.out.println(instance1);
        System.out.println(instance2);
        System.out.println(instance3);
        System.out.println(instance4);
        System.out.println(instance5);
        System.out.println(instance6);
        System.out.println(instance7);
        System.out.println(instance8);
        System.out.println(instance9);
    }
}
