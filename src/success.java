import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class success extends JFrame implements ActionListener{
    JButton change, remove, logout;
    JPanel logpanel = new JPanel();
    String getname, getid, getpw, getgender;
    LoginDAO dao = new LoginDAO();
    public success(){
        setTitle("login success");
        setSize(400, 450);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        logpanel.setLayout(null);

        logpanel.setBackground(Color.white);

        change = new JButton("개인정보 수정");
        remove = new JButton("탈퇴");
        logout = new JButton("로그아웃");

        change.setBounds(100, 50, 200, 50);
        remove.setBounds(100, 150, 200, 50);
        logout.setBounds(100, 250, 200, 50);

        logpanel.add(change);
        logpanel.add(remove);
        logpanel.add(logout);

        remove.addActionListener(this);
        logout.addActionListener(this);

        add(logpanel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("탈퇴")){
            int result = JOptionPane.showConfirmDialog(null, "탈퇴하시겠습니까?", "탈퇴", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){      //사용자가 예를 선택한 경우
                JOptionPane.showMessageDialog(null, "탈퇴되었습니다.");
                homepage page = new homepage();
                getid = page.getid;
                dao.deleteLogin(getid);
                setVisible(false);
            }
        }
        if(e.getActionCommand().equals("로그아웃")){
            setVisible(false);
            homepage page = new homepage();
        }
        if(e.getActionCommand().equals("개인정보 수정")){

        }
    }


    public static void main(String[] args){
        new success();
    }
}
