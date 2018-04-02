package com.benharvey.stackoverflowusers.models;

/**
 * Created by benharvey on 3/29/18.
 */

public class User {

    private BadgeCount badge_counts;

    private String location;

    private String profile_image;

    private String link;

    private String display_name;

    private String age;

    private String user_id;

    public String getLocation ()
    {
        return location;
    }

    public void setLocation (String location)
    {
        this.location = location;
    }

    public String getProfile_image ()
    {
        return profile_image;
    }

    public void setProfile_image (String profile_image)
    {
        this.profile_image = profile_image;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getDisplay_name ()
    {
        return display_name;
    }

    public void setDisplay_name (String display_name)
    {
        this.display_name = display_name;
    }

    public String getAge ()
    {
        return age;
    }

    public void setAge (String age)
    {
        this.age = age;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public BadgeCount getBadgeCount ()
    {
        return badge_counts;
    }

    public void setBadge_counts(String gold, String silver, String bronze){
        this.badge_counts = new BadgeCount(gold, silver, bronze);
    }
}
