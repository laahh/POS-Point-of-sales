/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.swing.JTable;


public class Model {
    public static void table(JTable table) {
        table.getTableHeader().setFont(Attr.quicksandBold(14));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(Attr.blue);
        table.getTableHeader().setForeground(Attr.white);
        table.setRowHeight(25);
        
    }
}
