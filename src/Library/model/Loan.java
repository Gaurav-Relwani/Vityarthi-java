package Library.model;

public class Loan {
    private int id;
    private int bookId;
    private int memberId;

    public Loan(int id, int bookId, int memberId) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
    }

    public int getId() { return id; }
    public int getBookId() { return bookId; }
    public int getMemberId() { return memberId; }

    public String toDataString() {
        return id + "|" + bookId + "|" + memberId;
    }

    public static Loan fromDataString(String line) {
        String[] p = line.split("\\|");
        return new Loan(
                Integer.parseInt(p[0]),
                Integer.parseInt(p[1]),
                Integer.parseInt(p[2])
        );
    }

    @Override
    public String toString() {
        return "Loan ID: " + id +
                " | Book ID: " + bookId +
                " | Member ID: " + memberId;
    }
}
