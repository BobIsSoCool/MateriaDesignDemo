package com.example.wub.materialdemo;

/**
 * Created by wub on 2017/6/19.
 */
public class Fruit {
    /**
     * 水果名
     */
    private String name;
    /**
     * 对应图片
     */
    private int imageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
