import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class change extends JFrame implements ActionListener {

    JTextField text_name;
    JPasswordField text_pw;
    JPanel chapanel = new JPanel();
    change(String id1, String pw1){
        setTitle("change");
        setSize(400, 450);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        chapanel.setLayout(null);
        chapanel.setBackground(Color.white);

        System.out.println("Aaa");
        System.out.println(id1);

        JLabel name = new JLabel("이름: ");
        JLabel id = new JLabel("ID: ");
        JLabel pw = new JLabel("Password: ");
        JLabel gender = new JLabel("성별");
        JLabel getid = new JLabel(id1);
        JTextField chaname = new JTextField();
        JTextField chapw = new JTextField();

        text_name = new JTextField();
        text_pw = new JPasswordField();

        JRadioButton F = new JRadioButton("여자");
        JRadioButton M = new JRadioButton("남자");

        JButton submit = new JButton("수정하기");

        ButtonGroup Gender = new ButtonGroup();
        Gender.add(F);
        Gender.add(M);

        name.setBounds(50, 70, 50, 30);
        chaname.setBounds(110,70, 110, 30);
        id.setBounds(50, 100, 50, 30);
        getid.setBounds(110, 100, 50, 30);
        pw.setBounds(50, 130, 110, 30);
        chapw.setBounds(110,130,110,30);
        gender.setBounds(50, 160, 50, 30);
        F.setBounds(90, 160, 60, 30);
        M.setBounds(150, 160,60,30);
        submit.setBounds(150, 350, 100, 30);

        chapanel.add(name);
        chapanel.add(id);
        chapanel.add(pw);
        chapanel.add(gender);
        chapanel.add(F);
        chapanel.add(M);
        chapanel.add(submit);
        chapanel.add(text_name);
        chapanel.add(text_pw);
        chapanel.add(chaname);
        chapanel.add(chapw);
        chapanel.add(getid);

        submit.addActionListener(this);
        F.addActionListener(this);
        M.addActionListener(this);

        add(chapanel);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

//    public static void main(String[] args){
//        new change();
//    }
}
