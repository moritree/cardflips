package moritree;
import java.time.LocalDateTime;

public class Card {
    String front, reverse;
    LocalDateTime due;

    public Card(String front, String reverse, LocalDateTime due) {
        this.front = front;
        this.reverse = reverse;
        this.due = due;
    }

    @Override
    public String toString() {
        return "\"" + front + "\",\"" + reverse + "\",\"" + (this.due != null ? this.due.toString() : "") + "\"\n";
    }
}
