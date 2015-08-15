package com.example.findr;

import java.sql.Timestamp;

/**
 * Created by Ian on 2015-08-15.
 */
public class Time {
    String type = "duration";
    Timestamp start, end;

    public Time(long start, long end) {
        this.start = new Timestamp(start);
        this.end = new Timestamp(end);
    }
}
