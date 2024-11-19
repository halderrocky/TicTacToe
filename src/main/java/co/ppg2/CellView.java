package co.ppg2;




import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;




public class CellView extends StackPane {




    private Button button;
    private int row, col;
    private GameController gameController;




    public CellView(int row, int col, GameController gameController) {
        this.row = row;
        this.col = col;
        this.gameController = gameController;




        // Initialize the button and add it to the cell view
        button = new Button();
        button.setPrefSize(100, 100);
        getChildren().add(button);




        // Set action for button click
        button.setOnAction(e -> handleClick());
    }




    private void handleClick() {
        // Only proceed if the cell is empty
        if (gameController.getCell(row, col) == null || gameController.getCell(row, col).getToken() == ' ') {
            char currentPlayerToken = gameController.getWhoseTurn();




            // Set the token in GameController and update button text
            gameController.setCell(row, col, new CellBase() {
                @Override
                public void setToken(char token) {
                    this.token = currentPlayerToken;
                    button.setText(String.valueOf(currentPlayerToken));
                }
            });




            // Check if there's a winner or if the board is full
            if (gameController.isWon(currentPlayerToken)) {
                gameController.getGameView().handleGameOver(currentPlayerToken);  // Handle win
            } else if (gameController.isFull()) {
                gameController.getGameView().handleTie();  // Handle tie
            } else {
                // Switch player turn and prepare for the next move
                gameController.switchTurn();
            }
        }
    }
}
