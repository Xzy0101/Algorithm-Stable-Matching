import java.util.*;

public class GaleShapleyAlgorithm {

	public static void GSA(ArrayList<PrefList> active, ArrayList<PrefList> passive) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		for(int i = 0;i<active.size();++i) {
			queue.add(i);
		}
		
		int elem, pelem, newpos;
		while(!queue.isEmpty()) {
			elem = queue.removeFirst();
			pelem = active.get(elem).next();
			if(pelem == -1) {
				return;
			}
			newpos = passive.get(pelem).search(elem);
			if(passive.get(pelem).getCurrentPos() == -1) {
				passive.get(pelem).setCurrentPos(newpos);
			}else if(passive.get(pelem).getCurrentPos() > newpos) {
				queue.addFirst(passive.get(pelem).getCurrent());
				passive.get(pelem).setCurrentPos(newpos);
			}else {
				queue.addFirst(elem);
			}
		}
	}
	
	public static void main(String[] args) {
		ArrayList<PrefList> active = new ArrayList<PrefList>();
		ArrayList<PrefList> passive = new ArrayList<PrefList>();
		active.add(new PrefList(Arrays.asList(1,0,3,4,2)));
		active.add(new PrefList(Arrays.asList(3,1,0,2,4)));
		active.add(new PrefList(Arrays.asList(1,4,2,3,0)));
		active.add(new PrefList(Arrays.asList(0,3,2,1,4)));
		active.add(new PrefList(Arrays.asList(1,3,0,4,2)));
		passive.add(new PrefList(Arrays.asList(4,0,1,3,2)));
		passive.add(new PrefList(Arrays.asList(2,1,3,0,4)));
		passive.add(new PrefList(Arrays.asList(1,2,3,4,0)));
		passive.add(new PrefList(Arrays.asList(0,4,3,2,1)));
		passive.add(new PrefList(Arrays.asList(3,1,4,2,0)));
		
		GSA(active,passive);
		
		System.out.println("Result:");
		System.out.printf("%d - %d\n", 0, active.get(0).getCurrent());
		System.out.printf("%d - %d\n", 1, active.get(1).getCurrent());
		System.out.printf("%d - %d\n", 2, active.get(2).getCurrent());
		System.out.printf("%d - %d\n", 3, active.get(3).getCurrent());
		System.out.printf("%d - %d\n", 4, active.get(4).getCurrent());
	}
	
}

class PrefList{
	public PrefList(List<Integer> list) {
		this.current = -1;
		this.list = new ArrayList<Integer>();
		for(int i:list) {
			this.list.add(i);
		}
	}
		
	public int getCurrentPos() {
		return this.current;
	}
	
	public void setCurrentPos(int position) {
		this.current = position;
	}
	
	public int getCurrent() {
		return this.list.get(this.current);
	}
	
	public int next() {
		if(this.current<list.size()-1) {
			this.current++;
			return this.list.get(this.current);
		}else {
			return -1;
		}
	}
	
	public int search(int elem) {
		int pos = -1;
		for(int i=0;i<list.size();++i) {
			if(list.get(i) == elem) {
				pos = i;
			}
		}
		return pos;
	}
	
	private int current;
	private ArrayList<Integer> list;
}