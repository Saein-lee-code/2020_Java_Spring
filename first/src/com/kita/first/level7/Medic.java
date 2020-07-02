package com.kita.first.level7;

public class Medic extends Unit{	
	public Medic() {
		super("메딕", 70);		
	}
	public void heal(Unit u) {
		if(u == this || !(u instanceof Carable)) {
			System.out.println("치료할 수 없습니다.");
			return; 
		}
		else {
			System.out.println("치료가 되었습니다.");
			u.setCurrent_hp(u.MAX_HP);
		}
	}	
}
