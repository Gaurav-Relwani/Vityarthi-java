package Library;

import Library.model.Book;
import Library.model.Member;
import Library.model.Loan;
import Library.storage.FileStorage;
import Library.service.BookService;
import Library.service.MemberService;
import Library.service.LoanService;
import Library.util.InputUtil;

import java.util.List;
import java.util.Optional;

public class LibraryApp {
    private final FileStorage storage;
    private final BookService bookService;
    private final MemberService memberService;
    private final LoanService loanService;

    public LibraryApp() {
        storage = new FileStorage();
        bookService = new BookService(storage);
        memberService = new MemberService(storage);
        loanService = new LoanService(storage, bookService);
    }

    public void start() {
        while (true) {
            showMenu();
            int choice = InputUtil.readInt("Choose: ", 1, 12);
            try {
                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> listBooks();
                    case 3 -> searchBooks();
                    case 4 -> updateBook();
                    case 5 -> removeBook();
                    case 6 -> addMember();
                    case 7 -> listMembers();
                    case 8 -> issueBook();
                    case 9 -> returnBook();
                    case 10 -> listLoans();
                    case 11 -> showReports();
                    case 12 -> { System.out.println("Goodbye!"); System.exit(0); }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println(); // spacing
        }
    }

    private void showMenu() {
        System.out.println("=== Library Menu ===");
        System.out.println("1. Add Book");
        System.out.println("2. List All Books");
        System.out.println("3. Search Books (title/author)");
        System.out.println("4. Update Book");
        System.out.println("5. Remove Book");
        System.out.println("6. Add Member");
        System.out.println("7. List Members");
        System.out.println("8. Issue Book");
        System.out.println("9. Return Book");
        System.out.println("10. List Loans");
        System.out.println("11. Reports");
        System.out.println("12. Exit");
    }

    private void addBook() {
        String title = InputUtil.readNonEmpty("Title: ");
        String author = InputUtil.readNonEmpty("Author: ");
        Book b = bookService.addBook(title, author);
        System.out.println("Added: " + b);
    }

    private void listBooks() {
        List<Book> list = bookService.listAll();
        if (list.isEmpty()) System.out.println("No books.");
        else list.forEach(System.out::println);
    }

    private void searchBooks() {
        String q = InputUtil.readNonEmpty("Search query: ");
        List<Book> res = bookService.search(q);
        if (res.isEmpty()) System.out.println("No results.");
        else res.forEach(System.out::println);
    }

    private void updateBook() {
        int id = InputUtil.readInt("Book ID to update: ");
        Optional<Book> ob = bookService.findById(id);
        if (ob.isEmpty()) { System.out.println("Book not found."); return; }
        String title = InputUtil.readNonEmpty("New title: ");
        String author = InputUtil.readNonEmpty("New author: ");
        boolean ok = bookService.update(id, title, author);
        System.out.println(ok ? "Updated." : "Update failed.");
    }

    private void removeBook() {
        int id = InputUtil.readInt("Book ID to remove: ");
        boolean ok = bookService.remove(id);
        System.out.println(ok ? "Removed." : "Not found.");
    }

    private void addMember() {
        String name = InputUtil.readNonEmpty("Member name: ");
        String email = InputUtil.readNonEmpty("Member email: ");
        Member m = memberService.addMember(name, email);
        System.out.println("Added: " + m);
    }

    private void listMembers() {
        List<Member> m = memberService.listAll();
        if (m.isEmpty()) System.out.println("No members.");
        else m.forEach(System.out::println);
    }

    private void issueBook() {
        int bookId = InputUtil.readInt("Book ID: ");
        Optional<Book> ob = bookService.findById(bookId);
        if (ob.isEmpty()) { System.out.println("Book not found."); return; }
        if (ob.get().isIssued()) { System.out.println("Book already issued."); return; }

        int memberId = InputUtil.readInt("Member ID: ");
        Optional<?> om = memberService.findById(memberId);
        if (om.isEmpty()) { System.out.println("Member not found."); return; }

        Loan loan = loanService.issueBook(bookId, memberId);
        System.out.println("Issued: " + loan);
    }

    private void returnBook() {
        int loanId = InputUtil.readInt("Loan ID to return: ");
        boolean ok = loanService.returnBook(loanId);
        System.out.println(ok ? "Book returned." : "Loan not found or return failed.");
    }

    private void listLoans() {
        List<Loan> list = loanService.listAll();
        if (list.isEmpty()) System.out.println("No active loans.");
        else list.forEach(System.out::println);
    }

    private void showReports() {
        System.out.println("---- Reports ----");
        System.out.println("Total books: " + bookService.listAll().size());
        System.out.println("Available books: " + bookService.availableBooks().size());
        System.out.println("Issued books: " + bookService.issuedBooks().size());
        System.out.println("Total members: " + memberService.listAll().size());
        System.out.println("Active loans: " + loanService.listAll().size());
    }

    public static void main(String[] args) {
        new LibraryApp().start();
    }
}
