package classes;

import java.util.Date;

public class Record {
    private String userId;
    private String bookISBN;
    private Date borrowedAt;

    public Record(String userId, String ISBN) {
        this.userId = userId;
        this.bookISBN = ISBN;
    }

    public boolean is(User user, String ISBN) {
        return this.userId.equals(user.getId()) &&
                this.bookISBN.equals(ISBN);
    }
}
