import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * Класс с месячным отчотом, содержащем данные о доходах и расходах
 * в рамках одного календарного месяца
 */
public class MonthlyReport {

    public int month;
    String[] monthName = new String[]{"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль",
            "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    HashMap<String,MonthlyReportData> monthData = new HashMap<>();

    public MonthlyReport(int month) {
        this.month = month;
    }


    /**
     * Метод приводит данные из файла для дальнейшей обработки
     * @param monthPath  путь к файлу
     */
    void readMonthReport(String monthPath) {
        String content = readFileContentsOrNull(monthPath); //<< содержимое файла
        String[] lines = content.split("\r?\n"); //массив строк
        for (int i = 1; i < lines.length; i++){
            String line = lines[i]; // "Коньки,TRUE,50,2000"
            String[] parts = line.split(","); // "Коньки,TRUE,50,2000" -> ["Коньки", "TRUE", "50", "2000"]
            String itemName = (parts[0]);
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);


            if (!monthData.containsKey(itemName)) {
                monthData.put(itemName, new MonthlyReportData(itemName));
            }
            MonthlyReportData oneMonthData = monthData.get(itemName);
            oneMonthData.quantity = quantity;
            oneMonthData.isExpense = isExpense;
            oneMonthData.sumOfOne = sumOfOne;
        }
        System.out.println("Месячный отчет за " + monthName[month - 1] + " месяц считан.");
    }

    /**
     * Метод рассчитывает доходы за месяц
     */
    int findSumIncome () {
        int sumIncome = 0;
        for (String name : monthData.keySet()) {
            MonthlyReportData value = monthData.get(name);
            if (!value.isExpense) {
                sumIncome += value.sumOfOne * value.quantity;
            }
        }
        return sumIncome;
    }

    /**
     * Метод рассчитывает расходы за месяц
     * @return возвращает сумму расхдов
     */
    int findSumExpenses () {
        int sumExpenses = 0;
        for (String name : monthData.keySet()) {
            MonthlyReportData value = monthData.get(name);
            if (value.isExpense) {
                sumExpenses += value.sumOfOne * value.quantity;
            }
        }
        return sumExpenses;
    }

    /**
     * Метод находит самый прибыльный товар. Выводит название категории и сумму.
     */
    void findMostIncomeItem () {
        int maxSum = 0;
        String category = "";
        for (String name : monthData.keySet()) {
            int sum = 0;
            MonthlyReportData value = monthData.get(name);
            sum += value.sumOfOne * value.quantity;
            if (!value.isExpense && sum > maxSum) {
                maxSum = sum;
                category = name;
            }
        }
        System.out.println("Самая прибыльная категория: " + category + ", на сумму " + maxSum);
    }

    /**
     * Метод находит самую большую трату. Выодит название категории и траты.
     */
    void findMostExpenseItem () {
        int maxSum = 0;
        String category = "";
        for (String name : monthData.keySet()) {
            int sum = 0;
            MonthlyReportData value = monthData.get(name);
            sum += value.sumOfOne * value.quantity;
            if (value.isExpense && sum > maxSum) {
                maxSum = sum;
                category = name;
            }
        }
        System.out.println("Самая большая трата в категории: " + category + ", на сумму " + maxSum);
    }


    /**
     * Метод выводит всю необходимую статистику
     */
    void printStat() {
        System.out.println("Месяц " + monthName[month - 1]);
        findMostIncomeItem();
        findMostExpenseItem();
        System.out.print("\n");
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

