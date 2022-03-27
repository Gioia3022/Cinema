package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FilmPage extends JPanel {
    private Controller.FilmPage filmPage;
    private JFrame frame;
    public FilmPage(Controller.FilmPage ca, JFrame f){
        this.filmPage=ca;
        this.frame=f;

        Font f1= new Font(Font.SERIF,  Font.BOLD, 40);
        Font f2= new Font(Font.SERIF, Font.PLAIN,  20);
        Font f3= new Font(Font.SERIF, Font.PLAIN,  30);

        GridBagLayout gbl= new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel buttons = new JPanel(gbl);

        //Set layout for the content pane
        setLayout(new GridBagLayout());

        setBorder(new EmptyBorder(50, 10, 10, 10));

        //Set color of background
        this.setBackground(new Color(239,223,187));

        //Where the text is to be shown
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 5, 0);

        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        System.out.println(this.filmPage.getFilm().getFilmName());
        Label filmName= new Label(this.filmPage.getFilm().getFilmName());
    }
}
