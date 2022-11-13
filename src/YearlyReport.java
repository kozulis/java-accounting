import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * Класс для работы с годовым отчётом
 */
public class YearlyReport {
    String[] monthName = new String[]{"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль",
            "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    public int year = 2021;
    public HashMap<Integer, YearlyReportMonth> monthsData = new HashMap<>();


    /**
     * Метод приводит данные из файла для дальнейшей обработки
     * @param yearPath  расположение файла
     */
    void readYearReport(String yearPath) {
        String content = readFileContentsOrNull(yearPath); //<< содержимое файла
        String[] lines = content.split("\r?\n"); //массив строк
        for (int i = 1; i < lines.length; i++){
            String line = lines[i]; // "01,350000,true"
            String[] parts = line.split(","); // "01,350000,true" -> ["01", "350000", "true"]
            int month = Integer.parseInt(parts[0]);
            int sum = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            if (!monthsData.containsKey(month)) {
                monthsData.put(month, new YearlyReportMonth(month));
            }
            YearlyReportMonth oneMonthData = monthsData.get(month);
            if (isExpense) {
                oneMonthData.expenses += sum;
            } else {
                oneMonthData.income += sum;
            }
        }
        System.out.println("Годовой отчет считан.\n");
    }

    /**
     * Метод расчитывает прибыль за каждый месяц в годовом отчете
     */
    void findProfit() {
        System.out.println("Отчетный год: " + year);
        for (Integer oneMonthData : monthsData.keySet()) {
            int profit = 0;
            YearlyReportMonth value = monthsData.get(oneMonthData);
            profit += value.income - value.expenses;
            System.out.println("Прибыль за " + monthName[oneMonthData - 1] + ": " + profit);
        }
    }

    /**
     * Метод рассчитвыет средний расход за месяцы
     */
    void findAverageExpenses() {
        int averageExpenses = 0;
        for (Integer oneMonthData : monthsData.keySet()) {
            YearlyReportMonth value = monthsData.get(oneMonthData);
            averageExpenses += value.expenses;
        }
        System.out.println("Средний расход за все месяцы в году: " + averageExpenses/monthsData.size());
    }

    /**
     * Метод рассчитвыет средний доход за месяцы
     */
    void findAverageIncome() {
        int averageExpenses = 0;
        for (Integer oneMonthData : monthsData.keySet()) {
            YearlyReportMonth value = monthsData.get(oneMonthData);
            averageExpenses += value.income;
        }
        System.out.println("Средний доход за все месяцы в году: " + averageExpenses/monthsData.size());
    }

    /**
     * Метод выводит всю необходимую статистику
     */
    void printStat() {
        if (!monthsData.isEmpty()) {
            findProfit();
            findAverageExpenses();
            findAverageIncome();
            System.out.print("\n");
        } else {
            System.out.println("Годовой отчет не считан. Считайте годовой отчет.\n");
        }
    }

    /**
     * Метод считывает файл
     */
    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}

