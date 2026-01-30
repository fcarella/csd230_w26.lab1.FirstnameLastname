package csd230.lab1.pojos;

import java.util.Objects;

public class Book extends Publication {
    private String author;

    public Book() {
    }

    public Book(String author) {
        this.author = author;
    }

    public Book(String author, String title, double price, int copies) {
        super(title, price, copies);
        this.author = author;
    }

    // REMOVED: initialize(), edit(), sellItem() methods if they use getInput()

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + getTitle() + '\'' +
                ", price=" + getPrice() +
                ", copies=" + getCopies() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author);
    }

    @Override
    public void sellItem() {

    }
}