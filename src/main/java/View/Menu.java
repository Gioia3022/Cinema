
package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JPanel implements ActionListener {
    private Controller.Menu menu;
    private JFrame frame;

    public Menu(Controller.Menu menu, JFrame frame) {
        this.menu = menu;
        this.frame = frame;
        //We call the menu
        //Fonts
        Font f1 = new Font(Font.SERIF, Font.BOLD, 60);
        Font f3 = new Font(Font.SERIF, Font.PLAIN, 35);

        //Add button to layout
        JButton access_as_guest = new JButton("Access as Guest");
        access_as_guest.setBackground(new Color(59, 47, 47));
        access_as_guest.setForeground(new Color(239, 223, 187));
        access_as_guest.setFont(f3);

        JButton access_as_admin = new JButton("Access as Admin");
        access_as_admin.setBackground(new Color(59, 47, 47));
        access_as_admin.setForeground(new Color(239, 223, 187));
        access_as_admin.setFont(f3);

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(59, 47, 47));
        exit.setForeground(new Color(239, 223, 187));
        exit.setFont(f3);

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();


        //Set layout for the content pane
        setLayout(new GridBagLayout());

        setBorder(new EmptyBorder(150, 10, 10, 10));

        //Set color of background
        this.setBackground(new Color(239, 223, 187));

        //Where the text is to be shown
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.SOUTH;

        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 0, 5, 0);

        //Add Title
        JLabel menuTitle = new JLabel(" Cinéma Les Halles");
        menuTitle.setForeground(Color.DARK_GRAY);
        menuTitle.setFont(f1);
        add(menuTitle, gbc);

        //Making the layout prettier by adding spaces, ect.

        JLabel line = new JLabel("__________________");
        line.setForeground(Color.DARK_GRAY);
        line.setFont(f1);
        add(line, gbc);

        Label h = new Label("");
        add(h, gbc);


        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Add actionListener for each button
        //AFFICHAGE GUEST
        access_as_guest.addActionListener(e1 -> {
            this.menu.guest();
            this.menu.setVisible(false);
            this.menu.getGuest_controller().setVisible(true);
        });
        //AFFICHAGE ADMIN
        access_as_admin.addActionListener(e2 -> {
            this.menu.admin();
            this.menu.setVisible(false);
            this.menu.getAdmin_controller().setVisible(true);
        });

        exit.addActionListener(e3 -> System.exit(0));

        JPanel buttons = new JPanel(gbl);

        buttons.setBackground(new Color(239, 223, 187));

        //Add buttons to JPanel
        buttons.add(access_as_guest, gbc);
        buttons.add(access_as_admin, gbc);
        buttons.add(exit, gbc);


        add(buttons, gbc);

        JLabel line1 = new JLabel("__________________");
        line1.setForeground(Color.DARK_GRAY);
        line1.setFont(f1);
        add(line1, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
