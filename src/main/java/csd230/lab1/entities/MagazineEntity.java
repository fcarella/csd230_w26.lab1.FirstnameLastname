package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("MAGAZINE")
public class MagazineEntity extends PublicationEntity {

    private int orderQty;
    private LocalDateTime currentIssue;

    public MagazineEntity() {}

    public MagazineEntity(String title, double price, int copies, int orderQty, LocalDateTime currentIssue) {
        super(title, price, copies);
        this.orderQty = orderQty;
        this.currentIssue = currentIssue;
    }

    public int getOrderQty() { return orderQty; }
    public void setOrderQty(int orderQty) { this.orderQty = orderQty; }

    public LocalDateTime getCurrentIssue() { return currentIssue; }
    public void setCurrentIssue(LocalDateTime currentIssue) { this.currentIssue = currentIssue; }

    @Override
    public String toString() {
        return super.toString() + ", orderQty=" + orderQty + ", currentIssue=" + currentIssue;
    }
}
