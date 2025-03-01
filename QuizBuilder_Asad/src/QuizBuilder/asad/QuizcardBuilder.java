package QuizBuilder.asad;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class QuizcardBuilder {
    private String question;
    private String answer;
    
    public QuizcardBuilder(String question, String answer) {
    	this.question = question;
    	this.answer = answer;
    }
    
    String getQuestion(){
    	return question;
    }
    String getAnswer(){
    	return answer;
    }
}
class Quizcards{
	private ArrayList<QuizcardBuilder> cardList = new ArrayList<>();
	private JFrame frame;
	private JTextArea question;
	private JTextArea answer;
	public static void main(String[] args) {
		Quizcards obj = new Quizcards();
		obj.go();
	}
	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.add(new JLabel("Question:"));
		Font font = new Font("serif", Font.BOLD, 20);
	     question = createTextArea(font);
	     question.requestFocus();
	     answer = createTextArea(font);
		JScrollPane quesScroll = createScroller(question);
		JScrollPane ansScroll = createScroller(answer);
		
		panel.add(quesScroll);
		panel.add(new JLabel("Answer"));
		panel.add(ansScroll);
		
		JButton next = new JButton("Next Card");
		next.addActionListener(e -> nextCard());
		panel.add(next);
		JMenuBar bar = new JMenuBar();
		JMenu list = new JMenu("File");
		JMenuItem item = new JMenuItem("Save");
		item.addActionListener(e -> saveCards());
		
		bar.add(list);
		list.add(item);
		
		frame.setJMenuBar(bar);
		frame.getContentPane().add(panel);
		frame.setSize(400,530);
		frame.setVisible(true);
		
		
	}
	
	public JTextArea createTextArea(Font font) {
		JTextArea ques = new JTextArea(6,20);
		ques.setFont(font);
		ques.setWrapStyleWord(true);
		ques.setLineWrap(true);
		return ques;
	}
	public JScrollPane createScroller(JTextArea area) {
		JScrollPane sc = new JScrollPane(area);
		sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		return sc;
	}
	public void nextCard() {
		QuizcardBuilder card = new QuizcardBuilder(question.getText(), answer.getText());
		cardList.add(card);
		clearField();
	}
	void clearField() {
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}
	void clearAll() {
		cardList.clear();
		question.setText("");
		answer.setText("");
	}
	public void saveCards() {
		QuizcardBuilder card = new QuizcardBuilder(question.getText(), answer.getText());
		cardList.add(card);
		JFileChooser save = new JFileChooser();
		save.showSaveDialog(frame);
		saveIt(save.getSelectedFile());
		
	}
	public void saveIt(File file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for(QuizcardBuilder c : cardList) {
			writer.write(c.getQuestion()+ "/" +c.getAnswer() + "\n");

			}
			writer.close();
		}catch(Exception e) {
				e.printStackTrace();			}
		clearAll();
		}
	}

