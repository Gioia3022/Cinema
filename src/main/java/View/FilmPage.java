package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

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

        GridBagLayout gbl_1= new GridBagLayout();
        GridBagConstraints gbc_1 = new GridBagConstraints();

        JPanel title= new JPanel(gbl);
        JPanel imageP= new JPanel(gbl_1);
        JPanel buttons = new JPanel(gbl_1);
        JPanel info= new JPanel(gbl_1);

        //Set layout for the content pane
        setLayout(new GridBagLayout());

        setBorder(new EmptyBorder(50, 10, 10, 10));

        //Set color of background
        this.setBackground(new Color(239,223,187));

        //Where the text is to be shown
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc_1.gridwidth = GridBagConstraints.WEST;
        gbc_1.anchor = GridBagConstraints.WEST;

        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 10, 0);

        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc_1.weightx = 0;
        gbc_1.weighty = 1;
        gbc_1.insets = new Insets(0, 10, 5, 0);

        gbc_1.anchor = GridBagConstraints.WEST;
        gbc_1.fill = GridBagConstraints.WEST;

        JLabel filmName= new JLabel(this.filmPage.getFilm().getFilmName());
        filmName.setFont(f1);

        Image image = null;
        try {
            URL url= new URL(this.filmPage.getFilm().getImage());
            image= ImageIO.read(url);
        } catch (IOException e) {
        }
        int newWidth=220, newHeight=300;
        JLabel filmImage= new JLabel(new ImageIcon(image.getScaledInstance(newWidth, newHeight, Image.SCALE_FAST)));
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
        imageP.setBackground(new Color(239, 223, 187));
        info.setBackground(new Color(239, 223, 187));
        title.setBackground(new Color(239, 223, 187));

        //Add buttons to JPanel
        title.add(filmName, gbc);
        info.add(filmImage, gbc_1);
        info.add(filmGenre, gbc_1);
        info.add(filmLength,gbc_1);
        info.add(filmRelease,gbc_1);
        info.add(filmDirector,gbc_1);
        buttons.add(ticket,gbc_1);
        buttons.add(close,gbc_1);
        buttons.add(exit, gbc_1);

        this.add(title,gbc);
        this.add(imageP,gbc);
        this.add(info,gbc);
        this.add(buttons,gbc);
    }
}
