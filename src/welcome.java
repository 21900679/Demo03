import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class welcome extends JFrame implements ActionListener {

    String getname, getid, getpw, getgender;
    JTextField text_name, text_id;
    JPasswordField text_pw;
    JRadioButton F, M;
    LoginDAO dao = new LoginDAO();
    LoginVo vo = new LoginVo();
    boolean ys = false;

    public welcome(){
        setTitle("welcome site");
        setSize(400, 450);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel welpanel = new JPanel();

        welpanel.setLayout(null);

        welpanel.setBackground(Color.white);

        JLabel name = new JLabel("이름: ");
        JLabel id = new JLabel("ID: ");
        JLabel pass = new JLabel("Password: ");
        JLabel fm = new JLabel("성별");
        text_name = new JTextField();
        text_id = new JTextField();
        text_pw = new JPasswordField();
        F = new JRadioButton("여자");
        M = new JRadioButton("남자");
        JButton submit = new JButton("등록하기");
        JButton check = new JButton("중복체크");

        ButtonGroup Gender = new ButtonGroup();
        Gender.add(F);
        Gender.add(M);

        name.setBounds(20, 70, 70, 30);
        text_name.setBounds(60, 70, 90, 30);
        id.setBounds(20, 100,70,30);
        text_id.setBounds(60, 100, 90, 30);
        pass.setBounds(20, 130, 70, 30);
        text_pw.setBounds(90, 130, 90, 30);
        fm.setBounds(20, 160, 60, 30);
        F.setBounds(60,160, 60, 30);
        M.setBounds(120, 160, 60, 30);
        submit.setBounds(150, 350,100, 30);
        check.setBounds(160,100, 90, 20);

        welpanel.add(name);
        welpanel.add(id);
        welpanel.add(pass);
        welpanel.add(text_name);
        welpanel.add(text_id);
        welpanel.add(text_pw);
        welpanel.add(F);
        welpanel.add(M);
        welpanel.add(fm);
        welpanel.add(submit);
        welpanel.add(check);

        submit.addActionListener(this);
        F.addActionListener(this);
        M.addActionListener(this);
        check.addActionListener(this);

        add(welpanel);
        setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {

        getname = text_name.getText();
        getid = text_id.getText();
        getpw = text_pw.getText();

        if(e.getActionCommand().equals("중복체크")){
            int success = dao.yslogin(getid, getpw);
            if(success == -1){
                ys = true;
                JOptionPane.showMessageDialog(null, "사용가능한 id입니다.");
            }
            else{
                JOptionPane.showMessageDialog(null, "이미 사용중인 id입니다.");
            }
        }

        if(e.getActionCommand().equals("등록하기")){
            if(F.isSelected())
                getgender = "F";
            else if(M.isSelected())
                getgender = "M";
            else
                getgender = "N";
            if(ys == false){
                JOptionPane.showMessageDialog(null, "중복체크 하시오.");
            }
            else if(getname.length() < 1){
                JOptionPane.showMessageDialog(null, "이름을 입력하시오.");
            }
            else if(getid.length() < 4){
                JOptionPane.showMessageDialog(null, "id 4글자 이상 입력하시오.");
                new welcome();
            }
            else if(getpw.length() < 8){
                JOptionPane.showMessageDialog(null, "Password 8글자 이상 입력하시오.");
            }
            else if(getgender == "N"){
                JOptionPane.showMessageDialog(null, "성별을 선택 하시오.");
            }
            else{
                int success = dao.insertLogin(getname, getid, getpw, getgender);
                if(success == 0)
                    System.out.println("실패");
                else{
                    System.out.println("성공");
                    JOptionPane.showMessageDialog(null, "등록 완료.");
                    setVisible(false);
                    homepage home = new homepage();
                }
            }

        }
    }

    public static void main(String[] args){
        new welcome();
    }
}
