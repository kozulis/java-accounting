import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport;
        CompareReports compareReports = new CompareReports();
        String yearPath = "resources/y.2021.csv";

        HashMap<Integer, MonthlyReport> monthlyReportsList = new HashMap<>();

        while (true) {
            printMenu();
            String userInput = scanner.nextLine();

            if (userInput.equals("q")) {   /* Завершение работы при нажатии символа"q" */
                System.out.println("Выход");
                break;

            } else if (Integer.parseInt(userInput) == 1) {
                for (int month = 1; month <= 3; month++) {
                    monthlyReport = new MonthlyReport(month);
                    monthlyReport.readMonthReport("resources/m.20210" + month + ".csv");
                    monthlyReportsList.put(month, monthlyReport);
                }

            } else if (Integer.parseInt(userInput) == 2) {
                yearlyReport.readYearReport(yearPath);


            } else if (Integer.parseInt(userInput) == 3) {
                compareReports.compareReports1(yearlyReport, monthlyReportsList);

            } else if (Integer.parseInt(userInput) == 4) {
                if(!monthlyReportsList.isEmpty()) {
                    for (MonthlyReport monthReport : monthlyReportsList.values()) {
                        monthReport.printStat();
                    }
                } else {
                    System.out.println("Ежемесячные отчеты не считаны. Считайте отчеты.\n");
                }

            } else if (Integer.parseInt(userInput) == 5) {
                yearlyReport.printStat();

            } else {
                System.out.println("Введена несуществующая команда\n");
            }
        }
    }

    /**
     * Метод вывода интерфейса для работы с программой
     */
    public static void printMenu() {
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.print("Введите номер пункта программы: ");
    }
}

