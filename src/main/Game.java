package main;
import main.Biome.*;
import main.Item.*;
import main.Entity.*;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class Game {
	//Variable
	private Scene mapScene;
	private Scene inventoryScene;
	private Scene duelScene;
	private Scene venteScene;
	private Biome biome;
	private Player player;
	private Stage primaryStage;
	
	//Cons
	public Game(Stage primaryStage) {
		super();
		
		this.player = new Player();
		this.primaryStage = primaryStage;
		
		this.goToSpawnPoint();
		
        this.mapScene = new Scene(this.loadBiome(), Constantes.STAGE_HEIGHT , Constantes.STAGE_WIDTH);
        this.mapScene.setOnKeyPressed(e -> {
	       	 if(e.getCode().equals(Constantes.KEY_INVENTORY)) {
	       	        System.out.println("Inventaire ouvert");        
	       	        //this.primaryStage.setScene(this.getPlayer().getInventoryScene());
	       	        this.inventoryScene.setRoot(this.loadInventory());
	       	        this.primaryStage.setScene(this.inventoryScene);
	       	    }
	       	 else if(e.getCode().equals(Constantes.KEY_MOVE_TOP)){
		 	        this.playerMoveTop();
		       	 }
	       	 else if(e.getCode().equals(Constantes.KEY_MOVE_BOTTOM)){
	 	 	        this.playerMoveBottom();
	 	       	 }
	       	 else if(e.getCode().equals(Constantes.KEY_MOVE_LEFT)){
	  	 	        this.playerMoveLeft();
	  	       	 }
	       	 else if(e.getCode().equals(Constantes.KEY_MOVE_RIGHT)){
	  	 	        this.playerMoveRight();
	  	       	 }
	       	 else if(e.getCode().equals(Constantes.KEY_INTERACTION)) {
	       		 this.playerInteraction();
	       	 }
	    });
        
        
        this.inventoryScene = new Scene(this.loadInventory(), Constantes.STAGE_HEIGHT, Constantes.STAGE_WIDTH); 
        this.inventoryScene.setOnKeyPressed(e -> {
	       	 if (e.getCode().equals(Constantes.KEY_INVENTORY)) {
	 	        System.out.println("Inventaire ferm�");
	 	        this.primaryStage.setScene(this.mapScene);
	       	 }
        });
        
        Label emptyLabel = new Label("");
        this.duelScene = new Scene(emptyLabel, Constantes.STAGE_HEIGHT, Constantes.STAGE_WIDTH); 
        this.duelScene.setOnKeyPressed(e -> {
	       	 if (e.getCode().equals(Constantes.KEY_INVENTORY)) {
	 	        System.out.println("Hello");
	       	 }
        });

		Label venteLabel = new Label("");
		this.venteScene = new Scene(venteLabel, Constantes.STAGE_HEIGHT, Constantes.STAGE_WIDTH);
		this.venteScene.setOnKeyPressed(e -> {
			if (e.getCode().equals(Constantes.KEY_INVENTORY)) {
				System.out.println("vente");
			}
		});
        
        //Controle Scene
        Label control = new Label();
        control.setAlignment(Pos.CENTER);
        control.setText(" Fleche haut, bas, droite et gauche pour se deplacer.\n F pour interagir avec les items et les PNJ.\n I pour l'inventaire.\n Appuyer sur F pour commencer a jouer");
        
        Scene controlScene = new Scene(control, Constantes.STAGE_HEIGHT , Constantes.STAGE_WIDTH);
        controlScene.setOnKeyPressed(e -> {
	       	 if (e.getCode().equals(Constantes.KEY_INTERACTION)) {
	       		this.primaryStage.setScene(this.mapScene);
	       	 } 
        });
        
        this.primaryStage.setTitle("GAME");
        this.primaryStage.setScene(controlScene);
        this.primaryStage.show();
	}
		
	//GetSet
	public int getMinRowView() {
		return (int) Math.ceil(Constantes.NUMBER_OF_ROW / 2);
	}
	public int getMaxRowView() {
		return this.biome.getWidth() - this.getMinRowView() - 1;
	}
	public int getMinColView() {
		return (int) Math.ceil(Constantes.NUMBER_OF_COL / 2);
	}
	public int getMaxColView() {
		return this.biome.getHeight() - this.getMinRowView() - 1;
	}

	//Methode
	public void goToSpawnPoint() {
		this.biome = Constantes.BIOME_HOUSE;
		this.player.setPosX(2);
		this.player.setPosY(7);
	}
	public void playerMove() {
		if(this.biome.getTile(this.player.getPosX(), this.player.getPosY()).isTpTile()) {
			
			int x = this.biome.getTile(this.player.getPosX(), this.player.getPosY()).getSpawnX();
			int y = this.biome.getTile(this.player.getPosX(), this.player.getPosY()).getSpawnY();
			
			switch(this.biome.getTile(this.player.getPosX(), this.player.getPosY()).getIdTpBiome()) {
			 case 0:
				 this.biome = Constantes.BIOME_VILLAGE;
				 break;
			 case 1:
				 this.biome = Constantes.BIOME_BOSS;
				 break;
			 case 2:
				 this.biome = Constantes.BIOME_HOUSE;
			 	 break;
			default:
				break;
			}
			
			this.player.setPosX(x);
			this.player.setPosY(y);
			this.player.setDirection(Constantes.DIRECTION_SOUTH);
		}
		
		
	}
	public void playerMoveTop() {
		System.out.println("Joueur monte");
		
		int x = this.player.getPosX();
		int y = this.player.getPosY() - 1;
		
		this.player.setDirection(Constantes.DIRECTION_NORTH);
		if(this.biome.moveIsPossible(x, y, this.player)) {
			this.player.setPosY(y);
			this.playerMove();
		}

		this.mapScene.setRoot(this.loadBiome());
	}
	public void playerMoveBottom() {
		System.out.println("Joueur descend"); 
		
		int x = this.player.getPosX();
		int y = this.player.getPosY() + 1;
		
		this.player.setDirection(Constantes.DIRECTION_SOUTH);
		if(this.biome.moveIsPossible(x, y, this.player)) {
			this.player.setPosY(y);
			this.playerMove();
		}	
		this.mapScene.setRoot(this.loadBiome());
	}
	public void playerMoveLeft() {
		System.out.println("Joueur Gauche"); 
		
		int x = this.player.getPosX() - 1;
		int y = this.player.getPosY();
		
		this.player.setDirection(Constantes.DIRECTION_WEST);
		if(this.biome.moveIsPossible(x, y, this.player)) {
			this.player.setPosX(x);
			this.playerMove();
		}
		this.mapScene.setRoot(this.loadBiome());
	}
	public void playerMoveRight() {
		System.out.println("Joueur Droit"); 
		
		int x = this.player.getPosX() + 1;
		int y = this.player.getPosY();
		
		this.player.setDirection(Constantes.DIRECTION_EAST);
		if(this.biome.moveIsPossible(x, y, this.player)) {
			this.player.setPosX(x);
			this.playerMove();
		}
		this.mapScene.setRoot(this.loadBiome());
	}
	
	public void playerInteraction() {
		int x = this.player.getPosX();
		int y = this.player.getPosY();
		
		switch(this.player.getDirection()) {
			case Constantes.DIRECTION_NORTH:
				y -= 1;
				break;
			case Constantes.DIRECTION_SOUTH:
				y += 1;
				break;
			case Constantes.DIRECTION_WEST:
				x -= 1;
				break;
			case Constantes.DIRECTION_EAST:
				x += 1;
				break;
			default:
				break;
		}
		
		if(this.biome.isTileExist(x, y)) {
			if(this.biome.getTile(x, y).getPnj() != null && this.biome.getTile(x, y).getPnj().getDialog() != null) {
				
				//int nLine = this.biome.getTile(x, y).getPnj().getDialog().length;
				//int i = 0;
				if(this.biome.getTile(x, y).getPnj().getName().equals("PNJ new")){
					if(this.player.getInventory().isEmpty()){
						System.out.println(this.player.getInventory().size());
						this.loadTextBox(this.biome.getTile(x, y).getPnj().getDialog()[0]);
					} else {
						int ran = getRandomNumber(0, this.player.getInventory().size());
						this.player.getInventory().remove(ran);
					}
				} else if(this.biome.getTile(x, y).getPnj().getName().equals("Marchand")){
					System.out.println("Debut vente");

					final int xf = x;
					final int yf = y;

					Vente vente = new Vente(this.primaryStage, this.player, this.biome.getTile(xf, yf).getPnj());
					vente.setCloseChangeListener(closeVente -> {
						if (closeVente) {
							this.endVente(vente, xf, yf);
						}
					});
				}
				else {
					this.loadTextBox(this.biome.getTile(x, y).getPnj().getDialog()[0]);

					if(!this.biome.getTile(x, y).getPnj().getInventory().isEmpty()) {
						System.out.println("donne " + this.biome.getTile(x, y).getPnj().getInventoryElement(0).getName());
						this.player.getInventory().add(this.biome.getTile(x, y).getPnj().getInventoryElement(0).deepCopy());
						this.biome.getTile(x, y).getPnj().getInventory().remove(0);
					}
				}

			}
			else if(this.biome.getTile(x, y).getPokemon() != null) {
				System.out.println("Debut du combat");		
				
				final int xf = x;
				final int yf = y;
				
				Duel duel = new Duel(this.primaryStage, this.player, this.biome.getTile(x, y).getPokemon(), xf, yf);
				duel.setCloseChangeListener(isClose -> this.endDuel(duel,xf,yf));
				
				if(this.biome.getTile(x, y).getPokemon().isKo()) {
					this.mapScene.setRoot(this.loadBiome());
					this.biome.getTile(x, y).setPokemon(null);
				}	
			}
			else if(this.biome.getTile(x, y).getItem() != null) {
				
				Item item = this.biome.getTile(x, y).getItem();
				
				this.biome.getTile(x, y).setItem(null);
				this.mapScene.setRoot(this.loadBiome());
				
				this.player.getInventory().add(item);
				this.loadTextBox(this.player.getName() + " obtient " + item.getName());
				
			}

		}
	}

	public void endVente(Vente vente, int x, int y) {
		if (vente.getCloseVente()) {
			this.mapScene.setRoot(this.loadBiome());
			this.primaryStage.setScene(this.mapScene);
		}
	}
	public void endDuel(Duel duel, int x, int y) {
		if (duel.getIsClose()) {
            System.out.println("Fin du combat");
            
            this.mapScene.setRoot(this.loadBiome());
            this.primaryStage.setScene(this.mapScene);
            
            if(duel.isPlayerWin()) {
				if(duel.getPokemon().getReward() != null) {

					this.mapScene.setRoot(this.loadBiome());
					this.loadTextBox(this.player.getName() + " obtient " + duel.getPokemon().getReward().getName());
					this.player.getInventory().add(duel.getPokemon().getReward());

				}

				this.biome.getTile(x, y).setPokemon(null);
				this.mapScene.setRoot(this.loadBiome());
				if(Objects.equals(duel.getPokemon().getName(), "M")) {
					if(!checkPokemonBiome(Constantes.BIOME_BOSS, "M") && !checkPokemonBiome(Constantes.BIOME_VILLAGE, "M") && !checkPokemonBiome(Constantes.BIOME_HOUSE, "M")){
						this.biome.getTile(duel.getxDuel(), duel.getyDuel()).setItem(Constantes.ITEM_POKEBALL);
						this.mapScene.setRoot(this.loadBiome());
					}
				}

			}
            else {
            	this.goToSpawnPoint();
            	this.player.setHp(this.player.getHpMax());
            	this.loadTextBox("Papi vous a sauv� et gu�rit");
            }

       
        }
	}
	//Load
	public GridPane loadBiome() {
		
		GridPane gridPane = new GridPane();

		int endGameStatut = this.checkEndGame();
		if(endGameStatut == Constantes.NO_WIN) {
			int c;
			if(this.player.getPosX() <= this.getMinRowView() ) {
				c = 0;
			}
			else if (this.player.getPosX() >= this.getMaxRowView()) {
				c = this.getMaxRowView() - this.getMinRowView();
			}
			else {
				c = this.player.getPosX() - this.getMinRowView();
			}
			
			int r;
			if(this.player.getPosY() <= this.getMinColView() ) {
				r = 0;
			}
			else if (this.player.getPosY() >= this.getMaxColView()) {
				r = this.getMaxColView() - this.getMinColView();
			}
			else {
				r = this.player.getPosY() - this.getMinColView();
			}
			
		
	        for (int row = 0; row < Constantes.NUMBER_OF_ROW; row++) {
	            for (int col = 0; col < Constantes.NUMBER_OF_COL; col++) {
	               
	                gridPane.add(this.biome.getTile(col + c, row + r).getBloc().getSprite(), col, row);
	                
	                if(this.biome.getTile(col + c, row + r).getPnj() != null) {
	                	gridPane.add(this.biome.getTile(col + c, row + r).getPnj().getSprite(), col, row);
	                }
	                
	                if(this.biome.getTile(col + c, row + r).getPokemon() != null) {
	                	gridPane.add(this.biome.getTile(col + c, row + r).getPokemon().getSprite(), col, row);
	                }
	                
	                if(this.biome.getTile(col + c, row + r).getItem() != null) {

	                	String spritePath = this.biome.getTile(col + c, row + r).getItem().getSpritePath();
	                	ImageView img = new ImageView(new Image(spritePath));
	                	img.setFitHeight(Constantes.CASE_HEIGHT);
	                	img.setFitWidth(Constantes.CASE_WIDTH);
	                	
	                	gridPane.add(img, col, row);
	                }
	                
	            }
	        }
	        System.out.println(this.player.toString());
	     
	        int x;
	        if(this.player.getPosX() <= this.getMinRowView()) {
	        	x = this.player.getPosX();
	        }
	        else if(this.player.getPosX() >= this.getMaxRowView()) {
	        	x = this.player.getPosX() - this.getMaxRowView() + this.getMinRowView();
	        }
	        else {
	        	x = this.getMinRowView();
	        }
	        
	        int y;
	        if(this.player.getPosY() <= this.getMinColView()) {
	        	y = this.player.getPosY();
	        }
	        else if(this.player.getPosY() >= this.getMaxColView()) {
	        	y = this.player.getPosY() - this.getMaxColView() + this.getMinColView();
	        }
	        else {
	        	y = this.getMinColView();
	        }
	        
	        gridPane.add(this.player.getSprite(), x, y);
		}
		else {
			Label l = new Label();
			
			if(endGameStatut  == Constantes.WIN_DRACO) {
				l.setText("Victoire: Vous avez battu dracofeu et sauv� Papi.");
			} else if (endGameStatut == Constantes.GAME_OVER) {
				endGameOver();
			} else if (endGameStatut == Constantes.GAME_WIN) {
				endGameWin();
			}
			
			
			gridPane.setAlignment(Pos.CENTER);
			gridPane.add(l, 0, 0);
		}
		
        return gridPane;
	}
	public void loadTextBox(String line) {
		
		double rectangleWidth = Constantes.STAGE_WIDTH;
        double rectangleHeight = Constantes.STAGE_HEIGHT/5;
        
        ImageView textBox = new ImageView(new Image ("main/img/text.jpg"));
        textBox.setFitHeight(rectangleHeight);
        textBox.setFitWidth(rectangleWidth);
        textBox.setY(rectangleHeight * 4);
     
        Text text = new Text(line);
        text.setFont(Font.font("Arial", 20));
        text.setFill(Color.BLACK);
        text.setY(Constantes.STAGE_HEIGHT*4/5);
        
        text.setLayoutX((rectangleWidth - text.getBoundsInLocal().getWidth()) / 2);
        text.setLayoutY((rectangleHeight - text.getBoundsInLocal().getHeight()) / 2 + text.getBoundsInLocal().getHeight());
        
		Pane root = new Pane();
        root.getChildren().addAll(this.loadBiome(),textBox,text); // Ajouter le GridPane et le rectangle
        this.mapScene.setRoot(root);
	}
	public GridPane loadInventory() {

        GridPane gridPane = new GridPane();

        for (int i = 0; i < Constantes.NUMBER_OF_ROW; i++) {
             for (int j = 0; j < Constantes.NUMBER_OF_COL; j++) {

            	 Rectangle border = new Rectangle(Constantes.CASE_HEIGHT, Constantes.CASE_WIDTH);
                 border.setFill(null);
                 border.setStroke(Color.BLACK);
                 border.setStrokeWidth(1);
                 gridPane.add(border, j, i);

                 GridPane.setMargin(border, new javafx.geometry.Insets(-1));

            	 if(i*Constantes.NUMBER_OF_COL + j < this.player.getInventory().size()) {

            		 Item item = this.player.getInventoryElement(i*Constantes.NUMBER_OF_COL + j);
            		 ImageView img = item.getSprite();


            		 gridPane.add(img, j, i);

					 img.setOnMouseClicked(e -> {
						 System.out.println(item.getName());
						 if(item.isUseableInBiome()){
							 if (item.getnUseRemain() > 1){
								 useObjectInventory(item.getName());
							 } else if (item.getnUseRemain() == 1){
								 useObjectInventory(item.getName());
								 this.player.getInventory().remove(item);
							 }
							 this.inventoryScene.setRoot(this.loadInventory());
						 }
					 });
            	 }
            }
         }

        return gridPane;

	}

	public void useObjectInventory(String itemName){
		if (Objects.equals(itemName, Constantes.ITEM_POTION.getName())){
			this.player.useObject(itemName);
		} else if (Objects.equals(itemName, Constantes.ITEM_OVER.getName())) {
			endGameOver();
		} else if (Objects.equals(itemName, Constantes.ITEM_BOMB_10.getName())) {
			replaceTilesAroundPlayer(3, 30);
		}
	}

	private void replaceTilesAroundPlayer(int radius, double damage) {
		int playerX = this.player.getPosX();
		int playerY = this.player.getPosY();

		int mapWidth = this.biome.getWidth();  // Assuming you have a method to get the map width
		int mapHeight = this.biome.getHeight();  // Assuming you have a method to get the map height

		double currentHp = this.player.getHp();

		if (damage > this.player.getHpMax()) {
			this.player.setHp(0);
		} else {
			this.player.setHp(currentHp-damage);
		}

		// Iterate over the area around the player
		for (int x = Math.max(playerX - radius, 0); x <= Math.min(playerX + radius, mapWidth - 1); x++) {
			for (int y = Math.max(playerY - radius, 0); y <= Math.min(playerY + radius, mapHeight - 1); y++) {

				System.out.println("bomb");

				// Get the current tile
				Tile currentTile = this.biome.getTile(x, y);

				// Check if the tile type is neither BLOC_6 nor BLOC_7
				if (currentTile.getBloc().getId() != Constantes.BLOC_GATE.getId()
						&& currentTile.getBloc().getId() != Constantes.BLOC_NETHER.getId()
						&& currentTile.getBloc().getId() != Constantes.BLOC_HOUSE.getId()
						&& currentTile.getBloc().getId() != Constantes.BLOC_DOOR.getId()
						&& currentTile.getBloc().getId() != Constantes.BLOC_WATER.getId()) {

					currentTile.setBloc(Constantes.BLOC_STONE.deepCopy());
					if (currentTile.getPokemon() != null){
						double currentHpPoke = currentTile.getPokemon().getHp();
						if (damage > currentTile.getPokemon().getHpMax()) {
							currentTile.setPokemon(null);
						} else {
							currentTile.getPokemon().setHp(currentHpPoke-damage);
						}
					}
					currentTile.setItem(null);
					this.mapScene.setRoot(this.loadBiome());
				}
			}
		}
	}

	//Win
	public int checkEndGame() {
		
		if(Constantes.BIOME_BOSS.getTile(5, 7).getPokemon() == null) {
			System.out.println("Vous avez gagn�");
			return Constantes.WIN_DRACO;
		} else if (this.biome.getTile(this.player.getPosX(), this.player.getPosY()).getBloc().getId() == Constantes.BLOC_WATER.getId() && !player.inventoryContainsItem(3)){
			return Constantes.GAME_OVER;
		} else if (!checkPokemonBiome(Constantes.BIOME_BOSS, "B")){
			return Constantes.GAME_WIN;
		} else if (this.player.getHp() == 0){
			return Constantes.GAME_OVER;
		}
		
		return Constantes.NO_WIN;
	}

	public boolean checkPokemonBiome(Biome biome, String pokemonName){
		for( int i = 0; i < biome.getWidth(); i++) {
			for(int j = 0; j < biome.getHeight(); j++) {
				if(biome.getTile(i,j).getPokemon() != null){
					if(biome.getTile(i,j).getPokemon().getName().equals(pokemonName)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean checkPnjBiome(Biome biome, String pnjName){
		for( int i = 0; i < biome.getWidth(); i++) {
			for(int j = 0; j < biome.getHeight(); j++) {
				if(biome.getTile(i,j).getPnj() != null){
					if(biome.getTile(i,j).getPnj().getName().equals(pnjName)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void endGameOver() {

		Label gameOverLabel = new Label("Game Over");
		gameOverLabel.setFont(new Font("Arial", 40));
		gameOverLabel.setTextFill(Color.RED);
		gameOverLabel.setAlignment(Pos.CENTER);

		Pane root = new Pane(gameOverLabel);
		gameOverLabel.setLayoutX((Constantes.STAGE_WIDTH - gameOverLabel.getWidth()) / 2);
		gameOverLabel.setLayoutY((Constantes.STAGE_HEIGHT - gameOverLabel.getHeight()) / 2);

		this.primaryStage.setScene(new Scene(root, Constantes.STAGE_WIDTH, Constantes.STAGE_HEIGHT));
	}

	public void endGameWin() {

		Label gameOverLabel = new Label("Gagné contre B avec X");
		gameOverLabel.setFont(new Font("Arial", 40));
		gameOverLabel.setTextFill(Color.RED);
		gameOverLabel.setAlignment(Pos.CENTER);

		Pane root = new Pane(gameOverLabel);
		gameOverLabel.setLayoutX((Constantes.STAGE_WIDTH - gameOverLabel.getWidth()) / 2);
		gameOverLabel.setLayoutY((Constantes.STAGE_HEIGHT - gameOverLabel.getHeight()) / 2);

		this.primaryStage.setScene(new Scene(root, Constantes.STAGE_WIDTH, Constantes.STAGE_HEIGHT));
	}

	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

}
