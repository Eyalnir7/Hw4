public class DateCalculator {
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
            case 1, 3, 5, 7, 8, 10, 12: return 31;
            case 2:
                if(year%4 == 0 && year%100!=0 || year%400 == 0){
                    return 29;
                }
                return 28;
            case 4, 6, 9, 11: return 30;
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

enum Months{
    JANUARY, FEBUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
}