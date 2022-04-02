package Controller;

import java.sql.SQLException;

public class App {
    //private static ResultSet rs;
    public static void main(String[] args)  { //MAIN : LANCE L'AFFICHAGE DU MENU
        try {
            BigController bc= new BigController(); //création attribut controller
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

/*
        QRCode qRcode=new QRCode();
        //data that we want to store in the QR code
        String str = "THE HABIT OF PERSISTENCE IS THE HABIT OF VICTORY.";
//path where we want to get QR Code
        String path = "C:\\JAVA\\Java ECE\\testqrcode\\Quote.png";
//Encoding charset to be used
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
//generates QR code with Low level(L) error correction capability
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//invoking the user-defined method that creates the QR code
        qRcode.generateQRcode(str, path, charset, hashMap, 200, 200);//increase or decrease height and width accodingly
//prints if the QR code is generated
        System.out.println("QR Code created successfully.");

//path where the QR code is saved
        String path1 = "C:\\JAVA\\Java ECE\\testqrcode\\Quote.png";
//Encoding charset to be used
        String charset1 = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
//generates QR code with Low level(L) error correction capability
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        System.out.println("Data stored in the QR Code is: \n"+ QRCode.readQRcode(path1, charset1, hintMap));

    }
 */

    }
}
