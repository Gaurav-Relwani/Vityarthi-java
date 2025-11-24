package Library.model;

public class Member {
    private int id;
    private String name;
    private String email;

    public Member(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    public String toDataString() {
        return id + "|" + name + "|" + email;
    }

    public static Member fromDataString(String line) {
        String[] p = line.split("\\|");
        return new Member(
                Integer.parseInt(p[0]),
                p[1],
                p[2]
        );
    }

    @Override
    public String toString() {
        return id + " - " + name + " (" + email + ")";
    }
}
