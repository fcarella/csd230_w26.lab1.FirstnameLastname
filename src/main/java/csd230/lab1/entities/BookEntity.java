package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("BOOK")
public class BookEntity extends PublicationEntity {
    private String author;

    public BookEntity() {}

    public BookEntity(String title, String description, double price,
                      int copies, String author) {
        super(title, description, price, copies);  // Now matches PublicationEntity constructor
        this.author = author;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String a) { this.author = a; }

    @Override
    public String toString() {
        return "Book{author='" + author + "', " + super.toString() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author);
    }
}