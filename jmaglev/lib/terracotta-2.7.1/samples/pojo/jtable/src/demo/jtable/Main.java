/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.jtable;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *  Description of the Class
 *
 *@author    Terracotta, Inc.
 */
class Main extends JFrame {
   private DefaultTableModel model;

   private Object[] tableHeader = {"Time", "Room A", "Room B", "Room C"};

   private static Object[][] tableData = {{" 9:00", "", "", ""},
         {"10:00", "", "", ""}, {"11:00", "", "", ""},
         {"12:00", "", "", ""}, {" 1:00", "", "", ""},
         {" 2:00", "", "", ""}, {" 3:00", "", "", ""},
         {" 4:00", "", "", ""}, {" 5:00", "", "", ""}};

   Main() {
      super("Table Demo");

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setDefaultLookAndFeelDecorated(true);

      model = new DefaultTableModel(tableData, tableHeader);

      JTable table = new JTable(model);
      table.setFont(new Font("Courier New", Font.PLAIN, 14));
      getContentPane().add(new JScrollPane(table));
      setSize(500, 200);
      setVisible(true);
   }

   public static void main(String[] args) {
      javax.swing.SwingUtilities.invokeLater(
               new Runnable() {
                  public void run() {
                     new Main();
                  }
               });
   }
}
