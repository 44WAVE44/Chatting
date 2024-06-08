import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Client_two_NP {
    public static void main(String[] args) throws Exception {

		C2frame tst = new C2frame();
        tst.c2Frame();
    }
}

class C2frame extends JFrame implements ActionListener {

    JButton btn2;
    JTextField txtf2;
    JTextArea txtarea2;
    Socket s2;

    public void c2Frame () throws Exception {     
        
        this.setTitle("Client_2");
        this.setSize(500,500);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtarea2 = new  JTextArea(10,20);
        txtarea2.setEditable(false);
        JScrollPane scp2 = new JScrollPane(txtarea2);
        scp2.setBounds(0,0,483,400);
        scp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scp2);

        txtf2 = new JTextField();
        txtf2.setSize(20,90);
        JScrollPane scp12 = new JScrollPane(txtf2);
        scp12.setBounds(0,400,400,60);
        scp12.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scp12.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.getContentPane().add(scp12);


        btn2 = new JButton("Send");
        btn2.addActionListener(this);
        btn2.setBounds(400,400,83,60);
        this.add(btn2);

        this.setVisible(true);

        while (true) {

            s2 = new Socket(InetAddress.getLocalHost(),1501);

            BufferedReader bfr1 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
            String msg1 = bfr1.readLine();
			if(msg1 != null){
				txtarea2.append("Client 1 says : "+msg1+"\n");
			}
            
        }     

    }

    @Override
    public void actionPerformed(ActionEvent e) {

			try{

				if(e.getSource() == btn2){
					String sMsg = txtf2.getText();
					txtarea2.append("Sent messege : "+sMsg+"\n");
					PrintWriter out2 = new PrintWriter(s2.getOutputStream(),true);
					out2.println(sMsg);
					txtf2.setText("");
				}
			}
			catch(Exception ae){}

        
    }
}