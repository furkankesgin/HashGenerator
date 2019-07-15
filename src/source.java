//import com.chilkatsoft.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;


public class source<success> extends JFrame implements ActionListener, KeyListener {
    int pX, pY;
    source(){
        super("Hash Generator");
        locations();
        add();
        listener();
        propertiesitem();
        mouseListener();


    }
void mouseListener(){
    addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent me){
            pX = me.getX();
            pY = me.getY();
        }
    });

    addMouseMotionListener(new MouseAdapter() {
        public void mouseDragged(MouseEvent me){
            setLocation(getLocation().x+me.getX()-pX,getLocation().y+me.getY()-pY);
        }
    });
}


    //CkCrypt2 crypt = new CkCrypt2();
    JTextField hashalani = new JTextField();
    JTextField textresult = new JTextField();
    JButton butonhashle = new JButton("Hash It");
    String[] hashType = {"MD2", "MD5", "MD5_HEX", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512", "BASE64", "ROT" , "Number to Binary"};
    JComboBox cmbox = new JComboBox(hashType);
    JTextField rot = new JTextField("13");
    
    void listener(){
        butonhashle.addActionListener(this);
        cmbox.addActionListener(this);
        hashalani.addActionListener(this);
        butonhashle.addKeyListener(this);
        hashalani.addKeyListener(this);

    }
void add(){
    add(hashalani);
    add(butonhashle);
    add(cmbox);
    add(textresult);
    add(rot);
}
void propertiesitem(){
    textresult.setEditable(false);
    cmbox.setFocusable(false);
    rot.setVisible(false);
}

void locations(){
        hashalani.setBounds(5,12,400,30);
        butonhashle.setBounds(200,50,100,35);
        cmbox.setBounds(400,12,110,35);
        textresult.setBounds(5,100,505,30);
        textresult.setBackground(new Color(0,0,0,0));
        rot.setBounds(400,50,110,20);
    }
/*
   void ripemd128(String input) {
       String content = input;
       crypt.put_EncodingMode("hex");
       crypt.put_HashAlgorithm("ripemd128");
       String hashStr;
       hashStr = crypt.hashStringENC(content);
       textresult.setText(hashStr);
   }


   void ripemd160(String input) {
       String content = input;
       crypt.put_EncodingMode("hex");
       crypt.put_HashAlgorithm("ripemd160");
       String hashStr;
       hashStr = crypt.hashStringENC(content);
       textresult.setText(hashStr);
   }
   void ripemd256(String input) {
       String content = input;
       crypt.put_EncodingMode("hex");
       crypt.put_HashAlgorithm("ripemd256");
       String hashStr;
       hashStr = crypt.hashStringENC(content);
       textresult.setText(hashStr);
   }
   void ripemd320(String input){
       String content = input;
       crypt.put_EncodingMode("hex");
       crypt.put_HashAlgorithm("ripemd320");
       String hashStr;
       hashStr = crypt.hashStringENC(content);
       textresult.setText(hashStr);
   }
*/
    public static String md5(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(input.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; ++i) {
            sb.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    public String md5gen(String girilen){
        try {
            MessageDigest md5 = MessageDigest.getInstance(String.valueOf(cmbox.getSelectedItem()));
            byte[] bytedonusum = md5.digest(girilen.getBytes());
            BigInteger no = new BigInteger(1,bytedonusum);
            String hashtext = no.toString(16);
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public  String rot13(String input) {
        StringBuilder sb = new StringBuilder();
        int a = Integer.parseInt(String.valueOf(Math.abs(Integer.parseInt(rot.getText()))));
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if       (c >= 'a' && c <= 'm') c += a;
            else if  (c >= 'A' && c <= 'M') c += a;
            else if  (c >= 'n' && c <= 'z') c -= a;
            else if  (c >= 'N' && c <= 'Z') c -= a;
            sb.append(c);
        }
        return sb.toString();
    }


    void alinandeger(){
    if (cmbox.getSelectedItem() == "MD5"
            || cmbox.getSelectedItem() == "SHA-1"
            || cmbox.getSelectedItem() == "SHA-256"
            || cmbox.getSelectedItem() == "MD2"
            || cmbox.getSelectedItem() == "SHA-224"
            || cmbox.getSelectedItem() == "SHA-384"
            || cmbox.getSelectedItem() == "SHA-512") {
        textresult.setText(md5gen(hashalani.getText()));
        rot.setVisible(false);

    }else if (cmbox.getSelectedItem() == "BASE64"){
        byte[] encodedBytes = Base64.getEncoder().encode(hashalani.getText().getBytes());
        textresult.setText(new String(encodedBytes));
        rot.setVisible(false);

    }
    else if (cmbox.getSelectedItem()=="ROT"){
            rot.setVisible(true);
            textresult.setText(rot13(hashalani.getText()));
    }

    else if (cmbox.getSelectedItem() =="MD5_HEX"){
        rot.setVisible(false);
        try {
            textresult.setText(md5(hashalani.getText()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    else if(cmbox.getSelectedItem() == "Number to Binary"){
        rot.setVisible(false);
        try {
            textresult.setText(Integer.toBinaryString(Integer.parseInt(hashalani.getText()))+" ");
            int ascii = Integer.parseInt(hashalani.getText());
            System.out.println(ascii);
        }catch (Exception e){
            System.out.println("YAZAMADIM");
        }



    }
    /*
    else if (cmbox.getSelectedItem() == "ripemd128"){
        ripemd128(hashalani.getText());
    }
    else if (cmbox.getSelectedItem() == "ripemd160"){
        ripemd160(hashalani.getText());
    }
    else if (cmbox.getSelectedItem() == "ripemd256"){
        ripemd256(hashalani.getText());
    }
    else if (cmbox.getSelectedItem() == "ripemd320"){
        ripemd320(hashalani.getText());
    }

*/
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == butonhashle){
            alinandeger();

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            alinandeger();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
