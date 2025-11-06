package teacher.models;

public class Teacher {
    private int id;
    private String name;
    private String email;
    private String subject;

    public Teacher(int id, String name, String email, String subject) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subject = subject;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getSubject() { return subject; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setSubject(String subject) { this.subject = subject; }
}
