package com.daojia.toSql.jframe.component;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GeneratorButton extends JPanel{
	
	
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GeneratorButton());
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public GeneratorButton() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel bp = new JPanel() {
            public Dimension getMaximumSize() {
                return new Dimension(getPreferredSize().width, super.getMaximumSize().height);
            }
        };
        bp.setLayout(new BoxLayout(bp, BoxLayout.Y_AXIS));

        bp.add(createComponentDialogButton());
    }
	
    private JButton createComponentDialogButton() {
        Action a = new AbstractAction("demo") {
            public void actionPerformed(ActionEvent e) {
                // In a ComponentDialog, you can show as many message components and
                // as many options as you want:

                // Messages
                Object[] message = new Object[4];
                message[0] = "OptionPaneDemo.componentmessage";
                message[1] = new JTextField("OptionPaneDemo.componentmessage");

                JComboBox cb = new JComboBox();
                cb.addItem("OptionPaneDemo.component_cb1");
                cb.addItem("OptionPaneDemo.component_cb2");
                cb.addItem("OptionPaneDemo.component_cb3");
                message[2] = cb;

                message[3] = "OptionPaneDemo.componentmessage2";

                // Options
                String[] options = {
                        "OptionPaneDemo.component_op1",
                        "OptionPaneDemo.component_op2",
                        "OptionPaneDemo.component_op3",
                        "OptionPaneDemo.component_op4"
                };
                int result = JOptionPane.showOptionDialog(
                		GeneratorButton.this,                        // the parent that the dialog blocks
                        message,                                    // the dialog message array
                        "OptionPaneDemo.componenttitle", // the title of the dialog window
                        JOptionPane.DEFAULT_OPTION,                 // option type
                        JOptionPane.INFORMATION_MESSAGE,            // message type
                        null,                                       // optional icon, use null to use the default icon
                        options,                                    // options string array, will be made into buttons
                        options[3]                                  // option that should be made into a default button
                );
                switch (result) {
                    case 0: // yes
                        JOptionPane.showMessageDialog(GeneratorButton.this, "OptionPaneDemo.component_r1");
                        break;
                    case 1: // no
                        JOptionPane.showMessageDialog(GeneratorButton.this, "OptionPaneDemo.component_r2");
                        break;
                    default:
                        break;
                }

            }
        };
        return createButton(a);
    }

    private JButton createButton(Action a) {
        JButton b = new JButton() {
            public Dimension getMaximumSize() {
                int width = Short.MAX_VALUE;
                int height = super.getMaximumSize().height;
                return new Dimension(width, height);
            }
        };
        // setting the following client property informs the button to show
        // the action text as it's name. The default is to not show the
        // action text.
        b.putClientProperty("displayActionText", Boolean.TRUE);
        b.setAction(a);
        // b.setAlignmentX(JButton.CENTER_ALIGNMENT);
        return b;
    }

}
