import java.util.HashMap;

public class CompareReports {

    String[] monthName = new String[]{"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль",
            "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    /**
     * метод сверяет данные годового отчета с даннымм ежемесячных отчетов
     */
    void compareReports1(YearlyReport yearlyReport, HashMap<Integer, MonthlyReport> monthlyReportsList) {
        int errorCount = 0;
        if (yearlyReport.monthsData.isEmpty() || monthlyReportsList.isEmpty()) {
            System.out.println("Для начала сверки необходимо считать все отчеты\n");
        } else {
            for(int i = 1; i <= monthlyReportsList.size() ; i++) {
                int yearlyExpensesByMonth = yearlyReport.monthsData.get(i).expenses;
                int monthlyExpenses = monthlyReportsList.get(i).findSumExpenses();

                int yearlyIncomeByMonth = yearlyReport.monthsData.get(i).income;
                int monthlyIncome = monthlyReportsList.get(i).findSumIncome();

                if (yearlyExpensesByMonth != monthlyExpenses || yearlyIncomeByMonth != monthlyIncome) {
                    System.out.println("Обнаружено несоответсвие данных за месяц " + monthName[i - 1]);
                    errorCount ++;
                }
            }
            if (errorCount == 0) {
                System.out.println("Ошиибок при сверке отчетов не обнаружено.\n");
            }
        }
    }
}

