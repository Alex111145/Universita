
public class btMain {
	void doTheSearch(int vx, BinTree bt) {
		BinTree res=bt.seqSearch(vx);
		if(res==null){
			System.out.println("Not found "+vx);
		} else {
			System.out.println("Found "+vx);
		}
	}
	void exec() {
		BinTree root=null;
		BinTree bt1 = new BinTree(3, null, null);
		BinTree bt2 = new BinTree(11, bt1, null);
		bt1=new BinTree(4, null, bt2);
		bt2=new BinTree(8, null, bt1);
		root=bt2;
		bt1=new BinTree(2, null, null);
		bt2=new BinTree(12, bt1, null);
		bt1=new BinTree(1, null, null);
		bt2=new BinTree(7, bt1, bt2);
		bt1=new BinTree(9, null, null);
		bt2=new BinTree(5, bt1, bt2);
		root.addLeft(bt2);
		root.printBT(); System.out.println();
		doTheSearch(13, root);
		doTheSearch(6, root);
		doTheSearch(8, root);
		doTheSearch(7, root);
		doTheSearch(3, root);
	}
	public static void main(String args[]){
		btMain btm=new btMain();
		btm.exec();
	}
}
