package uet.oop.bomberman.level;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import java.net.URL;
import java.sql.SQLOutput;

public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
	 * từ ma trận bản đồ trong tệp cấu hình
	 */
	private static char[][] _map;
	
	public FileLevelLoader(Board board, int level) throws LoadLevelException {
		super(board, level);
	}





	@Override
	public void loadLevel(int level) {
		// TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
		// TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
		String path = new String();

		path = "/levels/Level" + String.valueOf(level)+".txt";

		BufferedReader br = null;
		FileReader fr = null;
		try {

			InputStream is = this.getClass().getResourceAsStream(path);
			br = new BufferedReader(new InputStreamReader(is));

//			URL fullPath = this.getClass().getClassLoader().getResource('/'+path);

//			System.out.println(fullPath);
//			System.out.println(path);

			//br = new BufferedReader(new FileReader(FILENAME));


//			fr = new FileReader(fullPath);
//			fr = new FileReader("/home/tuan/Desktop/Workspace/bomberman/res/levels/Level1.txt");
//			br = new BufferedReader(fr);
//			InputStream is = this.getClass().getResourceAsStream(path);
//			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String[] option = br.readLine().split(" ");
			_width = Integer.parseInt(option[2]);
			_height = Integer.parseInt(option[1]);
			_level = Integer.parseInt(option[0]);

			_map = new char[_width][_height];
			for (int i = 0; i<_height; i++){
				String line = br.readLine();
				for (int j = 0; j<_width; j++){
					_map[j][i] = line.charAt(j);

				}
			}

		} catch (IOException e) {

			System.out.println(e.getMessage());

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				System.out.println(ex.getMessage());

			}

		}

	}


	@Override
	public void createEntities() {
		// TODO: tạo các Entity của màn chơi
		// TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

		// TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
		// TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình


		for (int i = 0; i < _width; i++) {
			for (int  j= 0; j < _height; j++) {

				if (_map[i][j]== '#') {
					int pos = i +j * _width;
					Sprite sprite = Sprite.wall;
					_board.addEntity(pos, new Wall(i, j, sprite));
				}
				else if (_map[i][j] == '*') {
					_board.addEntity(i + j * _width,
							new LayeredEntity(i, j,
									new Grass(i, j, Sprite.grass),
									new Brick(i, j, Sprite.brick)
							)
					);
				}
				else if(_map[i][j]=='p'){

					_board.addCharacter(new Bomber(Coordinates.tileToPixel(i), Coordinates.tileToPixel(j) + Game.TILES_SIZE, _board));
					Screen.setOffset(0, 0);

					_board.addEntity(i + j * _width, new Grass(i, j, Sprite.grass));
				}
				else if (_map[i][j]=='1'){
					_board.addCharacter(new Balloon(Coordinates.tileToPixel(i), Coordinates.tileToPixel(j) + Game.TILES_SIZE, _board));
					_board.addEntity(i + j * _width, new Grass(i, j, Sprite.grass));

				}
				else if (_map[i][j] == '2'){
					_board.addCharacter(new Oneal(Coordinates.tileToPixel(i), Coordinates.tileToPixel(j) + Game.TILES_SIZE, _board));
					_board.addEntity(i + j * _width, new Grass(i, j, Sprite.grass));
				}
				else if (_map[i][j] == 'f'){
					_board.addEntity(i + j*_width, new LayeredEntity(i, j,
							new Grass(i ,j, Sprite.grass),
							new FlameItem(i ,j,Sprite.powerup_flames),
							new Brick(i ,j, Sprite.brick)) );
				}
				else if (_map[i][j] == 's'){
					_board.addEntity(i + j*_width, new LayeredEntity(i, j,
							new Grass(i ,j, Sprite.grass),
							new SpeedItem(i ,j,Sprite.powerup_speed),
							new Brick(i ,j, Sprite.brick)) );
				}
				else if (_map[i][j] == 'b'){
					_board.addEntity(i + j*_width, new LayeredEntity(i, j,
							new Grass(i ,j, Sprite.grass),
							new BombItem(i ,j,Sprite.powerup_bombs),
							new Brick(i ,j, Sprite.brick)) );
				}

				else if (_map[i][j] == 'x'){
					_board.addEntity(i + j*_width, new LayeredEntity(i, j,
							new Grass(i ,j, Sprite.grass),
							new Portal(i ,j,_board,Sprite.portal),
							new Brick(i ,j, Sprite.brick)) );
				}
				else {
					_board.addEntity(i + j * _width, new Grass(i, j, Sprite.grass));

				}
			}
		}


	}



}
