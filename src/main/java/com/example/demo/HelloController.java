package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.*;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

class tiles{
    private double xCoordinate;
    private double yCoordinate;

    public  tiles(double x, double y){
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public double getX(){
        return this.xCoordinate;
    }

    public  double getY(){
        return this.yCoordinate;
    }
}

class player{
    private int currentTile;

    private  ImageView token;

    private  String name;

    @FXML
    public Label winningPlayer;

    @FXML
    private  ImageView homeButton;

    private  Boolean isUnlocked;
    public  player(int tile, ImageView token, String name){
        this.currentTile = tile;
        this.token = token;
        isUnlocked = false;
        this.name = name;
    }

    public void setCurrentTile(int currentTile) {
        this.currentTile = currentTile;
    }

    public int getCurrentTile(){
        return this.currentTile;
    }

    public ImageView getToken(){
        return this.token;
    }

    public Boolean getIsUnlocked(){
        return this.isUnlocked;
    }

    public void setIsUnlocked(Boolean flag){
        this.isUnlocked = flag;
    }

    public void makeMovement(ArrayList<tiles> tilesArrayList) throws InterruptedException, IOException {
        if (!HelloController.currentPlayer.getIsUnlocked()) {
            if (HelloController.currentNumber == 1) {
                HelloController.currentPlayer.setCurrentTile(1);
                HelloController.currentPlayer.setIsUnlocked(true);
                TranslateTransition translate = new TranslateTransition(Duration.millis(600), HelloController.currentPlayer.getToken());
                translate.setToX(-5);
                translate.setToY(-50);
                translate.play();
            }
        }
        else{
            this.move(tilesArrayList);
        }
    }

