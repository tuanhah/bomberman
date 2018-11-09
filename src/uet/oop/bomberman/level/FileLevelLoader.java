package uet.oop.bomberman.level;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

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

		path = "levels/level" + String.valueOf(level)+".txt";

		BufferedReader br = null;
		FileReader fr = null;
		try {
			URL fullPath = FileLevelLoader.class.getResource('/'+path);
//			System.out.println(fullPath.getPath());
//			System.out.println(path);

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader( fullPath.getPath());
			br = new BufferedReader(fr);


			String[] option = br.readLine().split(" ");
			_width = Integer.parseInt(option[2]);
			_height = Integer.parseInt(option[1]);
			_level = Integer.parseInt(option[0]);
			System.out.println(_width);
			System.out.println(_height);
			System.out.println(_level);
			_map = new char[_height][_width];
			for (int i = 0; i<_height; i++){
				String line = br.readLine();
//				System.out.println(line.charAt(i));
				for (int j = 0; j<_width; j++){
//					Syst
					_map[i][j] = line.charAt(j);

				}
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

	@Override
	public void createEntities() {
		// TODO: tạo các Entity của màn chơi
		// TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

		// TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
		// TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình
		// thêm Wall
//		for (int x = 0; x < _width; x++) {
//			for (int y = 0; y < _height; y++) {
//				// thêm Wall
//
////
//				System.out.println();
//				int pos = x + y * _width;
//				Sprite sprite = y == 0 || x == 0 || x == 10 || y == 10 ? Sprite.wall : Sprite.grass;
//				_board.addEntity(pos, new Grass(x, y, sprite));
//
//
//
//				// thêm Bomber
//				int xBomber = 1, yBomber = 1;
//				_board.addCharacter( new Bomber(Coordinates.tileToPixel(xBomber), Coordinates.tileToPixel(yBomber) + Game.TILES_SIZE, _board) );
//				Screen.setOffset(0, 0);
//				_board.addEntity(xBomber + yBomber * _width, new Grass(xBomber, yBomber, Sprite.grass));
//
//				// thêm Enemy
//				int xE = 2, yE = 1;
//				_board.addCharacter( new Balloon(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
//				_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
//
//				// thêm Brick
//				int xB = 3, yB = 1;
//				_board.addEntity(xB + yB * _width,
//						new LayeredEntity(xB, yB,
//								new Grass(xB, yB, Sprite.grass),
//								new Brick(xB, yB, Sprite.brick)
//						)
//				);
//
//				// thêm Item kèm Brick che phủ ở trên
//				int xI = 1, yI = 2;
//				_board.addEntity(xI + yI * _width,
//						new LayeredEntity(xI, yI,
//								new Grass(xI ,yI, Sprite.grass),
//								new SpeedItem(xI, yI, Sprite.powerup_flames),
//								new Brick(xI, yI, Sprite.brick)
//						)
//				);
//			}
//
//		}
	}



}
