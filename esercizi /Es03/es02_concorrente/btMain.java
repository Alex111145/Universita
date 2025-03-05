
public class btMain {
	void doSeqSearch(int vx, BinTree bt) {
		BinTree res=bt.seqSearch(vx);
		if(res==null){
			System.out.println("Not found "+vx);
		} else {
			System.out.println("Found "+vx);
		}
	}
	void doParSearch(int vx, BinTree bt) {
		Result resObj=new Result(bt.getSize());
		Thread mySearcher = new ParallelSearchThread(bt, vx, resObj);
		System.out.println("Main: launching thread "+mySearcher.getName());
		mySearcher.start();
		while(!resObj.isCompleted()){
			System.out.println("Main: witing ...");
			try { Thread.sleep(100); } catch (InterruptedException e) { }
		}
		if(resObj.isSuccess()){
			System.out.println("Main: Found "+vx);
		} else {
			System.out.println("Main: Not found "+vx);			
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
		System.out.println("================================");
		doParSearch(13, root);
		System.out.println("================================");
		doParSearch(6, root);
		System.out.println("================================");
		doParSearch(12, root);
	}
	public static void main(String args[]){
		btMain btm=new btMain();
		btm.exec();
	}
}
