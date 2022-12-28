import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class admin extends JFrame implements MouseListener, ActionListener {
    LoginDAO dao = new LoginDAO();
    JTable table;
    List<LoginVo> clients = new ArrayList<LoginVo>();
    String getname, getgender, getpw, getid;
    String[][] contents;
    JPanel list = new JPanel();
    JPanel botton = new JPanel();
    Font font;
    admin(){
        setTitle("admin list");
        setSize(400, 450);
        setLocationRelativeTo(null);
        list.setLayout(null);
        botton.setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        font = new Font("SanSerif", Font.BOLD, 15);

        JButton back = new JButton("로그아웃");
        String header[] = {"Name", "ID", "Password", "Gender"};
        clients = dao.getLoginList();
        contents = new String[clients.size()][4];
        for(int i = 0; i < clients.size(); i++){
            contents[i][0] = clients.get(i).getName();
            contents[i][1] = clients.get(i).getId();
            contents[i][2] = clients.get(i).getPw();
            contents[i][3] = clients.get(i).getGender();
        }
        table = new JTable(contents, header);
        table.getTableHeader().setReorderingAllowed(false);     //이동 불가
        table.getTableHeader().setResizingAllowed(false);   //크기 조절 불가
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setSize(400, 350);

        list.setBounds(0,0,400,350);
        botton.setBounds(0,350,400,100);
        back.setBounds(140,350,100,30);

        botton.setBackground(Color.white);
        back.setBackground(Color.PINK);
        scrollPane.getViewport().setBackground(Color.WHITE);

        botton.setFont(font);

        botton.add(back);
        list.add(scrollPane);
        add(list);
        add(botton);

        back.addActionListener(this);
        table.addMouseListener(this);

        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();

        getname = contents[row][0];
        getid = contents[row][1];
        getpw = contents[row][2];
        getgender = contents[row][3];

        new adminpage(getname, getid, getpw, getgender);
        setVisible(false);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("로그아웃")){
            setVisible(false);
            new homepage();
        }
    }
//    public static void main(String[] args){
//        new admin();
//    }
}
