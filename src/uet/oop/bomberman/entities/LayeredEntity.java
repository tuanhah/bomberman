package uet.oop.bomberman.entities;


import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.destroyable.DestroyableTile;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.Item;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.graphics.Screen;

import java.net.URL;
import java.util.LinkedList;

import static uet.oop.bomberman.Board.playSound;

/**
 * Chứa và quản lý nhiều Entity tại cùng một vị trí
 * Ví dụ: tại vị trí dấu Item, có 3 Entity [Grass, Item, Brick]
 */
public class LayeredEntity extends Entity {
	
	protected LinkedList<Entity> _entities = new LinkedList<>();
	private boolean canpass = false;
	
	public LayeredEntity(int x, int y, Entity ... entities) {
		_x = x;
		_y = y;
		
		for (int i = 0; i < entities.length; i++) {
			_entities.add(entities[i]); 
			
			if(i > 1) {
				if(entities[i] instanceof DestroyableTile)
					((DestroyableTile)entities[i]).addBelowSprite(entities[i-1].getSprite());
			}
		}
	}
	
	@Override
	public void update() {
		clearRemoved();
		getTopEntity().update();
	}

	public int getLength(){
		return _entities.size();
	}
	
	@Override
	public void render(Screen screen) {
		getTopEntity().render(screen);
	}
	
	public Entity getTopEntity() {
		
		return _entities.getLast();
	}
	
	private void clearRemoved() {
		Entity top  = getTopEntity();
		
		if(top.isRemoved())  {
			_entities.removeLast();
		}
	}
	
	public void addBeforeTop(Entity e) {
		_entities.add(_entities.size() - 1, e);
	}
	
	@Override
	public boolean collide(Entity e) {
		// TODO: lấy entity trên cùng ra để xử lý va chạm
		if (this.getTopEntity() instanceof Brick && e instanceof Flame){
			Entity e1 = this.getTopEntity();
			((Brick)e1).destroy();
			canpass = true;

		}
		if (this.getTopEntity() instanceof Item && e instanceof Bomber){
			Entity item = this.getTopEntity();
			URL resource = getClass().getResource("/music/upgrade.mp3");
			System.out.println(resource.getPath());
			playSound(resource.getPath());
			item.remove();
			if (item instanceof FlameItem) ( (FlameItem) item).use();
			if (item instanceof BombItem) ( (BombItem) item).use();
			if (item instanceof SpeedItem) ( (SpeedItem) item).use();

		}
		if (this.getTopEntity() instanceof Portal && e instanceof Bomber){
			((Portal)this.getTopEntity()).collide(e);
		}
		return canpass;
	}

}
