
public class Signal {
	int completedConsumers;
	int completedProducers;
	Signal(){
		completedConsumers=0;
		completedProducers=0;
	}
	synchronized void incConsumers() {
		completedConsumers++;
		notifyAll();
	}
	synchronized void incProducers() {
		completedProducers++;
		notifyAll();
	}
	synchronized ProdCons waitSignal() {
		while(completedConsumers+completedProducers==0) {
			try {
				wait();
			} catch (InterruptedException e) { }
		}
		if(completedConsumers>completedProducers) {
			completedConsumers--;
			return ProdCons.CONSUMER;
		} else {
			completedProducers--;
			return ProdCons.PRODUCER;
		}
	}
}
