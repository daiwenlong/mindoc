package com.me.mindoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Hello world!
 *
 */
public class App 
{
	 public static void main(String[] args) {    
	        // 创建 JFrame 实例
	        JFrame frame = new JFrame("文档生成器-https://github.com/daiwenlong/mindoc");
	        // Setting the width and height of frame
	        frame.setSize(500, 330);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        /* 创建面板，这个类似于 HTML 的 div 标签
	         * 我们可以创建多个面板并在 JFrame 中指定位置
	         * 面板中我们可以添加文本字段，按钮及其他组件。
	         */
	        JPanel panel = new JPanel();    
	        // 添加面板
	        frame.add(panel);
	        /* 
	         * 调用用户定义的方法并添加组件到面板
	         */
	        placeComponents(panel);

	        // 设置界面可见
	        frame.setVisible(true);
	    }

	    private static void placeComponents(JPanel panel) {

	        /* 布局部分我们这边不多做介绍
	         * 这边设置布局为 null
	         */
	        panel.setLayout(null);
	        
	        
	        
	        // 创建 JLabel
	        JLabel urlLabel = new JLabel("数据库地址:");
	       
	        urlLabel.setBounds(50,20,80,25);
	        panel.add(urlLabel);

	      
	        final JTextField urlText = new JTextField(20);
	        urlText.setBounds(150,20,265,25);
	        panel.add(urlText);

	        // 创建 JLabel
	        JLabel userLabel = new JLabel("数据库用户:");
	        /* 这个方法定义了组件的位置。
	         * setBounds(x, y, width, height)
	         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
	         */
	        userLabel.setBounds(50,50,80,25);
	        panel.add(userLabel);

	        /* 
	         * 创建文本域用于用户输入
	         */
	        final JTextField userText = new JTextField(20);
	        userText.setBounds(150,50,265,25);
	        panel.add(userText);
	        
	        

	        // 输入密码的文本域
	        JLabel passwordLabel = new JLabel("数据库密码:");
	        passwordLabel.setBounds(50,80,80,25);
	        panel.add(passwordLabel);

	        /* 
	         *这个类似用于输入的文本域
	         * 但是输入的信息会以点号代替，用于包含密码的安全性
	         */
	        final JPasswordField passwordText = new JPasswordField(20);
	        passwordText.setBounds(150,80,265,25);
	        panel.add(passwordText);
	        
	        
	        JLabel nameLabel = new JLabel("数据库实例:");
	        nameLabel.setBounds(50,110,80,25);
	        panel.add(nameLabel);
	        
	        final JTextField nameText = new JTextField(20);
	        nameText.setBounds(150,110,265,25);
	        panel.add(nameText);
	        
	        JLabel typeLabel = new JLabel("数据库类型:");
	        typeLabel.setBounds(50,140,80,25);
	        panel.add(typeLabel);
	        
	        
	        final JComboBox<String> selectBox = new JComboBox<String>();
	        
	        selectBox.setBounds(150,140,265,25);
	        selectBox.addItem("MySQL");
	        selectBox.addItem("Oracle");
	        panel.add(selectBox);
	        
	        
	        JLabel fileLabel = new JLabel("文档    类型:");
	        fileLabel.setBounds(50,170,80,25);
	        panel.add(fileLabel);
	        
	        
	        final JComboBox<String> fileBox = new JComboBox<String>();
	        
	        fileBox.setBounds(150,170,265,25);
	        fileBox.addItem("Word");
	        fileBox.addItem("PDF");
	        panel.add(fileBox);
	        
	        
	        
	        
	        
	        JButton loginButton = new JButton("生成文档");
	        loginButton.setBounds(180, 210, 120, 35);
	        
	        
	        final JLabel message = new JLabel();
	        message.setBounds(10,250,450,25);
	        panel.add(message);
	        
	        
	        loginButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					String url = urlText.getText().toString();
					String user = userText.getText().toString();
					String password = passwordText.getText().toString();
					String name = nameText.getText().toString();
					String type = selectBox.getSelectedItem().toString();
					String file = fileBox.getSelectedItem().toString();
					Generator generator = new Generator(url, user, password, name, type, file);
					message.setText(generator.makeDoc());
				}
			});
	        panel.add(loginButton);
	        
	       
	    }
}
