import java.util.Random;

public class Client {
	Bank myBank;
	int myAccountNum;
	Client(int accNum, Bank b){
		myBank = b;
		myAccountNum=accNum;
	}
	public void exec() {
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
	public static void main(String[] args) {
		Client c=new Client(1, new Bank());
		c.exec();
	}
}
