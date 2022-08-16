/**
 * four-in-a-row game that is definitely not Connect 4
 *
 * @author Lin Beliaeva
 * @version 12-08-22
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
    String text = "";

    //table
    JTable tab;
    int rows = 6; //maximum number of rows
    int columns = 7; //maximum number of columns
    int board[][] = new int[rows][columns]; //array that stores the states of the grid itself
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
    boolean gameInProgress=false; //whether or not the game has started
    boolean loadGame = false; //whether or not the game has been loaded. this is a separate boolean from gameInProgress so that gameplay can be ended, but the board will remain loaded
    int currentPlayer = 1; //whos turn it is. player 1 starts on default.
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

        addMouseListener(this);
        add(pan);
        setVisible(true);
        repaint();
    }

    public int returnColumn (int mFromLeft){
        double placeX = Math.floor((mFromLeft-70)/100);
        return (int) placeX;
        //I don't rmeember where exactly I got this method, but it is definitely not entirely my original work

    }

    public void changePlayer(){
        if(currentPlayer == 1) {
            currentPlayer = 2;
            if (gameInProgress)text = "Yellow's Turn";
            repaint();
            return;
        }
        if(currentPlayer == 2) {
            currentPlayer = 1;
            if (gameInProgress)text = "Blue's Turn";
            repaint();
            return;
        }

        //does what it says: changes player when trigerred. could be written better but its a basic piece of code anyway
    }

    public void gameWin(){
        gameInProgress = false;
        if(currentPlayer == 1) text = "Blue wins!"; repaint(); //there's probablly a better way of doing this
        if(currentPlayer == 2) text = "Yellow wins!"; repaint(); //seriously this is a lot of repaints
    }

    public void clearBoard(){
        for (int j=0;j<rows; j++){ // x = row, y = column
            for(int i=0; i<columns; i++){
                board[j][i]=0;
            }
        }
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
                            if (count>=4) gameWin();
                        } else {
                            count = 0; //if the streak stops, the counter restarts
                        }
                        //System.out.println("horizontal " + count);
                    }

                    //vertical win
                    for (int i=0; i<rows; i++){
                        if(board[i][mouseColumn]==currentPlayer){
                            count++;
                            if (count>=4) gameWin();
                        } else {
                            count = 0;
                        }
                        //System.out.println("vertical " + count);
                    }

                    //diagonal win (left to right)
                    for(int i = 5; i>=0; i--){
                        for(int j = 0; j < columns; j++){
                            for(int x = j, y = i; x < columns && y >= 0; x++, y--){
                                if (board[y][x] == currentPlayer){
                                    count++;
                                    if (count>=4) gameWin();
                                } else {
                                    count = 0;
                                }
                                System.out.println("Diagonal A count " + count);
                            }
                        }
                    }
                    
                    //diagonal win (right to left)
                    for(int i = 5; i>=0; i--){
                        for(int j = 6; j >= 0; j--){
                            for(int x = j, y = i; x >= 0 && y >= 0; x--, y--){
                                if (board[y][x] == currentPlayer){
                                    count++;
                                    if (count>=4) gameWin();
                                } else {
                                    count = 0;
                                }
                                System.out.println("Diagonal B count " + count);
                            }
                        }
                    }

                    changePlayer();

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
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Dialog", Font.PLAIN, 25));
        g2.drawString(text,80,740);
    }

    public void actionPerformed(ActionEvent e){
        String cmd=e.getActionCommand();
        switch(cmd){
            case "Quit":
                System.exit(0);
                break;
            case "HELP":
                //text stuff
                //text string outside of void
                //void loop for the dialog box itself
                break;
            case "Play": {
                gameInProgress = true; 
                loadGame = true; 
                clearBoard(); 
                if (currentPlayer == 1) text = "Blue Starts";
                else text = "Yellow Starts";
                repaint();
                break;}
        }

    }

}
