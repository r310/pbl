public class Pbl {
	public static void main(String[] args) {
		View view = new View();
		Getdata getdata = new Getdata();
		Savedata savedata = new Savedata();
		Controller controller = new Controller();
		
		view.setController(controller);
		
		controller.setView(view);
		controller.setGetdata(getdata);
		controller.setSavedata(savedata);
	}
}