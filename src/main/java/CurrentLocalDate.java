import java.time.LocalDate;

public class CurrentLocalDate {

    public LocalDate localDate() {

        LocalDate currentDate = LocalDate.now().plusDays(3);
        return currentDate;


    }



}
