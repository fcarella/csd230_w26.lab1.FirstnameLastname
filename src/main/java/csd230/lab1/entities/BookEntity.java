package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BOOK")
public class BookEntity extends PublicationEntity {

    private String author;

    @Column(unique = true)
    private String isbn;

    public BookEntity() {}

    public BookEntity(String title, double price, int copies, String author, String isbn) {
        super(title, price, copies);
        this.author = author;
        this.isbn = isbn;
    }

    public BookEntity(String title, double price, int copies, String author) {
        this(title, price, copies, author, null);
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    @Override
    public String toString() {
        return super.toString() + ", author='" + author + "', isbn='" + isbn + "'";
    }
}
