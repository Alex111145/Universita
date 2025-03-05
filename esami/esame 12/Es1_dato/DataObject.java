
public class DataObject {
	String content;
	DataObject(){
		content="";
	}
	public String get() {
		String t=content;
		content="";  // il contenuto vene azzerato a ogni lettura
		return t;
	}
	public void set(String s) {
		content=s;
	}
}
