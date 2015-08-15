package com.example.findr;

/**
 * Created by Ian on 2015-08-15.
 */
public class Event {
    public String hostName, name, contact, location, description;
    public String[] tags, qualifications;
    public Time[] times;
    public double[] coordinates;
    public Policy policy;
}

public enum Policy {
    DROP_IN, ARRIVE_AT_START
}