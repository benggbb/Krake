import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Gui {
	JFrame win;

	JScrollPane sp;
	
	JButton run;

	JTextArea tf = new JTextArea();
	JMenuBar jb;
	

	public Gui() {
		tf.setBackground(new Color(20, 20, 20));
		tf.setForeground(new Color(255, 255, 255));

		run = new JButton("run");
		run.setBackground(new Color(255, 255, 255));
		run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BKrake.Start(tf.getText());
			}
		});

		jb = new JMenuBar();
		jb.add(run);

		win = new JFrame("IDE");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setSize(800, 600);
		win.getContentPane().setBackground(new Color(20, 20, 20));

		sp = new JScrollPane(tf);
		sp.setUI(null);
		win.add(sp);
		win.setJMenuBar(jb);
		win.setVisible(true);
	}

}