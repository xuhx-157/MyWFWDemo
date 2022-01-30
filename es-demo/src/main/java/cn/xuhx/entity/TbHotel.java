package cn.xuhx.entity;

import java.io.Serializable;

/**
 * (TbHotel)实体类
 *
 * @author makejava
 * @since 2022-01-24 21:24:37
 */
public class TbHotel implements Serializable {
    private static final long serialVersionUID = -30802616734726470L;
    /**
    * 酒店id
    */
    private Long id;
    /**
    * 酒店名称
    */
    private String name;
    /**
    * 酒店地址
    */
    private String address;
    /**
    * 酒店价格
    */
    private Integer price;
    /**
    * 酒店评分
    */
    private Integer score;
    /**
    * 酒店品牌
    */
    private String brand;
    /**
    * 所在城市
    */
    private String city;
    /**
    * 酒店星级，1星到5星，1钻到5钻
    */
    private String starName;
    /**
    * 商圈
    */
    private String business;
    /**
    * 纬度
    */
    private String latitude;
    /**
    * 经度
    */
    private String longitude;
    /**
    * 酒店图片
    */
    private String pic;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

}