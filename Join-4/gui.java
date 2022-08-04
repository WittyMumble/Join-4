/**
 * four-in-a-row game that is definitely not Connect 4
 *
 * @author Lin Beliaeva
 * @version 02-08-22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; //listener
import java.util.Scanner; //input scanner

public class gui extends JFrame implements ActionListener, MouseListener
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

    int x = 100; //game grid x
    int y = 70; //game grid y
    Canvas canv;

    //image files
    final String player1="player.png";
    final String player2="player2.png";
    final String empty="empty.png";
    final String select="select.png";
    //image icons
    ImageIcon image1=    new ImageIcon(player1);
    ImageIcon image2=    new ImageIcon(player2);
    ImageIcon imageEmpty= new ImageIcon(empty);
    ImageIcon image3=    new ImageIcon(select);

    //game statuses
    boolean gameInProgress=false;
    boolean loadGame = false;
    int currentPlayer = 1; //whos turn it is. player 1 starts.
    public gui()
    {
        System.out.println('\u000c'); //clears terminal  
        setTitle("Join 4"); //sets window title
        menuX = 850;
        menuY = 800;

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

        JPanel pan = new JPanel();
        pan.setPreferredSize(new Dimension(700,600));
        canv = new Canvas();
        pan.add(canv);

        //board[0][0] = 1; //test
        
        addMouseListener(this);
        add(pan);
        setVisible(true);
        repaint();
    }

    public int returnColumn (int mFromLeft){
        double placeX = Math.floor((mFromLeft-70)/100);
        return (int) placeX; //copied all this bc can't think of more efficient way rn

    }

    public void changePlayer(){
        if(currentPlayer == 1) {
            currentPlayer = 2;
            return;
        }
        if(currentPlayer == 2) {
            currentPlayer = 1;
            return;
        }
        //does what it says: changes player when trigerred. could be written better but its a basic piece of code anyway
    }
    
    public void gameWin(){
        if(currentPlayer == 1) System.out.println("Blue wins!");
        if(currentPlayer == 2) System.out.println("Yellow wins!");
        gameInProgress = false;
    }

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}    

    public void mouseReleased(MouseEvent e){}

    public void mousePressed(MouseEvent e){}

    public void mouseClicked(MouseEvent e){
        if (gameInProgress){    
        //System.out.println("mouse click");
            //when player clicks, column is chosen in accordance to mouse X position, and then piece is placed in bottom-most open row within that column
            //mouse
            int mouseX = e.getX(); 
            int mouseColumn = returnColumn(mouseX);
            int mouseRow;
            int countRow = 5; //current row for point counting purposes
            if ((mouseX >= x)&&(mouseX<=(x+ columns*100))){//grid boundaries. if mouse is outside the grid then it doesnt care
                if (board[0][mouseColumn] == 0){ //if there is space in the column (if the top slot isn't taken)
                    //placing pieces
                    for(mouseRow = 0;mouseRow<rows;mouseRow++){//for loop checking all rows
                        if(board[mouseRow][mouseColumn] > 0){ //if slots aren't empty
                            board[mouseRow-1][mouseColumn] = currentPlayer; //sets slot to current player, aka places the piece
                            countRow = mouseRow-1;//sets variable for counting horizontal tokens
                            System.out.println("row is " + countRow);
                            break;
                        }
                    }
                    board[mouseRow-1][mouseColumn] = currentPlayer;
                    
                    //horizontal win
                    int count = 0; //reset counter
                    
                    System.out.println("player is " + currentPlayer);
                    for (int i=0; i<columns;i++){ //for loop for checking through all columns
                        if(board[countRow][i]==currentPlayer){
                            count++; //if a given slot has the token of the current player, counter goes up
                            if (count==4) gameWin();
                        } else {
                            count = 0; //if the streak stops, the counter restarts
                        }
                        System.out.println(count);
                        
                    }
                    changePlayer();
                    
                    //vertical win
                    //diagonal win
                    
                } else { //else if the top of the column is already full
                    System.out.println("this column is full!"); //change to dialog box later?
                }
    
                //y value
                //status
                repaint();
            };
            System.out.println("column is " + mouseColumn);
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        if (loadGame){    
            for (int row=0;row<rows; row++){ // x = row, y = column
                for(int col=0; col<columns; col++){
                    switch(board[row][col]){
                        case 0: //empty cell
                        imageEmpty.paintIcon(this,g,col*100+y,row*100+x);
                        break; //100 = accounting for image x and y
                        case 1: //player 1
                        image1.paintIcon(this,g,col*100+y,row*100+x);
                        break;
                        case 2: //player 2
                        image2.paintIcon(this,g,col*100+y,row*100+x);
                        break;
                        case 3:
                        image3.paintIcon(this,g,col*100+y,row*100+x);
                        default:
                        break;
                    }
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e){
        String cmd=e.getActionCommand();
        switch(cmd){
            case "Quit":
            System.exit(0);
            break;
            case "HELP":
            //text stuff
            // try adding it as a panel again but if that goes to shit maybe try dialog boxes instead
            break;
            case "Play": gameInProgress = true; loadGame = true; repaint();
            break;
        }

    }

}
