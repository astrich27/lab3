import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableMode extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public GornerTableMode(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    public int getColumnCount() {
        return 3; // Три столбца
    }

    public int getRowCount() {
        return new Double(Math.ceil((to - from) / step)).intValue() + 1;
    }

    public Object getValueAt(int row, int col) {
        double x = from + step * row;
        if (col == 0) {
            // Первый столбец: значение X
            return x;
        } else if (col == 1) {
            // Второй столбец: значение многочлена
            double result = coefficients[0];
            for (int i = 1; i < coefficients.length; i++) {
                result = result * x + coefficients[i];
            }
            return result;
        } else {
            // Третий столбец: проверка на две пары одинаковых цифр
            double result = coefficients[0];
            for (int i = 1; i < coefficients.length; i++) {
                result = result * x + coefficients[i];
            }
//            if (result >=2 && result <3){
//                return true;
//            }
//            return false;
            return hasTwoPairs(String.valueOf(result));
        }
    }

    // Проверка на две пары одинаковых цифр
    private boolean hasTwoPairs(String value) {
/*       if (value >=2 && value <3){
           return true;
       }
       return false;*/
         int pairs = 0;
        for (int i = 0; i < value.length() - 1; i++) {
            if (Character.isDigit(value.charAt(i))&& value.charAt(i) ==2/* value.charAt(i) == value.charAt(i + 1)*/) {
                pairs++;
                i++; // Пропускаем следующую цифру, чтобы пары не пересекались
            }
            if (pairs == 2) {
                return true;
            }
        }
        return false;
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X";
            case 1:
                return "Значение многочлена";
            default:
                return "Две пары";
        }
    }

    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
            case 1:
                return Double.class;
            default:
                return Boolean.class; // Флажки для третьего столбца
        }
    }
}