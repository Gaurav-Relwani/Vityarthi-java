package Library.service;

import Library.model.Book;
import Library.storage.FileStorage;

import java.util.*;
import java.util.stream.Collectors;

public class BookService {
    private final FileStorage storage;
    private final List<Book> books;

    public BookService(FileStorage storage) {
        this.storage = storage;
        this.books = new ArrayList<>(storage.readAllBooks());
    }

    public Book addBook(String title, String author) {
        int id = storage.nextBookId();
        Book b = new Book(id, title.trim(), author.trim(), false);
        books.add(b);
        storage.writeAllBooks(books);
        return b;
    }

    public List<Book> listAll() { return Collections.unmodifiableList(books); }

    public Optional<Book> findById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst();
    }

    public List<Book> search(String q) {
        String ql = q.toLowerCase();
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(ql) || b.getAuthor().toLowerCase().contains(ql))
                .collect(Collectors.toList());
    }

    public boolean remove(int id) {
        Optional<Book> ob = findById(id);
        if (ob.isPresent()) {
            books.remove(ob.get());
            storage.writeAllBooks(books);
            return true;
        }
        return false;
    }

    public boolean update(int id, String newTitle, String newAuthor) {
        Optional<Book> ob = findById(id);
        if (ob.isPresent()) {
            Book b = ob.get();
            b.setTitle(newTitle.trim());
            b.setAuthor(newAuthor.trim());
            storage.writeAllBooks(books);
            return true;
        }
        return false;
    }

    public boolean setIssued(int id, boolean issued) {
        Optional<Book> ob = findById(id);
        if (ob.isPresent()) {
            ob.get().setIssued(issued);
            storage.writeAllBooks(books);
            return true;
        }
        return false;
    }

    public List<Book> availableBooks() {
        return books.stream().filter(b -> !b.isIssued()).collect(Collectors.toList());
    }

    public List<Book> issuedBooks() {
        return books.stream().filter(Book::isIssued).collect(Collectors.toList());
    }
}
