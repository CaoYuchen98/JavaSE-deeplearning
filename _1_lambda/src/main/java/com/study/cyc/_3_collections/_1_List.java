package com.study.cyc._3_collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author caoyuchen <caoyuchen@kuaishou.com>
 * Created on 2023-02-23
 */
//jdk1.8 在Collections 集合类下新增的方法，大部分用到java.util.function 包下的接口
//settings -- file -- live templates 打开 psvm 和 sout
//command home/end 定位到行首和行尾
public class _1_List {
    /**
     * forEach(), removeIf(), replaceAll()
     *
     * @param args
     */

    public static Logger logger = LoggerFactory.getLogger(_1_List.class);

    public static void main(String[] args) {
       //1. forEach() 对每个元素执行action 动作
       //打印出所有长度大于3 的字符串
        //注意asList 创造的是一个内部类，缺乏一些删除的方法
        List<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        list.forEach(str -> {
            if (str.length() > 3)
                logger.info(str);
        });

        //2. removeIf()
        // 删除长度大于3 的字符串
        list.removeIf(str -> str.length() > 3);  // 删除了 "love"
        list.forEach(logger::info);

        //3. replaceAll()
        //替换长度为3 的字符串为大写 toUpperCase()
        list.replaceAll(str -> {
            if (str.length() == 3)
                return str.toUpperCase();
            //  replaceAll() 需要return
            return str;
        });
        list.forEach(logger::info);
    }

}
