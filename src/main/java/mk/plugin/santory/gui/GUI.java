package mk.plugin.santory.gui;

import java.util.Map;

import mk.plugin.santory.item.modifty.ItemAscents;
import mk.plugin.santory.item.modifty.ItemEnhances;
import mk.plugin.santory.item.modifty.ItemUpgrades;

public enum GUI {
	
	ASCENT(54, "§0§lTINH LUYỆN TRANG BỊ", ItemAscents.getSlots(), ItemAscents.getInputChecker(), ItemAscents.getAmountChecker()),
	UPGRADE(45, "§0§lNÂNG BẬC TRANG BỊ", ItemUpgrades.getSlots(), ItemUpgrades.getInputChecker(), ItemUpgrades.getAmountChecker()),
	ENHANCE(45, "§0§lCƯỜNG HÓA TRANG BỊ", ItemEnhances.getSlots(), ItemEnhances.getInputChecker(), ItemEnhances.getAmountChecker());
	;
	
	private int size;
	private String title;
	private Map<Integer, GUISlot> slots;
	private PlaceChecker placer;
	private AmountChecker amounter;
	
	private GUI(int size, String title, Map<Integer, GUISlot> slots, PlaceChecker placer, AmountChecker amounter) {
		this.size = size;
		this.title = title;
		this.slots = slots;
		this.placer = placer;
		this.amounter = amounter;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public Map<Integer, GUISlot> getSlots() {
		return this.slots;
	}
	
	public PlaceChecker getPlacer() {
		return this.placer;
	}
	
	public AmountChecker getAmounter() {
		return this.amounter;
	}
	
}