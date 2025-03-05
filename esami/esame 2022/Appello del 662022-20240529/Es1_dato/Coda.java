public class Coda {
	private int BUFFERSIZE;
	private int numItems = 0;
	private Elemento[] valori;
	private int first;  // indice elemento in testa alla coda
	private int last;   // indice ultimo elemento inserito nella coda
	public Coda(int bufsize) {
		BUFFERSIZE=bufsize;
		first=0;
		last=0;
		valori=new Elemento[BUFFERSIZE];
	}
	// stampa messaggio dato, preceduto dal nome del thread
	void printWithName(String s, Elemento v) {
		System.out.println(Thread.currentThread().getName()+s+v+"["+numItems+"]");		
	}
	// legge elemento estraendolo dalla testa della coda
	public synchronised Elemento getItem(){
		Elemento tmp;
		while(numItems==0){
		  try{
			wait();
		  }catch(InterruptedException e){}	
		}
		numItems--;
		tmp=valori[first];
		first=(first+1)%BUFFERSIZE;
		}
		printWithName(" estratto ", tmp);
		return tmp;
		notifyAll();
	}
	// inserisce elemento in coda
	public synchronised void setItem(Elemento v) {
		if (numItems!=BUFFERSIZE){
			valori[last]=v;
			last=(last+1)%BUFFERSIZE;
			numItems++;
			printWithName(" scritto ", v);
		}
	}
	// legge elemento dalla testa della coda, senza fare modifiche alla coda
	public synchronised Elemento readItem() {
		Elemento tmp;
		if (numItems==0){
			tmp = new Elemento("boh", -1);
		} else {
			tmp=valori[first];
		}
		printWithName(" letto ", tmp);
		return tmp;
	}
}

