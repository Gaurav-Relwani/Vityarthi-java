package Library.service;

import Library.model.Loan;
import Library.model.Book;
import Library.storage.FileStorage;

import java.util.*;
import java.util.stream.Collectors;

public class LoanService {
    private final FileStorage storage;
    private final BookService bookService;
    private final List<Loan> loans;

    public LoanService(FileStorage storage, BookService bookService) {
        this.storage = storage;
        this.bookService = bookService;
        this.loans = new ArrayList<>(storage.readAllLoans());
    }

    public Loan issueBook(int bookId, int memberId) {
        Optional<Book> ob = bookService.findById(bookId);
        if (ob.isEmpty()) throw new IllegalArgumentException("Book not found");
        Book book = ob.get();
        if (book.isIssued()) throw new IllegalStateException("Book already issued");

        int id = storage.nextLoanId();
        Loan loan = new Loan(id, bookId, memberId);
        loans.add(loan);
        bookService.setIssued(bookId, true);
        storage.writeAllLoans(loans);
        return loan;
    }

    public boolean returnBook(int loanId) {
        Optional<Loan> ol = loans.stream().filter(l -> l.getId() == loanId).findFirst();
        if (ol.isEmpty()) return false;
        Loan loan = ol.get();
        boolean ok = bookService.setIssued(loan.getBookId(), false);
        if (!ok) return false;
        loans.remove(loan);
        storage.writeAllLoans(loans);
        return true;
    }

    public List<Loan> listAll() { return Collections.unmodifiableList(loans); }

    public List<Loan> findByMemberId(int memberId) {
        return loans.stream().filter(l -> l.getMemberId() == memberId).collect(Collectors.toList());
    }

    public Optional<Loan> findByBookId(int bookId) {
        return loans.stream().filter(l -> l.getBookId() == bookId).findFirst();
    }
}
