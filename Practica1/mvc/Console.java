package mvc;

import java.util.Observer;
import java.util.Observable;

//View
@SuppressWarnings("deprecation")
public class Console implements Observer {
	@Override
	public void update(Observable obs, Object obj) {
		String[] action = (String []) obj;
		if(action[0]=="true") {
			System.out.println(action[1]);
		}else {
			System.out.println("Invalid input");
		}
	}
}
