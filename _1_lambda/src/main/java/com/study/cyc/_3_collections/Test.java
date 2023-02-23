package com.study.cyc._3_collections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author caoyuchen <caoyuchen@kuaishou.com>
 * Created on 2023-02-23
 */


public class Test {
    public static Logger logger = LoggerFactory.getLogger(_1_List.class);

    public static void main(String args[]) {
        try {
            // 读入TXT文件
            String pathname = "/Users/yuchencao/Desktop/tmpTxt/serviceNameAndCnt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            line = br.readLine();


             // 写入Txt文件
            File writename = new File("/Users/yuchencao/Desktop/tmpTxt/result.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));

            String end = "\r\n";
            String interval = "\t";
            out.write("sum(`count`)" + interval + "kServiceName" + end);
            while (line != null) {
                //保留2 -3 3-4个｜中的元素
                String[] ak = line.split("\\|");
                out.write(ak[3].trim());

                out.write(interval);
                out.write(ak[2].trim());
                //System.out.println(ak.length);
                //out.write(line); // \r\n即为换行
                out.write(end);
                //处理line, 忽略第一行
                line = br.readLine(); // 一次读入一行数据
            }


            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件

            logger.info("success");

        } catch (Exception e) {
            logger.info("读取文件失败");
            e.printStackTrace();
        }
    }
}