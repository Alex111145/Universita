
public class Main {
	public static void main(String[] args) {
		String ns[]={"Ping", "Pong"};
		Table tab=new Table(0);
		new PingPong(0, ns[0], tab).start();
		new PingPong(1, ns[1], tab).start();
	}
}
