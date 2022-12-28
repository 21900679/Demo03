import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminpage extends JFrame implements ActionListener {
    JTextField chaname, chapw;
    JRadioButton F, M;
    JPanel chapanel = new JPanel();
    String getname, getgender, getpw, getid;
    LoginDAO dao = new LoginDAO();
    adminpage(String name1, String id1, String pw1, String gender1){
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

        chaname.setText(name1);
        chapw.setText(pw1);

        System.out.println(gender1);

        if(gender1.equals("F")){
            System.out.println("여자입니다.");
            F = new JRadioButton("여자", true);
            M = M = new JRadioButton("남자");
        }
        else{
            System.out.println("남자입니다.");
            M = new JRadioButton("남자", true);
            F = new JRadioButton("여자");
        }

        JButton submit = new JButton("수정하기");
        JButton out = new JButton("탈퇴");

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
        out.setBounds(250, 350, 100, 30);

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
        chapanel.add(out);

        submit.addActionListener(this);
        out.addActionListener(this);
        F.addActionListener(this);
        M.addActionListener(this);

        add(chapanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getname = chaname.getText();
        getpw = chapw.getText();
        if(F.isSelected())
            getgender = "F";
        else
            getgender = "M";

        if(e.getActionCommand().equals("수정하기")){
            int success = dao.updateLogin(getname, getid, getpw, getgender);
            if(success == 0)
                System.out.println("실패");
            else{
                System.out.println("성공");
                JOptionPane.showMessageDialog(null, "수정 완료.");
                setVisible(false);
                new admin();
            }
        }
        if(e.getActionCommand().equals("탈퇴")){
            int result = JOptionPane.showConfirmDialog(null, "탈퇴시킬까요?", "탈퇴", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){      //사용자가 예를 선택한 경우
                JOptionPane.showMessageDialog(null, "탈퇴시켰습니다.");
                dao.deleteLogin(getid);
                setVisible(false);
                new admin();
            }
        }
    }

    public static void main(String[] args){
        new adminpage("정지민", "ㅇㅇ", "ㅇㅇㅇㅇ", "12345");
    }
}
