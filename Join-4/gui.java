
/**
 * four-in-a-row game that is definitely not Connect 4
 *
 * @author Lin Beliaeva
 * @version 17-05-22
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
    JPanel pan;
    
    //files
    final String fileA = "yellow.png";
    final String fileB = "blue.png";
    
    //table
    JTable tab;
    int rows = 6;
    int collumns = 9;
    Object[] cols = {};
    Object[][] data = {};
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

        //grid
        //trying to use example from https://stackoverflow.com/questions/21158083/how-to-set-the-value-of-specific-cell-in-jtable
        
        
        this.pack();
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
            lab = new JLabel("connect the 4, make the beep-boop not connect the 4");
            pan.add(lab); //adds label to panel
            this.add(pan); //adds panel to frame
            //fram.setSize(400,90); //sets frame size
            this.show(); //shows frame and its contents

            break;
        }

    }
}
