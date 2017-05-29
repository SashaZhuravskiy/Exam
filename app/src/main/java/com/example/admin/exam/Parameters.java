package com.example.admin.exam;

/**
 * Created by User on 13.04.2017.
 */

public class Parameters {
    String Param1;
    String Param2;
    long id;

    public Parameters(String Param1, String Param2, long id)
    {
        this.Param1 = Param1;
        this.Param2 = Param2;
        this.id = id;
    }

    public String GetParam1(){return Param1;}
    public String GetParam2(){return Param2;}
    public long getId() { return id;}
}
