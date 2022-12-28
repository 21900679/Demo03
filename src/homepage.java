import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class homepage extends JFrame {
    logPanel panel = new logPanel();
    //JPanel panel_CreateUser = new JPanel();
    JPanel panel_UpdateUser = new JPanel();

    private JTextField text_name;
    private JTextField text_id;
    private JTextField text_pass;
    String getid, getpw;

    public homepage(){
        setTitle("jimin_login");
        setSize(295, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //getContentPane().setLayout(null);
        add(panel);
    }

    class logPanel extends JPanel {
        public logPanel(){
            setBackground(Color.white);
            setLayout(null);        // setBounds를 하기 위해서 해야함.
            setVisible(true);
            setOpaque(true);

            JButton client = new JButton("회원가입");
            JButton login = new JButton("로그인");
            JLabel id = new JLabel("ID: ");
            JLabel password = new JLabel("Password: ");
            JTextField field1 = new JTextField(15);
            JTextField field2 = new JTextField(15);

            client.setBounds(30, 250, 100, 30);
            login.setBounds(150, 250, 100, 30);
            id.setBounds(50, 70, 50, 30);
            password.setBounds(50, 100, 70, 30);
            field1.setBounds(115, 70, 110, 30);
            field2.setBounds(115, 100, 110, 30);

            add(id);
            add(field1);
            add(password);
            add(field2);
            add(client);
            add(login);

            client.setBackground(Color.pink);
            login.setBackground(Color.pink);

            client.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new welcome();
                    setVisible(true);
                }
            });
            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LoginDAO dao = new LoginDAO();
                    getid = field1.getText();
                    getpw = field2.getText();
                    System.out.println(getid);
                    System.out.println(getpw);

                    int success = dao.yslogin(getid, getpw);

                    if(success == 1){
                        JOptionPane.showMessageDialog(null, "로그인 성공.");
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
            });
        }

    }
    class panel_CreateUser extends JPanel{
        public panel_CreateUser(){

        }

    }
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String userName = "root";
        String password = "Mouth612612*";

        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from client");

        resultSet.next();
        String name = resultSet.getString("NAME");
        System.out.println(name);

        resultSet.close();
        statement.close();
        connection.close();
        new homepage();
    }
}