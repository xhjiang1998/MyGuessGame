/**
 * 
 */
package jiangxuhui;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * @author 江旭晖
 *
 */
public class MyGuessGame extends JFrame implements ActionListener {
	JTextField tf=new JTextField();
	JButton b1=new JButton();
	JLabel j1=new JLabel();
	int m;
	int count;//存放猜测次数。
	int oldNumber;//存放原有记录次数变量
    boolean isEnd;  //标记是否破记录变量
	/**
	 * @param args
	 */
    public MyGuessGame() {
    	b1.setActionCommand("start");
    	JPanel p=new JPanel();
    	p.add(b1);
    	b1.addActionListener(this);
    	tf.addActionListener(this);
    	tf.setEnabled(false);
    	this.getContentPane().add(tf,"North");
    	this.getContentPane().add(j1);
    	this.getContentPane().add(p,"South");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setSize(300,200);
    	this.setLocation(300,200);
    	this.setVisible(true);
    	
    }
    
    public int getNumber() {
    	int m=(int)(Math.random()*100)+1;//1`100
        return m;
    }
    
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String s=e.getActionCommand();
		int n=0;//存放用户所猜数字的变
	
		if(s.equals("start")) {
			isEnd=false;
			count=0;
			m=this.getNumber();
			System.out.print(m);
			b1.setEnabled(false);
			tf.setEnabled(true);
			j1.setText("请输入1-100的数字");
			tf.requestFocus();
			oldNumber=readRecord();
		}
		else {
		
			if(!isEnd) {
				++count;
				String sn=tf.getText();
				try {
					n=Integer.parseInt(sn);
				}catch(NumberFormatException e1) {
					j1.setText("请输入数字");
					return;
				}
				
				if(n<m) {
					j1.setText("您猜的数偏小");
					return;
				}
				else if(n>m) {
					j1.setText("您猜的数偏大");
					return;
				}else {
					j1.setText("恭喜你猜对了，所花次数为:"+ count);
					tf.setText("");
					b1.setEnabled(true);
					if(oldNumber>count)
					{
						j1.setText("您破记录了，请在文本框输入您的姓名：");
						isEnd=true;
					}
					}
				
				}
			
			else {
					String name=tf.getText();
					this.saveRecord(name,count);
					j1.setText("您的记录已经记录在册，继续努力！");
					tf.setText("");
					b1.setEnabled(true);
				}
				
			}
	}	

	public void saveRecord(String name, int count) {
		File f1=new File("record.txt");
		try {
			FileWriter fout=new FileWriter(f1);
			PrintWriter bw=new PrintWriter(fout);
			bw.println(count);
			bw.println(name);
			bw.close();
			fout.close();
		}catch(java.io.FileNotFoundException e){}
			catch(IOException e) {}
	
		// TODO Auto-generated method stub
		
	}

		public int readRecord() {
		int count=100;
		File f1=new File("record.text");
		try {
			FileReader fin=new FileReader(f1);
			BufferedReader br=new BufferedReader(fin);
			String s=br.readLine();
			count =Integer.parseInt(s);
			br.close();
			fin.close();
			
			
		}catch(java.io.FileNotFoundException e) {
			
		}catch(IOException e) {
			
		}return count;
	}
	
	
	public static void main(String[] args) {
		new MyGuessGame();
		// TODO Auto-generated method stub

	}
}
