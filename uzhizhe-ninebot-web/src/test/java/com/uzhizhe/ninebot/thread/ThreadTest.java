package com.uzhizhe.ninebot.thread;

import org.junit.Test;

/**
 * @Desc thread test
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-08-07
 */
public class ThreadTest {


    @Test
    public void test() {
        new Thread(() -> {
            System.out.println("A");
        }).start();

        new Thread(() -> {
            System.out.println("B");
        }).start();

        System.out.println("C");
    }

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {

                System.out.print("A");
            }
        }.start();

        new Thread() {
            @Override
            public void run() {

                System.out.print("B");
            }
        }.start();


        System.out.print("C");

    }

}
