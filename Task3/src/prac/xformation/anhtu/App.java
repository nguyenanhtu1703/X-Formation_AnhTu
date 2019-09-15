package prac.xformation.anhtu;

public class App {
	public static void main(String[] args) {
		MineSweeper mineSweeper = new MineSweeperImp();
		mineSweeper.setMineField("*...\n..*.\n....");
		System.out.println(mineSweeper.getHintField());
	}
}
