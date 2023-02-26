package taskbackend.utils;

import br.ce.wcaquino.taskbackend.utils.DateUtils;
import org.junit.Test;
import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateUtilsTest {

    @Test
    public void deveRetornarTrueParaDataFuturas(){
        LocalDate date = LocalDate.of(2030, 01, 01);
        assertTrue(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void deveRetornarFalseParaDatasPassadas(){
        LocalDate date = LocalDate.of(2010, 01, 01);
        assertFalse(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void deveRetornarTrueParaDataAtual(){
        LocalDate date = LocalDate.now();
        assertFalse(DateUtils.isEqualOrFutureDate(date));
    }
}
