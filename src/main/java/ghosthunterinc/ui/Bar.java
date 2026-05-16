package fxproject.ui;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.Node;

public class Bar{
    private double posX;
    private double posY;
    private double barPercentage;
    private Rectangle rectangleBar;
    private Rectangle rectangleBackground;
    private VBox container;
    private Label barLabel;
    private final double BAR_HEIGHT = 400;
    private final double BAR_WIDTH  = 50;

    public Bar(double posX, double posY, String labelText, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.barPercentage = 100;

        this.barLabel = new Label(labelText);
        barLabel.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: 800;" +
                "-fx-letter-spacing: 1px;"
        );

        this.rectangleBackground = new Rectangle(BAR_WIDTH + 10, BAR_HEIGHT + 10, Color.BLACK);
        this.rectangleBar = new Rectangle(BAR_WIDTH, BAR_HEIGHT, color);

        StackPane barStack = new StackPane();
        barStack.setAlignment(Pos.BOTTOM_CENTER);
        barStack.getChildren().addAll(rectangleBackground, rectangleBar);

        // Add padding to background to create the border effect
        StackPane.setMargin(rectangleBar, new javafx.geometry.Insets(5));

        this.container = new VBox(10);
        this.container.setAlignment(Pos.CENTER);
        this.container.getChildren().addAll(barLabel, barStack);
        
        this.container.setTranslateX(posX);
        this.container.setTranslateY(posY);
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }
    public double getPosX() {
        return posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }
    public double getPosY() {
        return posY;
    }

    public void setRectangleBar(Rectangle rectangleBar) {
        this.rectangleBar = rectangleBar;
    }
    public Rectangle getRectangleBar() {
        return rectangleBar;
    }

    public void setRectangleBackground(Rectangle rectangle) {
        this.rectangleBackground = rectangle;
    }
    public Rectangle getRectangleBackground() {
        return rectangleBackground;
    }

    public void setBarLabel(Label barLabel) {
        this.barLabel = barLabel;
    }
    public Label getBarLabel() {
        return barLabel;
    }

    public void setBarPercentage(double barPercentage) {
        if((barPercentage <= 100) && (barPercentage >= 0)){
            this.barPercentage = barPercentage;

            double newHeight = BAR_HEIGHT * (barPercentage / 100.0);
            this.rectangleBar.setHeight(newHeight);
        }

        else if (barPercentage > 100) {
            this.barPercentage = 100;
            this.rectangleBar.setHeight(BAR_HEIGHT);
        }

        else if (barPercentage < 0){
            this.barPercentage = 0;
            this.rectangleBar.setHeight(0);
        }
    }
    public double getBarPercentage() {
        return barPercentage;
    }

    public Node getGroup() { return container; }

}
