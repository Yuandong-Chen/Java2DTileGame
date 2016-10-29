package Inventory;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Main;
import mytool.load;

public enum Item {
	RED_BALL("Red Ball",1,load.LoadImageFrom(Main.class, "AKIcon.png")),
	GREEN_BALL("Red Ball",2,load.LoadImageFrom(Main.class, "Bullet.png"));
	
	private int itemID;
	private BufferedImage itemImage;
	private String itemName;
	
	private Item(String itemName,int itemID,BufferedImage itemImage) {
		this.itemName=itemName;
		this.itemID=itemID;
		this.itemImage=itemImage;
	}
	
	public int getItemID() {
		return itemID;
	}
	public BufferedImage getItemImage() {
		return itemImage;
	}
	public String getItemName() {
		return itemName;
	}
}
