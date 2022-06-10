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
    String text = "connect the 4, make the beep-boop not connect the 4";
    
    //table
    JTable tab;
    int rows = 6;
    int columns = 7;
    int board[][] = new int[rows][columns];
    
    int x;
    int y;
    Canvas canv;
    
    final String player1="player.png";
    final String player2="player2.png";
    final String empty="empty.png";
    ImageIcon image1=    new ImageIcon(player1);
    ImageIcon image2=    new ImageIcon(player2);
    ImageIcon imageEmpty= new ImageIcon(empty);
    
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
        
        x = 200;
        y = 200;
        JPanel pan = new JPanel();
        pan.setPreferredSize(new Dimension(700,600));
        canv = new Canvas();
        pan.add(canv);
    
        
        add(pan);
        setVisible(true);
        repaint();
    }
    
    public void paint(Graphics g){
        super.paint(g);
        for (int row=0;row<6; row++){ // x - row, y-column
            for(int col=0; col<7; col++){
                switch(board[row][col]){
                    case 0: //empty cell
                        imageEmpty.paintIcon(this,g,col*100+100,row*100+100);
                        break;
                    case 1: //player 1
                        image1.paintIcon(this,g,col*100+100,row*100+100);
                        break;
                    case 2: //player 2
                        image2.paintIcon(this,g,col*100+100,row*100+100);
                        break;
                    default:
                        break;
                }
            }
        }
        
    }
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void actionPerformed(ActionEvent e){
        String cmd=e.getActionCommand();
        switch(cmd){
            case "Quit":
                System.exit(0);
                break;
            case "HELP":
                //text stuff
               /* fram = new JFrame("info");
                pan = new JPanel(); //creates panel
                lab = new JLabel(text);
                pan.add(lab); //adds label to panel
                this.add(pan); //adds panel to frame
                fram.setSize(400,90); //sets frame size
                this.show(); //shows frame and its contents*/
                break;
            case "Play": gameStart = true;
                break;
        }

    }
    
}
