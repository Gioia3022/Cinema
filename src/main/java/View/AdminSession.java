package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AdminSession extends JPanel{
    private JFrame frame;
    private Controller.AdminSession adminSession;
    public AdminSession(Controller.AdminSession as, JFrame f) {
        this.frame=f;
        this.adminSession=as;
        //Fonts
        Font f1 = new Font(Font.SERIF, Font.BOLD, 60);
        Font f2 = new Font(Font.SERIF, Font.PLAIN, 20);
        Font f3 = new Font(Font.SERIF, Font.PLAIN, 30);

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel buttons = new JPanel(gbl);

        //Set layout for the content pane
        setLayout(new GridBagLayout());

        setBorder(new EmptyBorder(200, 10, 10, 10));

        //Set color of background
        this.setBackground(new Color(239, 223, 187));

        //Where the text is to be shown
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 0, 5, 0);


        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel c = new JLabel(" ");
        c.setFont(f1);

        //SUPRESSION

        JLabel delete= new JLabel("Voulez-vous supprimer une des séances suivantes ? ");
        delete.setFont(f3);

        String[] fil= new String[this.adminSession.getSession().getDateArrayList().size()];

        for(int i = 0; i<fil.length; i++){
            fil[i]= this.adminSession.getSession().getDateArrayList().get(i);
        }

        JComboBox list= new JComboBox(fil);
        list.setBounds(50, 50,90,20);
        list.setFont(f2);

        //MODIFICATION

        JLabel modifier= new JLabel("Voulez-vous modifier une des séances des films suivant ? ");
        modifier.setFont(f3);

        String[] fil1 = new String[this.adminSession.getFilm().getNames().size()];

        for (int i = 0; i < fil1.length; i++) {
            fil1[i] = this.adminSession.getFilm().getNames().get(i);
        }

        JComboBox film = new JComboBox(fil1);
        film.setBounds(50, 50, 90, 20);
        film.setFont(f2);

        //MODIFICATION Partie 2
        JLabel mod_seance= new JLabel("Voulez-vous modifier une des séances suivantes ? Elles sont associées au film : "+ this.adminSession.getFilm().getFilmName());
        mod_seance.setFont(f3);

        String[] session = new String[this.adminSession.getSession().getDateArrayList().size()];

        for (int i = 0; i < session.length; i++) {
            session[i] = String.valueOf(this.adminSession.getSession().getDateArrayList().get(i));
        }

        JComboBox comboSession = new JComboBox(session);
        comboSession.setBounds(50, 50, 90, 20);
        comboSession.setFont(f2);

        JTextField new_session = new JTextField("Nouvelle session : ");
        new_session.setForeground(new Color(59, 47, 47));
        new_session.setFont(f2);
        new_session.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                new_session.setText("");
            }
        });

        JButton modifierSeance= new JButton("Modifier");
        modifierSeance.setBackground(new Color(59, 47, 47));
        modifierSeance.setForeground(new Color(239, 223, 187));
        modifierSeance.setFont(f3);

        //AJOUT

        JLabel create= new JLabel("Voulez-vous ajouter une nouvelle séance ? ");
        create.setFont(f3);

        JComboBox film2 = new JComboBox(fil1);
        film2.setBounds(50, 50, 90, 20);
        film2.setFont(f2);

        JTextField room = new JTextField("Numéro de la salle");
        room.setForeground(new Color(59, 47, 47));
        room.setFont(f2);
        room.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                room.setText("");
            }
        });

        JTextField date = new JTextField("Date de la séance");
        date.setForeground(new Color(59, 47, 47));
        date.setFont(f2);
        date.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                date.setText("");
            }
        });

        JButton addSeance= new JButton("Nouvelle séance");
        addSeance.setBackground(new Color(59, 47, 47));
        addSeance.setForeground(new Color(239, 223, 187));
        addSeance.setFont(f3);

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(59, 47, 47));
        exit.setForeground(new Color(239, 223, 187));
        exit.setFont(f3);

        //button to go back to menu
        JButton close = new JButton("Back");
        close.setBackground(new Color(59, 47, 47));
        close.setForeground(new Color(239, 223, 187));
        close.setFont(f3);

        list.addActionListener(e0->{
            adminSession.delete((String) list.getItemAt(list.getSelectedIndex()));
        });

        film.addActionListener(e1->{
            this.adminSession.m((String) film.getItemAt(film.getSelectedIndex()));
        });

        addSeance.addActionListener(e2->{
            this.adminSession.ajouter((String) film2.getItemAt(film2.getSelectedIndex()), room.getText(), date.getText());
        });

        close.addActionListener(e3-> {
            this.adminSession.menu();
            this.adminSession.setVisible(false);
            this.adminSession.getAdminMenu().setVisible(true);
        });

        exit.addActionListener(e4->System.exit(0));

        modifierSeance.addActionListener(e5->{
            if(isValidDate(new_session.getText()) )
                this.adminSession.modifier(new_session.getText(), (String) comboSession.getItemAt(list.getSelectedIndex()));
            else{
                this.adminSession.setVisible(true);
                mes1();
            }
        });

        buttons.setBackground(new Color(239, 223, 187));
        if(!this.adminSession.isModifier()) {
            buttons.add(delete, gbc);
            buttons.add(list, gbc);
            buttons.add(modifier, gbc);
            buttons.add(film, gbc);
            buttons.add(create, gbc);
            buttons.add(film2, gbc);
            buttons.add(room, gbc);
            buttons.add(date, gbc);
            buttons.add(addSeance, gbc);
        }
        else {
            buttons.add(mod_seance, gbc);
            buttons.add(comboSession, gbc);
            buttons.add(new_session, gbc);
            buttons.add(modifierSeance, gbc);
        }
        buttons.add(c,gbc);
        buttons.add(close,gbc);
        buttons.add(exit,gbc);

        this.add(buttons, gbc);
    }
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
    public void mes1() {
        JOptionPane.showMessageDialog(this, "Erreur de saisie de la date");
    }

}
