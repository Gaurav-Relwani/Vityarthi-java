package Library.storage;

import Library.model.Book;
import Library.model.Member;
import Library.model.Loan;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class FileStorage {
    private final Path dataDir = Paths.get("data");
    private final Path booksFile = dataDir.resolve("books.txt");
    private final Path membersFile = dataDir.resolve("members.txt");
    private final Path loansFile = dataDir.resolve("loans.txt");

    private final AtomicInteger bookIdGen = new AtomicInteger(0);
    private final AtomicInteger memberIdGen = new AtomicInteger(0);
    private final AtomicInteger loanIdGen = new AtomicInteger(0);

    public FileStorage() {
        try {
            if (!Files.exists(dataDir)) Files.createDirectories(dataDir);
            if (!Files.exists(booksFile)) Files.createFile(booksFile);
            if (!Files.exists(membersFile)) Files.createFile(membersFile);
            if (!Files.exists(loansFile)) Files.createFile(loansFile);
            initIdGenerators();
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize storage: " + e.getMessage(), e);
        }
    }

    private void initIdGenerators() throws IOException {
        Files.lines(booksFile).filter(s -> !s.isBlank()).forEach(line -> {
            try { int id = Integer.parseInt(line.split("\\|", -1)[0]); bookIdGen.updateAndGet(x -> Math.max(x, id)); } catch (Exception ignored) {}
        });
        Files.lines(membersFile).filter(s -> !s.isBlank()).forEach(line -> {
            try { int id = Integer.parseInt(line.split("\\|", -1)[0]); memberIdGen.updateAndGet(x -> Math.max(x, id)); } catch (Exception ignored) {}
        });
        Files.lines(loansFile).filter(s -> !s.isBlank()).forEach(line -> {
            try { int id = Integer.parseInt(line.split("\\|", -1)[0]); loanIdGen.updateAndGet(x -> Math.max(x, id)); } catch (Exception ignored) {}
        });
    }

    public int nextBookId() { return bookIdGen.incrementAndGet(); }
    public int nextMemberId() { return memberIdGen.incrementAndGet(); }
    public int nextLoanId() { return loanIdGen.incrementAndGet(); }

    // BOOKS
    public synchronized List<Book> readAllBooks() {
        try {
            return Files.readAllLines(booksFile).stream()
                    .filter(s -> !s.isBlank())
                    .map(Book::fromDataString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void writeAllBooks(List<Book> books) {
        try {
            List<String> lines = books.stream().map(Book::toDataString).collect(Collectors.toList());
            Files.write(booksFile, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // MEMBERS
    public synchronized List<Member> readAllMembers() {
        try {
            return Files.readAllLines(membersFile).stream()
                    .filter(s -> !s.isBlank())
                    .map(Member::fromDataString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void writeAllMembers(List<Member> members) {
        try {
            List<String> lines = members.stream().map(Member::toDataString).collect(Collectors.toList());
            Files.write(membersFile, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // LOANS
    public synchronized List<Loan> readAllLoans() {
        try {
            return Files.readAllLines(loansFile).stream()
                    .filter(s -> !s.isBlank())
                    .map(Loan::fromDataString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void writeAllLoans(List<Loan> loans) {
        try {
            List<String> lines = loans.stream().map(Loan::toDataString).collect(Collectors.toList());
            Files.write(loansFile, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
