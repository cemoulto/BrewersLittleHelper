package se.angstroms.blh.anders.view.recipe.selector.grid;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javax.inject.Inject;
import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.UnitStringFormatter;

/**
 *
 * @author eriklark
 */
public class GridButton extends FlowPane {

    private final FullContext recipe;

    @Inject
    private UnitStringFormatter unitStringFormatter;

    public GridButton(FullContext recipe) {
        this.recipe = recipe;
        this.getStyleClass().add("recipe-grid-button");
        this.getStylesheets().add("styles/anders.css");
    }

    public void render() {
        addImage();
        addRecieName();
        addRandomFacts();

        this.setPrefWidth(220);
    }

    private void addImage() {
        Canvas image = new Canvas(100, 150);
        image.getGraphicsContext2D().fillRect(0, 0, image.getWidth(), image.getHeight());

        HBox lol = new HBox(image);
        lol.getStyleClass().add("recipe-grid-image");
        this.getChildren().add(lol);
    }

    private void addRecieName() {
        Label name = new Label(recipe.nameProperty().get());
        name.setFont(Font.font(name.getFont().getFamily(), FontWeight.BOLD, name.getFont().getSize()));
        name.getStyleClass().add("recipe-grid-name");
        this.getChildren().add(name);
    }

    private void addRandomFacts() {
        this.getChildren().add(new Label("· OG: " + unitStringFormatter.format(recipe.getOriginalGravity())));
        this.getChildren().add(new Label("· ABV: " + unitStringFormatter.format(recipe.getAlcoholContent())));
        this.getChildren().add(new Label("· IBU: " + unitStringFormatter.format(recipe.getBitterness())));
        this.getChildren().add(new Label("· Last brewed: Yesterday"));
    }

    public FullContext getRecipe() {
        return recipe;
    }
}
