import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame (){
        this.add(new GamePanel()); //create a game panel
        this.setTitle("Snake"); // Title of the game
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // by default, closing application does not stop game, so I changed default settings
        this.setResizable(false); // I set it so that you can't change a panel size
        this.pack();
        this.setVisible(true); // make panel visible
        this.setLocationRelativeTo(null); // center the panel

    }
}
