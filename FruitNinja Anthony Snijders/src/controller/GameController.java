package controller;

import model.PlayingField;

public class GameController implements Runnable {
	private PlayingField field;
	private boolean active =true;
	private int fps =24;
	private boolean makeNewObject=false;
	
	public GameController(PlayingField field){
		this.field=field;
	}

	@Override
	public void run() {
		field.makeRandomGameObject();
		while(active){
			if(makeNewObject){
				field.makeRandomGameObject();
				makeNewObject=false;
			}
			field.updateGameObject();
			try {
				Thread.sleep(1000/fps);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void disable(){
		active=false;
	}
	
	public void makeNewObject(){
		makeNewObject=true;
	}

}
