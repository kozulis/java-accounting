import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static final int YEAR_FOR_REPORT = 2021;

    public static void main(String[] args) {

        String yearPath = "resources/y." + YEAR_FOR_REPORT + ".csv";
        Scanner scanner =new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport();
        HashMap<Integer, MonthlyReport> monthlyReportsList = new HashMap<>();
        MonthlyReport monthlyReport;
        CompareReports compareReports = new CompareReports();

        while (true) {
            printMenu();
            String userInput = scanner.nextLine();

            if (userInput.equals("q")) {   /* Завершение работы при нажатии символа"q" */
                System.out.println("Выход");
                break;

            } else if (Integer.parseInt(userInput) == 1) {                       /* считывает файлы с определенным названием, в качестве номера */
                File monthsList1 = new File("resources");                /* месяца вычленяет из файла данных цифру */
                for (String monthsName: monthsList1.list()) {
                    String substring = monthsName.substring(6, 8);
                    String monthNameFull = "m." + YEAR_FOR_REPORT + substring;
                    if (monthsName.contains(monthNameFull)) {
                        int monthNumber = Integer.parseInt(substring);
                        monthlyReport = new MonthlyReport(monthNumber);

                        monthlyReport.readMonthReport("resources/" + monthNameFull + ".csv");
                        monthlyReportsList.put(monthNumber, monthlyReport);
                    }
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

