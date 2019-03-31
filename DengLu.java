package DengluJieMian;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import AWARD.YongHub;
import AWARD.YongHubDAO;
import AWARD.YongHubDAOImpl;
import DButil.DButil;
import Menu.GuanLi;
public class DengLu extends JFrame implements ActionListener{
	JButton b1= null;
	JButton b2= null;
	JTextField jt = null;
	JPasswordField jp = null;
	public  static void main (String arg[]) {
		DengLu dengLu = new DengLu();
	 }
	public DengLu() {
		this.init();
	}
	public void init() {
		JFrame gameFrame = new JFrame();
		gameFrame.setTitle("某景点门票销售管理系统");
		JLabel jl =new JLabel("用户名:",JLabel.CENTER);
		 jl.setBounds(200, 100, 100, 50);
		jt =new JTextField();
		jt.setBounds(300, 100, 200, 50);
		JLabel js =new JLabel("密码:",JLabel.CENTER);
		 js.setBounds(200, 200, 100, 50);
		 jp=new JPasswordField();
	    jp.setBounds(300, 200, 200, 50);
	    jl.setHorizontalAlignment(SwingConstants.CENTER);
	    this.b1 =new JButton("登录");
	    b1.addActionListener(this);
	    JButton b2 =new JButton("退出");
	    b2.addActionListener(this);
	    b1.setBounds(100, 400, 100, 50);
	    b2.setBounds(600, 400, 100, 50);
	    gameFrame.add(jl);
	    gameFrame.add(jt);
	    gameFrame.add(js);
	    gameFrame.add(jp);
	    gameFrame.add(b1);
		gameFrame.add(b2);
	    gameFrame.setLayout(null);
     	gameFrame.setSize(800, 600);
	    gameFrame.setVisible(true); 
	   // setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.b1) {
			String name = this.jt.getText();
			String passWord = new String(this.jp.getPassword());
			if (name == null || name.length() <= 0 || passWord == null || passWord.length() <= 0) {
				JOptionPane.showMessageDialog(this,"用户名密码不能为空");
				return;
			}else if (this.checkUser(name, passWord)) {
				JOptionPane.showMessageDialog(this, "登录成功");
				MainFrame3 mainFrame3 = new MainFrame3() ;
				mainFrame3.setBounds(200, 200, 1300, 800);
				mainFrame3.setVisible(true);
				this.dispose();//此界面消失
				
			} else {
				JOptionPane.showMessageDialog(this, "用户名密码错误");
			}
		} /*else if(e.getSource() == this.buttonReset) {
			this.fieldName.setText("");
			this.fieldPassWord.setText("");
		}*/
		
	}
	private boolean checkUser(String name, String passWord) {
		boolean flag = false;
		YongHubDAO yongHubDAO = new YongHubDAOImpl();
		YongHub tempUser =yongHubDAO.findbyId(name);
		if (tempUser != null && tempUser.getYhmm().equals(passWord)) {
			flag = true;
		}
		return flag;
	}
}
   
 
	
	

	

