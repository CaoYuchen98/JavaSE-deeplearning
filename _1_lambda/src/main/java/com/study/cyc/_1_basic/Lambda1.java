package com.study.cyc._1_basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author caoyuchen <caoyuchen@kuaishou.com>
 * Created on 2023-02-23
 */
public class Lambda1 {

    static final Logger logger = LoggerFactory.getLogger(Lambda1.class);

    public static void main(String[] args){
        //1. 无参函数
        //启动线程1
        new Thread(){
            @Override
            public void run(){
                logger.info(Thread.currentThread().getName() + " : Hello, my lord");
            }
        }.start();

        //启动线程2, Runnable 接口是个函数式接口，使用lambda 表达式重写run
        //省略了接口名和函数名 -- 基于javac 编译器的类型推断机制,单行甚至省略了return 和 大括号，并且实际上没有产生匿名内部类的对象（用this 指针去验证）
        new Thread(() -> logger.info(Thread.currentThread().getName() + " : use lambda")).start();

        //2. 有参函数 sort
        // option+command+v 快速生成对象  +f 快速生成静态参数
        List<String> list = Arrays.asList("I", "love", "you", "too");
        List<String> list2 = new ArrayList<>(list);
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length())
                    return o1.compareTo(o2);
                return o1.length() - o2.length();
            }
        });
        list.forEach(logger::info);

        list2.sort((o1, o2) -> {
            if (o1.length() == o2.length())
                return o1.compareTo(o2);
            return o1.length() - o2.length();
        });
        list2.forEach(logger::info);

        //3. 测试自定义的接口
        String tmp = "ss";
        ConsumerInterface<String> consumer = str -> logger.info(str);

        //扩展实现MyStream 中的myForEach 方法
        Lambda1 lambda1 = new Lambda1();
        Lambda1.MyStream<String> stream = lambda1.new MyStream<String>();
        stream.list = list;
        //stream.myForEach(str -> logger.info(str));
        stream.myForEach(logger::info);
    }

    public class MyStream<T>{
        public List<T> list;
        public void myForEach(ConsumerInterface<T> consumer){
            for (T t : list){
                //每一个元素都调用consumer 中的重写的accept 方法
                consumer.accept(t);
            }
        }
    }
}


/*
解决Error 错误，不支持发行版本5：
1。 project-structure(command + ;) --- 修改sdk和language level --- 如果已经依赖了旧版本的jdk，在同界面的SDKs 中删除Lib 中的jdk包
2。 检查Setting（command + ，）中的maven - importing 中的jdk for importer 是否一致
3。 检查Compile -- Java Compiler -- Project-byte-verison 是否修改了版本，并且更改已加载module 的bytecode version

解决项目import 的问题
1。 调整Profiles 中的参数，让project 中的所有modules 都加载完毕后再reload maven projects
2。 尽量使用openjdk 版本，不要使用adopte jdk 版本等
3。 直接通过命令行 mvn import project，并将import 重定向到一个日志文件中，或者ps -ef | prog maven 查看maven 进程的进展
 */
