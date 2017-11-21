package singletons;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class Decorator {
	
	public static void setBoldWithSize(JComponent comp, int size) {
		Font font = comp.getFont();
		comp.setFont(new Font(font.getName(), Font.BOLD, size));
	}
	
	public static void setFontSize(JComponent comp, int size) {
		Font font = comp.getFont();
		comp.setFont(new Font(font.getName(), Font.PLAIN, size));
	}
}
