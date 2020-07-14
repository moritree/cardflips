package moritree;
import java.time.LocalDateTime;

public class Card {
    String front, reverse;
    LocalDateTime due;

    public Card(String front, String reverse, LocalDateTime due) {
        assert front != null && reverse != null;

        this.front = front;
        this.reverse = reverse;
        this.due = due;
    }

    public Card(Card card) {
        this.front = card.front;
        this.reverse = card.reverse;
        this.due = card.due;
    }

    @Override
    public String toString() {
        return "`" + front + "`,`" + reverse + "`,`" + (this.due != null ? this.due.toString() : "") + "`";
    }
}
