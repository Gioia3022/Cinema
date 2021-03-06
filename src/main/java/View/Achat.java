package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Achat extends JPanel {
    private Controller.Achat achat;
    private JFrame frame;

    /**
     * Constructeur
     * @param ca
     * @param f
     */
    public Achat(Controller.Achat ca, JFrame f) {
        this.achat = ca;
        this.frame = f;
        Font f1 = new Font(Font.SERIF, Font.BOLD, 60);
        Font f2 = new Font(Font.SERIF, Font.PLAIN, 20);
        Font f3 = new Font(Font.SERIF, Font.PLAIN, 30);

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints gbc_1 = new GridBagConstraints();
        GridBagConstraints gbc_2 = new GridBagConstraints();

        JPanel info = new JPanel(gbl);
        JPanel buttons = new JPanel(gbl);

        setLayout(new GridBagLayout());
        if (this.achat.isaBoolean())
            setBorder(new EmptyBorder(150, 10, 10, 10));
        else
            setBorder(new EmptyBorder(0, 10, 10, 10));


        //Set color of background
        this.setBackground(new Color(239, 223, 187));

        //Where the text is to be shown
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 10, 0);

        gbc_1.gridwidth = GridBagConstraints.CENTER;
        gbc_1.insets = new Insets(0, 0, 200, 0);
        gbc_1.anchor = GridBagConstraints.SOUTHEAST;
        gbc_1.fill = GridBagConstraints.HORIZONTAL;

        gbc_2.gridwidth = GridBagConstraints.CENTER;
        gbc_2.insets = new Insets(0, 100, 200, 0);
        gbc_2.anchor = GridBagConstraints.SOUTHWEST;
        gbc_2.fill = GridBagConstraints.HORIZONTAL;


        Label a = new Label("  Achat du ticket");
        a.setFont(f1);
        JLabel prix = new JLabel("Prix du ticket : "+this.achat.getTicket().getPrice());
        prix.setFont(f3);
        Label b = new Label("__________________");
        b.setFont(f1);
        JLabel cB = new JLabel("Num??ro de carte bancaire :");
        JTextField carte_bancaire = new JTextField(16);
        carte_bancaire.setForeground(new Color(59, 47, 47));
        carte_bancaire.setFont(f2);
        carte_bancaire.setHorizontalAlignment(JTextField.CENTER);

        JLabel date = new JLabel("Date d'??ch??ance :");
        JLabel d = new JLabel("mm-yyyy");
        JTextField date_carte = new JTextField(7);
        date_carte.setForeground(new Color(59, 47, 47));
        date_carte.setFont(f2);
        date_carte.setHorizontalAlignment(JTextField.CENTER);


        JLabel code = new JLabel("Code de s??curit?? :");
        JTextField code_secu = new JTextField(3);
        code_secu.setForeground(new Color(59, 47, 47));
        code_secu.setFont(f2);
        code_secu.setHorizontalAlignment(JTextField.CENTER);

        //Affichage du ticket
        JLabel ticket = new JLabel("Voici votre ticket :");
        ticket.setFont(f3);
        JLabel film = new JLabel("Film : " + this.achat.getFilm().getFilmName());
        film.setFont(f3);
        JLabel nSeance = new JLabel("S??ance du : " + this.achat.getSession().getDate());
        nSeance.setFont(f3);
        JLabel places = new JLabel("Nombre de places : " + this.achat.getTicket().getNbrPlace());
        places.setFont(f3);
        JLabel price = new JLabel("Prix : " + this.achat.getTicket().getPrice());
        price.setFont(f3);

        //QRCODE
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(this.achat.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert img != null;
        JLabel pic = new JLabel(new ImageIcon(img));


        JButton achat = new JButton("Achat");
        achat.setBackground(new Color(59, 47, 47));
        achat.setForeground(new Color(239, 223, 187));
        achat.setFont(f3);

        JButton close = new JButton("Back");
        close.setBackground(new Color(59, 47, 47));
        close.setForeground(new Color(239, 223, 187));
        close.setFont(f3);

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(59, 47, 47));
        exit.setForeground(new Color(239, 223, 187));
        exit.setFont(f3);

        //Verification : 16 chiffres pour la carte bancaire sinon message d'erreur
        achat.addActionListener(e0 -> {
            if (carte_bancaire.getText().length() == 16 && isValidDate(date_carte.getText()) && code_secu.getText().length() == 3) {
                this.achat.ticket();
            } else {
                this.achat.setVisible(true);
                mes2();
            }
        });

        //LORSQUE LA PAGE SE FERME => RETOUR AU MENU
        close.addActionListener(e1 -> {
            this.achat.menu();
            this.achat.setVisible(false);
            this.achat.getMenu().setVisible(true);
        });

        exit.addActionListener(e2 -> System.exit(0));

        buttons.setBackground(new Color(239, 223, 187));
        info.setBackground(new Color(239, 223, 187));

        if (!this.achat.isaBoolean()) {
            info.add(a, gbc);
            info.add(prix,gbc);
            info.add(b, gbc);
            info.add(cB, gbc);
            info.add(carte_bancaire, gbc);
            info.add(date, gbc);
            info.add(d, gbc);
            info.add(date_carte, gbc);
            info.add(code, gbc);
            info.add(code_secu, gbc);
            info.add(achat, gbc);
            buttons.add(close, gbc_1);
            buttons.add(exit, gbc_2);

            this.add(info, gbc);
            this.add(buttons, gbc);
        } else {
            buttons.add(ticket, gbc);
            buttons.add(film, gbc);
            buttons.add(nSeance, gbc);
            buttons.add(places, gbc);
            buttons.add(price, gbc);
            buttons.add(b, gbc);
            buttons.add(pic,gbc);
            this.add(buttons, gbc);
        }
    }

    //Verification format de la date indiqu??e
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public void mes1() {
        JOptionPane.showMessageDialog(this, "Payement reussi\n Vous aller recevoir un mail");
    }

    public void mes2() {
        JOptionPane.showMessageDialog(this, "Erreur de saisie");
    }

    public void mes3() {
        JOptionPane.showMessageDialog(this, "Payement reussi");
    }
}
