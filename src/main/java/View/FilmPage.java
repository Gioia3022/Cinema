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

    public FilmPage(Controller.FilmPage ca, JFrame f) {
        this.filmPage = ca;
        this.frame = f;

        Font f1 = new Font(Font.SERIF, Font.BOLD, 60);
        Font f2 = new Font(Font.SERIF, Font.PLAIN, 20);
        Font f3 = new Font(Font.SERIF, Font.PLAIN, 30);

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints gbc_1 = new GridBagConstraints();
        GridBagConstraints gbc_2 = new GridBagConstraints();
        GridBagConstraints gbc_3 = new GridBagConstraints();

        JPanel title = new JPanel(gbl);
        JPanel imageP = new JPanel(gbl);
        JPanel buttons = new JPanel(gbl);
        JPanel info = new JPanel(gbl);
        JPanel seance = new JPanel(gbl);

        //Set layout for the content pane
        setLayout(new GridBagLayout());

        setBorder(new EmptyBorder(150, 10, 10, 10));

        //Set color of background
        this.setBackground(new Color(239, 223, 187));

        //Where the text is to be shown
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc_1.gridwidth = GridBagConstraints.WEST;
        gbc_2.gridwidth = GridBagConstraints.SOUTHEAST;
        gbc_3.gridwidth = GridBagConstraints.SOUTHEAST;

        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 10, 0);

        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc_1.weightx = 0;
        gbc_1.weighty = 1;
        gbc_1.insets = new Insets(0, 50, 5, 0);

        gbc_1.anchor = GridBagConstraints.WEST;
        gbc_1.fill = GridBagConstraints.WEST;

        gbc_2.weightx = 0;
        gbc_2.weighty = 1;
        gbc_2.insets = new Insets(0, 0, 50, 0);

        gbc_2.anchor = GridBagConstraints.SOUTH;
        gbc_2.fill = GridBagConstraints.HORIZONTAL;

        gbc_3.weightx = 0;
        gbc_3.weighty = 1;
        gbc_3.insets = new Insets(0, 10, 10, 0);

        gbc_3.anchor = GridBagConstraints.SOUTH;
        gbc_3.fill = GridBagConstraints.HORIZONTAL;

        String[] fil = new String[filmPage.getSession().getDateArrayList().size()];

        //AFFICHAGE DES SESSIONS DANS UN COMBOBOX
        for (int i = 0; i < fil.length; i++) {
            fil[i] = String.valueOf(filmPage.getSession().getDateArrayList().get(i));
        }

        JComboBox list = new JComboBox(fil);
        list.setBounds(50, 50, 90, 20);
        list.setFont(f2);

        JLabel filmName = new JLabel(this.filmPage.getFilm().getFilmName());
        filmName.setFont(f1);

        String[] nbrTicket = new String[this.filmPage.getUsable_seets()];
        for (int i = 0; i < nbrTicket.length; i++) {
            nbrTicket[i] = String.valueOf(i + 1);
        }

        JComboBox nT = new JComboBox(nbrTicket);
        list.setBounds(50, 50, 90, 20);
        list.setFont(f2);

        Image image = null;
        try {
            URL url = new URL(this.filmPage.getFilm().getImage());
            image = ImageIO.read(url);
        } catch (IOException e) {
        }
        int newWidth = 440, newHeight = 600;

        //TOUTES LES INFORMATIONS DE LA CLASSE FILMS AFFICHES
        JLabel filmImage = new JLabel(new ImageIcon(image.getScaledInstance(newWidth, newHeight, Image.SCALE_FAST)));
        filmImage.setFont(f3);
        JLabel filmGenre = new JLabel("Genre : " + this.filmPage.getFilm().getFilmGenre());
        filmGenre.setFont(f3);
        JLabel filmLength = new JLabel("Durée : " + this.filmPage.getFilm().getDuration());
        filmLength.setFont(f3);
        JLabel filmRelease = new JLabel("Année de sortie : " + this.filmPage.getFilm().getFilmRelease());
        filmRelease.setFont(f3);
        JLabel filmDirector = new JLabel("Réalisateur : " + this.filmPage.getFilm().getDirector());
        filmDirector.setFont(f3);

        JLabel choixSceance = new JLabel("Choisir un jour et une séance : ");
        choixSceance.setFont(f3);

        JLabel choixNbrDePlaces = new JLabel("Choisir le nombre de places : ");
        choixNbrDePlaces.setFont(f3);

        JButton ticket = new JButton("Achat");
        ticket.setBackground(new Color(59, 47, 47));
        ticket.setForeground(new Color(239, 223, 187));
        ticket.setFont(f3);

        JButton close = new JButton("Back");
        close.setBackground(new Color(59, 47, 47));
        close.setForeground(new Color(239, 223, 187));
        close.setFont(f3);

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(59, 47, 47));
        exit.setForeground(new Color(239, 223, 187));
        exit.setFont(f3);

        //CREATION D'UN TICKET AVEC LES INFORMATIONS INSCRITES -> AFFICHAGE DE LA FRAME ACHAT
        ticket.addActionListener(e0 -> {
            this.filmPage.newTicket((String) list.getItemAt(list.getSelectedIndex()), (String) nT.getItemAt(nT.getSelectedIndex()));
        });
        //RETOUR PAGE D'AVANT
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
        seance.setBackground(new Color(239, 223, 187));

        //Add buttons to JPanel
        title.add(filmName, gbc);
        info.add(filmGenre, gbc);
        info.add(filmLength, gbc);
        info.add(filmRelease, gbc);
        info.add(filmDirector, gbc);
        imageP.add(filmImage, gbc_1);
        info.add(choixSceance, gbc);
        info.add(list, gbc);
        info.add(choixNbrDePlaces, gbc);
        info.add(nT, gbc);
        buttons.add(ticket, gbc_3);
        buttons.add(close, gbc_3);
        buttons.add(exit, gbc_3);

        this.add(title, gbc);
        this.add(imageP, gbc_1);
        this.add(info, gbc_1);
        //this.add(list,gbc_1);
        this.add(buttons, gbc_2);
    }
}
