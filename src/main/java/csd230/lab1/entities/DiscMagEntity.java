package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity @DiscriminatorValue("DISCMAG")
public class DiscMagEntity extends MagazineEntity {
    private boolean hasDisc;
    public DiscMagEntity() {}
    public DiscMagEntity(String title, String description, double price,
                         int quantity, int orderQty, LocalDateTime currentIssue, boolean hasDisc) {
        super(title, description, price, quantity, orderQty, currentIssue);  // Call MagazineEntity constructor
        this.hasDisc = hasDisc;
    }
    public boolean isHasDisc() { return hasDisc; }
    public void setHasDisc(boolean h) { this.hasDisc = h; }

    public DiscMagEntity(boolean hasDisc) {
        this.hasDisc = hasDisc;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DiscMagEntity that = (DiscMagEntity) o;
        return hasDisc == that.hasDisc;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(hasDisc);
    }

    @Override public String toString() { return "DiscMag{disc=" + hasDisc + ", " + super.toString() + "}"; }
}
