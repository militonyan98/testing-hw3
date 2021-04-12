import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCreditCard {
    @Test
    void testCreditExpiration()
    {
        assertEquals(true, getExpiredCreditCard().isExpired());
    }

    @Test
    void testCreditExpirationFalse()
    {
        assertEquals(false, getNormalCreditCard().isExpired());
    }

    @Test
    void canWithdrawPositive()
    {
        assertEquals(true, getNormalCreditCard().withdrawATM(100));
    }

    @Test
    void canWithdrawNegative()
    {
        assertEquals(false, getNormalCreditCard().withdrawATM(150));
    }

    //TODO: add expired credit card withdrawal both positive and negative

    @Test
    void expiredCardWithdrawalMoreThanBalance()
    {
        assertEquals(false, getExpiredCreditCard().withdrawATM(200));
    }

    @Test
    void expiredCardWithdrawalLessThanBalance()
    {
        assertEquals(false, getExpiredCreditCard().withdrawATM(119));
    }

    @Test
    void canGetCreditPositive()
    {
        assertEquals(true, getNormalCreditCardWithCreditLimit().credit(140));
    }

    @Test
    void canGetCreditNegative()
    {
        assertEquals(false, getNormalCreditCardWithCreditLimit().credit(1600));
    }

    @Test
    void expiredCardTransferFromMoreThanBalance()
    {
        assertEquals(false, getExpiredCreditCard().transferFrom(302.1));
    }

    @Test
    void expiredCardTransferFromLessThanBalance()
    {
        assertEquals(false, getExpiredCreditCard().transferFrom(10.0));
    }

    @Test
    void canTransferFromNegative()
    {
        assertEquals(false, getNormalCreditCard().transferFrom(121.0));
    }

    @Test
    void canTransferFromPositive()
    {
        assertEquals(true, getNormalCreditCard().transferFrom(12.0));
    }

    @Test
    void expiredCardTransferTo()
    {
        assertEquals(false, getExpiredCreditCard().transferTo(10000.0));
    }

    @Test
    void normalCardTransferTo()
    {
        assertEquals(true, getNormalCreditCard().transferTo(10));
    }

    //TODO add expired credit card credit, and credit line disabled credit testing
    @Test
    void expiredCardWithoutCreditLineWithMoreThanCreditLimitCredit()
    {
        assertEquals(false, getExpiredCreditCard().credit(200.0));
    }

    @Test
    void expiredCardWithoutCreditLineWithLessThanCreditLimitCredit()
    {
        assertEquals(false, getExpiredCreditCard().credit(10.0));
    }

    @Test
    void expiredCardWithCreditLineWithMoreThanCreditLimitCredit()
    {
        assertEquals(false, getExpiredCreditCardWithCreditLineAndCreditLimit().credit(35.0));
    }

    @Test
    void expiredCardWithCreditLineWithLessThanCreditLimitCredit()
    {
        assertEquals(false, getExpiredCreditCardWithCreditLineAndCreditLimit().credit(2.0));
    }

    @Test
    void normalCreditCardWithCreditLineWithMoreThanCreditLimit()
    {
        assertEquals(false, getNormalCreditCardWithCreditLimit().credit(235.0));
    }

    @Test
    void normalCreditCardWithCreditLineWithLessThanCreditLimit()
    {
        assertEquals(true, getNormalCreditCardWithCreditLimit().credit(25.0));
    }

    @Test
    void normalCreditCardWithoutCreditLineWithMoreThenCreditLimit()
    {
        assertEquals(false, getNormalCreditCardWithoutCreditLineAndCreditLimit().credit(230.01));
    }

    @Test
    void normalCreditCardWithoutCreditLineWithLessThenCreditLimit()
    {
        assertEquals(false, getNormalCreditCardWithoutCreditLineAndCreditLimit().credit(29.99));
    }


    //
    static CreditCard getExpiredCreditCard()
    {
        return new CreditCard("5555-5555-5555-5555",120.0,
                "Jane Doe", new GregorianCalendar(2014, Calendar.FEBRUARY, 0).getTime(),
                false, 30.0);
    }

    static CreditCard getNormalCreditCard()
    {
        return new CreditCard("5555-5555-5555-5555",120.0,
                "Jane Doe", new GregorianCalendar(2024, Calendar.FEBRUARY, 0).getTime(),
                false, 0.0);
    }

    static CreditCard getNormalCreditCardWithCreditLimit()
    {
        return new CreditCard("5555-5555-5555-5555",120.0,
                "Jane Doe", new GregorianCalendar(2024, Calendar.FEBRUARY, 0).getTime(),
                true, 200.0);
    }

    static CreditCard getExpiredCreditCardWithCreditLineAndCreditLimit()
    {
        return new CreditCard("5555-5555-5555-5555",120.0,
                "Jane Doe", new GregorianCalendar(2014, Calendar.FEBRUARY, 0).getTime(),
                true, 30.0);
    }

    static CreditCard getNormalCreditCardWithoutCreditLineAndCreditLimit()
    {
        return new CreditCard("5555-5555-5555-5555",120.0,
                "Jane Doe", new GregorianCalendar(2022, Calendar.FEBRUARY, 0).getTime(),
                false, 30.0);
    }

}
