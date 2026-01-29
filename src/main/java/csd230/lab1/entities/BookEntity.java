package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Entity that represents a Book in the database.
 * It extends PublicationEntity, so it inherits:
 * - title
 * - description
 * - copies
 * - pubPrice
 */
@Entity
@DiscriminatorValue("BOOK")
public class BookEntity extends PublicationEntity {

    // Author of the book
    private String author;

    // New field required by the repository (findByIsbn)
    private String isbn;

    // Empty constructor required by JPA
    public BookEntity() {
    }

    // Convenience constructor used in Application.run(...)
    public BookEntity(String t, double p, int c, String a) {
        super(t, p, c);  // calls PublicationEntity(title, pubPrice, copies)
        this.author = a;
    }

    // --- Getters and setters ---

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String a) {
        this.author = a;
    }

    // ISBN getter/setter for Spring Data query (findByIsbn)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // --- toString for debugging ---

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + getId() +              // from ProductEntity
                ", title='" + getTitle() + '\'' +  // from PublicationEntity
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", copies=" + getCopies() +
                ", pubPrice=" + getPrice() +
                "} ";
    }
}
