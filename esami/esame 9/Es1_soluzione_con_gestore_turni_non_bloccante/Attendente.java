
public class Attendente extends Thread {
	int titolare;
	Turno turno;
	Giocatore giocatore; // thread genitore
	Attendente(int chi, Turno t, Giocatore g){
		titolare=chi;
		turno=t;
		giocatore=g;
	}
	public void run() {
		turno.aspettaTurno(titolare);
		giocatore.setFlag(true);
	}
}
