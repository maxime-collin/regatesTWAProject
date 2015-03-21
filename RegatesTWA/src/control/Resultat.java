package control;

import java.util.HashMap;
import java.util.Map;

public class Resultat {
	private String res = "";
	private Map <String,String> err = new HashMap<String, String>();
	
	public Map<String, String> getErr() {
		return err;
	}
	public void setErr(Map<String, String> err) {
		this.err = err;
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}	
}