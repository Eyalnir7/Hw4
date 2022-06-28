public class DateCalculator {
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH =3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;
    private static final int DAYS_IN_MONTH1 = 31;
    private static final int DAYS_IN_MONTH2 = 30;
    private static final int DAYS_IN_FEBRUARY1 = 28;
    private static final int DAYS_IN_FEBRUARY2 = 29;
    public static Date addToDate(Date date, int num) {
        if(num == 0)
            return date;
        int newDay = date.getDay() + num;
        Boolean increment = num > 0;
        int daysInMonth = daysInMonth(date.getYear(), date.getMonth());
        if(newDay > daysInMonth || newDay<1){
            return addToDate(changeMonth(date, increment),
                    num - daysUntilNextMonth(daysInMonth, date.getDay(), increment));
        }
        return new Date(newDay, date.getMonth(),date.getYear());
    }

    private static int daysInMonth(int year, int month){
        switch(month){
            case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER: return 31;
            case FEBRUARY:
                if(year%4 == 0 && year%100!=0 || year%400 == 0){
                    return DAYS_IN_FEBRUARY2;
                }
                return DAYS_IN_FEBRUARY1;
            case APRIL, JUNE, SEPTEMBER, NOVEMBER: return 30;
        }
        return 0; // not supposed to happen
    }

    private static Date changeMonth(Date date, Boolean increment){
        int month = date.getMonth();
        int year = date.getYear();
        if(increment){
            if(date.getMonth()==12){
                return new Date(0, 0, year+1);
            }
            return new Date(0, month+1, year);
        }
        if(month==1){
            return new Date(31, 12, year-1);
        }
        return new Date(daysInMonth(year, month-1), month-1, year);
    }

    private static int daysUntilNextMonth(int daysInMonth, int currentDay, Boolean increment){
        if(increment){
            return daysInMonth - currentDay;
        }
        return (-1)*currentDay;
    }
}