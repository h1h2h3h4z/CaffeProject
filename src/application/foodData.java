package application;

import java.util.Date;

public class foodData {
    private int foodID;
    private String foodName;
    private String foodStatus;
    private double foodPrice;
    private Date foodDate;
    private String foodImage;

    // Constructor
    public foodData(int foodID, String foodName, String foodStatus, double foodPrice, Date foodDate, String foodImage) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodStatus = foodStatus;
        this.foodPrice = foodPrice;
        this.foodDate = foodDate;
        this.foodImage = foodImage;
    }

    // Getters and Setters
    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodStatus() {
        return foodStatus;
    }

    public void setFoodStatus(String foodStatus) {
        this.foodStatus = foodStatus;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Date getFoodDate() {
        return foodDate;
    }

    public void setFoodDate(Date foodDate) {
        this.foodDate = foodDate;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }
}
