package QuizBuilder.asad;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class QuizcardPlayer {
	private ArrayList<QuizcardBuilder> cardList = new ArrayList<>();
	private JFrame frame;
	private JTextArea area;
	private JButton next;
	private QuizcardBuilder card;
	private boolean flag;
	private int currentIt = 0;
       public static void main(String[] args) {
    	   new QuizcardPlayer().go();
       }
       public void go() {
    	   frame = new JFrame();
    	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	   JPanel panel = new JPanel();
    	   panel.setBackground(Color.LIGHT_GRAY);
    	   area = new JTextArea(9,20);
    	   area.setFont(new Font("serif",Font.BOLD,20));
    	   area.setLineWrap(true);
    	   area.setWrapStyleWord(true);
    	   area.setEditable(false);
    	   JScrollPane scroll = new JScrollPane(area);
    	   scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	   scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    	   panel.add(scroll);
    	   
    	   next = new JButton("Next Card");
    	   next.addActionListener(e-> nextCard());
    	   panel.add(next);
    	   
    	   JMenuBar bar = new JMenuBar();
    	   JMenu list = new JMenu("File");
    	   JMenuItem item = new JMenuItem("Load Cards");
    	   item.addActionListener(e-> loadCards());
    	   bar.add(list);
    	   list.add(item);
    	   
    	   frame.setJMenuBar(bar);
    	   frame.getContentPane().add(panel);
    	   frame.setSize(400,400);
    	   frame.setVisible(true);
       }
       public void loadCards() {
    	   JFileChooser open = new JFileChooser();
    	   open.showOpenDialog(frame);
    	   makeCards(open.getSelectedFile());
    	   
       }
       public void makeCards(File file) {
    	   try {
    		   BufferedReader reader = new BufferedReader(new FileReader(file));
    		   String line;
    		   while((line = reader.readLine()) != null) {
    			   String[] mCards = line.split("/");
    			   QuizcardBuilder card1 = new QuizcardBuilder(mCards[0],mCards[1]);
    			   cardList.add(card1);
    		   }
    		   reader.close();
    		   
    	   }catch(Exception e) {
    		   e.printStackTrace();
    		   }
    	   showQuestion();
       }
       public void showQuestion() {
    	   card = cardList.get(currentIt);
    	   currentIt++;
    	   area.setText(card.getQuestion());
    	   next.setText("Show Answer");
    	   flag = true;
    	   
       }
       public void nextCard() {
    	   if(flag) {
    	   area.setText(card.getAnswer());
    	   next.setText("Next Card");
    	   flag = false;
    	   }else {
    	   if(currentIt<cardList.size()) {
    		   showQuestion();
    	   }
    	   else {
    		   area.setText("Cards ended lol!");
    	   }
       }
}
}