    @FXML
    public void move(ArrayList<tiles> tilesArrayList) throws InterruptedException, IOException {
        int currentNumber = HelloController.currentNumber;
        ArrayList<TranslateTransition> translateList = new ArrayList<TranslateTransition>();
        SequentialTransition s = null;
        if(currentTile + currentNumber <= 100){
            if(currentTile%10 == 0){
                TranslateTransition ty = new TranslateTransition(Duration.millis(600), this.token);
                TranslateTransition tx = new TranslateTransition(Duration.millis(600), this.token);
                translateList.add(ty);
                translateList.add(tx);
                ty.setByY(-53.5);
                if(((currentTile+1)/10)%2==0){
                    tx.setByX(39.5*(currentNumber-1));
                }
                else{
                    tx.setByX(-39.5*(currentNumber-1));
                }
                currentTile += currentNumber;
            }
            else{
                if(10-(currentTile%10) < currentNumber){
                    TranslateTransition tx1 = new TranslateTransition(Duration.millis(600),this.token);
                    TranslateTransition ty = new TranslateTransition(Duration.millis(600),this.token);
                    TranslateTransition tx2 = new TranslateTransition(Duration.millis(600),this.token);
                    translateList.add(tx1);
                    translateList.add(ty);
                    translateList.add(tx2);

                    if(((currentTile)/10)%2==0){
                        tx1.setByX(35*(10-currentTile%10));
                    }
                    else{
                        tx1.setByX(-35*(10-currentTile%10));
                    }
                    ty.setByY(-53.5);
                    currentTile += currentNumber;
                    if(((currentTile)/10)%2==0){
                        tx2.setByX(39.5*((currentTile%10)-1));
                    }
                    else{
                        tx2.setByX(-39.5*((currentTile%10)-1));
                    }

                }
                else{
                    TranslateTransition tx = new TranslateTransition(Duration.millis(600), this.token);
                    translateList.add(tx);
                    if(((currentTile)/10)%2==0){
                        tx.setByX(39.5*(currentNumber));
                    }
                    else{
                        tx.setByX(-39.5*(currentNumber));
                    }
                    currentTile += currentNumber;
                    tx.play();
                }
            }

        }
        TranslateTransition translate = new TranslateTransition(Duration.millis(600), this.token);
        if(this.currentTile == 3){
            translate.setByX(-80);
            translate.setByY(-108);
            this.currentTile = 21;
        }
        else if(this.currentTile == 8){
            translate.setByX(-80);
            translate.setByY(-216);
            this.currentTile = 46;
        }
        else if(this.currentTile == 16){
            translate.setByX(40);
            translate.setByY(-57);
            this.currentTile = 16;
        }
        else if(this.currentTile == 29){
            translate.setByX(-40);
            translate.setByY(-54);
            this.currentTile = 33;
        }
        else if(this.currentTile == 37){
            translate.setByX(40);
            translate.setByY(-162);
            this.currentTile = 65;
        }
        else if(this.currentTile == 50){
            translate.setByX(0);
            translate.setByY(-108);
            this.currentTile = 70;
        }
        else if(this.currentTile == 61){
            translate.setByX(40);
            translate.setByY(-108);
            this.currentTile = 82;
        }
        else if(this.currentTile == 64){
            translate.setByX(0);
            translate.setByY(-54);
            this.currentTile = 77;
        }
        else if(this.currentTile == 76){
            translate.setByX(40);
            translate.setByY(-108);
            this.currentTile = 95;
        }
        else if(this.currentTile == 89){
            translate.setByX(40);
            translate.setByY(-54);
            this.currentTile = 91;
        }
        if(currentTile == 100){
            Stage st = new Stage();
            FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("winningScreen.fxml"));
            Scene scene = new Scene(fxmlLoader1.load(), 210, 208);
            HelloController root = fxmlLoader1.getController();
            root.set_Stage(st);
            st.setScene(scene);
            st.show();
        }
        System.out.println(this.currentTile);
        TranslateTransition snake1 = new TranslateTransition(Duration.millis(40), this.token);
        TranslateTransition snake2 = new TranslateTransition(Duration.millis(40), this.token);
        TranslateTransition snake3 = new TranslateTransition(Duration.millis(40), this.token);
        TranslateTransition snake4 = new TranslateTransition(Duration.millis(40), this.token);
        TranslateTransition snake5 = new TranslateTransition(Duration.millis(40), this.token);
        TranslateTransition snake6 = new TranslateTransition(Duration.millis(40), this.token);
        TranslateTransition snake7 = new TranslateTransition(Duration.millis(40), this.token);
        TranslateTransition snake8 = new TranslateTransition(Duration.millis(40), this.token);
        Boolean isBitten = false;
        if(this.currentTile == 24){
            isBitten = true;
            snake1.setByX(23);
            snake1.setByY(11);
            snake2.setByX(10);
            snake2.setByY(18);
            snake3.setByX(-1);
            snake3.setByY(16);
            snake4.setByX(-11.2);
            snake4.setByY(11.2);
            snake5.setByX(-14.4);
            snake5.setByY(11.2);
            snake6.setByX(1.6);
            snake6.setByY(22.4);
            snake7.setByX(15.6);
            snake7.setByY(5.6);
            snake8.setByX(4);
            snake8.setByY(13.4);
            this.currentTile = 5;
        }
        else if(currentTile == 43){
            isBitten = true;

            snake1.setByX(-18.5);
            snake1.setByY(8.8);
            snake2.setByX(-14.6);
            snake2.setByY(10.4);
            snake3.setByX(-6.4);
            snake3.setByY(19.2);
            snake4.setByX(11.2);
            snake4.setByY(18.4);
            snake5.setByX(15.2);
            snake5.setByY(12);
            snake6.setByX(7.2);
            snake6.setByY(17.4);
            snake7.setByX(-28.8);
            snake7.setByY(14.4);
            snake8.setByX(-6.4);
            snake8.setByY(18.4);
            this.currentTile = 22;
        }
        else if(this.currentTile == 56){
            isBitten = true;

            snake1.setByX(8.8);
            snake1.setByY(26.4);
            snake2.setByX(6.4);
            snake2.setByY(16.8);
            snake3.setByX(-16);
            snake3.setByY(18.4);
            snake4.setByX(-3.2);
            snake4.setByY(20);
            snake5.setByX(11.2);
            snake5.setByY(25.6);
            snake6.setByX(-7.2);
            snake6.setByY(22.4);
            snake7.setByX(12.8);
            snake7.setByY(23.2);
            snake8.setByX(-9.6);
            snake8.setByY(12);
            this.currentTile = 25;
        }
        else if(currentTile == 60){
            isBitten = true;

            snake1.setByX(16);
            snake1.setByY(6.4);
            snake2.setByX(12.8);
            snake2.setByY(6.4);
            snake3.setByX(8.8);
            snake3.setByY(12);
            snake4.setByX(-3.2);
            snake4.setByY(10.4);
            snake5.setByX(-16);
            snake5.setByY(8.8);
            snake6.setByX(-11.2);
            snake6.setByY(12);
            snake7.setByX(12.8);
            snake7.setByY(11.2);
            snake8.setByX(11.8);
            snake8.setByY(-10.4);
            this.currentTile = 42;
        }
        else if(currentTile == 69){
            isBitten = true;

            snake1.setByX(-23.4);
            snake1.setByY(8);
            snake2.setByX(-14.4);
            snake2.setByY(9.6);
            snake3.setByX(-2.4);
            snake3.setByY(22.4);
            snake4.setByX(16.8);
            snake4.setByY(15.2);
            snake5.setByX(16.8);
            snake5.setByY(14.4);
            snake6.setByX(2.4);
            snake6.setByY(17.6);
            snake7.setByX(-21.6);
            snake7.setByY(0.8);
            snake8.setByX(-15.2);
            snake8.setByY(14.4);
            this.currentTile = 48;
        }
        else if(currentTile == 86){
            isBitten = true;

            snake1.setByX(14.8);
            snake1.setByY(7.6);
            snake2.setByX(14.4);
            snake2.setByY(6.4);
            snake3.setByX(15.2);
            snake3.setByY(23.2);
            snake4.setByX(-17.6);
            snake4.setByY(31.2);
            snake5.setByX(-29.6);
            snake5.setByY(22.4);
            snake6.setByX(30.4);
            snake6.setByY(28);
            snake7.setByX(36);
            snake7.setByY(15);
            snake8.setByX(8);
            snake8.setByY(18);
            this.currentTile = 53;
        }
        else if(currentTile == 90){
            isBitten = true;

            snake1.setByX(-18.4);
            snake1.setByY(8.8);
            snake2.setByX(-16);
            snake2.setByY(6.4);
            snake3.setByX(-8.8);
            snake3.setByY(6.4);
            snake4.setByX(2.4);
            snake4.setByY(16.8);
            snake5.setByX(14.4);
            snake5.setByY(8);
            snake6.setByX(15.2);
            snake6.setByY(8.8);
            snake7.setByX(-6.2);
            snake7.setByY(12);
            snake8.setByX(-21.6);
            snake8.setByY(-8.8);
            this.currentTile = 72;
        }
        else if(currentTile == 94){
            isBitten = true;

            snake1.setByX(23.2);
            snake1.setByY(6.4);
            snake2.setByX(14.4);
            snake2.setByY(11.2);
            snake3.setByX(2.4);
            snake3.setByY(28.8);
            snake4.setByX(-24);
            snake4.setByY(19.2);
            snake5.setByX(-3.2);
            snake5.setByY(26.4);
            snake6.setByX(22.4);
            snake6.setByY(0.8);
            snake7.setByX(9.6);
            snake7.setByY(10.4);
            snake8.setByX(-8.8);
            snake8.setByY(9.6);
            this.currentTile = 73;
        }
        else if(currentTile == 96){
            isBitten = true;

            snake1.setByX(-14.4);
            snake1.setByY(7.2);
            snake2.setByX(-11.2);
            snake2.setByY(3.2);
            snake3.setByX(-12);
            snake3.setByY(10.4);
            snake4.setByX(2.4);
            snake4.setByY(14.4);
            snake5.setByX(12);
            snake5.setByY(12);
            snake6.setByX(15.2);
            snake6.setByY(10.4);
            snake7.setByX(-6.4);
            snake7.setByY(10.4);
            snake8.setByX(-22.4);
            snake8.setByY(-8);
            this.currentTile = 84;
        }
        else if(currentTile == 98){
            isBitten = true;

            snake1.setByX(0.8);
            snake1.setByY(12);
            snake2.setByX(-12.8);
            snake2.setByY(17.6);
            snake3.setByX(10.4);
            snake3.setByY(21.6);
            snake4.setByX(9.6);
            snake4.setByY(20);
            snake5.setByX(-11.2);
            snake5.setByY(27.2);
            snake6.setByX(10.4);
            snake6.setByY(32);
            snake7.setByX(-16.8);
            snake7.setByY(38.4);
            snake8.setByX(11.2);
            snake8.setByY(45.6);
            this.currentTile = 58;
        }
        if(!isBitten) {
            if (translateList.size() == 1) {
                s = new SequentialTransition(translateList.get(0), translate);
            } else if (translateList.size() == 2) {
                s = new SequentialTransition(translateList.get(0), translateList.get(1), translate);
            } else if (translateList.size() == 3) {
                s = new SequentialTransition(translateList.get(0), translateList.get(1), translateList.get(2), translate);
            }
        }
        else{
            if (translateList.size() == 1) {
                s = new SequentialTransition(translateList.get(0), snake1,snake2,snake3,snake4,snake5,snake6,snake7,snake8);
            } else if (translateList.size() == 2) {
                s = new SequentialTransition(translateList.get(0), translateList.get(1), snake1,snake2,snake3,snake4,snake5,snake6,snake7,snake8);
            } else if (translateList.size() == 3) {
                s = new SequentialTransition(translateList.get(0), translateList.get(1), translateList.get(2), snake1,snake2,snake3,snake4,snake5,snake6,snake7,snake8);
            }
        }
        if(s!= null) {
            s.play();
        }

    }

