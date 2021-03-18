package mvc;

import java.util.Observer;
import java.util.Observable;
//View
@SuppressWarnings("deprecation")
public class Console implements Observer {
	@Override
	public void update(Observable obs, Object obj) {
		String[] notify = (String []) obj;
		if(notify[0]=="true") {
			System.out.println(notify[1]);
		}else {
			System.out.println("Invalid input");
		}
	}
}
