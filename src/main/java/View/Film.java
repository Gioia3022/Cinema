package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Film extends JPanel{
    private Controller.Film film;
    private JFrame frame;
   
    public Film(Controller.Film ca, JFrame f){
        this.film=ca;
        this.frame=f;
        //Fonts
        Font f1= new Font(Font.SERIF,  Font.BOLD, 60);
        Font f2= new Font(Font.SERIF, Font.PLAIN,  20);
        Font f3= new Font(Font.SERIF, Font.PLAIN,  30);
        Font f4= new Font(Font.SERIF, Font.BOLD, 30);

        GridBagLayout gbl= new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel buttons = new JPanel(gbl);

        //Set layout for the content pane
        setLayout(new GridBagLayout());

        setBorder(new EmptyBorder(170, 10, 10, 10));

        //Set color of background
        this.setBackground(new Color(239,223,187));

        //Where the text is to be shown
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 0, 5, 0);

        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Label a= new Label("      Film selection");
        a.setFont(f1);
        Label b= new Label("__________________");
        b.setFont(f1);
        Label c= new Label("");
        c.setFont(f2);

        JLabel l_b= new JLabel("Select film: ");
        l_b.setFont(f4);

        String[] fil= new String[film.getFilm().getNames().size()];

        for(int i = 0; i<fil.length; i++){
            fil[i]= film.getFilm().getNames().get(i);
        }

        JComboBox list= new JComboBox(fil);
        list.setBounds(50, 50,90,20);
        list.setFont(f2);

        JButton info = new JButton("Get info");
        info.setBackground(new Color(59,47,47));
        info.setForeground(new Color(239,223,187));
        info.setFont(f3);

        JButton close = new JButton("Menu Principal");
        close.setBackground(new Color(59, 47, 47));
        close.setForeground(new Color(239, 223, 187));
        close.setFont(f3);

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(59, 47, 47));
        exit.setForeground(new Color(239, 223, 187));
        exit.setFont(f3);

        info.addActionListener(e0->{
            this.film.get_info((String) list.getItemAt(list.getSelectedIndex()));
            this.film.filmPage();
            this.film.setVisible(false);
            this.film.getFilmPage().setVisible(true);
        });


        close.addActionListener(e2 -> {
            this.film.menu();
            this.film.setVisible(false);
            this.film.getMenu().setVisible(true);
        });

        exit.addActionListener(e4 -> System.exit(0));

        buttons.setBackground(new Color(239, 223, 187));

        //Add buttons to JPanel
        buttons.add(a, gbc);
        buttons.add(b, gbc);
        buttons.add(l_b,gbc);
        buttons.add(list,gbc);
        buttons.add(info,gbc);
        buttons.add(c, gbc);
        buttons.add(b, gbc);
        buttons.add(c, gbc);
        buttons.add(close, gbc);
        buttons.add(exit, gbc);

        this.add(buttons,gbc);
    }
}
