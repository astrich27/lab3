import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer {
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private String needle = null;
    private DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();

    public GornerTableCellRenderer() {

        formatter.setMaximumFractionDigits(5);
        // Не использовать группировку (например, "1000", а не "1,000")
        formatter.setGroupingUsed(false);

        // Установить точку как разделитель дробной части
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);

        panel.add(label);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int col) {
        // Если столбец 2 (булевский), вернуть стандартный рендеринг
        if (value instanceof Boolean) {
            return table.getDefaultRenderer(Boolean.class).getTableCellRendererComponent(table, value, isSelected,
                    hasFocus, row, col);
        }

        // Преобразовать double в строку с помощью форматировщика
        String formattedDouble = formatter.format(value);
        label.setText(formattedDouble);

        if (col == 1 && value instanceof Double) {
            double doubleValue = (Double) value;
            int integerPart = (int) Math.floor(doubleValue);

            if (integerPart % 2 == 0) {

                panel.setBackground(Color.GREEN);
            } else {

                panel.setBackground(Color.RED);
            }
        } else {

            panel.setBackground(Color.WHITE);
        }


        if (col == 1 && needle != null && needle.equals(formattedDouble)) {
            panel.setBackground(Color.YELLOW);
        }

        return panel;
    }

    public void setNeedle(String needle) {
        this.needle = needle;
    }
}