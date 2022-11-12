import java.util.HashMap;

public class CompareReports {

    String[] monthName = new String[]{"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль",
            "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    /**
     * метод сверяет данные годового отчета с даннымм ежемесячных отчетов
     */
    void compareReports1(YearlyReport yearlyReport, HashMap<Integer, MonthlyReport> monthlyReportsList) {   /*допилил считывание и сверку отчетов по ключу мапы*/
        int errorCount = 0;
        if (yearlyReport.monthsData.isEmpty() || monthlyReportsList.isEmpty()) {
            System.out.println("Для начала сверки необходимо считать все отчеты\n");
        } else {
            for (Integer number: monthlyReportsList.keySet()) {
                int yearlyExpensesByMonth = yearlyReport.monthsData.get(number).expenses;
                int monthlyExpenses = monthlyReportsList.get(number).findSumExpenses();

                int yearlyIncomeByMonth = yearlyReport.monthsData.get(number).income;
                int monthlyIncome = monthlyReportsList.get(number).findSumIncome();

                if (yearlyExpensesByMonth != monthlyExpenses || yearlyIncomeByMonth != monthlyIncome) {
                    System.out.println("Обнаружено несоответсвие данных за месяц " + monthName[number - 1]);
                    errorCount ++;
                }
            }
        }
        if (errorCount == 0) {
            System.out.println("Ошиибок при сверке отчетов не обнаружено.\n");
        }
    }
}


