package com.studygroup.util;

public class ObjectToLong
{
    public static Long convert(Object o){
        return Long.valueOf(o.toString());
    }
}
