import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class homepage extends JFrame implements ActionListener {
    JPanel logpanel = new JPanel();
    JTextField field1;
    JPasswordField field2;
    String getid, getpw;

    public homepage(){
        setTitle("jimin_login");
        setSize(295, 350);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        logpanel.setBackground(Color.white);
        logpanel.setLayout(null);        // setBounds를 하기 위해서 해야함.

        JButton client = new JButton("회원가입");
        JButton login = new JButton("로그인");
        JLabel id = new JLabel("ID: ");
        JLabel password = new JLabel("Password: ");
        field1 = new JTextField(15);
        field2 = new JPasswordField(15);

        client.setBounds(30, 250, 100, 30);
        login.setBounds(150, 250, 100, 30);
        id.setBounds(50, 70, 50, 30);
        password.setBounds(50, 100, 70, 30);
        field1.setBounds(115, 70, 110, 30);
        field2.setBounds(115, 100, 110, 30);

        logpanel.add(id);
        logpanel.add(field1);
        logpanel.add(password);
        logpanel.add(field2);
        logpanel.add(client);
        logpanel.add(login);

        client.addActionListener(this);
        login.addActionListener(this);

        client.setBackground(Color.pink);
        login.setBackground(Color.pink);

        add(logpanel);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("회원가입")){
            welcome come = new welcome();
            come.setVisible(true);
            setVisible(false);
        }
        else if(e.getActionCommand().equals("로그인")){
            LoginDAO dao = new LoginDAO();
            getid = field1.getText();
            getpw = field2.getText();
            System.out.println(getid);
            System.out.println(getpw);

            int success = dao.yslogin(getid, getpw);

            if(success == 1){
                JOptionPane.showMessageDialog(null, "로그인 성공.");
                setVisible(false);
                new success(getid, getpw);
            }
            else if(success == 0){
                JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
            }
            else if(success == -1){
                JOptionPane.showMessageDialog(null, "일치하는 아이디가 없습니다.");
            }
            else{
                JOptionPane.showMessageDialog(null, "데이터 베이스 오류.");
            }
        }

    }
    public static void main(String[] args) {
        new homepage();
    }
}