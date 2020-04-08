package com.lloydfinch.javalib;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestClass {

    /**
     * 测试Properties，类似于Android的SharedPreferences
     *
     * @param args
     */
    public static void main(String[] args) {
        File file = new File("private.properties");
        Properties properties = new Properties();
        try {
            properties.load(new DataInputStream(new FileInputStream(file)));
            String password = properties.getProperty("release.password");
            System.out.println("password: " + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
