package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminSession extends JPanel{
    private JFrame frame;
    AdminSession() {

        //Fonts
        Font f1 = new Font(Font.SERIF, Font.BOLD, 60);
        Font f2 = new Font(Font.SERIF, Font.PLAIN, 20);
        Font f3 = new Font(Font.SERIF, Font.PLAIN, 30);

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel buttons = new JPanel(gbl);

        //Set layout for the content pane
        setLayout(new GridBagLayout());

        setBorder(new EmptyBorder(100, 10, 10, 10));

        //Set color of background
        this.setBackground(new Color(239, 223, 187));

        //Where the text is to be shown
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 0, 5, 0);

        JButton ajouterSeance = new JButton("Ajouter une seance");
        ajouterSeance.setBackground(new Color(59, 47, 47));
        ajouterSeance.setForeground(new Color(239, 223, 187));
        ajouterSeance.setFont(f3);

        JButton modifierSeance = new JButton("Modifier une seance");
        modifierSeance.setBackground(new Color(59, 47, 47));
        modifierSeance.setForeground(new Color(239, 223, 187));
        modifierSeance.setFont(f3);

        JButton suprimerSeance = new JButton("Suprimer une seance");
        suprimerSeance.setBackground(new Color(59, 47, 47));
        suprimerSeance.setForeground(new Color(239, 223, 187));
        suprimerSeance.setFont(f3);

        JButton ajouterFilm = new JButton("Ajouter un film");
        ajouterFilm.setBackground(new Color(59, 47, 47));
        ajouterFilm.setForeground(new Color(239, 223, 187));
        ajouterFilm.setFont(f3);

        JButton modifierFilm = new JButton("Modifier un film");
        modifierFilm.setBackground(new Color(59, 47, 47));
        modifierFilm.setForeground(new Color(239, 223, 187));
        modifierFilm.setFont(f3);

        JButton suprimerFilm = new JButton("Suprimer un film");
        suprimerFilm.setBackground(new Color(59, 47, 47));
        suprimerFilm.setForeground(new Color(239, 223, 187));
        suprimerFilm.setFont(f3);

        //button to go back to menu
        JButton close = new JButton("Back to main menu");
        close.setBackground(new Color(59, 47, 47));
        close.setForeground(new Color(239, 223, 187));
        close.setFont(f3);

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(59, 47, 47));
        exit.setForeground(new Color(239, 223, 187));
        exit.setFont(f3);

        /*Ajout d'un film*/
        JTextField film_name = new JTextField("Nom du film");
        film_name.setForeground(new Color(59, 47, 47));
        film_name.setFont(f2);
        film_name.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                film_name.setText("");
            }
        });

        JTextField film_genre = new JTextField("Genre du film");
        film_genre.setForeground(new Color(59, 47, 47));
        film_genre.setFont(f2);
        film_genre.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                film_genre.setText("");
            }
        });

        JTextField film_durée = new JTextField("Durée du film");
        film_durée.setForeground(new Color(59, 47, 47));
        film_durée.setFont(f2);
        film_durée.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                film_durée.setText("");
            }
        });

        JTextField date_sortie = new JTextField("Date de sortie du film");
        date_sortie.setForeground(new Color(59, 47, 47));
        date_sortie.setFont(f2);
        date_sortie.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                date_sortie.setText("");
            }
        });

        JTextField director_film = new JTextField("Realisateur du film");
        director_film.setForeground(new Color(59, 47, 47));
        director_film.setFont(f2);
        director_film.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                director_film.setText("");
            }
        });

        JTextField film_image = new JTextField("URL de l'affiche");
        film_image.setForeground(new Color(59, 47, 47));
        film_image.setFont(f2);
        film_image.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                film_image.setText("");
            }
        });

        JButton back_to_admin_menu = new JButton("Back to admin menu");
        back_to_admin_menu.setBackground(new Color(59, 47, 47));
        back_to_admin_menu.setForeground(new Color(239, 223, 187));
        back_to_admin_menu.setFont(f3);


        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        ajouterSeance.addActionListener(e0 -> {
        });

        modifierSeance.addActionListener(e1 -> {
        });

        suprimerSeance.addActionListener(e2 -> {
        });

        ajouterFilm.addActionListener(e3 -> {
        });

        modifierFilm.addActionListener(e4 -> {
        });

        suprimerFilm.addActionListener(e5 -> {
        });
/*

        close.addActionListener(e6 -> {
            this.adminMenu.menu();
            this.adminMenu.setVisible(false);
            this.adminMenu.getMenu().setVisible(true);
        });

        back_to_admin_menu.addActionListener(e7 -> {
            this.adminMenu.AM();
            this.adminMenu.setVisible(false);
        });
*/
        exit.addActionListener(e8 -> System.exit(0));

        film_name.addActionListener(e9 ->{

        });

        film_genre.addActionListener(e10 ->{

        });

        film_durée.addActionListener(e11 ->{

        });

        date_sortie.addActionListener(e12 ->{

        });

        director_film.addActionListener(e13 ->{

        });

        film_image.addActionListener(e14 ->{

        });

        buttons.setBackground(new Color(239, 223, 187));
/*
        switch (this.adminMenu.getChoix()){
            case 1:
                buttons.add(a, gbc);
                buttons.add(b, gbc);
                buttons.add(ajouterSeance, gbc);
                buttons.add(modifierSeance, gbc);
                buttons.add(suprimerSeance, gbc);
                buttons.add(ajouterFilm, gbc);
                buttons.add(modifierFilm, gbc);
                buttons.add(suprimerFilm, gbc);

                buttons.add(c,gbc);

                buttons.add(close, gbc);
                buttons.add(exit, gbc);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
        }
*/
        this.add(buttons, gbc);

    }
}
