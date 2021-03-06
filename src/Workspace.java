import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import parser.Parser;
import MovementAndImageAPI.src.ImageUpdater;
import MovementAndImageAPI.src.PenHandler;
import MovementAndImageAPI.src.TurtleHandler;
import commands.CommandsFactory;
import commands.ICommand;

/**
 * Sets up the Slogo workspace 
 * which contains the feature buttons, display, previous commands list, and user input area.
 * @author Yoonhyung Choi
 *
 */
public class Workspace {
    
    private static final int DISPLAY_WIDTH = 700;
    private static final int DISPLAY_HEIGHT = 600; 
    private Canvas myBackDisplay;
    private Canvas myLineCanvas;
    private Canvas myTurtleCanvas;
    private CommandsFactory commandsFactory;
    private Parser parser;
    private GraphicsContext gcBack;
    private TurtleHandler myTurtleHandler;
    private ArrayList<Text> prevCommandList;
    private ObservableList<Text> prevCommandObsvList;
    private Map<Text, ICommand> prevCommandMap;
    private String userInput;
    private Button RefGridButton;
    private Button HelpPageButton;
    private Button TCButton;
    private Button TurtleDataButton;
    private Button FileChooserButton;
    
    /**
     * Returns the turtleHandler in the workspace
     * @return
     */
    public TurtleHandler getTurtleHandler() {
        return myTurtleHandler;
    }
    
