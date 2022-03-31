package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        setBorder(new EmptyBorder(100, 10, 10, 10));

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

        JLabel delete= new JLabel("Voulez vous supprimer une des seances suivantes?");
        delete.setFont(f3);

        String[] fil= new String[this.adminSession.getSession().getDateArrayList().size()];

        for(int i = 0; i<fil.length; i++){
            fil[i]= this.adminSession.getSession().getDateArrayList().get(i);
        }

        JComboBox list= new JComboBox(fil);
        list.setBounds(50, 50,90,20);
        list.setFont(f2);
/*
        JLabel modifier= new JLabel("Voulez vous modifier une des seances suivantes?");
        delete.setFont(f3);

        JComboBox list1= new JComboBox(fil);
        list1.setBounds(50, 50,90,20);
        list1.setFont(f2);

        JTextField date = new JTextField("Nouvelle date");
        date.setForeground(new Color(59, 47, 47));
        date.setFont(f2);
        date.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                date.setText("");
            }
        });

 */

        JLabel create= new JLabel("Voulez vous ajouter une nouvelle seance?");
        create.setFont(f3);

        JTextField name = new JTextField("Nom du film");
        name.setForeground(new Color(59, 47, 47));
        name.setFont(f2);
        name.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                name.setText("");
            }
        });

        JTextField room = new JTextField("Numero de la salle");
        room.setForeground(new Color(59, 47, 47));
        room.setFont(f2);
        room.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                room.setText("");
            }
        });

        JTextField date = new JTextField("Date de la session");
        date.setForeground(new Color(59, 47, 47));
        date.setFont(f2);
        date.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                date.setText("");
            }
        });

        JButton addSeance= new JButton("Nouvelle seance");
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
/*
        list1.addActionListener(e1->{
            adminSession.modifier((String) list.getItemAt(list.getSelectedIndex()));
        });
 */
        addSeance.addActionListener(e2->{
            this.adminSession.ajouter(name.getText(), room.getText(), date.getText());
        });

        close.addActionListener(e3-> {
            this.adminSession.menu();
            this.adminSession.setVisible(false);
            this.adminSession.getAdminMenu().setVisible(true);
        });

        exit.addActionListener(e4->System.exit(0));

        buttons.setBackground(new Color(239, 223, 187));
        buttons.add(delete,gbc);
        buttons.add(list, gbc);
        buttons.add(create,gbc);
        buttons.add(name,gbc);
        buttons.add(room, gbc);
        buttons.add(date,gbc);
        buttons.add(date,gbc);
        buttons.add(addSeance, gbc);
        buttons.add(close,gbc);
        buttons.add(exit,gbc);

        this.add(buttons, gbc);
    }
}
