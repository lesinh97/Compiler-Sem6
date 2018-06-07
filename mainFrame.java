
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class mainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File file;
	final JFileChooser fc = new JFileChooser();
	private JPanel contentPane;
	private JTextArea textArea;
	private JScrollPane sp;
	private JScrollPane sp_1;
	private JTextArea textArea_1;
	private String forTok;
	private JButton btnOpen;
	private Scanner sc;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private static Parser prs;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 114, 607, 174);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setWrapStyleWord(true);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(55, 340, 607, 111);
		contentPane.add(scrollPane_1);
		
		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);

		
		JButton btnSub = new JButton("SUB");
		btnSub.setBounds(328, 83, 71, 20);
		contentPane.add(btnSub);
		
		JButton btnGet = new JButton("GET");
		btnGet.setBounds(409, 83, 71, 20);
		contentPane.add(btnGet);
		
		btnOpen = new JButton("Open");
		btnOpen.setBounds(247, 83, 71, 20);
		contentPane.add(btnOpen);
		
		JLabel lblInput = new JLabel("Input");
		lblInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInput.setBounds(55, 89, 71, 20);
		contentPane.add(lblInput);
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOutput.setBounds(55, 309, 71, 20);
		contentPane.add(lblOutput);
		
		
		btnOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 if (e.getSource() == btnOpen) {
				        int returnVal = fc.showOpenDialog(mainFrame.this);

				        if (returnVal == JFileChooser.APPROVE_OPTION) {
				            file = fc.getSelectedFile();
				            try {
								sc = new Scanner(file);
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				        }
				 }
				 sc.useDelimiter("\\z");
				 textArea.setText(sc.next());
			}
		});
		btnGet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				forTok = textArea.getText();
				System.out.println(forTok);
				new Parser(forTok);
//				Parser.tokenizer.display();
				Parser.nextToken();
				Parser.chuongtrinh();

				String tmp="";
				for (Token t : Parser.tokenizer.tokenData) {
					tmp+= t.getType() +" " + t.getToken()+ " " + t.getLine() +"\n";
				}
				tmp+= Parser.tmpString + " ";
				if(Parser.cuPhap == true) {
					tmp+= "\n" + "Dung cu phap";
				}
				textArea_1.setText(tmp);
				
			}
		});
	}
}
