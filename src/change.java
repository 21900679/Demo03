import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class change extends JFrame implements ActionListener {

    JTextField chaname, chapw;
    JRadioButton F, M;
    JPanel chapanel = new JPanel();
    String getname, getgender, getpw, getid;
    LoginDAO dao = new LoginDAO();
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
        getid = id1;

        JLabel name = new JLabel("이름: ");
        JLabel id = new JLabel("ID: ");
        JLabel pw = new JLabel("Password: ");
        JLabel gender = new JLabel("성별");
        JLabel getid = new JLabel(id1);
        chaname = new JTextField();
        chapw = new JTextField();

        F = new JRadioButton("여자");
        M = new JRadioButton("남자");

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

        if(e.getActionCommand().equals("수정하기")){
            getname = chaname.getText();
            getpw = chapw.getText();
            if(F.isSelected())
                getgender = "F";
            else
                getgender = "M";
            int success = dao.updateLogin(getname, getid, getpw, getgender);
            System.out.println("abcde");
            System.out.println(getname);
            System.out.println(getpw);
            System.out.println(getgender);
            System.out.println(getid);
            if(success == 0)
                System.out.println("실패");
            else{
                System.out.println("성공");
                JOptionPane.showMessageDialog(null, "수정 완료.");
                setVisible(false);
                success suc = new success(getid, getpw);
            }
        }

    }

//    public static void main(String[] args){
//        new change();
//    }
}
