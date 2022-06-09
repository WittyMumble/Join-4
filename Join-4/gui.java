
/**
 * four-in-a-row game that is definitely not Connect 4
 *
 * @author Lin Beliaeva
 * @version 9-06-22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; //listener
import java.util.Scanner; //input scanner

public class gui extends JFrame implements ActionListener
{
    //menu variables
    private int menuX;
    private int menuY;
    JMenuBar bar;
    JMenu File, Help;
    JMenuItem A, B, C; //A=Play, B=Quit, C=Help
    static JFrame fram;
    static JLabel lab = new JLabel("");
    JPanel pan = new JPanel();
    String text = "connect the 4, make the beep-boop not connect the 4";
    
    //table
    JTable tab;
    int rows = 6;
    int columns = 7;
    int board[][] = new int[rows][columns];
    
    //Images
    JPanel game = new JPanel();
    Canvas graphic;
    String fileName;
    private ImageIcon piece = new ImageIcon(fileName);
    int imX = 100;
    int imY = 100;
    
    
    //game
    boolean gameStart=true;
    public gui()
    {
        setTitle("Join 4"); //sets window title
        menuX = 1000;
        menuY = 1000;

        this.getContentPane().setPreferredSize(new Dimension (menuX,menuY)); //set dimensions
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //what happens on close (exits the window)
        this.pack(); //sizes window so content = above preferred size
        this.toFront(); //whether the window is placed infront of other windows
        this.setVisible(true); //visible window
        this.setResizable(false);
        pan.setLayout(new GridLayout(rows,columns));

        bar = new JMenuBar(); //creates menu bar
        this.setJMenuBar(bar); //sets created bar as the menu bar being used

        //File Menu
        File = new JMenu("File"); //file menu
        bar.add(File); //add file menu to bar

        JMenuItem A= new JMenuItem("Play"); //creates play button
        A.addActionListener(this);
        File.add(A); // adds play button to file menu

        JMenuItem B= new JMenuItem("Quit");
        B.addActionListener(this);
        File.add(B);
        //Help menu
        Help = new JMenu("Help"); //creates help menu
        bar.add(Help);// adds help menu to bar

        JMenuItem C= new JMenuItem("HELP");
        C.addActionListener(this);
        Help.add(C); //adds help button to help menu
        this.pack();
        //https://stackoverflow.com/questions/22583164/simple-java-game-using-grid
        //Game board
        game.setPreferredSize(new Dimension(imX,imY));
        graphic = new Canvas();
       
        for (int x=0;x<rows;x++){
            for(int y=0;y<columns;y++){
                board[x][y] = 0;
                fileName = "empty.png";
                game.add(graphic); 
                imY+=50;
            }
            imX+=50;
        }
        
        add(pan);
        setVisible(true);
        
    }
    public void paint (Graphics g) {
        super.paint(g);
        piece.paintIcon(this,g,200,200);
    }
    public void actionPerformed(ActionEvent e){
        String cmd=e.getActionCommand();
        switch(cmd){
            case "Quit":
                System.exit(0);
                break;
            case "HELP":
                //text stuff
                fram = new JFrame("info");
                pan = new JPanel(); //creates panel
                lab = new JLabel(text);
                pan.add(lab); //adds label to panel
                this.add(pan); //adds panel to frame
                //fram.setSize(400,90); //sets frame size
                this.show(); //shows frame and its contents
                break;
            case "Play": gameStart = true;
                break;
        }

    }
    
}
