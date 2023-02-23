package com.study.cyc._1_basic;

/**
 * @author caoyuchen <caoyuchen@kuaishou.com>
 * Created on 2023-02-23
 */
@FunctionalInterface
public interface ConsumerInterface<T> {
    void accept(T t);
}
