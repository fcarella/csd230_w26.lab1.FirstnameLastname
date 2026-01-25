package csd230.lab1.pojos;

import java.util.Date;
import java.util.Objects;

public class Magazine extends Publication {
    private int orderQty;
    private Date currentIssue;

    public Magazine() {
    }

    public Magazine(int orderQty, Date currentIssue, String title, double price, int copies) {
        super(title, price, copies);
        this.orderQty = orderQty;
        this.currentIssue = currentIssue;
    }

    // REMOVED: initialize(), edit(), sellItem() methods

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public Date getCurrentIssue() {
        return currentIssue;
    }

    public void setCurrentIssue(Date currentIssue) {
        this.currentIssue = currentIssue;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "orderQty=" + orderQty +
                ", currentIssue=" + currentIssue +
                ", title='" + getTitle() + '\'' +
                ", price=" + getPrice() +
                ", copies=" + getCopies() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Magazine)) return false;
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return orderQty == magazine.orderQty &&
                Objects.equals(currentIssue, magazine.currentIssue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderQty, currentIssue);
    }

    @Override
    public void sellItem() {

    }
}