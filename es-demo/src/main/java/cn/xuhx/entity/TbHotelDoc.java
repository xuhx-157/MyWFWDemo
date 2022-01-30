package cn.xuhx.entity;

public class TbHotelDoc {

    private static final long serialVersionUID = -30802616734726470L;
    /**
     * 酒店id
     */
    private String id;
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
     * 经纬度
     */
    private String location;

    /**
     * 酒店图片
     */
    private String pic;

    public TbHotelDoc() {
    }

    public TbHotelDoc(TbHotel tbHotel) {
        this.id = tbHotel.getId().toString();
        this.name = tbHotel.getName();
        this.address = tbHotel.getAddress();
        this.price = tbHotel.getPrice();
        this.score = tbHotel.getScore();
        this.brand = tbHotel.getBrand();
        this.city = tbHotel.getCity();
        this.starName = tbHotel.getStarName();
        this.business = tbHotel.getBusiness();
        this.location = tbHotel.getLatitude() + ", " + tbHotel.getLongitude();
        this.pic = tbHotel.getPic();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "TbHotelDoc{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", score=" + score +
                ", brand='" + brand + '\'' +
                ", city='" + city + '\'' +
                ", starName='" + starName + '\'' +
                ", business='" + business + '\'' +
                ", location='" + location + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
