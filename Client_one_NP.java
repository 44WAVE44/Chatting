import java.net.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Client_one_NP {
        public static void main(String [] args) throws Exception {

            C1frame tst = new C1frame();
            tst.cFrame();

        }    
}

class C1frame extends JFrame implements ActionListener {

    JButton btn;
    JTextField txtf;
    JTextArea txtarea;
    Socket s;

    public void cFrame () throws Exception {     
        
        this.setTitle("Client_1");
        this.setSize(500,500);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtarea = new  JTextArea(10,20);
        txtarea.setEditable(false);
        JScrollPane scp = new JScrollPane(txtarea);
        scp.setBounds(0,0,483,400);
        scp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scp);

        txtf = new JTextField();
        txtf.setSize(20,90);
        JScrollPane scp1 = new JScrollPane(txtf);
        scp1.setBounds(0,400,400,60);
        scp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.getContentPane().add(scp1);


        btn = new JButton("Send");
        btn.addActionListener(this);
        btn.setBounds(400,400,83,60);
        this.add(btn);

        this.setVisible(true);

        while (true) {

            s = new Socket(InetAddress.getLocalHost(),1500);

            BufferedReader bfr1 = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String msg1 = bfr1.readLine();
            if(msg1 != null){
                txtarea.append("Client 2 says : "+msg1+"\n");
            }
            
        }     

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        try{

            if(e.getSource() == btn){
                String sMsg = txtf.getText();
                txtarea.append("Sent messege : "+sMsg+"\n");
                PrintWriter out = new PrintWriter(s.getOutputStream(),true);
                out.println(sMsg);
                txtf.setText("");
            }
        }
        catch(Exception ae){}   
        
    }
}