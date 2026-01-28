package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@DiscriminatorValue("MAGAZINE")
public class MagazineEntity extends PublicationEntity {
    private int orderQty;
    private LocalDateTime currentIssue;

    public MagazineEntity() {}

    public MagazineEntity(String title, String description, double price,
                          int quantity, int orderQty, LocalDateTime currentIssue) {
        super(title, description, price, quantity);  // This should work now
        this.orderQty = orderQty;
        this.currentIssue = currentIssue;
    }

    public int getOrderQty() { return orderQty; }
    public void setOrderQty(int o) { this.orderQty = o; }
    public void setCurrentIssue(LocalDateTime d) { this.currentIssue = d; }
    public LocalDateTime getCurrentIssue() { return currentIssue; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MagazineEntity that = (MagazineEntity) o;
        return orderQty == that.orderQty && Objects.equals(currentIssue, that.currentIssue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderQty, currentIssue);
    }

    @Override
    public String toString() {
        return "Mag{issue=" + currentIssue + ", " + super.toString() + "}";
    }

    @Override
    public boolean sell(int quantity) {
        return super.sell(quantity); // Call PublicationEntity's sell method
    }
}