package platformer.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import platformer.Handler;
import platformer.entities.creatures.Hero;

public class EntityManager {
	
	private Handler handler;
	private Hero hero;
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSorter = new Comparator<Entity>(){
		@Override
		public int compare(Entity a, Entity b) {
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
				return -1;
			return 1;
		}
	};
	
	public EntityManager(Handler handler, Hero hero){
		this.handler = handler;
		this.hero = hero;
		entities = new ArrayList<Entity>();
		addEntity(hero);
	}
	
	public void tick(){
		for(int i = 0;i < entities.size();i++){
			Entity e = entities.get(i);
			e.tick();
			if(!e.isActive())
				entities.remove(e);
		}
		entities.sort(renderSorter);
	}
	
	public void render(Graphics g){
		for(Entity e : entities){
			e.render(g);
		}
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	//GETTERS SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Hero getPlayer() {
		return hero;
	}

	public void setPlayer(Hero hero) {
		this.hero = hero;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}
