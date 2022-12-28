import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminpage extends JFrame implements ActionListener {
    JTextField chaname;
    JRadioButton F, M;
    JPasswordField chapw;
    JPanel chapanel = new JPanel();
    String getname, getgender, getpw, getid;
    Font font;
    LoginDAO dao = new LoginDAO();
    adminpage(String name1, String id1, String pw1, String gender1){
        setTitle("change");
        setSize(350, 400);
        setLocationRelativeTo(null);

        font = new Font("SanSerif", Font.BOLD, 15);

        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        chapanel.setLayout(null);
        chapanel.setBackground(Color.white);

        getid = id1;
        getname = name1;
        getpw = pw1;
        getgender = gender1;

        JLabel name = new JLabel("이름: ");
        JLabel id = new JLabel("ID: ");
        JLabel pw = new JLabel("Password: ");
        JLabel gender = new JLabel("성별");
        JLabel getid = new JLabel(id1);
        chaname = new JTextField();
        chapw = new JPasswordField();

        chaname.setText(name1);
        chapw.setText(pw1);

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
        JButton back = new JButton("<-");

        ButtonGroup Gender = new ButtonGroup();
        Gender.add(F);
        Gender.add(M);

        name.setBounds(65, 60, 100, 30);
        chaname.setBounds(155,60, 110, 30);
        id.setBounds(65, 110, 50, 30);
        getid.setBounds(155, 110, 50, 30);
        pw.setBounds(65, 160, 110, 30);
        chapw.setBounds(155,160,110,30);
        gender.setBounds(65, 210, 50, 30);
        F.setBounds(155, 210, 60, 30);
        M.setBounds(215, 210,60,30);
        submit.setBounds(70, 290, 100, 30);
        out.setBounds(180, 290, 100, 30);
        back.setBounds(5,5,50,20);

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
        chapanel.add(back);

        name.setFont(font);
        id.setFont(font);
        pw.setFont(font);
        gender.setFont(font);
        getid.setFont(font);
        submit.setFont(font);
        F.setFont(font);
        M.setFont(font);
        out.setFont(font);

        F.setBackground(Color.white);
        M.setBackground(Color.white);
        submit.setBackground(Color.pink);
        back.setBackground(Color.pink);
        out.setBackground(Color.pink);

        submit.addActionListener(this);
        out.addActionListener(this);
        F.addActionListener(this);
        M.addActionListener(this);
        back.addActionListener(this);

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

            if(getname.length() < 1){
                JOptionPane.showMessageDialog(null, "이름을 입력하시오.");
            }
            else if(getpw.length() < 8){
                JOptionPane.showMessageDialog(null, "Password 8글자 이상 입력하시오.");
            }
            else{
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
        if(e.getActionCommand().equals("<-")){
            setVisible(false);
            new admin();
        }
    }
//    public static void main(String[] args){
//        new adminpage("정지민", "ㅇㅇ", "ㅇㅇㅇㅇ", "12345");
//    }
}
