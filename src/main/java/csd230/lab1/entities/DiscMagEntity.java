package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("DISCMAG")
public class DiscMagEntity extends MagazineEntity {

    private boolean hasDisc;

    public DiscMagEntity() {}

    public DiscMagEntity(String title, double price, int copies, int orderQty, LocalDateTime currentIssue, boolean hasDisc) {
        super(title, price, copies, orderQty, currentIssue);
        this.hasDisc = hasDisc;
    }

    public boolean isHasDisc() { return hasDisc; }
    public void setHasDisc(boolean hasDisc) { this.hasDisc = hasDisc; }

    @Override
    public String toString() {
        return super.toString() + ", hasDisc=" + hasDisc;
    }
}
