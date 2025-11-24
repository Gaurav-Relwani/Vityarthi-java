package Library.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private boolean issued;

    public Book(int id, String title, String author, boolean issued) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = issued;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return issued; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setIssued(boolean issued) { this.issued = issued; }

    // Convert to text for file saving
    public String toDataString() {
        return id + "|" + title + "|" + author + "|" + issued;
    }

    // Convert text back into book object
    public static Book fromDataString(String line) {
        String[] p = line.split("\\|");
        return new Book(
                Integer.parseInt(p[0]),
                p[1],
                p[2],
                Boolean.parseBoolean(p[3])
        );
    }

    @Override
    public String toString() {
        return id + " - " + title + " by " + author +
                (issued ? " (Issued)" : " (Available)");
    }
}