    /**
     * Creates the Slogo workspace
     * @param primaryStage Stage where the workspace is located
     * @param root Group where the workspace is located
     * @return Tab that contains the workspace
     */
    public Tab createWorkspace (Stage primaryStage, Group root) {
        Tab tab = new Tab();
        Pane pane = new Pane();
        BorderPane bpane = new BorderPane();

        // Add back display canvas
        myBackDisplay = new Canvas(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        gcBack = myBackDisplay.getGraphicsContext2D();
        pane.getChildren().add(myBackDisplay);

        // Add line display canvas
        myLineCanvas = new Canvas(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        pane.getChildren().add(myLineCanvas);

        // Add turtle display canvas
        myTurtleCanvas = new Canvas(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        pane.getChildren().add(myTurtleCanvas);

        // Setting display positions
        myBackDisplay.toBack();
        myTurtleCanvas.toFront();

        // Setting pane(containing the displays) to the center of the borderpane.
        bpane.setCenter(pane);

        // adding imageUpdater
        ImageUpdater frontImageUpdater =
                new ImageUpdater(myTurtleCanvas, myLineCanvas);

        // adding my turtle
        myTurtleHandler = new TurtleHandler(frontImageUpdater);
        myTurtleHandler.updateImage(new
                Image(getClass().getResourceAsStream("/images/turtle.png")));

        // setting textbox settings
        TextField textBox = new TextField("");
        commandsFactory = new CommandsFactory();
        commandsFactory.setTurtleHandler(myTurtleHandler);
        parser = new Parser(commandsFactory);
        parser.createLogoParser();
        
        bpane.setBottom(textBox);
        
        // Add previousCommands box
        ListView<Text> prevCommandListView = new ListView<Text>();
        prevCommandList = new ArrayList<Text>();
        prevCommandMap = new HashMap<Text, ICommand>();
        prevCommandList.add(new Text("Previous Commands: "));
        prevCommandObsvList = FXCollections.observableArrayList(prevCommandList);
        prevCommandListView.setItems(prevCommandObsvList);
        bpane.setRight(prevCommandListView);
        sendUserInput(textBox, prevCommandListView);

        // Add Feature buttons on top
        bpane.setTop(addFeatureButtons(bpane, primaryStage, pane, myTurtleHandler.getPenHandler(), myTurtleHandler,
                                       root, frontImageUpdater));

        // Set borderpane to tab
        tab.setContent(bpane);

        return tab;
    }
    
    /**
     * Adds features and associated buttons.
     */
    @SuppressWarnings("unchecked")
    public Node addFeatureButtons (BorderPane bpane, Stage primaryStage, Pane pane, PenHandler penHandler,
                                   TurtleHandler turtleHandler, Group root, ImageUpdater iu) {

        HBox featureButtons = new HBox();

        ColorFeature ColorOptions = new ColorFeature();
        featureButtons.getChildren().addAll(new Text(" Pen:"),
                                            ColorOptions.changePenColor(penHandler),
                                            new Text(" Canvas:"),
                                            ColorOptions.changeBGColor(gcBack, DISPLAY_WIDTH,
                                                                       DISPLAY_HEIGHT));

        RefGridFeature RefGrid = new RefGridFeature();
        RefGridButton = RefGrid.makeButton("RefGrid On/Off",
                                           event -> RefGrid.showReferenceGrid(RefGridButton, pane, DISPLAY_WIDTH, DISPLAY_HEIGHT));

        HelpPageFeature HelpPage = new HelpPageFeature();
        HelpPageButton = HelpPage.makeButton("Help Page",
                                             event -> HelpPage.openHelpPage(HelpPageButton));

        TurtleChooserFeature TurtleChooser = new TurtleChooserFeature();
        TCButton = TurtleChooser.makeButton("Turtle Image",
                                            event -> TurtleChooser.openTurtleChooser(TCButton, root, iu, turtleHandler));

        TurtleDataFeature TurtleData = new TurtleDataFeature();
        TurtleDataButton =
                TurtleData.makeButton("Turtle Data",
                                      event -> TurtleData.openTurtleDataPage(TurtleDataButton,
                                                                             turtleHandler));
        
        
        FileChooserFeature FileChooser = new FileChooserFeature();
        FileChooserButton = TurtleData.makeButton("Open File", event -> FileChooser.openFileChooser(FileChooserButton, parser));

        //also add a language selector combobox, and handle the event here
        ObservableList<String> options = 
                FXCollections.observableArrayList(
                    "English",
                    "Chinese",
                    "French",
                    "Italian",
                    "Portuguese",
                    "Russian"
                );
        @SuppressWarnings({ "rawtypes", "unchecked" })
        final ComboBox languageComboBox = new ComboBox(options);
        languageComboBox.setPromptText("Choose a language");
        languageComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @SuppressWarnings("rawtypes")
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {                
                parser.setForeignLanguage(t1);              
            }    
        });

        featureButtons.getChildren().addAll(RefGridButton, HelpPageButton, TCButton,
                                            TurtleDataButton,FileChooserButton,languageComboBox);
        return featureButtons;
    }
    
    
    /**
     * Sends the user input to the parse, which then executes the command.
     * Adds the command to the previous command list.
     * @param textBox TextField where the user will enter the command
     * @param prevCommandListView ListView for the previous commands
     */
    public void sendUserInput (TextField textBox, ListView<Text> prevCommandListView) {
        textBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle (KeyEvent key) {
                ICommand command = null;
                if (key.getCode() == KeyCode.ENTER) {
                    userInput = textBox.getText();
                    try {
                        command = parser.parse(userInput);
                        command.execute();
                        Text userInputText = new Text(userInput);
                        prevCommandList.add(userInputText);
                        prevCommandMap.put(userInputText, command);
                        setUpPreviousCommands(prevCommandListView);
                    }
                    catch (Exception e) {
                        System.out.println("Invalid user command.");
                    }
                    textBox.clear();
                }
            }
        });
    }

    /**
     * Displays a listview of clickable previous commands.
     * Command executes when clicked.
     * @param prevCommandListView Listview for the previous commands
     */
    public void setUpPreviousCommands (ListView<Text> prevCommandListView) {
        prevCommandObsvList = FXCollections.observableArrayList(prevCommandList);
        prevCommandListView.setItems(prevCommandObsvList);
        for (int i = 0; i < prevCommandObsvList.size(); i++) {
            Text text = prevCommandObsvList.get(i);
            text.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent arg0) {
                    ICommand command = prevCommandMap.get(text);
                    command.execute();
                }
            });
        }
    }  
} 
