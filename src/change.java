import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class change extends JFrame implements ActionListener {

    JTextField chaname;
    JRadioButton F, M;
    JPanel chapanel = new JPanel();
    JPasswordField chapw;
    String getname, getgender, getpw, getid;
    Font font;
    String[][] contents;
    List<LoginVo> clients = new ArrayList<LoginVo>();
    LoginDAO dao = new LoginDAO();
    change(String id1, String pw1){
        setTitle("change");
        setSize(350, 400);
        setLocationRelativeTo(null);

        font = new Font("SanSerif", Font.BOLD, 15);

        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        chapanel.setLayout(null);
        chapanel.setBackground(Color.white);

        clients = dao.getLoginList();
        contents = new String[clients.size()][4];
        for(int i = 0; i < clients.size(); i++){
            contents[i][0] = clients.get(i).getName();
            contents[i][1] = clients.get(i).getId();
            contents[i][2] = clients.get(i).getPw();
            contents[i][3] = clients.get(i).getGender();
        }
        getid = id1;
        getpw = pw1;

        for(int i = 0; i < clients.size(); i++){
            if(contents[i][1].equals(getid)){
                getname = contents[i][0];
                getgender = contents[i][3];
                break;
            }
        }

        JLabel name = new JLabel("이름 : ");
        JLabel id = new JLabel("ID : ");
        JLabel pw = new JLabel("Password : ");
        JLabel gender = new JLabel("성별");
        JLabel getid = new JLabel(id1);
        chaname = new JTextField();
        chapw = new JPasswordField();

        chaname.setText(getname);
        chapw.setText(getpw);

        if(getgender.equals("F")){
            System.out.println("여자입니다.");
            F = new JRadioButton("여자", true);
            M = new JRadioButton("남자");
        }
        else{
            System.out.println("남자입니다.");
            M = new JRadioButton("남자", true);
            F = new JRadioButton("여자");
        }

        JButton submit = new JButton("수정하기");
        JButton back = new JButton("<-");

        ButtonGroup Gender = new ButtonGroup();
        Gender.add(F);
        Gender.add(M);

        name.setBounds(65, 60, 100, 30);
        chaname.setBounds(155,60, 110, 30);
        id.setBounds(65, 110, 50, 30);
        getid.setBounds(155, 110, 200, 30);
        pw.setBounds(65, 160, 110, 30);
        chapw.setBounds(155,160,110,30);
        gender.setBounds(65, 210, 50, 30);
        F.setBounds(155, 210, 60, 30);
        M.setBounds(215, 210,60,30);
        submit.setBounds(125, 290, 100, 30);
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
        chapanel.add(back);

        name.setFont(font);
        id.setFont(font);
        pw.setFont(font);
        gender.setFont(font);
        getid.setFont(font);
        submit.setFont(font);
        F.setFont(font);
        M.setFont(font);

        F.setBackground(Color.white);
        M.setBackground(Color.white);
        submit.setBackground(Color.pink);
        back.setBackground(Color.pink);

        submit.addActionListener(this);
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
                    success suc = new success(getid, getpw);
                }
            }
        }
        if(e.getActionCommand().equals("<-")){
            setVisible(false);
            success suc = new success(getid, getpw);
        }
    }
//    public static void main(String[] args){
//        new change("aaaa", "bbbb");
//    }
}
