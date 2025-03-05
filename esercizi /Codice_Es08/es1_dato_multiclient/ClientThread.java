import java.util.Random;

public class ClientThread extends Thread {
	Bank myBank;
	int myAccountNum;
	ClientThread(int accNum, Bank b){
		myBank = b;
		myAccountNum=accNum;
	}
	public void run() {
		Result res;
		OperationRequest req;
		int howMuch=0;
		int times=new Random().nextInt(10);
		for(int i=0; i<times; i++) {
			howMuch=new Random().nextInt(10,1000);
			if(new Random().nextBoolean()) {
				req=new OperationRequest(myAccountNum, howMuch, "Deposit");
			} else {
				req=new OperationRequest(myAccountNum, howMuch, "Withdraw");
			}
			res=myBank.executeOperation(req);
			System.out.println(req);
			System.out.println(res);
		}
		req=new OperationRequest(myAccountNum, 2, "Double");
		res=myBank.executeOperation(req);
		System.out.println(req);
		System.out.println(res);
	}
}
