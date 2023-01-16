package org.openjfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private RadioButton wholeImageRadioButton;
    @FXML
    private RadioButton circularAreaRadioButton;
    @FXML
    private RadioButton polygonalAreaRadioButton;
    @FXML
    private RadioButton identityMatrixKernelRadioButton;
    @FXML
    private RadioButton blur3x3MatrixKernelRadioButton;
    @FXML
    private RadioButton blur5x5MatrixKernelRadioButton;
    @FXML
    private RadioButton sharpen3x3MatrixKernelRadioButton;
    @FXML
    private RadioButton sharpen5x5MatrixKernelRadioButton;
    @FXML
    private RadioButton edgeDetectionMatrixKernelRadioButton;
    @FXML
    private Button applyButton;
    @FXML
    private Slider radiusSlider;
    @FXML
    private VBox sliderLayout;
    @FXML
    private MenuItem saveAsMenuItem;
    @FXML
    private MenuItem openMenuItem;
    @FXML
    private MenuItem resetMenuItem;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    protected void onWholeImageRadioButtonSelected() {
        System.out.println("Whole image area selected");
    }

    @FXML
    protected void onCircularAreaRadioButtonSelected() {
        System.out.println("Circular image area selected");
    }

    @FXML
    protected void onPolygonalAreaRadioButtonSelected() {
    }

    @FXML
    protected void onIdentityMatrixRadioButtonSelected() {
    }

    @FXML
    protected void on3x3BlurMatrixRadioButtonSelected() {
    }

    @FXML
    protected void on5x5BlurMatrixRadioButtonSelected() {
    }

    @FXML
    protected void on3x3SharpenMatrixRadioButtonSelected() {
    }

    @FXML
    protected void on5x5SharpenMatrixRadioButtonSelected() {
    }

    @FXML
    protected void onEdgeDetectionMatrixRadioButtonSelected() {
    }

    @FXML
    protected void onApplyButtonClick() {
    }

    @FXML
    protected void onSaveAsMenuItemClick() {
    }

    @FXML
    protected void onOpenMenuItemClick() {
    }

    @FXML
    protected void onResetMenuItemClick() {
    }
}