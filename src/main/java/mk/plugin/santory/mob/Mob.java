package mk.plugin.santory.mob;

import java.util.List;
import java.util.UUID;

import mk.plugin.santory.item.StatValue;
import mk.plugin.santory.stat.Stat;

public class Mob {

	private UUID id;
	private MobType type;
	private int level;
	private List<StatValue> stats;
	
	private float damageMulti;

	public Mob(UUID id, MobType type, int level, List<StatValue> stats) {
		this.id = id;
		this.type = type;
		this.level = level;
		this.stats = stats;
		this.damageMulti = 1;
	}

	public UUID getID() {
		return this.id;
	}

	public MobType getType() {
		return this.type;
	}

	public int getLevel() {
		return this.level;
	}

	public int getStat(Stat stat) {
		for (StatValue sv : stats) {
			if (sv.getStat() == stat)
				return sv.getValue();
		}
		return 0;
	}

	public float getDamageMulti() {
		return this.damageMulti;
	}
	
	public void setDamageMulti(float multi) {
		this.damageMulti = multi;
	}
	
	public List<StatValue> getStats() {
		return this.stats;
	}

}