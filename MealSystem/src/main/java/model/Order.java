package model;

import java.sql.Timestamp;
import java.util.List;

//表示完整的订单
//包括订单中有哪些菜
//Oder中包含了两张表的信息
public class Order {
    private int orderId;
    private int userId;
    private Timestamp time;  //时间戳
    private int isDone;
    private List<Dish> dishes; //一个订单包含了许多菜

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", time=" + time +
                ", isDone=" + isDone +
                ", dishes=" + dishes +
                '}';
    }
}
