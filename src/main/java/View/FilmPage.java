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

        JLabel filmName= new JLabel("Nom du film: "+this.filmPage.getFilm().getFilmName());
        JLabel filmGenre= new JLabel("Genre: "+this.filmPage.getFilm().getFilmGenre());
        JLabel filmLength= new JLabel("Duree: "+ this.filmPage.getFilm().getDuration());
        JLabel filmRelease= new JLabel("Annee de sortie: "+ this.filmPage.getFilm().getFilmRelease());
        JLabel filmDirector= new JLabel("Realisateur: "+this.filmPage.getFilm().getDirector());

        JButton ticket = new JButton("Get ticket");
        ticket.setBackground(new Color(59,47,47));
        ticket.setForeground(new Color(239,223,187));
        ticket.setFont(f3);

        JButton close = new JButton("Selection de films");
        close.setBackground(new Color(59, 47, 47));
        close.setForeground(new Color(239, 223, 187));
        close.setFont(f3);

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(59, 47, 47));
        exit.setForeground(new Color(239, 223, 187));
        exit.setFont(f3);

        ticket.addActionListener(e0->{
        });
        close.addActionListener(e2 -> {
            this.filmPage.film();
            this.filmPage.setVisible(false);
            this.filmPage.getFilm_c().setVisible(true);
        });
        exit.addActionListener(e3 -> System.exit(0));

        buttons.setBackground(new Color(239, 223, 187));

        //Add buttons to JPanel
        buttons.add(filmName, gbc);
        buttons.add(filmGenre, gbc);
        buttons.add(filmLength,gbc);
        buttons.add(filmRelease,gbc);
        buttons.add(filmDirector,gbc);
        buttons.add(ticket,gbc);
        buttons.add(close,gbc);
        buttons.add(exit, gbc);

        this.add(buttons,gbc);
    }
}
