package com.example.fileupload.helper;

public class MyException extends Throwable {




    public MyException (String str) {
        super(str);
        System.out.println("invalid column value");

    }
}
