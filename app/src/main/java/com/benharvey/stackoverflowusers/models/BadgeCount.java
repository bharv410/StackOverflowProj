package com.benharvey.stackoverflowusers.models;

/**
 * Created by benharvey on 3/30/18.
 */

public class BadgeCount {

    private String bronze;

    private String silver;

    private String gold;

    public String getBronze ()
    {
        return bronze;
    }


    public String getSilver ()
    {
        return silver;
    }

    public String getGold ()
    {
        return gold;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [bronze = "+bronze+", silver = "+silver+", gold = "+gold+"]";
    }

}
