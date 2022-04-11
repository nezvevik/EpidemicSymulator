package cz.cvut.fel.pjv.handlers;

import cz.cvut.fel.pjv.SimulationModel;
import cz.cvut.fel.pjv.models.person.BasePerson;
import cz.cvut.fel.pjv.models.person.DisobedientPerson;
import cz.cvut.fel.pjv.models.person.ObedientPerson;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class SimulationCanvasHandler implements Handler{
    public void draw(SimulationModel model) {
        Stage stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root);

        for (int i = 0; i < model.getPeople().size(); i++) {
            BasePerson person = model.getPeople().get(i);
            if (person instanceof ObedientPerson) {
                Circle shape = new Circle();
                shape.setRadius(5);
                shape.setCenterX(person.getPosition().getX());
                shape.setCenterY(person.getPosition().getY());
                root.getChildren().add(shape);
            } else  {
                Rectangle shape = new Rectangle();
                shape.setHeight(10);
                shape.setWidth(10);
                shape.setX(person.getPosition().getX());
                shape.setY(person.getPosition().getY());
                root.getChildren().add(shape);
            }
        }



        stage.setScene(scene);
        stage.show();



    }
    private void drawPerson(BasePerson basePerson) {

    }


    @Override
    public void draw() {}
    public void clear() {}
}