    public void homeButtonClicked(MouseEvent mouseEvent) throws InterruptedException {
        HelloApplication.main(null);
    }
}
public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private ImageView dice;

    @FXML
    private Button start;

    @FXML
    private  ImageView arrow;

    @FXML
    public ImageView redToken;

    @FXML
    public ImageView blueToken;

    public static Stage stage;

    public static int count;

    @FXML
    public ImageView leftpanel;

    @FXML
    public ImageView rightpanel;

    private Boolean samePos = false;

    public static player currentPlayer;

    public static int currentNumber;

    public static player player1;

    public  static  player player2;

    @FXML
    private Text player1Text;

    @FXML
    private Text player2Text;

    private ArrayList<tiles> tilesArrayList = new ArrayList<tiles>();

    @FXML
    public void gameStart() throws InterruptedException {
        start.setOpacity(0);
        tilesArrayList.add(new tiles(33.6, 564.8));
        tilesArrayList.add(new tiles(70.4, 569.6));
        tilesArrayList.add(new tiles(110.4, 569.6));
        tilesArrayList.add(new tiles(146.4, 572.0));
        tilesArrayList.add(new tiles(186.4, 571.2));
        tilesArrayList.add(new tiles(223.2, 568.8));
        tilesArrayList.add(new tiles(262.4, 570.4));
        tilesArrayList.add(new tiles(300.0, 570.4));
        tilesArrayList.add(new tiles(338.4, 569.6));
        tilesArrayList.add(new tiles(373.6, 572.0));
        tilesArrayList.add(new tiles(375.2, 519.2));
        tilesArrayList.add(new tiles(337.6, 519.2));
        tilesArrayList.add(new tiles(299.2, 518.4));
        tilesArrayList.add(new tiles(262.4, 516.8));
        tilesArrayList.add(new tiles(224.0, 517.6));
        tilesArrayList.add(new tiles(186.4, 517.6));
        tilesArrayList.add(new tiles(148.0, 516.8));
        tilesArrayList.add(new tiles(110.4, 516.8));
        tilesArrayList.add(new tiles(71.2, 516.8));
        tilesArrayList.add(new tiles(33.6, 516.8));
        tilesArrayList.add(new tiles(32.8, 460.8));
        tilesArrayList.add(new tiles(69.6, 465.6));
        tilesArrayList.add(new tiles(109.6, 466.4));
        tilesArrayList.add(new tiles(148.0, 464.0));
        tilesArrayList.add(new tiles(184.8, 464.8));
        tilesArrayList.add(new tiles(224.0, 466.4));
        tilesArrayList.add(new tiles(263.2, 468.0));
        tilesArrayList.add(new tiles(297.6, 466.4));
        tilesArrayList.add(new tiles(337.6, 468.0));
        tilesArrayList.add(new tiles(376.0, 465.6));
        tilesArrayList.add(new tiles(378.4, 413.6));
        tilesArrayList.add(new tiles(336.8, 410.4));
        tilesArrayList.add(new tiles(300.8, 410.4));
        tilesArrayList.add(new tiles(262.4, 412.0));
        tilesArrayList.add(new tiles(224.0, 411.2));
        tilesArrayList.add(new tiles(184.0, 411.2));
        tilesArrayList.add(new tiles(147.2, 410.4));
        tilesArrayList.add(new tiles(110.4, 409.6));
        tilesArrayList.add(new tiles(67.2, 409.6));
        tilesArrayList.add(new tiles(35.2, 410.4));
        tilesArrayList.add(new tiles(32.0, 352.8));
        tilesArrayList.add(new tiles(72.0, 354.4));
        tilesArrayList.add(new tiles(111.2, 354.4));
        tilesArrayList.add(new tiles(148.0, 354.4));
        tilesArrayList.add(new tiles(185.6, 356.0));
        tilesArrayList.add(new tiles(226.4, 355.2));
        tilesArrayList.add(new tiles(261.6, 354.4));
        tilesArrayList.add(new tiles(300.8, 356.8));
        tilesArrayList.add(new tiles(340.0, 357.6));
        tilesArrayList.add(new tiles(377.6, 355.2));
        tilesArrayList.add(new tiles(378.4, 299.2));
        tilesArrayList.add(new tiles(340.8, 299.2));
        tilesArrayList.add(new tiles(300.8, 301.6));
        tilesArrayList.add(new tiles(261.6, 303.2));
        tilesArrayList.add(new tiles(224.8, 303.2));
        tilesArrayList.add(new tiles(186.4, 300.0));
        tilesArrayList.add(new tiles(149.6, 299.2));
        tilesArrayList.add(new tiles(110.4, 299.2));
        tilesArrayList.add(new tiles(70.4, 300.0));
        tilesArrayList.add(new tiles(35.2, 300.0));
        tilesArrayList.add(new tiles(29.6, 248.0));
        tilesArrayList.add(new tiles(72.0, 248.0));
        tilesArrayList.add(new tiles(108.0, 245.6));
        tilesArrayList.add(new tiles(146.4, 246.4));
        tilesArrayList.add(new tiles(186.4, 246.4));
        tilesArrayList.add(new tiles(221.6, 245.6));
        tilesArrayList.add(new tiles(261.6, 245.6));
        tilesArrayList.add(new tiles(300.0, 247.2));
        tilesArrayList.add(new tiles(338.4, 244.8));
        tilesArrayList.add(new tiles(380.8, 243.2));
        tilesArrayList.add(new tiles(376.0, 190.4));
        tilesArrayList.add(new tiles(336.8, 192.0));
        tilesArrayList.add(new tiles(300.8, 190.4));
        tilesArrayList.add(new tiles(263.2, 193.6));
        tilesArrayList.add(new tiles(225.6, 193.6));
        tilesArrayList.add(new tiles(184.0, 198.4));
        tilesArrayList.add(new tiles(149.6, 192.8));
        tilesArrayList.add(new tiles(108.0, 194.4));
        tilesArrayList.add(new tiles(72.0, 195.2));
        tilesArrayList.add(new tiles(36.0, 194.4));
        tilesArrayList.add(new tiles(32.8, 141.6));
        tilesArrayList.add(new tiles(68.8, 140.0));
        tilesArrayList.add(new tiles(108.8, 137.6));
        tilesArrayList.add(new tiles(148.0, 136.0));
        tilesArrayList.add(new tiles(184.8, 136.8));
        tilesArrayList.add(new tiles(220.8, 136.8));
        tilesArrayList.add(new tiles(264.0, 139.2));
        tilesArrayList.add(new tiles(299.2, 139.2));
        tilesArrayList.add(new tiles(335.2, 139.2));
        tilesArrayList.add(new tiles(380.0, 138.4));
        tilesArrayList.add(new tiles(381.6, 80.0));
        tilesArrayList.add(new tiles(340.0, 82.4));
        tilesArrayList.add(new tiles(298.4, 82.4));
        tilesArrayList.add(new tiles(264.8, 82.4));
        tilesArrayList.add(new tiles(226.4, 83.2));
        tilesArrayList.add(new tiles(190.4, 82.4));
        tilesArrayList.add(new tiles(148.8, 82.4));
        tilesArrayList.add(new tiles(110.4, 82.4));
        tilesArrayList.add(new tiles(72.8, 82.4));
        tilesArrayList.add(new tiles(33.6, 80.0));

        player1 = new player(0, redToken, "Player 1");
        player2 = new player(0, blueToken, "Player 2");
        TranslateTransition arrowTranslate = new TranslateTransition(Duration.millis(300), this.arrow);
        arrowTranslate.setToY(6.6);
        arrowTranslate.setAutoReverse(true);
        arrowTranslate.setCycleCount(Animation.INDEFINITE);
        arrowTranslate.play();
    }


    public  void changePlayer(){
        if(count%2==0){
            currentPlayer = player1;
        }
        else{
            currentPlayer = player2;
        }
    }

    @FXML
    public void diceRoll(MouseEvent mouseEvent) throws InterruptedException, IOException {
        Random rand = new Random();
            currentNumber = rand.nextInt(6) + 1;
            File file = new File("src/main/resources/Images/Dice-images/dice" + currentNumber + ".jpeg");
            this.dice.setImage(new Image(file.toURI().toString()));

        changePlayer();
        if(currentPlayer == player1){
            rightpanel.setOpacity(0.5);
            player2Text.setOpacity(0.5);
            player1Text.setOpacity(1);
            leftpanel.setOpacity(1);
        }
        else{
            leftpanel.setOpacity(0.5);
            player2Text.setOpacity(1);
            player1Text.setOpacity(0.5);
            rightpanel.setOpacity(1);
        }
        currentPlayer.makeMovement(tilesArrayList);
        if(player1.getCurrentTile() == player2.getCurrentTile() && samePos == false && player1.getCurrentTile()!=0){
            samePostion();
            samePos = true;
        }
        else{
            if(player1.getCurrentTile() != player2.getCurrentTile() && samePos == true){
                samePos = false;
                redToken.setFitHeight(41);
                redToken.setFitWidth(42);
                redToken.setX(7);
                blueToken.setX(-10);
                blueToken.setFitWidth(42);
                blueToken.setFitHeight(41);
            }
        }
        count++;
    }


    public void samePostion(){
        redToken.setFitHeight(30);
        redToken.setFitWidth(30);
        redToken.setX(-7);
        blueToken.setX(10);
        blueToken.setFitWidth(30);
        blueToken.setFitHeight(30);
    }

    public void Position(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getSceneX() + " " + mouseEvent.getSceneY());
    }

    public void arrowPosition(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getY());
    }

    public void set_Stage(Stage stage) {
        HelloController.stage = stage;
    }

    public void playClicked(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader1.load(), 411, 700);
        stage.setTitle("Snakes and Ladder");
        HelloController rootHelp = fxmlLoader1.getController();
        rootHelp.set_Stage(stage);
        stage.setScene(scene);
        stage.show();
    }

    public void exitClicked(MouseEvent mouseEvent) {
        stage.close();
    }

    public void homeButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelloController.stage.close();
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("homeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader1.load(), 411, 700);
        stage.setTitle("Snakes and Ladder");
        HelloController rootHelp = fxmlLoader1.getController();
        rootHelp.set_Stage(stage);
        stage.setScene(scene);
        stage.show();
    }
}