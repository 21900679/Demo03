import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class admin extends JFrame implements MouseListener {
    LoginDAO dao = new LoginDAO();
    JTable table;
    List<LoginVo> clients = new ArrayList<LoginVo>();
    String getname, getgender, getpw, getid;
    String[][] contents;
    admin(){
        setTitle("admin list");
        setSize(400, 450);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
        setVisible(true);

        table.addMouseListener(this);
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

    public static void main(String[] args){
        new admin();
    }
}
