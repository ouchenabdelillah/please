
package hotel.tableModel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


/**
 *
 * @author Faysal
 */
public class CustomCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

     
      
        if(column == 0)
        {
            
            setForeground(table.getTableHeader().getForeground());
            setBackground(Color.LIGHT_GRAY);
            setFont(table.getTableHeader().getFont());
        }
        else
        {
            setBackground(table.getBackground());
        }
        
        
        if (value.equals(">"))
        {
            c.setBackground(new java.awt.Color(0, 0, 255));
        }
        else 
        {
            c.setBackground(table.getBackground());
        }
        

        return c;
    }

}
